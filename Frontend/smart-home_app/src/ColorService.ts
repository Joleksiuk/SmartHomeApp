
class ColorService {
  
    hsvToRgb(h:number, s:number, v:number) {
        var r:number=0, g:number=0, b:number=0;
      
        var i = Math.floor(h * 6);
        var f = h * 6 - i;
        var p = v * (1 - s);
        var q = v * (1 - f * s);
        var t = v * (1 - (1 - f) * s);
      
        switch (i % 6) {
          case 0: r = v; g = t; b = p; break;
          case 1: r = q; g = v; b = p; break;
          case 2: r = p; g = v; b = t; break;
          case 3: r = p; g = q; b = v; break;
          case 4: r = t; g = p; b = v; break;
          case 5: r = v; g = p; b = q; break;
        }
      
        return [ r * 255, g * 255, b * 255 ];
      }
    
      rgbToHex(rgb:number[]){
        return "#" + (1 << 24 | rgb[0] << 16 | rgb[1] << 8 | rgb[2]).toString(16).slice(1);
      }
}
export default new ColorService();

