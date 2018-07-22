package link;

import frame.Packet;
import node.Node;

public class Router2Router extends Link {

	public Router2Router(int delay, int MTU, Node dest) {
		super(delay, MTU, dest);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(Packet packet, Node from, Node to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receive(Node to, Node from) {
		// TODO Auto-generated method stub

	}

}
