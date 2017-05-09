package backend;


import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class DocumentReader {

	private String docName;

	public DocumentReader(String name){
		docName = name;

	}
	
	public DocumentReader(File f){
		docName = f.getName();
	}

	public Semester getSemester(String name){
		try{

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(docName);


			Semester toReturn;
			ArrayList<Class> classesTaken = new ArrayList<Class>();
			NodeList semesterElements = doc.getElementsByTagName("semester");
			Node semester = semesterElements.item(0);
			boolean found = false;

			for(int count = 0; count < semesterElements.getLength(); count++){
				Node tempSemester = semesterElements.item(count);
				if(tempSemester.getAttributes().getNamedItem("id").getTextContent().equals(name)){
					semester = tempSemester;
					count = semesterElements.getLength();
					found = true;
				}
			}

			if(!found){return null;}

			NodeList classes = semester.getChildNodes();
			for(int count = 0; count < classes.getLength(); count++){
				Node cl = classes.item(count);
				NamedNodeMap classMap = cl.getAttributes();

				int idNum = Integer.valueOf(classMap.getNamedItem("internal_id").getTextContent());
				double credits = Double.valueOf(classMap.getNamedItem("credits").getTextContent());
				double grade = Double.valueOf(classMap.getNamedItem("grade").getTextContent());
				String className = classMap.getNamedItem("name").getTextContent();
				String code = classMap.getNamedItem("code").getTextContent();

				Class c = new Class(idNum,code,className,grade,credits);
				classesTaken.add(c);
			}

			toReturn = new Semester(classesTaken,name);
			return toReturn;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ArrayList<Semester> getAllSemesters(){
		
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(docName);
			
			NodeList semesters = doc.getElementsByTagName("semester");
			ArrayList<Semester> toReturn = new ArrayList<Semester>();
			
			for(int count = 0; count < semesters.getLength(); count++){
				
				Node semester = semesters.item(count);
				NamedNodeMap attributes = semester.getAttributes();
				String name = attributes.getNamedItem("id").getTextContent();
				
				Semester toAdd = this.getSemester(name);
				toReturn.add(toAdd);
			}
			return toReturn;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
}
