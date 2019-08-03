
public class Account {
	private String Username;
	private String Password;
	private int Level;
	
	public Account() {
		
	}
	
	public Account(String username, String password, int level) {
		this.Username = username;
		this.Password = password;
		this.Level = level;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}

	@Override
	public String toString() {
		return Username + "," + Password + "," + Level;
	}
}
