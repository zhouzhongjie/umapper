package com.umapper.message.req;

import com.umapper.message.WxMsgConstants;

public class WxRequestVoice {

	private WxRequestItf request = null;

	public WxRequestVoice(WxRequestItf request) {
		this.request = request;
	}
	
	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
	 * @return
	 */
	public String mediaId()
	{
		return this.request.getAttribute(WxMsgConstants.ATTR_VOICE_MEDIAID);
	}
	
	/**
	 * 语音格式，如amr，speex等
	 * @return
	 */
	public String format()
	{
		return this.request.getAttribute(WxMsgConstants.ATTR_VOICE_FORMAT);
	}
	
	public String recognition()
	{
		return this.request.getAttribute(WxMsgConstants.ATTR_VOICE_RECOGNITION);
	}
}
