package rs.bg.ac.student.ivana.MavenServer.operation.riskType;

import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class DeleteRiskType extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
       
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        RiskType rt=(RiskType)param;
        repository.delete(rt);
    }
    
}
