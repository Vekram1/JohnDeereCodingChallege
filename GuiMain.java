import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GuiMain {
    public static void main(String[] args) {
        while (true) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("FieldInfo.txt"));
                ArrayList<Field> fieldArrayList = new ArrayList<>();

                String line = "";
                int lineCounter = 0;

                while ((line = br.readLine()) != null) {
                    lineCounter++; // Number of lines in our text file
                }

                for (int i = 0; i < lineCounter; i += 7) {
                    String name = Files.readAllLines(Paths.get("FieldInfo.txt")).get(i);
                    String location = (Files.readAllLines(Paths.get("FieldInfo.txt")).get(i + 1)).substring(10);
                    int size = Integer.parseInt(Files.readAllLines(Paths.get("FieldInfo.txt")).get(i + 2).substring(7));
                    Field field = new Field(name, location, size);

                    fieldArrayList.add(field);
                }


                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(new FileWriter("FieldInfo.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                JOptionPane.showMessageDialog(null, "Welcome to the John Deere Operations Coordinator" +
                        "", "John Deere Ops", JOptionPane.INFORMATION_MESSAGE);

                Object[] options1 = {"create", "update", "delete", "select"};
                int selection = JOptionPane.showOptionDialog(null, "Would you like to create, update, delete, or select a field?",
                        "John Deere", JOptionPane.DEFAULT_OPTION, 0, null, options1, options1[0]);

                if (selection == 0) { // create

                    String name = JOptionPane.showInputDialog(null, "Enter the name of the field", "", JOptionPane.PLAIN_MESSAGE);
                    String location = JOptionPane.showInputDialog(null, "Enter the location of the field", "", JOptionPane.PLAIN_MESSAGE);
                    int acres = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the size of the field in acres", "", JOptionPane.PLAIN_MESSAGE));

                    Field field = new Field(name, location, acres);

                    JOptionPane.showMessageDialog(null, field.toString(), "", JOptionPane.PLAIN_MESSAGE);

                    pw.print(field.toString2());
                    pw.flush();

                } else if (selection == 1) { // update
                    ArrayList<String> fieldNames = new ArrayList<>();

                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        fieldNames.add(fieldArrayList.get(i).getName());
                    }

                    Object[] arrayFieldName = fieldNames.toArray();

                    String input = (String) JOptionPane.showInputDialog(null, "Choose Option",
                            "The choice", JOptionPane.QUESTION_MESSAGE, null, arrayFieldName, arrayFieldName[0]);

                    Field preAdjustedField = new Field();
                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        if (fieldArrayList.get(i).getName().equals(input)) {
                            preAdjustedField = fieldArrayList.get(i);
                        }
                    }

                    //System.out.println(arrayFieldName[1]);

                    Object[] options = {"location", "name", "size"};
                    int fieldEdits = JOptionPane.showOptionDialog(null, "Select What you want to chane", "Menu"
                            , JOptionPane.DEFAULT_OPTION, 0, null, options, options[0]);
                    //String selectionString = selectionObject.toString();

                    if (fieldEdits == 0) {
                        String editLocation = JOptionPane.showInputDialog(null, "Change the location",
                                "", JOptionPane.DEFAULT_OPTION);
                        preAdjustedField.setLocation(editLocation);
                    }
                    if (fieldEdits == 1) {
                        String editName = JOptionPane.showInputDialog(null, "Change the name",
                                "", JOptionPane.DEFAULT_OPTION);
                        preAdjustedField.setName(editName);
                    }
                    if (fieldEdits == 2) {
                        String editSize = JOptionPane.showInputDialog(null, "Change the size",
                                "", JOptionPane.DEFAULT_OPTION);
                        preAdjustedField.setAcres(Integer.parseInt(editSize));
                    }

                    PrintWriter writer = new PrintWriter("FieldInfo.txt");
                    writer.print("");
                    writer.close();

                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        pw.print(fieldArrayList.get(i).toString2());
                    }
                    pw.flush();


                    //System.out.println(options[Integer.parseInt(selectionString)]);
                } else if (selection == 2) { // delete field
                    ArrayList<String> fieldNames = new ArrayList<>();

                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        fieldNames.add(fieldArrayList.get(i).getName());
                    }

                    Object[] arrayFieldName = fieldNames.toArray();

                    String input = (String) JOptionPane.showInputDialog(null, "Choose Option",
                            "The choice", JOptionPane.QUESTION_MESSAGE, null, arrayFieldName, arrayFieldName[1]);

                    int where_to_remove = 0;
                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        if (fieldArrayList.get(i).getName().equals(input)) {
                            where_to_remove = i;
                        }
                    }
                    fieldArrayList.remove(where_to_remove);

                    PrintWriter writer = new PrintWriter("FieldInfo.txt");
                    writer.print("");
                    writer.close();

                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        pw.print(fieldArrayList.get(i).toString2());
                    }
                    pw.flush();
                } else if (selection == 3) {
                    ArrayList<String> fieldNames = new ArrayList<>();

                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        fieldNames.add(fieldArrayList.get(i).getName());
                    }

                    Object[] arrayFieldName = fieldNames.toArray();

                    String input = (String) JOptionPane.showInputDialog(null, "Choose Option",
                            "The choice", JOptionPane.QUESTION_MESSAGE, null, arrayFieldName, arrayFieldName[0]);

                    Field fieldCUD = new Field();
                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        if (fieldArrayList.get(i).getName().equals(input)) {
                            fieldCUD = fieldArrayList.get(i);
                        }
                    }

                    Object[] optionsCUD = {"Add operation", "update operation", "remove operation"};
                    int selectionCUD = JOptionPane.showOptionDialog(null, fieldCUD.toString(),
                            "John Deere", JOptionPane.DEFAULT_OPTION, 0, null, optionsCUD, optionsCUD[0]);

                    if (selectionCUD == 0) {
                        Object[] optionsFieldOps = {"Seeding", "Spraying", "Tillage", "Harvesting"};
                        int intOptionsFieldOps = JOptionPane.showOptionDialog(null, "Which operation would you like to add",
                                "John Deere", JOptionPane.DEFAULT_OPTION, 0, null, optionsFieldOps, optionsFieldOps[0]);
                        if (intOptionsFieldOps == 0) {
                            String seedName = JOptionPane.showInputDialog(null, "What seed do you want ot add?", JOptionPane.DEFAULT_OPTION);
                            fieldCUD.setSeed(seedName);
                        }
                        if (intOptionsFieldOps == 1) {
                            String sprayName = JOptionPane.showInputDialog(null, "What spray do you want ot add?", JOptionPane.DEFAULT_OPTION);
                            fieldCUD.setSpray(sprayName);
                        }
                        if (intOptionsFieldOps == 2) {
                            fieldCUD.setTillage(true);
                        }
                        if (intOptionsFieldOps == 3) {
                            fieldCUD.setHarvest(true);
                        }
                    } else if (selectionCUD == 1) {
                        Object[] optionsFieldOps = {"Seeding", "Spraying", "Tillage", "Harvesting"};
                        int intOptionsFieldOps = JOptionPane.showOptionDialog(null, "Which operation would you like to add",
                                "John Deere", JOptionPane.DEFAULT_OPTION, 0, null, optionsFieldOps, optionsFieldOps[0]);
                        if (intOptionsFieldOps == 0) {
                            String seedName = JOptionPane.showInputDialog(null, "What seed do you want ot add?", JOptionPane.DEFAULT_OPTION);
                            fieldCUD.setSeed(seedName);
                        }
                        if (intOptionsFieldOps == 1) {
                            String sprayName = JOptionPane.showInputDialog(null, "What spray do you want ot add?", JOptionPane.DEFAULT_OPTION);
                            fieldCUD.setSpray(sprayName);
                        }
                        if (intOptionsFieldOps == 2) {
                            fieldCUD.setTillage(true);
                        }
                        if (intOptionsFieldOps == 3) {
                            fieldCUD.setHarvest(true);
                        }
                    } else if (selectionCUD == 2) {
                        Object[] optionsFieldOps = {"Seeding", "Spraying", "Tillage", "Harvesting"};
                        int intOptionsFieldOps = JOptionPane.showOptionDialog(null, "Which operation would you like to add",
                                "John Deere", JOptionPane.DEFAULT_OPTION, 0, null, optionsFieldOps, optionsFieldOps[0]);
                        if (intOptionsFieldOps == 0) {
                            //String seedName = JOptionPane.showInputDialog(null, "What seed do you want ot add?", JOptionPane.DEFAULT_OPTION);
                            fieldCUD.setSeed(null);
                        }
                        if (intOptionsFieldOps == 1) {
                            //String sprayName = JOptionPane.showInputDialog(null, "What spray do you want ot add?", JOptionPane.DEFAULT_OPTION);
                            fieldCUD.setSpray(null);
                        }
                        if (intOptionsFieldOps == 2) {
                            fieldCUD.setTillage(false);
                        }
                        if (intOptionsFieldOps == 3) {
                            fieldCUD.setHarvest(false);
                        }
                    }
                    PrintWriter writer = new PrintWriter("FieldInfo.txt");
                    writer.print("");
                    writer.close();

                    int where = 0;
                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        if (fieldArrayList.get(i).getName().equals(fieldCUD.getName())) {
                            where = i;
                        }
                    }

                    fieldArrayList.remove(where);
                    fieldArrayList.add(fieldCUD);

                    for (int i = 0; i < fieldArrayList.size(); i++) {
                        pw.print(fieldArrayList.get(i).toString2());
                    }
                    pw.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

