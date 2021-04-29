import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
public class DemoCase {

    public static void create(File srcImgFile, File destAsciiImgFile) {
        final  String base ="@#&$%*o!;.";
        try {
            BufferedImage bufferedImage = ImageIO.read(srcImgFile);
            BufferedImage image = new BufferedImage(bufferedImage.getWidth()*4, bufferedImage.getHeight()*4,
                    BufferedImage.TYPE_INT_BGR);
            Graphics g = image.getGraphics();
            g.setClip(0, 0, bufferedImage.getWidth()*4, bufferedImage.getHeight()*4);
            g.setColor(Color.white);
            Font fnt =new Font("Arial", Font.PLAIN, 10);
            g.setFont(fnt);// 设置画笔字体
            g.fillRect(0, 0, bufferedImage.getWidth()*4, bufferedImage.getHeight()*4);// 先用黑色填充整张图片,也就是背景
            for (int i = 0; i < bufferedImage.getHeight(); i +=2) {
                for (int j = 0; j < bufferedImage.getWidth(); j +=2) {
                    int pixel = bufferedImage.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字
                    int red = (pixel & 0xff0000) >> 16;
                    int green = (pixel & 0xff00) >> 8;
                    int blue = (pixel & 0xff);
                    float gray = 0.299f * red + 0.578f * green + 0.114f * blue;
                    int index = Math.round(gray * (base.length() + 1) / 255);
                    Color a = new Color(bufferedImage.getRGB(j, i));
                    g.setColor(a);
                    g.drawString(index >= base.length() ? " " : String.valueOf(base.charAt(index)), j*4, i*4);// 画出字符串
                }
            }
            g.dispose();
            ImageIO.write(image, "png", destAsciiImgFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) throws IOException {

        create(new File("C:\\Users\\caizheng\\Desktop\\g.jpg"),new File("C:\\Users\\caizheng\\Desktop\\dd.png"));
    }
}
