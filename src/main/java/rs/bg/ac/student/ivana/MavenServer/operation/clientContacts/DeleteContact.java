package rs.bg.ac.student.ivana.MavenServer.operation.clientContacts;

import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja brise kontakt klijenta
 * @author Ivana
 *
 */
public class DeleteContact extends AbstractGenericOperation {
	
	/**
	 * Proverava broj kontakata klijenta
	 * @param  param Object kao zahtev koji se salje
	 * @throws Exception Ukoliko klijent poseduje samo jedan kontakt
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
        GetAllByClientContacts op=new GetAllByClientContacts();
        op.executeOperation(param);
        List<ClientContacts> list=op.getList();
        if(list.size()<=1){
            throw new Exception("only one contact available");
        }
    }

    /**
     * Brise kontakt klijenta
     * @param param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        ClientContacts cc=(ClientContacts) param;
        repository.delete(cc);
    }
    
}
