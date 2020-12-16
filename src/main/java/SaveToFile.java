import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToFile {
    String filename;
    FileWriter newFile;
    SaveToFile(String filename){
        this.filename = filename;
        this.newFile = createFile();
    }

    public FileWriter createFile() {
        try {
            return new FileWriter(filename + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void writeToFile(ArrayList<String> content, String separator){
        try {
            for (String obj : content) {
                newFile.write(obj + separator);
            }
            newFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
