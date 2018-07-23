package frame;

import java.util.HashMap;

public class SharingTable extends Frame {

	public SharingTable(String IP_orig, String IP_dest, 
			String client, String next_router) {
		super(IP_orig, IP_dest);
		// TODO Auto-generated constructor stub
		HashMap<String, String> content = new HashMap<String, String>();
		content.put(client, next_router);
		
		this.setContent(content);
	}

}
