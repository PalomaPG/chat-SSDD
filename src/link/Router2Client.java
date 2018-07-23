package link;

import frame.*;
import node.*;

public class Router2Client extends Link {

	public Router2Client(Client dest) {
		super(1, 512, dest);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(Frame frame, Node from, Node to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receive(Node to, Node from) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if (!(o instanceof Router2Client)) return false;
		else {
			Router2Client r2c = (Router2Client)o;
			if(r2c.getDelay()==1 && r2c.getMTU()==512 && (r2c.get_dest()).equals(dest)) 
				return true;
			else return false;
		}
	}

}
