package pl.smarthome.Controllers.tuya.details;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HSVColorTest {

    @Test
    void fromHex() {
        HSVColor hsv = new HSVColor(312,954,941);
        Color color = Color.getHSBColor((float)(hsv.getH()/360.0),(float) (hsv.getS()/1000.0),(float) (hsv.getV()/1000.0)) ;
        String result= String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        System.out.println(result);
    }
    @Test
    void toHex(){
        String hex="f5a623";
        if(hex.charAt(0)!='#'){
            hex="#"+hex;
        }
        CodeValue cv=new CodeValue("colour_data",hex);
        HSVColor color = HSVColor.fromHex(cv.getValue().toString());
        System.out.println(color);
    }
}