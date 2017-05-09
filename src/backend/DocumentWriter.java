package backend;

import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DocumentWriter {

	private String docName;

	public DocumentWriter(String name) {
		docName = name;
		File testFile = new File(docName);

		if(!testFile.exists()){
			try{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();

				Element transcriptElement = doc.createElement("transcript");
				doc.appendChild(transcriptElement);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(docName));
				transformer.transform(source, result);

			}catch(Exception e){
				System.out.println("Exception "+e.getMessage()+" was thrown");
			}
		}
	}

	public void writeSemester(Semester s){

		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(docName);

			boolean set = false;

			NodeList tempSemester = doc.getElementsByTagName("semester");
			for(int temp = 0; temp < tempSemester.getLength(); temp ++){
				Node possibleSem = tempSemester.item(temp);
				NamedNodeMap attr = possibleSem.getAttributes();
				Node nodeAttr = attr.getNamedItem("id");
				if(nodeAttr.getTextContent().equals(s.getSemester())){
					editSemester(s);
					set = true;
				}
			}

			if(!set){
				addSemester(s);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}

	public void addSemester(Semester s){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(docName);

			Node transcript = doc.getFirstChild();
			Element semester;
			semester = doc.createElement("semester");
			transcript.appendChild(semester);

			Attr id = doc.createAttribute("id");
			id.setValue(s.getSemester());
			semester.setAttributeNode(id);

			Attr gpa = doc.createAttribute("gpa");
			gpa.setValue(String.valueOf(s.getGpa()));
			semester.setAttributeNode(gpa);

			Attr credits = doc.createAttribute("credits");
			credits.setValue(String.valueOf(s.getCredits()));
			semester.setAttributeNode(credits);

			ArrayList<Class> classesTaken = s.getClasses();
			for(Class c : classesTaken){
				Element classTaken = doc.createElement("class");
				semester.appendChild(classTaken);

				Attr idNum = doc.createAttribute("internal_id");
				idNum.setValue(String.valueOf(c.getIdNum()));
				classTaken.setAttributeNode(idNum);
				
				Attr name = doc.createAttribute("name");
				name.setValue(c.getName());
				classTaken.setAttributeNode(name);

				Attr code = doc.createAttribute("code");
				code.setValue(c.getCode());
				classTaken.setAttributeNode(code);
				
				Attr credVal = doc.createAttribute("credits");
				credVal.setValue(String.valueOf(c.getCredits()));
				classTaken.setAttributeNode(credVal);

				Attr grade = doc.createAttribute("grade");
				grade.setValue(String.valueOf(c.getGrade()));
				classTaken.setAttributeNode(grade);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(docName));
			transformer.transform(source, result);

		}catch(Exception e){

			System.out.println("Exception "+e.getMessage()+" was thrown");

		}
	}
	
	public void editSemester(Semester s){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(docName);

			NodeList semesterList = doc.getElementsByTagName("semester");
			Node semester = semesterList.item(0);
			NamedNodeMap semesterMap = doc.getAttributes();
			
			for(int counter = 0; counter < semesterList.getLength(); counter++){
				Node tempSemester = semesterList.item(counter);
				NamedNodeMap tempMap = tempSemester.getAttributes();
				Node tempAttr = tempMap.getNamedItem("id");
				if(tempAttr.getTextContent().equals(s.getSemester())){
					semester = tempSemester;
					semesterMap = tempMap;
					counter = semesterList.getLength();
				}
			}
			

			Node gpa = semesterMap.getNamedItem("gpa");
			gpa.setTextContent(String.valueOf(s.getGpa()));

			Node credits = semesterMap.getNamedItem("credits");
			credits.setTextContent(String.valueOf(s.getCredits()));

			ArrayList<Class> classesTaken = s.getClasses();
			NodeList classNodes = semester.getChildNodes();
			for(Class c : classesTaken){
				Node classTakenNode = semester.getFirstChild();
				boolean found = false;
				for(int counter = 0; counter < classNodes.getLength(); counter++){
					Node tempNode = classNodes.item(counter);
					if(c.getIdNum() == Integer.valueOf(tempNode.getAttributes().getNamedItem("internal_id").getTextContent())){
						classTakenNode = tempNode;
						found = true;
					}
				}
				if(!found){
					Element classTaken = doc.createElement("class");
					semester.appendChild(classTaken);

					Attr idNum = doc.createAttribute("internal_id");
					idNum.setValue(String.valueOf(c.getIdNum()));
					classTaken.setAttributeNode(idNum);
					
					Attr name = doc.createAttribute("name");
					name.setValue(c.getName());
					classTaken.setAttributeNode(name);

					Attr code = doc.createAttribute("code");
					code.setValue(c.getCode());
					classTaken.setAttributeNode(code);
					
					Attr credVal = doc.createAttribute("credits");
					credVal.setValue(String.valueOf(c.getCredits()));
					classTaken.setAttributeNode(credVal);

					Attr grade = doc.createAttribute("grade");
					grade.setValue(String.valueOf(c.getGrade()));
					classTaken.setAttributeNode(grade);
				}else{
					NamedNodeMap classAttr = classTakenNode.getAttributes();
					
					Node name = classAttr.getNamedItem("name");
					name.setTextContent(c.getName());
					
					Node code = classAttr.getNamedItem("code");
					code.setTextContent(c.getCode());
					
					Node credVal = classAttr.getNamedItem("credits");
					credVal.setTextContent(Double.toString(c.getCredits()));
					
					Node grade = classAttr.getNamedItem("grade");
					grade.setTextContent(Double.toString(c.getGrade()));
				}
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(docName));
			transformer.transform(source, result);

		}catch(Exception e){

			System.out.println("Exception "+e.getMessage()+" was thrown");

		
		}
		
	}

	public String getDocName(){return docName;}
}
