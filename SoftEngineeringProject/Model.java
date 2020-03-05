
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Model {
	/*
	 * this is the model part of the MVC architecture we have implemented
	 */
	private PTTDirector pttDirector;
	private ArrayList<ClassDirector> classDirector;
	private Administrator admin;
	private ArrayList<Staff> staff;
	private ArrayList<CandidateEmployee> employees;
	public Model() throws ClassNotFoundException{
		staff=new ArrayList<Staff>();
		classDirector=new ArrayList<ClassDirector>();
		//read the objects from file
		try {
			FileInputStream is=null;
			ObjectInputStream ois=null;
			is = new FileInputStream("data.ser");
			try {
			ois = new ObjectInputStream(is);
			}catch(IOException e) {
			}finally {
		
			if(ois!=null)
			{
			TeachingRequirements.read(ois);
			PTTDirector.read(ois);
			Administrator.read(ois);
			Decision.read(ois);
			
			classDirector=(ArrayList<ClassDirector>) ois.readObject();
			if(classDirector==null)
				classDirector=new ArrayList<ClassDirector>();
						if(classDirector==null)
						{classDirector=new ArrayList<ClassDirector>();}
				ois.close();		
				}
			}
		}catch (IOException e) {
			System.out.print("With a new data file\n");
		}
		initializeStaff();
	}
	public void initializeStaff() {
		staff.add(SystemAdmin.getInstance());
		staff.add(PTTDirector.getInstance());
		staff.add(Administrator.getInstance());
		staff.addAll(classDirector);
	}

	public PTTDirector getPttDirector() {
		return pttDirector;
	}
	public Administrator getAdmin() {
		return admin;
	}
	public ArrayList<Staff> getStaff() {
		return staff;
	}
	public ArrayList<ClassDirector> getCD() {
		return classDirector; 
	}
	
	//reset the data of file
	public void resetData() {
		try {
			FileOutputStream f = new FileOutputStream(new File("data.ser"));
			f.write("".getBytes());
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Reset Done");
		}finally {
			getStaff().removeAll(classDirector);
			classDirector.clear();
			Administrator.getInstance().getCandidates().clear();
		}
		
	}
	
	//update the data of file
	public void update() {
		try {
			FileOutputStream f = new FileOutputStream(new File("data.ser"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(TeachingRequirements.getInstance());
			o.writeObject(PTTDirector.getInstance());
			o.writeObject(Administrator.getInstance());
			o.writeObject(Decision.getInstance());
			o.writeObject(classDirector);
			o.flush();
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
}