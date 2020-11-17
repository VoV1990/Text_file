import java.io.*;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Создать объект класса Текстовый файл, используя классы Файл, Директория.
//Методы: создать, переименовать, вывести на консоль содержимое, дополнить, удалить.

public class TextFile extends File {
    private java.io.File file;
    java.io.File folder;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public TextFile(String fileName) throws IOException, IllegalFormat {
        if(validateForTxt(fileName)) createFile(fileName);
        else throw new IllegalFormat("It's not txt file name.");
    }

    private boolean validateForTxt(String filename) {
        Pattern pattern = Pattern.compile("\\w+\\.txt");
        Matcher matcher = pattern.matcher(filename);
        return matcher.matches();
    }

    private void createFile(String fileName) throws IOException {
        System.out.println("Enter the name of the folder where your file will be located:");
        String folderName = reader.readLine();
        Directory directory = new Directory(folderName);
        folder = directory.getFolder();

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
    public void renameFile() throws IOException, IllegalFormat {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the new file name:");
        String newFileName = reader.readLine();
        if(validateForTxt(newFileName)) {
            Files.move(file.toPath(), file.toPath().resolveSibling(newFileName));
            file = new java.io.File(folder.getPath(), newFileName);
            System.out.println("File was renamed");
        } else throw new IllegalFormat("It's not txt file name.");
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
            writer.write(string + " ");
        }
        writer.close();
    }

    @Override
    public void deleteFile() throws IOException {
        reader.close();
        System.out.println(file.getPath());
        System.out.println(file.getName());
        if (file.delete()) {
            System.out.println(file.getAbsoluteFile() + " was deleted.");
        } else {
            System.out.println(file.getAbsoluteFile() + " wasn't found.");
        }
    }
}
