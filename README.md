
# Prueba Técnica Para Almundo / Call Center
* Autor: Andrés Felipe Hernández Rocha
* email: ingeingero@gmail.com
* Fecha: 11 Junio de 2019

## Paquetes

El proyecto fue desarrollado utilizando java 1.8, el proyecto posee los siguientes 4 paquetes:

### callcenter.unittest

Contiene las pruebas unitarias correspondientes.

### callcenter.admin

controla el proceso encolamiento de las llamadas.

### callcenter.dto

Contiene los objetos de transferencia de datos necesarios para la aplicación.

### callcenter.dispatcher

Contiene la clase dispatcher solicitada en el enunciado de la prueba.

### callcenter.thread

Contiene los hilos necesarios para procesar las llamadas.

## LLamada en cola

Cuando se reciba una llamada y no exista un empleado disponible para atenderla, esta llamada se encola y será atendida cuando  un empleado este libre, es decir cuando un empleado finalice la llamdada que esta atendiendo en el momento.

## Caso de prueba de 10 llamadas simultáneas, 5 operadores, 5 supervisores y 1 manager.

```
	/**
	 * Caso de prueba que valida que cuando se reciban 10 llamadas, existan 5 operadores y 5 supervisores 5 de las 	llamadas sean procesadas por 5 supervisores.
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
	
Print de la consola

LLAMADA RECIBIDA : El OPERATOR 2 acaba de recibir la llamada 2
LLAMADA RECIBIDA : El OPERATOR 3 acaba de recibir la llamada 3
LLAMADA RECIBIDA : El OPERATOR 1 acaba de recibir la llamada 1
LLAMADA RECIBIDA : El OPERATOR 4 acaba de recibir la llamada 4
LLAMADA RECIBIDA : El OPERATOR 5 acaba de recibir la llamada 5
LLAMADA RECIBIDA : El SUPERVISOR 1 acaba de recibir la llamada 6
LLAMADA RECIBIDA : El SUPERVISOR 2 acaba de recibir la llamada 7
LLAMADA RECIBIDA : El SUPERVISOR 3 acaba de recibir la llamada 8
LLAMADA RECIBIDA : El SUPERVISOR 4 acaba de recibir la llamada 9
LLAMADA RECIBIDA : El SUPERVISOR 5 acaba de recibir la llamada 10
LLAMADA FINALIZADA : La llamada 10 terminó , fue atendida por el SUPERVISOR 5, la llamada tuvo una duración de 5 segundos.
LLAMADA FINALIZADA : La llamada 1 terminó , fue atendida por el OPERATOR 1, la llamada tuvo una duración de 5 segundos.
LLAMADA FINALIZADA : La llamada 5 terminó , fue atendida por el OPERATOR 5, la llamada tuvo una duración de 6 segundos.
LLAMADA FINALIZADA : La llamada 4 terminó , fue atendida por el OPERATOR 4, la llamada tuvo una duración de 6 segundos.
LLAMADA FINALIZADA : La llamada 3 terminó , fue atendida por el OPERATOR 3, la llamada tuvo una duración de 7 segundos.
LLAMADA FINALIZADA : La llamada 7 terminó , fue atendida por el SUPERVISOR 2, la llamada tuvo una duración de 7 segundos.
LLAMADA FINALIZADA : La llamada 9 terminó , fue atendida por el SUPERVISOR 4, la llamada tuvo una duración de 7 segundos.
LLAMADA FINALIZADA : La llamada 2 terminó , fue atendida por el OPERATOR 2, la llamada tuvo una duración de 7 segundos.
LLAMADA FINALIZADA : La llamada 6 terminó , fue atendida por el SUPERVISOR 1, la llamada tuvo una duración de 8 segundos.
LLAMADA FINALIZADA : La llamada 8 terminó , fue atendida por el SUPERVISOR 3, la llamada tuvo una duración de 8 segundos.
El OPERATOR 1 contestó un total de 1 llamadas , felicitaciones OPERATOR 1
El OPERATOR 2 contestó un total de 1 llamadas , felicitaciones OPERATOR 2
El OPERATOR 3 contestó un total de 1 llamadas , felicitaciones OPERATOR 3
El OPERATOR 4 contestó un total de 1 llamadas , felicitaciones OPERATOR 4
El OPERATOR 5 contestó un total de 1 llamadas , felicitaciones OPERATOR 5
El SUPERVISOR 1 contestó un total de 1 llamadas , felicitaciones SUPERVISOR 1
El SUPERVISOR 2 contestó un total de 1 llamadas , felicitaciones SUPERVISOR 2
El SUPERVISOR 3 contestó un total de 1 llamadas , felicitaciones SUPERVISOR 3
El SUPERVISOR 4 contestó un total de 1 llamadas , felicitaciones SUPERVISOR 4
El SUPERVISOR 5 contestó un total de 1 llamadas , felicitaciones SUPERVISOR 5
El MANAGER 1 contestó un total de 0 llamadas , felicitaciones MANAGER 1

	
	
## Caso de prueba de más de 10 llamadas simultáneas, 6 operadores, 2 supervisores y 1 manager.

```
  /**
  * Caso de prueba que valida que cuando se reciban 20 llamadas, 
  * el dispatcher procesa 10 y deje 10 en cola y espera cuando     
  * hayan empleados disponibles para atender las restantes
  * finalmente debe atender las 20 llamadas en su totalidad.
  */
	@Test
	public void testAnswersMoreThan10Calls() {
		
		this.dispatcher = new Dispatcher();
		this.dispatcherLauncherDTO = new DispatcherLauncherDTO();
		this.dispatcherResultDTO = new DispatcherResultDTO();
		
		// rango de tiempo en segundos que puede tardar la llamada.
		int[ ] timeRange = {5,10};
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
	
	

