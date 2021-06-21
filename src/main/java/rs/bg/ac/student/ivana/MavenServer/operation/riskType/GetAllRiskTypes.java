package rs.bg.ac.student.ivana.MavenServer.operation.riskType;

import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve tipove rizika
 * @author Ivana
 *
 */
public class GetAllRiskTypes extends AbstractGenericOperation{
	/**
	 * List kao lista instanci tipa RiskType
	 */
    List<RiskType> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    /**
     * Vraca sve tipove rizika iz baze i cuva ih u listi
     * @param param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
         list = repository.getAll((RiskType) param);
    }

    /**
     * Vraca listu instanci klase RiskType
     * @return List kao lista intanci klase RiskType
     */
    public List<RiskType> getList() {
        return list;
    }
    
    
}
