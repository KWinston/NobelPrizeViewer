package NobelPrizeViewer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class PictureLoader extends SwingWorker<BufferedImage, Void> {

    private final DefaultTableModel tableModel;
    private final MainGUI gui;
    private final String profileLink;
    private BufferedImage img;

    public PictureLoader(String profileLink, DefaultTableModel model, MainGUI view) {
        tableModel = model;
        gui = view;
        this.profileLink = profileLink;
    }

    @Override
    protected BufferedImage doInBackground() throws Exception {
        try {
                URL url = new URL(profileLink);
                BufferedImage img = ImageIO.read(url);
                gui.setProfilePicture(img);
            } catch (IOException ex) {
                Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        return img;
    }
}
