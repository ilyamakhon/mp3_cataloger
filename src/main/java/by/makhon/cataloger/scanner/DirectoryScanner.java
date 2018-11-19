package by.makhon.cataloger.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DirectoryScanner scans all directories and than creates list of files
 */
public class DirectoryScanner {

    private List<File> filesList = new ArrayList<>();

    public List<File> getFilesList() {
        return filesList;
    }

    public void scanDirectories(String[] paths) {
        List<File> directories = new ArrayList<>();
        if (paths.length == 0) {
            System.out.println("No directories to scan");
        }
        for (String path : paths) {
            directories.add(new File(path));
        }

        for (File directory : directories) {
            if (directory.listFiles() != null) {
                for (File dirItem : directory.listFiles()) {
                    if (dirItem.isFile()) {
                        filesList.add(dirItem);
                    }
                    if (dirItem.isDirectory()) {
                        String[] iterableListOfDirs = {dirItem.getAbsolutePath()};
                        scanDirectories(iterableListOfDirs);
                    }
                }
            } else {
                System.out.println("There are no files to scan (PLEASE CHECK DIRECTORY PATH): " + directory.getAbsolutePath());
            }
        }
    }

}
