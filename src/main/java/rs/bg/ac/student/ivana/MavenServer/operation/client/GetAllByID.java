package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenServer.operation.claim.*;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class GetAllByID extends AbstractGenericOperation{
    private String id;
    private List<Client> list;

    @Override
    protected void preconditions(Object param) throws Exception {
      
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=repository.getAllBy((Client)param, " jmbg ", id);
        if(list.isEmpty()){
            throw new Exception("there is no client with this jmbg");
        }
            
    }

    public Client getClient() {
        return list.get(0);
    }

    public void setID(String id) {
        this.id = id;
    }
    
    
    
    
}
