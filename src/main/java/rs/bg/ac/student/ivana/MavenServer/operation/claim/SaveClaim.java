
package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class SaveClaim extends AbstractGenericOperation{
    private Claim claim;
    @Override
    protected void preconditions(Object param) throws Exception {
           if (param == null || !(param instanceof Claim)) {
            throw new Exception("Invalid claim data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        claim= (Claim) repository.add((Claim)param);
    }

    public Claim getClaim() {
        return claim;
    }
    
    
    
}
