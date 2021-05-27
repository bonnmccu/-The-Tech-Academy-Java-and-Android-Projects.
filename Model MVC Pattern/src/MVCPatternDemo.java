
public class MVCPatternDemo 
{
	public static void main(String[] args) 
	{
		Student model = retrieveStudentFromDatabase();//retrieves student record based on roll number
		StudentView view = new StudentView(); //creates a view on console
		StudentController controller = new StudentController(model,view);
		
		controller.updateView();
		controller.setStudentName ("John"); //update model
		controller.updateView();
		controller.setStudentName ("Jack"); 
		controller.updateView();
		controller.setStudentName ("Jason");
		controller.updateView();

	}

	private static Student retrieveStudentFromDatabase() 
	{
		Student student = new Student();
		student.setName("Robert");
		student.setRollNo("11");
		return student;

	}
	
}
