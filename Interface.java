import java.io.*;
import java.util.*;
import java.lang.*;
/**
 * Class to read a file, put all of the information in the dictionary in the proper way
 * and then output the parts of the tree requested by the user
 *
 * Class: CS2210
 * Date: March 24th 2024
 * @author Jonathan Peters
 */
public class Interface {
    public static void main(String[] args) {
        //Name of the input file will be stored in args[0]
        //Creating a BSTDictionarty object
        BSTDictionary dict = new BSTDictionary();
        ArrayList<String> labels = new ArrayList<String>(); //ArrayList to hold the labels
        //Try catch to read the file
        try {
            //Getting a buffered reader object to go through the given file
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            String tempLabel = in.readLine(); //Storing the input from the file
            String tempTypeData; //Getting the temporary data type for the record
            Key k = null; //Initializing to stop error
            while (tempLabel  != null) { //Looping until temp = null
                tempTypeData = in.readLine();
                //Templabel stores the label for the key k and tempTypeData
                labels.add(tempLabel); //Adding each temp label to the array of labels
                switch (tempTypeData.charAt(0)) {
                    case '-': //Sound file
                        tempTypeData = tempTypeData.substring(1); //Removing the first part of the string
                        k = new Key(tempLabel, 3); //Creating a key
                        break;
                    case '+': //Music file
                        tempTypeData = tempTypeData.substring(1);//Removing the first part of the string
                        k = new Key(tempLabel, 4);//Creating a key
                        break;
                    case '*': //Voice file
                        tempTypeData = tempTypeData.substring(1);//Removing the first part of the string
                        k = new Key(tempLabel, 5);//Creating a key
                        break;
                    case '/': //French
                        tempTypeData = tempTypeData.substring(1);//Removing the first part of the string
                        k = new Key(tempLabel, 2);//Creating a key
                        break;
                    default: //In all other cases
                        if (!tempTypeData.contains(" ")) { //If it doesnt have spaces
                            //Checking each possible file extension
                            if (tempTypeData.endsWith(".gif")) {
                                k = new Key(tempLabel, 7);
                            } else if (tempTypeData.endsWith(".jpg")) {
                                k = new Key(tempLabel, 6);
                            } else if (tempTypeData.endsWith(".html")) {
                                k = new Key(tempLabel, 8);
                            } else {
                                k = new Key(tempLabel, 1);
                            }
                        } else { //If it does have spaces, then its none of the file extensions
                            k = new Key(tempLabel, 1);
                        }
                }
                dict.put(new Record(k, tempTypeData)); //Putting the new record in the dictionary
                tempLabel = in.readLine();
            }
        } catch (IOException e) { //Catching an exception
            System.out.println("Error occured while getting data from file");
        } catch (Exception d) {
            System.out.println("Error occured apart from getting data from the file");
        }

        //Getting user input and initializing the ways to read and display the files
        StringReader keyboard = new StringReader();
        SoundPlayer sp = new SoundPlayer();
        PictureViewer pv = new PictureViewer();
        ShowHTML show = new ShowHTML();
        String[] words;
        String line = "";
        Record d;
        //Looping until the user quits
        while (!line.equals("quit")) {
            line = keyboard.read("Enter next command: "); //Getting the user command
            words = line.trim().split(" ", 3); //Splitting the words into a space separated list
            switch (words[0]) { //Using a switch for the first word inputted by the user
                case "define": //If the user wants to define something
                    if (words.length > 1) { //If the length of the
                        d = dict.get(new Key(words[1], 1)); //Get the record
                        if (d == null) { //If null give error message
                            System.out.println("The word " + words[1] + " is not in the dictionary");
                        } else { //Else print out the value
                            System.out.println(d.getDataItem());
                        }
                    } else {
                        System.out.println("Invalid Command"); //Give invalid commands
                    }
                    break;
                case "translate": //If the user wants to translate something
                    d = dict.get(new Key(words[1], 2)); //Get the record
                    if (d == null) { //If null
                        System.out.println("There is no definition for the word " + words[1]);
                    } else {
                        System.out.println(d.getDataItem());
                    }
                    break;
                case "sound": //If the user wants to play a sound
                    d = dict.get(new Key(words[1], 3));
                    if (d == null) {
                        System.out.println("There is no sound file for " + words[1]);
                    } else {
                        try { //Try playing the record
                            sp.play(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Sound file error for " + words[1]);
                        }
                    }
                    break;
                case "play": //If the user wants to play a song
                    d = dict.get(new Key(words[1], 4)); //Get the record
                    if (d == null) {
                        System.out.println("There is no music file for " + words[1]);
                    } else {
                        try { //Try to play the song
                            sp.play(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Sound file error for " + words[1]);
                        }
                    }
                    break;
                case "say": //If the user wants to hear a file of a voice
                    d = dict.get(new Key(words[1], 5)); //Get the record
                    if (d == null) {
                        System.out.println("There is no voice file for " + words[1]);
                    } else {
                        try { //Try playing the sound
                            sp.play(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Sound file error for " + words[1]);
                        }
                    }
                    break;
                case "show": //If the user wants to show an image
                    d = dict.get(new Key(words[1], 6)); //Get the record
                    if (d == null) {
                        System.out.println("There is no image file for " + words[1]);
                    } else {
                        try { //Try showing the image
                            pv.show(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Show file error for " + words[1]);
                        }
                    }
                    break;
                case "animate": //If the user wants to play a gif
                    d = dict.get(new Key(words[1], 7)); //Get the gif
                    if (d == null) {
                        System.out.println("There is no animated image file for " + words[1]);
                    } else {
                        try { //Try playing the gif
                            pv.show(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Animated file error for " + words[1]);
                        }
                    }
                    break;
                case "browse": //If the user wants to browse a site
                    d = dict.get(new Key(words[1], 8)); //Get the record
                    if (d == null) {
                        System.out.println("There is no webpage " + words[1]);
                    } else {
                        show.show(d.getDataItem()); //Show the site
                    }
                    break;
                case "delete": //If the user wants to delete a node
                    try { //Try remove the node
                        dict.remove(new Key(words[1], Integer.parseInt(words[2])));
                        labels.remove(words[1]); //Remove the node from the labels
                    } catch (DictionaryException e) { //Exception if the record is not in the dictionary
                        System.out.println("No record in the ordered dictionary has key (" + words[1] + "," + words[2] + ")");
                    }
                    break;
                case "add": //If the user wants to add a node
                    try { //Try to add
                        d = new Record(new Key(words[1], Integer.parseInt(words[2])), words[3]); //Create new record
                        dict.put(d);//Add record to the dictionary
                        labels.add(words[1]); //add the label to the arraylist
                    } catch (DictionaryException e) { //If the node is already in the tree
                        System.out.println("A record with the given key (" + words[1] + ", " + words[2] + ") is already in the ordered dictionary");
                    }
                    break;
                case "list": //List all of the values with a certain prefix
                    //Looping through all of the labels in the arraylist
                    String output = ""; //String to concatenate output to
                    Collections.sort(labels);
                    for (String label: labels) {
                        if (label.startsWith(words[1])) { //If it starts with the prefix
                            output += label;
                            output += ", ";
                        }
                    }
                    if (output.endsWith(", ")) { //Change output to properly display
                        System.out.println(output.substring(0, output.length() - 2)); //Trim output then
                    } else { //Otherwise if nothing starts with the prefix
                        System.out.println("No label attributes in the ordered dictionary start with prefix " + args[1]);
                    }
                    break;
                case "first": //Get the first or smallest element in the tree
                    d = dict.smallest();
                    if (d == null) { //If null
                        System.out.println("No smallest in the dictionary");
                    } else { //Otherwise get the data item, label and type
                        System.out.println(d.getKey().getLabel() + "," + d.getKey().getType() + "," + d.getDataItem());
                    }
                    break;
                case "last": //Get the last or largest  element in the tree
                    d = dict.largest(); //Get largest
                    if (d == null) { //If null
                        System.out.println("No largest in the dictionary");
                    } else { //Otherwise get the data item, label and type
                        System.out.println(d.getKey().getLabel() + "," + d.getKey().getType() + "," + d.getDataItem());
                    }
                    break;
                case "exit":
                    System.exit(0); //Might not need this
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }
}
