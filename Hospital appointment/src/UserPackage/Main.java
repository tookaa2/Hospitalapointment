package UserPackage;

import java.io.IOException;
import java.text.*;
import java.util.*;
import AppointmentData.AppointmentList;

public class Main {
	public static void main(String args[]) throws IOException {
		/// DateFormat todayDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		/// Date day = new Date();
		/// System.out.println(todayDate.format(day));
		Calendar today = Calendar.getInstance();
		int dateToday = today.get(Calendar.DATE);
		int month = today.get(Calendar.MONTH);
		int year = today.get(Calendar.YEAR) - 543;
		System.out.println(dateToday + "," + month + "," + year);
		today.add(Calendar.DATE, 15);
		dateToday = today.get(Calendar.DATE);
		month = today.get(Calendar.MONTH);
		year = today.get(Calendar.YEAR) - 543;
		System.out.println(dateToday + "," + month + "," + year);
		Date date = new Date();
		String[] userList;
		String[] appointmentList;
		String[] changeList;
		UserList mainList = new UserList();
		userList = ReadProcess.readFile("C:\\Users\\Donut\\Documents\\GitHub\\test1.txt");
		appointmentList = ReadProcess.readFile("C:\\Users\\Donut\\Documents\\GitHub\\appoint1.txt");
		changeList = ReadProcess.readFile("C:\\Users\\Donut\\Documents\\GitHub\\change1.txt");
		AppointmentList appointmentData1 = new AppointmentList();
		AppointmentList changeData = new AppointmentList();
		AppointmentList.initializeAppointments(appointmentList, appointmentData1);
		AppointmentList.initializeAppointments(changeList, changeData);
		UserAccessOperations.initializeUsers(userList, mainList);
		Main(mainList, appointmentData1, changeData);
	}

	public static void Main(UserList mainList, AppointmentList appointData, AppointmentList changeData)
			throws IOException {
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
				temporaryUser = mainList.getUserObject(username);
				if (temporaryUser instanceof Doctor) {
					menuDoctor(mainList, appointData, temporaryUser,changeData);
				} else if (temporaryUser instanceof Reception) {
					menuReception(mainList, appointData, temporaryUser, true);
				} else {
					menuPatient(mainList, appointData, temporaryUser, changeData, true);
				}

			} else {
				System.out.println("Invalid! Program terminating. . .");
			}
		}

	}

	public static void menuDoctor(UserList mainList, AppointmentList appointData, User currentUser,AppointmentList changeList1) throws IOException {
		Scanner input = new Scanner(System.in);
		Doctor tempUser = (Doctor) currentUser;
		int selector = -1;
		System.out.println("Welcome " + currentUser.getUserType() + currentUser.getFirstName());
		while (selector != 5) {
			System.out.println(
					"Please select what you want to do:\n1)Lookup Pending Appointment \n2)Lookup Appointment\n3)Change appointment\n4)Add new appointment\n5)Exit");
			selector = input.nextInt();
			switch (selector) {

			case 1:
				tempUser.lookupPendingRequest(appointData, mainList, "C:\\Users\\Donut\\Documents\\GitHub\\appoint1.txt", changeList1, "C:\\Users\\Donut\\Documents\\GitHub\\change1.txt");
				break;
			case 2:
				tempUser.lookUpAppointment(appointData, mainList);
				break;
			case 3:
				tempUser.moveAppointment(appointData, mainList, "C:\\Users\\Donut\\Documents\\GitHub\\appoint1.txt");
				break;
			case 4:
				tempUser.addNewAppointment(appointData, mainList, "C:\\Users\\Donut\\Documents\\GitHub\\appoint1.txt");
				break;
			case 5:
				System.out.println("Thankyou! Program terminating");
				break;
			default:
				System.out.println("Incorrect Input!");
			}

		}

	}

	public static void menuReception(UserList mainList, AppointmentList appointData, User currentUser,
			boolean firstTime) {
		Scanner input = new Scanner(System.in);
		Reception tempUser = (Reception) currentUser;
		int selector = -1;
		if (firstTime) {
			System.out.println("Welcome " + currentUser.getUserType() + currentUser.getFirstName());
		}

		System.out.println(
				"Please select what you want to do:\n1)Lookup Appointment\n2)Add new patient\n3)Add new Doctor\n4)Lookup Doctor's Appointment");
		selector = input.nextInt();
		if (selector == 1) {
			currentUser.lookUpAppointment(appointData, mainList);
			menuReception(mainList, appointData, currentUser, false);
		} else if (selector == 2) {
			tempUser.addNewPatient(appointData, mainList, "C:\\Users\\Donut\\Documents\\GitHub\\test1.txt");
			menuReception(mainList, appointData, currentUser, false);
		} else if (selector == 3) {
			tempUser.addNewDoctor(appointData, mainList, "C:\\Users\\Donut\\Documents\\GitHub\\test1.txt");
			menuReception(mainList, appointData, currentUser, false);
		} else if (selector == 4) {
			tempUser.lookupDoctor(appointData, mainList);
			menuReception(mainList, appointData, currentUser, false);

		}
	}

	public static void menuPatient(UserList mainList, AppointmentList appointData, User currentUser,
			AppointmentList changeList, boolean firstTime) throws IOException {
		Scanner input = new Scanner(System.in);
		int selector = -1;
		Patient tempUser = (Patient) currentUser;
		if (firstTime) {
			System.out.println("Welcome " + currentUser.getUserType() + currentUser.getFirstName());
		}

		System.out.println(
				"Please select what you want to do:\n1)Lookup Appointment\n2)Change upcoming appointment\n3)Exit");
		selector = input.nextInt();
		if (selector == 1) {
			tempUser.lookUpAppointment(appointData, mainList);
			menuPatient(mainList, appointData, currentUser, changeList, false);
		}
		if (selector == 2) {
			tempUser.requestAppointmentChange(appointData, mainList, changeList,
					"C:\\Users\\Donut\\Documents\\GitHub\\change1.txt");
			menuPatient(mainList, appointData, currentUser, changeList, false);

		}

		if (selector == 3) {

			System.out.println("Thankyou! Program terminating");
		}
	}

}
