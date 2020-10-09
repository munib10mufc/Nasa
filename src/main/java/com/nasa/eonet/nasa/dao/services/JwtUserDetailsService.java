package com.nasa.eonet.nasa.dao.services;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nasa.eonet.nasa.beans.UserDTO;
import com.nasa.eonet.nasa.beans.dao.Users;
import com.nasa.eonet.nasa.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private static final Logger logger = LogManager.getLogger(JwtUserDetailsService.class);

	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users dbUser = userDao.findByNameIgnoreCase(username);
		if (null == dbUser) {
			throw new UsernameNotFoundException("Invalid username: " + username + " provided");
		}
		
		return new User(dbUser.getName(), dbUser.getPassword(), new ArrayList<>());
	}

	public Users save(UserDTO user) {
		Users newUser = new Users();
		newUser.setName(user.getName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		Users dbUser = userDao.findByNameIgnoreCase(user.getName());
		if (null != dbUser) {
			logger.info(logger.isInfoEnabled() ? "User already exists in db, going to return data from db" : null);
			return dbUser;
		}
		logger.info(logger.isInfoEnabled() ? "User successfully stored in db" : null);
		return userDao.save(newUser);
	}

}