public enum CommandsEnum {
    DEFAULT(""),
    ECHO("echo"),
    EXIT("exit"),
    TYPE ("type"),
    PWD("pwd");

    public final String value;

    CommandsEnum(String value){
        this.value=value;
    }
    String getValue(){
        return this.value;
    }
    public static CommandsEnum fromString(String value) {
        for (CommandsEnum b : CommandsEnum.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        return CommandsEnum.DEFAULT;
    }
    public static boolean contains(String test) {

        for (CommandsEnum c : CommandsEnum.values()) {
            if (c.getValue().equals(test)) {
                return true;
            }
        }
    
        return false;
    }
}
