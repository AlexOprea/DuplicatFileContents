package filefilter.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {

    public static List<File> getAllFilesFromFolder(String folderName, String... extensions) throws IOException {
        List<File> files = Files.walk(Paths.get(folderName),Integer.MAX_VALUE).filter(f->f.toString().toLowerCase().endsWith(extensions[0])||f.toString().toLowerCase().endsWith(extensions[1]))
                .map(Path::toFile)
                .collect(Collectors.toList());
        return files;
    }
}
