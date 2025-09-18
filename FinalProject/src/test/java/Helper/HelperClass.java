package Helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class HelperClass {

    private static final String TestPrjRoot = "src/test/java/";
    private static final String TestDataFolder = "TestingData/";
    
    public static String ReadFromFile(String fileName, String Key) throws FileNotFoundException
    {
        FileReader reader = new FileReader(TestPrjRoot+TestDataFolder+fileName);
        JsonElement e1 = JsonParser.parseReader(reader);
        return e1.getAsJsonObject().get(Key).getAsString();
    }

    public static <T> T[] ReadUsers (String fileName, Class<T[]> data) throws FileNotFoundException
    {
        FileReader reader = new FileReader(TestPrjRoot+TestDataFolder+fileName);
        return new Gson().fromJson(reader, data);
    }
    
}
