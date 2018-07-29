package chat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test; 

import frame.*;
import node.*;
import link.*;

public class MsgTransferTest {

	public static final String EXAMPLE_IP1 = "123.123.123.113:1541";
	public static final String EXAMPLE_IP2 = "123.123.123.114:2323";
	public static final String msg1 = "HELLO";
	public static final String msg2 = "HOLA";
	public static final String msg3 = "CIAO";
	
	protected Client client1, client2;
	protected Router router1, router2, router3;
	
	@Before
	public void setUp() {
		client1 = new Client("client1", "123.123.123.123:1541");
		client2 = new Client("client2", "123.123.123.123:2321");
		router1 = new Router("R1", "123.123.123.121:1540");
		router2 = new Router("R2", "123.123.123.122:2320");
		router3 = new Router("R3", "123.123.123.122:2321");
	}
	
	@Test
	public void basicMsgTest() {
		client1.connect(router1);
		router1.connect(router2);
		router2.connect(router3);
		router3.connect(client2);
		
		router1.share_route_table(router2.getIP(), router1.getIP(), client1.getIP());
		router3.share_route_table(router2.getIP(), router3.getIP(), client2.getIP());
		router1.share_route_table(router3.getIP(), router2.getIP(), client1.getIP());
		router3.share_route_table(router1.getIP(), router2.getIP(), client2.getIP());
		
		router1.receive();
		router2.receive();
		router3.receive();
		
		System.err.println(router1.getRoute_table());
		System.err.println(router2.getRoute_table());
		System.err.println(router3.getRoute_table());
		
		client1.send(client2.getIP(), msg1);
		client2.receive();
		
		
	}
}
