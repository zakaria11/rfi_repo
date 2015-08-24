package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionSupport;



@Namespace("/event")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class EventActions extends ActionSupport	{
	
	private static final long serialVersionUID = 8397678142447406483L;
	private List<Event> list= new ArrayList<Event>();
	private Integer eventId;
	private Event bookedEvent;
	private Map<String,String> paymentMethods;
	private Event event;
	private String errorNotification;
	private String paymentMethod;
	private static Logger logger = Logger.getLogger(EventActions.class);
	
	
	@Autowired private EventManagerDao eventManagerDao;
	private Subscription subscription;
	
	
	@Action(value = "list", results = {@Result(name="list",location="/event/list.jsp")})
	public String  list() {
		try{
			list = eventManagerDao.listEvents();			
			return "list";
		}catch(DataAccessResourceFailureException e){
			e.printStackTrace();
			this.errorNotification = "Error accured during listing the events : DataAccessResourceFailureException";
			return "list";
		}
	}
	
	@Action(value = "add", results = {@Result  (name = "success", type = "json")})
	public String add() throws IOException{
		eventManagerDao.addEvent(event);
		ProjectHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}

	@Action(value = "del", results = {@Result  (name = "success", type = "json")})
	public String del() throws IOException{
		eventManagerDao.deleteEvent(eventId);
		ProjectHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}

	@Action(value = "initEdit", results = {@Result(name="edit",location="/event/edit.jsp")})
	public String initEdit(){
		event = eventManagerDao.retreivEvent(eventId);
		ServletActionContext.getRequest().getSession().setAttribute("editEvent", event);
		return "edit";
	}
	
	@Action(value = "edit", results = {@Result(name = "success", type = "json")})
	public String edit() throws IOException{
		Event e= (Event) ServletActionContext.getRequest().getSession().getAttribute("editEvent");
		event.setId(e.getId());
		eventManagerDao.updateEvent(event);
		ProjectHelper.sendObjectAsJsonResponse(null,ServletActionContext.getResponse());
		return null;
	}
	
	
	@Action(value = "initBooking", results = {@Result(name="initBookingSuccess",location="/event/bookingPage.jsp")})
	public String initBooking(){
		bookedEvent= eventManagerDao.retreivEvent(eventId);
		ServletActionContext.getRequest().getSession().setAttribute("bookedEvent", bookedEvent);
		paymentMethods = ProjectHelper.parseMap(getText("global.paymentMethods"));
		eventManagerDao.listEvents();
		return "initBookingSuccess";
	}

	@Action(value = "booking", results = {
		@Result(name="initBookingUsingCash",location="/event/initBookingUsingCash.jsp"),
		@Result(name="initBookingByLDAP",location="/event/initBookingByLDAP.jsp"),
		@Result(name="initBookingByMsCard",location="/event/initBookingByMsCard.jsp")
	})
	public String booking(){
		logger.debug("Selected paymentMethod : "+paymentMethod);
		bookedEvent = (Event)ServletActionContext.getRequest().getSession().getAttribute("bookedEvent");
		subscription = new Subscription();
		subscription.setEvent(bookedEvent);
		subscription.setType(paymentMethod);
		
		//TODO get user form context
	    ServletActionContext.getRequest().getSession().getAttribute("user");
	    subscription.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		subscription.setType(paymentMethod);
		subscription.setSubscriptionDate(new Date());
		eventManagerDao.saveSubscription(subscription);
		ServletActionContext.getRequest().getSession().setAttribute("subscription",subscription);
		
		
		if("CASH".equals(paymentMethod) || "CR_CARD".equals(paymentMethod)){			
			return "initBookingUsingCash";
		}else if("LIST".equals(paymentMethod)){
			return "initBookingByLDAP";			
		}else if("MS_CARD".equals(paymentMethod)){
			return "initBookingByMsCard";
		}
		return null;
	}

	@Action(value = "printTicket", results = {@Result(name = "success", type = "json")})	
	public String printTicket(){
		//TODO jasperreport impl
		return null;
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
	public Map<String, String> getPaymentMethods()
	{
		return paymentMethods;
	}

	public void setPaymentMethods(Map<String, String> paymentMethods)
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

	public EventManagerDao getEventDao()
	{
		return eventManagerDao;
	}

	public void setEventDao(EventManagerDao eventDao)
	{
		this.eventManagerDao = eventDao;
	}

	public String getErrorNotification()
	{
		return errorNotification;
	}

	public void setErrorNotification(String errorNotification)
	{
		this.errorNotification = errorNotification;
	}

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public Subscription getSubscription()
	{
		return subscription;
	}

	public void setSubscription(Subscription subscription)
	{
		this.subscription = subscription;
	}
	
	

	
}
