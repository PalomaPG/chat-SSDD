package link;

import node.*;

import java.util.HashMap;

import frame.*;

public class Client2Router extends Link {

	public Client2Router(Router dest) {
		super(1, 512, dest);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(Frame frame) {
		// TODO Auto-generated method stub
		((Router)dest).send(frame);
		
	}

	@Override
	public void receive(String to, String from) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if (!(o instanceof Client2Router)) return false;
		else {
			Client2Router c2r = (Client2Router)o;
			if(c2r.getDelay()==1 && c2r.getMTU()==512 && (c2r.get_dest()).equals(dest)) 
				return true;
			else return false;
		}
	}

}
