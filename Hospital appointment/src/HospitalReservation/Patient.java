package HospitalReservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Module.*;

public class Patient extends User implements ILookUpAppointment {
	protected static int counter = 0;

	public Patient(String authority, int ID, String firstname, String lastname, long phone, String username,
			String password) {
		super(authority, ID, firstname, lastname, phone, username, password);

		counter = ID;
		counter++;
	}

	public Patient(String authority, String firstname, String lastname, long phone, String username, String password) {
		this(authority, counter, firstname, lastname, phone, username, password);
		counter++;

	}

	public void lookUpAppointment(ArrayList<Appointment> list1, ArrayList<User> userListData) {
		Scanner input = new Scanner(System.in);
		Display.print("Your appointment:");
		int[] appointmentOfPatient = DatabaseOperationAppointmentList.searchByPatientID(this.getID(), list1);
		for (int i = 0; i < appointmentOfPatient.length; i++) {
			DatabaseOperationAppointmentList.printOutAppointment(i, userListData, list1);
		}

	}

	public void requestAppointmentChange(ArrayList<Appointment> list1, ArrayList<User> userListData, ArrayList<Appointment> changeList1,
			String changeListLocation) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Please specify the appointment ID:");
		int appointmentID = input.nextInt();
		
		Input.get
		
		if (list1.checkAccessPatient(appointmentID, this.getID())) {

			System.out.println("Access Granted!");
			int appointmentIndex = list1.searchByAppointmentID(appointmentID);

			Appointment temp = list1.getAppointmentByIndex(appointmentIndex);
			System.out.println(temp.getAppointmentID());
			System.out.println(temp.appointmentToString());
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
						newEndTime, temp.getDoctorID(), temp.getPatientID(), this.getID(), 0);
				if (list1.appointmentAlreadyExists(newOne)) {
					System.out.println("Sorry, this appointment already exist/time clash.");
				} else {
					changeList1.addAppointment(
							new Appointment(temp.getAppointmentID(), temp.getDate(), temp.getMonth(), temp.getYear(),
									newBeginTime, newEndTime, temp.getDoctorID(), this.getID(), this.getID(), 0));

					System.out.println("No time clash, appointment change request sent");
					changeList1.writeToDisk(changeListLocation);
					System.out.println(temp.appointmentToString());

				}

			} else {
				System.out.print("Please input the new begin time(eg 1600 for 16.00)");
				int newBeginTime = input.nextInt();
				System.out.print("Please input the new end time(eg 1700 for 17.00)");
				int newEndTime = input.nextInt();
				Appointment newOne = new Appointment(temp.getDate(), temp.getMonth(), temp.getYear(), newBeginTime,
						newEndTime, temp.getDoctorID(), this.getID(), this.getID(), 0);
				if (list1.appointmentAlreadyExists(newOne)) {
					System.out.println("Sorry, this appointment already exist/time clash.");
				} else {
					changeList1.addAppointment(
							new Appointment(temp.getAppointmentID(), temp.getDate(), temp.getMonth(), temp.getYear(),
									newBeginTime, newEndTime, temp.getDoctorID(), this.getID(), this.getID(), 0));
					System.out.println("No time clash, appointment change request sent.");
					System.out.println(changeList1.getAppointmentByIndex(0).appointmentToString());
				}

			}

		} else {
			System.out.println("Appointment doesnot exist / you have no permission to change this appointment.");
		}

	}
}
