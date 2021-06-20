package rs.bg.ac.student.ivana.MavenServer.operation;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenServer.repository.Repository;
import rs.bg.ac.student.ivana.MavenServer.repository.db.DBRepository;
import rs.bg.ac.student.ivana.MavenServer.repository.db.impl.RepositoryDBGeneric;


public abstract class AbstractGenericOperation {
     protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

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

    private void startTransaction() throws Exception {
        ((DBRepository) repository).connect();
    }

    // Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((DBRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DBRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DBRepository) repository).disconnect();
    }

    public Claim getClaim() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
