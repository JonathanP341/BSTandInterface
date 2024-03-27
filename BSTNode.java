/**
 * Class to create a node that will be used in a BST
 *
 * Class: CS2210
 * Date: March 24th 2024
 * @author Jonathan Peters
 */
public class BSTNode {
    private Record item;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private BSTNode parent;

    /**
     * Name: BSTNode
     * Creates a BSTNode object
     * @param Record item - Storing the data of the node
     */
    public BSTNode(Record item) {
        this.item = item;
        //Set every other part of the ndoe to null
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    /**
     * Name: isLeaf
     * Checks if the node is a leaf node by looking at its children
     * @return boolean - if its a leaf node or not
     */
    public boolean isLeaf() {
        if (leftChild == null && rightChild == null) { //if both children are null, return true
            return true;
        }
        return false;
    }
    /**
     * Name: setLeftChild
     * Set the left child to a given node
     * @param bstnode - the node to be set to the left child
     */
    public void setLeftChild(BSTNode u) {
        leftChild = u;
    }
    /**
     * Name: setRightChild
     * Set the right child to a given node
     * @param bstnode - the node to be set to the right child
     */
    public void setRightChild(BSTNode u) {
        rightChild = u;
    }
    /**
     * Name: setParent
     * Set the parent to a given node
     * @param bstnode - the node to be set to the parent
     */
    public void setParent(BSTNode u) {
        parent = u;
    }
    /**
     * Name: Key
     * Creates a Key object
     * @param String data - Stored string for the record
     * @param Key k - K being stored in the record
     */
    public Record getRecord() {
        return item;
    }
    /**
     * Name: setRecord
     * Set the record of a given node
     * @param record - the record to be set
     */
    public void setRecord(Record d) {
        item = d;
    }
    /**
     * Name: getLeftChild
     * Get the left child of a given node
     * @return bstnode - The left child
     */
    public BSTNode getLeftChild() {
        return leftChild;
    }
    /**
     * Name: getRightChild
     * Get the right child of a given node
     * @return bstnode - The right child
     */
    public BSTNode getRightChild() {
        return rightChild;
    }
    /**
     * Name: getParent
     * Get the parent of a given node
     * @return bstnode - The parent
     */
    public BSTNode getParent() {
        return parent;
    }
}
