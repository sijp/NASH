package dataStructure;

public class Employee {
	private String fName;
	private int fAge;
	private int fId;
	private String fType;
	
	public Employee(String name, int age, int id, String type){
		fName = name;
		fAge = age;
		fId = id;
		fType = type;
	}
	
	public String toString(){
		return "Employee\n\tName:" + fName + "\n\tAge:" + fAge + "\n\tId:" + fId + "\n\tType:" + fType + "\n"; 
	}
	
}
