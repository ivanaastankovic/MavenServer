package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja brise klijenta iz baze
 * @author Ivana
 *
 */
public class DeleteClient extends AbstractGenericOperation{

	/**
	 * Proverava da li je zahtev tipa klase Client
	 * @param  param Object kao zahtev koji se salje
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
        Client cl=(Client) param;
        
    }

    /**
     * Brise klijenta i sve njegove kontakte iz baze
     * @param  param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        List<ClientContacts> contacts=((Client)param).getContacts();
        try{
        	for (ClientContacts contact : contacts) {
        		repository.delete(contact);
          repository.delete((Client)param);
        }
        }catch(Exception ex){
            throw new Exception("active client");
        }
        
        
      
    }
    
}
