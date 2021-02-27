import java.util.*;

public class Basic {
    public static void main(String[] args) {

        String[] array = {"A", "B", "C", "D", "E", "F", "D", "A", "A", "A", "J", "G", "M", "N", "Z"};
        List<String> list = new ArrayList<String>(Arrays.asList(array));
        Iterator<String> iter = list.listIterator();
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        Set<String> set = new HashSet<String>(Arrays.asList(array));
        System.out.println("Уникальные слова");
        System.out.println("Set: " + set);

        // я конечно нашел метод frequency(colls, obj), но подумал так интереснее
        while (iter.hasNext()) {
            Integer count = 0;
            String str = iter.next();
            for(String s : array){
                if(s.equals(str))
                    count++;
            }
            hm.put(str,count);
        }
        System.out.print("Map: ");
        for(Map.Entry<String,Integer> o : hm.entrySet()){
            System.out.print(o.getKey() + ":" + o.getValue() + "  ");
        }
        System.out.println();

        //Задание 2

        Phonebook book = new Phonebook();
        book.add("Petrov", "+79032213141");
        book.add("Ivanov", "+79053324321");
        book.add("Sidorov", "+79154335600");
        book.add("Petrov", "+79266895632");

        book.get("Petrov");
        book.get("Sidorov");
        book.get("Smirnov");

    }
}
