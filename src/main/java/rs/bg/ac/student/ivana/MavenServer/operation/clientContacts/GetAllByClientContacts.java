package rs.bg.ac.student.ivana.MavenServer.operation.clientContacts;

import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.ArrayList;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve kontakte klijenta
 * @author Ivana
 *
 */
public class GetAllByClientContacts extends AbstractGenericOperation{
	/**
	 * List kao lista kontakata klijenta
	 */
    private List<ClientContacts> list;
    @Override
    protected void preconditions(Object param) throws Exception {
       
    }

    /**
     * Vraca sve kontakte klijenta
     * @param  param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
      ClientContacts cc=(ClientContacts)param;
      list=new ArrayList<>();
      System.out.println(cc.getClient().getClientID());
      list=repository.getAllBy(cc, " clientID ", cc.getClient().getClientID()+"");
    }

    /**
     * Vraca listu kontakata klijenta
     * @return List kao lista kontakata klijenta
     */
    public List<ClientContacts> getList() {
        return list;
    }
    
    
    
}
