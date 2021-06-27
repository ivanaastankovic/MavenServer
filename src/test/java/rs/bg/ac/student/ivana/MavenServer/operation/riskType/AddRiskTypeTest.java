package rs.bg.ac.student.ivana.MavenServer.operation.riskType;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.client.DeleteClient;

public class AddRiskTypeTest {
	private AbstractGenericOperation addRiskType;
	private static RiskType riskType;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileOutputStream out = new FileOutputStream("config/dbconfig.properties");
		Properties properties = new Properties();
        properties.setProperty("url","jdbc:mysql://localhost:3306/osiguranje_test");
        properties.setProperty("username", "root");
        properties.setProperty("password", "");
        properties.store(out, null);
        out.close();
        riskType= new RiskType();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		AbstractGenericOperation deleteRiskType= new DeleteRiskType();
		deleteRiskType.execute(riskType);
		
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
		addRiskType = new AddRiskType();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		addRiskType = null;
		
	}
	
	@Test
	void constructorTest() {
		addRiskType = new AddRiskType();
		assertNotNull(addRiskType);
	}


	@Test
	void testExecuteOperation() throws Exception {
		//riskType.setRiskTypeID(16l);
		riskType.setName("Risk");
		riskType.setDomain("Domain");
		riskType.setDescription("Description");
		riskType.setMinSum(new BigDecimal(10));
		riskType.setMaxSum(new BigDecimal(50));
		addRiskType.execute(riskType);
		
		AbstractGenericOperation getAllRiskTypes = new GetAllRiskTypes();
		getAllRiskTypes.execute(new RiskType());
		List<RiskType> list = ((GetAllRiskTypes)getAllRiskTypes).getList();
		assertEquals(2, list.size());
	
	}



}
