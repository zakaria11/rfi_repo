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
	
	private String state;
	
	private String places;
	
	private RoomVo room;

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
	@ViewField(name="Etat")
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
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
	public RoomVo getRoom()
	{
		return room;
	}
	public void setRoom(RoomVo room)
	{
		this.room = room;
	}
	

}
