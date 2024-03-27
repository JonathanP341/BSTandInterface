/**
 * Class to implement a Binary Search Tree to make a dictionary
 *
 * Class: CS2210
 * Date: March 24th 2024
 * @author Jonathan Peters
 */
public class BSTDictionary implements BSTDictionaryADT {
    private BinarySearchTree tree;

    /**
     * Name: BSTDictionary
     * Creates a BSTDictionary object
     */
    public BSTDictionary() {
        this.tree = new BinarySearchTree();
    }

    /**
     * Name: get
     * Creates a BSTDictionary object
     * @param k - Key to find in the tree
     * @return Record found in the tree
     */
    public Record get(Key k) {
        BSTNode node = tree.get(tree.getRoot(), k); //Get the node
        if (node == null) { //If null return it
            return null;
        }
        return node.getRecord(); //Return the record of the node
    }
    /**
     * Name: put
     * Creates a BSTDictionary object
     * @param d - Record to add to the tree
     * @throws dictionaryexception if already in the tree
     */
    public void put(Record d) throws DictionaryException {
        tree.insert(tree.getRoot(), d);
    }
    /**
     * Name: remove
     * Creates a BSTDictionary object
     * @param k - Record to add to the tree
     * @throws dictionaryexception if not in the tree
     */
    public void remove(Key k) throws DictionaryException {
        tree.remove(tree.getRoot(), k);
    }
    /**
     * Name: successor
     * Finds the successor of a node given the key k
     * @param k - Key to find in the tree
     * @return Record - The successor found in the tree
     */
    public Record successor(Key k) {
        BSTNode node = tree.successor(tree.getRoot(), k);
        return node.getRecord();
    }
    /**
     * Name: predecessor
     * Finds the predecessor of a node given the key k
     * @param k - Key to find in the tree
     * @return Record - The predecessor found in the tree
     */
    public Record predecessor(Key k) {
        BSTNode node = tree.predecessor(tree.getRoot(), k);
        return node.getRecord();
    }
    /**
     * Name: smallest
     * Finds the smallest node in the tree
     * @return Record - The smallest node found in the tree
     */
    public Record smallest() {
        BSTNode node = tree.smallest(tree.getRoot());
        return node.getRecord();
    }
    /**
     * Name: largest
     * Finds the smallest node in the tree
     * @return Record - The smallest node found in the tree
     */
    public Record largest() {
        BSTNode node = tree.largest(tree.getRoot());
        return node.getRecord();
    }
}
