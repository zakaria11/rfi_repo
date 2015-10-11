package ma.eventmanager.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ma.eventmanager.model.ClientVo;

@Entity
public class Client
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String cne;
	
	@Column
	private String cin;
	
	@Column
	private String roleId;
	
	@Column
	private String mail;
	
	@Column
	private String password;
	
	@Column
	private String orderNumber;
	
	@Column
	private String gender;

	
	public Client(){}
	
	

	public Client(String firstName, String lastName, String cne, String cin, String roleId,String orderNumber,String mail,String gender)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cne = cne;
		this.cin = cin;
		this.roleId = roleId;
		this.orderNumber = orderNumber;
		this.mail = mail;
		this.gender = gender;

	}



	public Client(ClientVo client)
	{
		if(client.getId() != null){
			this.id = Integer.parseInt(client.getId());
		}
		this.firstName = client.getFirstName();
		this.lastName = client.getLastName();
		this.cne = client.getCne();
		this.cin = client.getCin();
		this.roleId = client.getRoleId();
		
		this.mail = client.getMail();
		this.password = client.getPassword();
		this.orderNumber = client.getOrderNumber();
		this.gender = client.getGender();
	}



	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
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



	public String getOrderNumber()
	{
		return orderNumber;
	}



	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}



	public String getGender()
	{
		return gender;
	}



	public void setGender(String gender)
	{
		this.gender = gender;
	}



	public Object toClientVo()
	{
		ClientVo clientVo = new ClientVo();
		
		clientVo.setId(this.getId()+"");
		clientVo.setFirstName(this.getFirstName());
		clientVo.setLastName(this.getLastName());
		clientVo.setCne(this.getCne());
		clientVo.setCin(this.getCin());
		clientVo.setRoleId(this.getRoleId());
		
		clientVo.setMail(this.getMail());
		clientVo.setPassword(this.getPassword());
		clientVo.setOrderNumber(this.getOrderNumber());
		clientVo.setGender(this.getGender());
		
		return clientVo;
	}

}
