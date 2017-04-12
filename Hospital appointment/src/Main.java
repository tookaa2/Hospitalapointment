import java.util.*;
import AppointmentData.AppointmentList;

public class Main {
	public static void main(String args[]) {
		String[] userList;
		String[] appointmentList;
		UserList mainList = new UserList();
		userList = ReadProcess.readFile("C:\\Users\\Donut\\Documents\\GitHub\\test1.txt");
		appointmentList = ReadProcess.readFile("C:\\Users\\Donut\\Documents\\GitHub\\appoint1.txt");
		AppointmentList appointmentData1 = new AppointmentList();
		AppointmentList.initializeAppointments(appointmentList, appointmentData1);
		UserAccessOperations.initializeUsers(userList, mainList);
		Main(mainList, appointmentData1);
	}

	public static void Main(UserList mainList, AppointmentList appointData) {
		User temporaryUser;
		int failTries = 1;
		Scanner input = new Scanner(System.in);

		System.out.println("CAMT Hospital reservation center. \nPlease login to continue \nUsername:");
		String username = input.next();
		while (!mainList.verifyExist(username)) {
			System.out.println("Incorrect Username,please try again.\nUsername:");
			username = input.next();
		}
		System.out.println("Password:");

		String password = input.next();
		while (!mainList.verifyPassword(username, password) && failTries < 3) {
			System.out.println("Incorrect Password,please try again.\nUsername:");
			password = input.next();
			failTries++;
		}
		if (mainList.verifyExist(username)) {
			if (mainList.verifyPassword(username, password)) {
				System.out.println("Login Success!");
				temporaryUser=mainList.getUserObject(username);
				
				
				menu2(mainList, appointData, temporaryUser);
			} else {
				System.out.println("Invalid! Program terminating. . .");
			}
		}

	}

	public static void menu2(UserList mainList, AppointmentList appointData, User currentUser) {
		System.out.println("Welcome " +currentUser.getUserType()+ currentUser.getFirstName());
		System.out.println("Please select what you want to do:");
	}
}
