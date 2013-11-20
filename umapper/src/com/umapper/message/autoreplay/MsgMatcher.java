package com.umapper.message.autoreplay;

import java.util.HashMap;
import java.util.List;

import com.umapper.service.AutoReplayServie;

/**
 * 消息匹配类，对收到的文本消息进行匹配
 * 如果存在自定义回复消息，则回复相应的消息；
 * 如果不存在，那么检查是否存在不匹配的回复规则，如果存在，则返回该规则
 * 如果都不存在，那么返回null
 * @author lcy
 *
 */
public class MsgMatcher {

	/**
	 * 找出匹配的回复消息
	 * @param content	请求消息内容
	 * @param user		商户
	 * @return
	 */
	public static AutoReplyMsg match(String content, String user)
	{
		List<HashMap<String, String>> keysMap = AutoReplayServie.getKeys(user);
		String strKey = "";
		boolean bFound = false;
		for (HashMap<String, String> keyMap : keysMap)
		{
			strKey = keyMap.get("key");
			//如果该语句中包含这个关键字，则确认是精确匹配还是模糊匹配
			if (content.contains(strKey))
			{
				String matchrule = keyMap.get("matchrule");
				if (matchrule == AutoReplyKey.MATCH_ACCURATE)
				{
					//语句与关键字不精确匹配，则说明没有匹配的自定义回复
					if (!content.equals(strKey))
					{
						return null;
					}
					else
					{
						bFound = true;
					}
				}
				else
				{
					bFound = true;
				}
				break;
			}
		}
		if (!bFound)
		{
			return null;
		}
		
		return AutoReplayServie.getReplyMsg(user, strKey);
	}
}
