package ma.eventmanager.model;


import ma.eventmanager.annotations.ViewField;
import ma.eventmanager.annotations.ViewObject;

@ViewObject(name="Utilisateur")
public class UserVo
{
	private String id;
	private String name;
	private String username;
	private String password;
	private String mail;
	private String phone;
	private String description;
	
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
	
	@ViewField(name="Utilisateur")
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	@ViewField(name="Mot de passe")
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@ViewField(name="E-mail")
	public String getMail()
	{
		return mail;
	}
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	@ViewField(name="Téléphone")
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
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

	
	

}
