package backend;

public class Class {
	private double credits;
	private double grade;
	private String name;
	private String code;
	private int idNum;
	
	public Class(int inNum, String inputCode, String inputName, double inputGrade, double inputCredits){
		name = inputName;
		credits = inputCredits;
		grade = inputGrade;
		code = inputCode;
		idNum = inNum;
	}
	
	public String getName(){return name;}
	public double getCredits(){return credits;}
	public double getGrade(){return grade;}
	public String getCode(){return code;}
	public int getIdNum(){return idNum;}
	public void printClass(){
		System.out.println("Name: "+name);
		System.out.println("Code: "+code);
		System.out.println("Grade: "+grade);
		System.out.println("Credits: "+credits);
		System.out.println("-----------");
	}
}
