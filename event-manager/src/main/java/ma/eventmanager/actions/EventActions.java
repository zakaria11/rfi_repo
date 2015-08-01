package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ma.eventmanager.dao.EventDao;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.util.JsonHelper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;

import com.opensymphony.xwork2.ActionSupport;



@Namespace("/event")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class EventActions extends ActionSupport	{
	
	private static final long serialVersionUID = 8397678142447406483L;
	private List<Event> list= new ArrayList<Event>();
	private Integer eventId;
	private Event bookedEvent;
	private List<String> paymentMethods;
	private Event event;
	private String errorNotification;
	
	@Autowired private EventDao eventDao;
	
	
	@Action(value = "list", results = {@Result(name="list",location="/event/list.jsp")})
	public String  list() {
		try{
			list = eventDao.list();			
			return "list";
		}catch(DataAccessResourceFailureException e){
			e.printStackTrace();
			this.errorNotification = "Error accured during listing the events : DataAccessResourceFailureException";
			return "list";
		}
	}
	
	@Action(value = "add", results = {@Result  (name = "success", type = "json")})
	public String add() throws IOException{
		eventDao.add(event);
		JsonHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}

	@Action(value = "del", results = {@Result  (name = "success", type = "json")})
	public String del() throws IOException{
		eventDao.delete(eventId);
		JsonHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}

	@Action(value = "initEdit", results = {@Result(name="edit",location="/event/edit.jsp")})
	public String initEdit(){
		event = eventDao.retreivEvent(eventId);
		ServletActionContext.getRequest().getSession().setAttribute("editEvent", event);
		return "edit";
	}
	
	@Action(value = "edit", results = {@Result(name = "success", type = "json")})
	public String edit() throws IOException{
		Event e= (Event) ServletActionContext.getRequest().getSession().getAttribute("editEvent");
		event.setId(e.getId());
		eventDao.update(event);
		JsonHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}
	
	
	@Action(value = "booking", results = {@Result(name="initBookingSuccess",location="/event/bookingPage.jsp")})
	public String initBooking(){
		bookedEvent= eventDao.retreivEvent(eventId);
		

		paymentMethods = new ArrayList<String>();
		paymentMethods.add("Carte multiservices");
		paymentMethods.add("Monnaie");
		paymentMethods.add("Carte bancaire");
		paymentMethods.add("Echange d'un billet electrique");
		paymentMethods.add("Recherche sur un listing ");
		
		eventDao.list();
		
		return "initBookingSuccess";
	}
	

	public List<Event> getList() {
		return list;
	}

	public void setList(List<Event> list) {
		this.list = list;
	}
	public Integer getEventId()
	{
		return eventId;
	}
	public void setEventId(Integer eventId)
	{
		this.eventId = eventId;
	}
	public Event getBookedEvent()
	{
		return bookedEvent;
	}
	public void setBookedEvent(Event bookedEvent)
	{
		this.bookedEvent = bookedEvent;
	}
	public List<String> getPaymentMethods()
	{
		return paymentMethods;
	}
	public void setPaymentMethods(List<String> paymentMethods)
	{
		this.paymentMethods = paymentMethods;
	}
	public Event getEvent()
	{
		return event;
	}
	public void setEvent(Event event)
	{
		this.event = event;
	}

	public EventDao getEventDao()
	{
		return eventDao;
	}

	public void setEventDao(EventDao eventDao)
	{
		this.eventDao = eventDao;
	}

	public String getErrorNotification()
	{
		return errorNotification;
	}

	public void setErrorNotification(String errorNotification)
	{
		this.errorNotification = errorNotification;
	}
	
	

	
}
