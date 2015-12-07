package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.State;
import ma.eventmanager.entitys.Tag;
import ma.eventmanager.model.DataTableResponseObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/tag")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class TagActions extends AbstractAction
{
	@Autowired private EventManagerDao eventManagerDao;
	private List<Tag> list= new ArrayList<Tag>();
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
			list = eventManagerDao.getTags(offset, rows,usedSearchFields());
			resp.setData(list);
		}else{
			list = eventManagerDao.getTags(offset, rows);
			resp.setData(list);
		}
		total = (int) Math.ceil((double) records / (double) rows);
		
		
		return SUCCESS;
	}

	public List<CriteriaModel> usedSearchFields(){
		
		List<CriteriaModel> criterias = new ArrayList<CriteriaModel>();
		criterias.add(new CriteriaModel("name",getName()));
		criterias.add(new CriteriaModel("description",getDescription()));
		
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


	public List<Tag> getList()
	{
		return list;
	}


	public void setList(List<Tag> list)
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
