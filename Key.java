public class Key {
    private String label;
    private int type;

    public Key(String theLabel, int theType) {
        type = theType;
        label = theLabel.toLowerCase();
    }
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
    public String getLabel() {
        return label;
    }

    public int getType() {
        return type;
    }
}
