package com.pnp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pnp.entity.CustomUserDetails;
import com.pnp.entity.User;
import com.pnp.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService{
    @Autowired
	private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user=repo.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("Invalid username"));
		return user.map(CustomUserDetails::new).get();
	}
/*	 @Autowired
		private UserRepository repo;
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Optional<User> user=Optional.of(repo.findUserByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User ", "username", username)));
			return user;
		}*/

}
