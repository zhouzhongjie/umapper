package com.umapper.message.req;

import com.umapper.message.WxMsgConstants;

public class WxRequestEvent {

	private WxRequestItf request;
	
	public WxRequestEvent(WxRequestItf req)
	{
		this.request = req;
	}
	
	/**
	 * 获取事件值，包括：subscribe,unsubscribe，CLICK,LOCATION,scan
	 * subscribe：用户关注事件
	 * unsubscribe：用户取消关注事件
	 * CLICK：自定义菜单的用户点击事件
	 * LOCATION:用户地理位置上报事件，用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置
	 * scan：扫描带参数二维码事件，当用户已关注时扫描二维码，则发送scan事件；
	 * 		 如用户未关注时扫描二维码，则发送subscribe事件
	 * @return
	 */
	public String getEvent()
	{
		return request.getAttribute(WxMsgConstants.ATTR_EVENT_EVENT);
	}
	
	/**
	 * 二维码的ticket，当Event为scubscribe和scan时有效，可用来换取二维码图片
	 * @return
	 */
	public String getTicket()
	{
		return request.getAttribute(WxMsgConstants.ATTR_EVENT_QRCODE_TICKET);
	}
	
	/**
	 * 获取事件KEY值，当Event为subscribe，scan和CLICK时有效
	 * 当Event为subscribe时，EventKey返回一个qrscene_为前缀的字符串，后面为二维码的参数值
	 * 当Event为scan时，EventKey为一个无符号的32位整数
	 * @return
	 */
	public String getEventKey()
	{
		return request.getAttribute(WxMsgConstants.ATTR_EVENT_EVENTKEY);
	}
		
	/**
	 * 获取地理位置维度，当Event为LOCATION时有效
	 * @return
	 */
	public String getLatitude()
	{
		return request.getAttribute(WxMsgConstants.ATTR_EVENT_LOCATION_LATITUDE);
	}
	
	/**
	 * 获取地理位置经度，当Event为LOCATION时有效
	 * @return
	 */
	public String getLongitude()
	{
		return request.getAttribute(WxMsgConstants.ATTR_EVENT_LOCATION_LONGITUDE);
	}
	
	/**
	 * 获取地理位置精度，当Event为LOCATION时有效
	 * @return
	 */
	public String getPrecision()
	{
		return request.getAttribute(WxMsgConstants.ATTR_EVENT_LOCATION_PRECISION);
	}
}
