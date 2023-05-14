package services;

import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ExtensionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileParserTest {

    private FileParser fileParser;
    private String pathToTestFiles = "src/test/java/files";
    private File testYamlDirectory;
    private File testJsonDirectory;
    private File testXmlDirectory;

    private File[] yamlFiles;
    private File[] jsonFiles;
    private File[] xmlFiles;

    @BeforeEach
    public void setUp() {
        // get all files in the test directory
        // for each file, create a new FileParser instance
        // and test the methods
        this.testYamlDirectory = new File(pathToTestFiles + "/yaml");
        this.testJsonDirectory = new File(pathToTestFiles + "/json");
        this.testXmlDirectory = new File(pathToTestFiles + "/xml");

        if (!testYamlDirectory.exists() || !testJsonDirectory.exists() || !testXmlDirectory.exists())
            throw new RuntimeException("Test directories do not exist");

        this.yamlFiles = testYamlDirectory.listFiles();
        this.jsonFiles = testJsonDirectory.listFiles();
        this.xmlFiles = testXmlDirectory.listFiles();
    }

    @Test
    public void testYamlFiles() {

        Arrays.stream(yamlFiles).forEach(file -> {
            this.fileParser = new FileParser(file.getAbsolutePath(), "outputPath");
            assertEquals(ExtensionType.YAML, fileParser.getInputExtensionType());
        });
    }

    @Test
    public void testJsonFiles() {
        Arrays.stream(jsonFiles).forEach(file -> {
            this.fileParser = new FileParser(file.getAbsolutePath(), "outputPath");
            assertEquals(ExtensionType.JSON, fileParser.getInputExtensionType());
        });
    }

    @Test
    public void testXmlFiles() {
        Arrays.stream(xmlFiles).forEach(file -> {
            this.fileParser = new FileParser(file.getAbsolutePath(), "outputPath");
            assertEquals(ExtensionType.XML, fileParser.getInputExtensionType());
        });
    }

}
