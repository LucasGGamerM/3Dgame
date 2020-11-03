
package pkg3dgame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.geom.AffineTransform;

public class Main {

    public static BufferedImage makeImageTranslucent(BufferedImage source, double alpha) {
        BufferedImage target = new BufferedImage(source.getWidth(),
                source.getHeight(), java.awt.Transparency.TRANSLUCENT);
        // Get the images graphics
        Graphics2D g = target.createGraphics();
        // Set the Graphics composite to Alpha
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                (float) alpha));
        // Draw the image into the prepared reciver image
        g.drawImage(source, null, 0, 0);
        // let go of all system resources in this Graphics
        g.dispose();
        // Return the image
        return target;
    }

    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return max;
        } else if (var <= min) {
            return min;
        } else {
            return var;
        }
    }
    public static BufferedImage LoadImage(String FileName)
    {
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(FileName));
        }catch(IOException e) {

        }
        return img;
    }

    public static AffineTransform rotate(AffineTransform at, boolean FlipVertically, boolean FlipHorizontally, BufferedImage image)
    {
        int i;
        int o;
        if(FlipHorizontally)
        {
            i = -1;
        }else{
            i = 1;
        }
        if (FlipVertically) {
            o = -1;
        } else {
            o = 1;
        }


        at.concatenate(AffineTransform.getScaleInstance( i, o));
        at.concatenate(AffineTransform.getTranslateInstance((int)Main.clamp(i , -1, 0) * image.getWidth(), (int)Main.clamp(o , -1, 0) * image.getHeight()));

        

        
        return at;
    }
    


    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new Game();
    }
    
}
//