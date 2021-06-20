package rs.bg.ac.student.ivana.MavenServer.operation.riskType;

import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class GetAllRiskTypes extends AbstractGenericOperation{
    List<RiskType> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         list = repository.getAll((RiskType) param);
    }

    public List<RiskType> getList() {
        return list;
    }
    
    
}
