import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Categorizer {
    public static String Categorize(String csvPath){
        String[] banned = {"פרוסות","מל","קילו","לא חובה","מגורדת"};
        String resultCsv = "categorized.csv";
        List<String> lines = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath))){
            lines = ReadFromCsvToList(bufferedReader);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(lines == null){
            return csvPath;
        }
        HashMap<String, List> hashMap = new HashMap<>();
        for (String key : hashMap.keySet());
        return resultCsv;
    }

    private static List ReadFromCsvToList(BufferedReader bufferedReader){
        List<String> lines = new ArrayList<>();
        String line;
        try{
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lines.isEmpty() ? null : lines;
    }
}
