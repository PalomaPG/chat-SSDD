package link;

import frame.Packet;
import node.*;

public class Router2Client extends Link {

	public Router2Client(Client dest) {
		super(1, 512, dest);
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
