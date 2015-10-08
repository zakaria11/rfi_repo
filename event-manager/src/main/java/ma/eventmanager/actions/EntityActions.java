package ma.eventmanager.actions;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.model.EventVo;
import ma.eventmanager.util.ProjectHelper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/entity")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class EntityActions extends AbstractAction{

	@Autowired private EventManagerDao eventManagerDao;

	Map<String,Object> resp = new HashMap<String, Object>();
	private String entityName;
	private String entityId;
	private EventVo event;
	
	@Action(value = "load", results = {@Result  (name = "success", type = "json",params = {"root", "resp" })})
	public String load() throws IOException{
		
		Event ev = (Event) eventManagerDao.retrieve(entityName,entityId);
		ProjectHelper.sendObjectAsJsonResponse(ev,ServletActionContext.getResponse());
		return null;
	}

	@Action(value = "delete", results = {@Result  (name = "success", type = "json",params = {"root", "resp" })})
	public String entity() throws IOException{
		if("event".equals(entityName)){
			eventManagerDao.deleteEvent(Integer.parseInt(entityId));
		}		
		return SUCCESS;
	}

	@Action(value = "modify", results = {@Result  (name = "success", type = "json",params = {"root", "resp" })})
	public String update() throws IOException, ParseException{
		if("event".equals(entityName)){
			eventManagerDao.updateEvent(new Event(event));
			resp.put("isOK", "1");
		}		
		return SUCCESS;
	}
	
	public String getEntityName()
	{
		return entityName;
	}

	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}

	public String getEntityId()
	{
		return entityId;
	}

	public void setEntityId(String entityId)
	{
		this.entityId = entityId;
	}

	public EventVo getEvent()
	{
		return event;
	}

	public void setEvent(EventVo event)
	{
		this.event = event;
	}

	public Map<String, Object> getResp()
	{
		return resp;
	}

	public void setResp(Map<String, Object> resp)
	{
		this.resp = resp;
	}

	
	
	
	

}
