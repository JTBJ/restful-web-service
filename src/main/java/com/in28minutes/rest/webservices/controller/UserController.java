package com.in28minutes.rest.webservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.dao.UserDao;
import com.in28minutes.rest.webservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.model.user.User;

@RestController
public class UserController {

	private UserDao userDao;

	// == annotation not needed here as constructor injection is automatic
	// == since spring 4.1
	@Autowired
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return userDao.findAll();
	}

	@GetMapping("/users/{userId}")
	public EntityModel<User> getUserById(@PathVariable("userId") int userId) {
		User user = userDao.findById(userId);
		if(user == null) {
			throw new UserNotFoundException("Invalid id- " + userId);
		}
		
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getUsers());
		model.add(linkToUsers.withRel("all-user"));
		return model;
	}

	// == adds new user and returns status code
	// == stores uri in header
	@PostMapping("/users")
	public ResponseEntity<User> createUserReturnResponseEntity(@Valid @RequestBody User user) {
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{userId}")
		.buildAndExpand(userDao.save(user).getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
		
		// == returns created user and status code created
//		return new ResponseEntity<>(userDao.save(user), HttpStatus.CREATED);
	}
	
	// == adds new user and returns uri path to retrieve user
//	@PostMapping("/users")
	public URI createUserReturnUri(@Valid @RequestBody User user) {
		return ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{userId}")
		.buildAndExpand(userDao.save(user).getId())
		.toUri();
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUserById(@PathVariable("userId") int userId) {
		User user = userDao.deleteById(userId);
		
		if(user == null) {
			throw new UserNotFoundException("Invalid id- " + userId);
		} 
	}
}
