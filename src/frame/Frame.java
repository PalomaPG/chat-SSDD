package frame;

public class Frame {

	private Object content;
	private String IP_orig;
	private String IP_dest;
	
	public Frame(String IP_orig, String IP_dest) {
		this.IP_dest = IP_dest;
		this.IP_orig = IP_orig;
		
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
