import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class FileManager {

    private String currentFolder;
    private String root;

    public FileManager(String currentFolder) {
        this.currentFolder = currentFolder;
        this.root = currentFolder;
    }

    public void help() {
        String help = """
                ls - список файлов в директории
                ll - список файлов в директории с отображением занимаемой памяти
                pwd - просмотреть текущую директорию
                echo - вернуть значение в консоль
                cp - копировать файл
                touch - создать файл
                cat - просмотреть содежимое файла
                cd - сменить директорию
                mkdir - создать директорию
                help - показывает это сообщение
                rm - удалить файл
                exit - выйти из программы""";
        System.out.println(help);
    }

    public void listOfFiles(boolean withSize) {
        File currentFolderAsFile = new File(currentFolder);
        File[] files = currentFolderAsFile.listFiles();
        System.out.println(currentFolder);
        for (File file : files) {
            if (file.isDirectory()) {
                if (withSize) {
                    System.out.println(file.getName() + " -D  " + FileUtils.sizeOf(file));
                } else {
                    System.out.println(file.getName() + " -D");
                }
            } else {
                if (withSize) {
                    System.out.println(file.getName() + "  " + file.length());
                } else {
                    System.out.println(file.getName());
                }
            }
        }
    }


    public void copyFile(String sourceFileName, String destinationFileName) {
        File source = new File(currentFolder + File.separator + sourceFileName);
        File destination = new File(currentFolder + File.separator + destinationFileName);
        try {
            if (!source.exists()) throw new IOException();
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
            String[] endFolderPosition = this.currentFolder.split(directoryName);
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
            if (!file.exists()) throw new IOException();
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

    public void makeDirectory(String directoryName) {
        File file = new File(currentFolder + File.separator + directoryName);
        file.mkdir();
    }

    public void removeFile(String removeFile) {
        File file = new File(currentFolder + File.separator + removeFile);
        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("Нет такого файла");
        }
    }

    public void removeDirectory(String removeDirectory) {
        File file = new File(currentFolder + File.separator + removeDirectory);
        if (file.exists() && file.isDirectory()) {
            recursiveDelete(file);
        } else {
            removeFile(removeDirectory);
        }
    }


    public static void recursiveDelete(File file) {
        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                recursiveDelete(f);
            }
            file.delete();
        } else {
            file.delete();
        }
    }

    public void showLocalDirectory() {
        System.out.println("Вы сейчас находитесь в " + currentFolder);
    }

    public void echo(String[] tokens) {
        for (int i = 1; i < tokens.length; i++) {
            System.out.print(tokens[i] + " ");
        }
        System.out.println();
    }


    public void showHistory(int number) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/history.txt"))) {
            if(number == 0) {
                reader.lines().forEach(System.out::println);
            } else if (number > 0){
                List<String> history = reader.lines().toList();
                if (number >= history.size()) {
                    showHistory(0);
                }
                for (int i = history.size() - 1; i > history.size() - number - 1; i--) {
                    System.out.println(history.get(i));
                }
            }
        } catch (IOException exception) {
            System.out.println("Не удалось прочитать историю");
        }
    }

    public void addHistory(String input) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/history.txt", true))) {
            writer.append(input).append("\n").flush();
        } catch (IOException exception) {
            System.out.println("Не удалось сохранить ввод в историю");
        }
    }
}
