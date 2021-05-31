# Gestion de un concesionario
### proyecto-3a-evaluacion

En este proyecto se pide cumplir con los siguientes requisitos:
1. Controlar un concesionario con una base de datos
2. Controlar las ventas del concesionario y el taller
3. Controlar los clientes y los empleados, al igual que los pagos dados y recibidos de cada uno

Para cumplirlos hemos realizado la división del programa como se puede ver en el esquema de la [base de datos](database/diagrama1.jpg)

### Explicación de las clases

⋅⋅*[AppMain](AppMain.java): Nuestra clase main que contiene un menú para usar el resto de clases y ver los datos. Desarrollada por todos los miembros, especialmente Marat.
⋅⋅*[Cliente](Cliente.java): Extiende de la interfaz persona, contiene los datos de un cliente asi como métodos de acceso a base de datos y exportación a un fichero. Desarrollada principalmente por Karina.
⋅⋅*[Concesionario](Concesionario.java): Clase que contiene los datos del concesionario así como la relación con taller y venta, también contiene métodos de acceso a la base de datos y exportación a un fichero. Desarrollada por José Luís y Marat.
⋅⋅*[Empleado](Empleado.java): Extiende de la interfaz persona, contiene los datos de cada empleado, incluyendo su puesto de trabajo, contiene métodos de acceso a la base de datos y exportación a un fichero. Desarrollada principalmente por Karina.
⋅⋅*[Factura](Factura.java): Clase que contendrà los datos de las compras, incluyendo compras/ventas de vehículos y información sobre el taller. Desarrollada principalmente por Marat.
⋅⋅*[Motor](Motor.java): Clase que contiene los datos de un motor así como un enum para el tipo de gasolina, incluye métodos de acceso a la base de datos y exportación a un fichero. Desarrollada principalmente por Joan.
⋅⋅*[Nomina](Nomina.java): Clase que contendrà los datos de los sueldos de los empleados, también incluye métodos de acceso a la base de datos y exportación a un fichero. Desarrollada principalmente por Marat.
⋅⋅*[Persona](Persona.java): Interfaz con los datos genéricos de una persona, de ella eredan [Cliente](Cliente.java) y [Empleado](Empleado.java). Desarrollada principalmente por Karina.
⋅⋅*[Reserva](Reserva.java): Clase que contiene los datos de una reserva, donde una reserva és una hora y lugar específicos de un taller adónde el cliente irá a dejar el vehículo. Contiene métodos de acceso a la base de datos y exportación a un fichero. Desarrollada principalmente por Marat.
⋅⋅*[Taller](Taller.java): Clase que contiene los datos de un taller. Contiene métodos de acceso a la base de datos y exportación a un fichero. Desarrollada principalmente por José Luís.
⋅⋅*[Utils](Utils.java): Clase que contiene herramientas que se usan repetidas veces a lo largo del programa, asiste en lectura por teclado, lectura y escritura de archivos y acceso a la base de datos. Desarrollada por todos los miembros del grupo.
⋅⋅*[Vehiculo](Vehiculo.java): Clase que contiene todos los datos de un vehículo, contiene métodos de acceso a la base de datos y exportación a ficheros. Desarrollada principalmente por Joan.
⋅⋅*[Venta](Venta.java): Clase que contiene los datos de las ventas, entendiéndose venta como el acto de vender un vehículo a un cliente. Contiene métodos de acceso a la base de datos y exportación a fichero.

### Roles en el grupo
⋅⋅* Joan: Scrum master, como trabajos principales coordinar el grupo, encargarse del control de la base de datos, controlar el Git y solucionar dudas al grupo, además de preparar el esquema a seguir del programa.
⋅⋅* Marat: Encargado de investigar y testear sobre nuevos temas, como el acceso a la base de datos o escritura a ficheros.
⋅⋅* Karina: Encargada de investigar y testear las interfaces gràficas
⋅⋅* José Luís: Peón capaz de adaptarse a cualquier trabajo para ayudar.

Cabe decir que cada uno ha realizado almenos una clase y apoyado a los otros compañeros cuando hiciera falta.
