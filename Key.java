/**
 * This is the class to create a Key object with a label and type
 *
 * Class: CS2210
 * Date: March 24th 2024
 * @author Jonathan Peters
 */
public class Key {
    private String label; //String describing the key
    private int type; //Storing the type of label

    /**
     * Name: Key
     * Creates a Key object
     * @param String label - Label for the key
     * @param int Type - Type of key
     */
    public Key(String theLabel, int theType) {
        type = theType;
        label = theLabel.toLowerCase();
    }

    /**
     * Name: Compare
     * Compares two key objects based on lexicographical order
     * @param Key - Key to be compared to
     * @return int - 0 if equal, -1 if the object is smaller than k and 1 if its bigger
     */
    public int compare(Key k) {
        if (label.compareTo(k.getLabel()) > 0) {
            return 1;
        } else if (label.compareTo(k.getLabel()) < 0) {
            return -1;
        } else {
            if (type == k.getType()) {
                return 0;
            } else if (type < k.getType()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    /**
     * Name: getLabel
     * Gets the label of the key
     * @return String - the label
     */
    public String getLabel() {
        return label;
    }
    /**
     * Name: getType
     * Gets the type of the key
     * @return int - the type
     */
    public int getType() {
        return type;
    }
}
