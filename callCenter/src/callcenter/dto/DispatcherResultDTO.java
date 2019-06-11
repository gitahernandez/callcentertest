package callcenter.dto;

public class DispatcherResultDTO {
	
	private int ProccesedCallsQuantity ;
	private int ProccesedCallsQuantityByManagerRole;
	private int ProccesedCallsQuantityBySupervisorRole;
	private int ProccesedCallsQuantityByOperatorRole;

	public int getProccesedCallsQuantityByOperatorRole() {
		return ProccesedCallsQuantityByOperatorRole;
	}

	public void setProccesedCallsQuantityByOperatorRole(int proccesedCallsQuantityByOperatorRole) {
		ProccesedCallsQuantityByOperatorRole = proccesedCallsQuantityByOperatorRole;
	}

	public int getProccesedCallsQuantityBySupervisorRole() {
		return ProccesedCallsQuantityBySupervisorRole;
	}

	public void setProccesedCallsQuantityBySupervisorRole(int proccesedCallsQuantityBySupervisor) {
		ProccesedCallsQuantityBySupervisorRole = proccesedCallsQuantityBySupervisor;
	}

	public int getProccesedCallsQuantityByManagerRole() {
		return ProccesedCallsQuantityByManagerRole;
	}

	public void setProccesedCallsQuantityByManagerRole(int proccesedCallsQuantityByManager) {
		ProccesedCallsQuantityByManagerRole = proccesedCallsQuantityByManager;
	}

	public int getProccesedCallsQuantity() {
		return ProccesedCallsQuantity;
	}

	public void setProccesedCallsQuantity(int proccesedCallsQuantity) {
		ProccesedCallsQuantity = proccesedCallsQuantity;
	}
	
}