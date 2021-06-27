package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.client.DeleteClient;
import rs.bg.ac.student.ivana.MavenServer.operation.client.GetAllByID;
import rs.bg.ac.student.ivana.MavenServer.operation.riskType.GetAllRiskTypes;

public class SaveClaimTest {
	private AbstractGenericOperation saveClaim;
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
		saveClaim = new SaveClaim();
	}
	@AfterEach
	void tearDown() throws Exception{
		saveClaim = null;
	}
	@Test
	void constructorTest() {
		saveClaim = new SaveClaim();
		assertNotNull(saveClaim);
	}
	@Test
	void testPreconditionsNull() {
		assertThrows(java.lang.Exception.class, () -> saveClaim.execute(null));
	}
	@Test
	void testPreconditionsInstance() {
		assertThrows(java.lang.Exception.class, () -> saveClaim.execute(new Client()));
	}
	@Test
	void testExecuteOperation() throws Exception {
		/*Claim claim = new Claim();
	
		claim.setActivity("Act");
		claim.setPaymentSum(new BigDecimal(200));
		claim.setStatus(Status.FILED);
		
		Client client = new Client();
		client.setJmbg("1234567891234");
		AbstractGenericOperation getClient = new GetAllByID();
		getClient.execute(client);
		client = ((GetAllByID)getClient).getClient();
		
		claim.setClient(client);
		
		AbstractGenericOperation getAllRiskTypes = new GetAllRiskTypes();
		getAllRiskTypes.execute(new RiskType());
		RiskType riskType = ((GetAllRiskTypes)getAllRiskTypes).getList().get(0);
		claim.setRiskType(riskType);
		
		getClient.execute(client);
		client = ((GetAllByID)getClient).getClient();
		
		AbstractGenericOperation getAllClaims = new GetAllClaims();
		getAllClaims.execute(new Claim());
		Claim c = ((GetAllClaims)getAllClaims).getList().get(0);
		
	//	Date date = c.getFileDate();
		
		///////////////////////////////////////////////////////////////////////////////
		/*SimpleDateFormat format =  new SimpleDateFormat("yyyy-mm-dd");
		String dateString = format.format(new Date());
		Date date = format.parse("2021-06-29");
		claim.setFileDate(date);
		
		saveClaim.execute(claim);
		
		getAllClaims = new GetAllClaims();
		getAllClaims.execute(new Claim());
		List<Claim> list = ((GetAllClaims)getAllClaims).getList();
		assertNotNull(list);
		assertEquals(2, list.size());
	*/
	}

	
}
