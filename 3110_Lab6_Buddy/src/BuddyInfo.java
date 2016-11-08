

public class BuddyInfo {
	private String name, address;
	private int phoneNumber;
	private int age;
	
	public BuddyInfo() {
		
	}
	
	public BuddyInfo(String name, String address, int num) {
		if(name == null || address == null) {
			throw new NullPointerException("Input(s) cannot be done");
		}
		this.setName(name);
		this.setAddress(address);
		this.setPhoneNumber(num); 
	}
	
	public BuddyInfo(BuddyInfo buddy) {
		this(buddy.getName(), buddy.getAddress(), buddy.getPhoneNumber());
	}
	
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public String toString() {
		return name + "$" + getAddress() + "$" + getPhoneNumber();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isOver18() {
		return age >= 18;
	}
	
	public static BuddyInfo imporT(String str) {
		//BuddyInfo buddy = new BuddyInfo(); 
		String[] parts = str.split("\\$");

		return new BuddyInfo(parts[0], parts[1], Integer.parseInt(parts[2]));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world!");
		
		BuddyInfo buddy = new BuddyInfo("Kshamina", "111 address", 789333444);
		System.out.println(buddy.toString());
	}

	private void setAddress(String address) {
		this.address = address;
	}

	private void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}