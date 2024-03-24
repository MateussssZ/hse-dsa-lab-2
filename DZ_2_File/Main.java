import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CLI CLInterface = new CLI();

        Counter cnter = new Counter(CLInterface.getInFile(), CLInterface.getOutFile());
        cnter.countSymbols();
    }
}
