package rs.bg.ac.student.ivana.MavenServer.operation.riskType;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.client.GetAllClients;

public class GetAllRiskTypesTest {
	private AbstractGenericOperation getAllRiskTypes;
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
		getAllRiskTypes = new GetAllRiskTypes();
	}
	@AfterEach
	void tearDown() throws Exception{
		getAllRiskTypes = null;
	}
	@Test
	void constructorTest() {
		getAllRiskTypes = new GetAllRiskTypes();
		assertNotNull(getAllRiskTypes);
	}

	@Test
	void testExecuteOperation() throws Exception {
		getAllRiskTypes.execute(new RiskType());
		List<RiskType> list = ((GetAllRiskTypes)getAllRiskTypes).getList();
		assertNotNull(list);
		assertEquals(1, list.size());
	}


}
