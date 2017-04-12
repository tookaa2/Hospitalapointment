package UserPackage;
import java.util.*;

import AppointmentData.AppointmentList;

public class Reception extends User implements ILookUpAppointment{
	public Reception(String authority,int ID,String firstname,String lastname,long phone,String username, String password) {
		super(authority, ID,firstname,lastname,phone,username, password);

	}

public void lookUpAppointment(AppointmentList list1,UserList userListData){
	Scanner input=new Scanner(System.in);
		System.out.println("Please input the beginning date:/nDate:(example 31)");
		int beginday=input.nextInt();
		System.out.println("Month:(example 7)");
		int beginmonth=input.nextInt();
		System.out.println("Please input the ending date:/nDate:(example 31)");
		int endday=input.nextInt();
		System.out.println("Month:(example 7)");
		int endmonth=input.nextInt();
		
		list1.lookupAppointmentInterval(beginday, beginmonth, endday, endmonth, userListData);
	}
}
