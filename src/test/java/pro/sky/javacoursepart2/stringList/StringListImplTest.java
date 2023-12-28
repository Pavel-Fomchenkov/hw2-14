package pro.sky.javacoursepart2.stringList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.javacoursepart2.exceptions.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    private StringList list = new StringListImpl();

    private void setStringList() {
        list.add("0", "1", "2", "3", "4", "5", "6", "7");
    }

    @Test
    void constructorWithNegativeCapacityExceptionTest() {
        assertThrows(IllegalCapacityException.class, () -> new StringListImpl(-1));
        assertThrows(IllegalCapacityException.class, () -> new StringListImpl(0));
    }

    @Test
    @BeforeEach
    void clearMethodShouldMakeStringListEmpty() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void addMethodShouldAddStringToStringListAndReturnAddedString() {
        String expected = "первый";
        assertEquals(expected, list.add("первый"));
        expected = "второй";
        assertEquals(expected, list.add("второй"));
        expected = "[первый, второй]";
        assertEquals(expected, list.toString());
    }

    @Test
    void addMethodShouldThrowExceptionWhenReceivesNullAsArgument() {
        String s = null;
        assertThrows(NothingToAddException.class, () -> list.add(s));
    }

    @Test
    void addMethodShouldThrowExceptionWhenNoArgumentsArePassed() {
        assertThrows(NothingToAddException.class, () -> list.add());
    }

    @Test
    void addMethodShouldThrowExceptionWhenTooManyStringsArePassed() {
        setStringList();
        assertThrows(InsufficientCapacityException.class, () -> list.add("8", "9", "10"));
    }

    @Test
    void addMethodWithIndexAndMultipleStringsExceptions() {
        // if index is negative
        assertThrows(IllegalIndexException.class, () -> list.add(-3, "0", "1"));
        assertThrows(NothingToAddException.class, () -> list.add(1));
        assertThrows(NothingToAddException.class, () -> list.add(1));
    }

    @Test
    void addOverloadedWithIntAndStringShouldPutAddedItemOnSpecifiedOrLastPositionAndReturnItem() {
        setStringList();
        String expected = "g";
        assertEquals(expected, list.add(9, "g"));
        expected = "S";
        assertEquals(expected, list.add(0, "S"));
        expected = "[S, 0, 1, 2, 3, 4, 5, 6, 7, g]";
        assertEquals(expected, list.toString());
    }

    @Test
    void addOverloadedWithSpreadOperatorStringArgumentShouldAddSomeItemsToStringListAndReturnArrayOfAddedStrings() {
        list.add("4", "5", "6", "7");
        String expected = "[0, 1, 2]";
        String actual = Arrays.toString(list.add("0", "1", "2"));
        assertEquals(expected, actual);
        expected = "[4, 5, 6, 7, 0, 1, 2]";
        assertEquals(expected, list.toString());
    }

    @Test
    void addOverloadedMethodWithIntAndSpreadStringsShouldPutAddedStringsOnFromSpecifiedIndexOrFirstEmptyIndexAndReturnArrayOfAddedStrings() {
        list.add(2, "0", "1", "2");
        String expected = "[0, 1, 2]";
        assertEquals(expected, list.toString());
        expected = "[3, 4, 5]";
        assertEquals(expected, Arrays.toString(list.add(6, "3", "4", "5")));
        expected = "[0, 1, 2, 3, 4, 5]";
        assertEquals(expected, list.toString());
    }

    @Test
    void setMethodWillReplaceActualItemWithNewAndReturnsAddedItemRequiresAddMethodToWorkCorrectly() {
        setStringList();
        String expected = "new";
        assertEquals(expected, list.set(3, "new"));
        expected = "[0, 1, 2, new, 4, 5, 6, 7]";
        assertEquals(expected, list.toString());
    }

    @Test
    void setOverloadedWithIntAndSpreadStringsWillOverwriteExistingItemsFromDesiredIndexAndReturnArrayOfAddedStrings() {
        list.add("0", "1", "2");
        String expected = "[4, 5]";
        assertEquals(expected, Arrays.toString(list.set(8, "4", "5")));
        expected = "[0, 1, 2, four, five]";
        list.set(3, "four", "five");
        assertEquals(expected, list.toString());
    }

    @Test
    void removeWithStringParameterShouldDeleteStringAndReturnDeleted() {
        setStringList();
        String expected = "7";
        assertEquals(expected, list.remove("7"));
        expected = "[0, 1, 2, 3, 4, 5, 6]";
        assertEquals(expected, list.toString());
    }
    @Test
    void removeWithStringParameterThrowsAnExceptionWhenStringIsNotFound() {
        setStringList();
        assertThrows(ItemNotFoundException.class, () -> list.remove("apple"));
    }

    @Test
    void removeWithIntParameterDeletesItemFromStringListAndReturnsIt() {
        setStringList();
        String expected = "3";
        assertEquals("3", list.remove(3));
        expected = "[0, 1, 2, 4, 5, 6, 7]";
        assertEquals(expected, list.toString());
    }

    @Test
    void removeWithIntParameterThrowsAnExceptionWhenIndexIsNegativeOrHigherThanSize() {
        setStringList();
        assertThrows(IllegalIndexException.class, () -> list.remove(-2));
        assertThrows(IllegalIndexException.class, () -> list.remove(9));
    }


    @Test
    void containsReturnsTrueWhenStringListContainsGivenString() {
        setStringList();
        assertTrue(list.contains("0"));
        assertTrue(list.contains("7"));
        assertFalse(list.contains("8"));
        assertFalse(list.contains(""));
    }

    @Test
    void indexOfMethodShouldReturnFirstIndexOfStringArgumentOrMinusOne() {
        int expected = -1;
        assertEquals(expected, list.indexOf("Any string"));
        setStringList();
        expected = 7;
        assertEquals(expected, list.indexOf("7"));
        expected = 4;
        list.set(4, "7");
        assertEquals(expected, list.indexOf("7"));
    }

    @Test
    void lastIndexOfMethodShouldReturnLastIndexOfStringArgumentOrMinusOne() {
        int expected = -1;
        assertEquals(expected, list.lastIndexOf("Any string"));
        setStringList();
        expected = 3;
        assertEquals(expected, list.lastIndexOf("3"));
        list.set(5, "3");
        expected = 5;
        assertEquals(expected, list.lastIndexOf("3"));
    }

    @Test
    void getMethodReturnsItemByIndexOrThrowsAnExceptionIfIndexIsNotWithinSize() {
        assertThrows(IllegalIndexException.class, () -> list.get(0));
        setStringList();
        assertEquals("5", list.get(5));
    }

    @Test
    void getMethodThrowsAnExceptionWhenIndexIsNegativeOrMoreOrEqualThanSize() {
        setStringList();
        assertThrows(IllegalIndexException.class, () -> list.get(-1));
        assertThrows(IllegalIndexException.class, () -> list.get(8));
    }

    @Test
    void equalsMethodChecksTwoStringListsForEqualityByContentNotTheReference() {
        StringList list2 = new StringListImpl();
        assertTrue(list.equals(list2));
        setStringList();
        list2.add("0", "1", "2", "3", "4", "5", "6", "7");
        assertTrue(list.equals(list2));
        list.remove(0);
        assertFalse(list.equals(list2));
    }

    @Test
    void sizeShouldReturnQuantityOfItemsInStringList() {
        assertEquals(0, list.size());
        setStringList();
        assertEquals(8, list.size());
    }

    @Test
    void isEmptyReturnsFalseOrTrueWhenStringListIsEmptyOrNot() {
        assertTrue(list.isEmpty());
        setStringList();
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void toArray() {
        setStringList();
        String[] expected = {"0", "1", "2", "3", "4", "5", "6", "7"};
        assertEquals(Arrays.toString(expected), Arrays.toString(list.toArray()));
    }

    @Test
    void toStringShouldReturnStringOfItemsSeparatedByCommaWithinSquareBrackets() {
        assertEquals("[]", list.toString());
        setStringList();
        String expected = "[0, 1, 2, 3, 4, 5, 6, 7]";
        assertEquals(expected, list.toString());
    }

}