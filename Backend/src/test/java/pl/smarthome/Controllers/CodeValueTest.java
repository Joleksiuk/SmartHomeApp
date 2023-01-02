package pl.smarthome.Controllers;

import org.junit.jupiter.api.Test;
import pl.smarthome.Controllers.tuya.CodeValueBoolean;

import static org.junit.jupiter.api.Assertions.*;

class CodeValueTest {

    CodeValueBoolean codeValueBoolean =new CodeValueBoolean("Switch_led",true);
    @Test
    void ToJson() {
        System.out.println(codeValueBoolean.toJson());
        assertTrue(true);
    }

    @Test
    void getCode() {
        assertEquals("Switch_led", codeValueBoolean.getCode());
    }

    @Test
    void getValue() {
        assertEquals(true, codeValueBoolean.getValue());
    }

    @Test
    void setCode() {
        codeValueBoolean.setCode("a");
        assertEquals("a", codeValueBoolean.getCode());
    }

    @Test
    void setValue() {
        codeValueBoolean.setValue(false);
        assertSame(false, codeValueBoolean.getValue());
    }

    @Test
    void testParamsToList(){
      String params="a,b,c,d";
      String[] paramList=params.split(",");
      for(String s : paramList){
          System.out.println(s);
      }
    }

}