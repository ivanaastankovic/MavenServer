package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve zalbe klijenta
 * @author Ivana
 *
 */
public class GetAllByClient extends AbstractGenericOperation{
	/**
	 * Client kao instanca klase Client
	 */
    public Client client;
    /**
     * List kao lista intanci klase Claim
     */
    private List<Claim> list;

    @Override
    protected void preconditions(Object param) throws Exception {
      
    }

    /**
     * Pronalazi sve instance klase Claim sa zadatim uslovom i cuva ih u listi
     * @param  param Object kao zahtev koji se salje
     * 
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
    	Claim claim = (Claim)param;
    	claim.setClient(client);
        list=repository.getAllBy(claim, "cl.jmbg", claim.getClient().getJmbg());
        
    }
    /**
     * Vraca listu instanci klase Claim
     * @return List kao lista intanci klase Claim
     */
    public List<Claim> getList() {
        return list;
    }
    
    /**
     * Postavlja novi pokazivac na klasu Client 
     * @param client kao instanca klase Client
     */
    public void setClient(Client client) {
        this.client = client;
    }
    
    
    
    
}
