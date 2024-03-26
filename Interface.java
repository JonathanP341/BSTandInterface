import java.io.*;
import java.util.*;
import java.lang.*;
public class Interface {
    public static void main(String[] args) {
        //Name of the input file will be stored in args[0]
        //Creating a BSTDictionarty object
        BSTDictionary dict = new BSTDictionary();
        ArrayList<String> labels = new ArrayList<String>();
        //Try catch to read the file
        try {
            //Getting a buffered reader object to go through the given file
            File file = new File("small.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tempLabel; //Storing the input from the file
            String tempTypeData;
            Key k = new Key("", 0); //Initializing to stop error
            Record d;
            while ((tempLabel = br.readLine()) != null && (tempTypeData = br.readLine()) != null) { //Looping until temp = null
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
                            }
                        } else { //If it does have spaces, then its none of the file extensions
                            k = new Key(tempLabel, 1);
                        }
                }
                d = new Record(k, tempTypeData); //Creating a new record to be put into the dictionary
                dict.put(d);
            }
        } catch (Exception e) {
            System.out.println("Error occured while getting data from file");
        }

        //Getting user input
        StringReader keyboard = new StringReader();
        SoundPlayer sp = new SoundPlayer();
        PictureViewer pv = new PictureViewer();
        ShowHTML show = new ShowHTML();
        String[] words;
        String line = "";
        Record d;
        while (!line.equals("quit")) {
            line = keyboard.read("Enter next command: ");
            words = line.trim().split(" "); //Splitting the words into a space separated list
            switch (words[0]) { //Using a switch for the first word inputted by the user
                case "define":
                    d = dict.get(new Key(words[1], 1));
                    if (d == null) {
                        System.out.println("The word " + words[1] + " is not in the ordered dictionary");
                    } else {
                        System.out.println(d.getDataItem());
                    }
                    break;
                case "translate":
                    d = dict.get(new Key(words[1], 2));
                    if (d == null) {
                        System.out.println("There is no definition for the word " + words[1]);
                    } else {
                        System.out.println(d.getDataItem());
                    }
                    break;
                case "sound":
                    d = dict.get(new Key(words[1], 3));
                    if (d == null) {
                        System.out.println("There is no sound file for " + words[1]);
                    } else {
                        try {
                            sp.play(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Sound file error for " + words[1]);
                        }
                    }
                    break;
                case "play":
                    d = dict.get(new Key(words[1], 4));
                    if (d == null) {
                        System.out.println("There is no music file for " + words[1]);
                    } else {
                        try {
                            sp.play(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Sound file error for " + words[1]);
                        }
                    }
                    break;
                case "say":
                    d = dict.get(new Key(words[1], 5));
                    if (d == null) {
                        System.out.println("There is no voice file for " + words[1]);
                    } else {
                        try {
                            sp.play(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Sound file error for " + words[1]);
                        }
                    }
                    break;
                case "show":
                    d = dict.get(new Key(words[1], 6));
                    if (d == null) {
                        System.out.println("There is no image file for " + words[1]);
                    } else {
                        try {
                            pv.show(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Show file error for " + words[1]);
                        }
                    }
                    break;
                case "animate":
                    d = dict.get(new Key(words[1], 7));
                    if (d == null) {
                        System.out.println("There is no animated image file for " + words[1]);
                    } else {
                        try {
                            pv.show(d.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println("Animated file error for " + words[1]);
                        }
                    }
                    break;
                case "browse":
                    d = dict.get(new Key(words[1], 8));
                    if (d == null) {
                        System.out.println("There is no webpage " + words[1]);
                    } else {
                        show.show(d.getDataItem());
                    }
                    break;
                case "delete":
                    try {
                        dict.remove(new Key(words[1], Integer.parseInt(words[2])));
                    } catch (DictionaryException e) {
                        System.out.println("No record in the dictionary has key (" + words[1] + ", " + words[2] + ")");
                    }
                    break;
                case "add":
                    try {
                        d = new Record(new Key(words[1], Integer.parseInt(words[2])), words[3]);
                        dict.put(d);
                    } catch (DictionaryException e) {
                        System.out.println("A record with the given key (" + words[1] + ", " + words[2] + ") is already in the ordered dictionary");
                    }
                    break;
                case "list":
                    //Looping through all of the labels in the arraylist
                    String output = ""; //String to concatenate output to
                    for (String label: labels) {
                        if (label.startsWith(words[1])) {
                            output += label;
                            output += ", ";
                        }
                    }
                    if (output.endsWith(", ")) {
                        System.out.println(output.trim().substring(0, output.length() - 1)); //Trim output then
                    } else {
                        System.out.println("No label attributes in the ordered dictionary start with prefix " + args[1]);
                    }
                    break;
                case "first":
                    d = dict.smallest();
                    if (d == null) {
                        System.out.println("No smallest in the dictionary");
                    } else {
                        System.out.println(d.getKey().getLabel() + "," + d.getKey().getType() + "," + d.getDataItem());
                    }
                    break;
                case "last":
                    d = dict.largest();
                    if (d == null) {
                        System.out.println("No largest in the dictionary");
                    } else {
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
