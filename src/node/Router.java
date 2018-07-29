package node;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

import frame.*;
import link.*;

public class Router extends Node {

	private HashMap<String, ArrayList<String>> route_table;
	private HashMap<String, Link> link_table;
	
	public Router(String name, String ip) {
		super(name, ip);
		setRoute_table(new HashMap<String, ArrayList<String>>());
		setLink_table(new HashMap<String, Link>());
		// TODO Auto-generated constructor stub
	}

	public void update_route_table(String ip_client, String ip_neighbor, boolean connect) {
		
		if(connect) {
			if(!(route_table.containsKey(ip_client))) {
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
		if (link_table.containsKey(node.getIP()))
			return;

		else {
			if(node instanceof Client) {
				Router2Client link = new Router2Client((Client)node);
				link_table.put(node.getIP(), link);
				/*Check if exist @ route table*/
				this.update_route_table(node.getIP(), this.getIP(),true);
				node.connect(this);
			}
			else {
				Router2Router link = new Router2Router(0,0, (Router)node);
				link_table.put(node.getIP(), link);
				node.connect(this);
				
			}
		}
	}

	@Override
	public void send(Frame frame) {
		// TODO Auto-generated method stub
		String dest_IP = frame.getIP_dest();
		Link link = this.link_table.get(dest_IP);
		if(link!=null) {
			link.send(frame);
		}
		else {
			/*Check route table*/
		}
	}

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		ArrayList<Frame> frames = this.getFrames();
		while(frames.size()>0) {
			Frame frame = frames.remove(0);
			if(frame instanceof SharingTable) {
				HashMap<String, String> content =(HashMap<String, String>) ((SharingTable) frame).getContent();
				for(String key: content.keySet()) {
					if (!(this.route_table.containsKey(key))) {
						ArrayList<String> lst = new ArrayList<String>();
						lst.add(content.get(key));
						this.route_table.put(key,lst);
					}
					else {
						ArrayList<String> lst = this.route_table.get(key);
						String str = content.get(key);

						if(!(lst.contains(str))) lst.add(str);
					}
				}
			}
			else {/*Si es un mensaje*/
				
			}
				
		}
		
	}

	/*Getters and setters*/
	public HashMap<String, ArrayList<String>> getRoute_table() {
		return route_table;
	}

	public void setRoute_table(HashMap<String, ArrayList<String>> route_table) {
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

	public HashMap<String, Link> getLink_table() {
		return link_table;
	}

	public void setLink_table(HashMap<String, Link> link_table) {
		this.link_table = link_table;
	}


	public void share_route_table(String dest_IP, String from, String to) {
		
		SharingTable st = new SharingTable(this.getIP(), dest_IP, to, from);
		share_route_table(st);
	}
	
	public void share_route_table(SharingTable st) {
		// TODO Auto-generated method stub
		Router2Router link = (Router2Router)this.link_table.get(st.getIP_dest());
		
		if (link!=null) {
			link.send(st);
		}
		else {
			for (String key: this.link_table.keySet()) {
				Link aux_link = this.link_table.get(key);
				if(aux_link instanceof Router2Router) {
					aux_link.send(st);
				}
			}
		}
		
	}


}
