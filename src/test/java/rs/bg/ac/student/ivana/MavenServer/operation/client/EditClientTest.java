package rs.bg.ac.student.ivana.MavenServer.operation.client;

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
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.GetAllByClientContacts;

public class EditClientTest {
	
	private AbstractGenericOperation editClient;
	private static Client client;
	private static ClientContacts contact;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileOutputStream out = new FileOutputStream("config/dbconfig.properties");
		Properties properties = new Properties();
        properties.setProperty("url","jdbc:mysql://localhost:3306/osiguranje_test");
        properties.setProperty("username", "root");
        properties.setProperty("password", "");
        properties.store(out, null);
        
        /*AbstractGenericOperation readAllClients = new GetAllClients();
        readAllClients.execute(new Client());
        client = (Client)((GetAllClients)readAllClients).getList().get(0);
        */
        /*AbstractGenericOperation getAllByClientContacts = new GetAllByClientContacts();
        getAllByClientContacts.execute(client);
        contact= ((GetAllByClientContacts)getAllByClientContacts).getList().get(0);
        */
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
		editClient = new EditClient();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		editClient = null;
		
	}

	@Test
	void testExecuteOperation() throws Exception {
		/*
		contact.setAddress("Address update");
		
		editClient.execute(client);
		
		AbstractGenericOperation getAllByClientContacts = new GetAllByClientContacts();
		getAllByClientContacts.execute(client);
		ClientContacts c = ((GetAllByClientContacts)getAllByClientContacts).getList().get(0);
		assertEquals("Address update",c.getAddress());
		*/
	}

}
