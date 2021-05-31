# Gestion de un concesionario
### proyecto-3a-evaluacion

En este proyecto se pide cumplir con los siguientes requisitos:
1. Controlar un concesionario con una base de datos
2. Controlar las ventas del concesionario y el taller
3. Controlar los clientes y los empleados, al igual que los pagos dados y recibidos de cada uno

Para cumplirlos hemos realizado la división del programa como se puede ver en el esquema de la [base de datos](../database/diagrama1.jpg)

### Explicación de las clases

[AppMain](../AppMain.java): Nuestra clase main que contiene un menú para usar el resto de clases y ver los datos. Desarrollada por todos los miembros, especialmente Marat.
[Cliente](../Cliente.java): Extiende de la interfaz persona, contiene los datos de un cliente asi como métodos de acceso a base de datos y exportación a un fichero. Desarrollada principalmente por Karina.
[Concesionario](../Concesionario.java): Clase que contiene los datos del concesionario así como la relación con taller y venta, también contiene métodos de acceso a la base de datos y exportación a un fichero. Desarrollada por José Luís y Marat.
[Empleado](../Empleado.java): Extiende de la interfaz persona, contiene los datos de cada empleado, incluyendo su puesto de trabajo, contiene métodos de acceso a la base de datos y exportación a un fichero. Desarrollada principalmente por Karina.
[Factura](../Factura.java): Clase que contendrà los datos de las compras, incluyendo compras/ventas de vehículos y información sobre el taller. Desarrollada principalmente por Marat.
[Motor](../Motor.java): Clase que contiene los datos de un motor así como un enum para el tipo de gasolina, incluye métodos de acceso a la base de datos y exportación a un fichero. Desarrollada principalmente por Joan.
[Nomina](../Nomina.java): Clase que contendrà los datos de los sueldos de los empleados, también incluye métodos de acceso a la base de datos y exportación a un fichero. Desarrollada principalmente por Marat.
[Persona](../Persona.java): Interfaz con los datos genéricos de una persona, de ella eredan [Cliente](../Cliente.java) y [Empleado](../Empleado.java). Desarrollada principalmente por Karina.
[Reserva](../Persona.java): Clase que contiene los datos de una reserva, donde una reserva és una hora y lugar específicos de un taller adónde el cliente irá a dejar el vehículo.
