# ProyectoAwa
Proyecto de distribución de agua Para la materia de prog distribuida

## descripción
El propósito del sistema es proporcionar un medio de distribución de agua potable a los habitantes de una determinada población.
La distribución se hará considerando una cantidad finita de recursos,
horarios de reabastecimiento de los tanques de distribución y la ubicación geográfica de los solicitantes.

En cuanto a recursos, se considera que se tienen tanques para el almacenaje del agua, los cuales están identificados por un nombre específico y tienen también una capacidad determinada, como se indica en la siguiente tabla.

| tanque   | capacidad | Tiempo de llenado (min) |
|----------|-----------|-------------------------|
| tanque 1 | 10000     | 10                      |
| tanque 2 | 20000     | 20                      |
| tanque 3 | 3000      | 3                       |

Todos los tanques deberán ser reabastecidos diariamente a las 00:00 horas.
El servicio de distribución hacia los clientes estará deshabilitado durante el tiempo que ocurre el llenado de los tanques, proceso cuya duración se expresa en la tabla anterior.

## Requerimientos
1. El sistema deberá tener la capacidad de recibir solicitudes simultáneas de clientes las 24 horas del día
excepto durante los periodos de recarga de los tanques.
2. Los límites de entrega de agua por petición son de mínimo un litro y un máximo de 1000.
3. Todos los usuarios deberán realizar la solicitud indicando su número de cliente,
el tanque que tienen asignado y la cantidad de litros solicitados.

## TODO
- [x] Crear las clases Servidor/Cliente.
- [x] Implementar Logs en el Cliente y en el Servidor.
- [x] Ver si las 00:00 debe de ser con el reloj *irl* o implementar un reloj interno que vaya más rápido.
- [x] Implementar reabastecimiento a las 00:00 horas.
- [x] implementar manejo de clientes (que se va a hacer cuando un cliente se conecte?)
- [ ] Crear una interfaz en la que nos de in ID de cliente y podamos pedir una cantidad arbitraria de Agua
- [ ] Escribir todos los movimientos de agua en el log
- [ ] Documentar todo para que todos sepamos que onda con el proyecto cuando pregunte ggggg.

## Detalles
a a aa aaa aaaaa aaaaaaaa aaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 
