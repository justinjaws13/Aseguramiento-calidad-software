package org.example.service;

import org.example.model.ItemCarrito;
import org.example.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Lógica de negocio para manejar un carrito de compras.
 */
public class Carrito {
    private final List<ItemCarrito> items = new ArrayList<>();

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void agregarProducto(Producto p, int cantidad) {
        Optional<ItemCarrito> existente = items.stream()
                .filter(i -> i.getProducto().getId().equals(p.getId()))
                .findFirst();
        if (existente.isPresent()) {
            existente.get().setCantidad(existente.get().getCantidad() + cantidad);
        } else {
            items.add(new ItemCarrito(p, cantidad));
        }
    }

    public void eliminarProducto(String productoId) {
        items.removeIf(i -> i.getProducto().getId().equals(productoId));
    }

    public void modificarCantidad(String productoId, int nuevaCantidad) {
        items.stream()
                .filter(i -> i.getProducto().getId().equals(productoId))
                .findFirst()
                .ifPresent(i -> {
                    if (nuevaCantidad <= 0) {
                        eliminarProducto(productoId);
                    } else {
                        i.setCantidad(nuevaCantidad);
                    }
                });
    }

    public double calcularTotal() {
        return items.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
    }
}
