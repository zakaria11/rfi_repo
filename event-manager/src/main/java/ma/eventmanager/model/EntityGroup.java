package ma.eventmanager.model;

import java.util.List;

public class EntityGroup
{

	private String title;
	private List<?> list;
	private Double count;
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public List<?> getList()
	{
		return list;
	}
	public void setList(List<?> list)
	{
		this.list = list;
	}
	public Double getCount()
	{
		return count;
	}
	public void setCount(Double count)
	{
		this.count = count;
	}
	
	
}
