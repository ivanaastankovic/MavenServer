package rs.bg.ac.student.ivana.MavenServer.repository.db;

import rs.bg.ac.student.ivana.MavenServer.repository.Repository;

public interface DBRepository<T> extends Repository<T>{
    default public void connect() throws Exception{
        DBConnectionFactory.getInstance().getConnection();
    }
    
    default public void disconnect() throws Exception{
        DBConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void commit() throws Exception{
        DBConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws Exception{
        DBConnectionFactory.getInstance().getConnection().rollback();
    }
}
