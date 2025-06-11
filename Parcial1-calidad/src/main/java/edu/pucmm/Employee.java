package edu.pucmm;

import java.io.Serializable;

public class Employee implements Serializable {

    private final String id;
    private final String name;
    private Position position;
    private double salary;

    public Employee(String id, String name, Position position, double salary) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID no puede estar vacío.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío.");
        }
        if (position == null) {
            throw new IllegalArgumentException("La posición no puede ser nula.");
        }
        if (salary < position.getMinSalary()) {
            throw new IllegalArgumentException("El salario no puede estar por debajo del mínimo permitido para la posición.");
        }

        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Position getPosition() { return position; }
    public double getSalary() { return salary; }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return id.equals(employee.id);
    }

    public void setSalary(double salary) {
        if (salary < position.getMinSalary() || salary > position.getMaxSalary()) {
            throw new IllegalArgumentException("El salario no está dentro del rango permitido.");
        }
        this.salary = salary;
    }

    public void setPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("La posición no puede ser nula.");
        }
        this.position = position;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Employee{id='%s', name='%s', position='%s', salary=%.2f}", id, name, position.getName(), salary);
    }


}
