package lists;

import java.util.ArrayList;

/**
 *
 * @author Tony
 */
public class ListTester
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ListTester tester = new ListTester();
        
        // Create an instance of your list to test here!
        SimplifiedList listUnderTest = new EmptyList();
        
        tester.testHappyPaths(listUnderTest);
        tester.testEmpty(listUnderTest);
        tester.edgeCases(listUnderTest);
        if (listUnderTest instanceof AdvancedList)
        {
            tester.testAdvanced((AdvancedList) listUnderTest);
            tester.advancedEdgeCases((AdvancedList) listUnderTest);
        }
    }   


    String[] testValues = {"a", "b", "c", "d", "e"};
    String[] advancedValues = {"a", "b", "a", "d", "b"};

    private void testEmpty(SimplifiedList list)
    {
        try
        {
            int size = list.size();
            System.out.printf("Empty Test - size() %s\n", size == 0 ? "passed" : "failed- expected 0 got " + size);
        } catch (Exception e)
        {
            System.out.printf("Empty Test - size() failed- expected 0 got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        try
        {
            System.out.printf("Empty Test - isEmpty() %s\n", list.isEmpty() ? "passed" : "failed- expected true got false");
        } catch (Exception e)
        {
            System.out.printf("Empty Test - isEmpty() failed- expected true got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
            
        try
        {
            Object result = list.remove(1);
            System.out.printf("Empty Test - remove(1) %s\n", result == null ? "passed" : "failed- expected null got " + result);
        } catch (Exception e)
        {
            System.out.printf("Empty Test - remove(1) failed- expected null got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
       }

        try
        {
            Object result = list.get(1);
            System.out.printf("Empty Test - get(1) %s\n", result == null ? "passed" : "failed- expected null got " + result);
        } catch (Exception e)
        {
            System.out.printf("Empty Test - get(1) failed- expected null got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
       }

        try
        {
            Object result = list.get(1);
            System.out.printf("Empty Test - set(1) %s\n", result == null ? "passed" : "failed- expected null got " + result);
        } catch (Exception e)
        {
            System.out.printf("Empty Test - set(1) failed- expected null got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
       }
    }

    /**
     * Fills the provided list with the test values.
     * Should be classed after the Happy Path test of add is confirmed
     * @param list the list to fill
     */
    private void fillList(SimplifiedList list, String[] with)
    {
        for (String value : with)
        {
            list.add(value);
        }        
    }
    
    private void testHappyPaths(SimplifiedList list)
    {
        // ..........Test adds
        try
        {
            boolean passed = true;
            int count = 0;
            for (String value : testValues)
            {
                boolean result = list.add(value);
                count = count + 1;
                if (! result)
                {
                    passed = false;
                    System.out.printf("Happy Path Test - add(%s) returned false when expected true\n", value);
                }
                int size = list.size();
                if (size != count)
                {
                    passed = false;
                    System.out.printf("Happy Path Test - size() returned %d when expected %d\n", count, size);
                }
            }
            System.out.printf("Happy Path Test - add() and size() %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Happy Path Test - add(1) failed- expected null got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        
        // ..........Test get
        try
        {
            boolean passed = true;
            for(int i = 0; i < testValues.length; i++)
            {
                Object value = list.get(i);
                if (! testValues[i].equals(value))
                {
                    passed = false;
                    System.out.printf("Happy Path Test - get() failed- got %s, expected %s\n", value, testValues[i]);
                }
            }
            System.out.printf("Happy Path Test - get() %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Happy Path Test - get() failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        
        //........Test Set
        try
        {
            boolean passed = true;
            for(int i = 0; i < testValues.length; i++)
            {
                int reverseValue = testValues.length - 1 - i;
                Object value = list.set(i, testValues[reverseValue]);
                if (value != testValues[i])
                {
                    passed = false;
                    System.out.printf("Happy Path Test - set() failed- got %s, expected %s\n", value, testValues[i]);
                }
                
                value = list.get(i);
                if (! testValues[reverseValue].equals(value))
                {
                    passed = false;
                    System.out.printf("Happy Path Test - get() after set() failed- got %s, expected %s\n", value, testValues[reverseValue]);
                }
            }
            System.out.printf("Happy Path Test - set() %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Happy Path Test - set() failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

         // .............. Test clear()
        try
        {
            list.clear();
            int size = list.size();
            if (size == 0 && list.isEmpty())
            {
                System.out.printf("Happy Path Test - clear() passed\n");
            } else
            {
                System.out.printf("Happy Path Test - clear() failed- got: %d or isEmpty()= %s\n", size, list.isEmpty());
            }
        } catch (Exception e)
        {
            System.out.printf("Happy Path Test - clear() failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        
        //........Test Remove
        try
        {
            boolean passed = true;
            fillList(list, testValues);
            for(int i = 0; i < testValues.length; i++)
            {
                Object value = list.remove(0);
                if (value != testValues[i])
                {
                    passed = false;
                    System.out.printf("Happy Path Test - remove(forward) failed- got %s, expected %s\n", value, testValues[i]);
                }
            }

            fillList(list, testValues);
            for(int i = testValues.length - 1; i >= 0; i--)
            {
                Object value = list.remove(i);
                if (value != testValues[i])
                {
                    passed = false;
                    System.out.printf("Happy Path Test - remove(backward) failed- got %s, expected %s\n", value, testValues[i]);
                }
            }

            fillList(list, testValues);
            ArrayList exemplar = new ArrayList();
            for (String value : testValues)
            {
                exemplar.add(value);
            }
            while(! list.isEmpty())
            {
                int index = list.size()/2;
                Object value = list.remove(index);
                Object expected = exemplar.remove(index);
                if (value != expected)
                {
                    passed = false;
                    System.out.printf("Happy Path Test - remove(middle) failed- got %s, expected %s\n", value, expected);
                }
            }
            
            System.out.printf("Happy Path Test - remove() %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Happy Path Test - remove() failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
    
    private void edgeCases(SimplifiedList list)
    {
        list.clear();
        fillList(list, testValues);
        
        //........Test Outside Bounds
        try
        {
            boolean passed = true;
            Object value;
            value = list.get(-1);
            if (value != null || list.size() != testValues.length)
            {
                passed = false;
                System.out.printf("Edge Cases Test - get(-1) failed- got %s, expected null\n", value);
            }
            value = list.set(-1, "!");
            if (value != null || list.size() != testValues.length)
            {
                passed = false;
                System.out.printf("Edge Cases Test - set(-1) failed- got %s, expected null\n", value);
            }
            value = list.remove(-1);
            if (value != null || list.size() != testValues.length)
            {
                passed = false;
                System.out.printf("Edge Cases Test - remove(-1) failed- got %s, expected null\n", value);
            }
            System.out.printf("Edge Cases Test - negative indicies %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Edge Cases Test - negative indicies failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        try
        {
            boolean passed = true;
            Object value;
            value = list.get(6);
            if (value != null || list.size() != testValues.length)
            {
                passed = false;
                System.out.printf("Edge Cases Test - get(6) failed- got %s, expected null\n", value);
            }
            value = list.set(6, "!");
            if (value != null || list.size() != testValues.length)
            {
                passed = false;
                System.out.printf("Edge Cases Test - set(6) failed- got %s, expected null\n", value);
            }
            value = list.remove(6);
            if (value != null || list.size() != testValues.length)
            {
                passed = false;
                System.out.printf("Edge Cases Test - remove(6) failed- got %s, expected null\n", value);
            }
            System.out.printf("Edge Cases Test - too large indicies %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Edge Cases Test - too large indicies failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void testAdvanced(AdvancedList list)
    {
        list.clear();
        fillList(list, advancedValues);
        try
        {
            boolean passed = true;
            if (! (list.contains("a") && list.contains("b")))
            {
                passed = false;
                System.out.printf("Advanced Happy Path Test - contains() failed, did not contain expected values\n");
            }

            if (list.contains("c"))
            {
                passed = false;
                System.out.printf("Advanced Happy Path Test - contains() failed, should not contain value \"c\"\n");
            }
            System.out.printf("Advanced Happy Path Test - contains() %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Advanced Happy Path Test - contains() failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        ArrayList exemplar = new ArrayList();
        for (String value : advancedValues)
        {
            exemplar.add(value);
        }
        
        try
        {
            boolean passed = true;
            int value = list.indexOf("a");
            int expected = exemplar.indexOf("a");
            if (value != expected)
            {
                passed = false;
                System.out.printf("Advanced Happy Path Test - indexOf(\"a\") failed, got %d, expected %d\n", value, expected);
            }
            value = list.indexOf("c");
            if (value != -1)
            {
                passed = false;
                System.out.printf("Advanced Happy Path Test - indexOf(\"c\") failed, got %d, expected -1\n", value);
            }
            
            System.out.printf("Advanced Happy Path Test - indexOf() %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Advanced Happy Path Test - indexOf() failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        try
        {
            boolean passed = true;
            int value = list.lastIndexOf("a");
            int expected = exemplar.lastIndexOf("a");
            if (value != expected)
            {
                passed = false;
                System.out.printf("Advanced Happy Path Test - lastIndexOf(\"a\") failed, got %d, expected %d\n", value, expected);
            }
            value = list.lastIndexOf("c");
            if (value != -1)
            {
                passed = false;
                System.out.printf("Advanced Happy Path Test - lastIndexOf(\"c\") failed, got %d, expected -1\n", value);
            }
            
            System.out.printf("Advanced Happy Path Test - lastIndexOf() %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Advanced Happy Path Test - lastIndexOf() failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        //........Test Remove
        try
        {
            boolean passed = true;
            for(String original : advancedValues)
            {
                if (! list.remove(original))
                {
                    passed = false;
                    System.out.printf("Advanced Happy Path Test - remove(by object forward) failed- returned false\n");
                }
            }

            fillList(list, advancedValues);
            for(int i = advancedValues.length - 1; i >= 0; i--)
            {
                if (! list.remove(advancedValues[i]))
                {
                    passed = false;
                    System.out.printf("Advanced Happy Path Test - remove(by object backward) failed- returned false on %s at %d\n", advancedValues[i], i);
                }
            }

            System.out.printf("Advanced Happy Path Test - remove(by object) %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Advanced Happy Path Test - remove(by object) failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
        
    private void advancedEdgeCases(AdvancedList list)
    {
        list.clear();
        fillList(list, advancedValues);
        
        //........Test Outside Bounds
        try
        {
            boolean passed = true;
            int value;
            value = list.indexOf("c");
            if (value != -1)
            {
                passed = false;
                System.out.printf("Advanced Edge Cases Test - indexOf(\"c\") failed- got %s, expected -1\n", value);
            }
            value = list.lastIndexOf("c");
            if (value != -1)
            {
                passed = false;
                System.out.printf("Advanced Edge Cases Test - indexOf(\"c\") failed- got %s, expected -1\n", value);
            }
            if (list.remove("c"))
            {
                passed = false;
                System.out.printf("Advanced Edge Cases Test - remove(\"c\") failed- reported true\n", value);
            }
            System.out.printf("Advanced Edge Cases Test - not present %s\n", passed ? "passed" : "failed");
        } catch (Exception e)
        {
            System.out.printf("Advanced Edge Cases Test - negative indicies failed- got Exception: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
}