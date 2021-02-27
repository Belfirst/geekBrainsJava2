import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private Map<String,String> map = new HashMap<String, String>();

    public void add(String name, String phoneNumber){
        if(map.containsKey(name)){
            map.put(name,phoneNumber + ", " + map.get(name));
        } else map.put(name,phoneNumber);
    }

    public void get(String name){
        if(map.get(name) == null){
            System.out.println(name + ": Last name is not in the phone book");
        } else System.out.println(name + ": " + map.get(name));

    }
}
