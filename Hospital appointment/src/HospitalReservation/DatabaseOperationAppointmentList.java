package HospitalReservation;

import java.io.IOException;
import java.util.ArrayList;

import UserPackage.UserList;
import UserPackage.WriteProcess;

public class DatabaseOperationAppointmentList {
	public static void addAppointment(ArrayList<Appointment> AppointmentList, Appointment inputAppointment) {
		AppointmentList.add(inputAppointment);
	}

	public static void deleteAppointment(ArrayList<Appointment> AppointmentList, int index) {
		AppointmentList.remove(index);
	}

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

	public static boolean appointmentAlreadyExists(Appointment inputAppointment,
			ArrayList<Appointment> AppointmentList) {
		boolean temp = false;
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (inputAppointment.getAppointmentID() == AppointmentList.get(i).getAppointmentID()) {
				temp = true;
			}
			if (inputAppointment.getDate() == AppointmentList.get(i).getDate()
					&& inputAppointment.getMonth() == AppointmentList.get(i).getMonth()
					&& inputAppointment.getYear() == AppointmentList.get(i).getYear()
					&& timeClash(inputAppointment.getTimeBegin(), inputAppointment.getTimeEnd(),
							AppointmentList.get(i).getTimeBegin(), AppointmentList.get(i).getTimeEnd())) {
				temp = true;
			}
		}

		return temp;
	}

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

	public static boolean checkAccessPatient(int inputAppointment, int userID,ArrayList<Appointment> AppointmentList) {
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

	

	public static Appointment getAppointmentByIndex(int inputIndex,ArrayList<Appointment> AppointmentList) {
		return AppointmentList.get(inputIndex);
	}

	public static void changeAppointment(Appointment editedAppointment, int index,ArrayList<Appointment> AppointmentList) {
		AppointmentList.get(index).setDate(editedAppointment.getDate());
		AppointmentList.get(index).setMonth(editedAppointment.getMonth());
		AppointmentList.get(index).setTimeBegin(editedAppointment.getTimeBegin());
		AppointmentList.get(index).setTimeEnd(editedAppointment.getTimeEnd());
		AppointmentList.get(index).setEditbyID(editedAppointment.getEditbyID());
	}

	public static int[] searchByDoctorID(int inputID,ArrayList<Appointment> AppointmentList) {
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

	public static int searchByAppointmentID(int inputID,ArrayList<Appointment> AppointmentList) {
		int temp = -1;
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (AppointmentList.get(i).getAppointmentID() == inputID) {
				temp = i;
				break;
			}
		}

		return temp;
	}

	public static int[] searchByPatientID(int inputID,ArrayList<Appointment> AppointmentList) {
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

	public static void printOutAppointment(int appointmentIndex, UserList userListData,ArrayList<Appointment> AppointmentList) {
		double begin = AppointmentList.get(appointmentIndex).getTimeBegin();
		System.out.println("-----------------------------------------------------");
		System.out.println("Appointment ID: " + AppointmentList.get(appointmentIndex).getAppointmentID() + "\nMonth:"
				+ AppointmentList.get(appointmentIndex).getMonthName() + "\nDate: "
				+ AppointmentList.get(appointmentIndex).date + "\nTime: "
				+ AppointmentList.get(appointmentIndex).getTimeBegin() + "-"
				+ AppointmentList.get(appointmentIndex).getTimeEnd() + "\nDoctor incharge:Dr."
				+ userListData.getUserObject(AppointmentList.get(appointmentIndex).getDoctorID()).getFirstName()
				+ "\nPatient:"
				+ userListData.getUserObject(AppointmentList.get(appointmentIndex).getPatientID()).getFirstName());
		System.out.println("-----------------------------------------------------");

	}

	public static void writeToDisk(String location,ArrayList<Appointment> AppointmentList) throws IOException {
		WriteProcess.clearFile(location);
		for (int i = 0; i < AppointmentList.size(); i++) {
			if (i == 0) {
				WriteProcess.writeTo(AppointmentList.get(i).appointmentToString(), location, true);
			} else {
				WriteProcess.writeTo(AppointmentList.get(i).appointmentToString(), location, false);
			}
		}
	}

	public static void lookupAppointmentInterval(int beginDay, int beginMonth, int endDay, int endMonth,
			UserList userListData,ArrayList<Appointment> AppointmentList) {
		if (endDay < beginDay && endMonth > beginMonth || endDay > beginDay && endMonth > beginMonth) {

			for (int i = beginDay; i <= 31; i++) {
				for (int k = 0; k < AppointmentList.size(); k++) {
					if (AppointmentList.get(k).getMonth() == beginMonth && AppointmentList.get(k).getDate() == i
							&& AppointmentList.get(k).approved) {
						printOutAppointment(k, userListData,AppointmentList);
					}
				}
			}

			for (int i = 1; i <= endDay; i++) {
				for (int k = 0; k < AppointmentList.size(); k++) {
					if (AppointmentList.get(k).getMonth() == endMonth && AppointmentList.get(k).getDate() == i
							&& AppointmentList.get(k).approved) {
						printOutAppointment(k, userListData,AppointmentList);
					}
				}
			}

		} else if (endDay >= beginDay && endMonth == beginMonth) {
			for (int i = beginDay; i <= endDay; i++) {
				for (int k = 0; k < AppointmentList.size(); k++) {
					if (AppointmentList.get(k).getMonth() == beginMonth && AppointmentList.get(k).getDate() == i
							&& AppointmentList.get(k).approved) {
						printOutAppointment(k, userListData,AppointmentList);
					}
				}
			}
		}

	}

	public static void lookupDoctorAppointmentInterval(int beginDay, int beginMonth, int endDay, int endMonth,
			UserList userListData, int[] doctorList) {
		ArrayList<Appointment> templist = new ArrayList<Appointment>();
		for (int i = 0; i < doctorList.length; i++) {
			templist.add(AppointmentList1.get(doctorList[i]));
		}

		if (endDay < beginDay && endMonth > beginMonth || endDay > beginDay && endMonth > beginMonth) {

			for (int i = beginDay; i <= 31; i++) {
				for (int k = 0; k < templist.size(); k++) {
					if (templist.get(k).getMonth() == beginMonth && templist.get(k).getDate() == i) {
						printOutAppointment(k, userListData);
					}
				}
			}

			for (int i = 1; i <= endDay; i++) {
				for (int k = 0; k < templist.size(); k++) {
					if (templist.get(k).getMonth() == endMonth && templist.get(k).getDate() == i) {
						printOutAppointment(k, userListData);
					}
				}
			}

		} else if (endDay >= beginDay && endMonth == beginMonth) {
			for (int i = beginDay; i <= endDay; i++) {
				for (int k = 0; k < templist.size(); k++) {
					if (templist.get(k).getMonth() == beginMonth && templist.get(k).getDate() == i) {
						printOutAppointment(k, userListData);
					}
				}
			}
		}

	}
}
