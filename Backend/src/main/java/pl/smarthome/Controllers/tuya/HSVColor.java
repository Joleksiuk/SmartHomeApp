package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;

@ToString
@Setter
@Getter
public class HSVColor {
    double h;
    double s;
    double v;

    public static HSVColor fromHex(String hex) {
        Color color = Color.decode(hex);
        float[] hsv = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        HSVColor hsvColor = new HSVColor();
        hsvColor.h = (int)(hsv[0]*360);
        hsvColor.s = (int)(hsv[1]*1000);
        hsvColor.v = (int)(hsv[2]*1000);
        return hsvColor;
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
}