import org.example.CustomArrayList;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


class   CustomArrayListTest {

    @Test
    void testAddAndGet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        assertEquals(10, list.get(0));
    }

    @Test
    void testAddAtIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        list.add(20);
        list.add(1, 15);
        assertEquals(15, list.get(1));
    }

    @Test
    void testRemove() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        list.add(20);
        list.remove(0);
        assertEquals(20, list.get(0));
    }

    @Test
    void testClear() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        list.add(20);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testSet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        list.set(0, 99);
        assertEquals(99, list.get(0));
    }

    @Test
    void testSort() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(30);
        list.add(10);
        list.add(20);
        list.sort(Comparator.naturalOrder());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testStressTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        assertEquals(1000, list.size());

        for (int i = 999; i >= 0; i--) {
            list.remove(i);
        }
        assertEquals(0, list.size());
    }

    @Test
    void testIndexOutOfBounds() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }
}