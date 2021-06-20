package rs.bg.ac.student.ivana.MavenServer.thread;

import rs.bg.ac.student.ivana.MavenCommon.communication.Operation;
import rs.bg.ac.student.ivana.MavenCommon.communication.Receiver;
import rs.bg.ac.student.ivana.MavenCommon.communication.Request;
import rs.bg.ac.student.ivana.MavenCommon.communication.Response;
import rs.bg.ac.student.ivana.MavenCommon.communication.Sender;
import rs.bg.ac.student.ivana.MavenServer.controller.Controller;
import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunication extends Thread{
    
    private Socket socket;
    private boolean signal;

    public ServerCommunication(Socket socket) {
        this.socket = socket;
        signal=false;
    }

    @Override
    public void run() {
        try{
            Receiver receiver = new Receiver(socket);
            Sender sender = new Sender(socket);
            while(!socket.isClosed() && socket.isConnected()){
                Response response = new Response();
                Request request = (Request) receiver.receive();
                
                try{
                    switch(request.getOperation()){
                       
                        case LOGIN:
                            Admin admin = (Admin) request.getArgument();
                            admin = Controller.getInstance().login(admin.getUsername(), admin.getPassword(),socket);
                            response.setResult(admin);
                            break;
                      
                         case LOGOUT:
                            String admin_logout = (String) request.getArgument();
                            Controller.getInstance().logOutUser(admin_logout);

                            break;
                            
                            
                        case ADD_CLIENT:
                            Client client=(Client) request.getArgument();
                            Client newClient=Controller.getInstance().addClient(client);
                            response.setResult(newClient);
                            break;
                        case ADD_CLAIM:
                            Claim claim=(Claim) request.getArgument();
                            Claim newClaim=Controller.getInstance().addClaim(claim);
                            response.setResult(newClaim);
                            break;
                        case ADD_RISKTYPE:
                             RiskType riskType=(RiskType) request.getArgument();
                            RiskType newRiskType=Controller.getInstance().addRiskType(riskType);
                            response.setResult(newRiskType);
                            break;
                        case DELETE_CLIENT:
                            Client client_d=(Client) request.getArgument();
                            Controller.getInstance().deleteClient(client_d);
                            break;
                        case DELETE_RISKTYPE:
                            RiskType riskType_d=(RiskType) request.getArgument();
                            Controller.getInstance().deleteRiskType(riskType_d);
                            break;
                        case EDIT_CLAIM:
                            Claim claim_e=(Claim) request.getArgument();
                            Controller.getInstance().editClaim(claim_e);
                            break;
                        case EDIT_CLIENT:
                            Client client_e=(Client) request.getArgument();
                            System.out.println(client_e.getClientID());
                            Controller.getInstance().editClient(client_e);
                            break;
                        case GETALL_CLAIMS:
                            response.setResult(Controller.getInstance().getAllClaims());
                            break;
                        case GETALL_RISKTYPES:
                            response.setResult(Controller.getInstance().getAllRiskTypes());
                            break;
                        case GETALLBYCLIENT_CLAIMS:
                            String jmbg=(String) request.getArgument();
                            response.setResult(Controller.getInstance().getAllClaimsByJMBG(jmbg));
                            break;
                        case GETBYID_CLAIM:
                            Long id=(Long) request.getArgument();
                            response.setResult(Controller.getInstance().getClaimByID(id));
                            break;
                        case GETBYID_CLIENT:
                            String jmbg_c=(String) request.getArgument();
                            response.setResult(Controller.getInstance().getClientByJMBG(jmbg_c));
                            break;
                    }
                }catch(Exception e){
                    response.setException(e);
                }
                sender.send(response);
            }
        }catch(EOFException exception){
            System.out.println("client has left..");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
