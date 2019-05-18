package helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/*
 * This object holds the set of the diamond images
 */
public class ImagesLoader {
	public static ArrayList<BufferedImage> imagesArray = new ArrayList<BufferedImage>();

    // File representing the folder that you select using a FileChooser
    static File dir;

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "gif", "png", "bmp","jpg","jpeg","bmp" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    // load images from folder
    public ArrayList<BufferedImage> getImages(String folder) {
    	imagesArray = null;
    	imagesArray = new ArrayList<BufferedImage>();
    	dir = new File(folder);
    	
    	System.out.println(folder);
    	
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;

                try {
                    img = ImageIO.read(f);
                    
                    imagesArray.add(img);
                    
                } catch (final IOException e) {
                    // handle errors here
                }
            }
            return imagesArray;
        }
		return null;
    }
    
}