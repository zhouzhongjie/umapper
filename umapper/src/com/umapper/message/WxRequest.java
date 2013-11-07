package com.umapper.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class WxRequest implements WxRequestItf{	

	
	protected Map<String, String> attributes = new HashMap<String, String>();
	
	protected String toName = "";
	protected String fromName = "";
	protected String msgType = "";
	protected String msgId = "";
		
	public WxRequest(InputStream is)
	{
		this.parse(is);
	}
	
	@Override
	public String getFromName()
	{
		return this.fromName;
	}
	
	@Override
	public String getToName()
	{
		return this.toName;
	}
		
	@Override
	public String getMsgType()
	{
		return this.msgType;
	}
	
	@Override
	public String getMsgId()
	{
		return this.msgId;
	}
	
	@Override
	public String getAttribute(String attr)
	{
		return attributes.get(attr);
	}
	
	@Override
	public Map<String, String> getAttributes()
	{
		return attributes;
	}
	
	private void parse(InputStream is)
	{
		SAXBuilder sax = new SAXBuilder(); 
		Document doc;
		try {
			doc = sax.build(is);
			//获得文件的根元素
			Element root = doc.getRootElement();
			List list = root.getChildren();
			
			for (int i=0; i<list.size(); i++)
			{
				Element e = (Element) list.get(i);
				String eleName = e.getName();
				String eleValue = e.getValue().trim();
				if (eleName.equals("ToUserName"))
				{
					this.toName = eleValue;
				}
				else if (eleName.equals("FromUserName"))
				{
					this.fromName = eleValue;
				}
				else if (eleName.equals("MsgType"))
				{
					this.msgType = eleValue;
				}
				else if (eleName.equals("MsgId"))
				{
					continue;
				}
				else if (eleName.equals("CreateTime"))
				{
					continue;
				}
				else
				{
					attributes.put(eleName, eleValue);
				}

//				case "MsgId:":
//					msgId = eleValue;
//					break;
//				case "CreateTime:":					
//					break;
//				default:
//					attributes.put(eleName, eleValue);					
//					break;
//				}
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			msgType = "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			msgType = "";
		}
	}

}
