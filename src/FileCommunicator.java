import java.io.*;

public class FileCommunicator  extends FileReader {
    private BufferedReader bufferedReader;
    private String line = "", tmpLine = "";
    private File file;

    public FileCommunicator(File file) throws FileNotFoundException {
        super(file);
        this.file = file;
    }

    public String readFile() {
        bufferedReader = new BufferedReader(this);
        try {
            while ((tmpLine = bufferedReader.readLine()) != null) line = String.format("%s%s", line, String.format("%s\n", tmpLine));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public void exit() {
        try {
            bufferedReader.close();
            this.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean writeLine(String s) throws IOException {
        class MyFileWriter extends FileWriter {
            private BufferedWriter bufferedWriter;

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
                bufferedWriter = new BufferedWriter(this);

                try {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    this.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        MyFileWriter tmp = new MyFileWriter(this.file, true);
        return tmp.writeLine(s);
    }
}
