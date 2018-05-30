package com.myRestfulwebService.example.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.myRestfulwebService.example.model.UserPosts;
import com.myRestfulwebService.example.repository.UserObjectRepository;
import com.myRestfulwebService.example.repository.UserPostsRepository;

@RestController
public class UsersJPAController {

	@Autowired
	private UserObjectRepository userObjectRepository;
	
	@Autowired
	private UserPostsRepository userPostsRepository;

	@GetMapping("/jpa/allUsers")
	public List<UserObject> getAllUsers() {
		return userObjectRepository.findAll();
	}

	@GetMapping("/jpa/user/{id}")
	public Resource<UserObject> getOneUser(@PathVariable int id) {
		Optional<UserObject> foundUser = userObjectRepository.findById(id);

		if (!foundUser.isPresent())
			throw new UserNotFoundException("User with id=" + id + " doesnt exist");

		Resource<UserObject> resource = new Resource<UserObject>(foundUser.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;

	}

	@PostMapping("/jpa/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserObject user) {
		UserObject savedUser = userObjectRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/user/{id}")
	public void deleteOneUser(@PathVariable int id) {
		userObjectRepository.deleteById(id);
	}

	@GetMapping("/jpa/user/{id}/post")
	public List<UserPosts> getUserPosts(@PathVariable int id) {
		Optional<UserObject> foundUser = userObjectRepository.findById(id);

		if (!foundUser.isPresent())
			throw new UserNotFoundException("User with id=" + id + " doesnt exist");

		return foundUser.get().getPosts();
	}
	
	@PostMapping("/jpa/user/{id}/post")
	public ResponseEntity<Object> createUserPosts(@PathVariable int id, @RequestBody UserPosts post) {
		Optional<UserObject> foundUser = userObjectRepository.findById(id);
		
		if (!foundUser.isPresent())
			throw new UserNotFoundException("User with id=" + id + " doesnt exist");
		
		post.setUser(foundUser.get());
		UserPosts userPost = userPostsRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
