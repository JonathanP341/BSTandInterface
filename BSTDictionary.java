public class BSTDictionary implements BSTDictionaryADT {
    private BinarySearchTree tree;

    //Might not need this
    public BSTDictionary() {
        this.tree = new BinarySearchTree();
    }
    public Record get(Key k) {
        BSTNode node = tree.get(tree.getRoot(), k);
        if (node == null) {
            return null;
        }
        return node.getRecord();
    }
    public void put(Record d) throws DictionaryException {
        tree.insert(tree.getRoot(), d);
    }
    public void remove(Key k) throws DictionaryException {
        tree.remove(tree.getRoot(), k);
    }
    public Record successor(Key k) {
        BSTNode node = tree.successor(tree.getRoot(), k);
        return node.getRecord();
    }
    public Record predecessor(Key k) {
        BSTNode node = tree.predecessor(tree.getRoot(), k);
        return node.getRecord();
    }
    public Record smallest() {
        BSTNode node = tree.smallest(tree.getRoot());
        return node.getRecord();
    }
    public Record largest() {
        BSTNode node = tree.largest(tree.getRoot());
        return node.getRecord();
    }

    public void printTree() {
        printTreeRec(this.tree.getRoot(), 0);
    }

    public BinarySearchTree getTree() {
        return tree;
    }

    public void printTreeRec(BSTNode r, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        if (r == null) {
            System.out.println("Null");
        } else  {
            System.out.print(r.getRecord().getKey().getLabel() + "\n");
            printTreeRec(r.getLeftChild(), indent+3);
            printTreeRec(r.getRightChild(), indent+3);
        }
    }

}
