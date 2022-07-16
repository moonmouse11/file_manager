import org.apache.commons.io.FileUtils;

import java.io.*;

public class FileManager {

    private String currentFolder;
    private String root;

    public FileManager(String currentFolder) {
        this.currentFolder = currentFolder;
        this.root = currentFolder;
    }

    public void listOfFiles(boolean withSize) {
        File currentFolderAsFile = new File(currentFolder);
        File[] files = currentFolderAsFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (withSize) {
                    System.out.println(file.getName() + "\\  " + FileUtils.sizeOf(file));
                } else {
                    System.out.println(file.getName() + "\\");
                }
            } else {
                if (withSize) {
                    System.out.println(file.getName() + "  " + file.length());
                } else {
                    System.out.println(file.getName());
                }
            }
        }
        System.out.println();
    }


    public void copyFile(String sourceFileName, String destinationFileName) {
        File source = new File(currentFolder + File.separator + sourceFileName);
        File destination = new File(currentFolder + File.separator + destinationFileName);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            System.out.println("Файл не скопирован");
        }
    }

    public void createFile(String fileName) {
        File toCreate = new File(currentFolder + File.separator + fileName);
        try {
            FileUtils.touch(toCreate);
        } catch (IOException e) {
            System.out.println("Файл не создан");
        }
    }

    public void changeDirectory(String directoryName) {
        if (directoryName.equals("/")) {
            this.currentFolder = this.root;
            System.out.println(currentFolder);
        } else if (directoryName.equals("..")) {
            int startLastFolderPosition = this.currentFolder.lastIndexOf(File.separator);
            this.currentFolder = this.currentFolder.substring(0, startLastFolderPosition);
            System.out.println(currentFolder);
        } else if (this.currentFolder.contains(directoryName)) {
            String [] endFolderPosition = this.currentFolder.split(directoryName);
            this.currentFolder = endFolderPosition[0] + directoryName;
            System.out.println(this.currentFolder);
        } else {
            this.currentFolder = this.currentFolder + File.separator + directoryName;
            System.out.println(currentFolder);
        }

    }

    public void fileContent(String fileName) {
        File file = new File(currentFolder + File.separator + fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл");
        }
    }
}
