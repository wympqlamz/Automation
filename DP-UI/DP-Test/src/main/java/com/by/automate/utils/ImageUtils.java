package com.by.automate.utils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片工具类， 图片水印，文字水印，缩放，补白等
 *
 * @author Carl He
 */
public  class ImageUtils {
    /**
     * 图片格式：JPG
     */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    private ImageUtils() {
    }

    public static void tagImages() {

    }

    public static void ImageTset(String... strings) throws Exception {
        //创建四个文件对象（注：这里四张图片的宽度都是相同的，因此下文定义BufferedImage宽度就取第一只的宽度就行了）
        int paths = strings.length;
        //构造一个类型为预定义图像类型之一的 BufferedImage。 宽度为第一只的宽度，高度为各个图片高度之和
        Image src = ImageIO.read(new File(strings[0]));
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        BufferedImage tag = new BufferedImage(w, ((h+10)*paths)/2, BufferedImage.TYPE_INT_RGB);
        //创建输出流
        FileOutputStream out = new FileOutputStream("C:\\Users\\dp\\Desktop\\treasureMap.jpg");

        File file = new File("C:\\Users\\dp\\Desktop\\5.png");
        Image src2 = ImageIO.read(file);
        //绘制合成图像
        Graphics g = tag.createGraphics();
        for (int i = 1; i <= paths; i++) {
            file = new File(strings[i-1]);
            src = ImageIO.read(file);
            g.drawImage(src,w/4, ((i-1)*(h/2))  , w/2, h/2, null);

            g.drawImage(src2,w/4,(i-1)*h/2,w*2/4,10,null);

        }

        // 释放此图形的上下文以及它使用的所有系统资源。
        g.dispose();
        //将绘制的图像生成至输出流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag);
        //关闭输出流
        out.close();
        System.out.println("images merge successful.");

    }



    /**
     * 添加图片水印
     *
     * @param targetImg
     *            目标图片路径，如：C://myPictrue//1.jpg
     * @param waterImg
     *            水印图片路径，如：C://myPictrue//logo.png
     * @param x
     *            水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y
     *            水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha
     *            透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public final static void drawRect(String targetImg, int x, int y,int width,int height) {
        try {
            File imageFile = new File(targetImg);
            BufferedImage img = ImageIO.read(imageFile);

            Graphics2D graph = img.createGraphics();
            graph.setColor(Color.RED);
            Stroke stroke = graph.getStroke();
            graph.setStroke(new BasicStroke(3,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND));

            graph.drawRect(x,y,width,height); // 水印文件结束
            graph.setStroke(stroke);
            graph.dispose();

            ImageIO.write(img, "jpg", new File(targetImg));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setText(String targetImg){
        try{
            String key = "Sample";
            Image image = ImageIO.read(new File(targetImg));
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(0, 0, 200, 50);

            graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
            graphics.drawString(key, 10, 25);
            ImageIO.write(bufferedImage, "jpg", new File(
                "C:\\Users\\Justin\\Desktop\\Desktop\\20.png"));
            System.out.println("Image Created");
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    /**
     * 添加文字水印
     *
     * @param targetImg
     *            目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText
     *            水印文字， 如：中国证券网
     * @param fontName
     *            字体名称， 如：宋体
     * @param fontStyle
     *            字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize
     *            字体大小，单位为像素
     * @param color
     *            字体颜色
     * @param x
     *            水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y
     *            水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha
     *            透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg, String pressText,
                                 String fontName, int fontStyle, int fontSize, Color color, int x,
                                 int y, float alpha) {
        try {
            File file = new File(targetImg);

            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                alpha));

            int width_1 = fontSize * getLength(pressText);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }

            g.drawString(pressText, x, y + height_1);
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     *
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    /**
     * 图片缩放
     *
     * @param filePath
     *            图片路径
     * @param height
     *            高度
     * @param width
     *            宽度
     * @param bb
     *            比例不对时是否需要补白
     */
    public static void resize(String filePath, int height, int width, boolean bb) {
        try {
            double ratio = 0; // 缩放比例
            File f = new File(filePath);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height,
                BufferedImage.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                        / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(
                    AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {
                BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                        itemp.getWidth(null), itemp.getHeight(null),
                        Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                        itemp.getWidth(null), itemp.getHeight(null),
                        Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException,Exception {
        ImageTset("C:\\Users\\dp\\Desktop\\1.png", "C:\\Users\\dp\\Desktop\\2.png", "C:\\Users\\dp\\Desktop\\3.png", "C:\\Users\\dp\\Desktop\\1.png", "C:\\Users\\dp\\Desktop\\2.png", "C:\\Users\\dp\\Desktop\\3.png", "C:\\Users\\dp\\Desktop\\4.png");
        // drawRect("D:\\worspaces\\reomte\\devicepass-automation-poc\\DP-UI\\DP-Test\\target\\screenCaptures\\Test_FCApps_UiChange.png",  220, 67,60,30);
        //setText("C:\\Users\\Justin\\Desktop\\Desktop\\2.png");
    }

}
