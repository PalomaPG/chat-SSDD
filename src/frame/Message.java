package frame;

public class Message extends Frame{

	public Message(String IP_orig, String IP_dest, String msg) {
		super(IP_orig, IP_dest);
		this.setContent(msg);
		// TODO Auto-generated constructor stub
		
	}

}
