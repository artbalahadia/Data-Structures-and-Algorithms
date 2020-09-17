
/**
 * Runs tests on instances of the Time class using the main method of this class.
 * Tests are divided into four sets, one for each of the
 * sub-tasks described in the Time class, which are executed in the
 * order of the sub-tasks.
 * Sets are only attempted if all the previous sets have passed.
 * Tests within a set are attempted even if previous tests in the set have failed.
 *
 * Do not change this class.
 *
 * @author Ian Utting
 * Modified by Tony Lowe
 * @version 1.2
 */
public class TimeTester
{

    private static boolean forceCompare = false; // Force the compare tests to run
    private static boolean forceAdd = false; // Force the add tests to run
    private static boolean forceSubtract = false; // Force the subtract tests to run

    /**
     * Run all of the sets of tests, running each one only if the previous sets
     * have all passed. This makes the results less cluttered if you are
     * attempting the sub-tasks in order.
     */
    public static void main(String[] args)
    {
        if (forceCompare)
        {
            compareToTests();
            return;
        }
        if (forceAdd)
        {
            addTests();
            return;
        }
        if (forceSubtract)
        {
            subtractTests();
            return;
        }

        if (!tickTests())
        {
            return;
        }
        System.out.println("All advanceOneSecond() tests passed.");
        if (!compareToTests())
        {
            return;
        }
        System.out.println("All compareTo() tests passed.");
        if (!addTests())
        {
            return;
        }
        System.out.println("All add() tests passed.");
        if (!subtractTests())
        {
            return;
        }
        System.out.println("All subtract() tests passed.");
        System.out.println("All tests passed.");
    }

    /**
     * Test the tick() method of a Time. All of these tests will run,
     * independent of individual failures.
     */
    public static boolean tickTests()
    {
        boolean allPassed = true;

        allPassed &= tickTest(new Time(0, 0, 0), "00:00:01");
        allPassed &= tickTest(new Time(0, 0, 58), "00:00:59");
        allPassed &= tickTest(new Time(0, 0, 59), "00:01:00");
        allPassed &= tickTest(new Time(0, 58, 59), "00:59:00");
        allPassed &= tickTest(new Time(00, 59, 59), "01:00:00");
        allPassed &= tickTest(new Time(23, 59, 59), "00:00:00");
        Time t = new Time(0, 0, 0);
        allPassed &= tickTest(t, "00:00:01");
        allPassed &= tickTest(t, "00:00:02"); // Same t, ticked twice
        return allPassed;
    }

    /**
     * Test the compareTo() method of a Time. All of these tests will run,
     * independent of individual failures.
     */
    public static boolean compareToTests()
    {
        boolean allPassed = true;

        Time t1 = new Time(0, 0, 4);
        Time t1Clone = new Time(0, 0, 4);
        allPassed &= compareToTest(t1, t1, 0);
        allPassed &= compareToTest(t1, t1Clone, 0);
        Time t2 = new Time(0, 0, 5);
        allPassed &= compareToTest(t1, t2, - 1);
        allPassed &= compareToTest(t2, t1, 1);
        allPassed &= compareToTest(new Time(2, 2, 2), new Time(1, 2, 2), 1);
        allPassed &= compareToTest(new Time(2, 2, 2), new Time(2, 1, 2), 1);
        allPassed &= compareToTest(new Time(2, 2, 2), new Time(2, 2, 1), 1);
        allPassed &= compareToTest(new Time(1, 2, 2), new Time(2, 2, 2), - 1);
        allPassed &= compareToTest(new Time(2, 1, 2), new Time(2, 2, 2), - 1);
        allPassed &= compareToTest(new Time(2, 2, 1), new Time(2, 2, 2), - 1);
        return allPassed;
    }

    /**
     * Test the add() method of a Time. All of these tests will run, independent
     * of individual failures.
     */
    public static boolean addTests()
    {
        boolean allPassed = true;
        allPassed &= addTest(new Time(1, 1, 1), new Time(2, 2, 2), "03:03:03");
        allPassed &= addTest(new Time(0, 0, 59), new Time(0, 0, 1), "00:01:00");
        allPassed &= addTest(new Time(0, 59, 0), new Time(0, 0, 1), "00:59:01");
        allPassed &= addTest(new Time(0, 59, 59), new Time(0, 0, 1), "01:00:00");
        allPassed &= addTest(new Time(23, 0, 0), new Time(1, 0, 0), "00:00:00");
        allPassed &= addTest(new Time(23, 59, 0), new Time(0, 1, 0), "00:00:00");
        allPassed &= addTest(new Time(23, 59, 59), new Time(0, 0, 1), "00:00:00");
        allPassed &= addTest(new Time(23, 59, 59), new Time(23, 59, 59), "23:59:58");
        return allPassed;
    }

    /**
     * Test the subtract() method of a Time. All of these tests will run,
     * independent of individual failures.
     */
    public static boolean subtractTests()
    {
        boolean allPassed = true;

        allPassed &= subtractTest(new Time(2, 2, 2), new Time(1, 1, 1), "01:01:01");
        allPassed &= subtractTest(new Time(0, 1, 0), new Time(0, 0, 1), "00:00:59");
        allPassed &= subtractTest(new Time(1, 0, 0), new Time(0, 1, 0), "00:59:00");
        allPassed &= subtractTest(new Time(1, 0, 0), new Time(0, 0, 1), "00:59:59");
        allPassed &= subtractTest(new Time(1, 1, 1), new Time(1, 1, 1), "00:00:00");
        allPassed &= subtractTest(new Time(1, 1, 1), new Time(0, 0, 2), "01:00:59");
        allPassed &= subtractTest(new Time(1, 1, 1), new Time(0, 2, 2), "00:58:59");
        allPassed &= subtractTest(new Time(1, 1, 1), new Time(2, 2, 2), "22:58:59");
        return allPassed;
    }

    /**
     * Implementation of an individual tick test.
     */
    private static boolean tickTest(Time t, String expected)
    {
        String orig = t.toString();
        t.advanceOneSecond();
        if (t.toString().equals(expected))
        {
            return true;
        }

        System.out.println(
                "Test: with Time " + orig + ", advanceOneSecond() failed. " + "Expected \"" + expected + "\", got \"" + t + "\"");
        return false;
    }

    /**
     * Implementation of an individual comparison test.
     */
    private static boolean compareToTest(Time t1, Time t2, int expected)
    {
        int result = t1.compareTo(t2);

        if (result == expected)
        {
            return true;
        }
        System.out.println(
                "Test: with Time " + t1 + ", compareTo(" + t2 + ") failed. " + "Expected \"" + expected + "\", got \"" + result + "\"");
        return false;
    }

    /**
     * Implementation of an individual addition test.
     */
    private static boolean addTest(Time t1, Time t2, String expected)
    {
        String hdr = "Test: with Time " + t1 + ", add(" + t2 + ") failed. ";
        String origT2 = t2.toString();

        t1.add(t2);

        if (!t2.toString()
                .equals(origT2))
        { // Second parameter should not be changed
            System.out.println(hdr + "Parameter changed from \"" + origT2 + "\"to \"" + t2 + "\"");
            return false;
        }

        if (!t1.toString()
                .equals(expected))
        {
            System.out.println(hdr + "Expected \"" + expected + "\", got \"" + t1 + "\"");
            return false;
        }

        return true;
    }

    /**
     * Implementation of an individual subtraction test.
     */
    private static boolean subtractTest(Time t1, Time t2, String expected)
    {
        String hdr = "Test: with Time " + t1 + ", subtract(" + t2 + ") failed. ";
        String origT2 = t2.toString();
        t1.subtract(t2);
        if (!t2.toString().equals(origT2))
        { // Second parameter should not be changed
            System.out.println(hdr + "Parameter changed from \"" + origT2 + "\"to \"" + t2 + "\"");
            return false;
        }

        if (!t1.toString()
                .equals(expected))
        {
            System.out.println(hdr + "Expected \"" + expected + "\", got \"" + t1 + "\"");
            return false;
        }

        return true;
    }
}
