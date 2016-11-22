import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {
	private AddressBook addressBook;
	
	private BuddyInfo b1;
	private BuddyInfo b2;
	private BuddyInfo b3;
	
	private String name = "test";
	private String address = "testAddress";
	private String phonenumber = "5555555";
	
	private String filename = "TEST.txt";
	
	@Before
	public void setup() {
		addressBook = new AddressBook();
		b1 = new BuddyInfo(name, address, phonenumber);
		b2 = new BuddyInfo(name + "1",address + "1", phonenumber + "1");
		b3 = new BuddyInfo(name + "2",address + "2", phonenumber + "2");
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
	public void testAddBuddyAndSize() {
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
		addressBook.clear();
		assertEquals("AddressBook should be empty", 0, addressBook.size());
	}
	
	@Test 
	public void testImportExport() throws IOException {
		addressBook.export(filename);
		assertTrue(new File(filename).exists());
		
		AddressBook book = addressBook.importFromFile(filename);
		
		assertTrue(addressBook.equals(book));
		
	}
	
	@Test
	public void testImportExportSerialize() throws IOException, ClassNotFoundException {
		FileOutputStream fout = new FileOutputStream("serializeBook.txt");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		
		addressBook.writeObject(out);
		assertTrue(new File("serializeBook.txt").exists());
		
		FileInputStream fin = new FileInputStream("serializeBook.txt");
		ObjectInputStream in = new ObjectInputStream(fin);
		
		AddressBook book = addressBook.readObject(in);
		
		assertTrue(addressBook.equals(book));

	}

}
