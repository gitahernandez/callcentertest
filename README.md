
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

## Caso de prueba de más de 10 llamadas simultáneas, 6 operadores, 2 supervisores y 1 manager.

```
  /**
  * Caso de prueba que valida que cuando se reciban 20 llamadas, 
  * el dispatcher procese 10 y deje 10 en cola y espere cuando     
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
	
	

