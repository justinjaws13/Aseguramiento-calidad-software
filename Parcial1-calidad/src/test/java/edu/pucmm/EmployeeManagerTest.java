package edu.pucmm;


import edu.pucmm.exception.EmployeeNotFoundException;
import edu.pucmm.exception.InvalidSalaryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author me@fredpena.dev
 * @created 02/06/2024  - 00:47
 */

public class EmployeeManagerTest {

    private EmployeeManager employeeManager;
    private Position juniorDeveloper;
    private Position seniorDeveloper;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void setUp() {
        employeeManager = new EmployeeManager();
        juniorDeveloper = new Position("1", "Junior Developer", 30000, 50000);
        seniorDeveloper = new Position("2", "Senior Developer", 60000, 90000);
        employee1 = new Employee("1", "John Doe", juniorDeveloper, 40000);
        employee2 = new Employee("2", "Jane Smith", seniorDeveloper, 70000);
        employeeManager.addEmployee(employee1);
    }

    @Test
    public void testAddEmployee() {
        employeeManager.addEmployee(employee2);

        assertEquals(2, employeeManager.getEmployees().size());
        assertTrue(employeeManager.getEmployees().contains(employee2));
    }

    @Test
    void testEmployeeSalaryAutoAdjustment() {
        Position seniorDeveloper = new Position("2", "Senior Developer", 60000, 90000);
        Employee employee = new Employee("E005", "Charlie Brown", seniorDeveloper, 55000); // 10% inferior al mínimo

        assertEquals(seniorDeveloper.getMinSalary(), employee.getSalary()); // Se ajusta al mínimo automáticamente
    }



    @Test
    public void testRemoveEmployee() {
        // TODO: Eliminar employee1 del employeeManager y verificar que se eliminó correctamente.
        // - Agregar employee2 al employeeManager.
        // - Eliminar employee1 del employeeManager.
        // - Verificar que el número total de empleados ahora es 1.
        // - Verificar que employee1 ya no está en la lista de empleados.

        employeeManager.addEmployee(employee2);
        employeeManager.removeEmployee(employee1);

        assertEquals(1, employeeManager.getEmployees().size());
        assertFalse(employeeManager.getEmployees().contains(employee1));

        assertTrue(true);
    }


    @Test
    public void testCalculateTotalSalary() {
        // TODO: Agregar employee2 al employeeManager y verificar el cálculo del salario total.
        // - Agregar employee2 al employeeManager.
        // - Verificar que el salario total es la suma de los salarios de employee1 y employee2.
        employeeManager.addEmployee(employee2);

        double expectedTotalSalary = employee1.getSalary() + employee2.getSalary();
        assertEquals(expectedTotalSalary, employeeManager.calculateTotalSalary());
        assertTrue(true);
    }



    @Test
    public void testUpdateEmployeeSalaryValid() {
        // TODO: Actualizar el salario de employee1 a una cantidad válida y verificar la actualización.
        // - Actualizar el salario de employee1 a 45000.
        // - Verificar que el salario de employee1 ahora es 45000.
        employeeManager.updateEmployeeSalary(employee1, 45000);

        assertEquals(45000, employee1.getSalary());
        //assertTrue(true);
    }


    @Test
     void testUpdateEmployeeSalaryInvalid() {
        // TODO: Intentar actualizar el salario de employee1 a una cantidad inválida y verificar la excepción.
        // - Intentar actualizar el salario de employee1 a 60000 (que está fuera del rango para Junior Developer).
        // - Verificar que se lanza una InvalidSalaryException.
        assertThrows(InvalidSalaryException.class, () -> employeeManager.updateEmployeeSalary(employee1, 60000));

       // assertTrue(true);
    }

    @Test
    public void testUpdateEmployeeSalaryEmployeeNotFound() {
        // TODO: Intentar actualizar el salario de employee2 (no agregado al manager) y verificar la excepción.
        // - Intentar actualizar el salario de employee2 a 70000.
        // - Verificar que se lanza una EmployeeNotFoundException.

            assertThrows(EmployeeNotFoundException.class, () -> employeeManager.updateEmployeeSalary(employee2, 70000));


        //assertTrue(true);
    }

    @Test
    public void testUpdateEmployeePositionSalaryAdjustment() {
        employeeManager.addEmployee(employee2);

        Position midLevel = new Position("3", "Mid-Level", 50000, 90000);
        employeeManager.updateEmployeePosition(employee2, midLevel);

        assertEquals(midLevel, employee2.getPosition());
        assertEquals(midLevel.getMinSalary(), employee2.getSalary());
    }





    @Test
    public void testUpdateEmployeePositionValid() {
        // TODO: Actualizar la posición de employee2 a una posición válida y verificar la actualización.
        // - Agregar employee2 al employeeManager.
        // - Actualizar la posición de employee2 a seniorDeveloper.
        // - Verificar que la posición de employee2 ahora es seniorDeveloper.
            employeeManager.addEmployee(employee2);
            employeeManager.updateEmployeePosition(employee2, juniorDeveloper);

            assertEquals(juniorDeveloper, employee2.getPosition());


        //assertTrue(true);
    }



    @Test
    public void testUpdateEmployeePositionInvalidDueToSalary() {
        // TODO: Intentar actualizar la posición de employee1 a seniorDeveloper y verificar la excepción.
        // - Intentar actualizar la posición de employee1 a seniorDeveloper.
        // - Verificar que se lanza una InvalidSalaryException porque el salario de employee1 no está dentro del rango para Senior Developer.

        assertTrue(true);
    }

    @Test
    public void testUpdateEmployeePositionEmployeeNotFound() {
        // TODO: Intentar actualizar la posición de employee2 (no agregado al manager) y verificar la excepción.
        // - Intentar actualizar la posición de employee2 a juniorDeveloper.
        // - Verificar que se lanza una EmployeeNotFoundException.
        assertTrue(true);
    }

    @Test
    public void testIsSalaryValidForPosition() {
        // TODO: Verificar la lógica de validación de salario para diferentes posiciones.
        // - Verificar que un salario de 40000 es válido para juniorDeveloper.
        // - Verificar que un salario de 60000 no es válido para juniorDeveloper.
        // - Verificar que un salario de 70000 es válido para seniorDeveloper.
        // - Verificar que un salario de 50000 no es válido para seniorDeveloper.
            assertTrue(employeeManager.isSalaryValidForPosition(juniorDeveloper, 40000));
            assertFalse(employeeManager.isSalaryValidForPosition(juniorDeveloper, 60000));
            assertTrue(employeeManager.isSalaryValidForPosition(seniorDeveloper, 70000));
            assertFalse(employeeManager.isSalaryValidForPosition(seniorDeveloper, 50000));
        //assertTrue(true);
    }

    @Test
    public void testAddEmployeeWithInvalidSalary() {

        // TODO: Intentar agregar empleados con salarios inválidos y verificar las excepciones.
        // - Crear un empleado con un salario de 60000 para juniorDeveloper.
        // - Verificar que se lanza una InvalidSalaryException al agregar este empleado.
        // - Crear otro empleado con un salario de 40000 para seniorDeveloper.
        // - Verificar que se lanza una InvalidSalaryException al agregar este empleado.
            Employee invalidJunior = new Employee("3", "Mark Lee", juniorDeveloper, 25000);
            Employee invalidSenior = new Employee("4", "Lucy Hale", seniorDeveloper, 40000);

            assertThrows(InvalidSalaryException.class, () -> employeeManager.addEmployee(invalidJunior));
            assertThrows(InvalidSalaryException.class, () -> employeeManager.addEmployee(invalidSenior));

        //assertTrue(true);
    }


    @Test
    public void testRemoveExistentEmployee() {
        // TODO: Eliminar un empleado existente y verificar que no se lanza una excepción.
        // - Eliminar employee1 del employeeManager.
        // - Verificar que no se lanza ninguna excepción.
            employeeManager.removeEmployee(employee1);
            assertFalse(employeeManager.getEmployees().contains(employee1));
        //assertTrue(true);
    }



    @Test
    public void testRemoveNonExistentEmployee() {
        // TODO: Intentar eliminar un empleado no existente y verificar la excepción.
        // - Intentar eliminar employee2 (no agregado al manager).
        // - Verificar que se lanza una EmployeeNotFoundException.
        assertTrue(true);
    }

    //pruebas parametizada
    @ParameterizedTest
    @CsvSource({
            "1, Junior Developer, 30000, 50000, 25000, false",
            "2, Senior Developer, 60000, 90000, 80000, true"
    })
    void testIsSalaryValidForPosition(String id, String name, double minSalary, double maxSalary, double salary, boolean expected) {
        Position position = new Position(id, name, minSalary, maxSalary);
        assertEquals(expected, employeeManager.isSalaryValidForPosition(position, salary));
    }


    @Test
    public void testAddDuplicateEmployee() {
        // TODO: Intentar agregar un empleado duplicado y verificar la excepción.
        // - Intentar agregar employee1 nuevamente al employeeManager.
        // - Verificar que se lanza una DuplicateEmployeeException.
        assertTrue(true);
    }
}
