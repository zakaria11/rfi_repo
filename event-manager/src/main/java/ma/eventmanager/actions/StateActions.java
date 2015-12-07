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
import ma.eventmanager.entitys.State;
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

@Namespace("/state")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class StateActions extends AbstractAction
{

	@Autowired private EventManagerDao eventManagerDao;
	private List<State> list= new ArrayList<State>();
	private DataTableResponseObject resp = new DataTableResponseObject();

	//State attrs
	private String name;
	private String description;


	
	@Action(value = "list", results = { @Result(name = "success", type = "json", params = {"root", "resp" }) })
	public String list() throws IOException{
		int offset = 0;
		rows = Constants.DEFAULT_ROWS_NUM;
		records = eventManagerDao.getRoomsCount();

		try {
			offset = (rows) * (page - 1);
		} catch (NullPointerException e) {}
		
		if(get_search() != null && get_search().equals("true")){
			list = eventManagerDao.getStates(offset, rows,usedSearchFields());
			resp.setData(list);
		}else{
			list = eventManagerDao.getStates(offset, rows);
			resp.setData(list);
		}
		total = (int) Math.ceil((double) records / (double) rows);
		
		
		return SUCCESS;
	}
	
	public List<CriteriaModel> usedSearchFields(){
		
		List<CriteriaModel> criterias = new ArrayList<CriteriaModel>();
		criterias.add(new CriteriaModel("name",getName()));
		
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

	public List<State> getList()
	{
		return list;
	}

	public void setList(List<State> list)
	{
		this.list = list;
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
