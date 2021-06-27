package rs.bg.ac.student.ivana.MavenServer.operation.clientContacts;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.client.DeleteClient;
import rs.bg.ac.student.ivana.MavenServer.operation.client.GetAllClients;

public class SaveContactTest {
	private AbstractGenericOperation saveContact;
	private static ClientContacts contact;
	private static Client client;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileOutputStream out = new FileOutputStream("config/dbconfig.properties");
		Properties properties = new Properties();
        properties.setProperty("url","jdbc:mysql://localhost:3306/osiguranje_test");
        properties.setProperty("username", "root");
        properties.setProperty("password", "");
        properties.store(out, null);
        out.close();
        AbstractGenericOperation getAllClients = new GetAllClients();
        getAllClients.execute(new Client());
        client = ((GetAllClients)getAllClients).getList().get(0);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		FileOutputStream out = new FileOutputStream("config/dbconfig.properties");
		Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mysql://localhost:3306/database_osiguranje");
        properties.setProperty("username", "root");
        properties.setProperty("password", "");
        properties.store(out, null);
        out.close();
	}

	@Test
	void testPreconditionsAddressTown() throws Exception {
		ClientContacts cc = new ClientContacts();
		cc.setClient(client);
		cc.setAddress("aa");
		cc.setTown("tt");
		cc.setPhone("555");
		cc.setEmail("a@gma.com");
		cc.setPhone("5555");
		assertThrows(java.lang.Exception.class, () -> saveContact.execute(cc));
	}



	@Test
	void testPreconditionsPhone() throws Exception {
		ClientContacts cc = new ClientContacts();
		cc.setClient(client);
		cc.setAddress("a");
		cc.setTown("t");
		cc.setPhone("555");
		cc.setEmail("a@gma.com");
		assertThrows(java.lang.Exception.class, () -> saveContact.execute(cc));
	}
	@BeforeEach
	void setUp() throws Exception{
		saveContact = new SaveContact();
	}
	@AfterEach
	void tearDown() throws Exception{
		saveContact = null;
	}
	@Test
	void constructorTest() {
		saveContact = new SaveContact();
		assertNotNull(saveContact);
	}

	@Test
	void testExecuteOperation() throws Exception {
		contact = new ClientContacts();
		contact.setClient(client);
		contact.setAddress("aaa");
		contact.setTown("ttt");
		contact.setPhone("5555");
		contact.setEmail("a@gma.com");
		saveContact.execute(contact);
		
		AbstractGenericOperation getContacts = new GetAllByClientContacts();
		getContacts.execute(contact);
		List<ClientContacts> contacts = ((GetAllByClientContacts)getContacts).getList();
		
		assertEquals(2, contacts.size());
		
		AbstractGenericOperation deleteContact = new DeleteContact();
		deleteContact.execute(contact);
		
		getContacts = new GetAllByClientContacts();
		getContacts.execute(contact);
		contacts = ((GetAllByClientContacts)getContacts).getList();
		assertEquals(1, contacts.size());
	}

}
