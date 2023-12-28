package pro.sky.javacoursepart2;

import pro.sky.javacoursepart2.stringList.StringListImpl;
import pro.sky.javacoursepart2.stringList.StringList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringList list = new StringListImpl();
        list.add("0", "1", "2");
        System.out.println(list.add("первый элемент"));
        System.out.println(list);
        System.out.println(list.add("второй элемент"));
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.add("Третий Элемент", "Четвертый элемент"));
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(list.add(9, "Пятый элемент"));
        System.out.println(list);
        System.out.println(list.add(3, "Шестой элемент", "Седьмой элемент"));
        System.out.println(list);
//        System.out.println(list.set(1, "1", "2", "3","4", "5", "6", "7", "8", "9"));
//        System.out.println(list);
//        System.out.println(list.size());
////        System.out.println(list.remove("первый элемент"));
////        System.out.println(list);
//        System.out.println(list.remove(9));
//        System.out.println(list);
//        System.out.println(list.contains("8"));
//        System.out.println(list.indexOf("первый элемент"));
//        System.out.println(list.lastIndexOf("0"));
//        System.out.println(list.get(0));
//        System.out.println(list.isEmpty());
//        System.out.println(list);
//        System.out.println(list.get(2));
//        System.out.println(list);
//        String[]list2 = list.toArray();
//        System.out.println(list2.equals(list));
//        System.out.println(list);
//        System.out.println(list.remove(5));
//        System.out.println(list);
//        list.add();
//        System.out.println(list);
//        System.out.println(list.size());
    }
}