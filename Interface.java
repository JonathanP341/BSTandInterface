import java.io.*;
import java.util.*;
public class Interface {
    public static void main(String[] args) {
        //Name of the input file will be stored in args[0]
        //Creating a BSTDictionarty object
        BSTDictionary dict = new BSTDictionary();
        //Try catch to read the file
        try {
            //Getting a buffered reader object to go through the given file
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tempLabel; //Storing the input from the file
            String tempTypeData;
            Key k = new Key("", 0); //Initializing to stop error
            Record d;
            while ((tempLabel = br.readLine()) != null && (tempTypeData = br.readLine()) != null) { //Looping until temp = null
                //Templabel stores the label for the key k and tempTypeData
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
        String line = keyboard.read("Enter next command: ");

    }
}
