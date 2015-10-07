package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.util.ProjectHelper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/client")
@ResultPath(value = "/")
public class ClientActions extends AbstractAction{
	
	//Client filelds
	private String firstName;
	private String lastName;
	private String cne;
	private String cin;
	private String roleId;


	@Autowired private EventManagerDao eventManagerDao;
	private List<Client> list= new ArrayList<Client>();
	private String errorNotification;


	@Action(value = "list")
	public String list() throws IOException {
		int offset;
		try {
			offset = (rows) * (page - 1);
		} catch (NullPointerException e) {
			offset = 0;
			rows = Constants.DEFAULT_ROWS_NUM;
		}

		if(get_search() != null && get_search().equals("true")){
			list = eventManagerDao.listClients(offset, rows,usedSearchFields());
		}else{
			list = eventManagerDao.listClients(offset, rows);			
		}
		records = eventManagerDao.getClientsCount();
		total = (int) Math.ceil((double) records / (double) rows);	
		
		ProjectHelper.sendObjectAsJsonResponse(list,ServletActionContext.getResponse());
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

	public List<Client> getList()
	{
		return list;
	}

	public void setList(List<Client> list)
	{
		this.list = list;
	}

	public String getErrorNotification()
	{
		return errorNotification;
	}

	public void setErrorNotification(String errorNotification)
	{
		this.errorNotification = errorNotification;
	}
	
	
	
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getCne()
	{
		return cne;
	}

	public void setCne(String cne)
	{
		this.cne = cne;
	}

	public String getCin()
	{
		return cin;
	}

	public void setCin(String cin)
	{
		this.cin = cin;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public List<CriteriaModel> usedSearchFields(){
		
		List<CriteriaModel> criterias = new ArrayList<CriteriaModel>();
		criterias.add(new CriteriaModel("firstName",getFirstName()));
		criterias.add(new CriteriaModel("lastName",getLastName()));
		criterias.add(new CriteriaModel("cne",getCne()));
		criterias.add(new CriteriaModel("cin",getCin()));
		criterias.add(new CriteriaModel("roleId",getRoleId()));
		
		
		return criterias;
	}

		
}
