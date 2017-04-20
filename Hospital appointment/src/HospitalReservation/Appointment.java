package HospitalReservation;

public class Appointment {

	protected Date dateData;

	// protected int date;
	// protected int month;
	// protected int year;

	protected Time beginTime;
	protected Time endTime;

	// protected int timeBegin;
	// protected int timeEnd;

	protected int AppointmentID;
	protected int doctorID;
	protected int patientID;
	protected int editbyID;
	protected boolean approved;
	protected static int IDcounter = 0;

	public Appointment(int ID, int date, int month, int year, int timeBegin, int timeEnd, int doctorID, int patientID,
			int editbyID, int approve) {
		this.AppointmentID = ID;
		this.dateData.setDDMMYYYY(date, month, year);

		// this.date = date;
		// this.month = month;
		// this.year = year;

		this.beginTime.hour = (Integer.parseInt(Integer.toString(timeBegin).substring(0, 2)));
		this.beginTime.minute = (Integer.parseInt(Integer.toString(timeBegin).substring(3, 4)));
		this.endTime.hour = (Integer.parseInt(Integer.toString(timeEnd).substring(0, 2)));
		this.endTime.minute = (Integer.parseInt(Integer.toString(timeEnd).substring(3, 4)));
		// this.timeBegin = timeBegin;
		// this.timeEnd = timeEnd;
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

	public Appointment(int ID, Date dayObject, Time beginTime, TIme endTime, int doctorID, int patientID, int editbyID,
			int approve) {
		this.AppointmentID = ID;
		this.dateData = dayObject;
		// this.date = date;
		// this.month = month;
		// this.year = year;
		this.beginTime = beginTime;
		this.endTime.minute = endTime;
		// this.timeBegin = timeBegin;
		// this.timeEnd = timeEnd;
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
		// this.date = date;
		// this.month = month;
		// this.year = year;
		// this.timeBegin = timeBegin;
		// this.timeEnd = timeEnd;
		this.dateData.setDDMMYYYY(date, month, year);
		this.beginTime.hour = (Integer.parseInt(Integer.toString(timeBegin).substring(0, 2)));
		this.beginTime.minute = (Integer.parseInt(Integer.toString(timeBegin).substring(3, 4)));
		this.endTime.hour = (Integer.parseInt(Integer.toString(timeEnd).substring(0, 2)));
		this.endTime.minute = (Integer.parseInt(Integer.toString(timeEnd).substring(3, 4)));

		this.doctorID = doctorID;
		this.patientID = patientID;
		this.editbyID = editbyID;
		if (approve == 1) {
			this.approved = true;
		} else {
			this.approved = false;
		}

	}

	public Date getDateData() {
		return dateData;
	}

	public void setDateData(Date dateData) {
		this.dateData = dateData;
	}

	public Time getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Appointment(Appointment inputAppointmentObject) {
		this.AppointmentID = inputAppointmentObject.getAppointmentID();
		this.dateData.setDDMMYYYY(inputAppointmentObject.getDateData().getDate(),
				inputAppointmentObject.getDateData().getMonth(), inputAppointmentObject.getDateData().getYear());
		this.beginTime = inputAppointmentObject.getBeginTime();
		this.endTime = inputAppointmentObject.getEndTime();

		// this.date = inputAppointmentObject.getDate();
		// this.month = inputAppointmentObject.getMonth();
		// this.year = inputAppointmentObject.getYear();
		// this.timeBegin = inputAppointmentObject.getTimeBegin();
		// this.timeEnd = inputAppointmentObject.getTimeEnd();
		IDcounter++;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public void setEditbyID(int editbyID) {
		this.editbyID = editbyID;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public static void setIDcounter(int iDcounter) {
		IDcounter = iDcounter;
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

	public boolean getApproved() {
		return approved;
	}

	public static int getIDcounter() {
		return IDcounter;
	}

	public int getAppointmentID() {
		return AppointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		AppointmentID = appointmentID;
	}

	public String appointmentToString() {
		int temp = 0;
		if (this.getApproved()) {
			temp = 1;
		}
		String timeBegin = "" + this.getBeginTime().getTime();
		if (this.getBeginTime().getTime() < 1000) {
			timeBegin = "0" + this.getBeginTime().getTime();
		}

		String timeEnd = "" + this.getEndTime().getTime();
		if (this.getEndTime().getTime() < 1000) {
			timeEnd = "0" + this.getEndTime().getTime();
		}

		return this.getAppointmentID() + "," + this.getDateData().getDate() + "," + this.getDateData().getMonth() + ","
				+ this.getDateData().getYear() + "," + timeBegin + "," + timeEnd + "," + this.getDoctorID() + ","
				+ this.getPatientID() + "," + this.getEditbyID() + "," + temp;
	}

}
