import java.io.*;

public class FileCommunicator  extends FileReader {
    private BufferedReader bufferedReader = new BufferedReader(this);
    private String line;
    private File file;

    public FileCommunicator(File file) throws FileNotFoundException {
        super(file);
        this.file = file;
    }

    public String readFile() {
        try {
            while((line = bufferedReader.readLine()) != null) {
              line += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public void exit() throws IOException {
        bufferedReader.close();
        this.close();
    }

    public boolean writeLine(String s) throws IOException {
        class MyFileWriter extends FileWriter {
            private BufferedWriter bufferedWriter = new BufferedWriter(this);

            private MyFileWriter(String fileName) throws IOException {
                super(fileName);
            }

            private MyFileWriter(String fileName, boolean append) throws IOException {
                super(fileName, append);
            }

            private MyFileWriter(File file) throws IOException {
                super(file);
            }

            private MyFileWriter(File file, boolean append) throws IOException {
                super(file, append);
            }

            private MyFileWriter(FileDescriptor fd) {
                super(fd);
            }

            private boolean writeLine(String s) {
                try {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                    return true;
                } catch (IOException e) {
                    System.out.println("Error while writing to File");
                    return false;
                }
            }
        }

        MyFileWriter tmp = new MyFileWriter(file);
        return tmp.writeLine(s);
    }
}
