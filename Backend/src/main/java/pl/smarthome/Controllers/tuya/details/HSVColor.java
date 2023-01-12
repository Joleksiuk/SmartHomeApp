package pl.smarthome.Controllers.tuya.details;

import com.google.gson.Gson;
import lombok.*;

import java.awt.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HSVColor {
    private double h;
    private double s;
    private double v;

    public static HSVColor fromHex(String hex) {
        Color color = Color.decode(hex);
        float[] hsv = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        HSVColor hsvColor = new HSVColor();
        hsvColor.h = (int)(hsv[0]*360);
        hsvColor.s = (int)(hsv[1]*1000);
        hsvColor.v = (int)(hsv[2]*1000);
        return hsvColor;
    }

    public static String toHex(HSVColor hsv) {
        Color color = Color.getHSBColor((float)(hsv.h/360.0),(float) (hsv.s/1000.0),(float) (hsv.v/1000.0)) ;
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static String hsvToJson( HSVColor hsv){
        Gson gson = new Gson();
        String json = gson.toJson(hsv);
        return json;
    }

    public static HSVColor JsonToHSV(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,HSVColor.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}