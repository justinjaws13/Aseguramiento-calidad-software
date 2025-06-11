package edu.pucmm;

import java.io.Serializable;

/**
 * @author me@fredpena.dev
 * @created 01/06/2024  - 23:38
 */
public class Position implements Serializable {

    private final String id;
    private final String name;
    private final double minSalary;
    private final double maxSalary;

    public Position(String id, String name, double minSalary, double maxSalary) {
        if (minSalary < 0 || maxSalary < minSalary) {
            throw new IllegalArgumentException("El salario minimo no puede ser negativo y el maximo debe ser mayor o igual al minimo.");
        }
        this.id = id;
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getMinSalary() { return minSalary; }
    public double getMaxSalary() { return maxSalary; }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return id.equals(position.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Position{id='%s', name='%s', minSalary=%.2f, maxSalary=%.2f}", id, name, minSalary, maxSalary);
    }
}
