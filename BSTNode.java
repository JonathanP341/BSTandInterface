public class BSTNode {
    private Record item;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private BSTNode parent;
    public BSTNode(Record item) {
        this.item = item;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public boolean isLeaf() {
        if (leftChild == null && rightChild == null) {
            return true;
        }
        return false;
    }
    public void setLeftChild(BSTNode u) {
        leftChild = u;
    }
    public void setRightChild(BSTNode u) {
        rightChild = u;
    }
    public void setParent(BSTNode u) {
        parent = u;
    }
    public Record getRecord() {
        return item;
    }
    public void setRecord(Record d) {
        item = d;
    }
    public BSTNode getLeftChild() {
        return leftChild;
    }
    public BSTNode getRightChild() {
        return rightChild;
    }
    public BSTNode getParent() {
        return parent;
    }
}
