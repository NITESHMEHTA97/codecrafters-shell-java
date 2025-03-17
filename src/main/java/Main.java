import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        String input,typeSubstring;
        String cwd = System.getProperty("user.dir");
        while(true){
            System.out.print("$ ");
            input = scanner.nextLine();
            String[] inputParameters = input.split(" ");
            String command = inputParameters[0];
            CommandsEnum enumm = CommandsEnum.fromString(command);
            switch (enumm) {
                case CommandsEnum.ECHO:
                    System.out.println(input.substring(5));
                    break;
                case CommandsEnum.TYPE:
                    typeSubstring = input.substring(5);
                    if(CommandsEnum.contains(typeSubstring)) {
                        System.out.printf("%s is a shell builtin%n", typeSubstring);
                    }else {
                        String path = getPath(typeSubstring);
                        if (path != null) {
                        System.out.println(typeSubstring + " is " + path);
                        } else {
                        System.out.println(typeSubstring + ": not found");
                        }
                    }
                    break;
                case CommandsEnum.PWD:
                    System.out.println(cwd);
                    break;
                case CommandsEnum.EXIT:
                    System.exit(0);
                    break;
                case CommandsEnum.CD:
                    String dir = inputParameters[1];
                    if(dir.equals("~")){
                        dir=System.getenv("HOME");
                    } else if (!dir.startsWith("/")) {
                        dir = cwd + "/" + dir;
                    } 
                    if (Files.isDirectory(Path.of(dir))) {
                        cwd = Path.of(dir).normalize().toString();
                    } else {
                        System.out.printf("cd: %s: No such file or directory%n", dir);
                    }
                    break;
                default:
                    String path = getPath(command);
                    if(path!=null){
                        Process process = Runtime.getRuntime().exec(inputParameters);
                        process.getInputStream().transferTo(System.out);
                    }else{
                        System.out.println(input + ": command not found");
                    }
            }
        }
    }

    private static String getPath(String parameter) {
        String[] pathList = System.getenv("PATH").split(File.pathSeparator);
        for (String path : pathList) {
            Path fullPath = Path.of(path, parameter);
            if (Files.isRegularFile(fullPath)) {
                return fullPath.toString();
            }
        }
        return null;
    }
}
