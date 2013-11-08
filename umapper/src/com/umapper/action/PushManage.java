package com.umapper.action;

import java.io.InputStream;

import org.jdom.JDOMException;

import com.umapper.message.WxMenu;
import com.umapper.message.WxMenuItem;
import com.umapper.message.WxMsgConstants;
import com.umapper.message.WxRequest;
import com.umapper.message.WxRequestItf;
import com.umapper.message.WxResponse;
import com.umapper.service.UserService;

/**
 * 微信所有接口入口
 * @author slz 
 * 2013-7-26 上午11:01:08
 */
public class PushManage {
	
	
	
	public String PushManageXml(InputStream is) throws JDOMException{
		String returnStr = "";
		String toName = "";
		String FromName = "";
		String type = "";
		String content = "";
		
		WxRequestItf request = new WxRequest(is);
		toName = request.getToName();
		FromName = request.getFromName();
		type = request.getMsgType();
		
		if(type.equals("event")){
			if(request.getAttribute(WxMsgConstants.ATTR_EVENT_EVENT).equals("subscribe")){//此为关注事件
				content = "您好，欢迎关注我！";
			}
		}else if(type.equals("text")){
			String con = request.getAttribute(WxMsgConstants.ATTR_TEXT_CONTENT);
			if(con.equals("help")||con.equals("帮助")||con.equals("?")||con.equals("？")){
				content = "帮助中心\n\n";
			}else if(con.equals("bind")||con.equals("绑定")){
				content = "点击 <a target='_blank' href=\'\'>绑定账户</a>";
			}else if(con.equals("deng")||con.equals("绑定")){
				content = "好ok";
			}else if(con.startsWith("age")||con.equals("绑定")){
				String name = con.substring(3);
				
				content = new UserService().getUserAge(name);
				
			}else if(con.startsWith("ck")||con.startsWith("CK")){
				String order_id = "0";
				con.toLowerCase();
				if(con.startsWith("ck")){
					order_id = con.substring(con.indexOf("ck")+2);
				}else if(con.startsWith("CK")){
					order_id = con.substring(con.indexOf("CK")+2);
				}
				if(!"".equals(order_id)){
					StringBuffer sb = new StringBuffer();
					
					sb.append("订单详情...\n");
					content = sb.toString();
				}else{
					content = "我不懂，你再解释一下吧!";
				}
				
			}else{
				content = "我不懂，你再解释一下吧!";
			}
		}		else if (type.equals(WxMsgConstants.MSGTYPE_VOICE))
		{
			String mediaId = request.getAttribute(WxMsgConstants.ATTR_VOICE_MEDIAID);
			content = "收到一条语音消息,mediaId=" + mediaId;
			return WxResponse.createVoiceResponse(toName, FromName, mediaId).toString();
		}
		else if (type.equals(WxMsgConstants.MSGTYPE_LOCATION))
		{
			String locationX = request.getAttribute(WxMsgConstants.ATTR_LOCATION_X);
			String locationY = request.getAttribute(WxMsgConstants.ATTR_LOCATION_Y);
			content = "已收到你的位置:x=" + locationX + ", y=" + locationY;
		}
		else if (type.equals(WxMsgConstants.MSGTYPE_VIDEO))
		{
			content = "收到视频";
			String mediaId = request.getAttribute(WxMsgConstants.ATTR_VIDEO_MEDIAID);
			return WxResponse.createVideoResponse(toName, FromName, mediaId, mediaId).toString();
		}
		else if (type.equals(WxMsgConstants.MSGTYPE_IMAGE))
		{
			content = "收到图片";
			String mediaId = request.getAttribute(WxMsgConstants.ATTR_IMAGE_MEDIAID);
			return WxResponse.createImageResponse(toName, FromName, mediaId).toString();
		}
		else if (type.equals(WxMsgConstants.MSGTYPE_LINK))
		{
			content = "收到链接";
		}
		returnStr = WxResponse.createTextResponse(toName, FromName, content).toString();
		return returnStr;
	}
	
	protected WxMenu createMenu()
	{
		//创建一级菜单：显示进展
		WxMenuItem menu1 = WxMenuItem.createMenuItem("进展", false);
		menu1.setClick("MAIN_MENU_DEVELOPMENT", WxMenuItem.CONTENT_DEVELOP);
		
		//创建一级菜单：显示计划
		WxMenuItem menu2 = WxMenuItem.createMenuItem("计划", false);
		menu2.setClick("MAIN_MENU_PLAN", WxMenuItem.CONTENT_PLAN);
		
		//创建一级菜单：帮助
		WxMenuItem menu3 = WxMenuItem.createMenuItem("帮助", false);

		//创建帮助的二级菜单：使用说明
		WxMenuItem subMenu1 = WxMenuItem.createMenuItem("使用说明", true);
		subMenu1.setClick("MAIN_MENU_HELP_HELP", WxMenuItem.CONTENT_HELP);
		WxMenuItem subMenu2 = WxMenuItem.createMenuItem("支持的命令", true);
		subMenu2.setClick("MAIN_MENU_HELP_SUPPORTS", WxMenuItem.CONTENT_SUPPORT_CMD);
		menu3.addChild(subMenu1);
		menu3.addChild(subMenu2);
		
		//将子菜单放入主菜单中
		WxMenu menu = new WxMenu();
		menu.addSubMenu(menu1);
		menu.addSubMenu(menu2);
		menu.addSubMenu(menu3);
		return menu;
	}
	
}
