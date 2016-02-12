/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobelPrizeViewer;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author WinstonK
 */

// Used to fill with empty picture while waiting for a picture to load
public final class EmptyPicture implements Icon {

  private int width;
  private int height;
  
  public EmptyPicture() {
    this(0, 0);
  }
  
  public EmptyPicture(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getIconHeight() {
    return height;
  }

  public int getIconWidth() {
    return width;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) {
  }

}