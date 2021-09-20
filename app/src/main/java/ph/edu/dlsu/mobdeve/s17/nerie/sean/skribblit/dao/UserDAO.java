package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

public interface UserDAO {

    long addUser(User user);
    ArrayList<User> getUsers();
    User getUser(int userid);
    int updateUser(User user);
    int deleteUser(int userid);

}
