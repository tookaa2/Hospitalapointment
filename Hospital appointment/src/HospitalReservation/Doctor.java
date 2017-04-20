package HospitalReservation;

import AppointmentData.AppointmentList;
import AppointmentData.Appointment;

import java.io.IOException;
import java.util.*;

public class Doctor extends User implements ILookUpAppointment {
	protected static int counterD = 0;

	public Doctor(String authority, int ID, String firstname, String lastname, long phone, String username,
			String password) {
		super(authority, ID, firstname, lastname, phone, username, password);
		counterD = ID;
		counterD++;
	}

	public Doctor(String authority, String firstname, String lastname, long phone, String username, String password) {
		this(authority, counterD, firstname, lastname, phone, username, password);
		counterD++;

	}

	public void lookUpAppointment(AppointmentList list1, ArrayList<User> userListData) {
		Calendar today = Calendar.getInstance();
		int dateToday = today.get(Calendar.DATE);
		int month = today.get(Calendar.MONTH) + 1;
		int year = today.get(Calendar.YEAR) - 543;
		System.out.println("Today's day:" + dateToday + "," + month + "," + year);
		int[] appointmentOfDoctor = list1.searchByDoctorID(this.getID());
		for (int k = 0; k < 7; k++) {

			for (int i = 0; i < appointmentOfDoctor.length; i++) {

				if (list1.getAppointmentByIndex(appointmentOfDoctor[i]).getDate() == dateToday
						&& list1.getAppointmentByIndex(appointmentOfDoctor[i]).getMonth() == month
						&& list1.getAppointmentByIndex(appointmentOfDoctor[i]).getYear() == year) {
					list1.printOutAppointment(appointmentOfDoctor[i], userListData);
				}
			}
			today.add(Calendar.DATE, 1);
			dateToday = today.get(Calendar.DATE);
			month = today.get(Calendar.MONTH) + 1;
			year = today.get(Calendar.YEAR) - 543;
		}
	}

	public void lookupPendingRequest(AppointmentList list1, ArrayList<User> userListData, String appointmentLocation,
			AppointmentList changeList, String changeListLocation) throws IOException {
		Scanner input = new Scanner(System.in);
		int[] viewableList = changeList.searchByDoctorID(this.ID);
		for (int i = 0; i < viewableList.length; i++) {
			changeList.printOutAppointment(viewableList[i], userListData);
		}
		System.out.println("Please enter the appointment ID of the appointment request you would like to approve: ");
		int appointmentID = input.nextInt();
		System.out.println("Original appointment");
		list1.printOutAppointment(list1.searchByAppointmentID(appointmentID), userListData);
		System.out.println("Changed appointment");
		changeList.printOutAppointment(changeList.searchByAppointmentID(appointmentID), userListData);
		System.out.println("Confirm this change?(Y/N)");
		char confirm = input.next().charAt(0);
		if (confirm == 'y' || confirm == 'Y') {
			list1.changeAppointment(changeList.getAppointmentByIndex(changeList.searchByAppointmentID(appointmentID)),
					list1.searchByAppointmentID(appointmentID));
			System.out.println("Appointment updated sucessfully!");
			changeList.deleteAppointment(changeList.searchByAppointmentID(appointmentID));
			changeList.writeToDisk(changeListLocation);
			list1.writeToDisk(appointmentLocation);

		}
	}

	public void moveAppointment(AppointmentList list1, ArrayList<User> userListData, String appointmentLocation)
			throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Please specify the appointment ID:");
		int appointmentID = input.nextInt();
		if (list1.checkAccessDoctor(appointmentID, this.getID())) {

			System.out.println("Access Granted!");
			int appointmentIndex = list1.searchByAppointmentID(appointmentID);

			Appointment temp = list1.getAppointmentByIndex(appointmentIndex);
			System.out.println(temp.getAppointmentID());
			System.out.println("The current appointment: ");
			list1.printOutAppointment(appointmentIndex, userListData);
			System.out.println("Do you want to move the date? (Y/N)");
			char moveDate = input.next().charAt(0);
			if (moveDate == 'y') {
				System.out.print("Please input the new month(eg.7)");
				int newMonth = input.nextInt();
				System.out.print("Please input the new date(eg.10)");
				int newDate = input.nextInt();
				System.out.print("Please input the new begin time(eg 1600 for 16.00)");
				int newBeginTime = input.nextInt();
				System.out.print("Please input the new end time(eg 1700 for 17.00)");
				int newEndTime = input.nextInt();
				Appointment newOne = new Appointment(temp.getDate(), temp.getMonth(), temp.getYear(), newBeginTime,
						newEndTime, this.getID(), temp.getPatientID(), this.getID(), 1);
				if (list1.appointmentAlreadyExists(newOne)) {
					System.out.println("Sorry, this appointment already exist/time clash.");
				} else {
					list1.changeAppointment(newOne, appointmentIndex);

					System.out.println("No time clash, appointment updated.");
					list1.writeToDisk(appointmentLocation);
					System.out.println(temp.appointmentToString());

				}

			} else {
				////////////// HAVE ERROR!!!!
				System.out.print("Please input the new begin time(eg 1600 for 16.00)");
				int newBeginTime = input.nextInt();
				System.out.print("Please input the new end time(eg 1700 for 17.00)");
				int newEndTime = input.nextInt();
				Appointment newOne = new Appointment(temp.getDate(), temp.getMonth(), temp.getYear(), newBeginTime,
						newEndTime, this.getID(), temp.getPatientID(), this.getID(), 1);
				if (list1.appointmentAlreadyExists(newOne)) {
					System.out.println("Sorry, this appointment already exist/time clash.");
				} else {
					list1.changeAppointment(newOne, appointmentIndex);
					System.out.println("No time clash, appointment updated.");
					list1.writeToDisk(appointmentLocation);
					System.out.println(temp.appointmentToString());
				}

			}

		} else {
			System.out.println("Appointment doesnot exist / you have no permission to change this appointment.");
		}

	}

	public void addNewAppointment(AppointmentList list1, ArrayList<User> userListData, String appointmentLocation)
			throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the appointment date(eg 08): ");
		int appointmentDate = input.nextInt();
		System.out.println("Please enter the appointment month(eg 7): ");
		int appointmentMonth = input.nextInt();
		System.out.println("Please enter the appointment year(eg 2017): ");
		int appointmentYear = input.nextInt();
		System.out.println("Please enter the appointment begin time(eg 1600): ");
		int appointmentBeginTime = input.nextInt();
		System.out.println("Please enter the appointment end time(eg 1700): ");
		int appointmentEndTime = input.nextInt();
		System.out.println("Please enter the patient ID (eg 134): ");
		int appointmentPatientID = input.nextInt();
		Appointment tempAppointment = new Appointment(appointmentDate, appointmentMonth, appointmentYear,
				appointmentBeginTime, appointmentEndTime, this.getID(), appointmentPatientID, this.getID(), 1);
		if (userListData.verifyExist(appointmentPatientID, "P")) {
			System.out.println("Patient exist");
			if (list1.appointmentAlreadyExists(tempAppointment)) {
				System.out.println("Appointment time overlaps/already exist");
			} else {
				System.out.println("Appointment added successfully");
				list1.addAppointment(tempAppointment);
				list1.writeToDisk(appointmentLocation);
			}

		} else {
			System.out.println("Sorry patient doesn't exist!");
		}

	}
}
