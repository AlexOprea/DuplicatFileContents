import filefilter.duplicatefindingservice.DuplicateContentFinder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateContentFinderTest {

    private DuplicateContentFinder duplicateContentFinder = DuplicateContentFinder.getInstance();

    @Test
    public void wrongPath()
    {
        Exception e = assertThrows(IOException.class, () -> {duplicateContentFinder.getDuplicatedFilePaths("C:/some_wrong_path");});
        String actualMessage = e.getMessage();
        assertNotNull(actualMessage);
        assertEquals("C:\\some_wrong_path",actualMessage);
    }

    @Test
    public void testDuplicateFinders() throws IOException {
        File directory = new File("./src/test/resources");
        Map<Integer,String> duplicatedValues =  duplicateContentFinder.getDuplicatedFilePaths(directory.getAbsolutePath());
        assertNotNull(duplicatedValues);
        assertEquals(2,duplicatedValues.keySet().size());
        assertTrue(duplicatedValues.get(1).contains("A.txt"));
        assertTrue(duplicatedValues.get(1).contains("B.txt"));
        assertTrue(duplicatedValues.get(1).contains("C.txt"));
        assertTrue(duplicatedValues.get(2).contains("D.txt"));
        assertTrue(duplicatedValues.get(2).contains("E.txt"));
    }
}
