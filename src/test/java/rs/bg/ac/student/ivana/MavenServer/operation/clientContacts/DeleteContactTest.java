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
import rs.bg.ac.student.ivana.MavenServer.operation.client.GetAllClients;

public class DeleteContactTest {
	private AbstractGenericOperation deleteContact;
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
	@BeforeEach
	void setUp() throws Exception{
		deleteContact = new DeleteContact();
	}
	@AfterEach
	void tearDown() throws Exception{
		deleteContact = null;
	}
	@Test
	void constructorTest() {
		deleteContact = new DeleteContact();
		assertNotNull(deleteContact);
	}
	@Test
	void testPreconditions() {
		assertThrows(java.lang.Exception.class,  ()-> deleteContact.execute(client));
	}

	@Test
	void testExecuteOperation() throws Exception {
		AbstractGenericOperation getAllClients = new GetAllClients();
        getAllClients.execute(new Client());
        client = ((GetAllClients)getAllClients).getList().get(0);
		AbstractGenericOperation saveContact = new SaveContact();
		ClientContacts contact = new ClientContacts();
		contact.setClient(client);
		contact.setAddress("aaaaaaa");
		contact.setTown("tttttt");
		contact.setPhone("555555");
		contact.setEmail("aaa@aaa");
		saveContact.execute(contact);
		
		AbstractGenericOperation getContacts = new GetAllByClientContacts();
		getContacts.execute(contact);
		List<ClientContacts> contacts = ((GetAllByClientContacts)getContacts).getList();
		
		assertEquals(2, contacts.size());
		
		deleteContact.execute(contact);
		getContacts = new GetAllByClientContacts();
		getContacts.execute(contact);
		contacts = ((GetAllByClientContacts)getContacts).getList();
		
		assertEquals(1, contacts.size());
	}

}
