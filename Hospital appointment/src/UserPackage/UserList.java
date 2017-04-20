package UserPackage;

import java.util.*;

public class UserList {
	protected ArrayList<User> userlist1 = new ArrayList<User>();

	public UserList() {
	}

	public void addUser(User userObject) {
		if (userObject.authority.equals("D")) {
			userlist1.add(new Doctor(userObject.getAuthority(), userObject.getID(), userObject.getFirstName(), userObject.getLastName(),
					userObject.getPhoneNumber(), userObject.getUsername(), userObject.getPassword()));
		} else if (userObject.getAuthority().equals("R")) {
			userlist1.add(new Reception(userObject.getAuthority(), userObject.getID(), userObject.getFirstName(), userObject.getLastName(),
					userObject.getPhoneNumber(), userObject.getUsername(), userObject.getPassword()));
		} else if (userObject.authority.equals("P")){
			userlist1.add(new Patient(userObject.getAuthority(), userObject.getID(), userObject.getFirstName(), userObject.getLastName(),
					userObject.getPhoneNumber(), userObject.getUsername(), userObject.getPassword()));
		}

	}
	

	public boolean verifyExist(String username) {
		boolean temp = false;
		for (int i = 0; i < userlist1.size(); i++) {

			if (userlist1.get(i).username.equals(username)) {
				temp = true;

			}

		}
		return temp;
	}
	public boolean verifyExist(int userID,String userType) {
		boolean temp = false;
		for (int i = 0; i < userlist1.size(); i++) {

			if (userlist1.get(i).getID()==userID&&userlist1.get(i).getAuthority().equals(userType)) {
				temp = true;

			}

		}
		return temp;
	}

	public boolean verifyPassword(String username, String password) {
		boolean temp = false;
		for (int i = 0; i < userlist1.size(); i++) {
			if (userlist1.get(i).getUsername().equals(username)) {
				if (userlist1.get(i).getPassword().equals(password)) {
					temp = true;
					i = userlist1.size();/// Break loop!
				}

			}

		}
		return temp;
	}

	public User getUserObject(String username) {
		User temp = null;
		for (int i = 0; i < userlist1.size(); i++) {
			if (userlist1.get(i).getUsername().equals(username)) {
				if (userlist1.get(i).getAuthority().equals("D")) {
			
					temp = new Doctor(userlist1.get(i).getAuthority(), userlist1.get(i).getID(),
							userlist1.get(i).getFirstName(), userlist1.get(i).getLastName(),
							userlist1.get(i).getPhoneNumber(), userlist1.get(i).getUsername(), "0");
					break;
				} else if (userlist1.get(i).getAuthority().equals("R")) {
		
					temp = new Reception(userlist1.get(i).getAuthority(), userlist1.get(i).getID(),
							userlist1.get(i).getFirstName(), userlist1.get(i).getLastName(),
							userlist1.get(i).getPhoneNumber(), userlist1.get(i).getUsername(), "0");
					break;
				} else {
					temp = new Patient(userlist1.get(i).getAuthority(), userlist1.get(i).getID(),
							userlist1.get(i).getFirstName(), userlist1.get(i).getLastName(),
							userlist1.get(i).getPhoneNumber(), userlist1.get(i).getUsername(), "0");
					break;
				}
			}
		}
		return temp;
	}

	public User getUserObject(int ID) {
		User temp = null;

		for (int i = 0; i < userlist1.size(); i++) {
			if (userlist1.get(i).getID() == ID) {
				if (userlist1.get(i).getAuthority().equals("D")) {
		
					temp = new Doctor(userlist1.get(i).getAuthority(), userlist1.get(i).getID(),
							userlist1.get(i).getFirstName(), userlist1.get(i).getLastName(),
							userlist1.get(i).getPhoneNumber(), userlist1.get(i).getUsername(), "0");
					break;
				} else if (userlist1.get(i).getAuthority().equals("R")) {
					temp = new Reception(userlist1.get(i).getAuthority(), userlist1.get(i).getID(),
							userlist1.get(i).getFirstName(), userlist1.get(i).getLastName(),
							userlist1.get(i).getPhoneNumber(), userlist1.get(i).getUsername(), "0");
					break;
				} else {
					temp = new Patient(userlist1.get(i).getAuthority(), userlist1.get(i).getID(),
							userlist1.get(i).getFirstName(), userlist1.get(i).getLastName(),
							userlist1.get(i).getPhoneNumber(), userlist1.get(i).getUsername(), "0");
					break;
				}

			}
		}
		return temp;
	}

}
