package rs.bg.ac.student.ivana.MavenServer.operation.login;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import rs.bg.ac.student.ivana.MavenCommon.domain.DomainType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

public class LogInTest {
	
	private AbstractGenericOperation getAdmin;
	
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
		getAdmin = new LogIn();
	}
	@AfterEach
	void tearDown() throws Exception{
		getAdmin = null;
	}
	
	@Test
	void constructorTest() {
		getAdmin = new LogIn();
		assertNotNull(getAdmin);
	}
	
	@Test
	void testExecuteOperation() throws Exception {
		getAdmin.execute(new Admin("admin","admin")); // izvrsimo metodu
		Admin admin = ((LogIn)getAdmin).getAdmin();
		assertNotNull(admin);
		
	}



}
