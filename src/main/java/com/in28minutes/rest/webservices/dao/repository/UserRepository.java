package com.in28minutes.rest.webservices.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.rest.webservices.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
