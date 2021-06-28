package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import java.math.BigDecimal;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja azurira vrednosti zalbe
 * @author Ivana
 *
 */
public class EditClaim extends AbstractGenericOperation{

	/**
	 * Proverava da li je prosledjeni objekat instanca klase Claim ili je null
	 * @param  param Object kao zahtev koji se salje
	 * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Claim</li>					 
	 * <li>Ako je prethodni status zalbe PENDING, a novi FILED</li>
	 * <li>Ako je prethodni status zalbe ACCEPTED, a novi REJECTED</li>
	 * <li>Ako je novi paymentSum veci od maxSum za taj RiskType</li>
	 * </ul>
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
         
              
    }

    /**
     * Azurira vrednosti zalbe
     * @param param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Claim)param);
         
         
    }
    
}
