package ma.eventmanager.actions;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.entitys.User;
import ma.eventmanager.entitys.UserRole;
import ma.eventmanager.model.Attribute;
import ma.eventmanager.model.ClientVo;
import ma.eventmanager.model.EventVo;
import ma.eventmanager.model.RoomVo;
import ma.eventmanager.model.UserVo;
import ma.eventmanager.model.ViewEntity;
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
	private String entityFormat;
	
	private EventVo event;
	private RoomVo room;
	private ClientVo client;
	private UserVo user;
	
	@Action(value = "load", results = {@Result  (name = "success", type = "json",params = {"root", "resp" })})
	public String load() throws IOException{
		
		Object entity = null;
		if("event".equals(entityName)){
			entity = eventManagerDao.retrieveEvent(entityId).toEventVo();
		}
		if("room".equals(entityName)){
			entity= eventManagerDao.retrieveRoom(entityId).toRoomVo();
		}
		if("client".equals(entityName)){
			entity = eventManagerDao.retreiveClient(entityId).toClientVo();
		}
		if("user".equals(entityName)){
			entity=eventManagerDao.retrieveUser(entityId).toUserVo();
		}
		if("subscription".equals(entityName)){
			Subscription subscription= (Subscription) eventManagerDao.retrieveSubscription(entityId);
			//ProjectHelper.sendObjectAsJsonResponse(subscription.toSubscriptionVo(),ServletActionContext.getResponse());
			return null;			
		}
		if(entityFormat == null){
			ProjectHelper.sendObjectAsJsonResponse(entity,ServletActionContext.getResponse());			
		}else if ("view".equals(entityFormat)) {
			ViewEntity viewEntity = ProjectHelper.toViewEntity(entity);			
			ProjectHelper.sendObjectAsJsonResponse(viewEntity,ServletActionContext.getResponse());
		}
		return null;
	}

	@Action(value = "delete", results = {@Result  (name = "success", type = "json",params = {"root", "resp" })})
	public String delete() throws IOException{
		if("event".equals(entityName)){
			eventManagerDao.deleteEvent(Integer.parseInt(entityId));
		}		
		if("room".equals(entityName)){
			eventManagerDao.deleteRoom(Integer.parseInt(entityId));
			
		}		
		if("client".equals(entityName)){
			eventManagerDao.deleteClient(Integer.parseInt(entityId));
		}	
		if("user".equals(entityName)){
			eventManagerDao.deleteUser(Integer.parseInt(entityId));
		}	
		resp.put("isOK", "1");

		return SUCCESS;
	}

	@Action(value = "modify", results = {@Result  (name = "success", type = "json",params = {"root", "resp" })})
	public String modify() throws IOException, ParseException{
		if("event".equals(entityName)){
			eventManagerDao.updateEvent(new Event(event));
		}
		if("room".equals(entityName)){
			eventManagerDao.updateRoom(new Room(room));
		}		
		if("client".equals(entityName)){
			eventManagerDao.updateClient(new Client(client));
		}	
		if("user".equals(entityName)){
			eventManagerDao.updateUser(new User(user));
		}		

		resp.put("isOK", "1");
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

	public RoomVo getRoom()
	{
		return room;
	}

	public void setRoom(RoomVo room)
	{
		this.room = room;
	}

	public ClientVo getClient()
	{
		return client;
	}

	public void setClient(ClientVo client)
	{
		this.client = client;
	}

	public UserVo getUser()
	{
		return user;
	}

	public void setUser(UserVo user)
	{
		this.user = user;
	}

	public String getEntityFormat()
	{
		return entityFormat;
	}

	public void setEntityFormat(String entityFormat)
	{
		this.entityFormat = entityFormat;
	}
	

}
