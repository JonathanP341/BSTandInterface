public class Record {
    private Key theKey;
    private String data;

    public Record(Key k, String data) {
        theKey = k;
        this.data = data;
    }
    public Key getKey() {
        return theKey;
    }
    public String getDataItem() {
        return data;
    }

}
