package Application;

import Application.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String HOME_PATH = "/home/tomcat8/uploads/";
    /**
     * List all users
     * @return List of all users
     */
    public List<User> getAllUsers(){

        List<User> userList = null;
        try {
            File file = new File(HOME_PATH + "Users.dat");
            if (!file.exists()) {
                // create default user
                User user = new User(1, "NghiaNH", "nothing");
                userList = new ArrayList<User>();
                userList.add(user);
                saveUserList(userList);
            } else{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList = (List<User>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Find exist user by user name
     * @param username
     * @return user if exist or null
     */
    public User findByUsername(String username){
        User user = null;
        List<User> userList = null;
        try {
            File file = new File(HOME_PATH + "Users.dat");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList = (List<User>) ois.readObject();
                ois.close();

                for(User userTmp: userList) {
                    if(username.equals(userTmp.getUserName())) {
                        user = userTmp;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Find exist user by user id
     * @param id
     * @return user if exist or null
     */
    public User findById(long id){
        User user = null;
        List<User> userList = null;
        try {
            File file = new File(HOME_PATH + "Users.dat");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList = (List<User>) ois.readObject();
                ois.close();

                for(User userTmp: userList) {
                    if(id ==userTmp.getId()) {
                        user = userTmp;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Save new user to database
     * @param name
     * @param passwd
     * @return user was saved
     */
    public User createUser(String name, String passwd){
        long id;
        User user = null;
        List<User> userList = null;
        try {
            File file = new File(HOME_PATH + "Users.dat");
            if (!file.exists()) {
                id = 1;
                user = new User(id, name, passwd);
                userList = new ArrayList<User>();
                userList.add(user);
                saveUserList(userList);
            } else{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList = (List<User>) ois.readObject();
                ois.close();

                id = userList.size() + 1;
                user = new User(id, name, passwd);
                userList.add(user);
                saveUserList(userList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Save edited user to database
     * @param user
     * @return user was edited
     */
    public User editUser(User user){
        List<User> userList = null;
        try {
            File file = new File(HOME_PATH + "Users.dat");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList = (List<User>) ois.readObject();
                ois.close();

                for(User userTmp: userList) {
                    if(userTmp.equals(user)) {
                        userTmp.setUserPasswd(user.getUserPasswd());
                        break;
                    }
                }
                saveUserList(userList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Delete user from database
     * @param user
     * @return user was deleted
     */
    public User deleteUser(User user){
        List<User> userList = null;
        try {
            File file = new File(HOME_PATH + "Users.dat");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList = (List<User>) ois.readObject();
                ois.close();

                userList.remove(user);
                saveUserList(userList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Save list of user to data file
     * @param userList
     */
    private void saveUserList(List<User> userList){
        try {
            File file = new File(HOME_PATH + "Users.dat");

            FileOutputStream fos;
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}