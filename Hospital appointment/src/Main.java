import java.util.*;
public class Main {
public static void main(String args[]){
	String[] userList;
	UserList mainList= new UserList();
	userList=ReadProcess.readFile();
	UserAccessOperations.initializeUsers(userList, mainList);
	for(int i=1;i<userList.length;i+=2){
		System.out.println(userList[i]);
	}
	Main(mainList);
}
public static void Main(UserList mainList){
	Scanner input=new Scanner(System.in);
	
	System.out.println("CAMT Hospital reservation center. \nPlease login to continue \nUsername:");
	String username=input.next();
	System.out.println("Password:");
	String password=input.next();
	if(mainList.verifyExist(username)){
		if(mainList.verifyPassword(username, password)){
			
		}
	}
	
}
}
