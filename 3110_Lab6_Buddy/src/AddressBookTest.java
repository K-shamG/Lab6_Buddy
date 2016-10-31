import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {
	private AddressBook addressBook;
	
	private BuddyInfo b1;
	private BuddyInfo b2;
	private BuddyInfo b3;
	
	private String name = "test";
	private String address = "testAddress";
	private int phonenumber = 5555555;
	
	@Before
	public void setup() {
		addressBook = new AddressBook();
		b1 = new BuddyInfo(name,address, phonenumber);
		b2 = new BuddyInfo(name,address, phonenumber);
		b3 = new BuddyInfo(name,address, phonenumber);
		addressBook.addBuddy(b1);
		addressBook.addBuddy(b2);
		addressBook.addBuddy(b3);
	}
	
	@Test
	public void testConstructor() {
		AddressBook a = new AddressBook();
		assertNotNull(a);
	}
	
	@Test
	public void testAddBuddy() {
		assertEquals("Size of address book should now be 3", 3, addressBook.size());
	}
	
	@Test
	public void testRmBuddy() {
		addressBook.removeBuddy(0);
		assertEquals("Size should now be 2", 2, addressBook.size());
	}
	
	@Test
	public void testRmTwoBuddies() {
		addressBook.removeBuddy(0);
		addressBook.removeBuddy(1);
		assertEquals("Size should now be 1", 1, addressBook.size());
	}
	
	@Test
	public void testRmInvalidIndex() {
		assertEquals("Should return null", null, addressBook.removeBuddy(10));
	}
	
	@Test
	public void testClear(){
		System.out.println(addressBook.size());
		addressBook.clear();
		System.out.println(addressBook.size());
		assertEquals("AddressBook should be empty", 0, addressBook.size());
	}

}
