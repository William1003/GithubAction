import org.junit.*;
import org.junit.Assert.*;

/**
 * Term Tester.
 *
 * @author <William>
 * @version 1.0
 * @since <pre>Mar 16, 2021</pre>
 */
public class TermTest {

    Term term1 = new Term("2", "3");
    Term term2 = new Term("5", "4");

    /**
     * Method: derive()
     */
    @Test
    public void testDerive() throws Exception {
        //TODO: Test goes here...
        Assert.assertEquals("+6*x**2", term1.derive().toString());
    }

    /**
     * Method: hasSameExp(Term t)
     */
    @Test
    public void testHasSameExp() throws Exception {
        //TODO: Test goes here...
        Assert.assertFalse(term1.hasSameExp(term2));
    }


    /**
     * Method: add(Term t)
     */
    @Test
    public void testAdd() throws Exception {
        //TODO: Test goes here...
        Term term3 = new Term("3", "4");
        term3.add(term2);
        Assert.assertEquals("+8*x**4", term3.toString());
    }
} 
