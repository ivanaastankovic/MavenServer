package rs.bg.ac.student.ivana.MavenServer.operation.riskType;

import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class AddRiskType extends AbstractGenericOperation {
      private RiskType riskType;
    @Override
    protected void preconditions(Object param) throws Exception {
         
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        RiskType rt = (RiskType) param;
        riskType=(RiskType) repository.add(rt);
        if(riskType==null){
            throw new Exception("risk type can not be saved");
        }
        
    }   

    public RiskType getRiskType() {
        return riskType;
    }
    
    
}
