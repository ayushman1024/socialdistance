
# Social Distance


[[Linkedin](https://img.icons8.com/fluent/48/000000/linkedin.png)](https://www.linkedin.com/in/ayushman1024/)

[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/W7W01JFRM) <a href="https://www.buymeacoffee.com/ar7HQlZ" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-black.png" alt="Buy Me A Coffee" width=15% height=15%></a>

Rest API implemented in Spring Boot and deployed on aws ec2.

## Requirement
Implement a web-app using java spring-boot. 
We do not expect a UI but all interactions must be via REST APIs. Expecting following functionalities :
-  A user should be able to create his profile. 
-  A user should be able to add another user in his friend list. Friend list is mutual i.e if A is friend of B, then B is also friend of A.
- A user should be able to remove another user from his friend list. 4. A user should be able to view his friend list.
- Given an input integer K, a user should be able to view all connections at distance K from him.
- Distance is defined as the minimum steps needed to reach from user A to user B. Some examples for more clarity.
	- For example, if A is a friend of B, then distance of B from A is 1. 
	-  A is friend of B. B is a friend of C. C is a friend of D. Then Distance(A,D) = 3 
	-  A ⇔ B , B ⇔ C, C ⇔ D, D ⇔ E, C ⇔ E. In this case Distance(A,E) = 3 as E can be reached via C.
- You can either use some database or some in-memory data structure to store information.

## Technology stack
- Spring Boot
- Spring Data JPA
- Amazon RDS (MySQL)
- Amazon AWS EC2

## REST API

URL http://ec2-3-132-212-32.us-east-2.compute.amazonaws.com:8080/

|  | Method |	Mapping|
|--|--|--|
| Add new user  | POST  |  /addUser |
| Connect two user as friend   | GET | /addFriend/{uid}/{fid} |
| Remove friendship | DELETE | /removeFriend/{uid}/{fid} |
| Get all connections at K distance from user uid  | GET | /getDistantConn/{uid}/{k} |
| Get friendlist of user with id "uid" | GET | /getFriendList/{uid} |

- Add new user **Post /addUser** 
{
"name" : "user name"
"username" : " unique username"
"email" : " user's email"
}
