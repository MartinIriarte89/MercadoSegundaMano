package com.martiniriarte.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.martiniriarte.model.User;
import com.martiniriarte.repository.UserDAO;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

	private final UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User myUser = userDAO.findByEmail(username);

		UserBuilder builder = null;

		if (myUser != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(myUser.getEmail());
			builder.disabled(false);
			builder.password(myUser.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			throw new UsernameNotFoundException("Usuario " + username + " no encontrado.");
		}

		return builder.build();
	}

}
