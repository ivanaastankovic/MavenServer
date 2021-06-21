package rs.bg.ac.student.ivana.MavenServer.operation;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenServer.repository.Repository;
import rs.bg.ac.student.ivana.MavenServer.repository.db.DBRepository;
import rs.bg.ac.student.ivana.MavenServer.repository.db.impl.RepositoryDBGeneric;

/**
 * Apstraktna klasa koju nasledjuju sve sistemske operacije
 * @author Ivana
 *
 */
public abstract class AbstractGenericOperation {
	/**
	 * Repozitorijum za komunikaciju sa bazom podataka
	 */
     protected final Repository repository;
     
     /**
      * Neparametrizovana konstruktor metoda za inicijalizaciju repozitorijuma
      */
    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }
    /**
     * Komunicira sa bazom; Zapocinje transakciju, izvrsava operaciju, ukoliko su provereni uslovi zadovoljeni,
     * cuva promene ili ih odbacuje i zatvara transakciju
     * @param param Object koji predstavlja objekat zahteva za izvrsenje transakcije
     * @throws Exception Ukoliko neki od uslova nije zadovoljen 
     */
    public final void execute(Object param) throws Exception {
        try {
            startTransaction();
            preconditions(param);
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }
     /**
      * Zapocinje transakciju nad bazom podataka
      * @throws Exception Ako se pri konekciji sa bazom javi greska
      */
    private void startTransaction() throws Exception {
        ((DBRepository) repository).connect();
    }

    // Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;

    /**
     * Potvrdjuje izmene nad bazom podataka
     * @throws Exception Ukoliko se desi greska sa konekcijom nad bazom
     */
    private void commitTransaction() throws Exception {
        ((DBRepository) repository).commit();
    }
    
    /**
     * Ponistava transakciju
     * @throws Exception Ukoliko se desi greska sa konekcijom nad bazom
     */
    private void rollbackTransaction() throws Exception {
        ((DBRepository) repository).rollback();
    }
    
    /**
     * Prekida konekciju nad bazom podataka
     * @throws Exception Ukoliko se desi greska sa konekcijom nad bazom
     */
    private void disconnect() throws Exception {
        ((DBRepository) repository).disconnect();
    }
    
    /**
     * Vraca objekat klase zalba
     * @return Claim kao objekat klase zalba
     * @throws UnsupportedOperationException ukoliko metoda nije podrzana
     */
    public Claim getClaim() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
