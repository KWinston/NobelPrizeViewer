// Name: Winston Kouch
// CMPT 305: Lab 5
// Student ID: 1645889
// November 26, 2015
//
package NobelPrizeViewer;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            MainController controller = new MainController(gui);
            controller.showGUI();
        });
    }
}
