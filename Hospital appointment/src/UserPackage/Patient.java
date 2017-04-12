package UserPackage;
import java.util.ArrayList;


public class Patient extends User implements ILookUpAppointment{
	protected ArrayList<Integer> AppointmentList = new ArrayList<Integer>();
	public Patient(String authority,int ID,String firstname,String lastname,long phone,String username, String password) {
		super(authority, ID,firstname,lastname,phone,username, password);

	}
	
	
}
