package link;

import java.util.ArrayList;

import frame.*;
import node.*;

public abstract class Link {

	private int delay, MTU; 
	protected Node dest;
	
	public abstract void send(Packet packet, Node from, Node to);
	public abstract void receive(Node to, Node from);
	
	public Link(int delay, int MTU, Node dest) {
		this.setDelay(delay);
		this.setMTU(MTU);
		this.set_dest(dest);
	}
	
	public ArrayList<Packet>shater_packet(Packet packet){
		ArrayList<Packet> packets = new ArrayList<Packet>();
		return packets;
	}
	
	/*Getters and setters*/
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getMTU() {
		return MTU;
	}

	public void setMTU(int mTU) {
		MTU = mTU;
	}

	public Node get_dest() {
		return dest;
	}

	public void set_dest(Node dest) {
		this.dest = dest;
	}
}
