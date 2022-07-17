public class Help {

    public static void showTutorial(String command) {
        switch (command) {
            case Commands.HELP -> System.out.println("Просто введи help. Без аргументов.");
            case Commands.CHANGE_DIRECTORY -> System.out.println("cd <Имя директории>.");
            case Commands.LIST_OF_FILES -> System.out.println("Просто введи ls. Без аргументов.");
            case Commands.LIST_OF_FILE_WITH_SIZE -> System.out.println("Просто введи ll. Без аргументов.");
            case Commands.COPY_FILE -> System.out.println("cp <Копируемый файл> <Файл-копия>.");
            case Commands.CREATE_FILE -> System.out.println("touch <Имя файла>.");
            case Commands.FILE_CONTENT -> System.out.println("cat <Имя файла>.");
            case Commands.MAKE_DIRECTORY -> System.out.println("mkdir <Имя директории>.");
            case Commands.REMOVE_FILE -> System.out.println("rm <Имя файла>.");
            case Commands.REMOVE_DIRECTORY -> System.out.println("rmdir <Имя директории>.");
            case Commands.EXIT -> System.out.println("Просто введи exit. Без аргументов.");
            case Commands.LOCAL_DIRECTORY -> System.out.println("Просто введи pwd. Без аргументов.");
            case Commands.ECHO -> System.out.println("echo <Строка>");
            default -> System.out.println("Эта команда отсустсвует.");
        }

    }
}
