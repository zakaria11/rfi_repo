package ma.eventmanager.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
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

import org.apache.commons.io.FileUtils;
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
	Map<String, Object> responseInfo = new  HashMap<String, Object>();

	
	@Action(value = "initBooking", results = {@Result(name="initBookingSuccess",location="/event/bookingPage.jsp")})
	public String initBooking(){
		bookedEvent= (Event) eventManagerDao.retrieveEvent(eventId+"");
		ServletActionContext.getRequest().getSession().setAttribute("bookedEvent", bookedEvent);
		eventManagerDao.listEvents(0,Constants.DEFAULT_ROWS_NUM,new Date(),null);
		return "initBookingSuccess";
	}
		
	@Action(value = "executePayment", results = {@Result(name="ticket",location="/event/ticketStep.jsp")})
	public String executePayment() throws IOException{
		
		responseInfo.put("isOK", "1");
		
		
		bookedEvent = (Event)ServletActionContext.getRequest().getSession().getAttribute("bookedEvent");
		if(bookedEvent == null){
			bookedEvent= (Event) eventManagerDao.retrieveEvent(eventId+"");
		}
		
		
		selectedClient = (Client)ServletActionContext.getRequest().getSession().getAttribute("selectedClient");
		
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
		
		if(isAlreadySubscribed(bookedEvent.getId(),selectedClient.getId())){
			responseInfo.put("isOK","0");
			responseInfo.put("message",String.format(ReferenceService.getMessages().get("MSG000005"), selectedClient.getId()));
			ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
			return null;
		}
		
		eventManagerDao.saveSubscription(subscription);
		eventManagerDao.updateEvent(bookedEvent);
		responseInfo.put("subscription", subscription);
		ServletActionContext.getRequest().getSession().setAttribute("ticketRepotBean", createReportBean(subscription));

		/*Payment types test*/
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

			if(!validateCard(cartInfo)){
				ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
				return null;
			}		
			//debiter
			debitFromCard(cartInfo.getNumPme(),bookedEvent.getPrice()+"");
			ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
			return null;
		}else if("BORDER".equals(paymentMethod)){
			responseInfo.put("isOK","0");
			//Reteive client informations from credit card
			CartInformations cartInfo= creatCartInfo(ReferenceService.getEnvVariabls().get("payment.reader.outFile"));
			if(!validateCard(cartInfo)){
				ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
				return null;
			}
			//deiiter
			debitFromCard(cartInfo.getNumPme(),bookedEvent.getPrice()+"");
			ProjectHelper.sendObjectAsJsonResponse(responseInfo,ServletActionContext.getResponse());
			return null;
		}
		return "ticket";
	}

	private boolean validateCard(CartInformations cartInfo) throws IOException
	{
		if(cartInfo.getNumPme() == null){
			responseInfo.put("isOK","0");
			responseInfo.put("message",ReferenceService.getMessages().get("MSG000001"));
			return false;
		}
		
		if(Integer.parseInt(cartInfo.getSoldePme()) <= 0){
			responseInfo.put("isOK","0");
			responseInfo.put("message",ReferenceService.getMessages().get("MSG000002"));
			return false;
		}
		return true;
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
//			bean.getSubscriptionId()+";"
//			+bean.getClientFirstName()+" "+bean.getClientLastName()+";"
//			+bean.getRoomId()
			"ID_ISC_00001\n"
			+"NOM_PORTEUR000001\n"
			+"CODE_PORTEUR000001\n"
			+"http://server/path/to/image/image.jpeg\n"
			+"NOM_SALLE00001\n"
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
	

	private void debitFromCard(String pmeNumber,String price) throws IOException{
		
		// write price and numPme in DataDebitIn
		File paymentParamsFile = new File(ReferenceService.getEnvVariabls().get("payment.debit.inFile"));
		List<String> lines = FileUtils.readLines(paymentParamsFile);
		
		creatCartInfo(ReferenceService.getEnvVariabls().get("payment.reader.outFile"));
		
		lines.set(2, "NumPME>ASC/16="+pmeNumber);
		lines.set(6, "Recharge>ASC/1=0"+price);
		FileUtils.writeLines(paymentParamsFile, lines);
		
		//and execute payment.debit.exec using payment.debit.inFile (passed as param in bat file)
		Runtime.getRuntime().exec(new String[]{ReferenceService.getEnvVariabls().get("payment.debit.exec")});	
		String paymentOutData = ReferenceService.getEnvVariabls().get("payment.debit.outFile");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(paymentOutData));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			if(line.contains("-1")){
				responseInfo.put("message",ReferenceService.getMessages().get("MSG000004"));
				return;
			}
		}
		this.responseInfo.put("message",ReferenceService.getMessages().get("MSG000003"));

		
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
		
		selectedClient = eventManagerDao.retreiveClient(selectedClientId+"");
		ServletActionContext.getRequest().getSession().setAttribute("selectedClient",selectedClient);
		paymentMethods= ReferenceService.getPaymentMethods(); 
		return "success";
	}

	
	@Action(value = "identification", results = {@Result(name="identification",location="/event/identificationStep.jsp")})
	public String identification(){
		bookedEvent= (Event) eventManagerDao.retrieveEvent(eventId+"");
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
