import java.util.*;
/**
 * Class to create a Binary Search Tree with the respective methods that allows a user to add
 * record objects to the tree
 *
 * Class: CS2210
 * Date: March 24th 2024
 * @author Jonathan Peters
 */
public class BinarySearchTree {
    private BSTNode root;

    /**
     * Name: BSTNode
     * Creates a BinarySearchTree object
     */
    public BinarySearchTree() {
        this.root = null; //Setting root to null
    }

    /**
     * Name: get
     * Get a given key from the tree, return null if not found
     * @param  r - The root to search from(used recursively)
     * @param  k - The data in the node were searching for
     */
    public BSTNode get(BSTNode r, Key k) {
        if (r == null) { //If null, return null as its not found
            return null;
        } else {
            int val = r.getRecord().getKey().compare(k); //Compare the key and the root
            if (val == 0) { //If equal return the node
                return r;
            } else if (val > 0) { //If r > k, check the left child of r
                return get(r.getLeftChild(), k);
            } else { //else check the right child
                return get(r.getRightChild(), k);
            }
        }
    }

    /**
     * Name: insert
     * Get a given key from the tree, return null if not found
     * @param  r - The root to search from(used recursively)
     * @param  d - The data in the node were inserting
     * @throws DictionaryException - If the node is already present
     */
    public void insert(BSTNode r, Record d) throws DictionaryException {
        BSTNode node = get(r, d.getKey()); //Get the node
        if (node != null) { //If its in the array throw an exception
            throw new DictionaryException("Already in dictionary");
        } else if (root == null) {//If the root itself is null, set the root to a new bstnode
            root = new BSTNode(d);
        } else { //Otherwise
            int val; //Used to compare the values of the nodes and d
            BSTNode curr = r; //Setting curr to the root to loop through the list
            while (curr != null) { //While curr is not a leaf
                val = curr.getRecord().getKey().compare(d.getKey()); //Set val to the compared value of curr and d
                if (val > 0) { //If curr is greater than d, get left child
                    if (curr.getLeftChild() == null) { //If val > d and there is no left child, make one
                        BSTNode newNode = new BSTNode(d);
                        newNode.setParent(curr);
                        curr.setLeftChild(newNode);
                        break;
                    }
                    //Increment curr
                    curr = curr.getLeftChild();
                } else { //If curr is greater than d get right child
                    if (curr.getRightChild() == null) { //If val < d and there is no right child, make one
                        BSTNode newNode = new BSTNode(d);
                        newNode.setParent(curr);
                        curr.setRightChild(newNode);
                        break;
                    }
                    curr = curr.getRightChild(); //Increment curr
                }
            }
        }
    }

    /**
     * Name: largest
     * Get the largest key lexicographically from the tree
     * @param  r - The root to search from(used recursively)
     */
    public BSTNode largest(BSTNode r) {
        if (r == null) { //If null return null as it is not in the tree
            return null;
        }
        if (r.getRightChild() == null) { //If the right child is null, then it is the biggest node so return it
            return r;
        } else { //Otherwise keep descending down the right tree
            return largest(r.getRightChild());
        }
    }
    /**
     * Name: smallest
     * Get the smallest key lexicographically from the tree
     * @param  r - The root to search from(used recursively)
     */
    public BSTNode smallest(BSTNode r) {
        if (r == null) {//If null return null as it is not in the tree
            return null;
        }
        if (r.getLeftChild() == null) { //If the left child is null, then it is the smallest node so return it
            return r;
        } else { //Otherwise keep descending down the left tree
            return smallest(r.getLeftChild());
        }
    }

    /**
     * Name: succcessor
     * Get a given node from the tree then find the next biggest node in that tree
     * @param  r - The root to search from
     * @param  k - The data in the node were inserting
     */
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode node = get(r, k); //Get the node
        if (node == null) { //If it doesnt exist
            return null;
        } else { //If the node exists then
            if (node.getRightChild() != null) { //If its right child is not a leaf then return the smallest node in the subtree starting from the right child
                return smallest(node.getRightChild()); //Get the smallest node bigger than it
            }
            else { //Otherwise
                node = node.getParent(); //Get its parent
                while (node != null && node.getRecord().getKey().compare(k) < 0) { //While node is not null and smaller than k
                    node = node.getParent(); //Get its parent
                }
                return node; //Return the found node
            }
        }
    }

    /**
     * Name: predecessor
     * Get a given node from the tree then find the next smallest node in that tree
     * @param  r - The root to search from
     * @param  k - The data in the node were inserting
     */
    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode node = get(r, k); //Get the node
        if (node == null) { //If it doesnt exist
            return null;
        } else { //If the node exists then
            if (node.getLeftChild() != null) { //If its right child is not a leaf then return the smallest node in the subtree starting from the
                return largest(node.getLeftChild()); //Get biggest node smaller than it
            }
            else { //Otherwise
                node = node.getParent(); //Get parent
                while (node != null && node.getRecord().getKey().compare(k) > 0) { //While node is not null and bigger than k
                    node = node.getParent(); //Get parent
                }
                return node; //Return the found node
            }
        }
    }

    /**
     * Name: remove
     * Remove a given key from the tree, return null if not found
     * @param  r - The root to search from
     * @param  d - The data in the node to find and remove
     * @throws DictionaryException - If the node is not present
     */
    public void remove(BSTNode r, Key k) throws DictionaryException {
        BSTNode node = get(r, k);
        if (node == null) { //If its not in the dictionary
            throw new DictionaryException("Node not in dictionary");
        } else { //If it is in the dictionary
            if (node.getRecord().getKey().compare(root.getRecord().getKey()) == 0 && node.isLeaf()) { //If the root has to be removed and its the only element
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
                    } else { //Otherwise set the root to the right child
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
                    } else { //Otherwise set the root to the left child
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

    /**
     * Name: getRoot
     * Gets the root of the BST
     * @return BSTNode - the Root
     */
    public BSTNode getRoot() {
        return root;
    }
}
