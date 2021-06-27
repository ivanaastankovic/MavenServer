package rs.bg.ac.student.ivana.MavenServer.operation.client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenCommon.domain.ContractType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.SaveContact;

public class SaveClientTest {
	private AbstractGenericOperation saveClient;
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
        client= new Client();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		AbstractGenericOperation deleteClient = new DeleteClient();
		deleteClient.execute(client);
		
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
		saveClient = new SaveClient();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		saveClient = null;
		
	}
	
	@Test
	void constructorTest() {
		saveClient = new SaveClient();
		assertNotNull(saveClient);
	}
	@Test
	void testPreconditions() {
		client.setJmbg("1234567891234");
		assertThrows(java.lang.Exception.class, () -> saveClient.execute(client));
	}
	@Test
	void testPreconditionsNull() {
		assertThrows(java.lang.Exception.class, () -> saveClient.execute(null));
	}
	@Test
	void testExecuteOperation() throws Exception {
		client.setClientID(12l);
		client.setJmbg("1234567891237");
		client.setFirstName("Mika");
		client.setLastName("Mikic");
		client.setSignatureDate(new Date());
		client.setContractType(ContractType.ONE_YEAR);
		
		AbstractGenericOperation saveContact = new SaveContact();
		ClientContacts contact = new ClientContacts();
		contact.setAddress("a");
		contact.setContactID(12l);
		contact.setEmail("e@e");
		contact.setPhone("123456");
		contact.setTown("t");
		
		LinkedList<ClientContacts> contacts = new LinkedList<ClientContacts>();
		contacts.add(contact);
		client.setContacts(contacts);
		
		saveClient.execute(client);
		AbstractGenericOperation getAllClients = new GetAllClients();
		getAllClients.execute(new Client());
		List<Client> clients = ((GetAllClients)getAllClients).getList();
		
		assertEquals(2, clients.size());
		
	}


}
