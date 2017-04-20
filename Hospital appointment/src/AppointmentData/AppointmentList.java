package AppointmentData;

import UserPackage.UserList;
import UserPackage.WriteProcess;

import java.io.IOException;
import java.util.ArrayList;

public class AppointmentList {
	protected ArrayList<Appointment> AppointmentList1 = new ArrayList<Appointment>();

	public void addAppointment(Appointment inputAppointment) {
		AppointmentList1.add(inputAppointment);
	}

	public static void initializeAppointments(String[] appointmentList, AppointmentList mainList) {
		for (int i = 0; i < appointmentList.length; i += 10) {
			mainList.addAppointment(
					new Appointment(Integer.parseInt(appointmentList[i]), Integer.parseInt(appointmentList[i + 1]),
							Integer.parseInt(appointmentList[i + 2]), Integer.parseInt(appointmentList[i + 3]),
							Integer.parseInt(appointmentList[i + 4]), Integer.parseInt(appointmentList[i + 5]),
							Integer.parseInt(appointmentList[i + 6]), Integer.parseInt(appointmentList[i + 7]),
							Integer.parseInt(appointmentList[i + 8]), Integer.parseInt(appointmentList[i + 9])));
		}
	}
	
	public void deleteAppointment(int index){
		AppointmentList1.remove(index);
	}

	public boolean appointmentAlreadyExists(Appointment inputAppointment) {
		boolean temp = false;
		for (int i = 0; i < AppointmentList1.size(); i++) {
			if (inputAppointment.getAppointmentID() == AppointmentList1.get(i).getAppointmentID()) {
				temp = true;
			}
			if (inputAppointment.getDate() == AppointmentList1.get(i).getDate()
					&& inputAppointment.getMonth() == AppointmentList1.get(i).getMonth()
					&& inputAppointment.getYear() == AppointmentList1.get(i).getYear()
					&& timeClash(inputAppointment.getTimeBegin(), inputAppointment.getTimeEnd(),
							AppointmentList1.get(i).getTimeBegin(), AppointmentList1.get(i).getTimeEnd())) {
				temp = true;
			}
		}

		return temp;
	}

	public boolean checkAccessDoctor(int inputAppointment, int userID) {
		boolean temp = false;
		for (int i = 0; i < AppointmentList1.size(); i++) {
			if (inputAppointment == AppointmentList1.get(i).getAppointmentID()) {
				temp = true;
				if (AppointmentList1.get(i).getDoctorID() == userID) {
					temp = true;
				} else {
					temp = false;
				}
			}

		}

		return temp;
	}
	public boolean checkAccessPatient(int inputAppointment, int userID) {
		boolean temp = false;
		for (int i = 0; i < AppointmentList1.size(); i++) {
			if (inputAppointment == AppointmentList1.get(i).getAppointmentID()) {
				temp = true;
				if (AppointmentList1.get(i).getPatientID() == userID) {
					temp = true;
				} else {
					temp = false;
				}
			}

		}

		return temp;
	}

	public boolean timeClash(int AbeginTime, int AendTime, int BbeginTime, int BendTime) {
		boolean temp = false;
		for (int i = AbeginTime; i <= AendTime; i++) {
			for (int k = BbeginTime; k <= BendTime; k++) {
				if (k == i) {
					temp = true;
				}
			}
		}
		return temp;
	}

	public Appointment getAppointmentByIndex(int inputIndex) {
		return AppointmentList1.get(inputIndex);
	}

	public void changeAppointment(Appointment editedAppointment, int index) {
		AppointmentList1.get(index).setDate(editedAppointment.getDate());
		AppointmentList1.get(index).setMonth(editedAppointment.getMonth());
		AppointmentList1.get(index).setTimeBegin(editedAppointment.getTimeBegin());
		AppointmentList1.get(index).setTimeEnd(editedAppointment.getTimeEnd());
		AppointmentList1.get(index).setEditbyID(editedAppointment.getEditbyID());
	}

	public int[] searchByDoctorID(int inputID) {
		ArrayList<Integer> AppointmentLists = new ArrayList<Integer>();
		int[] temp = null;
		for (int i = 0; i < AppointmentList1.size(); i++) {
			if (AppointmentList1.get(i).getDoctorID() == inputID) {
				AppointmentLists.add(i);
			}
		}
		temp = new int[AppointmentLists.size()];
		for (int i = 0; i < AppointmentLists.size(); i++) {
			temp[i] = AppointmentLists.get(i);
		}
		return temp;
	}

	public int searchByAppointmentID(int inputID) {
		int temp = -1;
		for (int i = 0; i < AppointmentList1.size(); i++) {
			if (AppointmentList1.get(i).getAppointmentID() == inputID) {
				temp = i;
				break;
			}
		}

		return temp;
	}

	public int[] searchByPatientID(int inputID) {
		ArrayList<Integer> AppointmentLists = new ArrayList<Integer>();
		int temp[] = null;
		for (int i = 0; i < AppointmentList1.size(); i++) {
			if (AppointmentList1.get(i).getPatientID() == inputID) {
				AppointmentLists.add(i);
			}

		}
		temp = new int[AppointmentLists.size()];
		for (int i = 0; i < AppointmentLists.size(); i++) {
			temp[i] = AppointmentLists.get(i);
		}

		return temp;
	}

	public void printOutAppointment(int appointmentIndex, UserList userListData) {
		double begin = AppointmentList1.get(appointmentIndex).getTimeBegin();
		System.out.println("-----------------------------------------------------");
		System.out.println("Appointment ID: " + AppointmentList1.get(appointmentIndex).getAppointmentID() + "\nMonth:"
				+ AppointmentList1.get(appointmentIndex).getMonthName() + "\nDate: "
				+ AppointmentList1.get(appointmentIndex).date + "\nTime: "
				+ AppointmentList1.get(appointmentIndex).getTimeBegin() + "-"
				+ AppointmentList1.get(appointmentIndex).getTimeEnd() + "\nDoctor incharge:Dr."
				+ userListData.getUserObject(AppointmentList1.get(appointmentIndex).getDoctorID()).getFirstName()
				+ "\nPatient:"
				+ userListData.getUserObject(AppointmentList1.get(appointmentIndex).getPatientID()).getFirstName());
		System.out.println("-----------------------------------------------------");

	}

	public void writeToDisk(String location) throws IOException {
		WriteProcess.clearFile(location);
		for (int i = 0; i < AppointmentList1.size(); i++) {
			if (i == 0) {
				WriteProcess.writeTo(AppointmentList1.get(i).appointmentToString(), location, true);
			} else {
				WriteProcess.writeTo(AppointmentList1.get(i).appointmentToString(), location, false);
			}
		}
	}

	public void lookupAppointmentInterval(int beginDay, int beginMonth, int endDay, int endMonth,
			UserList userListData) {
		if (endDay < beginDay && endMonth > beginMonth || endDay > beginDay && endMonth > beginMonth) {

			for (int i = beginDay; i <= 31; i++) {
				for (int k = 0; k < AppointmentList1.size(); k++) {
					if (AppointmentList1.get(k).getMonth() == beginMonth && AppointmentList1.get(k).getDate() == i&&AppointmentList1.get(k).approved) {
						printOutAppointment(k, userListData);
					}
				}
			}

			for (int i = 1; i <= endDay; i++) {
				for (int k = 0; k < AppointmentList1.size(); k++) {
					if (AppointmentList1.get(k).getMonth() == endMonth && AppointmentList1.get(k).getDate() == i&&AppointmentList1.get(k).approved) {
						printOutAppointment(k, userListData);
					}
				}
			}

		} else if (endDay >= beginDay && endMonth == beginMonth) {
			for (int i = beginDay; i <= endDay; i++) {
				for (int k = 0; k < AppointmentList1.size(); k++) {
					if (AppointmentList1.get(k).getMonth() == beginMonth && AppointmentList1.get(k).getDate() == i&&AppointmentList1.get(k).approved) {
						printOutAppointment(k, userListData);
					}
				}
			}
		}

	}
	
	public void lookupDoctorAppointmentInterval(int beginDay, int beginMonth, int endDay, int endMonth,
			UserList userListData,int[] doctorList) {
		ArrayList<Appointment> templist = new ArrayList<Appointment>();
		for(int i=0;i<doctorList.length;i++){
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
