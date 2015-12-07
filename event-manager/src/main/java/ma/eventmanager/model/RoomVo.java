package ma.eventmanager.model;

import ma.eventmanager.annotations.ViewField;
import ma.eventmanager.annotations.ViewObject;

@ViewObject(name="Salle")
public class RoomVo{

	private String id;
	private String name;
	private String description;
	private String state;
	
	@ViewField(name="Id")
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
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
	
}
