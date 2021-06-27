package rs.bg.ac.student.ivana.MavenServer.operation.client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
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
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.login.LogIn;

public class GetAllClientsTest {

	private AbstractGenericOperation getAllClients;
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
		getAllClients = new GetAllClients();
	}
	@AfterEach
	void tearDown() throws Exception{
		getAllClients = null;
	}
	@Test
	void constructorTest() {
		getAllClients = new GetAllClients();
		assertNotNull(getAllClients);
	}
	@Test
	void testExecuteOperation() throws Exception {
		getAllClients.execute(new Client());
		List<Client> clients = ((GetAllClients)getAllClients).getList();
		assertNotNull(clients);
		assertEquals(1, clients.size());
	}

}
