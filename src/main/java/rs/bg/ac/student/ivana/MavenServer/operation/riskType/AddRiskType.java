package rs.bg.ac.student.ivana.MavenServer.operation.riskType;

import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenServer.operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja dodaje novi tip rizika
 * @author Ivana
 *
 */
public class AddRiskType extends AbstractGenericOperation {
	/**
	 * RiskType kao instanca klase RiskType
	 */
      private RiskType riskType;
    @Override
    protected void preconditions(Object param) throws Exception {
         
    }

    /**
     * Dodaje novi tip rizika u bazu podataka
     * @param param Object kao zahtev koji se salje
     * @throws Exception ako je prosledjeni tip rizika null
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        RiskType rt = (RiskType) param;
        riskType=(RiskType) repository.add(rt);
        if(riskType==null){
            throw new Exception("risk type can not be saved");
        }
        
    }   

    /**
     * Vraca instancu klase RiskType
     * @return RiskType kao instanca klase RiskType
     */
    public RiskType getRiskType() {
        return riskType;
    }
    
    
}
