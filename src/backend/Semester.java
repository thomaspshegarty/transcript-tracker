package backend;

import java.util.ArrayList;

public class Semester {
	private ArrayList<Class> classesTaken;
	private ArrayList<String> names;
	private double gpa;
	private double credits;
	private String semester;
	
	public Semester(ArrayList<Class> inputClasses, String s){
		double cumulGrade = 0;
		credits = 0;
		semester = s;
		
		classesTaken = new ArrayList<Class>();
		names = new ArrayList<String>();
		
		for(Class c : inputClasses){
			credits = credits+c.getCredits();
			cumulGrade = cumulGrade + c.getGrade();
			names.add(c.getName());
			classesTaken.add(c);
		}
		
		gpa = cumulGrade/inputClasses.size();
	}
	
	public void addClass(Class c){
		credits = credits + c.getCredits();
		gpa = gpa + (c.getGrade()/c.getCredits());
		names.add(c.getName());
		classesTaken.add(c);
	}
	
	public double getGpa(){return gpa;}
	public double getCredits(){return credits;}
	public ArrayList<String> getNames(){return names;}
	public ArrayList<Class> getClasses(){return classesTaken;}
	public String getSemester(){return semester;}
}
