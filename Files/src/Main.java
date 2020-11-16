import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException, IllegalFormat {
        System.out.println("Please enter the name of the file with specifying the extension (.txt) you want to create:");
        String fileName = reader.readLine();
        TextFile file = new TextFile(fileName);
        file.renameFile();
        file.addToFile();
        file.printFile();
        file.deleteFile();
    }
}
