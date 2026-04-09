
package TreePackage;

/**
 * An implementation of the ADT Binary Node.
 * 
 */
class BinaryNode<T> {

    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;
    // ADD PRIVATE VARIABLEs TO HOLD A PRARENT REFERENCE 
    // AND A THREAD REFERENCE HERE
    private BinaryNode<T> parent;
    private BinaryNode<T> thread;

    public BinaryNode() {
        this(null); // Call next constructor
    } // end default constructor

    public BinaryNode(T dataPortion) {
        this(dataPortion, null, null); // Call next constructor
    } // end constructor

    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
            BinaryNode<T> newRightChild) {
        // MODIFY THIS CONSTRUCTOR
        this(dataPortion, newLeftChild, newRightChild, null);
    } // end constructor

    // ADD TWO MORE CONSTRUCTORS

    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
            BinaryNode<T> newRightChild, BinaryNode<T> newParent){
        this(dataPortion, newLeftChild, newRightChild, newParent, null);
    }

    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
            BinaryNode<T> newRightChild, BinaryNode<T> newParent, BinaryNode<T> newThread){
        data = dataPortion;
        leftChild = newLeftChild;
        rightChild = newRightChild;
        parent = newParent;
        thread = newThread;
    }
    
    /**
     * Retrieves the data portion of this node.
     *
     * @return The object in the data portion of the node.
     */
    public T getData() {
        return data;
    } // end getData

    /**
     * Sets the data portion of this node.
     *
     * @param newData The data object.
     */
    public void setData(T newData) {
        data = newData;
    } // end setData

    /**
     * Retrieves the left child of this node.
     *
     * @return The node that is this node's left child.
     */
    public BinaryNode<T> getLeftChild() {
        return leftChild;
    } // end getLeftChild

    /**
     * Sets this node's left child to a given node.
     *
     * @param newLeftChild A node that will be the left child.
     */
    public void setLeftChild(BinaryNode<T> newLeftChild) {
        leftChild = newLeftChild;
    } // end setLeftChild

    /**
     * Detects whether this node has a left child.
     *
     * @return True if the node has a left child.
     */
    public boolean hasLeftChild() {
        return leftChild != null;
    } // end hasLeftChild

    /**
     * Retrieves the right child of this node.
     *
     * @return The node that is this node's right child.
     */
    public BinaryNode<T> getRightChild() {
        return rightChild;
    } // end getRightChild

    /**
     * Sets this nodes's right child to a given node.
     *
     * @param newRightChild A node that will be the right child.
     */
    public void setRightChild(BinaryNode<T> newRightChild) {
        rightChild = newRightChild;
    } // end setRightChild

    /**
     * Detects whether this node has a right child.
     *
     * @return True if the node has a right child.
     */
    public boolean hasRightChild() {
        return rightChild != null;
    } // end hasRightChild

    /**
     * Detects whether this node is a leaf.
     *
     * @return True if the node is a leaf.
     */
    public boolean isLeaf() {
        return (leftChild == null) && (rightChild == null);
    } // end isLeaf

    /**
     * Computes the height of the subtree rooted at this node.
     *
     * @return The height of the subtree rooted at this node.
     */
    public int getHeight() {
        return getHeight(this); // Call private getHeight
    } // end getHeight

    private int getHeight(BinaryNode<T> node) {
        int height = 0;
        if (node != null) {
            height = 1 + Math.max(getHeight(node.getLeftChild()),
                    getHeight(node.getRightChild()));
        }
        return height;
    } // end getHeight

    /**
     * Counts the nodes in the subtree rooted at this node.
     *
     * @return The number of nodes in the subtree rooted at this node.
     */
    public int getNumberOfNodes() {
        int leftNumber = 0;
        int rightNumber = 0;

        if (leftChild != null) {
            leftNumber = leftChild.getNumberOfNodes();
        }

        if (rightChild != null) {
            rightNumber = rightChild.getNumberOfNodes();
        }

        return 1 + leftNumber + rightNumber;
    } // end getNumberOfNodes

    /**
     * Copies the subtree rooted at this node.
     *
     * @return The root of a copy of the subtree rooted at this node.
     */
    public BinaryNode<T> copy() {
        BinaryNode<T> newRoot = new BinaryNode<T>(data);
        if (leftChild != null) {
            newRoot.setLeftChild(leftChild.copy(newRoot));
            leftChild.linkSubtreeThreadOut(newRoot);
        }
        if (rightChild != null) {
            newRoot.setRightChild(rightChild.copy(newRoot));
            newRoot.setThread(rightChild.getLeftmostInSubtree());
        }
        newRoot.setParent(null);

        
        return newRoot;
    } // end copy

    // ADD IN ANOTHER COPY THAT TAKES A PARENT REFERENCE

    public BinaryNode<T> copy(BinaryNode<T> p) {
        BinaryNode<T> newRoot = new BinaryNode<T>(data);
        if (leftChild != null) {
            BinaryNode<T> newLeft = leftChild.copy(newRoot);
            newRoot.setLeftChild(newLeft);
            newLeft.linkSubtreeThreadOut(newRoot);
        }
        if (rightChild != null) {
            BinaryNode<T> newRight = rightChild.copy(newRoot);
            newRoot.setRightChild(newRight);
            newRoot.setThread(newRight.getLeftmostInSubtree());
        }
        newRoot.setParent(p);

        return newRoot;
    } // end copy of a copy
    
    // ADD IN ACCESSORS FOR THE PARENT REFERENCE
    // AND THREAD REFERENCE

    // PARENT METHODS

    public BinaryNode<T> getParent(){
        return parent;
    }
    public void setParent(BinaryNode<T> p){
        parent = p;
    }
    public boolean hasParent(){
        return (parent != null);
    }

    // THREAD METHODS

    public BinaryNode<T> getThread(){
        return thread;
    }
    public void setThread(BinaryNode<T> target){
        thread = target;
    }
    public boolean hasThread(){
        return (thread != null);
    }

    // MORE METHODS

    public void linkSubtreeThreadOut(BinaryNode<T> linkTo){
        BinaryNode<T> curr = this;
        while(curr.getRightChild() != null)
            curr = curr.getRightChild();
        curr.setThread(linkTo);
    }

    public BinaryNode<T> getLeftmostInSubtree(){
        BinaryNode<T> curr = this;
        while(curr.getLeftChild() != null)
            curr = curr.getLeftChild();
        return curr;
    }
        
} // end BinaryNode
