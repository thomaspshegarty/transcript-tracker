package backend;

import java.util.ArrayList;

public class ModuleTester {
	
	public static void main(String[] args){
		DocumentWriter dWriter = new DocumentWriter("bob_transcript.xml");
		DocumentReader dReader = new DocumentReader("bob_transcript.xml");
		System.out.println(dWriter.getDocName());
		
		Class a = new Class(1,"MATH241","Calc III",3.0,4);
		Class b = new Class(2,"CMSC132","OOP II",2.7,4);
		Class c = new Class(3,"CHIN101","Introductory Chinese",4.0,6);
		Class d = new Class(4,"CPSS100","STS Colloquium",3.0,2);
		
		ArrayList<Class> firstSemester = new ArrayList<Class>();
		firstSemester.add(a);
		firstSemester.add(b);
		firstSemester.add(c);
		firstSemester.add(d);
		
		Semester firstSemesterObject = new Semester(firstSemester, "Fall 2017");
		dWriter.writeSemester(firstSemesterObject);
		
		Semester retrievedSemester = dReader.getSemester("Fall 2017");
		for(Class prinClass : retrievedSemester.getClasses()){
			prinClass.printClass();
		}
	}
	
}
