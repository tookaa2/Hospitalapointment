
public class User {
	private String username;
	private String password;
	private int authority;

	public User(int authority,String username, String password) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public int getAuthority() {
		return this.authority;
	}

}
