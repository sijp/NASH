import java.util.Iterator;
import java.util.Vector;

import dataStructure.Employee;


public class Driver {

	public static void main(String args[]){
		XMLParser xmlParser = new XMLParser("src/employees.xml");
		xmlParser.parseXmlFile();
		xmlParser.parseDocument();
		Vector<Employee> employees = xmlParser.getParsedXML();
		
		printData(employees);
		
	}
	
	
	private static void printData(Vector<Employee> employees){

		System.out.println("Number of Employees '" + employees.size() + "'.");

		Iterator<Employee> it = employees.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}
