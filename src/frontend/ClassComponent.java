package frontend;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import backend.Class;

public class ClassComponent extends ArrayList<JComponent>{

	private static final long serialVersionUID = 1L;
	private JLabel id = new JLabel("ID:-");
	private JLabel name = new JLabel("Name:-");
	private JLabel code = new JLabel("Code:-");
	private JLabel grade = new JLabel("Grade:-");
	private JLabel credits = new JLabel("Credits:-");

	private JTextField idField;
	private JTextField nameField;
	private JTextField codeField;
	private JTextField gradeField;
	private JTextField creditField;
	
	private boolean editable = true;

	public ClassComponent(int idNum){
		idField = new JTextField(String.valueOf(idNum));
		idField.setPreferredSize(new Dimension(50,20));
		idField.setEditable(false);

		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(50,20));

		codeField = new JTextField();
		codeField.setPreferredSize(new Dimension(50,20));

		gradeField = new JTextField();
		gradeField.setPreferredSize(new Dimension(50,20));

		creditField = new JTextField();
		creditField.setPreferredSize(new Dimension(50,20));

		this.addEverything();

	}

	public ClassComponent(Class thisComp, String mode){
		
		if(mode.equals("display")){
			editable = false;
		}
		
		idField = new JTextField(String.valueOf(thisComp.getIdNum()));
		idField.setPreferredSize(new Dimension(50,20));
		idField.setEditable(false);

		nameField = new JTextField(thisComp.getName(),thisComp.getName().length());
		nameField.setEditable(editable);

		codeField = new JTextField(thisComp.getCode(),thisComp.getCode().length());
		codeField.setEditable(editable);
		
		gradeField = new JTextField(Double.toString(thisComp.getGrade()));
		gradeField.setPreferredSize(new Dimension(50,20));
		gradeField.setEditable(editable);
		
		creditField = new JTextField(Double.toString(thisComp.getCredits()));
		creditField.setPreferredSize(new Dimension(50,20));
		creditField.setEditable(editable);
		
		this.addEverything();
	}

	private void addEverything(){
		
		this.add(id);
		this.add(idField);
		this.add(name);
		this.add(nameField);
		this.add(code);
		this.add(codeField);
		this.add(grade);
		this.add(gradeField);
		this.add(credits);
		this.add(creditField);
	}

	public int getId(){return Integer.valueOf(idField.getText());}
	public String getName(){return nameField.getText();}
	public String getCode(){return codeField.getText();}
	public double getGrade(){return Double.valueOf(gradeField.getText());}
	public double getCredits(){return Double.valueOf(creditField.getText());}
}
