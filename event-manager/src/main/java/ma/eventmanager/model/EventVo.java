package ma.eventmanager.model;


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
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public String getPrice()
	{
		return price;
	}
	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getRemainingPlaces()
	{
		return remainingPlaces;
	}
	public void setRemainingPlaces(String remainingPlaces)
	{
		this.remainingPlaces = remainingPlaces;
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
	public void setState(String state)
	{
		this.state = state;
	}
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
