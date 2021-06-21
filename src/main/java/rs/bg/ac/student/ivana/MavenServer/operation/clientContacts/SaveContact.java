package rs.bg.ac.student.ivana.MavenServer.operation.clientContacts;

import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja cuva novi kontakt klijenta
 * @author Ivana
 *
 */
public class SaveContact extends AbstractGenericOperation{

	/**
	 * Proverava da li je kontakt klijenta vec dodat u bazu
	 * @param  param Object kao zahtev koji se salje
	 * @throws Exception Ukoliko kontakt klijenta vec postoji
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
          ClientContacts cc=(ClientContacts)param;
          GetAllByClientContacts op=new GetAllByClientContacts();
          op.execute(cc);
        List<ClientContacts> list = op.getList();
        for(ClientContacts i : list){
          if((i.getAddress().equals(cc.getAddress()) && i.getTown().equals(cc.getTown()))|| i.getPhone().equals(cc.getPhone())){
            throw new Exception("Phone or address is already in use by this client");
        }
        }
    }

    /**
     * Dodaje novi kontakt klijenta u bazu
     * @param  param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        ClientContacts cc=(ClientContacts)param;
         repository.add(cc);
    }
    
}
