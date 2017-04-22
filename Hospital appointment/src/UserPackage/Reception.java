package UserPackage;

import java.util.*;

import AppointmentData.AppointmentList;

public class Reception extends User implements ILookUpAppointment {
	public Reception(String authority, int ID, String firstname, String lastname, long phone, String username,
			String password) {
		super(authority, ID, firstname, lastname, phone, username, password);
	}

	public void lookUpAppointment(AppointmentList list1, UserList userListData) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the beginning date:/nDate:(example 31)");
		int beginday = input.nextInt();
		System.out.println("Month:(example 7)");
		int beginmonth = input.nextInt();
		System.out.println("Please input the ending date:/nDate:(example 31)");
		int endday = input.nextInt();
		System.out.println("Month:(example 7)");
		int endmonth = input.nextInt();

		list1.lookupAppointmentInterval(beginday, beginmonth, endday, endmonth, userListData);
	}

	public void addNewPatient(AppointmentList list1, UserList userListData, String userDatalocation) {
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		System.out.println("Please input the patient's infos:");
		System.out.println("Patient's username:");
		String username = input2.next();
		System.out.println("Password:");
		String password = input2.next();
		System.out.println("First Name:");
		String firstName = input2.next();
		System.out.println("Last Name:");
		String lastName = input2.next();
		System.out.println("Phone Number:");
		long phoneNumber = input2.nextLong();
		userListData.addUser(new Patient("P", firstName, lastName, phoneNumber, username, password));
		System.out.println(userListData.userlist1.get(userListData.userlist1.size() - 1).getInfoString());
		WriteProcess.writeTo(userListData.userlist1.get(userListData.userlist1.size() - 1).getInfoString(),
				userDatalocation, false);
	}
	public void addNewDoctor(AppointmentList list1, UserList userListData, String userDatalocation) {
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		System.out.println("Please input the Doctor's infos:");
		System.out.println("Doctor's username:");
		String username = input2.next();
		System.out.println("Password:");
		String password = input2.next();
		System.out.println("First Name:");
		String firstName = input2.next();
		System.out.println("Last Name:");
		String lastName = input2.next();
		System.out.println("Phone Number:");
		long phoneNumber = input2.nextLong();
		userListData.addUser(new Doctor("D", firstName, lastName, phoneNumber, username, password));
		System.out.println(userListData.userlist1.get(userListData.userlist1.size() - 1).getInfoString());
		WriteProcess.writeTo(userListData.userlist1.get(userListData.userlist1.size() - 1).getInfoString(),
				userDatalocation, false);
	}
	public void lookupDoctor(AppointmentList list1, UserList userListData){
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the doctor's ID:");
		int doctorID=input.nextInt();
		System.out.println("Please input the beginning date:/nDate:(example 31)");
		int beginDay = input.nextInt();
		System.out.println("Month:(example 7)");
		int beginMonth = input.nextInt();
		System.out.println("Please input the ending date:/nDate:(example 31)");
		int endDay = input.nextInt();
		System.out.println("Month:(example 7)");
		int endMonth = input.nextInt();
		list1.lookupDoctorAppointmentInterval(beginDay, beginMonth, endDay, endMonth, userListData, list1.searchByDoctorID(doctorID));
		
	}
	
}
