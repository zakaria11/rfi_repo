package ma.eventmanager.model;


public class ClientVo{

	private String id;
	private String firstName;
	private String lastName;
	private String cne;
	private String cin;
	private String roleId;
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getCne()
	{
		return cne;
	}
	public void setCne(String cne)
	{
		this.cne = cne;
	}
	public String getCin()
	{
		return cin;
	}
	public void setCin(String cin)
	{
		this.cin = cin;
	}
	public String getRoleId()
	{
		return roleId;
	}
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	
	
}