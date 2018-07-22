package node;

import java.util.Hashtable;

import frame.*;

import java.util.ArrayList;

public abstract class Node {

	private String name, IP;
	private Hashtable<String, ArrayList<Packet>> packets;
	
	/*Abstract methods*/
	public abstract void disconnect(Node node);
	public abstract void connect(Node node);
	public abstract void send(Packet packet);
	public abstract void receive();
	
	/*Constructor and other methods*/
	public Node(String name, String ip) {
		this.setName(name);
		this.setIP(ip);
		this.setPackets(new Hashtable<String, ArrayList<Packet>>());
	}
	

	
	/*Getters and setters*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Hashtable<String, ArrayList<Packet>> getPackets() {
		return packets;
	}

	public void setPackets(Hashtable<String, ArrayList<Packet>> packets) {
		this.packets = packets;
	}
	
}
