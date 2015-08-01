package ma.eventmanager.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscription
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

}
