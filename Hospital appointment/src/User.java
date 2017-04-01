
public class User {
	protected String username;
	protected String password;
	protected int authority;

	public User(int authority,String username, String password) {
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
