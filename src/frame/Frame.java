package frame;

public class Frame {

	private Object content;
	private String IP_orig;
	private String IP_dest;
	
	public Frame(String IP_orig, String IP_dest) {
		this.setIP_dest(IP_dest);
		this.setIP_orig(IP_orig);
		
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getIP_orig() {
		return IP_orig;
	}

	public void setIP_orig(String iP_orig) {
		IP_orig = iP_orig;
	}

	public String getIP_dest() {
		return IP_dest;
	}

	public void setIP_dest(String iP_dest) {
		IP_dest = iP_dest;
	}
}
