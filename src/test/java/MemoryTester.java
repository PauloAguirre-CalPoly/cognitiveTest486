import org.junit.Assert;
import org.junit.Test;

public class MemoryTester {
    @Test
    public void checkMem(){
        DataGraph data = new DataGraph();
        int[] test = {0,0,0};
        Assert.assertArrayEquals(test, data.a);

    }
    @Test
    public void checkSetFun(){
        DataGraph data = new DataGraph();
        int[] test = {0,1,2};
        data.setA(test);
        Assert.assertArrayEquals(test, data.getA());
    }
}
