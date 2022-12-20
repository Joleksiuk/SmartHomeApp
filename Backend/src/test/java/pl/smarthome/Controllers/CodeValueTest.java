package pl.smarthome.Controllers;

import org.junit.jupiter.api.Test;
import pl.smarthome.Controllers.tuya.CodeValue;

import static org.junit.jupiter.api.Assertions.*;

class CodeValueTest {

    CodeValue codeValue=new CodeValue("Switch_led",true);
    @Test
    void ToJson() {
        System.out.println(codeValue.toJson());
        assertTrue(true);
    }

    @Test
    void getCode() {
        assertEquals("Switch_led", codeValue.getCode());
    }

    @Test
    void getValue() {
        assertEquals(true, codeValue.getValue());
    }

    @Test
    void setCode() {
        codeValue.setCode("a");
        assertEquals("a", codeValue.getCode());
    }

    @Test
    void setValue() {
        codeValue.setValue(false);
        assertSame(false, codeValue.getValue());
    }

}