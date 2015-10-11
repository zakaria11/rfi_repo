package ma.eventmanager.entitys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ma.eventmanager.model.UserVo;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String mail;
	
	@Column
	private String description;

	@Column
	private String phone;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	
	public User(){}
	
	public User(UserVo userVo){
		
		if(userVo.getId() != null){
			this.id = Integer.parseInt(userVo.getId());
		}
		this.setName(userVo.getName());
		this.setUsername(userVo.getUsername());
		this.setPassword(userVo.getPassword());
		this.setMail(userVo.getMail());
		this.setPhone(userVo.getPhone());
		this.setDescription(userVo.getDescription());
	}

	public User(String username, String password, String phone){
		this.username = username;
		this.password = password;
		this.phone =phone;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}


	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public Set<UserRole> getUserRole()
	{
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole)
	{
		this.userRole = userRole;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public UserVo toUserVo()
	{
		UserVo userVo = new UserVo();
		
		userVo.setId(this.getId()+"");
		userVo.setName(this.getName());
		userVo.setUsername(this.getUsername());
		userVo.setPassword(this.getPassword());
		userVo.setMail(this.getMail());
		userVo.setPhone(this.getPhone());
		userVo.setDescription(this.getDescription());
		
		return userVo;
	}
	
	
	
}
