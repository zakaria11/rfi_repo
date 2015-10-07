package ma.eventmanager.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.model.CartInformations;
import ma.eventmanager.model.TicketRepotBean;
import ma.eventmanager.service.ReferenceService;
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
		bookedEvent= (Event) eventManagerDao.retrieve("event",eventId+"");
		ServletActionContext.getRequest().getSession().setAttribute("bookedEvent", bookedEvent);
		eventManagerDao.listEvents(0,Constants.DEFAULT_ROWS_NUM);
		return "initBookingSuccess";
	}
	
	@Action(value = "payTicket", results = {@Result(name="ticket",location="/event/ticketStep.jsp")})
	public String payTicket() throws IOException{
		
		Map<String, Object> responseInfo = new  HashMap<String, Object>();
		responseInfo.put("isOK", "1");
		
		logger.debug("Selected paymentMethod : "+paymentMethod);
		bookedEvent = (Event)ServletActionContext.getRequest().getSession().getAttribute("bookedEvent");
		selectedClient = (Client)ServletActionContext.getRequest().getSession().getAttribute("selectedClient");
		
	    ServletActionContext.getRequest().getSession().getAttribute("user");

		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	
		subscription = new Subscription(bookedEvent,paymentMethod,userId,selectedClient,new Date());
		
		if(bookedEvent.getRemainingPlaces() <= 0){
			responseInfo.put("isOK","0");
			responseInfo.put("message",String.format(ReferenceService.getMessages().get("MSG000009"), subscription.getEvent().getPlaces()));
			ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
			return null;
		}else{
			Double count = bookedEvent.getRemainingPlaces();
			count--;
			bookedEvent.setRemainingPlaces(count);	
		}
		
		if(!isAlreadySubscribed(bookedEvent.getId(),selectedClient.getId())){
			eventManagerDao.saveSubscription(subscription);
			eventManagerDao.updateEvent(bookedEvent);
		
		}else{
			responseInfo.put("isOK","0");
			responseInfo.put("message",String.format(ReferenceService.getMessages().get("MSG000005"), selectedClient.getId()));
			ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
			return null;
		}		
		responseInfo.put("subscription", subscription);
		
		ServletActionContext.getRequest().getSession().setAttribute("ticketRepotBean", createReportBean(subscription));
		
		if("CASH".equals(paymentMethod) || "CR_CARD".equals(paymentMethod)){
			responseInfo.put("message",ReferenceService.getMessages().get("MSG000003"));
			ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
			return null;
		}else if("LIST".equals(paymentMethod)){
			responseInfo.put("message",ReferenceService.getMessages().get("MSG000003"));
			ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
			return null;
		}else if("MS_CARD".equals(paymentMethod)){
			//get card information
			CartInformations cartInfo= creatCartInfo(ReferenceService.getEnvVariabls().get("payment.reader.outFile"));
			if(cartInfo.getNumPme() == null){
				responseInfo.put("isOK","0");
				responseInfo.put("message",ReferenceService.getMessages().get("MSG000001"));
				ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
				return null;
			}
			
			if(Integer.parseInt(cartInfo.getSoldePme()) <= 0){
				responseInfo.put("isOK","0");
				responseInfo.put("message",ReferenceService.getMessages().get("MSG000002"));
				ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
				return null;
			}
			
			//debiter
			debitFromCard(cartInfo,responseInfo);
			ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
			return null;
		}
		return "ticket";
	}
	
	

	private TicketRepotBean createReportBean(Subscription subscription2)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		TicketRepotBean bean= new TicketRepotBean();
		bean.setSubscriptionId(subscription.getId()+"");
		bean.setSubscriptionType(ReferenceService.getPaymentMethods().get(subscription.getType()));
		bean.setSubscriptionUserId(subscription.getUserId()+"");		
		bean.setSubscriptionDate(sdf.format(subscription.getSubscriptionDate()));
		
		//Client Info
		Client c = subscription.getClient();
		bean.setClientId(c.getId()+"");
		bean.setClientLastName(c.getLastName());
		bean.setClientFirstName(c.getFirstName());
		bean.setClientCin(c.getCin());
		
		//Event Infos
		Event e = subscription.getEvent();
		bean.setEventId(e.getId()+"");
		bean.setEventName(e.getName());
		bean.setEventState(e.getState());
		bean.setEventDate(sdf.format(e.getDate()));
		bean.setEventdDscription(e.getDescription());
		bean.setEventdPlaces(e.getPlaces()+"");
		bean.setEventdPlaces(e.getPrice()+"");
		bean.setEventdRemainingPlaces(e.getRemainingPlaces()+"");
		
		//Room Infos
		Room r= e.getRoom();
		bean.setRoomId(r.getId()+"");
		bean.setRoomName(r.getName());
		bean.setRoomState(r.getState());
		bean.setRoomDescription(r.getDescription());
		
		//QR Code
		bean.setQrCode( 
			bean.getSubscriptionId()+";"
			+bean.getClientFirstName()+" "+bean.getClientLastName()+";"
			+bean.getRoomId()
		);
		
		return bean;
		
	}

	private boolean isAlreadySubscribed(Integer eventId,Integer cleintId){
		List<Subscription> subscriptions = eventManagerDao.loadSubscription(eventId,cleintId);
		if(subscriptions != null && subscriptions.size() > 0){
			return true;
		}else {
			return false;	
		}
	}

	private void debitFromCard(CartInformations cartInfo, Map<String, Object> responseInfo) throws IOException{
		Runtime.getRuntime().exec(new String[]{ReferenceService.getEnvVariabls().get("payment.debit.exec")});
		
		String paymentOutData = ReferenceService.getEnvVariabls().get("payment.debit.outFile");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(paymentOutData));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if(line.startsWith("param_XXXXXXXXXXX")){
				String id = line.substring(line.indexOf("=")+1);
				if(id != null && id.equals(cartInfo.getNumPme())){
					responseInfo.put("message",ReferenceService.getMessages().get("MSG000003"));			
				}else {
					responseInfo.put("message",ReferenceService.getMessages().get("MSG000004"));
				}
			}
		}
		

		
	}

	private CartInformations creatCartInfo(String outData) throws IOException{
		Runtime.getRuntime().exec(new String[]{ReferenceService.getEnvVariabls().get("payment.reader.exec")});
		CartInformations info = new CartInformations();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(outData));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if(line.startsWith("Num_PME")){
				info.setNumPme(line.substring(line.indexOf("=")+1));
			}
			if(line.startsWith("Solde_PME")){
				info.setSoldePme(line.substring(line.indexOf("=")+1));
			}
		}
		return info;
	}

	@Action(value = "paymentChoice", results = {@Result(name="success",location="/event/paymentStep.jsp")})
	public String paymentChoiceAction(){
		
		selectedClient = eventManagerDao.retreivClient(selectedClientId);
		ServletActionContext.getRequest().getSession().setAttribute("selectedClient",selectedClient);
		paymentMethods= ReferenceService.getPaymentMethods(); 
		return "success";
	}

	
	@Action(value = "identification", results = {@Result(name="identification",location="/event/identificationStep.jsp")})
	public String identification(){
		bookedEvent= (Event) eventManagerDao.retrieve("event",eventId+"");
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
