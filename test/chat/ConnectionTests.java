package chat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test; 

import frame.*;
import node.*;
import link.*;

public class ConnectionTests {

	public static final String EXAMPLE_IP1 = "123.123.123.113:1541";
	public static final String EXAMPLE_IP2 = "123.123.123.114:2323";
	
	protected Node client1, router1;
	protected Client client2, client3;
	protected Router router2, router3, router4;
	
	@Before
	public void setUp() {
		client1 = new Client("client1", "123.123.123.123:1541");
		client2 = new Client("client2", "123.123.123.123:2321");
		router1 = new Router("R1", "123.123.123.121:1540");
		router2 = new Router("R2", "123.123.123.122:2320");
		router3 = new Router("R3", "123.123.123.122:2321");
		router4 = new Router("R4", "123.123.123.122:2324");
	}
	
	@Test
	public void node_struct() {
		/*Client*/
		assertFalse(client1.getIP().equals( client2.getIP()));
		assertFalse(client1.getName().equals(client2.getName()));
		client2.setIP("123.123.123.123:1541");
		assertTrue(client2.getIP().equals(client1.getIP()));
		/*Router*/
		assertFalse(router1.getIP().equals( router2.getIP()));
		assertFalse(router1.getName().equals(router2.getName()));
		router1.setIP("123.123.123.122:2320");
		assertTrue(client2.getIP().equals(client1.getIP()));
	}
	/*checking route tables....*/
	
	@Test
	public void basic_connection() {
		
		/*Client- router -router - client*/
		assertTrue(((Router)router1).getRoute_table().isEmpty());
		assertTrue(router2.getRoute_table().isEmpty());
		assertTrue(((Router)router1).getLink_table().isEmpty());
		assertTrue(router2.getLink_table().isEmpty());
		/****CLIENT-ROUTER*****/
		client1.connect(router1);
		//Verify client1 link
		Client2Router c2r_1 = new Client2Router((Router)router1);
		assertTrue(c2r_1.equals(((Client)client1).getLink()));
		assertTrue(((Client)client1).getRouterIP().equals(router1.getIP()));
		//Verify router1 route_table
		Router2Client r2c_1 = new Router2Client((Client) client1);
		HashMap<String, ArrayList<String>> rt_1 = ((Router)router1).getRoute_table();
		assertFalse(rt_1.isEmpty());
		HashMap<String, Link> lt_1 =  ((Router)router1).getLink_table();
		assertFalse(lt_1.isEmpty());
		assertTrue((rt_1.get(client1.getIP()).contains(router1.getIP())));
		Router2Client r2c = (Router2Client)lt_1.get(client1.getIP());
		assertTrue(r2c.equals(r2c_1));
		
		/***ROUTER-CLIENT***/
		router2.connect(client2);
		//Verify client2 link
		Client2Router c2r_2 = new Client2Router(router2);
		assertTrue(c2r_2.equals((client2).getLink()));
		assertTrue((client2).getRouterIP().equals(router2.getIP()));
		//Verify router2 route_table
		Router2Client r2c_2 = new Router2Client(client2);
		HashMap<String, ArrayList<String>> rt_2 = (router2).getRoute_table();
		assertFalse(rt_2.isEmpty());
		HashMap<String, Link> lt_2 =  (router2).getLink_table();
		assertFalse(lt_2.isEmpty());
		assertTrue((rt_2.get(client2.getIP()).contains(router2.getIP())));
		r2c = (Router2Client)lt_2.get(client2.getIP());
		assertTrue(r2c.equals(r2c_2));
		
		/***ROUTER-ROUTER***/
		
		((Router)router1).connect(router2);
		Router2Router r2r_1 = new Router2Router(0,0, router2);
		Router2Router r2r_2 = new Router2Router(0,0, router1);
		lt_1=((Router)router1).getLink_table();
		lt_2=router2.getLink_table();
		assertTrue(lt_1.containsKey(router2.getIP()));
		assertTrue(lt_2.containsKey(router1.getIP()));
		
		/*Sharing route table*/
		SharingTable st_21 = new SharingTable(router2.getIP(), router1.getIP(), client2.getIP(), router2.getIP());
		SharingTable st_12 = new SharingTable(router1.getIP(), router2.getIP(), client1.getIP(), router1.getIP());
		router2.share_route_table(st_21);
		((Router)router1).share_route_table(st_12);
		assertTrue(router2.getFrames().size()>0);
		assertTrue(router1.getFrames().size()>0);
		router2.receive();
		((Router)router1).receive();
		assertTrue(router2.getRoute_table().size()>1);		
		assertTrue(((Router)router1).getRoute_table().size()>1);
		
		
	}
	
	@Test
	public void advanced_connection() {

		/*client1-router1-router3/router4- router2-client2*/
		client1.connect((Router) router1);
		client2.connect(router2);
		((Router)router1).connect(router3);
		((Router)router1).connect(router4);		
		router2.connect(router3);
		router2.connect(router4);
		
		Client2Router link1 = new Client2Router((Router) router1);
		assertTrue(((Client)client1).getLink().equals(link1));

		Client2Router link2 = new Client2Router(router2);
		assertTrue(client2.getLink().equals(link2));
		
		/*Check routers link lists */
		HashMap<String, Link> lst1 = ((Router)router1).getLink_table();
		
		
		{
			assertTrue(lst1.containsKey(client1.getIP()));
			assertTrue(lst1.containsKey(router3.getIP()));
			assertTrue(lst1.containsKey(router4.getIP()));
			
		}
		
		HashMap<String, Link> lst2 = router2.getLink_table();
		
		{
			assertTrue(lst2.containsKey(client2.getIP()));
			assertTrue(lst2.containsKey(router3.getIP()));
			assertTrue(lst2.containsKey(router4.getIP()));
			
		}
		
		assertEquals(lst1.size(),3);
		assertEquals(lst2.size(),3);
		
		HashMap<String, Link> lst3 = router3.getLink_table();
		
		{
			assertTrue(lst3.containsKey(router2.getIP()));
			assertTrue(lst3.containsKey(router1.getIP()));
		}
		HashMap<String, Link> lst4 = router4.getLink_table();
		
		{
			assertTrue(lst4.containsKey(router2.getIP()));
			assertTrue(lst4.containsKey(router1.getIP()));
		}
		
		assertEquals(lst3.size(),2);
		assertEquals(lst4.size(),2);
		
		/*Check route tables*/
		HashMap <String, ArrayList<String>> rt1 = ((Router) router1).getRoute_table();
		HashMap <String, ArrayList<String>> rt2 = router2.getRoute_table();
		HashMap <String, ArrayList<String>> rt3 = router3.getRoute_table();
		HashMap <String, ArrayList<String>> rt4 = router4.getRoute_table();
		
		assertEquals(rt1.size(), 1);
		assertEquals(rt2.size(), 1);
		assertEquals(rt3.size(), 0);
		assertEquals(rt4.size(), 0);
		
		((Router) router1).share_route_table(router3.getIP(), router1.getIP(), client1.getIP());
		router2.share_route_table(router3.getIP(), router2.getIP(), client2.getIP());
		((Router) router1).share_route_table(router4.getIP(), router1.getIP(), client1.getIP());
		router2.share_route_table(router4.getIP(), router2.getIP(), client2.getIP());

		router2.share_route_table(router1.getIP(), router4.getIP(), client2.getIP());
		router2.share_route_table(router1.getIP(), router3.getIP(), client2.getIP());
		
		((Router)router1).share_route_table(router2.getIP(), router3.getIP(), client1.getIP());
		((Router)router1).share_route_table(router2.getIP(), router4.getIP(), client1.getIP());

		((Router)router1).receive();
		router2.receive();
		router3.receive();
		router4.receive();
		
		rt1 = ((Router) router1).getRoute_table();
		rt2 = router2.getRoute_table();
		rt3 = router3.getRoute_table();
		rt4 = router4.getRoute_table();

		assertEquals(rt1.get(client2.getIP()).size(), 2);
		assertEquals(rt2.get(client1.getIP()).size(), 2);
		assertEquals(rt3.size(), 2);
		assertEquals(rt4.size(), 2);

	}
	
	
}
