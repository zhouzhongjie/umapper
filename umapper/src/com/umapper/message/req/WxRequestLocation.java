package com.umapper.message.req;

import com.umapper.message.WxMsgConstants;

public class WxRequestLocation {

	private WxRequestItf request = null;

	public WxRequestLocation(WxRequestItf request) {
		this.request = request;
	}
	
	/**
	 * 维度
	 * @return
	 */
	public String getLocationX()
	{
		return request.getAttribute(WxMsgConstants.ATTR_LOCATION_X);
	}
	
	/**
	 * 经度
	 * @return
	 */
	public String getLocationY()
	{
		return request.getAttribute(WxMsgConstants.ATTR_LOCATION_Y);
	}
	
	/**
	 * 缩放比例
	 * @return
	 */
	public String getScale()
	{
		return request.getAttribute(WxMsgConstants.ATTR_LOCATION_SCALE);
	}
	
	/**
	 * 获取地理位置信息
	 * @return
	 */
	public String getLabel()
	{
		return request.getAttribute(WxMsgConstants.ATTR_LOCATION_LABEL);
	}
}
