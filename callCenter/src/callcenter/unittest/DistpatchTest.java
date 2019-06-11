package callcenter.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import callcenter.dispatcher.Dispatcher;
import callcenter.dto.DispatcherLauncherDTO;
import callcenter.dto.DispatcherResultDTO;

public class DistpatchTest {
	
	private Dispatcher dispatcher;
	private DispatcherLauncherDTO dispatcherLauncherDTO;
	private DispatcherResultDTO dispatcherResultDTO;
	
	
	public DistpatchTest() {
		this.dispatcher = new Dispatcher();
		this.dispatcherLauncherDTO = new DispatcherLauncherDTO();
		this.dispatcherResultDTO = new DispatcherResultDTO();
	}
	
	
	/**
	 * Caso de prueba que valida que las 10 llamadas recibidas por el dispatcher sean procesadas y atendidas en su totalidad.
	*/
	@Test
	public void testAnswersQuantity() {
		
		this.dispatcher = new Dispatcher();
		this.dispatcherLauncherDTO = new DispatcherLauncherDTO();
		this.dispatcherResultDTO = new DispatcherResultDTO();
		
		int[ ] timeRange = {5,7};
		// cantidad de llamadas
		this.dispatcherLauncherDTO.setCallsQuantity(10);
		// cantidad de operarios disponibles para contestar las llamdas
		this.dispatcherLauncherDTO.setOperatorsQuantity(10);
		// cantidad de supervisores disponibles para contestar las llamdas
		this.dispatcherLauncherDTO.setSupervisorsQuantity(5);
		// cantidad de managers disponibles para contestar las llamdas
		this.dispatcherLauncherDTO.setManagersQuantity(1);
		// rango de tiempo en segundos que pueden durar las llamadas.
		this.dispatcherLauncherDTO.setTimeRange(timeRange);
		
		this.dispatcherResultDTO = dispatcher.dispatchCall(dispatcherLauncherDTO);
		
		assertEquals(10, this.dispatcherResultDTO.getProccesedCallsQuantity());
		
	}
	
	
	/**
	 * Caso de prueba que valida que cuando se reciban 20 llamadas , el dispatcher procese 10 y deje 10 en cola y espere cuando hayan empleados disponibles para atender las restantes
	 * finalmente debe atender las 20 llamdas en su totalidad.
	*/
	@Test
	public void testAnswersMoreThan10Calls() {
		
		this.dispatcher = new Dispatcher();
		this.dispatcherLauncherDTO = new DispatcherLauncherDTO();
		this.dispatcherResultDTO = new DispatcherResultDTO();
		
		int[ ] timeRange = {5,7};
		// cantidad de llamadas
		this.dispatcherLauncherDTO.setCallsQuantity(20);
		// cantidad de operarios disponibles para contestar las llamdas
		this.dispatcherLauncherDTO.setOperatorsQuantity(6);
		// cantidad de supervisores disponibles para contestar las llamdas
		this.dispatcherLauncherDTO.setSupervisorsQuantity(2);
		// cantidad de managers disponibles para contestar las llamdas
		this.dispatcherLauncherDTO.setManagersQuantity(1);
		// rango de tiempo en segundos que pueden durar las llamadas.
		this.dispatcherLauncherDTO.setTimeRange(timeRange);
		
		this.dispatcherResultDTO = dispatcher.dispatchCall(dispatcherLauncherDTO);
		
		assertEquals(20, this.dispatcherResultDTO.getProccesedCallsQuantity());
		
	}
	
	
	/**
	 * Caso de prueba que valida que cuando se reciban 10 llamadas, existan 5 operadores y 5 supervisores el manager no reciba ninguna llamada.
	*/
	@Test
	public void testManagerRoleNoAnswers() {
		
		this.dispatcher = new Dispatcher();
		this.dispatcherLauncherDTO = new DispatcherLauncherDTO();
		this.dispatcherResultDTO = new DispatcherResultDTO();
		
		int[ ] timeRange = {1,2};
		this.dispatcherLauncherDTO.setCallsQuantity(10);
		this.dispatcherLauncherDTO.setOperatorsQuantity(5);
		this.dispatcherLauncherDTO.setSupervisorsQuantity(5);
		this.dispatcherLauncherDTO.setManagersQuantity(1);
		this.dispatcherLauncherDTO.setTimeRange(timeRange);
		
		this.dispatcherResultDTO = dispatcher.dispatchCall(dispatcherLauncherDTO);
		
		assertEquals(0, this.dispatcherResultDTO.getProccesedCallsQuantityByManagerRole());
		
	}
	
	
	/**
	 * Caso de prueba que valida que cuando se reciban 11 llamadas, existan 5 operadores, 5 supervisores y 1 manager, el manager reciba 1 de las llamadas.
	*/
	@Test
	public void testManagerRoleAnswers() {
		
		this.dispatcher = new Dispatcher();
		this.dispatcherLauncherDTO = new DispatcherLauncherDTO();
		this.dispatcherResultDTO = new DispatcherResultDTO();
		
		int[ ] timeRange = {1,2};
		this.dispatcherLauncherDTO.setCallsQuantity(11);
		this.dispatcherLauncherDTO.setOperatorsQuantity(5);
		this.dispatcherLauncherDTO.setSupervisorsQuantity(5);
		this.dispatcherLauncherDTO.setManagersQuantity(1);
		this.dispatcherLauncherDTO.setTimeRange(timeRange);
		
		this.dispatcherResultDTO = dispatcher.dispatchCall(dispatcherLauncherDTO);
		
		assertEquals(1, this.dispatcherResultDTO.getProccesedCallsQuantityByManagerRole());
		
	}
	
	
	/**
	 * Caso de prueba que valida que cuando se reciban 10 llamadas, existan 5 operadores y 5 supervisores 5 de las llamadas sean procesadas por 5 supervisores.
	*/
	@Test
	public void testSupervisorRoleAnswers() {
		
		this.dispatcher = new Dispatcher();
		this.dispatcherLauncherDTO = new DispatcherLauncherDTO();
		this.dispatcherResultDTO = new DispatcherResultDTO();
		
		int[ ] timeRange = {1,2};
		this.dispatcherLauncherDTO.setCallsQuantity(10);
		this.dispatcherLauncherDTO.setOperatorsQuantity(5);
		this.dispatcherLauncherDTO.setSupervisorsQuantity(5);
		this.dispatcherLauncherDTO.setManagersQuantity(1);
		this.dispatcherLauncherDTO.setTimeRange(timeRange);
		
		this.dispatcherResultDTO = dispatcher.dispatchCall(dispatcherLauncherDTO);
		
		assertEquals(5, this.dispatcherResultDTO.getProccesedCallsQuantityBySupervisorRole());
		
	}
	
	
	/**
	 * Caso de prueba que valida que cuando se reciban 10 llamadas, existan 5 operadores y 5 supervisores 5 de las llamadas sean procesadas por 5 operadores.
	*/
	@Test
	public void testOperatorRoleAnswers() {
		
		this.dispatcher = new Dispatcher();
		this.dispatcherLauncherDTO = new DispatcherLauncherDTO();
		this.dispatcherResultDTO = new DispatcherResultDTO();
		
		int[ ] timeRange = {1,2};
		this.dispatcherLauncherDTO.setCallsQuantity(10);
		this.dispatcherLauncherDTO.setOperatorsQuantity(5);
		this.dispatcherLauncherDTO.setSupervisorsQuantity(5);
		this.dispatcherLauncherDTO.setManagersQuantity(1);
		this.dispatcherLauncherDTO.setTimeRange(timeRange);
		
		this.dispatcherResultDTO = dispatcher.dispatchCall(dispatcherLauncherDTO);
		
		assertEquals(5, this.dispatcherResultDTO.getProccesedCallsQuantityByOperatorRole());
		
	}


}
