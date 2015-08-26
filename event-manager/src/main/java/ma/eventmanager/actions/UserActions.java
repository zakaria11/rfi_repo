package ma.eventmanager.actions;

import java.io.IOException;
import java.util.List;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.User;
import ma.eventmanager.util.ProjectHelper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/user")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class UserActions extends ActionSupport{
	
	@Autowired private EventManagerDao eventManagerDao;
	
	private static final Integer DEFAULT_ROWS_NUM = 10;
	private Integer records;
	private Integer rows;
	private int total;
	private Integer page = 0;
	private String oper;

	private List<User> list;

	
	@Action(value = "list", results = { @Result(name = "success", type = "json", params = {"root", "list" }) })
	public String list() throws IOException{

		int offset;
		try {
			offset = (rows) * (page - 1);
		} catch (NullPointerException e) {
			offset = 0;
			rows = DEFAULT_ROWS_NUM;
		}

		records = eventManagerDao.getUsersCount();
		list = eventManagerDao.getUsers(offset, rows);
		total = (int) Math.ceil((double) records / (double) rows);	
		return SUCCESS;
	}


	public Integer getRecords(){
		return records;
	}


	public void setRecords(Integer records){
		this.records = records;
	}


	public Integer getRows(){
		return rows;
	}


	public void setRows(Integer rows){
		this.rows = rows;
	}


	public int getTotal(){
		return total;
	}


	public void setTotal(int total){
		this.total = total;
	}


	public Integer getPage(){
		return page;
	}


	public void setPage(Integer page){
		this.page = page;
	}


	public String getOper(){
		return oper;
	}


	public void setOper(String oper){
		this.oper = oper;
	}


	public List<User> getList()
	{
		return list;
	}


	public void setList(List<User> list)
	{
		this.list = list;
	}
	
	
	
}
