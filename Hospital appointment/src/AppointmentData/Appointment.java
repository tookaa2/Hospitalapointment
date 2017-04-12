package AppointmentData;

public class Appointment {
	protected int date;
	protected int month;
	protected int year;
	protected int timeBegin;
	protected int timeEnd;
	protected int AppointmentID;
	protected int doctorID;
	protected int patientID;
	protected int editbyID;
	protected boolean approved;
	protected static int IDcounter = 0;

	public Appointment(int ID, int date, int month, int year, int timeBegin, int timeEnd, int doctorID, int patientID,
			int editbyID, int approve) {
		this.AppointmentID = ID;
		this.date = date;
		this.month = month;
		this.year = year;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.editbyID = editbyID;
		if (approve == 1) {
			this.approved = true;
		} else {
			this.approved = false;
		}
		IDcounter = AppointmentID;
	}

	public Appointment(int date, int month, int year, int timeBegin, int timeEnd, int doctorID, int patientID,
			int editbyID, int approve) {
		IDcounter++;
		this.AppointmentID = IDcounter + 0;
		this.date = date;
		this.month = month;
		this.year = year;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.editbyID = editbyID;
		if (approve == 1) {
			this.approved = true;
		} else {
			this.approved = false;
		}

	}

	public Appointment(Appointment inputAppointmentObject) {
		this.AppointmentID = inputAppointmentObject.getAppointmentID();
		this.date = inputAppointmentObject.getDate();
		this.month = inputAppointmentObject.getMonth();
		this.year = inputAppointmentObject.getYear();
		this.timeBegin = inputAppointmentObject.getTimeBegin();
		this.timeEnd = inputAppointmentObject.getTimeEnd();
		IDcounter++;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public int getPatientID() {
		return patientID;
	}

	public int getEditbyID() {
		return editbyID;
	}

	public boolean isApproved() {
		return approved;
	}

	public static int getIDcounter() {
		return IDcounter;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(int timeBegin) {
		this.timeBegin = timeBegin;
	}

	public int getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(int timeEnd) {
		this.timeEnd = timeEnd;
	}

	public int getAppointmentID() {
		return AppointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		AppointmentID = appointmentID;
	}

}
