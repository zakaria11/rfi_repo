package ma.eventmanager.model;

import ma.eventmanager.annotations.ViewField;
import ma.eventmanager.annotations.ViewObject;


@ViewObject(name="événement")
public class EventVo{

	private String id;	
	
	private String date;
	
	private String price;
	
	private String remainingPlaces;
	
	private String name;
	
	private String description;
	
	private String places;
	
	private String rating;
	
	private String imageName;
	
	private RoomVo room;
	
	private StateVo state;

	private TagVo tag;

	@ViewField(name="Id")
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	@ViewField(name="Date")
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	@ViewField(name="Prix")
	public String getPrice()
	{
		return price;
	}
	public void setPrice(String price)
	{
		this.price = price;
	}
	@ViewField(name="Nbr places restantes")
	public String getRemainingPlaces()
	{
		return remainingPlaces;
	}
	public void setRemainingPlaces(String remainingPlaces)
	{
		this.remainingPlaces = remainingPlaces;
	}
	@ViewField(name="Nom")
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	@ViewField(name="Description")
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	@ViewField(name="Places")
	public String getPlaces()
	{
		return places;
	}
	public void setPlaces(String places)
	{
		this.places = places;
	}
	@ViewField(name="Salle")
	public String roomName()
	{
		if(room != null){
			return room.getName();			
		}
		return "";
	}
	public RoomVo getRoom()
	{
		return room;
	}
	public void setRoom(RoomVo room)
	{
		this.room = room;
	}

	@ViewField(name="Image")
	public String getImageName()
	{
		return imageName;
	}
	
	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}
	@ViewField(name="Importance")
	public String getRating()
	{
		return rating;
	}
	public void setRating(String rating)
	{
		this.rating = rating;
	}
	public StateVo getState()
	{
		return state;
	}
	public void setState(StateVo state)
	{
		this.state = state;
	}
	@ViewField(name="Etat")
	public String stateName()
	{
		if(state != null){
			return state.getName();
		}
		return "";
	}

	
	@ViewField(name="Tag")
	public String tagName()
	{
		if(tag != null){
			return tag.getName();			
		}
		return "";
	}

	public TagVo getTag()
	{
		return tag;
	}
	public void setTag(TagVo tag)
	{
		this.tag = tag;
	}
	
	
	
	

}
