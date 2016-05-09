package subjects;

import java.util.ArrayList;
import java.util.List;

import observers.IObserver;
import domain.Employee;
import domain.EmployeeDAO;

/**
 * This is the subject that should be able to notify the list of observers that
 * care for employee changes.
 * 
 * @author Screencasts
 * 
 */
public class EmployeeManagementSystem implements ISubject {

	private Employee emp;
	private String msg= null;
	private List<IObserver> observers;
//	private Map<String,List<IObserver>> observersMap; //¶à¸öoberver
	
	private List<Employee> employees;
	private EmployeeDAO employeeDAO;

	public EmployeeManagementSystem() {
		observers = new ArrayList<IObserver>();
		//observersMap = new HashMap<String, List<Event>>(); 
		employeeDAO = new EmployeeDAO();
		employees = employeeDAO.generateEmployees();
	}

	public void modifyEmployeeName(int id, String newName) {
		boolean notify = false;
		for (Employee emp : employees) {
			if (id == emp.employeeID) {
				emp.setName(newName);
				this.emp = emp;
				this.msg = "Employee Name Changed";
				notify = true;
			}
		}
		if(notify)
			notifyObservers();
	}

	public void hireNewEmployee(Employee emp) {
		this.emp = emp;
		this.msg = "New Hire";
		employees.add(emp);
		notifyObservers();
	}
	
	@Override
	public void registerObserver(IObserver addMe) {
		// the observers added to this list are interested in getting notified.
		observers.add(addMe);
	}
	
//	public void register(String eventName, IObserver event) {
//    if (observersmap.containsKey(eventName)) {
//        observersmap.get(eventName).add(event);
//      } else {
//        List<Event> events = new ArrayList<Event>();
//        events.add(event);
//        observersmap.put(eventName, events);
//      }
//    };

	@Override
	public void removeObserver(IObserver removeMe) {
		int removeIndex = observers.indexOf(removeMe);
		observers.remove(removeIndex);
	}
	
//	public void removeObserver(String eventName, IObserver removeMe) {
//	if (observersmap.containsKey(eventName)) {
//	      observersmap.get(eventName).remove(removeMe);
//	    }
//	}


	public void notifyObservers() {
		for (IObserver department : observers) {
			department.callMe(emp, msg);		
			}
	}
	
//	public void notifyObservers(String eventName) {
//	    if(observersmap.containsKey(eventName))
//		for (IObserver department : observersmap.get(eventName)) {
//			department.callMe(emp, msg);		
//			}
//	}
}
