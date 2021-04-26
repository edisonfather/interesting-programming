package tostr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ToStr {
    public static void main(final String[] args) throws IOException {
        final String base = "@#&$%*o!;.";// 字符串由复杂到简单
        Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:\\text\\text.txt"))));
        final BufferedImage image= ImageIO.read(new File("C:\\123.jpg"));
        float[][] bimage =new float[image.getWidth()][(image.getHeight()+1)/2];
        float[][] err =new float[image.getWidth()][(image.getHeight()+1)/2];
        int x=0,y=0;
        while (y < image.getHeight()){
            x=0;
            while (x< image.getWidth()){
                bimage[x][y/2]= 0.299f * ((image.getRGB(x, y) & 0xff0000) >> 16) + 0.578f * ((image.getRGB(x, y) & 0xff00) >> 8) + 0.114f * (image.getRGB(x, y) & 0xff);
                err[x][y/2] = bimage[x][y/2]-(Math.round(bimage[x][y/2] * (base.length() + 1) / 255)*255/ (base.length() + 1));
                x++;
            }
            y+=2;
        }
        for (y = 0; y < bimage[0].length; y ++) {
            for ( x = 0; x < bimage.length; x++) {//Floyd-Steinberg error diffusion
                if(y<(bimage[0].length-1)&&x>0){bimage[x-1][y+1] = (bimage[x-1][y+1]+err[x][y]*3/16)>255?255:((bimage[x-1][y+1]+err[x][y]*3/16)<0?0:(bimage[x-1][y+1]+err[x][y]*3/16));}
                if(y<(bimage[0].length-1)){bimage[x][y+1] = (bimage[x][y+1]+err[x][y]*5/16)>255?255:((bimage[x][y+1]+err[x][y]*5/16)<0?0:(bimage[x][y+1]+err[x][y]*5/16));}
                if(y<(bimage[0].length-1)&&x<(bimage.length-1)) {bimage[x+1][y+1] = (bimage[x+1][y+1]+err[x][y]*1/16)>255?255:((bimage[x+1][y+1]+err[x][y]*1/16)<0?0:(bimage[x+1][y+1]+err[x][y]*1/16));}
                if(x<(bimage.length-1)){bimage[x+1][y] = (bimage[x+1][y]+err[x][y]*7/16)>255?255:((bimage[x+1][y]+err[x][y]*7/16)<0?0:(bimage[x+1][y]+err[x][y]*7/16));}
            }
        }
        for (y = 0; y < bimage[0].length; y ++) {
            for (x = 0; x < bimage.length; x++) {
                w.write(Math.round(bimage[x][y] * (base.length() + 1) / 255) >= base.length() ? " " : String.valueOf(base.charAt(Math.round(bimage[x][y] * (base.length() + 1) / 255))));
            }
            w.write("\n");
        }
        w.close();
    }
}
