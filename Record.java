/**
 * This is the class to create a Record object storing a key and a data item
 *
 * Class: CS2210
 * Date: March 24th 2024
 * @author Jonathan Peters
 */
public class Record {
    private Key theKey;
    private String data;

    /**
     * Name: Key
     * Creates a Key object
     * @param String data - Stored string for the record
     * @param Key k - K being stored in the record
     */
    public Record(Key k, String data) {
        theKey = k;
        this.data = data;
    }
    /**
     * Name: getKey
     * Gets the key of the record
     * @return Key - the Key
     */
    public Key getKey() {
        return theKey;
    }
    /**
     * Name: getDataItem
     * Gets the data item of the key
     * @return String - the data item
     */
    public String getDataItem() {
        return data;
    }
}
