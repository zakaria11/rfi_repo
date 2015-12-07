package ma.eventmanager.model;

import ma.eventmanager.annotations.ViewField;

public class StateVo
{
	

	private String id;	

	private String name;
	

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

}
