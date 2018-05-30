package com.myRestfulwebService.example.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myRestfulwebService.example.exceptions.UserNotFoundException;
import com.myRestfulwebService.example.model.UserObject;
import com.myRestfulwebService.example.services.UserDaoService;

@RestController
public class UsersController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/allUsers")
	public List<UserObject> getAllUsers() {
		return userDaoService.findAllUsers();
	}

	@GetMapping("/user/{id}")
	public Resource<UserObject> getOneUser(@PathVariable int id) {
		UserObject foundUser = userDaoService.findOneUser(id);

		if (foundUser == null)
			throw new UserNotFoundException("User with id=" + id + " doesnt exist");

		Resource<UserObject> resource = new Resource<UserObject>(foundUser);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;

	}

	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserObject user) {
		UserObject savedUser = userDaoService.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/user/{id}")
	public void deleteOneUser(@PathVariable int id) {
		UserObject deleteUser = userDaoService.deleteOneUser(id);

		if (deleteUser == null)
			throw new UserNotFoundException("User with id=" + id + " doesnt exist");

	}

}
