package com.cdweb.service.impl;

import com.cdweb.constant.SystemConstant;
import com.cdweb.entity.UserEntity;
import com.cdweb.repository.RoleRepository;
import com.cdweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = this.userRepository.findByEmailIgnoreCaseAndIsEnabled(email,SystemConstant.ACTIVE_USER);
		if (userEntity == null) {
			System.out.println("User not found! " + email);
			throw new UsernameNotFoundException("email " +email + " was not found in the database or not active");
		}


		List<String> roleNames = this.roleRepository.getRoleNames(userEntity.getId());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		UserDetails userDetails = (UserDetails) new User(userEntity.getEmail(), //
				userEntity.getPassword(), grantList);

		return userDetails;
	}

}
