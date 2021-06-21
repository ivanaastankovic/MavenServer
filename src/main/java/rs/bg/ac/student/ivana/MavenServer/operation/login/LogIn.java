package rs.bg.ac.student.ivana.MavenServer.operation.login;

import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija za prijavu korisnika na sistem
 * @author Ivana
 *
 */
public class LogIn extends AbstractGenericOperation{
	/**
	 * Admin kao objekat klase Admin
	 */
    private Admin admin;
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    /**
     * Metoda koja proverava da li admin postoji u bazi podataka
     * @param  param Object kao zahtev koji se salje
     * @throws Exception Ukoliko admin ne postoji u bazi podataka
     */
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
    
    /**
     * Metoda koja vraca instancu klase Admin
     * @return Admin kao objekat klase Admin
     */
    public Admin getAdmin() {
        return admin;
    }
    
    
    
    
}
