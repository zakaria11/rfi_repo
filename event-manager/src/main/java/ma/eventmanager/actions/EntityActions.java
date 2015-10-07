package ma.eventmanager.actions;

import java.io.IOException;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Event;
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

	
	private String entityName;
	private String entityId;
	
	@Action(value = "load", results = {@Result  (name = "success", type = "json")})
	public String load() throws IOException{
		
		Event ev = (Event) eventManagerDao.retrieve(entityName,entityId);
		ProjectHelper.sendObjectAsJsonResponse(ev,ServletActionContext.getResponse());
		return null;
	}

	@Action(value = "delete", results = {@Result  (name = "success", type = "json")})
	public String entity() throws IOException{
		if("event".equals(entityName)){
			eventManagerDao.deleteEvent(Integer.parseInt(entityId));
			ProjectHelper.sendObjectAsJsonResponse("deleted",ServletActionContext.getResponse());
		}		
		return null;
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
	
	
	

}
