package com.shockman.webdev.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shockman.webdev.models.User;
import com.shockman.webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@PostMapping("/register")
	public User register(@RequestBody User user, HttpSession session) {
		User currentUser = repository.save(user);
		session.setAttribute("currentUser", currentUser);
		return currentUser;
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user, HttpSession session) {
		user = repository.findUserByCredentials(user.getUsername(), user.getPassword());
		session.setAttribute("currentUser", user);
		return user;
	}
	
	@GetMapping("/profile")
	public Optional<User> profile(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser == null) {
			return null;
		}
		return repository.findById(currentUser.getId());
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int id, @RequestBody User newUser) {
		Optional<User> optional = repository.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			return repository.save(user);
		}
		return null;
	}
	
	@GetMapping("/api/user/{userId}")
	public Optional<User> findUserByUserId(@PathVariable("userId") String userId) {
		int id = Integer.parseInt(userId);
		return repository.findById(id);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
}
