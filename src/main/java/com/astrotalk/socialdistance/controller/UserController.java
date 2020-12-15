package com.astrotalk.socialdistance.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.astrotalk.socialdistance.User;
import com.astrotalk.socialdistance.dto.NewUserDTO;
import com.astrotalk.socialdistance.resources.UserConnections;
import com.astrotalk.socialdistance.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	  @PostMapping("/addUser")
	  public ResponseEntity<User> addUser(@RequestBody NewUserDTO newUser) {
	    User user = new User();
	    User generatedUser =null;
	    BeanUtils.copyProperties(newUser, user);
	    generatedUser = userService.addUser(user);
	    return new ResponseEntity<User>(generatedUser, HttpStatus.CREATED);
	  }

	  @GetMapping("/addFriend/{uid}/{fid}")
	  public ResponseEntity<Boolean> addFriend(@PathVariable("uid") long requester,@PathVariable("fid") long receiver) {
	    userService.addFriend(requester, receiver);
	    return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	  }

	  @DeleteMapping("/removeFriend/{uid}/{fid}")
	  public ResponseEntity<Boolean> removeFriend(@PathVariable("uid") long requester, @PathVariable("fid") long receiver) {
	    userService.removeFriend(requester, receiver);
	    return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	  }

	  @GetMapping("/getDistantConn/{uid}/{k}")
	  public ResponseEntity<UserConnections> findAllDistantFriend(@PathVariable("uid") long id, @PathVariable("k") int k) {
		  UserConnections response = new UserConnections();
		  response.setDistance(k);
		  response.setUserList(userService.getAllConnection(id, k));
		  System.out.println(response.toString());
		  return new ResponseEntity<UserConnections>(response, HttpStatus.OK);
	  }
	  
	  @GetMapping("/getFriendList/{uid}")
	  public ResponseEntity<UserConnections> getFriendList(@PathVariable("uid") long id) {
		  UserConnections response = new UserConnections();
		  response.setDistance(1);
		  response.setUserList(userService.getFriendList(id));
		  return new ResponseEntity<UserConnections>(response, HttpStatus.OK);
	  }
}
