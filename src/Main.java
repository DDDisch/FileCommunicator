import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FileCommunicator file = null;
        String filePath = "src/test.txt";

        try {
            file = new FileCommunicator(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Read Content from File
        System.out.println(file != null ? file.readFile() : null);

        //Write to File
        try {
            System.out.println(file.writeLine("Append on Bottom"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read File with Changed content
        System.out.println(file.readFile());

        file.exit();
    }
}