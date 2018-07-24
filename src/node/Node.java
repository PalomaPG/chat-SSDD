package node;

import java.util.HashMap;
import java.util.ArrayList;

import frame.*;


public abstract class Node {

	private String name, IP;
	private ArrayList<Frame> frames;
	
	/*Abstract methods*/
	public abstract void disconnect(Node node);
	public abstract void connect(Node node);
	public abstract void send(Frame frame);
	public abstract void receive();
	
	/*Constructor and other methods*/
	public Node(String name, String ip) {
		this.setName(name);
		this.setIP(ip);
		this.setFrames(new ArrayList<Frame>());
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

	public ArrayList<Frame> getFrames() {
		return frames;
	}

	public void setFrames(ArrayList<Frame> frames) {
		this.frames = frames;
	}
	public void enqueue_frame(Frame frame) {
		// TODO Auto-generated method stub
		frames.add(frame);
	}
	
}
