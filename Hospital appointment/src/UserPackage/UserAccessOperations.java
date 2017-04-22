package UserPackage;

import java.lang.*;

public class UserAccessOperations {
	public static void initializeUsers(String[] userList, UserList mainList) {
		for (int i = 0; i < userList.length; i += 7) {
			mainList.addUser(new User(userList[i], Integer.parseInt(userList[i + 1]), userList[i + 2], userList[i + 3],
					Long.parseLong(userList[i + 4]), userList[i + 5], userList[i + 6]));
		}
	}

	public static boolean verifyUsername(String[] dataBase, String input) {
		boolean temp = false;
		for (int i = 5; i < dataBase.length; i += +7) {
			if (input.equals(dataBase[i])) {
				temp = true;
			}
		}
		return temp;
	}

	public static boolean passwordVerify(String[] dataBase, String userName, String password) {
		int locale = 0;
		boolean temp = false;
		for (int i = 2; i < dataBase.length; i += 3) {
			if (dataBase[i].equals(userName)) {
				locale = i + 1;
			}
		}

		if (password.equals(dataBase[locale])) {
			temp = true;
		}
		return temp;
	}
}
