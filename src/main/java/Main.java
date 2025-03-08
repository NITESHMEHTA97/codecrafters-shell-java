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
                if (commands.contains(typeSubstring)) {
                    System.out.printf("%s is a shell builtin%n", typeSubstring);
                  } else {
                    System.out.printf("%s: not found%n", typeSubstring);
                  }
            }else if (input.equals("exit 0")){
                break;            
            }else{
                System.out.println(input + ": command not found");
            }
        }        
    }
}
