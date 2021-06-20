package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import java.math.BigDecimal;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class EditClaim extends AbstractGenericOperation{

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

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Claim)param);
         
         
    }
    
}
