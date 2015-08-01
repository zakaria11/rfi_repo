package ma.eventmanager.entitys;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Date date;
	
	@Column
	private Double price;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String state;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "event_room", joinColumns = { 
		@JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "room_id") 
	})
	private Set<Room> rooms;
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Double getPrice()
	{
		return price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	
	public String getState()
	{
		return state;
	}

	/**
	 * state = 0 > event disabled
	 * state = 1 > event enabled
	 * */
	public void setState(String state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "Event [id=" + id + ", date=" + date + ", price=" + price + ", name=" + name + ", description=" + description + ", state=" + state + "]";
	}

		
}
