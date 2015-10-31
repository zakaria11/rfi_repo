package ma.eventmanager.model;

import java.util.List;

public class ViewEntity{
	
	private String name;
	private List<Attribute> attributes;

	public List<Attribute> getAttributes(){
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes){
		this.attributes = attributes;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	
	
}
