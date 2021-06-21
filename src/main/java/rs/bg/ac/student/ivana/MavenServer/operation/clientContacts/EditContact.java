package rs.bg.ac.student.ivana.MavenServer.operation.clientContacts;

import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja azurira vrednosti kontakta klijenta
 * @author Ivana
 *
 */
public class EditContact extends AbstractGenericOperation{

	
	/**
	 * Proverava da li telefon ili adresa vec postoje u kontaktima klijenta
	 * @param  param Object kao zahtev koji se salje
	 * @exception Ukoliko su telefon ili adresa vec u kontaktima klijenta
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
          ClientContacts cc=(ClientContacts)param;
          GetAllByClientContacts op=new GetAllByClientContacts();
          op.execute(cc);
        List<ClientContacts> list = op.getList();
        for(ClientContacts i : list){
          if(i.getClient().equals(cc.getClient()) && (!i.getContactID().equals(cc.getContactID())) && ((i.getAddress().equals(cc.getAddress()) && i.getTown().equals(cc.getTown()))|| i.getPhone().equals(cc.getPhone()))){
            throw new Exception("Phone or address is already in use by this client");
        }
        }
    }

    /**
     * Azurira informacije o kontaktu klijenta
     * @param  param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        ClientContacts cc=(ClientContacts)param;
        repository.edit(cc);
    }
    
}
