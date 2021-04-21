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
public class ClienteTest {
    
    public ClienteTest() {
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
     * Test of getReserva method, of class Cliente.
     */
    @Test
    public void testGetReserva() {
        System.out.println("getReserva");
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        Integer expResult = 1;
        Integer result = instance.getReserva();
        assertEquals(expResult, result);
    }

    /**
     * Test of setReserva method, of class Cliente.
     */
    @Test
    public void testSetReserva() {
        System.out.println("setReserva");
        Integer reserva = 25;
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        instance.setReserva(reserva);
    }

    /**
     * Test of getFactura method, of class Cliente.
     */
    @Test
    public void testGetFactura() {
        System.out.println("getFactura");
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        Integer expResult = 11;
        Integer result = instance.getFactura();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFactura method, of class Cliente.
     */
    @Test
    public void testSetFactura() {
        System.out.println("setFactura");
        Integer factura = 25;
        Cliente instance = new Cliente(1,11,"Patata", "Frita", "11111111A", 666555444, "C/Holi amazon");
        instance.setFactura(factura);
    }
    
}
