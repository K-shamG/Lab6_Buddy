import java.util.ArrayList;

public class AddressBook {
	private ArrayList<BuddyInfo> buddyInfo;
	
	public AddressBook() {
		buddyInfo = new ArrayList<BuddyInfo>(); 
	}
	
	public void addBuddy(BuddyInfo buddy) {
		if(buddyInfo != null){
			buddyInfo.add(buddy);
		}
	}
	
	public BuddyInfo removeBuddy(int i) {
		if(i >= 0 && i < this.buddyInfo.size()) {
			return buddyInfo.remove(i);
		}
		return null;
	}
	
	public int size() {
		return buddyInfo.size();
	}
	
	public void clear() {
		buddyInfo.clear();
	}
	
	public static void main(String [] args) {
		BuddyInfo buddy = new BuddyInfo("Kshamina", "ottawa", 6666666);
		AddressBook book = new AddressBook(); 
		book.addBuddy(buddy);
		book.removeBuddy(0);
	}
}