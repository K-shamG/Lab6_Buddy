import java.io.Serializable;

public class BuddyInfo implements Serializable{
	private static final long serialVersionUID = 4746560110855185031L;
	private String name, address;
	private String phoneNumber;
	private int age;

	public BuddyInfo(String name, String address, String num) {
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

	public String getPhoneNumber() {
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
		String[] parts = str.split("\\$");
		return new BuddyInfo(parts[0], parts[1], parts[2]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world!");
		
		BuddyInfo buddy = new BuddyInfo("Kshamina", "111 address", "789333444");
		System.out.println(buddy.toString());
	}

	private void setAddress(String address) {
		this.address = address;
	}

	private void setPhoneNumber(String num) {
		this.phoneNumber = num;
	}
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if((o == null) || !(o instanceof BuddyInfo)) return false;
		
		BuddyInfo b = (BuddyInfo) o;
		
		return(this.name.equals(b.getName()) && this.address.equals(b.getAddress()) && this.phoneNumber.equals(b.getPhoneNumber()));
	}

}