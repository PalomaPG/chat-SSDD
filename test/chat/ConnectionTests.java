package chat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test; 

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
	}
	
	@Test
	public void node_struct() {
		/*Client*/
		assertFalse(client1.getIP().equals( client2.getIP()));
		assertFalse(client1.getName().equals(client2.getName()));
		client2.setIP("123.123.123.123:1541");
		assertTrue(client2.getIP().equals(client1.getIP()));
		/*Router*/
		
	}
	/*checking route tables....*/
	
	@Test
	public void basic_connection() {
		
		/*Client-Router*/
		/*Router -Router*/
		/*Router-Client*/
	}
	
	@Test
	public void advanced_connection() {
		/*Client-router-client*/
		/*Client- router-router-client*/
		/*Client-router-router/router- client/client*/
	}
	
	
}
