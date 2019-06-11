package callcenter.thread;

import callcenter.admin.*;
import callcenter.dto.EmployeeDTO;

public class EmployeeInCall extends Thread {
	
	private EmployeeDTO employee;
	private CallsControl callAdmin;
	private int callTime;
	private int callsCount;

	public int getCallsCount() {
		return callsCount;
	}


	public void setCallsCount(int callsCount) {
		this.callsCount = callsCount;
	}


	public EmployeeDTO getEmployee() {
		return employee;
	}


	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	
	public EmployeeInCall(EmployeeDTO employeeDTO, CallsControl callAdmin, int[] timeRange) {
		this.employee = employeeDTO;
		this.callAdmin = callAdmin;
		this.callTime = (int) (Math.random()*((timeRange[1] * 1000)-(timeRange[0] * 1000)+1)+(timeRange[0]*1000));
	}

	
	public void run() {
		int callId;
		while (callAdmin.areThereCallInQueue()) {
			try {
				callId=callAdmin.endCall();
				this.callsCount = this.callsCount +1;
				System.out.println("El empleado " + this.employee.getName() + " recibe la llamada " + callId);
				sleep(this.callTime);
				System.out.println("******** El empleado " + this.employee.getName() + " contestó la llamada " + callId + ", la llamada duró " + callTime / 1000 + " segundos.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
