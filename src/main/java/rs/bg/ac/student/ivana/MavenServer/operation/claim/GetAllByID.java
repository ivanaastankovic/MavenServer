package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

public class GetAllByID extends AbstractGenericOperation{
    private Long id;
    private List<Claim> list;

    @Override
    protected void preconditions(Object param) throws Exception {
      
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=repository.getAllBy((Claim)param, " c.id ", ((Claim)param).getClaimID()+" ");
        if(list.isEmpty()){
            throw new Exception("no claim with this id");
        }
        
    }

    public Claim getClaim() {
        return list.get(0);
    }

    public void setID(Long id) {
        this.id = id;
    }
    
    
    
    
}
