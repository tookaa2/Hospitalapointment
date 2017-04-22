package HospitalReservation;

import java.util.ArrayList;

public class Database {
	protected ArrayList<User> userList = new ArrayList<User>();
	protected ArrayList<Appointment> AppointmentList = new ArrayList<Appointment>();
	protected ArrayList<Appointment> ChangeAppointmentList = new ArrayList<Appointment>();
	protected String userTextFileLocation;
	protected String appointmentTextFileLocation;
	protected String changeRequestTextFileLocation;

	public Database() {

	}

	public Database(String userFilePath, String appointmentFilePath, String changeRequestFilePath) {
		this.userTextFileLocation = userFilePath;
		this.appointmentTextFileLocation = appointmentFilePath;
		this.changeRequestTextFileLocation = changeRequestFilePath;
		DatabaseOperationUserList.initializeUsers(ReadProcess.readFile(this.userTextFileLocation), this.userList);
		DatabaseOperationAppointmentList.initializeAppointments(ReadProcess.readFile(this.appointmentTextFileLocation),
				this.AppointmentList);
		DatabaseOperationAppointmentList.initializeAppointments(
				ReadProcess.readFile(this.changeRequestTextFileLocation), this.ChangeAppointmentList);
	}

}
