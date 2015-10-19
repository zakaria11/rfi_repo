package ma.eventmanager.entitys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ma.eventmanager.model.EventVo;


@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ViewField(name="Date")
	private Integer id;
	
	@Column
	@ViewField(name="Date")
	private Date date;
	
	@Column
	@ViewField(name="Prix")
	private Double price;
	
	@Column
	@ViewField(name="Nbr places restantes")
	private Double remainingPlaces;
	
	@Column
	@ViewField(name="Nome")
	private String name;
	
	@Column
	@ViewField(name="Description")
	private String description;
	
	@Column
	@ViewField(name="Etat")
	private String state;

	@Column
	@ViewField(name="Places")
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
		this.remainingPlaces = places;
	}



	public Event(EventVo event) throws ParseException{
		if (event.getId() != null){
			this.id = Integer.parseInt(event.getId());	
		}
		
		if(event.getDate() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.date = sdf.parse(event.getDate());			
		}
		if (event.getPrice() != null){
			this.price = Double.parseDouble(event.getPrice());	
		}				
		if(event.getRemainingPlaces() != null){
			this.remainingPlaces = Double.parseDouble(event.getRemainingPlaces());
		}		
		if(event.getPlaces() != null){
			this.places = Double.parseDouble(event.getPlaces());
		}
		this.name = event.getName();
		this.description = event.getDescription();
		this.state = event.getDescription();
		if (event.getPlaces() != null){
			this.places = Double.parseDouble(event.getPlaces());	
		}
		this.room = new Room(event.getRoom());
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



	public EventVo toEventVo()
	{
		EventVo eventVo = new EventVo(); 
		eventVo.setId(this.getId()+"");
		
		
		if(this.getDate() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			eventVo.setDate(sdf.format(this.getDate()));	
		}
		eventVo.setPrice(this.getPrice()+"");
		eventVo.setRemainingPlaces(this.getRemainingPlaces()+"");
		eventVo.setPlaces(this.getPlaces()+"");
		eventVo.setName(this.getName());
		eventVo.setDescription(this.getDescription());
		eventVo.setState(this.getState());
		eventVo.setPlaces(this.getPlaces()+"");
		eventVo.setRoom(this.getRoom().toRoomVo());	

		
		
		return eventVo;
	}

		
}
