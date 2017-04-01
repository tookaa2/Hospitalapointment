import java.util.*;

public class UserList {
	protected ArrayList<User> userlist1 = new ArrayList<User>();

	public UserList() {
	}

	public void addUser(User userObject) {
		if (userObject.authority == 1) {
			userlist1.add(new Doctor(userObject.authority, userObject.username, userObject.password));
		} else if (userObject.authority == 2) {
			userlist1.add(new Reception(userObject.authority, userObject.username, userObject.password));
		} else {
			userlist1.add(new Patient(userObject.authority, userObject.username, userObject.password));
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

	public boolean verifyPassword(String username, String password) {
		boolean temp = false;
		for (int i = 0; i < userlist1.size(); i++) {
			if (userlist1.get(i).getUsername().equals(username)) {
				if (userlist1.get(i + 1).getPassword().equals(password)) {
					temp = true;
				}

			}

		}
		return temp;
	}

}
