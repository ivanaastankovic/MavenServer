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
          if (param == null || !(param instanceof Claim)) {
            throw new Exception("Invalid product data!");
        }
          Claim old=(Claim) repository.search((Claim)param).get(0);
          Claim cl=(Claim)param;
          if((old.getStatus()==Status.PENDING && cl.getStatus()==Status.FILED)){
              throw new Exception("invalid new status");
          }
          if((old.getStatus()==Status.ACCEPTED || old.getStatus()==Status.REJECTED)){
              throw new Exception("the status is finalized");
          }
          System.out.println(cl.getPaymentSum().toString());
           System.out.println(old.getRiskType().getMaxSum());
          if(cl.getPaymentSum().compareTo(old.getRiskType().getMaxSum())>0){
               throw new Exception("the sum is not in right range");
          }
          
              
              
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
