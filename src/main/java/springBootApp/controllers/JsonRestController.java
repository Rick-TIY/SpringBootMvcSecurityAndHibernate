package springBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springBootApp.entities.User;
import springBootApp.entities.UserDao;

@RestController
public class JsonRestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/rest/")
    public String index() {
        return "<h1>Greetings from Spring Boot!</h1>" +
                "Use the following Commands:<br><br>" +
                "CREATE USER: /rest/createUser?email=[email]&name=[name]<br>" +
                "GET ALL USERS: /rest/getAllUsers<br>" +
                "GET USER BY ID: /rest/getUser?id=[id]<br>" +
                "GET USER BY EMAIL: /rest/getUserByEmail?email=[email]<br>" +
                "UPDATE USER: /rest/updateUser?id=[id]&email=[email]&name=[name]<br>" +
                "DELETE USER: /rest/deleteUser?id=[id]<br><br><br><br>";
    }

    @RequestMapping("/rest/createUser")
    public String create(String email, String name) {
        try {
            User user = new User(email, name);
            userDao.save(user);
            return "User "+user.getId()+" created!";
        }
        catch (Exception e) {
            return "Error creating user: " + e.toString();
        }
    }

    @RequestMapping("/rest/getAllUsers")
    public Iterable<User> getUser() {
        return userDao.findAll();
    }

    @RequestMapping("/rest/getUser")
    public User getUser(long id) {
        return userDao.findOne(id);
    }

    @RequestMapping("/rest/getUserByEmail")
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @RequestMapping("/rest/updateUser")
    public String updateUser(long id, String email, String name) {
        try {
            User user = userDao.findOne(id);
            user.setEmail(email);
            user.setName(name);
            userDao.save(user);
            return "User "+id+" updated!";
        }
        catch (Exception e) {
            return "Error updating user: " + e.toString();
        }
    }

    @RequestMapping("/rest/deleteUser")
    public String deleteUser(long id) {
        try {
            userDao.delete(userDao.findOne(id));
            return "User "+id+" deleted!";
        }
        catch (Exception e) {
            return "Error deleting user:" + e.toString();
        }
    }
}