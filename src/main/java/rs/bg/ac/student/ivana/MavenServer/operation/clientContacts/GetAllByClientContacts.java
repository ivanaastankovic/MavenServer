package rs.bg.ac.student.ivana.MavenServer.operation.clientContacts;

import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.ArrayList;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class GetAllByClientContacts extends AbstractGenericOperation{
    private List<ClientContacts> list;
    @Override
    protected void preconditions(Object param) throws Exception {
       
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
      ClientContacts cc=(ClientContacts)param;
      list=new ArrayList<>();
      System.out.println(cc.getClient().getClientID());
      list=repository.getAllBy(cc, " clientID ", cc.getClient().getClientID()+"");
    }

    public List<ClientContacts> getList() {
        return list;
    }
    
    
    
}
