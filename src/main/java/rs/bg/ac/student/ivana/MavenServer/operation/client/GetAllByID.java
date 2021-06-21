package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenServer.operation.claim.*;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve klijente sa zadatim ID-em
 * @author Ivana
 *
 */
public class GetAllByID extends AbstractGenericOperation{
	/**
	 * String kao id klijenta
	 */
    private String id;
    /**
     * List kao lista klijenata
     */
    private List<Client> list;

    @Override
    protected void preconditions(Object param) throws Exception {
      
    }

    /**
     * Metoda koja vraca sve klijente sa zadatim ID-em
     * @param  param Object kao zahtev koji se salje
     * @throws Exception Ukoliko ne postoji klijent sa zadatim ID-em
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list=repository.getAllBy((Client)param, " jmbg ", id);
        if(list.isEmpty()){
            throw new Exception("there is no client with this jmbg");
        }
            
    }

    /**
     * Metoda koja vraca instancu klase Client
     * @return Client kao instancu klase Client
     */
    public Client getClient() {
        return list.get(0);
    }

    /**
     * Metoda koja postavlja ID na novu vrednost
     * @param id String kao ID klijenta
     */
    public void setID(String id) {
        this.id = id;
    }
    
    
    
    
}
