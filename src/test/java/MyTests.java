import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyTests {
    private static boolean arrayEqualsUpTo(int lastIndexExclusive, String[] arr1, Object[] arr2) {
        for (int i = 0; i < lastIndexExclusive; i++) {
            if (!arr1[i].equals(arr2[i])) {
                System.out.println("Trie does not match expected results at index " + i +
                        ". Got " + arr2[i] + " instead of " + arr1[i]);
                return false;
            }
        }
        return true;
    }

    @Test
    public void noParentheses() {
        // --------------------------
        // Test 1: Case 1 and Case 2 (No Parentheses)
        // --------------------------
        String[] solution = new String[]{"3", "4", "2", "*", "+", "1", "-"};
        assertTrue(arrayEqualsUpTo(7, solution, ShuntingYard.convert("3 + 4 * 2 - 1").toArray()));

        solution = new String[]{"3", "4", "2", "*", "+", "1", "5", "*", "-", "6", "2", "/", "+", "3", "+"};
        assertTrue(arrayEqualsUpTo(solution.length, solution, ShuntingYard.convert("3 + 4 * 2 - 1 * 5 + 6 / 2 + 3").toArray()));

        // Example 3: 1 + 2 * 3 - 10 / 5 + 2
        solution = new String[]{"1", "2", "3", "*", "+", "10", "5", "/", "-", "2", "+"};
        assertTrue(arrayEqualsUpTo(solution.length, solution, ShuntingYard.convert("1 + 2 * 3 - 10 / 5 + 2").toArray()));

        // Example 4: 1 + 2 * 3 ^ 2
        solution = new String[]{"1", "2", "3", "2", "^", "*", "+"};
        assertTrue(arrayEqualsUpTo(solution.length, solution, ShuntingYard.convert("1 + 2 * 3 ^ 2").toArray()));
    }

    @Test
    public void withParentheses() {
        // --------------------------
        // Test 2: All Cases (With Parentheses)
        // --------------------------
        String[] solution = new String[]{"5", "2", "1", "+", "*", "3", "/", "6", "5", "-", "3", "*", "+"};
        assertTrue(arrayEqualsUpTo(solution.length, solution, ShuntingYard.convert("5 * ( 2 + 1 ) / 3 + ( 6 - 5 ) * 3").toArray()));

        solution = new String[]{"2", "3", "^", "2", "4", "*", "/", "10", "+"};
        assertTrue(arrayEqualsUpTo(solution.length, solution, ShuntingYard.convert("2 ^ 3 / ( 2 * 4 ) + 10").toArray()));
    }

    @Test
    public void evaluatesMDAS() {
        // --------------------------
        // Test 3: Evaluates Correctly + - / *
        // --------------------------
        assertEquals(10, ShuntingYard.evaluate(ShuntingYard.convert("3 + 4 * 2 - 1")));

        assertEquals(12, ShuntingYard.evaluate(ShuntingYard.convert("3 + 4 * 2 - 1 * 5 + 6 / 2 + 3")));

        assertEquals(7, ShuntingYard.evaluate(ShuntingYard.convert("1 + 2 * 3 - 10 / 5 + 2")));
    }

    @Test
    public void evaluatesPEMDAS() {
        // --------------------------
        // Test 4: Evaluates Everything Correctly
        // --------------------------
        assertEquals(19, ShuntingYard.evaluate(ShuntingYard.convert("1 + 2 * 3 ^ 2")));

        assertEquals(8, ShuntingYard.evaluate(ShuntingYard.convert("5 * ( 2 + 1 ) / 3 + ( 6 - 5 ) * 3")));

        assertEquals(11, ShuntingYard.evaluate(ShuntingYard.convert("2 ^ 3 / ( 2 * 4 ) + 10")));
    }
}