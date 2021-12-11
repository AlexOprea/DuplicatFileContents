package filefilter.utils;

import java.io.*;

public class FileReaderHelper {

    public static String readFile(File file) throws IOException {
        StringBuffer stringBuilder = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine())!= null)
        {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

}
