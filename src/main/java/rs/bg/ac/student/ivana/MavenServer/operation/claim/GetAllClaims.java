package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import java.util.ArrayList;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class GetAllClaims extends AbstractGenericOperation {
    private List<Claim> list;
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=new ArrayList<>();
        list=repository.getAll((Claim)param);
    }

    public List<Claim> getList() {
        return list;
    }
    
    
    
}
