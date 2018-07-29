package node;

import java.util.ArrayList;

import frame.*;
import link.*;

public class Client extends Node {

	private Client2Router link;
	
	public Client(String name, String ip) {
		super(name, ip);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect(Node node) {
		// TODO Auto-generated method stub
		link = new Client2Router((Router)node);
		Router r = (Router)node;
		if(!(r.getLink_table().containsKey(this.getIP()))) node.connect(this);
	}

	public void send(String dest_IP, String msg) {
		Message m = new Message(this.getIP(), dest_IP, msg);
		send(m);
	}
	@Override
	public void send(Frame frame) {
		// TODO Auto-generated method stub
		link.send(frame);
	}

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		ArrayList<Frame> frames = this.getFrames();
		while(frames.size()>0) {
			Message  msg = (Message)frames.remove(0);
			System.err.println((String)msg.getContent());
			break;
		}
	}

	@Override
	public void disconnect(Node node) {
		// TODO Auto-generated method stub
		
	}

	public Client2Router getLink() {
		// TODO Auto-generated method stub
		return link;
	}

	public String getRouterIP() {
		// TODO Auto-generated method stub
		return link.get_dest().getIP();
	}

}
