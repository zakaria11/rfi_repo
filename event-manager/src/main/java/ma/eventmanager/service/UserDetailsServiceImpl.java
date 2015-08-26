package ma.eventmanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.User;
import ma.eventmanager.entitys.UserRole;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService{

	private EventManagerDao eventManagerDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

		User user = eventManagerDao.getUsers(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);
	}

	
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles){
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}



	private UserDetails buildUserForAuthentication(User user,List<GrantedAuthority> authorities){ 
		return new  org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
	}



	public EventManagerDao getEventManagerDao()
	{
		return eventManagerDao;
	}

	public void setEventManagerDao(EventManagerDao eventManagerDao)
	{
		this.eventManagerDao = eventManagerDao;
	}
	

}
