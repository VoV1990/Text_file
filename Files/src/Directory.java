import java.io.File;

public class Directory {
    private File folder;
    public Directory(String folderName) {
        folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public File getFolder() {
        return folder;
    }

}
