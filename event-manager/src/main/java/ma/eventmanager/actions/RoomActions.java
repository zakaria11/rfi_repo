package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.model.DataTableResponseObject;
import ma.eventmanager.model.Select2Model;
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
	private DataTableResponseObject resp = new DataTableResponseObject();
	private String name;
	private String description;
	private String state;
	
	
	@Action(value = "list", results = { @Result(name = "success", type = "json", params = {"root", "resp" }) })
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
			resp.setData(list);
		}else{
			list = eventManagerDao.getRooms(offset, rows);
			resp.setData(list);
		}
		total = (int) Math.ceil((double) records / (double) rows);
		
		if("SEMICOLON_MAP".equals(responseFormat)){
			Map rooms = new HashMap<String, String>();
			for(Room room:list){
				rooms.put(room.getId()+"",room.getName());
			}
			ProjectHelper.sendObjectAsJsonResponse(rooms,ServletActionContext.getResponse());
			return null;
		}
		if("SELECT2_MAP".equals(responseFormat)){
			List<Select2Model> modelList = new ArrayList<Select2Model>();
			for(Room room:list){
				Select2Model model = new Select2Model();
				model.setId(room.getId()+"");
				model.setText(room.getName());
				modelList.add(model);
			}
			ProjectHelper.sendObjectAsJsonResponse(modelList,ServletActionContext.getResponse());
			return null;
		}
		
		return SUCCESS;
	}
	
	@Action(value = "add", results = { @Result(name = "success", type = "json") })
	public String add() throws IOException{
		Room room = new Room(name,description,state);
		Integer addState = eventManagerDao.addRoom(room);
		ProjectHelper.sendObjectAsJsonResponse(addState,ServletActionContext.getResponse());
		return null;
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

	public DataTableResponseObject getResp()
	{
		return resp;
	}

	public void setResp(DataTableResponseObject resp)
	{
		this.resp = resp;
	}
	
	
	
	
	
}
