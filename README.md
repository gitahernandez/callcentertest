
# Prueba Técnica Para Almundo / Call Center
* Autor: Andrés Felipe Hernández Rocha *
* email: ingeingero@gmail.com *
* Fecha: 11 Junio de 2019 *

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

Cuando se reciba una llamada y no existe un empleado disponible para contestarla, esta llamada se encola y será atendida cuando exista un empleado disponible, es decir cuando un empleado finalize la llamada en la que se ecuentra ocupado.

