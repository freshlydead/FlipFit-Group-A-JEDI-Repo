package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;

public interface UserDAO {
    User validateUser(String username, String password);
    boolean registerGymOwner(GymOwner gymOwner);
    boolean registerCustomer(Customer customer);
    boolean updateUser(User user);
    boolean addUser(User user);
}
