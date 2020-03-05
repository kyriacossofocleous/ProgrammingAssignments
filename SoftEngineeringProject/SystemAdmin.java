public class SystemAdmin extends Staff{
	/*
	 * this class allows the user to add/remove/edit Staff members. It uses the singleton pattern since there is only one
	 */
	private String name;
	private int ID;
	private transient String password;
	private static SystemAdmin sysadmin = null;
	public SystemAdmin() {
		super();
		name="Morgan Freeman";
		ID=999;
		password="bigboss";
		title="System Admin";
		
	}
	
	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}
	
	public String getPassword() {
		return password;
	}

	public static SystemAdmin getInstance() {
		if (sysadmin == null) {
			sysadmin = new SystemAdmin();
		}
		return sysadmin;
	}
}