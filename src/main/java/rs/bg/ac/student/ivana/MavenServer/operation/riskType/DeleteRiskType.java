package rs.bg.ac.student.ivana.MavenServer.operation.riskType;

import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja brise tip rizika
 * @author Ivana
 *
 */
public class DeleteRiskType extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
       
    }

    /**
     * Brise tip rizika u bazi
     * @param param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        RiskType rt=(RiskType)param;
        repository.delete(rt);
    }
    
}
