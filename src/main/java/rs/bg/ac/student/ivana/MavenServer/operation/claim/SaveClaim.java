
package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja unosi novu zalbu
 * @author Ivana
 *
 */
public class SaveClaim extends AbstractGenericOperation{
	/**
	 * Claim kao instanca klase Claim
	 */
    private Claim claim;
    /**
     * Proverava uslov da li je objekat instanca tipa Claim ili je null 
     * @throws Exception ukoliko objekat nije instanca klase Claim ili je null
     */
    @Override
    protected void preconditions(Object param) throws Exception {
           if (param == null || !(param instanceof Claim)) {
            throw new Exception("Invalid claim data!");
        }
    }

    /**
     * Dodaje novu zalbu u bazu
     * @param  param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        claim= (Claim) repository.add((Claim)param);
    }

    /**
     * Vraca instancu klase Claim
     * @return Claim kao instanca klase Claim
     */
    public Claim getClaim() {
        return claim;
    }
    
    
    
}
