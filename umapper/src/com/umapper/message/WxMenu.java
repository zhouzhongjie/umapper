package com.umapper.message;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用于存放一级菜单，以生成完整的json对象
 * @author lcy
 *
 */
public class WxMenu {
	private JSONArray menus = new JSONArray();
	
	public WxMenu()
	{
		
	}
	
	public boolean addSubMenu(WxMenuItem item)
	{		
		menus.add(item.getJsonObject());
		return true;
	}
	
	public JSONObject getJsonObject()
	{
		if (menus.size() == 0)
		{
			return null;
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("button", menus);
		return jsonObj;
	}
}
