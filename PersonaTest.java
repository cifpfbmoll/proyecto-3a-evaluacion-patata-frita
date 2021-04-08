/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fp.concesionario;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Karina
 */
public class PersonaTest {
    
    public PersonaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Persona.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        String expResult = "{ Nombre: " + "Patata" + " | Apellidos: " + "Frita" + " | NIF: " + "11111111A" + " | Telefono: " + 666555444 + " | Domicilio: " + "C/Holi amazon" + "}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Persona.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        String expResult = "Patata";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Persona.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        instance.setNombre(nombre);
    }

    /**
     * Test of getApellidos method, of class Persona.
     */
    @Test
    public void testGetApellidos() {
        System.out.println("getApellidos");
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        String expResult = "Frita";
        String result = instance.getApellidos();
        assertEquals(expResult, result);
    }

    /**
     * Test of setApellidos method, of class Persona.
     */
    @Test
    public void testSetApellidos() {
        System.out.println("setApellidos");
        String apellidos = "";
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        instance.setApellidos(apellidos);
    }

    /**
     * Test of getNif method, of class Persona.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        instance.setNif("1111111A");
        String expResult = "1111111A";
        String result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNif method, of class Persona.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        String nif = "1111111A";
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        instance.setNif(nif);
        
        assertEquals(nif,instance.getNif());
    }

    /**
     * Test of getTelefono method, of class Persona.
     */
    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        Integer expResult = 666555444;
        Integer result = instance.getTelefono();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTelefono method, of class Persona.
     */
    @Test
    public void testSetTelefono() {
        System.out.println("setTelefono");
        Integer telefono = null;
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        instance.setTelefono(telefono);
    }

    /**
     * Test of getDomicilio method, of class Persona.
     */
    @Test
    public void testGetDomicilio() {
        System.out.println("getDomicilio");
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        String expResult = "C/Holi amazon";
        String result = instance.getDomicilio();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDomicilio method, of class Persona.
     */
    @Test
    public void testSetDomicilio() {
        System.out.println("setDomicilio");
        String domicilio = "";
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        instance.setDomicilio(domicilio);
    }
    
}
