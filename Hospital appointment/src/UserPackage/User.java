package UserPackage;

import AppointmentData.AppointmentList;

public class User implements ILookUpAppointment{
	protected final String username;
	protected String password;
	protected String authority;
	protected int ID;
	protected long phoneNumber;
	protected final String firstName;
	protected final String lastName;
	public User(String authority,int ID,String firstname,String lastname,long phone,String username, String password) {
		this.username = username;
		this.password = password;
		this.authority = authority;
		this.firstName=firstname;
		this.lastName=lastname;
		this.phoneNumber=phone;
		this.ID=ID;

	}
	
	
	public void lookUpAppointment(AppointmentList list1,UserList userListData){
		System.out.println("User cannot lookup");
	};

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getID() {
		return ID;
	}

	public String getAuthority() {
		return authority;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public String getUserType(){
		String temp=null;
		if (this.authority.equals("D")) {
			temp="Dr.";
		} else if (this.authority.equals("R")) {
			temp="Reception ";
		} else if (this.authority.equals("P")){
			temp="Patient ";
		}
		return temp;
	}
	public String getInfoString(){
		return this.authority+","+this.ID+","+this.firstName+","+this.lastName+","+this.phoneNumber+","+this.username+","+this.password;
	}

}
