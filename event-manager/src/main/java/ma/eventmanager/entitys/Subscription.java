package ma.eventmanager.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Subscription{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Event event;
	
	@Column
	private String userId;

	@ManyToOne
	private Client client;
	
	@Column
	private Date subscriptionDate;

	@Column
	private String type;
	
	/*
	 * yes = 1
	 * no = 0
	 * */
	@Column
	private String assisted;
	
	
	public Subscription(){
		this.assisted = "0";
	}

	public Subscription(Event bookedEvent, String paymentMethod, String userId, Client selectedClient, Date date)
	{
		this.setEvent(bookedEvent);
		this.setType(paymentMethod);
		this.setUserId(userId);
		this.setClient(selectedClient);
		this.setSubscriptionDate(date);

	}

	public Subscription(Event event, String cleintId)
	{
		this.setEvent(event);
		this.setClient(client);
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Event getEvent()
	{
		return event;
	}

	public void setEvent(Event event)
	{
		this.event = event;
	}

	public Client getClient()
	{
		return client;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	

	public Date getSubscriptionDate()
	{
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate)
	{
		this.subscriptionDate = subscriptionDate;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getAssisted()
	{
		return assisted;
	}

	public void setAssisted(String assisted)
	{
		this.assisted = assisted;
	}

	
	

}
