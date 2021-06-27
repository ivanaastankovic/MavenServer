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

import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

public class DeleteRiskTypeTest {
	private AbstractGenericOperation deleteRiskType;
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
		deleteRiskType = new DeleteRiskType();
	}
	@AfterEach
	void tearDown() throws Exception{
		deleteRiskType = null;
	}
	@Test
	void constructorTest() {
		deleteRiskType = new DeleteRiskType();
		assertNotNull(deleteRiskType);
	}

	@Test
	void testExecuteOperation() throws Exception {
		RiskType riskType = new RiskType();
		riskType.setName("Risk");
		riskType.setDomain("Domain");
		riskType.setDescription("Description");
		riskType.setMinSum(new BigDecimal(10));
		riskType.setMaxSum(new BigDecimal(50));
		AbstractGenericOperation addRT = new AddRiskType();
		addRT.execute(riskType);
		
		AbstractGenericOperation getAllRT = new GetAllRiskTypes();
		getAllRT.execute(new RiskType());
		List<RiskType> list = ((GetAllRiskTypes)getAllRT).getList();
		assertEquals(2, list.size());
		
		deleteRiskType.execute(riskType);
		getAllRT = new GetAllRiskTypes();
		getAllRT.execute(new RiskType());
		list = ((GetAllRiskTypes)getAllRT).getList();
		assertEquals(1, list.size());
	}

}
