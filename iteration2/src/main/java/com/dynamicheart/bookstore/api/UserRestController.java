package com.dynamicheart.bookstore.api;

import com.dynamicheart.bookstore.domain.User;
import com.dynamicheart.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by dynamicheart on 4/20/2017.
 */

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    //Retrieve All Users
    @RequestMapping(value = "/user", method = GET)
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = userService.findUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    //Retrieve Single User
    @RequestMapping(value = "/user/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //Create a User
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

        if (userService.isExist(user)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.save(user);

        System.out.println(user.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        headers.set("userID",Long.toString(user.getId()));
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update a User
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {

        if (userService.findOne(id)==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.update(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //Delete a User
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    //Delete All Users
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        //userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
