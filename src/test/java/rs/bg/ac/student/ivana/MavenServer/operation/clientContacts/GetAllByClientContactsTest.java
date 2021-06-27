package rs.bg.ac.student.ivana.MavenServer.operation.clientContacts;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.client.GetAllByID;
import rs.bg.ac.student.ivana.MavenServer.operation.client.GetAllClients;

public class GetAllByClientContactsTest {
	private AbstractGenericOperation getAllByClient;
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
		getAllByClient = new GetAllByClientContacts();
	}
	@AfterEach
	void tearDown() throws Exception{
		getAllByClient = null;
	}
	@Test
	void constructorTest() {
		getAllByClient = new GetAllByClientContacts();
		assertNotNull(getAllByClient);
	}


	@Test
	void testExecuteOperation() throws Exception {
		AbstractGenericOperation getAllClients = new GetAllClients();
		getAllClients.execute(new Client());
		Client client = ((GetAllClients)getAllClients).getList().get(0);
		
		ClientContacts contact = new ClientContacts();
		contact.setClient(client);
		getAllByClient.execute(contact);
		List<ClientContacts> contacts = ((GetAllByClientContacts)getAllByClient).getList();
		
		assertNotNull(contacts);
		assertEquals(1, contacts.size());
	}


}
