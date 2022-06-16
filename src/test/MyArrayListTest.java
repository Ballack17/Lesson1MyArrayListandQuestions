package test;

import main.MyArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyArrayListTest {

    @Test
    void shouldAddElementToArray() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        Assertions.assertTrue(myArrayList.size() == 0);
        Assertions.assertFalse(myArrayList.contains(5));

        myArrayList.add(5);

        Assertions.assertTrue(myArrayList.contains(5));
    }

    @Test
    void shouldAddThenRemoveElementFromArray() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        Assertions.assertTrue(myArrayList.size() == 0);
        Assertions.assertFalse(myArrayList.contains(5));
        myArrayList.add(5);
        Assertions.assertTrue(myArrayList.contains(5));

        myArrayList.remove(5);

        Assertions.assertFalse(myArrayList.contains(5));
    }

    @Test
    void shouldSetElementByIndex() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        Assertions.assertTrue(myArrayList.size() == 0);
        Assertions.assertFalse(myArrayList.contains(10));
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        Assertions.assertTrue(myArrayList.get(0).equals(1));

        myArrayList.set(0, 10);

        Assertions.assertTrue(myArrayList.get(0).equals(10));
    }

    @Test
    void shouldAutoGrow() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>(2);
        Assertions.assertTrue(myArrayList.size() == 0);
        myArrayList.add(1);
        myArrayList.add(2);

        myArrayList.add(3);

        Assertions.assertTrue(myArrayList.contains(3));
        Assertions.assertTrue(myArrayList.size() == 3);
    }

    @Test
    void shouldBeSorted() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        Assertions.assertTrue(myArrayList.size() == 0);
        myArrayList.add(8);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(3);
        myArrayList.add(2);
        myArrayList.add(1);
        Assertions.assertTrue(myArrayList.get(0).equals(8));
        Assertions.assertTrue(myArrayList.get(5).equals(1));

        myArrayList.quickSort();

        Assertions.assertTrue(myArrayList.get(0).equals(1));
        Assertions.assertTrue(myArrayList.get(5).equals(8));
    }

    @Test
    void shouldThrowException() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        Assertions.assertTrue(myArrayList.size() == 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {myArrayList.set(5,1);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {myArrayList.get(5);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {myArrayList.add(5,5);});
    }
}
