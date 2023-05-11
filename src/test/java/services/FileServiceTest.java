package services;

import com.pickle.parsers.FileParser;
import com.pickle.services.DirectoryService;
import com.pickle.services.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @see FileService
 * @author Illia Ponomarov
 * @version 1.0
 * @since 2023-05-10
 * @see FileParser
 * @see DirectoryService
 */

public class FileServiceTest {

    private FileParser fileParser;
    private DirectoryService directoryService;
    private FileService fileService;

    /**
     * Set up the FileParser and DirectoryService before each test
    */
    @BeforeEach
    public void setUp() {
        this.fileParser = new FileParser("inputPath", "outputPath");
        this.directoryService = new DirectoryService();
        this.fileService = new FileService(fileParser, directoryService);
    }

    /**
     * Test the createOutputFileStructure method in FileService ( Valid cases )
     */
    @Test
    @Disabled
    public void createOutputFileStructureValidTest() {
        String validInputPath = "src\\test\\java\\files\\yaml\\valid.yaml";
        String validOutputPath = "src\\test\\java\\testFolder\\yaml\\";

        // Test Case 1:
        FileService fileService = testFileService(validInputPath, validOutputPath);
        directoryService.deleteDirectory(new File(validOutputPath));

        fileService.deleteFile(validInputPath);
        fileService.createNewFile(validInputPath);



        boolean actual = fileService.createOutputFileStructure();
        assertTrue(actual);

        // Test Case 2:
        validInputPath = "src\\test\\java\\files\\json\\valid.json";
        validOutputPath = "src\\test\\java\\testFolder\\json\\";

        fileService = testFileService(validInputPath, validOutputPath);
        fileService.deleteFile(validInputPath);
        fileService.createNewFile(validInputPath);

        actual = fileService.createOutputFileStructure();
        assertTrue(actual);

        // Test Case 3:
        validInputPath = "src\\test\\java\\files\\xml\\valid.xml";
        validOutputPath = "src\\test\\java\\testFolder\\xml\\";

        fileService = testFileService(validInputPath, validOutputPath);
        fileService.deleteFile(validInputPath);
        fileService.createNewFile(validInputPath);

        actual = fileService.createOutputFileStructure();

        assertTrue(actual);
    }

    /**
     * Test the createOutputFileStructure method in FileService ( Invalid cases )
     */
    @Test
    @Disabled
    public void createOutputFileStructureInvalidTest() {
        String invalidInputPath = "src\\test\\java\\files\\yaml\\invalid.yaml";
        String invalidOutputPath = "src/test/java/testFolder/yaml/";

        // Test Case 1:
        FileService fileService = testFileService(invalidInputPath, invalidOutputPath);

        boolean actual = fileService.createOutputFileStructure();
        assertFalse(actual);

        // Test Case 2:
        invalidInputPath = "src\\test\\java\\files\\json\\invalid.json";
        invalidOutputPath = "src/test/java/testFolder/json/";

        fileService = testFileService(invalidInputPath, invalidOutputPath);
        actual = fileService.createOutputFileStructure();
        assertFalse(actual);

        // Test Case 3:
        invalidInputPath = "src\\test\\java\\files\\xml\\invalid.xml";
        invalidOutputPath = "src/test/java/testFolder/xml/";

        fileService = testFileService(invalidInputPath, invalidOutputPath);
        actual = fileService.createOutputFileStructure();

        assertFalse(actual);
    }

    /**
     * Generate a FileService object for testing
     */
    public FileService testFileService(String inputPath, String outputPath) {
        FileParser fileParser = new FileParser(inputPath, outputPath);
        return new FileService(fileParser, directoryService);
    }

    /**
     * Test the createNewFile method in FileService ( Valid cases )
     */
    @Test
    @Disabled
    public void createNewFileValidTest() {
        // Test Case 1:
        String validInputPath = "src\\test\\java\\files\\yaml\\valid.yaml";
        String validOutputPath = "src\\test\\java\\files\\yaml\\";

        FileService fileService = testFileService(validInputPath, validOutputPath);
        fileService.deleteFile(validInputPath);
        boolean actual = fileService.createNewFile(validInputPath);
        assertTrue(actual);


        // Test Case 2:
        validInputPath = "src\\test\\java\\files\\json\\valid.json";
        validOutputPath = "src/test/java/testFolder/json/";

        fileService = testFileService(validInputPath, validOutputPath);
        fileService.deleteFile(validInputPath);
        actual = fileService.createNewFile(validInputPath);
        assertTrue(actual);

        // Test Case 3:
        validInputPath = "src\\test\\java\\files\\xml\\valid.xml";
        validOutputPath = "src/test/java/testFolder/xml/";

        fileService = testFileService(validInputPath, validOutputPath);
        fileService.deleteFile(validInputPath);
        actual = fileService.createNewFile(validInputPath);

        assertTrue(actual);
    }

    /**
     * Test the createNewFile method in FileService ( Invalid cases )
    */

    @Test
    @Disabled
    public void createNewFileInvalidTest() {
        String invalidInputPath = "src\\test\\java\\files\\lololo\\invalid.yaml";
        String invalidOutputPath = "src/test/test/files/yaml/invalid.yaml";

        this.fileParser = new FileParser(invalidInputPath, invalidOutputPath);
        this.fileService = new FileService(fileParser, directoryService);

        boolean actual = fileService.createNewFile(invalidInputPath);

        assertFalse(actual);
    }

    /**
     * Test the deleteFile method in FileService ( Valid cases )
    */

    @Test
    @Disabled
    public void deleteFileValidTest() {

        // Test Case 1:
        String validInputPath = "src\\test\\java\\files\\yaml\\valid.yaml";
        String validOutputPath = "src\\test\\java\\files\\yaml\\";

        FileService fileService = testFileService(validInputPath, validOutputPath);
        boolean actual = fileService.deleteFile(validInputPath);

        assertTrue(actual);

        // Test Case 2:
        validInputPath = "src\\test\\java\\files\\json\\valid.json";
        validOutputPath = "src/test/java/testFolder/json/";

        fileService = testFileService(validInputPath, validOutputPath);
        actual = fileService.deleteFile(validInputPath);
        assertTrue(actual);

        // Test Case 3:
        validInputPath = "src\\test\\java\\files\\xml\\valid.xml";
        validOutputPath = "src/test/java/testFolder/xml/";

        fileService = testFileService(validInputPath, validOutputPath);
        actual = fileService.deleteFile(validInputPath);
        assertTrue(actual);
    }

    /**
     * Test the deleteFile method in FileService ( Invalid cases )
     */

    @Test
    @Disabled
    public void deleteFileInvalidTest() {
        String invalidInputPath = "src\\test\\java\\files\\lololo\\invalid.yaml";
        String invalidOutputPath = "src/test/test/files/yaml/invalid.yaml";

        this.fileParser = new FileParser(invalidInputPath, invalidOutputPath);
        this.fileService = new FileService(fileParser, directoryService);

        boolean actual = fileService.deleteFile(invalidInputPath);

        assertFalse(actual);
    }
}
