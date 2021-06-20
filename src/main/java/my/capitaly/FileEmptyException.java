package my.capitaly;

public class FileEmptyException extends Exception {
    public FileEmptyException() {
        super("File is Empty!");
    }
}
