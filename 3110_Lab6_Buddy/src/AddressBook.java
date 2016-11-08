import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
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
	
	public StringWriter export() throws IOException {
		StringWriter sw = new StringWriter(); 
		BufferedWriter w = new BufferedWriter(sw);
		
		for(BuddyInfo b: buddyInfo) {
			w.write(b.toString() + "\n");
		}
		w.flush(); 
		return sw; 
	}
	
	public static void main(String [] args) {
		BuddyInfo buddy = new BuddyInfo("Kshamina", "ottawa", 6666666);
		BuddyInfo b2 = new BuddyInfo("Bobby", "toronto", 444444444);
		
		BuddyInfo testImport = BuddyInfo.imporT(buddy.toString());
		
		AddressBook book = new AddressBook(); 
		book.addBuddy(buddy);
		book.addBuddy(b2);
		book.addBuddy(testImport);
		
		try {
			System.out.println(book.export());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}
}