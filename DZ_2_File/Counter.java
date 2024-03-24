import java.io.*;

public class Counter {
    private File inFile;
    private File outFile;
    private int[] cnt;

    public Counter(File inFile,File outFile){
        this.inFile = inFile;
        this.outFile = outFile;
        this.cnt = new int[58];
    }

    public void countSymbols() throws IOException {
        try(FileReader reader = new FileReader(this.inFile)) {
            int symbol;
            while((symbol = reader.read())!=-1){
                if (symbol>=65 && symbol <=122){
                    this.cnt[symbol - 65]++;
                }
            }
        }
        catch (IOException err){
            System.out.println(err.getMessage());
            throw new IOException();
        }
        try(FileWriter writer = new FileWriter(this.outFile)){
            for (int i =65;i<91;i++){
                writer.write((char)i + ": "+this.cnt[i-65]+"\n");
            }
            for (int i =97;i<123;i++) {
                writer.write((char)i + ": "+this.cnt[i-65]+"\n");
            }
        }
        catch (IOException err){
            System.out.println(err.getMessage());
            throw new IOException();
        }
    }
}
