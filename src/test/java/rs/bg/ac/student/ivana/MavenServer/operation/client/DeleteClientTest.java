package rs.bg.ac.student.ivana.MavenServer.operation.client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenCommon.domain.ContractType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

public class DeleteClientTest {
	private AbstractGenericOperation deleteClient;
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
		deleteClient = new DeleteClient();
	}
	@AfterEach
	void tearDown() throws Exception{
		deleteClient = null;
	}
	@Test
	void constructorTest() {
		deleteClient = new DeleteClient();
		assertNotNull(deleteClient);
	}
	@Test
	void testPreconditions() {
		assertThrows(java.lang.Exception.class, () -> deleteClient.execute(new Admin()));
	}

	@Test
	void testExecuteOperation() throws Exception {
		Client client = new Client();
		
		client.setFirstName("Mika");
		client.setLastName("Mikic");
		client.setJmbg("1234567891255");
		Date date = new Date(System.currentTimeMillis());
		client.setSignatureDate(date);
		client.setContractType(ContractType.ONE_YEAR);
		ClientContacts contact = new ClientContacts();
		contact.setAddress("a");
		contact.setContactID(12l);
		contact.setEmail("e@e");
		contact.setPhone("123456");
		contact.setTown("t");
		
		LinkedList<ClientContacts> contacts = new LinkedList<ClientContacts>();
		contacts.add(contact);
		client.setContacts(contacts);
		
		AbstractGenericOperation saveClient = new SaveClient();
		saveClient.execute(client);
		
		/*AbstractGenericOperation getClients =  new GetAllClients();
		getClients.execute(new Client());
		List<Client> clients = ((GetAllClients)getClients).getList();
		assertEquals(2, clients.size());
		*/
		deleteClient.execute(client);
		
		AbstractGenericOperation getClients =  new GetAllClients();
		getClients.execute(new Client());
		List<Client> clients = ((GetAllClients)getClients).getList();
		
		assertEquals(1, clients.size());
	}

}
