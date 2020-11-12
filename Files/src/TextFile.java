import java.io.*;
import java.nio.file.Files;

public class TextFile extends File {
    private java.io.File file;
    java.io.File folder;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public TextFile() {

    }

    public TextFile(String fileName) throws IOException {
        createFile(fileName);
    }

    @Override
    public void createFile(String fileName) throws IOException {
        System.out.println("Enter the name of the folder where your file will be located:");
        String folderName = reader.readLine();
        Directory directory = new Directory(folderName);
        folder = directory.folder;

        file = new java.io.File(folder, fileName);
        try {
            boolean created = file.createNewFile();
            if (created)
                System.out.println("File has been created");
        } catch (IOException ex) {
            System.out.println("Exception!");
        }
    }

    @Override
    public void renameFile() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the new file name:");
        String newFileName = reader.readLine();
        Files.move(file.toPath(), file.toPath().resolveSibling(newFileName));
        file = new java.io.File(folder.getPath(), newFileName);
        System.out.println("File was renamed");
    }

    @Override
    public void printFile() throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(inputStream));
        while (reader.ready()) {
            System.out.println(reader.readLine());
        }
    }

    @Override
    public void addToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter text which you want add to file:");
        String string;
        while (!(string = reader.readLine()).equals("")) {
            writer.write(string);
        }
        writer.close();
    }

    @Override
    public void deleteFile() {
        if (file.delete()) {
            System.out.println(file.getAbsoluteFile() + " was deleted.");
        } else {
            System.out.println(file.getAbsoluteFile() + " wasn't found.");
        }
    }
}
