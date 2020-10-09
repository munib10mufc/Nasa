package com.nasa.eonet.nasa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nasa.eonet.nasa.beans.dao.Users;

@Service
public interface UserRepository extends JpaRepository<Users, Long> {
	Users findByNameIgnoreCase(String name);

}
