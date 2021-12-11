import filefilter.duplicatefindingservice.DuplicateContentFinder;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String... args)
    {
        DuplicateContentFinder duplicateContentFinder = DuplicateContentFinder.getInstance();
        Map<Integer,String> duplicatedFiles;
        if (args.length <1)
            System.out.println("No folder given. Try with parameter!");
        else {
            try {
                duplicatedFiles = duplicateContentFinder.getDuplicatedFilePaths(args[0]);
                for (int key : duplicatedFiles.keySet())
                {
                    System.out.println(duplicatedFiles.get(key));
                }
            } catch (IOException e) {
                System.out.println(String.format("Could not parse given folder. Reason %s",e.getMessage()));
            }
        }

    }
}
