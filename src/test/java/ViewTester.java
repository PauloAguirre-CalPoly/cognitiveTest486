import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ViewTester {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintToConsole() {
        DataGraph data = new DataGraph();
        View view = new View();
        view.drawBoard(data);
        System.out.println("Hello");
        Assert.assertEquals("  |   |   |  \n  |   |   |  \n  |   |   |  \n  |   |   |  \n ___ ___ ___", outContent.toString());
    }
}
