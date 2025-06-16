package service;

import model.UserProfile;
import dao.UserDAO;

public class ProfileService {
	private UserDAO userDAO = new UserDAO();

    public void createProfile(UserProfile user) {
        userDAO.saveUser(user);
    }
}	
