import AppointmentData.AppointmentList;
public class Doctor extends User implements ILookUpAppointment{
	public Doctor(String authority,int ID,String firstname,String lastname,long phone,String username, String password) {
		super(authority, ID,firstname,lastname,phone,username, password);

	}
	public void lookUpAppointment(AppointmentList list1){
		int[] appointmentOfDoctor=list1.searchByDoctorID(this.getID());
		for(int i=0;i<appointmentOfDoctor.length;i++){
			
		}
	}
}
