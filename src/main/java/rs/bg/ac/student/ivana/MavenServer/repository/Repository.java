
package rs.bg.ac.student.ivana.MavenServer.repository;

import java.util.List;


public interface Repository<T>{
    List<T> getAll(T param) throws Exception;
    T add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;
    List<T> getAllBy(T param, String field, String value) throws Exception;
    List<T> search(T param) throws Exception;
}
