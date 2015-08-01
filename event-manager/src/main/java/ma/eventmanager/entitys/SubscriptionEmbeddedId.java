package ma.eventmanager.entitys;

import java.util.Date;

import javax.persistence.ManyToOne;


public class SubscriptionEmbeddedId
{

	
	@ManyToOne
	private Event eventId;
	
	@ManyToOne
	private User userId;
	
	private String clientId;
	
	private Date subscriptionDate;

}
