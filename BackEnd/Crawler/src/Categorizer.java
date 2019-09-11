import java.io.*;
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

    public void MarkOff(){
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("C:\\Users\\dordo\\AndroidStudioProjects\\SummerProject\\BackEnd\\DataBase\\notmarkedoff5.csv", true));
            BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\dordo\\AndroidStudioProjects\\SummerProject\\BackEnd\\DataBase\\notmarkedoff4.csv"));
            String line1;
            while((line1 = br1.readLine()) != null){
                BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\dordo\\AndroidStudioProjects\\SummerProject\\BackEnd\\DataBase\\CompressedIngridientTable1.csv"));
                boolean found=false;
                String line2;
                while((line2 = br2.readLine()) != null){
                    String[] seperated=line2.split(",");
                    for(int i=0; i<seperated.length; i++){
                        if(line1.contains(seperated[i])){
                            found=true;
                            break;
                        }
                    }
                    if(found)
                        break;
                }
                if(!found){
                    bw.newLine();
                    bw.write(line1);
                }
                br2.close();
            }
            bw.close();
            br1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
