package com.umapper.message;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 可用于创建一级、二级菜单
 * @author lcy
 *
 */
public class WxMenuItem {
	public static final String TYPE_CLICK = "click";
	public static final String TYPE_VIEW  = "view";
	
	private static final int MAX_CHILD_COUNT = 5;
	private String itemName = "";
	private String itemType = "";
	private String itemAttr = "";
	private boolean bSubMenu = true;
	
	private JSONArray subItems = new JSONArray();
	
	//存放click菜单项及处理动作
	private static Map<String, String> keyActions = new HashMap<String, String>();
	
	//click菜单项返回的字符串
	public static final String CONTENT_DEVELOP = "进展：已完成自定义菜单、一级3G网站、自动应答、声音、图像的处理";
	public static final String CONTENT_PLAN = "计划：1）需要服务号升级认证，以调试高级接口；\n" + 
				"2）11.15前完成第一个模板的制作，包括商户从web网站上选取模板、制作素材，微信用户发送特点命令后回复该模板";
	public static final String CONTENT_HELP = "使用说明：1）发送特点命令系统自动回复" +
				"2）发送一段语音回复该语音" +
				"3）发送一张图片回复该图片" +
				"4）发送自己的位置回复该位置的经纬度";
	public static final String CONTENT_SUPPORT_CMD = "支持的命令：1）?：回复帮助中心；\n" +
			"2）model或模板：可查看图文模板";
	
	/**
	 * 创建一个一级或二级菜单
	 * @param name 菜单项名称
	 * @param bSubMenu 是否为二级菜单
	 * @return 返回一个WxMenuItem对象
	 */
	public static WxMenuItem createMenuItem(String name, boolean bSubMenu)
	{		
		WxMenuItem item = new WxMenuItem(name, bSubMenu);		
		return item;
	}
	
	/**
	 * 构造函数
	 * @param itemName 菜单的名字
	 * @param bFirstLevel 是否为二级菜单
	 */
	private WxMenuItem(String name, boolean submenu)
	{
		this.bSubMenu = submenu;
		this.itemName = name;
	}
	
	protected String getName()
	{
		return this.itemName;
	}
	
	protected String getType()
	{
		return this.itemType;
	}
	
	protected String getKeyOrUrl()
	{
		return this.itemAttr;
	}
	
//	public int setKeyOrUrl(String itemType, String itemAttr)
//	{
//		this.itemType = itemType;
//		this.itemAttr = itemAttr;
//		return WxErrCode.ERR_NOERROR;
//	}
//	
	/**
	 * 当菜单类型为click类型时，设置菜单项的key值以及服务器收到click事件时的处理动作
	 * @param keyValue key值，每个菜单项最好唯一
	 * @param keyAction 服务器收到click事件时的处理动作（暂时定义为文本消息，后续优化）
	 */
	public void setClick(String keyValue, String keyAction)
	{
		this.itemType = TYPE_CLICK;
		this.itemAttr = keyValue;
		WxMenuItem.keyActions.put(keyValue, keyAction);
	}
	
	/**
	 * 获取click事件的处理对象
	 * @param keyValue 菜单项对应的key值
	 * @return
	 */
	public static String getClickAction(String keyValue)
	{
		return keyActions.get(keyValue);
	}
	
	/**
	 * 当菜单项为view类型时，设置菜单项点击响应的URL
	 * @param url
	 */
	public void setView(String url)
	{
		this.itemType = TYPE_VIEW;
		this.itemAttr = url;
	}
	
	public int addChild(WxMenuItem item)
	{
		if (bSubMenu)
		{
			return WxErrCode.ERR_OPERATOIN_FORBIDDEN;
		}
		if (subItems.size() == MAX_CHILD_COUNT)
		{
			return WxErrCode.ERR_ALREADY_MAXCOUNT;
		}
		JSONObject subItem = convertSingelItem(item);
		subItems.add(subItems.size(), subItem);
		
		return WxErrCode.ERR_NOERROR;
	}
	
	public JSONObject getJsonObject()
	{
		if (subItems.size() == 0 && !itemType.isEmpty())
		{
			return convertSingelItem(this);
		}
		else if (subItems.size() > 0)
		{
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("name", itemName);
			jsonObj.put("sub_button", subItems);
			return jsonObj;
		}
		return null;
	}
	
	
	private JSONObject convertSingelItem(WxMenuItem item)
	{
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", item.getName());
		jsonObj.put("type", item.getType());
		if (item.getType() == TYPE_CLICK)
		{
			jsonObj.put("key", item.getKeyOrUrl());
		}
		else
		{
			jsonObj.put("url", item.getKeyOrUrl());
		}
		return jsonObj;
	}
}
