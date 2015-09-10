package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.util.ProjectHelper;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;


@Namespace("/event")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class EventActions extends AbstractAction{
	
	private static final long serialVersionUID = 8397678142447406483L;
	private List<Event> list= new ArrayList<Event>();
	private Event event;
	private String errorNotification;
	private static Logger logger = Logger.getLogger(EventActions.class);
	
	//Event fields	
	private Integer id;
	private String name;
	private String description;
	private String state;
	
	@Autowired private EventManagerDao eventManagerDao;
	private Subscription subscription;
	
	
	@Action(value = "choice", results = {@Result(name="home",location="/event/choice.jsp")})
	public String  list() {
		try{
			list = eventManagerDao.listEvents(0,Constants.DEFAULT_ROWS_NUM);				
			return "list";
		}catch(DataAccessResourceFailureException e){
			e.printStackTrace();
			this.errorNotification = "Error accured during listing the events : DataAccessResourceFailureException";
			return "choice";
		}
	}
	
	@Action(value = "administration", results = {@Result(name="administration",location="/event/administration.jsp")})
	public String  administration() {
		try{
			list = eventManagerDao.listEvents(0,Constants.DEFAULT_ROWS_NUM);				
			return "list";
		}catch(DataAccessResourceFailureException e){
			e.printStackTrace();
			this.errorNotification = "Error accured during listing the events : DataAccessResourceFailureException";
			return "administration";
		}
	}

	

	
	private List<CriteriaModel> usedSearchFields(){
		List<CriteriaModel> criterias = new ArrayList<CriteriaModel>();
		criterias.add(new CriteriaModel("name",getName()));
		criterias.add(new CriteriaModel("description",getDescription()));
		criterias.add(new CriteriaModel("state",getState()));
		
		return criterias;
	}


	@Action(value = "listJson", results = { @Result(name = "success", type = "json", params = {"root", "list" }) })
	public String listJson() throws IOException{
		int offset;
		try {
			offset = (rows) * (page - 1);
		} catch (NullPointerException e) {
			offset = 0;
			rows = Constants.DEFAULT_ROWS_NUM;
		}

		if(get_search() != null && get_search().equals("true")){
			list = eventManagerDao.listEvents(offset, rows,usedSearchFields());
		}else{
			list = eventManagerDao.listEvents(offset, rows);			
		}
		records = eventManagerDao.getEventsCount();
		total = (int) Math.ceil((double) records / (double) rows);	
		return SUCCESS;
	}
	
	
	@Action(value = "add", results = {@Result  (name = "success", type = "json")})
	public String add() throws IOException{
		eventManagerDao.addEvent(event);
		ProjectHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}

	@Action(value = "del", results = {@Result  (name = "success", type = "json")})
	public String del() throws IOException{
		eventManagerDao.deleteEvent(id);
		ProjectHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}

	
	@Action(value = "edit", results = {@Result(name = "success", type = "json")})
	public String edit() throws IOException{
		Event e = (Event) ServletActionContext.getRequest().getSession().getAttribute("editEvent");
		event.setId(e.getId());
		eventManagerDao.updateEvent(event);
		ProjectHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}

	public List<Event> getList() {
		return list;
	}

	public void setList(List<Event> list) {
		this.list = list;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}


	public Event getEvent(){
		return event;
	}
	public void setEvent(Event event){
		this.event = event;
	}

	public EventManagerDao getEventDao(){
		return eventManagerDao;
	}

	public void setEventDao(EventManagerDao eventDao){
		this.eventManagerDao = eventDao;
	}

	public String getErrorNotification(){
		return errorNotification;
	}

	public void setErrorNotification(String errorNotification){
		this.errorNotification = errorNotification;
	}

	public Subscription getSubscription(){
		return subscription;
	}

	public void setSubscription(Subscription subscription){
		this.subscription = subscription;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state = state;
	}
	
}
