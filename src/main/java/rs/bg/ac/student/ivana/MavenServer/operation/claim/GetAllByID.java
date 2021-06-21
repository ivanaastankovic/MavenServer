package rs.bg.ac.student.ivana.MavenServer.operation.claim;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve zalbe sa zadatim ID-em
 * @author Ivana
 *
 */
public class GetAllByID extends AbstractGenericOperation{
	/**
	 * Long kao id zalbe
	 */
    private Long id;
    /**
     * List kao lista instanci klase Claim
     */
    private List<Claim> list;

    @Override
    protected void preconditions(Object param) throws Exception {
      
    }

    /**
     * Vraca sve objekte klase Claim sa zadatim ID-em iz baze
     * @param  param Object kao zahtev koji se salje
     * @throws Exception Ukoliko ne postoji zalba sa zadatim ID-em
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list=repository.getAllBy((Claim)param, " c.id ", ((Claim)param).getClaimID()+" ");
        if(list.isEmpty()){
            throw new Exception("no claim with this id");
        }
        
    }

    /**
     *  Vraca listu objekata klase Claim
     *  @return List kao lista objekata klase Claim
     */
    public Claim getClaim() {
        return list.get(0);
    }

    /**
     * Postavlja ID na novu vrednost
     * @param id Long kao ID 
     */
    public void setID(Long id) {
        this.id = id;
    }
    
    
    
    
}
