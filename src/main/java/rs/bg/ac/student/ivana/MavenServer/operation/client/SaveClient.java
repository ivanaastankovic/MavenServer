package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.SaveContact;

public class SaveClient extends AbstractGenericOperation{
    Client client;
    @Override
    protected void preconditions(Object param) throws Exception {
        Client c=(Client)param;
       List<Client> clients=repository.getAll(c);
        for (Client client : clients) {
            if(client.getJmbg().equals(c.getJmbg())){
                throw new Exception("client with same jmbg already exists");
        }
    }
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Client c=(Client)param;
        client=(Client) repository.add(c);
        
        for (ClientContacts contact : client.getContacts()) {
            contact.setClient(client);
             repository.add(contact);
        }
    }

    public Client getClient() {
       return client;
               
    }
    
}
