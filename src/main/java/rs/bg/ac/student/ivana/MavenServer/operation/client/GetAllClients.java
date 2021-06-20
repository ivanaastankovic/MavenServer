package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.ArrayList;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.GetAllByClientContacts;


public class GetAllClients extends AbstractGenericOperation{
     
    private List<Client> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=new ArrayList<>();
        list=repository.getAll((Client)param);
        for (Client client : list) {
            List<ClientContacts> contacts=new ArrayList<>();
            ClientContacts c=new ClientContacts();
            c.setId(client.getClientID());
            GetAllByClientContacts op=new GetAllByClientContacts();
            op.execute(c);
            contacts=op.getList();
            for (ClientContacts con : contacts) {
                client.getContacts().add(con);
            }
            
        }
    }

    public List<Client> getList() {
        return list;
    }
    
    
    
}
