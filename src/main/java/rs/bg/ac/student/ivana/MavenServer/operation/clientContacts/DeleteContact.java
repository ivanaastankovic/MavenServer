package rs.bg.ac.student.ivana.MavenServer.operation.clientContacts;

import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;


public class DeleteContact extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        GetAllByClientContacts op=new GetAllByClientContacts();
        op.executeOperation(param);
        List<ClientContacts> list=op.getList();
        if(list.size()<=1){
            throw new Exception("only one contact available");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        ClientContacts cc=(ClientContacts) param;
        repository.delete(cc);
    }
    
}
