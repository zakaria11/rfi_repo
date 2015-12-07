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
	private Double places;
	
	@Column
	private Integer rating;

	
	@Column
	private String imageName;

	@ManyToOne
	private Room room;

	@ManyToOne
	private State state;
	
	@ManyToOne
	private Tag tag;
	
	
	public Event(){}
	
	

	public Event(Date date, Double price, String name, String description, State state, Double places,Integer rating, Room room,Tag tag,String imageName)
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
		this.rating = rating;
		this.imageName = imageName;
		this.tag = tag;
	}



	public Event(EventVo event) throws ParseException{
		
		this.imageName = event.getImageName();
		if (event.getId() != null){
			this.id = Integer.parseInt(event.getId());	
		}
		
		if(event.getDate() != null && !"".equals(event.getDate())){
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
		
		if(event.getRating() != null){
			this.rating = Integer.parseInt(event.getRating());
		}
		if (event.getPlaces() != null){
			this.places = Double.parseDouble(event.getPlaces());	
		}

		if(event.getRoom() != null && event.getRoom().getId() != null && !"".equals(event.getRoom().getId()) && !"null".equals(event.getRoom().getId())){
			this.room = new Room(event.getRoom());
		}
		
		if(event.getState() != null && event.getState().getId() != null && !"".equals(event.getState().getId()) && !"null".equals(event.getState().getId())){
			this.state = new State(event.getState());			
		}
		
		if(event.getTag() != null && event.getTag().getId() != null && !"".equals(event.getTag().getId()) && !"null".equals(event.getTag().getId())){
			this.tag = new Tag(event.getTag());			
		}

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

	public State getState()
	{
		return state;
	}



	public void setState(State state)
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




	public String getImageName()
	{
		return imageName;
	}



	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	@Override
	public String toString()
	{
		return "Event [id=" + id + ", date=" + date + ", price=" + price + ", remainingPlaces=" + remainingPlaces + ", name=" + name + ", description=" + description + ", places=" + places
				+ ", rating=" + rating + ", imageName=" + imageName + ", room=" + room + ", state=" + state + ", tag=" + tag + "]";
	}



	public Double getRemainingPlaces()
	{
		return remainingPlaces;
	}



	public void setRemainingPlaces(Double remainingPlaces)
	{
		this.remainingPlaces = remainingPlaces;
	}


	


	public Integer getRating()
	{
		return rating;
	}



	public void setRating(Integer rating)
	{
		this.rating = rating;
	}



	public Tag getTag()
	{
		return tag;
	}



	public void setTag(Tag tag)
	{
		this.tag = tag;
	}



	public EventVo toEventVo()
	{
		EventVo eventVo = new EventVo(); 
		eventVo.setId(this.getId()+"");
		
		
		if(this.getDate() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			eventVo.setDate(sdf.format(this.getDate()));	
		}
		eventVo.setImageName(this.getImageName());
		eventVo.setPrice(this.getPrice()+"");
		eventVo.setRemainingPlaces(this.getRemainingPlaces()+"");
		eventVo.setPlaces(this.getPlaces()+"");
		eventVo.setName(this.getName());
		eventVo.setDescription(this.getDescription());
		eventVo.setPlaces(this.getPlaces()+"");
		eventVo.setRating(this.getRating()+"");
		
		if(this.getRoom()!= null){
			eventVo.setRoom(this.getRoom().toRoomVo());				
		}
		if(this.getState() != null){
			eventVo.setState(this.getState().toStateVo());			
		}
		if(this.getTag() != null){
			eventVo.setTag(this.getTag().toTagVo());			
		}
		
		
		return eventVo;
	}




	
		
}
