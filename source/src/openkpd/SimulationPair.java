package openkpd;

import java.util.Arrays;
import java.util.List;

import edu.cmu.cs.dickerson.kpd.structure.VertexPair;
import edu.cmu.cs.dickerson.kpd.structure.types.BloodType;

public class SimulationPair extends VertexPair {
	
	/**
	 * 
	 */
	//Number of iterations in the pool
	private int iterations = 0;
	private int num_pairs; //number of pairs at instantiation
	private int num_alts; // number of altruists at instantiation
	
	public int DISPLAY_ID;
	
	
	//Extra features for UNOS policy
	//Already included is patient CPRA and donor/patient blood types
	
	//Continuous features
	public double age_don;
	public double age_cand;
	public double egfr;
	public double bmi_don;
	public double bp_systolic;
	public double weight_don;
	public double weight_cand;
	
	//Binary features
	public boolean isAfricanAmerican;
	public boolean isCigaretteUser;
	public boolean isDonorMale;
	public boolean isPatientMale;
	
	//HLA
	public double[] HLA_A_don = new double[2];
	public double[] HLA_B_don = new double[2];
	public double[] HLA_DR_don = new double[2];
	
	public double[] HLA_A_cand = new double[2];
	public double[] HLA_B_cand = new double[2];
	public double[] HLA_DR_cand = new double[2];
	
	//Lookup code for pairs.csv
	static List<String> dict =  Arrays.asList(new String[]{"ABO_CAND","ABO_DON","AFRICAN_AMERICAN","AGE_AT_ADD_CAND","AGE_AT_ADD_DON",
	                 "BMI_DON","BP_SYSTOLIC","CA1","CA2","CB1","CB2","CDR1","CDR2","CPRA_AT_MATCH_RUN",
	                 "DA1","DA2","DB1","DB2","DDR1","DDR2","EGFR","HCU","SEX_CAND","SEX_DON",
	                 "WEIGHT_CAND","WEIGHT_DON"});
	
	String[] donor_entries;
	
	public SimulationPair(int ID, String[] entries) {
		super(ID);
		DISPLAY_ID = ID;
		this.isWifePatient = false;
		
		this.bloodTypePatient = BloodType.getBloodType(entries[0]);
		this.bloodTypeDonor = BloodType.getBloodType(entries[1]);
		this.isAfricanAmerican = entries[2].equals("1") ? true : false;
		this.age_cand = Double.parseDouble(entries[3]);
		this.age_don = Double.parseDouble(entries[4]);
		this.bmi_don = Double.parseDouble(entries[5]);
		this.bp_systolic = Double.parseDouble(entries[6]);
		this.HLA_A_cand[0] = Double.parseDouble(entries[7]);
		this.HLA_A_cand[1] = Double.parseDouble(entries[8]);
		this.HLA_B_cand[0] = Double.parseDouble(entries[9]);
		this.HLA_B_cand[1] = Double.parseDouble(entries[10]);
		this.HLA_DR_cand[0] = Double.parseDouble(entries[11]);
		this.HLA_DR_cand[1] = Double.parseDouble(entries[12]);
		this.patientCPRA = Double.parseDouble(entries[13]);
		this.HLA_A_don[0] = Double.parseDouble(entries[14]);
		this.HLA_A_don[1] = Double.parseDouble(entries[15]);
		this.HLA_B_don[0] = Double.parseDouble(entries[16]);
		this.HLA_B_don[1] = Double.parseDouble(entries[17]);
		this.HLA_DR_don[0] = Double.parseDouble(entries[18]);
		this.HLA_DR_don[1] = Double.parseDouble(entries[19]);
		this.egfr = Double.parseDouble(entries[20]);
		this.isCigaretteUser = entries[21].equals("1") ? true : false;
		this.isPatientMale = entries[22].equals("1") ? true : false;
		this.isDonorMale = entries[23].equals("1") ? true : false;
		this.weight_cand = Double.parseDouble(entries[24]);
		this.weight_don = Double.parseDouble(entries[25]);
		
		donor_entries = new String[] {entries[1], entries[2], entries[4], entries[5], entries[6],
				entries[14], entries[15], entries[16], entries[17], entries[18], entries[19], entries[20],
				entries[21], entries[23], entries[25]};
		
	}
	
	public SimulationAltruist toSimulationAltruist() {
		return new SimulationAltruist(this.ID, donor_entries);
	}
	
	public void setCompatible(boolean ic) {
		isCompatible = ic;
	}
	
	public int getIterations() {
		return iterations;
	}
	
	public void increment() {
		iterations++;
	}
	
	public void reset() {
		iterations = 0;
	}
	
	public void set(int np, int na) {
		num_pairs = np;
		num_alts = na;
	}
	
	public int getPairs() {
		return num_pairs;
	}
	
	public int getAlts() {
		return num_alts;
	}
	
	public void setDisplay(int id) {
		DISPLAY_ID = id;
	}

}
