package ma.eventmanager.model;

public class Attribute{
	
	private String lable;
	private String value;
	
	public Attribute(){
	}

	
	public Attribute(String lable, String value){
		this.lable = lable;
		this.value = value;
	}


	public String getLable()
	{
		return lable;
	}
	public void setLable(String lable)
	{
		this.lable = lable;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
	
}
