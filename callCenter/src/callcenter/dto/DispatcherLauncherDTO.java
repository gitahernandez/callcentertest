package callcenter.dto;

public class DispatcherLauncherDTO {
	
	private int callsQuantity;
	private int operatorsQuantity;
	private int supervisorsQuantity; 
	private int managersQuantity; 
	private int[] timeRange;
	
	public int getCallsQuantity() {
		return callsQuantity;
	}
	public void setCallsQuantity(int callsQuantity) {
		this.callsQuantity = callsQuantity;
	}
	public int getOperatorsQuantity() {
		return operatorsQuantity;
	}
	public void setOperatorsQuantity(int operatorsQuantity) {
		this.operatorsQuantity = operatorsQuantity;
	}
	public int getSupervisorsQuantity() {
		return supervisorsQuantity;
	}
	public void setSupervisorsQuantity(int supervisorsQuantity) {
		this.supervisorsQuantity = supervisorsQuantity;
	}
	public int getManagersQuantity() {
		return managersQuantity;
	}
	public void setManagersQuantity(int managersQuantity) {
		this.managersQuantity = managersQuantity;
	}
	public int[] getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(int[] timeRange) {
		this.timeRange = timeRange;
	}
	
}