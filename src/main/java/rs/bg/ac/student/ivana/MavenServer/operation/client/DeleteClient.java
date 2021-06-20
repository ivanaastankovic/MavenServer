package rs.bg.ac.student.ivana.MavenServer.operation.client;

import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class DeleteClient extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        Client cl=(Client) param;
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<ClientContacts> contacts=((Client)param).getContacts();
        try{
          repository.delete((Client)param);
          for (ClientContacts contact : contacts) {
            repository.delete(contact);
        }
        }catch(Exception ex){
            throw new Exception("active client");
        }
        
        
      
    }
    
}
