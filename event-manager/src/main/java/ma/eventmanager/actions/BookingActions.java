package ma.eventmanager.actions;

import java.util.Date;
import java.util.Map;

import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Client;
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
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/booking")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class BookingActions extends ActionSupport{
	
	private Integer eventId;
	private Event bookedEvent;
	private Client selectedClient;
	private Integer selectedClientId;
	private String paymentMethod;
	private Map<String,String> paymentMethods;
	@Autowired private EventManagerDao eventManagerDao;
	private Subscription subscription;
	private static Logger logger = Logger.getLogger(EventActions.class);

	
	@Action(value = "initBooking", results = {@Result(name="initBookingSuccess",location="/event/bookingPage.jsp")})
	public String initBooking(){
		bookedEvent= eventManagerDao.retreivEvent(eventId);
		ServletActionContext.getRequest().getSession().setAttribute("bookedEvent", bookedEvent);
		eventManagerDao.listEvents(0,Constants.DEFAULT_ROWS_NUM);
		return "initBookingSuccess";
	}
	
	@Action(value = "payTicket", results = {@Result(name="ticket",location="/event/ticketStep.jsp")})
	public String payTicket(){
		
		logger.debug("Selected paymentMethod : "+paymentMethod);
		bookedEvent = (Event)ServletActionContext.getRequest().getSession().getAttribute("bookedEvent");
		selectedClient = (Client)ServletActionContext.getRequest().getSession().getAttribute("selectedClient");
		subscription = new Subscription();
		subscription.setEvent(bookedEvent);
		subscription.setType(paymentMethod);
		
	    ServletActionContext.getRequest().getSession().getAttribute("user");
	    subscription.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		subscription.setType(paymentMethod);
		subscription.setClient(selectedClient);
		subscription.setSubscriptionDate(new Date());
		eventManagerDao.saveSubscription(subscription);
		ServletActionContext.getRequest().getSession().setAttribute("subscription",subscription);

		
		if("CASH".equals(paymentMethod) || "CR_CARD".equals(paymentMethod)){			
			return "ticket";
		}else if("LIST".equals(paymentMethod)){
			return "ticket";
		}else if("MS_CARD".equals(paymentMethod)){
			//TODO call *.bat file 
			return "ticket";
		}
		return "ticket";
	}
	
	
	@Action(value = "paymentChoice", results = {@Result(name="success",location="/event/paymentStep.jsp")})
	public String paymentChoiceAction(){
		
		selectedClient = eventManagerDao.retreivClient(selectedClientId);
		ServletActionContext.getRequest().getSession().setAttribute("selectedClient",selectedClient);
		paymentMethods= ProjectHelper.parseMap(getText("global.paymentMethods"));
		return "success";
	}

	
	@Action(value = "identification", results = {@Result(name="identification",location="/event/identificationStep.jsp")})
	public String identification(){
		bookedEvent= eventManagerDao.retreivEvent(eventId);
		ServletActionContext.getRequest().getSession().setAttribute("bookedEvent", bookedEvent);
		return "identification";
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

	public Client getSelectedClient()
	{
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient)
	{
		this.selectedClient = selectedClient;
	}

	public Integer getSelectedClientId()
	{
		return selectedClientId;
	}

	public void setSelectedClientId(Integer selectedClientId)
	{
		this.selectedClientId = selectedClientId;
	}

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public Map<String, String> getPaymentMethods()
	{
		return paymentMethods;
	}

	public void setPaymentMethods(Map<String, String> paymentMethods)
	{
		this.paymentMethods = paymentMethods;
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
