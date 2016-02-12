package NobelPrizeViewer;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by WinstonK on 11/10/2015.
 */
public class MainController {

    private final DefaultTableModel tableModel; // Model
    private final MainGUI gui; // View
    private ArrayList<NobelEntry> entryData = new ArrayList<>();

    public MainController(MainGUI gui) {

        // Custom initalization of table model
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        tableModel.addColumn("Year");
        tableModel.addColumn("Prize");
        tableModel.addColumn("Name");
        this.gui = gui;
        gui.setNobelTableModel(tableModel);
        gui.addPrevButtonListener(new PrevButtonListener());
        gui.addNextButtonListener(new NextButtonListener());

        // Set single selection mode
        gui.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gui.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (gui.getTable().getSelectedRow() > -1) {

                    // Reupdate the nobel entry
                    gui.setProfilePicture(null);
                    gui.setEntryDetails(entryData.get(gui.getTable().getSelectedRow()));
                    // Load picture worker
                    PictureLoader worker = new PictureLoader(entryData.get(gui.getTable().getSelectedRow()).getProfileLink(), tableModel, gui);
                    worker.execute();
                }

                // Handle previous / next buttons
                // enable/disable buttons
                if (gui.getTable().getSelectedRow() > 0
                        && gui.getTable().getSelectedRow() < gui.getTable().getRowCount() - 1) {
                    gui.getPrevButton().setEnabled(true);
                    gui.getNextButton().setEnabled(true);
                }
                
                if (gui.getTable().getSelectedRow() == 0) {
                    gui.getPrevButton().setEnabled(false);
                }
                if (gui.getTable().getSelectedRow() == (gui.getTable().getRowCount() - 1)) {
                    gui.getNextButton().setEnabled(false);
                }
            }
        });
        // Load nobel data
        try {
            Scanner scanner = new Scanner(new File("nobel.txt"));
            FileLoader worker = new FileLoader(scanner, tableModel, gui);
            worker.execute();
            entryData = worker.getEntryList(); // stores entry data
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showGUI() {
        gui.setVisible(true);
    }

    private class PrevButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.getTable().getSelectionModel().setSelectionInterval(gui.getTable().getSelectedRow() - 1, gui.getTable().getSelectedRow() - 1);

        }
    }

    private class NextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.getTable().getSelectionModel().setSelectionInterval(gui.getTable().getSelectedRow() + 1, gui.getTable().getSelectedRow() + 1);
        }
    }

}
