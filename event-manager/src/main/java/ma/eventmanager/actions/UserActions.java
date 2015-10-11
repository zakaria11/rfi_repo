package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.User;
import ma.eventmanager.model.DataTableResponseObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/user")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class UserActions extends AbstractAction{
	
	@Autowired private EventManagerDao eventManagerDao;
	private DataTableResponseObject resp = new DataTableResponseObject();

	
	//User data
	private Integer id;
	private String name;
	private String username;
	private String password;
	private String mail;
	private String phone;
	
	private String[] roles;

	private List<User> list;

	
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
			list = eventManagerDao.getUsers(offset, rows,usedSearchFields());
			resp.setData(list);

			
		}else{
			list = eventManagerDao.getUsers(offset, rows);			
			resp.setData(list);

		}
		total = (int) Math.ceil((double) records / (double) rows);	
		return SUCCESS;
	}
	
	@Action(value = "add", results = { @Result(name = "success", type = "json") })
	public String add() {
		User user = new User();
		user.setId(null);
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setMail(mail);
		user.setPhone(phone);		
		eventManagerDao.addUser(user,new ArrayList<String>(Arrays.asList(roles)));
		return SUCCESS;

	}
	
	@Action(value = "edit", results = { @Result(name = "success", type = "json") })
	public String edit() {
		User user = new User();

		user.setId(id);
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setMail(mail);
		user.setPhone(phone);

		eventManagerDao.editUser(user);
		return SUCCESS;

	}





	public List<User> getList()
	{
		return list;
	}


	public void setList(List<User> list)
	{
		this.list = list;
	}

	
	public String getId()
	{
		return id+"";
	}

	public void setId(String id)
	{
		if (id.equals("_empty")) {
			return ;
		} else {
			this.id = Integer.parseInt(id);
		}
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	
	public DataTableResponseObject getResp()
	{
		return resp;
	}

	public void setResp(DataTableResponseObject resp)
	{
		this.resp = resp;
	}

	public String[] getRoles()
	{
		return roles;
	}

	public void setRoles(String[] roles)
	{
		this.roles = roles;
	}

	public List<CriteriaModel> usedSearchFields(){
		
		List<CriteriaModel> criterias = new ArrayList<CriteriaModel>();
		criterias.add(new CriteriaModel("name",getName()));
		criterias.add(new CriteriaModel("username",getUsername()));
		criterias.add(new CriteriaModel("mail",getMail()));
		criterias.add(new CriteriaModel("phone",getPhone()));
		
		return criterias;
	}
	
}
