package com.umapper.message.autoreplay;

import com.umapper.service.AutoReplayServie;

public class Test {
	
	public static void main(String[] args)
	{
		AutoReplyKey key = new AutoReplyKey("?,？", AutoReplyKey.MATCH_APPROXIMATE);
		AutoReplyMsg msg = new AutoReplyMsgText();
		msg.addContent("", "帮助");
		AutoReplayServie.addReplyRule("user", key, msg);

	}

}
