
public class User {
	protected String username;
	protected String password;
	protected int authority;

	public User(String username, String password, int authority) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getAuthority() {
		return authority;
	}

}
