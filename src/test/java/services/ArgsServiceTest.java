package services;

import com.pickle.services.ArgsService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsServiceTest {

    private ArgsService argparseService;
    private String[] inputPaths;
    private String[] outputPaths;
    private CommandLine myArgumentParser;

    @BeforeEach
    public void setUp() {
        Option input = new Option("i", "input", true, "input path");
        Option output = new Option("o", "output", true, "output path");

        argparseService = new ArgsService();
        this.inputPaths = new String[]{"inputPaths", "", null};
        this.outputPaths = new String[]{"outputPaths", "", null};
    }

    @Test
    public void testHasInputAndOutputPath() {

        // Right side of the && operator is not evaluated if the left side is false
        assertTrue(argparseService.hasInputAndOutputPath(Optional.ofNullable(this.inputPaths[0]), Optional.ofNullable(this.outputPaths[0])));

        // Otherwise, both sides are evaluated
        assertFalse(argparseService.hasInputAndOutputPath(Optional.ofNullable(this.inputPaths[1]), Optional.ofNullable(this.outputPaths[1])));
        assertFalse(argparseService.hasInputAndOutputPath(Optional.ofNullable(this.inputPaths[2]), Optional.ofNullable(this.outputPaths[2])));
        assertFalse(argparseService.hasInputAndOutputPath(Optional.ofNullable(this.inputPaths[1]), Optional.ofNullable(this.outputPaths[0])));
        assertFalse(argparseService.hasInputAndOutputPath(Optional.ofNullable(this.inputPaths[2]), Optional.ofNullable(this.outputPaths[0])));
        assertFalse(argparseService.hasInputAndOutputPath(Optional.ofNullable(this.inputPaths[0]), Optional.ofNullable(this.outputPaths[1])));
        assertFalse(argparseService.hasInputAndOutputPath(Optional.ofNullable(this.inputPaths[0]), Optional.ofNullable(this.outputPaths[2])));

    }

    @Test
    public void testHasInputPath() {
        try {
            Method hasInputPathTest1 = ArgsService.class.getDeclaredMethod("hasInputPath", Optional.class);
            Method hasInputPathTest2 = ArgsService.class.getDeclaredMethod("hasInputPath", Optional.class);
            Method hasInputPathTest3 = ArgsService.class.getDeclaredMethod("hasInputPath", Optional.class);

            hasInputPathTest1.setAccessible(true);
            hasInputPathTest2.setAccessible(true);
            hasInputPathTest3.setAccessible(true);

            boolean hasInputPath1 = (boolean) hasInputPathTest1.invoke(argparseService, Optional.ofNullable(this.inputPaths[0]));
            boolean hasInputPath2 = (boolean) hasInputPathTest2.invoke(argparseService, Optional.ofNullable(this.inputPaths[1]));
            boolean hasInputPath3 = (boolean) hasInputPathTest3.invoke(argparseService, Optional.ofNullable(this.inputPaths[2]));

            assertTrue(hasInputPath1);
            assertFalse(hasInputPath2);
            assertFalse(hasInputPath3);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testHasOutputPath() {
        try {
            Method hasOutputPathTest1 = ArgsService.class.getDeclaredMethod("hasOutputPath", Optional.class);
            Method hasOutputPathTest3 = ArgsService.class.getDeclaredMethod("hasOutputPath", Optional.class);
            Method hasOutputPathTest2 = ArgsService.class.getDeclaredMethod("hasOutputPath", Optional.class);
            Method hasOutputPathTest4 = ArgsService.class.getDeclaredMethod("hasOutputPath", Optional.class);


            hasOutputPathTest1.setAccessible(true);
            hasOutputPathTest2.setAccessible(true);
            hasOutputPathTest3.setAccessible(true);
            hasOutputPathTest4.setAccessible(true);


            boolean hasOutputPath1 = (boolean) hasOutputPathTest1.invoke(argparseService, Optional.ofNullable(this.outputPaths[0]));
            boolean hasOutputPath2 = (boolean) hasOutputPathTest2.invoke(argparseService, Optional.ofNullable(this.outputPaths[1]));
            boolean hasOutputPath3 = (boolean) hasOutputPathTest3.invoke(argparseService, Optional.ofNullable(this.outputPaths[2]));

            assertTrue(hasOutputPath1);
            assertFalse(hasOutputPath2);
            assertFalse(hasOutputPath3);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
