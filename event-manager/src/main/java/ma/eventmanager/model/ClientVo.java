package ma.eventmanager.model;

import ma.eventmanager.annotations.ViewField;
import ma.eventmanager.annotations.ViewObject;


@ViewObject(name="Client")
public class ClientVo{

	private String id;
	private String firstName;
	private String lastName;
	private String cne;
	private String cin;
	private String roleId;
	
	private String mail;
	private String password;
	private String orderNumber;
	private String gender;
	
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
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	@ViewField(name="Prénom")
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	@ViewField(name="CNE")
	public String getCne()
	{
		return cne;
	}
	public void setCne(String cne)
	{
		this.cne = cne;
	}
	@ViewField(name="CIN")
	public String getCin()
	{
		return cin;
	}
	public void setCin(String cin)
	{
		this.cin = cin;
	}
	@ViewField(name="Id rôle")
	public String getRoleId()
	{
		return roleId;
	}
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}
	@ViewField(name="E-Mail")
	public String getMail()
	{
		return mail;
	}
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@ViewField(name="Numéro d'ordre")
	public String getOrderNumber()
	{
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}
	
	@ViewField(name="Le sexe")
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}

}
