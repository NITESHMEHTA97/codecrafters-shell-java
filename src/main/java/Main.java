import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Set<String> commands = Set.of("echo", "exit", "type");
        String input,typeSubstring;
        while(true){
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if(input.startsWith("echo")){
                 System.out.println(input.substring(5));
            }else if(input.startsWith("type")){
                typeSubstring = input.substring(5);
                if(commands.contains(typeSubstring)) {
                    System.out.printf("%s is a shell builtin%n", typeSubstring);
                  }else {
                    String path = getPath(typeSubstring);
                    if (path != null) {
                    System.out.println(typeSubstring + " is " + path);
                    } else {
                    System.out.println(typeSubstring + ": not found");
                    }
                  }
            }else if((getPath(input.split(" ")[0])!=null)){
                Process process = Runtime.getRuntime().exec(input.split(" "));
                process.getInputStream().transferTo(System.out);
            }else if (input.equals("exit 0")){
                break;            
            }else{
                System.out.println(input + ": command not found");
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
