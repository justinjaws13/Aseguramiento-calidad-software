package model;

import org.example.model.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {
    @Test
    void constructorYGetters() {
        Producto p = new Producto("P1", "Manzana", 0.50);
        assertEquals("P1", p.getId());
        assertEquals("Manzana", p.getNombre());
        assertEquals(0.50, p.getPrecio());
    }
}
