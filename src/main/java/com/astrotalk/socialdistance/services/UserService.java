package com.astrotalk.socialdistance.services;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrotalk.socialdistance.User;
import com.astrotalk.socialdistance.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public User addUser(User user){
		return repo.save(user);
	}
	
	public void addFriend(long id,long friendId) {
		
		User user = repo.findById(id).get();
		User friend = repo.findById(friendId).get();
		
		List<User> userFriendList = user.getFriendList();
		userFriendList.add(repo.getOne(friendId));
		user.setFriendList(userFriendList);
		repo.save(user);
		
		List<User> otherFriendList = friend.getFriendList();
		otherFriendList.add(repo.getOne(id));
		friend.setFriendList(otherFriendList);
		repo.save(friend);
	}

	public boolean removeFriend(long id,long friendId) {
		User user = repo.findById(id).get();
		User removeUser = repo.getOne(friendId);
		List<User> friendList = user.getFriendList();
		List<User> otherFriendList = removeUser.getFriendList();
		
		if(!friendList.contains(removeUser)) return false;
		
		if(friendList.contains(removeUser)) {
			friendList.remove(repo.getOne(friendId));
			user.setFriendList(friendList);
			repo.save(user);
		}
		if(otherFriendList.contains(user)) {
			otherFriendList.remove(repo.getOne(id));
			removeUser.setFriendList(otherFriendList);
			repo.save(removeUser);
		}
		return true;
	}
	
	public List<User> getFriendList(long id) {
		Optional<User> u = repo.findById(id);
		if(u.isPresent()){
			List<User> friendList = u.get().getFriendList();
			return friendList;
		}
		return null;
	}
	
	public List<User> getAllConnection(long id, long k){
		User user = repo.findById(id).get();
		List<User> connectionList = user.getFriendList();
		List<Long> ids = connectionList.stream().map(u -> u.getId()).collect(Collectors.toList());
		List<Long> allLevelIds = iterateConn(ids,k);
		allLevelIds.remove((long) id);
		List<User> allUsers = allLevelIds.stream().map(uid -> repo.getOne(uid)).collect(Collectors.toList()) ;
		return allUsers;
	}

	private List<Long> iterateConn(List<Long> connectionListIds, long k){
		
		Set<Long> allLevelIds = new LinkedHashSet<Long>(connectionListIds);
		Set<Long> nextLevelIds = new LinkedHashSet<Long>(connectionListIds);
		while(k!=0) {
			Set<Long> tempIds = new LinkedHashSet<Long>();
			
			for(long u: nextLevelIds)
				tempIds.addAll(repo.getOne(u).getFriendList().stream().map(p -> p.getId()).collect(Collectors.toList()));
			nextLevelIds.clear();
			nextLevelIds.addAll(tempIds);
			allLevelIds.addAll(nextLevelIds);
			k--;
		}
		return new ArrayList<Long>(allLevelIds);
	}

}




