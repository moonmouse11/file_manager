import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File main = new File("main");
        FileManager fileManager = new FileManager(File.separator + main.getAbsolutePath().split(File.separator)[1]);
        System.out.println("Консольный файл менеджер с командами bash");
        System.out.println("help для просмотра команд");

        String input = scanner.nextLine();

        while (!input.equals(Commands.EXIT)) {
            try {
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
                    case Commands.MAKE_DIRECTORY -> {
                        String directoryName = tokens[1];
                        fileManager.makeDirectory(directoryName);
                    }
                    case Commands.HELP -> {
                        if(tokens.length == 1) {
                            fileManager.help();
                        } else {
                            String commandForHelp = tokens[1];
                            Help.showTutorial(commandForHelp);
                        }
                    }
                    case Commands.REMOVE_FILE -> {
                        String removeFile = tokens[1];
                        fileManager.removeFile(removeFile);
                    }
                    case Commands.REMOVE_DIRECTORY -> {
                        String removeDirectory = tokens[1];
                        fileManager.removeDirectory(removeDirectory);
                    }
                    case Commands.LOCAL_DIRECTORY -> {
                        fileManager.showLocalDirectory();
                    }
                    default -> System.out.println("Некорректная команда. Попробуй help");
                }
            }catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Некорректный ввод программы.");
                System.out.println("Попробуй help <Команда>");
            }
            input = scanner.nextLine();
        }
    }
}
