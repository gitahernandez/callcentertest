package callcenter.dispatcher;

import java.util.List;
import java.util.ArrayList;

import callcenter.admin.*;
import callcenter.dto.*;
import callcenter.thread.*;

/**

 * Clase encargada de procesar las llamadas recibidas, se comporta como una especie de orquestador de todos los componentes de la apicación.

 * @author: Andrés Felipe Hernández Rocha

 */


public class Dispatcher {
	
	static final String ROLE_OPERATOR_NAME = "OPERATOR";
	static final String ROLE_SUPERVISOR_NAME = "SUPERVISOR";
	static final String ROLE_MANAGER_NAME = "MANAGER";
	
	private CallsControl callAdmin;
	private EmployeeInCall[] employeesInCall;
	private Call[] calls;
	private EmployeeDTO employeeDTO;
	private List<EmployeeDTO> employeesListDTO;
	private DispatcherResultDTO dispatcherResultDTO;
	private int callsQuantity;
	private int operatorsQuantity;
	private int supervisorsQuantity;
	private int managersQuantity;
	int[] timeRange;
	
	/** Esta función recibe el objeto DispatcherLauncherDTO que posee las variables para que el dispatcher y al aplicación pueda funcionar (cantidad de llamadas simultaneas,
	 * cantidad de operarios, cantidad de supervisores, cantidad de directores, y rango de tiempo en segundos de duración de las llamadas)
     * @param DispatcherLauncherDTO
     * @return DispatcherResultDTO :  Resultado del proceso
     */
	
	public DispatcherResultDTO dispatchCall(DispatcherLauncherDTO dispatcherLauncherDTO) {
		
		this.callsQuantity = dispatcherLauncherDTO.getCallsQuantity();
		this.operatorsQuantity = dispatcherLauncherDTO.getOperatorsQuantity();
		this.supervisorsQuantity = dispatcherLauncherDTO.getSupervisorsQuantity();
		this.managersQuantity = dispatcherLauncherDTO.getManagersQuantity();
		
		this.timeRange = dispatcherLauncherDTO.getTimeRange();
		
		callAdmin = new CallsControl();
		this.employeesListDTO =  new ArrayList<EmployeeDTO>();
		
		this.initializeEmployeeDto(this.operatorsQuantity, ROLE_OPERATOR_NAME);
		this.initializeEmployeeDto(this.supervisorsQuantity, ROLE_SUPERVISOR_NAME);
		this.initializeEmployeeDto(this.managersQuantity, ROLE_MANAGER_NAME);
			
		this.initializeCalls(callAdmin);
		this.initializeEmployeesInCall(callAdmin);
		
		this.joinCalls();
		this.joinEmployeesInCall();
		
		int proccesedCallsQuantity = 0;
		int proccesedCallsQuantityByManager = 0;
		int proccesedCallsQuantityBySupervisor = 0;
		int proccesedCallsQuantityByOperator = 0;
		for (EmployeeInCall obj : this.employeesInCall) {
			proccesedCallsQuantity = proccesedCallsQuantity + obj.getCallsCount();
			if (obj.getEmployee().getType().equals(ROLE_MANAGER_NAME)) {
				proccesedCallsQuantityByManager = proccesedCallsQuantityByManager + obj.getCallsCount();
			}
			else if (obj.getEmployee().getType().equals(ROLE_SUPERVISOR_NAME)) {
				proccesedCallsQuantityBySupervisor = proccesedCallsQuantityBySupervisor + obj.getCallsCount();
			}
			else if (obj.getEmployee().getType().equals(ROLE_OPERATOR_NAME)) {
				proccesedCallsQuantityByOperator = proccesedCallsQuantityByOperator + obj.getCallsCount();
			}
			System.out.println("El " + obj.getEmployee().getName() + " contestó un total de " + obj.getCallsCount() + " llamadas , felicitaciones " + obj.getEmployee().getName() );
		}
		
		dispatcherResultDTO =  new DispatcherResultDTO();
		dispatcherResultDTO.setProccesedCallsQuantity(proccesedCallsQuantity);
		dispatcherResultDTO.setProccesedCallsQuantityByManagerRole(proccesedCallsQuantityByManager);
		dispatcherResultDTO.setProccesedCallsQuantityBySupervisorRole(proccesedCallsQuantityBySupervisor);
		dispatcherResultDTO.setProccesedCallsQuantityByOperatorRole(proccesedCallsQuantityByOperator);
		return dispatcherResultDTO;
		
		
	}
	
	
	/** 
	* Crea cierta cantidad de empleados con un role especifico.
    * @param quantity (cantidad de empleados a crear), role (role de los empleados que se van a crear).
    * @return 
    */
	public void initializeEmployeeDto(int quantity, String role) {
		for (int i = 0; i < quantity; i++) {
			this.employeeDTO = new EmployeeDTO();
			this.employeeDTO.setId(i+1);
			this.employeeDTO.setName(role + " " + (i+1));
			this.employeeDTO.setType(role);
			this.employeesListDTO.add(this.employeeDTO);
		}
	}
	
	
	/** 
	* Unifica la ejecución de los hilos correspondientes a las llamdas. (Esperar que todos los hilos finalicen para continuar con el hilo principal de la aplicación).
    * @param 
    * @return 
    */
	public void joinCalls() {
		for (int i = 0; i < this.callsQuantity; i++) {
			try {
				this.calls[i].join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/** 
	* Unifica la ejecución de los hilos correspondientes a los empleados que contestan las llamadas. (Esperar que todos los hilos finalicen para continuar con el hilo principal de la aplicación).
    * @param 
    * @return 
    */
	public void joinEmployeesInCall() {
		for (int i = 0; i < this.employeesListDTO.size(); i++) {
			try {
				this.employeesInCall[i].join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/** 
	* crea cierta cantidad de empleados con un role especifico.
    * @param 
    * @return 
    */
	public void initializeCalls(CallsControl callAdmin) {
		this.calls = new Call[this.callsQuantity];
		for (int i = 0; i < this.callsQuantity; i++) {
			this.calls[i] = new Call(i + 1, callAdmin);
			this.calls[i].start();
		}
	}
	
	
	/** 
	* Inicializa el arreglo de hilos correspondiente a los empleados que van a atender las llamadas.
    * @param callAdmin (controla las cantidad de llamadas que ingresan)
    * @return 
    */
	public void initializeEmployeesInCall(CallsControl callAdmin) {
		this.employeesInCall = new EmployeeInCall[this.employeesListDTO.size()];
		for (int i = 0; i < this.employeesListDTO.size(); i++) {
			this.employeesInCall[i] = new EmployeeInCall(this.employeesListDTO.get(i), callAdmin, this.timeRange);
			this.employeesInCall[i].start();
		}
	}
	
	
	/** 
	* Inicializa el proceso de ejecuíon de la aplicacion (main)
    * @param args
    * @return 
    */
	public static void main(String[] args) {
		DispatcherLauncherDTO dispatcherLauncherDTO = new DispatcherLauncherDTO();
		int[ ] timeRange = {5,10};
		dispatcherLauncherDTO.setCallsQuantity(10);
		dispatcherLauncherDTO.setOperatorsQuantity(5);
		dispatcherLauncherDTO.setSupervisorsQuantity(5);
		dispatcherLauncherDTO.setManagersQuantity(1);
		dispatcherLauncherDTO.setTimeRange(timeRange);
		
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.dispatchCall(dispatcherLauncherDTO);

	}
	
}



