package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.DeleteContact;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.EditContact;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.GetAllByClientContacts;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.SaveContact;


/**
 * Sistemska operacija koja azurira informacije o klijentu
 * @author Ivana
 *
 */
public class EditClient extends AbstractGenericOperation{
   
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }
    /**
     * Metoda koja azurira informacije o klijentu
     * @param  param Object kao zahtev koji se salje
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Client c=(Client) param;
        GetAllByClientContacts op=new GetAllByClientContacts();
        ClientContacts cc=new ClientContacts();
        cc.setClient(c);
       System.out.println(cc.getClient().getClientID());
        op.execute(cc);
        List<ClientContacts> list=op.getList();
        
       
            for (ClientContacts i : list) {
                  if(!c.getContacts().contains(i)){
                       DeleteContact dc=new DeleteContact();
                      dc.execute(i);
                  }
            }

        
        for (ClientContacts i : c.getContacts()) {
                if(i.getContactID()==null){
                    SaveContact op_two=new SaveContact();
                    op_two.execute(i);
                }else{
                     EditContact op_tr=new EditContact();
                    op_tr.execute(i);
                }
        }
            }
        }
        
        
    
    

