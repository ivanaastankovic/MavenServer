package rs.bg.ac.student.ivana.MavenServer.repository.db.impl;

import rs.bg.ac.student.ivana.MavenCommon.domain.DomainType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.bg.ac.student.ivana.MavenServer.repository.db.DBConnectionFactory;
import rs.bg.ac.student.ivana.MavenServer.repository.db.DBRepository;


public class RepositoryDBGeneric implements DBRepository<DomainType>{
     @Override
    public DomainType add(DomainType entity) throws Exception {
       try{
           Connection connection = DBConnectionFactory.getInstance().getConnection();
           StringBuilder sb = new StringBuilder();
           sb.append("INSERT INTO ").append(entity.getTableName())
                   .append(" (")
                   .append(entity.getColumnNamesForInsert())
                   .append(") ")
                   .append(" VALUES (")
                   .append(entity.getInsertValues())
                   .append(" ) ");
           String query = sb.toString();
           System.out.println(query);
           Statement statement = connection.createStatement();
           statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
           ResultSet rsKey = statement.getGeneratedKeys();
           if(rsKey.next()){
               Long id = rsKey.getLong(1);
               entity.setId(id);
               
           }
           statement.close();
           rsKey.close();
           return entity;
           
    
                   
       }catch(Exception e){
           throw e;
       }
    }
    
    @Override
    public List<DomainType> getAll(DomainType entity) throws Exception {
        List<DomainType> list=new ArrayList<>();
        try{
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from ")
                .append(entity.getTableName()).append(" ").append(entity.getJoinCondition());
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery(sb.toString());
        return entity.getRS(rs);
        
        }catch(Exception e){
            throw e;
            
        }
        
    }

    @Override
    public void edit(DomainType entity) throws Exception {
        try{
           Connection connection = DBConnectionFactory.getInstance().getConnection();

            String query = new StringBuilder()
                    .append("UPDATE ")
                    .append(entity.getTableName())
                    .append(" SET ")
                    .append(entity.getUpdateString(entity))
                    .toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            
            
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<DomainType> getAllBy(DomainType param, String field, String value) throws Exception {
        try{
        Connection connection = DBConnectionFactory.getInstance().getConnection();

        StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM ")
                .append(param.getTableName())
                .append(" ").append(param.getJoinCondition()).append(" ")
                .append(" WHERE ").append(field).append(" = '").append(value).append("'");
        String query = sb.toString();
            System.out.println(query);
        
        Statement s = connection.createStatement();
        ResultSet rs=s.executeQuery(query);
        return param.getRS(rs);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public void delete(DomainType entity) throws Exception {
        try{
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        
        String query = new StringBuilder()
                .append("DELETE FROM ")
                .append(entity.getTableName())
                .append(" WHERE ")
                .append(entity.getDeleteString())
                .toString();
        System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
        }catch(SQLIntegrityConstraintViolationException ex){
            throw new Exception("active type");
        }
        catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<DomainType> search(DomainType param) throws Exception {
        try{
        Connection connection = DBConnectionFactory.getInstance().getConnection();

        StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM ")
                .append(param.getTableName())
                .append(" ").append(param.getJoinCondition()).append(" ")
                .append(" WHERE ").append(param.getDeleteString());
        String query = sb.toString();
            System.out.println(query);
        
        Statement s = connection.createStatement();
        ResultSet rs=s.executeQuery(query);
        return param.getRS(rs);
        }catch(Exception e){
            throw e;
        }
    }
}
