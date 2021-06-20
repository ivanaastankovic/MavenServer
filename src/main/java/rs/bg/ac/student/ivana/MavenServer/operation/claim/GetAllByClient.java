package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

public class GetAllByClient extends AbstractGenericOperation{
    private Client client;
    private List<Claim> list;

    @Override
    protected void preconditions(Object param) throws Exception {
      
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=repository.getAllBy((Claim)param, "cl.jmbg", client.getJmbg());
        
    }

    public List<Claim> getList() {
        return list;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
    
    
}
