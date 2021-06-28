package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

public class EditClaimTest {
	private AbstractGenericOperation editClaim;
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
		editClaim = new EditClaim();
	}
	@AfterEach
	void tearDown() throws Exception{
		editClaim = null;
	}
	@Test
	void constructorTest() {
		editClaim = new EditClaim();
		assertNotNull(editClaim);
	}
	@Test
	void testPreconditionsNull() {
		assertThrows(java.lang.Exception.class, () -> editClaim.execute(null));
	}

	@Test
	void testPreconditionsNotInstance() {
		assertThrows(java.lang.Exception.class, () -> editClaim.execute(new Client()));
	}
	

	
	@Test
	void testExecuteOperation() throws Exception {
		AbstractGenericOperation getClaimById = new GetAllByID();
		Claim claim = new Claim();
		claim.setClaimID(18l);
		getClaimById.execute(claim);
		Claim c = ((GetAllByID)getClaimById).getClaim();
		c.setStatus(Status.PENDING);
		editClaim.execute(c);
	}

}
