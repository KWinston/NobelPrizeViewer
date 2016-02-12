package NobelPrizeViewer;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Scanner;

// Nobel Prize data loader
public class FileLoader extends SwingWorker<Integer, NobelEntry> {

    private final Scanner scanner;
    private final DefaultTableModel tableModel;
    private final MainGUI gui;
    private final ArrayList<NobelEntry> nobelList = new ArrayList<>();

    public FileLoader(Scanner fileScanner, DefaultTableModel model, MainGUI view) {
        scanner = fileScanner;
        tableModel = model;
        gui = view;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int total = 0;
        
        // Keeps track of state if done processing the file
        boolean processingEntry = true;
        String input;
        String year = "";
        String name = "";
        String gender = "";
        String country = "";
        String affiliation = "";
        String prize = "";
        String birthYear = "";
        String deathYear = "";
        String profileLink = "";
        
        // Parsing entries
        while (processingEntry) {
            //Thread.sleep(1000);
            while (!(input = scanner.nextLine()).isEmpty()) {
                //System.out.println("Input: " + input);
                if (input.startsWith("year: ")) {
                    year = input.substring(6);
                }
                if (input.startsWith("prize: ")) {
                    prize = input.substring(7);
                }
                if (input.startsWith("name: ")) {
                    name = input.substring(6);
                }
                if (input.startsWith("gender: ")) {
                    gender = input.substring(8);
                }
                if (input.startsWith("photo: ")) {
                    profileLink = input.substring(7);
                }
                if (input.startsWith("country: ")) {
                    country = input.substring(9);
                }
                if (input.startsWith("affiliation: ")) {
                    affiliation = input.substring(13);
                }
                if (input.startsWith("prize: ")) {
                    prize = input.substring(7);
                }
                if (input.startsWith("birthyear: ")) {
                    birthYear = input.substring(11);
                }
                if (input.startsWith("deathyear: ")) {
                    deathYear = input.substring(11);
                }
            }
            
            if (scanner.hasNextLine()) {
                // Process entry
                NobelEntry nobelEntry = new NobelEntry(name, gender, year, country, affiliation, prize, birthYear, deathYear, profileLink);
                nobelList.add(nobelEntry);
                total++;
                publish(nobelEntry);
                scanner.nextLine();
            } else {
                // Final entry to process
                NobelEntry nobelEntry = new NobelEntry(name, gender, year, country, affiliation, prize, birthYear, deathYear, profileLink);
                nobelList.add(nobelEntry);
                total++;
                publish(nobelEntry);
                scanner.nextLine();
                processingEntry = false;
            }
        }
        return total;
    }

    @Override
    protected void process(List<NobelEntry> results) {
        // Add each entry processed into GUI element
        results.forEach((nobelEntry) -> {
            tableModel.addRow(nobelEntry.getEntryObject());
        });
    }

    @Override
    protected void done() {
        // Selects first entry after entries are loaded
        gui.getTable().setRowSelectionInterval(0, 0);
    }

    public ArrayList<NobelEntry> getEntryList() {
        return nobelList;
    }
}
