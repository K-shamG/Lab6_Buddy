
public class BuddyInfo {
	private String name, address;
	private int phoneNumber;
	private int age;
	
	public BuddyInfo(String name, String address, int num) {
		if(name == null || address == null) {
			throw new NullPointerException("Input(s) cannot be done");
		}
		this.name = name;
		this.address = address;
		this.phoneNumber = num; 
	}
	
	public BuddyInfo(BuddyInfo buddy) {
		this(buddy.getName(), buddy.getAddress(), buddy.getPhoneNumber());
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public String toString() {
		return "Hello " + name; 
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world!");
		
		BuddyInfo buddy = new BuddyInfo("Kshamina", "fhdjksf", 7897);
		System.out.println("Hello " + buddy.getName());
	}

}