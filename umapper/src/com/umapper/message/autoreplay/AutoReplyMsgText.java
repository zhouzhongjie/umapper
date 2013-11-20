package com.umapper.message.autoreplay;

import com.umapper.message.WxMsgConstants;

public class AutoReplyMsgText extends AutoReplyMsg{

	public AutoReplyMsgText() {
		// TODO Auto-generated constructor stub
		type = WxMsgConstants.MSGTYPE_TEXT;
	}

	
	@Override
	public void addContent(String desp, String content) {
		// TODO Auto-generated method stub
		jsonReplay.put(WxMsgConstants.ATTR_TEXT_CONTENT, content);		
	}		
	
}
