package com.astrotalk.socialdistance.resources;

import java.util.List;

import com.astrotalk.socialdistance.User;

public class UserConnections {
	
	int distance;
	private List<User> userList;

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}