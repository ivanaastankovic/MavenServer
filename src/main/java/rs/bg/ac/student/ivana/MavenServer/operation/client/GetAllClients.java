package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.GetAllByClientContacts;

/**
 * Sistemka operacija koja vraca sve klijente
 * @author Ivana
 *
 */
public class GetAllClients extends AbstractGenericOperation{
     /**
      * List kao lista svih klijenata
      */
    private List<Client> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    /**
     * Vraca sve klijente i kontakte tih klijenata
     * @param param Object kao zahtev koji se salje
     * 
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list=new ArrayList<>();
        list=repository.getAll((Client)param);
        for (Client client : list) {
            List<ClientContacts> contacts=new ArrayList<>();
            ClientContacts c=new ClientContacts();
            c.setId(client.getClientID());
            GetAllByClientContacts op=new GetAllByClientContacts();
            c.setClient(client);  /// dodato
            op.execute(c);
            contacts=op.getList();
            for (ClientContacts con : contacts) {
            	client.setContacts(new LinkedList<ClientContacts>());// dodato
                client.getContacts().add(con);
            }
            
        }
    }
    
    /**
     * Vraca listu klijenata
     * @return List kao listu instanci klase Client
     */
    public List<Client> getList() {
        return list;
    }
    
    
    
}
