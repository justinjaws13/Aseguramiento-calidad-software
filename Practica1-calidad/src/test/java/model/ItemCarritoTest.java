package model;

import org.example.model.ItemCarrito;
import org.example.model.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemCarritoTest {
    @Test
    void subtotalCalculaBien() {
        Producto p = new Producto("P1", "Pan", 1.20);
        ItemCarrito item = new ItemCarrito(p, 3);
        assertEquals(3.60, item.getSubtotal(), 0.0001);
    }
}
