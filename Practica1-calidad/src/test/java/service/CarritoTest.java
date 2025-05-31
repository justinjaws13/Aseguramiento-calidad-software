package service;

import  org.example.model.Producto;
import  org.example.model.ItemCarrito;
import org.example.service.Carrito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarritoTest {
    private Carrito carrito;
    private Producto pan;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        pan = new Producto("P1", "Pan", 1.20);
    }

    @Test
    void agregarProductoNuevo() {
        carrito.agregarProducto(pan, 2);
        assertEquals(1, carrito.getItems().size());
        assertEquals(2, carrito.getItems().get(0).getCantidad());
    }

    @Test
    void agregarIncrementaCantidadSiExiste() {
        carrito.agregarProducto(pan, 2);
        carrito.agregarProducto(pan, 3);
        assertEquals(1, carrito.getItems().size());
        assertEquals(5, carrito.getItems().get(0).getCantidad());
    }

    @Test
    void eliminarProducto() {
        carrito.agregarProducto(pan, 2);
        carrito.eliminarProducto("P1");
        assertTrue(carrito.getItems().isEmpty());
    }

    @Test
    void modificarCantidadAMenor() {
        carrito.agregarProducto(pan, 5);
        carrito.modificarCantidad("P1", 2);
        assertEquals(2, carrito.getItems().get(0).getCantidad());
    }

    @Test
    void modificarCantidadACeroElimina() {
        carrito.agregarProducto(pan, 2);
        carrito.modificarCantidad("P1", 0);
        assertTrue(carrito.getItems().isEmpty());
    }

    @Test
    void calcularTotalSumaSubtotales() {
        carrito.agregarProducto(pan, 2); // 2×1.2 = 2.4
        carrito.agregarProducto(new Producto("P2","Leche",0.80), 3); // 3×0.8 = 2.4
        assertEquals(4.8, carrito.calcularTotal(), 0.0001);
    }
}
