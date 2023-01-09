import java.util.HashMap;
import java.util.Map;

public class Contact {
    public static void contact(){
        int i ;
        Map<String, String> i = new HashMap<>(); 
        i.put("Numéro", "0");
        i.put("Nom", "Le nom");
        i.put("rénom", "Prénom");
        i.add(new String("Autre nom"));
        i.get("Nom");
    }
}
