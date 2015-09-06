package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.util.ProjectHelper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/room")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class RoomActions extends AbstractAction{

	
	@Autowired private EventManagerDao eventManagerDao;
	private String responseFormat;
	private List<Room> list= new ArrayList<Room>();

	private String name;
	private String description;
	private String state;
	
	
	@Action(value = "list", results = { @Result(name = "success", type = "json", params = {"root", "list" }) })
	public String list() throws IOException{
		int offset;
		try {
			offset = (rows) * (page - 1);
		} catch (NullPointerException e) {
			offset = 0;
			rows = Constants.DEFAULT_ROWS_NUM;
		}

		records = eventManagerDao.getUsersCount();
		
		if(get_search() != null && get_search().equals("true")){
			list = eventManagerDao.getRooms(offset, rows,usedSearchFields());
		}else{
			list = eventManagerDao.getRooms(offset, rows);
		}
		total = (int) Math.ceil((double) records / (double) rows);
		
		if("SEMICOLON_MAP".equals(responseFormat)){
			StringBuilder sb = new StringBuilder();
			for(Room room:list){
				sb.append(room.getId());
				sb.append(":");
				sb.append(room.getName());
				sb.append(";");
			}
			if(sb.length() > 0){				
				sb.deleteCharAt(sb.length() -1);
			}
			
			ProjectHelper.sendObjectAsJsonResponse(sb,ServletActionContext.getResponse());
			return null;
		}
		
		return SUCCESS;
	}


	public EventManagerDao getEventManagerDao()
	{
		return eventManagerDao;
	}


	public void setEventManagerDao(EventManagerDao eventManagerDao)
	{
		this.eventManagerDao = eventManagerDao;
	}
	

	public String getResponseFormat()
	{
		return responseFormat;
	}


	public void setResponseFormat(String responseFormat)
	{
		this.responseFormat = responseFormat;
	}


	public List<Room> getList()
	{
		return list;
	}


	public void setList(List<Room> list)
	{
		this.list = list;
	}


	public List<CriteriaModel> usedSearchFields(){
		
		List<CriteriaModel> criterias = new ArrayList<CriteriaModel>();
		criterias.add(new CriteriaModel("name",getName()));
		criterias.add(new CriteriaModel("description",getDescription()));
		criterias.add(new CriteriaModel("state",getState()));
		
		return criterias;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	public String getState()
	{
		return state;
	}


	public void setState(String state)
	{
		this.state = state;
	}
	
	
	
	
	
}
