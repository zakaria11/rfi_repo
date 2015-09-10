package ma.eventmanager.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "role", "username" }))
public class UserRole
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer userRoleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	private User user;
	
	
	@Column
	private String role;
	
	
	
	public UserRole(){}


	public UserRole(String role){
		this.role = role;
	}


	public UserRole(User user, String role) {
		this.user = user;
		this.role = role;
	}


	public Integer getUserRoleId()
	{
		return userRoleId;
	}


	public void setUserRoleId(Integer userRoleId)
	{
		this.userRoleId = userRoleId;
	}


	public User getUser()
	{
		return user;
	}


	public void setUser(User user)
	{
		this.user = user;
	}


	public String getRole()
	{
		return role;
	}


	public void setRole(String role)
	{
		this.role = role;
	}
	
	
}
