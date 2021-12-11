package filefilter.duplicatefindingservice;

import filefilter.utils.FileHelper;
import filefilter.utils.FileReaderHelper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicateContentFinder {
    private static DuplicateContentFinder thisInstance;

    private DuplicateContentFinder() {

    }

    public static DuplicateContentFinder getInstance() {
        if (thisInstance == null)
            thisInstance = new DuplicateContentFinder();
        return thisInstance;
    }

    /**
     * Returns a map of duplicated files content grouped by their content (using index, not the content itself). Do not use relative path, absolute path is required.
     * @param path folder path
     * @return map of duplicated files
     * @throws IOException
     */
    public Map<Integer, String> getDuplicatedFilePaths(String path) throws IOException {
        Map<String, String> fileContents = new HashMap<>();
        Map<Integer, String> duplicateFiles = new HashMap<>();
        List<File> files;
        StringBuffer buffer = new StringBuffer();
        int ind = 1;

        files = FileHelper.getAllFilesFromFolder(path, ".txt",".bin");
        if (files != null) {
            for (File file : files) {
                fileContents.put(file.toString(), FileReaderHelper.readFile(file));
            }
        }
        Set<String> uniqueContent = new HashSet<>();
        Set<String> notUniqueContent = new HashSet<>();
        for (String key : fileContents.keySet()) {
            boolean isUnique = uniqueContent.add(fileContents.get(key));
            if (!isUnique) {
                notUniqueContent.add(fileContents.get(key));
            }
        }
        for (String content : notUniqueContent) {
            buffer.setLength(0);
            Set<String> identicContentFiles = fileContents.entrySet().stream().filter(entry -> entry.getValue().equals(content)).map(Map.Entry::getKey).collect(Collectors.toSet());
            identicContentFiles.forEach(val -> buffer.append(val).append(" "));
            duplicateFiles.put(ind, buffer.toString());
            ind++;
        }
        return duplicateFiles;
    }
}
