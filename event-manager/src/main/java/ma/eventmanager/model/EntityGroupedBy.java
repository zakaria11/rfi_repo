package ma.eventmanager.model;

import java.util.ArrayList;
import java.util.List;

public class EntityGroupedBy
{
	private String title;
	private List<EntityGroup> groups;
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public List<EntityGroup> getGroups()
	{
		if(this.groups == null){
			groups = new ArrayList<EntityGroup>();
		}
		return groups;
	}
	public void setGroups(List<EntityGroup> groups)
	{
		this.groups = groups;
	}

	
	

}
