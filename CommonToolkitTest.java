import org.junit.Test;

import static org.junit.Assert.*;

public class CommonToolkitTest {
    private CommonToolkit commonToolkit= new CommonToolkit();

    //Test to convert number formatting
    @Test
    public void convertToEnglishFormatTest_WithNoDesimalPoint() {
        String result= commonToolkit.convertToEnglishFormat("2 ml");
        assertEquals("2 ml",result);
    }
    @Test
    public void convertToEnglishFormatTest_WithDesimalPoint() {
        String result= commonToolkit.convertToEnglishFormat("2,5 ml");
        assertEquals("2.5 ml",result);
    }

    //Test Find next time to give medicine
    @Test
    //testing with given input
    public void getNextDoesTimeTest1() throws Exception {
        String previousDoesTime="7:22AM";
        String duration="6:00";
        String output=commonToolkit.getNextDoesTime(previousDoesTime,duration);
        String expected="1:22PM";
        assertEquals(expected,output);
    }
    @Test
    //When PM time value change has given
    public void getNextDoesTimeTest2() throws Exception {
        String previousDoesTime="1:22PM";
        String duration="6:00";
        String output=commonToolkit.getNextDoesTime(previousDoesTime,duration);
        String expected="7:22PM";
        assertEquals(expected,output);
    }

    @Test
    //When PM time value that can occur AM to PM change has given
    public void getNextDoesTimeTest3() throws Exception {
        String previousDoesTime="11:22PM";
        String duration="6:00";
        String output=commonToolkit.getNextDoesTime(previousDoesTime,duration);
        String expected="5:22AM";
        assertEquals(expected,output);
    }

    //Test  Swedish words contain Unicode

    @Test
    public void getSwedishTextTest_WithUnicodes()
    {
        assertEquals(commonToolkit.getSwedishText("\\u228sk\\u228da"), "äskäda");
    }

    @Test
    public void getSwedishTextTest_WithoutUnicodes()
    {
        assertEquals(commonToolkit.getSwedishText("228sk228da"), "228sk228da");
    }

    @Test
    public void main() {
    }
}