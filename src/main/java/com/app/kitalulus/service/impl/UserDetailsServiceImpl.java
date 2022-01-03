package com.app.kitalulus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.kitalulus.dto.AppUserDetails;
import com.app.kitalulus.model.User;
import com.app.kitalulus.repository.RepositoryUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Aweem Aslam
 *
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private RepositoryUser repositoryUsers;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Loading user: {}", username);

		User user = getUser(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with name " + username + " not found");
		}
		return setUserDetailsObject(user);
	}

	private UserDetails setUserDetailsObject(User users) {
		if (users != null) {
			AppUserDetails user = new AppUserDetails(users.getPrincipalName(), users.getPassword());
			List<String> authorities = new ArrayList<>();
			if (users.getRoles() != null) {
				users.getRoles().stream().forEach(role -> {
					authorities.add(role.getCode());
				});
				user.setAuthorities(AuthorityUtils.createAuthorityList(authorities.toArray(new String[] {})));
			}

			return user;
		}
		return null;
	}


	/**
	 * Returns SQL query to load user
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	private User getUser(String username) {
		return repositoryUsers.findByPrincipalName(username);
	}

}
