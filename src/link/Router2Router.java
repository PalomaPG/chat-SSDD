package link;

import frame.*;
import node.*;

public class Router2Router extends Link {

	public Router2Router(int delay, int MTU, Node dest) {
		super(delay, MTU, dest);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(Frame frame) {
		// TODO Auto-generated method stub
		if((frame.getIP_dest()).equals(dest.getIP())) {
			dest.enqueue_frame(frame);
		}
		else {
			dest.send(frame);
		}
	}

	@Override
	public void receive(String to, String from) {
		// TODO Auto-generated method stub

	}

}
