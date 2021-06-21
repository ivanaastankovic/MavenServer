package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import java.util.ArrayList;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve zalbe
 * @author Ivana
 *
 */
public class GetAllClaims extends AbstractGenericOperation {
	/**
	 * List kao lista instanci klase Claim
	 */
    private List<Claim> list;
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }
    
    /**
     * Vraca listu instanci klase Claim iz baze
     * @param  param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list=new ArrayList<>();
        list=repository.getAll((Claim)param);
    }

    /**
     * Vraca listu instanci klase Claim
     * @return List kao lista instanci klase Claim
     */
    public List<Claim> getList() {
        return list;
    }
    
    
    
}
