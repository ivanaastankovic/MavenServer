package rs.bg.ac.student.ivana.MavenServer.controller;

import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;
import rs.bg.ac.student.ivana.MavenServer.operation.claim.EditClaim;
import rs.bg.ac.student.ivana.MavenServer.operation.claim.GetAllByClient;
import rs.bg.ac.student.ivana.MavenServer.operation.claim.GetAllClaims;
import rs.bg.ac.student.ivana.MavenServer.operation.claim.SaveClaim;
import rs.bg.ac.student.ivana.MavenServer.operation.client.DeleteClient;
import rs.bg.ac.student.ivana.MavenServer.operation.client.GetAllByID;
import rs.bg.ac.student.ivana.MavenServer.operation.client.SaveClient;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.GetAllByClientContacts;
import rs.bg.ac.student.ivana.MavenServer.operation.clientContacts.SaveContact;
import rs.bg.ac.student.ivana.MavenServer.operation.login.LogIn;
import rs.bg.ac.student.ivana.MavenServer.operation.riskType.AddRiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.riskType.DeleteRiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.riskType.GetAllRiskTypes;
import rs.bg.ac.student.ivana.MavenServer.server.Server;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Controller {
    
     private static Controller instance;
    private Server server;
    private Map<String,Socket> activeClients;
    
    
    
    private Controller(){
        activeClients = new HashMap<>();
    }
    
    public static Controller getInstance(){
        if(instance==null){
            instance = new Controller();
        }
        return instance;
    }
    
    public void startServer(){
        server = new Server();
        server.start();
    }
    
    
     public void stopServer(){
        for(String username : activeClients.keySet()){
            System.out.println(username);
            try{
                activeClients.get(username).close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        activeClients.clear();
        server.close();
    }

    public Admin login(String username, String password, Socket socket) throws Exception {
         LogIn operation = new LogIn();
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        operation.execute(admin);
        admin = operation.getAdmin();
        if(activeClients.containsKey(admin.getUsername())){
            throw new Exception("This admin is already active!");
        }
        activeClients.put(admin.getUsername(), socket);
       return admin;
    }

    public Client addClient(Client client) throws Exception {
         SaveClient operation = new SaveClient();
        operation.execute(client);
        return operation.getClient();
    }

    public Claim addClaim(Claim claim) throws Exception {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	String str= sdf.format(new Date());
    	
    	claim.setFileDate(sdf.parse(str));				///// dodato
        SaveClaim operation = new SaveClaim();
        operation.execute(claim);
        return operation.getClaim();
    }

    public void deleteClient(Client client_d) throws Exception {
         AbstractGenericOperation operation = new DeleteClient();
        operation.execute(client_d);
    }

    public void deleteRiskType(RiskType riskType) throws Exception {
         AbstractGenericOperation operation = new DeleteRiskType();
        operation.execute(riskType);
    }

    public RiskType addRiskType(RiskType riskType) throws Exception {
        AddRiskType operation = new AddRiskType();
        operation.execute(riskType);
        return operation.getRiskType();
    }

    public void editClaim(Claim claim_e) throws Exception {
         EditClaim operation = new EditClaim();
        operation.execute(claim_e);
    }


    public List<Claim> getAllClaims() throws Exception {
          GetAllClaims operation = new GetAllClaims();
          operation.execute(new Claim());
          List<Claim> list=new ArrayList<>();
          list=operation.getList();
          return list;
    }

    public List<RiskType> getAllRiskTypes() throws Exception {
       GetAllRiskTypes operation = new GetAllRiskTypes();
          operation.execute(new RiskType());
          List<RiskType> list=new ArrayList<>();
          list=operation.getList();
          return list;
    }

    public List<Claim> getAllClaimsByJMBG(String jmbg) throws Exception {
        GetAllByClient operation = new GetAllByClient();
          Client client=new Client();
          client.setJmbg(jmbg);
          operation.setClient(client);
          operation.execute(new Claim());
          List<Claim> list=new ArrayList<>();
          list=operation.getList();
          list.get(0);
          return list;
    }

    public Client getClientByJMBG(String jmbg_c) throws Exception {
        try{
        GetAllByID operation=new GetAllByID();
      //  operation.setID(jmbg_c);
        Client c = new Client();
        c.setJmbg(jmbg_c);
        operation.execute(c);
        Client client=new Client();
        client=operation.getClient();
        client.setContacts(getAllContactsByClient(client));
        return client;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
            
    }

    public Claim getClaimByID(Long id) throws Exception {
    	rs.bg.ac.student.ivana.MavenServer.operation.claim.GetAllByID operation=new rs.bg.ac.student.ivana.MavenServer.operation.claim.GetAllByID();
         Claim claim=new Claim();
         claim.setClaimID(id);
        operation.execute(claim);
        claim=operation.getClaim();
        
        return claim;
    }
    
    
    public List<ClientContacts> getAllContactsByClient(Client client) throws Exception{
        List<ClientContacts> list=new ArrayList<>();
        ClientContacts cc=new ClientContacts();
        cc.setClient(client);
        GetAllByClientContacts operation=new GetAllByClientContacts();
        operation.execute(cc);
        list=operation.getList();
        return list;
    }

    public void logOutUser(String admin_logout) {
        activeClients.remove(admin_logout);
        System.out.println(activeClients.isEmpty());
    }
    
}
