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
	
	
	
	public Client(){}
	
	

	public Client(String firstName, String lastName, String cne, String cin, String roleId)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cne = cne;
		this.cin = cin;
		this.roleId = roleId;
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



	public Object toClientVo()
	{
		ClientVo clientVo = new ClientVo();
		
		clientVo.setId(this.getId()+"");
		clientVo.setFirstName(this.getFirstName());
		clientVo.setLastName(this.getLastName());
		clientVo.setCne(this.getCne());
		clientVo.setCin(this.getCin());
		clientVo.setRoleId(this.getRoleId());
		
		return clientVo;
	}

}
