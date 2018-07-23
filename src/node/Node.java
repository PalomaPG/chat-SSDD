package node;

import java.util.HashMap;

import frame.*;

import java.util.ArrayList;

public abstract class Node {

	private String name, IP;
	private HashMap<String, ArrayList<Message>> packets;
	
	/*Abstract methods*/
	public abstract void disconnect(Node node);
	public abstract void connect(Node node);
	public abstract void send(Frame frame);
	public abstract void receive();
	
	/*Constructor and other methods*/
	public Node(String name, String ip) {
		this.setName(name);
		this.setIP(ip);
		this.setPackets(new HashMap<String, ArrayList<Message>>());
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

	public HashMap<String, ArrayList<Message>> getPackets() {
		return packets;
	}

	public void setPackets(HashMap<String, ArrayList<Message>> packets) {
		this.packets = packets;
	}
	
}
