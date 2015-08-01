package ma.eventmanager.actions;

import java.util.Collection;
import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionSupport;


@Namespace("/")
@ResultPath(value = "/")
public class AuthenticationActions extends ActionSupport
{
	
	private String logout;
	private String error;
	private String msg;

	@Action(value = "login", results = {@Result(name="success",location="/login.jsp")})
	public String login(){
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("username: " + userDetails.getUsername());
//        System.out.println("password: " + userDetails.getPassword());
//        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
//        for (Iterator it = authorities.iterator(); it.hasNext();) {
//            SimpleGrantedAuthority authority = (SimpleGrantedAuthority) it.next();
//            System.out.println("Role: " + authority.getAuthority());
//        }
 
		
		if (error != null) {
			error = "Invalid username and password!";
		}
 
		if (logout != null) {
			msg = "You've been logged out successfully";
		}
        return SUCCESS;
	}

	public String getLogout()
	{
		return logout;
	}

	public void setLogout(String logout)
	{
		this.logout = logout;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	
}
