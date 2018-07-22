package node;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

import frame.Packet;
import link.*;

public class Router extends Node {

	private Hashtable<String, ArrayList<Link>> route_table;
	
	public Router(String name, String ip) {
		super(name, ip);
		setRoute_table(new Hashtable<String, ArrayList<Link>>());
		// TODO Auto-generated constructor stub
	}

	public void update_route_table(Link link, boolean connect) {
		
		Node next = link.get_dest();
		String IP = next.getIP();
		
		if(route_table.contains(IP)) {
			return;
		
		}
		else{
			if(next instanceof Client) {
				
				ArrayList<Link> lst = new ArrayList<Link>();
				lst.add(link);
				route_table.put(IP, lst);
			}
			
			else if(next instanceof Router) {
				Router next_router = (Router)next;
				Hashtable<String, ArrayList<Link>> route_table_neighbor = next_router.getRoute_table(); 
				//Router2Router link = new Router2Router();
				//ArrayList<Link> lst = new ArrayList<Link>();
			}
		
			else 
				throw new java.lang.Error("Nodo debe ser Router o Client");	
		}
	}
	
	@Override
	public void connect(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(Packet packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		
	}

	/*Getters and setters*/
	public Hashtable<String, ArrayList<Link>> getRoute_table() {
		return route_table;
	}

	public void setRoute_table(Hashtable<String, ArrayList<Link>> route_table) {
		this.route_table = route_table;
	}

	@Override
	public void disconnect(Node node) {
		// TODO Auto-generated method stub
		
	}

}
