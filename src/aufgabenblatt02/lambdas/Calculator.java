/*
 * Praktikum PM2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */
package aufgabenblatt02.lambdas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

/**
 * Calculator to demonstrate use of Lambda Expressions
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 09.11.2016
 */
public class Calculator {

    /**
     * Basic Operations
     */
    public enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    /**
     * Used to map operation enums to lambda expressions
     */
    private Map<Operation, DoubleBinaryOperator> operations;

    public Calculator() {
        operations = new HashMap<>();

        // populate map
        operations.put(Operation.ADD, (d1, d2) -> d1 + d2);
        operations.put(Operation.SUBTRACT, (d1, d2) -> d1 - d2);
        operations.put(Operation.MULTIPLY, (d1, d2) -> d1 * d2);
        operations.put(Operation.DIVIDE, (d1, d2) -> d1 / d2);
    }

    /**
     * Evaluates the given Operand using the two values supplied
     * 
     * @param op
     *            the Operation to perform
     * @param d1
     *            the first operand
     * @param d2
     *            the second operand
     * @return the result of applying operation on d1 and d2
     */
    public double calculate(Operation op, double d1, double d2) {
        return operations.get(op).applyAsDouble(d1, d2);
    }
}
