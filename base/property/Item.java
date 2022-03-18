package property;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Item {
    String name;
    int price;

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(~5));
        System.out.println(~5);
        BigDecimal bd = new BigDecimal(29999999954545459.455454);
        System.out.println(bd);


        int i = 0;
        loop:
        while (true) {
            i++;
            if (i > 10) break loop;
        }
        System.out.println(i);

        int[] arr = {2, 5, 6};
        for (int j : arr) {
            System.out.println(j);
        }

        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(list);

        int[] arr2 = new int[3];
        System.arraycopy(arr, 0, arr2, 0, 3);
        for (int i2 : arr2) {
            System.out.println(i2);
        }

        int[][] b = new int[5][];
        b[2] = new int[2];
        b[2][1] = 6;

        int[][] b2 = new int[5][4];
        b2[4][3] = 8;

        int[][] b3 = new int[][]{
                {1, 2, 3},
                {2, 554, 88}
        };
        System.out.println("————————————————————————————");
        int[] arr3= Arrays.copyOfRange(arr,1,6);
        String s = Arrays.toString(arr3);
        System.out.println(s);

        int[] arr4=new int[]{5,54,12,0,-364,6711,-332};
        System.out.println(Arrays.toString(arr4));
        Arrays.sort(arr4);
        System.out.println(Arrays.toString(arr4));
        System.out.println(Arrays.binarySearch(arr4, -332));

        int[] arr5=new int[]{5,54,12,0,-364,6711,-332};
        int[] arr6=new int[]{5,54,12,0,-364,6711,66666};
        System.out.println(Arrays.equals(arr5,arr6));

        int[] arr7=new int[10];
        Arrays.fill(arr7,6);
        System.out.println(Arrays.toString(arr7));

        Item item1=new Item();
        Item item2=item1;
        Item item3=item1;
        Item item4=item2;
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println(item4);
        item1=new Item();
        System.out.println(item1);
        System.out.println(item1 == item1);

    }
}