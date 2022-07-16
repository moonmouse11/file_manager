import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager("home/");

        String input = scanner.nextLine();

        while(!input.equals(Commands.EXIT)){
            String[] tokens = input.split(" ");
            String command = tokens[0];
            switch (command) {
                case Commands.LIST_OF_FILES -> fileManager.listOfFiles(false);
                case Commands.LIST_OF_FILE_WITH_SIZE -> fileManager.listOfFiles(true);
                case Commands.COPY_FILE -> {
                    String sourceFileName = tokens[1];
                    String destinationFileName = tokens[2];
                    fileManager.copyFile(sourceFileName, destinationFileName);
                }
                case Commands.CREATE_FILE -> {
                    String fileName = tokens[1];
                    fileManager.createFile(fileName);
                }
                case Commands.CHANGE_DIRECTORY -> {
                    String directoryName = tokens[1];
                    fileManager.changeDirectory(directoryName);
                }
                case Commands.FILE_CONTENT -> {
                    String fileName = tokens[1];
                    fileManager.fileContent(fileName);
                }
            }
            input = scanner.nextLine();

        }
    }
}
