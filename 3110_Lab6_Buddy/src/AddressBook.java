import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AddressBook implements Serializable {

	private static final long serialVersionUID = 7416088425234620160L;
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
	
	public StringWriter export(String filename) throws IOException {
		StringWriter sw = new StringWriter(); 
		FileWriter fw = new FileWriter(filename);
		
		for(BuddyInfo b: buddyInfo) {
			sw.write(b.toString());
			sw.write(System.lineSeparator());
		}
		
		fw.write(sw.toString());
		fw.close();
		
		return sw; 
	}
	
	public boolean equals(Object o) {
		
		if(this == o) return true;
		if((o == null) || !(o instanceof AddressBook)) return false;
		
		AddressBook book = (AddressBook) o;
		if(book.size() != this.size()) return false; 
		
		for(BuddyInfo b: book.buddyInfo) {
			for(BuddyInfo thisB: this.buddyInfo) {
				return thisB.equals(b); 
			}
		}
		return false;
	}
	
	public AddressBook importFromFile(String filename) {
		BufferedReader br = null; 
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AddressBook book = new AddressBook();
		String line = null; 
		try {
			while((line = br.readLine()) != null) {
				if(!(line.equals(""))) {
					String[] parts = line.toString().split("\\$");
					BuddyInfo buddy = new BuddyInfo(parts[0], parts[1], parts[2]);
					book.addBuddy(buddy);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return book; 
	}
	
	public void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(this);
		out.close();
	}

	public AddressBook readObject(ObjectInputStream in) throws IOException, ClassNotFoundException  {
		AddressBook book = (AddressBook) in.readObject();
		
		for(BuddyInfo b: book.buddyInfo) {
			System.out.println(b.toString());
		}
		
		return book;
	}
	
	public void toXML(String filename) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		
		Document doc = documentBuilder.newDocument();
		Element root = doc.createElement("addressBook");
		doc.appendChild(root);
		
		for(BuddyInfo b: buddyInfo) {
			Element buddy = doc.createElement("buddyInfo");
			root.appendChild(buddy);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(b.getName()));
			buddy.appendChild(name);
			
			Element address = doc.createElement("address");
			address.appendChild(doc.createTextNode(b.getAddress()));
			buddy.appendChild(address);
			
			Element phoneNum = doc.createElement("phoneNumber");
			phoneNum.appendChild(doc.createTextNode(b.getPhoneNumber()));
			buddy.appendChild(phoneNum);	
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename));
		
		transformer.transform(source, result);
		
	}
	
	public AddressBook importFromXML(String filename) throws Exception {
		AddressBook book = new AddressBook();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.parse(new File(filename));
		
		System.out.println("Address Book: " + doc.getDocumentElement().getNodeName());
		
		NodeList lst = doc.getDocumentElement().getChildNodes();
		for(int i = 0; i < lst.getLength(); i++) { // buddy info
			Node n = lst.item(i);			
			Node name = doc.getElementsByTagName("name").item(i);
			Node address = doc.getElementsByTagName("address").item(i);
			Node phoneNum = doc.getElementsByTagName("phoneNumber").item(i);
			
			BuddyInfo buddy = new BuddyInfo(name.getTextContent(), address.getTextContent(), phoneNum.getTextContent());
			book.addBuddy(buddy);
		}
		
		return book;
	}
	
	
	
	public static void main(String [] args) {
		BuddyInfo buddy = new BuddyInfo("Kshamina", "ottawa", "6666666");
		BuddyInfo b2 = new BuddyInfo("Bobby", "toronto", "444444444");
		
		BuddyInfo testImport = BuddyInfo.imporT(buddy.toString() + "\n");

		System.out.println("Test Import: " + testImport.toString());
		
		AddressBook book = new AddressBook(); 
		book.addBuddy(buddy);
		book.addBuddy(b2);
		book.addBuddy(testImport);

		
		try {
			System.out.println(book.export("addressBook.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Test import from file");
		AddressBook book2 = book.importFromFile("addressBook.txt");
		for(BuddyInfo b: book2.buddyInfo) {
			System.out.println(b.toString());
		}
		
		try {
			book.toXML("test.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			book.importFromXML("test.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
