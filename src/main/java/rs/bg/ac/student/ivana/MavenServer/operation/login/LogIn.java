package rs.bg.ac.student.ivana.MavenServer.operation.login;

import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

public class LogIn extends AbstractGenericOperation{
    private Admin admin;
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       List<Admin> list=repository.getAll(new Admin());
       Admin a=(Admin) param;
       
        for (Admin ad : list) {
            if(ad.equals(a)){
                admin=ad;
                return;
            }
        }
        throw new Exception("wrong credentials");
        
    }

    public Admin getAdmin() {
        return admin;
    }
    
    
    
    
}
