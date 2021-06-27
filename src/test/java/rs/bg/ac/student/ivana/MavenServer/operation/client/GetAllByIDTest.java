package rs.bg.ac.student.ivana.MavenServer.operation.client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

public class GetAllByIDTest {
	private AbstractGenericOperation getAllByID;
	private static Client createdClient;
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
		getAllByID = new GetAllByID();
	}
	@AfterEach
	void tearDown() throws Exception{
		getAllByID = null;
	}
	@Test
	void constructorTest() {
		getAllByID = new GetAllByID();
		assertNotNull(getAllByID);
	}
	

	@Test
	void testExecuteOperation() throws Exception {
		createdClient=new Client();
		createdClient.setClientID(11l);
		createdClient.setJmbg("1234567891234");
		getAllByID.execute(createdClient);
		Client c = ((GetAllByID)getAllByID).getClient();
		assertNotNull(c);
	}

}
