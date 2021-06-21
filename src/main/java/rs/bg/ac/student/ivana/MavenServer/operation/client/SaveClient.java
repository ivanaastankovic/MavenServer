package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.SaveContact;

/**
 * Sistemska operacija za unos novog klijenta
 * @author Ivana
 *
 */
public class SaveClient extends AbstractGenericOperation{
	/**
	 * Client kao instanca klase Client
	 */
    Client client;
    /**
     * Metoda koja proverava da li klijent vec postoji u sistemu
     * @param param Object kao zahtev koji se salje 
     * @throws Exception Ukoliko klijent vec postoji u sistemu
     */
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

    /**
     * Metoda koja dodaje novog klijenta i sve njegove kontakte u bazu
     * @param param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Client c=(Client)param;
        client=(Client) repository.add(c);
        
        for (ClientContacts contact : client.getContacts()) {
            contact.setClient(client);
             repository.add(contact);
        }
    }
    
    /**
     * Metoda koja vraca instancu klase Client
     * @return Client kao instanca klase Client
     */
    public Client getClient() {
       return client;
               
    }
    
}
