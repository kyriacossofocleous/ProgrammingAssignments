import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/*
 * this class provides a link between the administrator and PTT director, it is a singleton, because there should be only one of it in the program
 */
public class Decision implements Serializable{
	private static final long serialVersionUID = 1L;
	private HashMap<CandidateEmployee, String> approvals; //proposals approved by the PTT director
	private HashMap<CandidateEmployee, String> proposals; //proposals set forth by the administrator - candidate and the requirement the might satisfy
	private static Decision decision = null;
	
	/*
	 * needed for serialisation
	 */	
	private Decision() {
		approvals = new HashMap<CandidateEmployee , String>();
		proposals = Administrator.getInstance().getProposals();
	}
	
	public static synchronized void read(ObjectInputStream in){

        try{

        	decision = (Decision)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static Decision getInstance() {
		if (decision == null) {
			decision = new Decision();
		}
		return decision;
	}
	public HashMap<CandidateEmployee, String> getApprovals() {
		return approvals;
	}
	
	/**
	 * checks the entries in proposals and if an entry's key, being a candidate, is approved it puts this entry in the approvals hash map 	
	 */
	public void updateApprovals() {
		for(Entry<CandidateEmployee, String> entry: proposals.entrySet()) {
			if (entry.getKey().isApproved()) {
				approvals.put(entry.getKey(), entry.getValue());
			}
		}
		for(CandidateEmployee c: approvals.keySet()) {
			System.out.println(c.toString());
		}
		
	}
/**
 * removes proposals that are already in the approvals hashmap form the proposals hashmap
 */
	public void updateProposals() {
		for(Entry<CandidateEmployee, String> entry: approvals.entrySet()) {
			if (entry.getKey().isApproved()) {
				proposals.remove(entry.getKey(), entry.getValue()); //assures only this tuple is removed, in case the candidate was proposed for another position
			}
		}
	}
	public HashMap<CandidateEmployee, String> getProposals() {
		return proposals;
	}

	public void removeProposal(int index) {
		ArrayList<CandidateEmployee> toBeRemoved=new ArrayList<CandidateEmployee>();
		for(CandidateEmployee c: proposals.keySet()) {
			if(c.getID()==index) {
				toBeRemoved.add(c);
			}
		}
		for(CandidateEmployee c:toBeRemoved) {
			proposals.remove(c);
		}
	}
	
	/**
	 * sets the CandidateEmployee's, whose ID is passed, approved boolean to true 
	 * @param index - ID of a CandidateEmployee
	 */

	public void approve(int index) {
		for(CandidateEmployee c: proposals.keySet()) {
			if(c.getID()==index) {
				c.setApproved(true);
			}
		}
		
	}
}
