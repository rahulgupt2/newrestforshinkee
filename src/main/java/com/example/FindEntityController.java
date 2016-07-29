package com.example;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



/*
 * @RestController : First of all, we are using Spring 4 new @RestController
 * annotation. This annotation eliminates the need of annotating each method
 * with @ResponseBody. Under the hood, @RestController is itself annotated with
 * @ResponseBody, and can be considered as combination of @Controller and
 * @ResponseBody.
 */

@RestController
public class FindEntityController {

	
	
	private static int number;
	private static int number1;
	int i = 0;
	private static BigInteger nextId;
	private static Map<BigInteger, User> userMap;
	
	private static User save(User user) {
		
		if(userMap == null) {
			userMap = new HashMap<BigInteger, User>();
			nextId = BigInteger.ONE;					
		}
		user.setUserId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		userMap.put(user.getUserId(), user);
		
		
	return user;	
	}
	
private static boolean delete(String userId) {
			
	
		
	return false;	
	}
	
	
	
	
	
	
	
	static {
		User userOne = new User();
		userOne.setUserAddress("sb road");
		userOne.setUserName("shinkee");
		save(userOne);
		
		User userTwo = new User();
		
		userTwo.setUserAddress("sb road");
		userTwo.setUserName("rahul");
		save(userTwo);
		
		
		User userThree = new User();
		userThree.setUserAddress("akrudi");
		userThree.setUserName("satish");
		save(userThree);		
		
		
		User userFour = new User();
		userFour.setUserAddress("kothrud");
		userFour.setUserName("vaibhav");
		save(userFour);
		
		User userFive = new User();
		userFive.setUserAddress("ghorpadi");
		userFive.setUserName("varun");
		save(userFive);

	}
	
	
	
	private static final Logger LOG = LoggerFactory.getLogger(FindEntityController.class);
	
	
	@CrossOrigin
	@RequestMapping(value = "/users",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Collection<User>> getUsers() {
				
		Collection<User> users =  userMap.values();		
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
				
	}
	
	@CrossOrigin
	@RequestMapping(value = "/users",
					method = RequestMethod.OPTIONS,
					produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Collection<User>> getUserList() {
				
		Collection<User> users =  userMap.values();		
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
				
	}
	/*
	@RequestMapping(value = "/postuser", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<> postUsers(User user) {	
				
	}		
	*/
	
	
	@CrossOrigin
	@RequestMapping(value = "/user", method = RequestMethod.GET ,
	produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<User> getUser(@RequestParam("id") BigInteger id) {
		
		
		
		User user = null;
		for(BigInteger key : userMap.keySet()) {
					System.out.println(key);
		// for big integer comparision we use equals method
			if(key.equals(id)) {
				user = userMap.get(key);
				System.out.println(user);
			}	
		}		
		if(user == null) {
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}	
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
		
	@CrossOrigin
	@RequestMapping(value = "/user", method = RequestMethod.POST ,
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> PostUser(@RequestBody User user) {
				
		if(user == null) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}		
		User userCreated = save(user);
		if(userCreated == null) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}				
		return new ResponseEntity<User>(userCreated, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE ,
	
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") BigInteger id) {
			
	boolean flag = false;		
		for(BigInteger key : userMap.keySet()) {
			if(key.equals(id)) {
				flag = true;				
			}	
		}		
		if(flag == true) {
			userMap.remove(id);
			return  new ResponseEntity<String>("{ \"message\" : \"user removed succefully\" } ", HttpStatus.OK);
		}		
		return  new ResponseEntity<String>("{ \"message\" : \"user not found\" } ", HttpStatus.OK);	
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/user", method = RequestMethod.PUT ,	
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> putUser(@RequestBody User user) {
			
	BigInteger id = user.getUserId();	
	boolean flag = false;		
		for(BigInteger key : userMap.keySet()) {
			if(key.equals(id)) {
				flag = true;				
			}	
		}		
		if(flag == true) {
			userMap.put(id, user);
			return  new ResponseEntity<String>("{ \"message\" : \"user updated succefully\" } ", HttpStatus.OK);
		}		
		return  new ResponseEntity<String>("{ \"message\" : \"user not found\" } ", HttpStatus.OK);	
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/FileUpload", 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
	
		
		String fileName = file.getOriginalFilename();
		String path = "src\\main\\resources\\static\\images\\" + fileName;
		
	String pathToSend =  "https://shinkeetechm.herokuapp.com" + "/images/" + fileName;
	//	String pathToSend =  "/images/" + fileName;
		byte[] bytes = file.getBytes();
		BufferedOutputStream buffStream = new BufferedOutputStream(
				new FileOutputStream(new File(path)));
		
		
		buffStream.write(bytes);
		buffStream.close();
		
		
		return new ResponseEntity<String>(" { \"messageOne\" : \"" + fileName + "\", "
				+ "\"messageTwo\" : \"file uploaded successfully\","
				+ "\"path\" : \"" + pathToSend + "\" } ", HttpStatus.OK);
		
	}
	
	
}	
	
	
	
	
