import org.junit.Assert;
import org.junit.Test;

public class BrainTester {
    @Test
    public void brainTest(){
        DataGraph data = new DataGraph();
        int[] test = {0,1,2};
        data.setA(test);
        Brain brain = new Brain(data);
        Assert.assertArrayEquals(test, brain.getMatchList("a"));
    }
    @Test
    public void checkB(){
        DataGraph data = new DataGraph();
        int[] test = {3,2,0};
        data.setA(test);
        Brain brain = new Brain(data);
        brain.isValid("2","a","b");
        int[] test2 = {2,0,0};
        int[] test3 = {3,0,0};
        Assert.assertArrayEquals(test2, data.getB());
        Assert.assertArrayEquals(test3, data.getA());
    }
    @Test
    public void checkIsValid(){
        DataGraph data = new DataGraph();
        View view = new View();
        view.drawBoard(data);
        int[] test = {3,2,0};
        data.setA(test);
        Brain brain = new Brain(data);
        Assert.assertTrue(brain.isValid("2","a","b"));
        view.drawBoard(data);
    }
    @Test
    public void checkIsValidTrue(){
        DataGraph data = new DataGraph();
        int[] listA = {1,0,0};
        int[] listB = {2,0,0};
        data.setA(listA);
        data.setB(listB);
        Brain brain = new Brain(data);
        Assert.assertTrue(brain.isValid("1","a","b"));
        int[] testA = {0,0,0};
        int[] testB = {2,1,0};
        Assert.assertArrayEquals(testA, data.getA());
        Assert.assertArrayEquals(testB, data.getB());
        View view = new View();
        view.drawBoard(data);
    }
    @Test
    public void checkIsValidFalse(){
        DataGraph data = new DataGraph();
        int[] listA = {1,0,0};
        int[] listB = {2,0,0};
        data.setA(listA);
        data.setB(listB);
        Brain brain = new Brain(data);
        Assert.assertFalse(brain.isValid("2","b","a"));
//        int[] testA = {0,0,0};
//        int[] testB = {2,1,0};
//        Assert.assertArray(testA, data.getA());
//        Assert.assertArrayEquals(testB, data.getB());
    }
}
