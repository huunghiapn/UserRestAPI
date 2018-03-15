package Application;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class UserController {
    UserDao userDao = new UserDao();

    /**
     * Handle list all users action
     * @return list of all users
     */
    @RequestMapping(value = "/users", method = GET)
    public List<User> getUsers() {
        try {
            return userDao.getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Handle add user action by id or user name
     * @param username
     * @return user was created
     */
    @RequestMapping(value = "/useradd", method=POST)
    public User createUser(@RequestParam(value="username") String username, @RequestParam(value="passwd") String passwd) {
        try {
            if(StringUtils.isEmpty(username) || StringUtils.isEmpty(passwd)) {
                throw new RuntimeException("User name and password must have value!");
            }
            if(userDao.findByUsername(username) != null) {
                throw new RuntimeException("User name is used by another!");
            }
            return userDao.createUser(username, passwd);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Handle edit user action by id or user name
     * @param id
     * @param username
     * @return user was edited
     */
    @RequestMapping(value = "/useredit", method=POST)
    public User editUser(@RequestParam(value="id", required = false) String id, @RequestParam(value="username",
            required = false) String username, @RequestParam(value="passwd", required = false) String passwd) {
        try {
            User user;
            if(StringUtils.isEmpty(username) && StringUtils.isEmpty(id)) {
                throw new RuntimeException("User id or user name must have value!");
            }
            if(!StringUtils.isEmpty(id))
                user = userDao.findById(Long.valueOf(id));
            else
                user = userDao.findByUsername(username);
            if(user == null) {
                throw new RuntimeException("User not found!");
            }
            if(StringUtils.isEmpty(passwd)) {
                throw new RuntimeException("Nothing to change!");
            }
            user.setUserPasswd(passwd);
            return userDao.editUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Handle delete user action by id or user name
     * @param id
     * @param username
     * @return user was deleted
     */
    @RequestMapping(value = "/userdel", method=DELETE)
    public User deleteUser(@RequestParam(value="id", required = false) String id,
                           @RequestParam(value="username", required = false) String username) {
        try {
            User user;
            if(StringUtils.isEmpty(username) && StringUtils.isEmpty(id)) {
                throw new RuntimeException("User id or user name must have value!");
            }
            if(!StringUtils.isEmpty(id))
                user = userDao.findById(Long.valueOf(id));
            else
                user = userDao.findByUsername(username);
            if(user == null) {
                throw new RuntimeException("User not found!");
            }
            return userDao.deleteUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
