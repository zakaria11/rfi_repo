//package ma.eventmanager.entitys;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.ManyToOne;
//
//
//
//public class SubscriptionEmbeddedId implements Serializable{
//
//	
//	@ManyToOne
//	private Event eventId;
//	
//	@ManyToOne
//	private User userId;
//	
//	private String clientId;
//	
//	private Date subscriptionDate;
//
//	public Event getEventId()
//	{
//		return eventId;
//	}
//
//	public void setEventId(Event eventId)
//	{
//		this.eventId = eventId;
//	}
//
//	public User getUserId()
//	{
//		return userId;
//	}
//
//	public void setUserId(User userId)
//	{
//		this.userId = userId;
//	}
//
//	public String getClientId()
//	{
//		return clientId;
//	}
//
//	public void setClientId(String clientId)
//	{
//		this.clientId = clientId;
//	}
//
//	public Date getSubscriptionDate()
//	{
//		return subscriptionDate;
//	}
//
//	public void setSubscriptionDate(Date subscriptionDate)
//	{
//		this.subscriptionDate = subscriptionDate;
//	}
//
//	
//}
