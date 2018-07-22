package node;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

import frame.Packet;
import link.*;

public class Router extends Node {

	private Hashtable<String, ArrayList<String>> route_table;
	private Hashtable<String, ArrayList<Link>> link_table;
	
	public Router(String name, String ip) {
		super(name, ip);
		setRoute_table(new Hashtable<String, ArrayList<String>>());
		setLink_table(new Hashtable<String, ArrayList<Link>>());
		// TODO Auto-generated constructor stub
	}

	public void update_route_table(String ip_client, String ip_neighbor, boolean connect) {
		
		if(connect) {
			if(!(route_table.contains(ip_client))) {
				ArrayList<String> ip_lst = new ArrayList<String>();
				ip_lst.add(ip_neighbor);
				route_table.put(ip_client, ip_lst);
			}
			else {
				ArrayList<String> ip_lst = route_table.get(ip_client);
				if (!(ip_lst.contains(ip_neighbor)))
					ip_lst.add(ip_neighbor);
			}
			
		}
		else {
			
		}
		
	}
	
	@Override
	public void connect(Node node) {
		// TODO Auto-generated method stub
		if (link_table.contains(node.getIP())) return;
		else {
			if(node instanceof Client) {
				Router2Client link = new Router2Client((Client)node);
				ArrayList<Link> link_lst = new ArrayList<Link>();
				link_lst.add(link);
				link_table.put(node.getIP(), link_lst);
				/*Check if exist @ route table*/
				this.update_route_table(node.getIP(), this.getIP(),true);
				node.connect(this);
			}
			else return;
		}
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
	public Hashtable<String, ArrayList<String>> getRoute_table() {
		return route_table;
	}

	public void setRoute_table(Hashtable<String, ArrayList<String>> route_table) {
		this.route_table = route_table;
	}

	@Override
	public void disconnect(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if (!(o instanceof Router)) return false;
		else {
			Router r = (Router)o;
			if(r.getIP().equals(this.getIP()) && r.getName().equals(this.getName())) return true;
			else return false;
		}
	}

	public Hashtable<String, ArrayList<Link>> getLink_table() {
		return link_table;
	}

	public void setLink_table(Hashtable<String, ArrayList<Link>> link_table) {
		this.link_table = link_table;
	}


}
