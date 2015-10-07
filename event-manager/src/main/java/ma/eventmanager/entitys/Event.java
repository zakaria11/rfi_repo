package ma.eventmanager.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


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
	private Double remainingPlaces;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String state;

	@Column
	private Double places;

	@ManyToOne
	private Room room;

	
	
	
	public Event(){}
	
	

	public Event(Date date, Double price, String name, String description, String state, Double places, Room room)
	{
		super();
		this.date = date;
		this.price = price;
		this.name = name;
		this.description = description;
		this.state = state;
		this.places = places;
		this.room = room;
	}



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

	



	public Double getPlaces()
	{
		return places;
	}



	public void setPlaces(Double places)
	{
		this.places = places;
	}



	public Room getRoom()
	{
		return room;
	}



	public void setRoom(Room room)
	{
		this.room = room;
	}



	@Override
	public String toString()
	{
		return "Event [id=" + id + ", date=" + date + ", price=" + price + ", name=" + name + ", description=" + description + ", state=" + state + "]";
	}



	public Double getRemainingPlaces()
	{
		return remainingPlaces;
	}



	public void setRemainingPlaces(Double remainingPlaces)
	{
		this.remainingPlaces = remainingPlaces;
	}

		
}
