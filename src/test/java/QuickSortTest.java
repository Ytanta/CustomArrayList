import org.example.CustomArrayList;
import org.example.QuickSort;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testSortAscending() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(5);
        list.add(1);
        list.add(8);
        list.add(3);

        QuickSort.sort(list, Integer::compareTo);

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(8, list.get(3));
    }

    @Test
    void testSortDescending() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(2);
        list.add(10);
        list.add(5);
        list.add(1);

        QuickSort.sort(list, (a, b) -> b - a); // Сортировка по убыванию

        assertEquals(10, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(1, list.get(3));
    }

    @Test
    void testSortEmptyList() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        QuickSort.sort(list, Integer::compareTo);

        assertEquals(0, list.size());
    }

    @Test
    void testSortSingleElement() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(42);

        QuickSort.sort(list, Integer::compareTo);

        assertEquals(1, list.size());
        assertEquals(42, list.get(0));
    }

    @Test
    void testSortAlreadySorted() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        QuickSort.sort(list, Integer::compareTo);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test
    void testSortNullList() {
        assertThrows(NullPointerException.class, () -> QuickSort.sort(null, Integer::compareTo));
    }



    @Test
    void testSortLargeList() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        Random random = new Random();
        int size = 1000;
        Integer[] expected = new Integer[size];

        for (int i = 0; i < size; i++) {
            int value = random.nextInt(10000);
            list.add(value);
            expected[i] = value;
        }

        QuickSort.sort(list, Integer::compareTo);
        java.util.Arrays.sort(expected);

        for (int i = 0; i < size; i++) {
            assertEquals(expected[i], list.get(i));
        }
    }
}