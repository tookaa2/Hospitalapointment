package HospitalReservation;

import java.io.IOException;
import java.util.ArrayList;

import Module.Display;

public class DatabaseOperationAppointmentList {
	
	public static void addAppointment(ArrayList<Appointment> AppointmentList, Appointment inputAppointment) {
		AppointmentList.add(inputAppointment);
	}

	public static void deleteAppointment(ArrayList<Appointment> AppointmentList, int index) {
		AppointmentList.remove(index);
	}
	///Add appointments to Appointment Arraylist from the input String[], this is for when program starts
	public static void initializeAppointments(String[] appointmentStringArray, ArrayList<Appointment> mainList) {
		for (int i = 0; i < appointmentStringArray.length; i += 10) {
			addAppointment(mainList, new Appointment(Integer.parseInt(appointmentStringArray[i]),
					Integer.parseInt(appointmentStringArray[i + 1]), Integer.parseInt(appointmentStringArray[i + 2]),
					Integer.parseInt(appointmentStringArray[i + 3]), Integer.parseInt(appointmentStringArray[i + 4]),
					Integer.parseInt(appointmentStringArray[i + 5]), Integer.parseInt(appointmentStringArray[i + 6]),
					Integer.parseInt(appointmentStringArray[i + 7]), Integer.parseInt(appointmentStringArray[i + 8]),
					Integer.parseInt(appointmentStringArray[i + 9])));
		}
	}
	///Checks if this appointment already exist to avoid a time clashing/duplicating appointments
	public static boolean appointmentAlreadyExists(Appointment inputAppointment,
			ArrayList<Appointment> AppointmentList) {
		boolean temp = false;
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (inputAppointment.getAppointmentID() == AppointmentList.get(i).getAppointmentID()) {
				temp = true;
			}
			if (inputAppointment.getDateData() == AppointmentList.get(i).getDateData()

					&& Time.timeClash(inputAppointment.getBeginTime().getTime(),
							inputAppointment.getEndTime().getTime(), AppointmentList.get(i).getBeginTime().getTime(),
							AppointmentList.get(i).getEndTime().getTime())) {
				temp = true;
			}
		}

		return temp;
	}
	///Checks if the Doctor ID that was inputted matches the Appointment index to grant access for further operations
	public static boolean checkAccessDoctor(int inputAppointment, int userID, ArrayList<Appointment> AppointmentList) {
		boolean temp = false;
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (inputAppointment == AppointmentList.get(i).getAppointmentID()) {
				temp = true;
				if (AppointmentList.get(i).getDoctorID() == userID) {
					temp = true;
				} else {
					temp = false;
				}
			}

		}

		return temp;
	}
	///Checks if the patient ID that was inputted matches the Appointment index to grant access for further operations
	public static boolean checkAccessPatient(int inputAppointment, int userID, ArrayList<Appointment> AppointmentList) {
		boolean temp = false;
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (inputAppointment == AppointmentList.get(i).getAppointmentID()) {
				temp = true;
				if (AppointmentList.get(i).getPatientID() == userID) {
					temp = true;
				} else {
					temp = false;
				}
			}

		}

		return temp;
	}
	///Get appointment object from the input index integer
	public static Appointment getAppointmentByIndex(int inputIndex, ArrayList<Appointment> AppointmentList) {
		return AppointmentList.get(inputIndex);
	}
	///Make amendments to the appoinment(Approved by doctor)
	public static void changeAppointment(Appointment editedAppointment, int index,
			ArrayList<Appointment> AppointmentList) {

		AppointmentList.get(index).setBeginTime(editedAppointment.getBeginTime());
		AppointmentList.get(index).setDateData(editedAppointment.getDateData());
		AppointmentList.get(index).setEditbyID(editedAppointment.getEditbyID());
		AppointmentList.get(index).setEndTime(editedAppointment.getEndTime());

	}
	///Search and get appointment indexes that contains the input doctor ID
	public static int[] searchByDoctorID(int inputID, ArrayList<Appointment> AppointmentList) {
		ArrayList<Integer> AppointmentLists = new ArrayList<Integer>();
		int[] temp = null;
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (AppointmentList.get(i).getDoctorID() == inputID) {
				AppointmentLists.add(i);
			}
		}
		temp = new int[AppointmentLists.size()];
		for (int i = 0; i < AppointmentLists.size(); i++) {
			temp[i] = AppointmentLists.get(i);
		}
		return temp;
	}
	///Get appointment index from the input appointment ID
	public static int searchByAppointmentID(int inputID, ArrayList<Appointment> AppointmentList) {
		int temp = -1;
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (AppointmentList.get(i).getAppointmentID() == inputID) {
				temp = i;
				break;
			}
		}

		return temp;
	}
	///Get list of appointments indexes related to the input patient ID
	public static int[] searchByPatientID(int inputID, ArrayList<Appointment> AppointmentList) {
		ArrayList<Integer> temporaryList = new ArrayList<Integer>();
		int tempIntHolder[] = null;
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (AppointmentList.get(i).getPatientID() == inputID) {
				temporaryList.add(i);
			}

		}
		tempIntHolder = new int[temporaryList.size()];
		for (int i = 0; i < temporaryList.size(); i++) {
			tempIntHolder[i] = temporaryList.get(i);
		}

		return tempIntHolder;
	}
	///Prints out the appointment individually
	public static void printOutAppointment(int appointmentIndex, ArrayList<User> userListData,
			ArrayList<Appointment> AppointmentList) {

		Display.print("-----------------------------------------------------");
		Display.print("Appointment ID: " + AppointmentList.get(appointmentIndex).getAppointmentID() + "\nMonth:"
				+ AppointmentList.get(appointmentIndex).getDateData().getMonthName() + "\nDate: "
				+ AppointmentList.get(appointmentIndex).getDateData().getDate() + "\nTime: "
				+ AppointmentList.get(appointmentIndex).getBeginTime().displayTime() + "-"
				+ AppointmentList.get(appointmentIndex).getEndTime().displayTime() + "\nDoctor incharge:Dr."
				+ DatabaseOperationUserList
						.getUserObject(userListData, AppointmentList.get(appointmentIndex).getDoctorID()).getFirstName()
				+ "\nPatient:"
				+ DatabaseOperationUserList
						.getUserObject(userListData, AppointmentList.get(appointmentIndex).getPatientID())
						.getFirstName());
		Display.print("-----------------------------------------------------");

	}
	///Write all the appointments to the file input location
	public static void writeToDisk(String location, ArrayList<Appointment> AppointmentList) throws IOException {
		WriteProcess.clearFile(location);
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (i == 0) {
				WriteProcess.writeTo(AppointmentList.get(i).appointmentToString(), location, true);
			} else {
				WriteProcess.writeTo(AppointmentList.get(i).appointmentToString(), location, false);
			}
		}
	}
	///Look up appointments in specify time interval
	public static void lookupAppointmentInterval(int beginDay, int beginMonth, int endDay, int endMonth,
			ArrayList<User> userListData, ArrayList<Appointment> AppointmentList) {
		if (endDay < beginDay && endMonth > beginMonth || endDay > beginDay && endMonth > beginMonth) {

			for (int i = beginDay; i <= 31; i++) {
				for (int k = 0; k < AppointmentList.size(); k++) {
					if (AppointmentList.get(k).getDateData().getMonth() == beginMonth
							&& AppointmentList.get(k).getDateData().getDate() == i && AppointmentList.get(k).approved) {
						printOutAppointment(k, userListData, AppointmentList);
					}
				}
			}

			for (int i = 1; i <= endDay; i++) {
				for (int k = 0; k < AppointmentList.size(); k++) {
					if (AppointmentList.get(k).getDateData().getMonth() == endMonth
							&& AppointmentList.get(k).getDateData().getDate() == i && AppointmentList.get(k).approved) {
						printOutAppointment(k, userListData, AppointmentList);
					}
				}
			}

		} else if (endDay >= beginDay && endMonth == beginMonth) {
			for (int i = beginDay; i <= endDay; i++) {
				for (int k = 0; k < AppointmentList.size(); k++) {
					if (AppointmentList.get(k).getDateData().getMonth() == beginMonth
							&& AppointmentList.get(k).getDateData().getDate() == i && AppointmentList.get(k).approved) {
						printOutAppointment(k, userListData, AppointmentList);
					}
				}
			}
		}

	}
	///Look up appointment of specific doctor within the input time interval
	public static void lookupDoctorAppointmentInterval(int beginDay, int beginMonth, int endDay, int endMonth,
			ArrayList<User> userListData, int[] doctorList,ArrayList<Appointment> AppointmentList) {
		
		ArrayList<Appointment> templist = new ArrayList<Appointment>();
		for (int i = 0; i < doctorList.length; i++) {
			templist.add(AppointmentList.get(doctorList[i]));
		}
		
		

		if (endDay < beginDay && endMonth > beginMonth || endDay > beginDay && endMonth > beginMonth) {

			for (int i = beginDay; i <= 31; i++) {
				for (int k = 0; k < templist.size(); k++) {
					if (templist.get(k).getDateData().getMonth() == beginMonth && templist.get(k).getDateData().getDate() == i) {
						
						printOutAppointment(k, userListData,AppointmentList);
					}
				}
			}

			for (int i = 1; i <= endDay; i++) {
				for (int k = 0; k < templist.size(); k++) {
					if (templist.get(k).getDateData().getMonth() == endMonth && templist.get(k).getDateData().getDate() == i) {
						printOutAppointment(k, userListData,AppointmentList);
					}
				}
			}

		} else if (endDay >= beginDay && endMonth == beginMonth) {
			for (int i = beginDay; i <= endDay; i++) {
				for (int k = 0; k < templist.size(); k++) {
					if (templist.get(k).getDateData().getMonth() == beginMonth && templist.get(k).getDateData().getDate()== i) {
						printOutAppointment(k, userListData,AppointmentList);
					}
				}
			}
		}

	}
}
