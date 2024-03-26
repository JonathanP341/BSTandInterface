import java.util.*;
public class BinarySearchTree {
    private BSTNode root;
    public BinarySearchTree() {
        //Just putting a record and setting label to lantern as its the middle word of the dictionary
        this.root = null;
    }

    public BSTNode get(BSTNode r, Key k) {
        if (r == null) {
            return null;
        } else {
            int val = r.getRecord().getKey().compare(k);
            if (val == 0) {
                return r;
            } else if (val > 0) {
                return get(r.getLeftChild(), k);
            } else {
                return get(r.getRightChild(), k);
            }
        }
    }
    public void insert(BSTNode r, Record d) throws DictionaryException {
        BSTNode node = get(r, d.getKey());
        if (node != null) { //If its in the array throw an exception
            throw new DictionaryException("Already in dictionary");
        } else if (root == null) {
            root = new BSTNode(d);
        } else {
            int val;
            BSTNode curr = r;
            while (curr != null) { //While curr is not a leaf
                val = curr.getRecord().getKey().compare(d.getKey());
                if (val > 0) { //If curr is greater than d, get left child
                    if (curr.getLeftChild() == null) {
                        BSTNode newNode = new BSTNode(d);
                        newNode.setParent(curr);
                        curr.setLeftChild(newNode);
                        break;
                    }
                    curr = curr.getLeftChild();
                } else { //If curr is greater than d get right child
                    if (curr.getRightChild() == null) {
                        BSTNode newNode = new BSTNode(d);
                        newNode.setParent(curr);
                        curr.setRightChild(newNode);
                        break;
                    }
                    curr = curr.getRightChild();
                }
            }
        }
    }

    public BSTNode largest(BSTNode r) {
        if (r == null) {
            return null;
        }
        if (r.getRightChild() == null) {
            return r;
        } else {
            return largest(r.getRightChild());
        }
    }

    public BSTNode smallest(BSTNode r) {
        if (r == null) {
            return null;
        }
        if (r.getLeftChild() == null) {
            return r;
        } else {
            return smallest(r.getLeftChild());
        }
    }
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode node = get(r, k);
        if (node == null) { //If it doesnt exist
            return null;
        } else { //If the node exists then
            if (node.getRightChild() != null) { //If its right child is not a leaf then return the smallest node in the subtree starting from the right child
                return smallest(node.getRightChild());
            }
            else {
                node = node.getParent();
                while (node != null && node.getRecord().getKey().compare(k) < 0) { //While node is not null
                    node = node.getParent();
                }
                return node;
            }
        }
    }

    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode node = get(r, k);
        if (node == null) { //If it doesnt exist
            return null;
        } else { //If the node exists then
            if (node.getLeftChild() != null) { //If its right child is not a leaf then return the smallest node in the subtree starting from the
                return largest(node.getLeftChild());
            }
            else {
                node = node.getParent();
                while (node != null && node.getRecord().getKey().compare(k) > 0) { //While node is not null
                    node = node.getParent();
                }
                return node;
            }
        }
    }

    public void remove(BSTNode r, Key k) throws DictionaryException {
        BSTNode node = get(r, k);
        if (node == null) { //If its not in the dictionary
            throw new DictionaryException("Node not in dictionary");
        } else { //If it is in the dictionary
            if (node.getRecord().getKey().compare(root.getRecord().getKey()) == 0) {
                root = null;
            }
            else if (node.isLeaf()) { //If its leaf then just remove it
                if (node.getParent().getRecord().getKey().compare(k) > 0) { //If the parent is bigger than the child then K is the left child so set it to null
                    node.getParent().setLeftChild(null);
                    node.setParent(null);
                } else { //Otherwise if its bigger remove the right child
                    node.getParent().setRightChild(null);
                    node.setParent(null);
                }
            } else { //If its not a leaf
                if (node.getLeftChild() == null) { //Cant be a leaf so if left child is null then right child is a node
                    BSTNode nodeParent = node.getParent();
                    if (nodeParent != null) { //If there is a parent
                        if (nodeParent.getLeftChild().getRecord().getKey().compare(node.getRecord().getKey()) == 0) { //If its the left child of parent
                            nodeParent.setLeftChild(node.getRightChild()); //Stting the new child node
                            nodeParent.getLeftChild().setParent(nodeParent); //Setting the new parent
                        } else {
                            nodeParent.setRightChild(node.getRightChild()); //Setting the new child node
                            nodeParent.getLeftChild().setParent(nodeParent); //Setting the new parent
                        }
                    } else {
                        root = node.getRightChild();
                        root.setParent(null);
                    }
                } else if (node.getRightChild() == null) { //Again cant be a leaf so if right child is null then left child is a node
                    BSTNode nodeParent = node.getParent();
                    if (nodeParent != null) { //If there is a parent
                        if (nodeParent.getLeftChild().getRecord().getKey().compare(node.getRecord().getKey()) == 0) { //If its the left child of parent
                            nodeParent.setLeftChild(node.getLeftChild()); //Stting the new child node
                            nodeParent.getLeftChild().setParent(nodeParent); //Setting the new parent
                        } else {
                            nodeParent.setRightChild(node.getLeftChild()); //Setting the new child node
                            nodeParent.getLeftChild().setParent(nodeParent); //Setting the new parent
                        }
                    } else {
                        root = node.getLeftChild();
                        root.setParent(null);
                    }
                } else { //Else if it has 2 children
                    BSTNode small = smallest(node.getRightChild()); //Get its successor
                    node.setRecord(small.getRecord()); //Swap the information
                    remove(small, small.getRecord().getKey()); //Remove the successor
                }
            }
        }
    }

    public BSTNode getRoot() {
        return root;
    }
}
