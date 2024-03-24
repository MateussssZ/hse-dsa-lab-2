import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CLI {
    private File inFile;
    private File outFile;

    public CLI() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название входного файла:");
        String inName = in.next();

        System.out.println("Введите название выходного файла:");
        String outName = in.next();

        this.inFile = new File("./"+inName);
        this.outFile = new File("./"+outName);

        if (!this.inFile.exists()){
            throw new FileNotFoundException();
        }
        if (!this.outFile.exists()){
            throw new FileNotFoundException();
        }
    }
    public File getInFile() {
        return inFile;
    }

    public File getOutFile() {
        return outFile;
    }
}
