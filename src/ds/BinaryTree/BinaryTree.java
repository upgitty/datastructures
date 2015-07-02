package ds.BinaryTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	private Node root;
	
	/**
	 * Node in a binary tree
	 * @author ULHAS
	 *
	 */
	private static class Node {
		Node left;
		Node right;
		int data;
		
		public Node(int d) {
			left = null;
			right = null;
			data = d;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
	}
	
	/**
	 * Creates an EMPTY binary tree
	 */
	public BinaryTree() {
		root = null;
	}
	
	/******************************************************************/
	/** LOOKUP **/
	/******************************************************************/
	
	public boolean lookup(int data) {
		return lookup(root,data);
	}
	
	/**
	 * RECURSIVE LOOKUP
	 * @param node
	 * @param data
	 * @return
	 */
	private boolean lookup(Node node, int data) {
		if(node == null) {
			return false;
		}
		
		if(data == node.data) {
			return true;
		}else if(data < node.data) {
			return lookup(node.left, data);
		}else {
			return lookup(node.right, data);
		}
	}
	
	/******************************************************************/
	/** INSERT **/
	/******************************************************************/
	
	public void insert(int data) {
		root = insert(root, data);
	}
	
	/**
	 * RECURSIVE INSERT
	 * @param node
	 * @param data
	 * @return
	 */
	private Node insert(Node node, int data) {
		if(node == null) {
			node = new Node(data);
		} else {
			if(data <= node.data) {
				node.left = insert(node.left, data);
			}else {
				node.right = insert(node.right, data);
			}
		}
		return node;
		
	}
	
	/******************************************************************/
	/** BUILD **/
	/******************************************************************/
	
	public void build(int[] values) {
		for(int i=0; i<values.length; i++) {
			insert(values[i]);
		}
	}
	
	/******************************************************************/
	/** PRETTY PRINT **/
	/******************************************************************/
	
	public void prettyPrint() {
        prettyPrint("", root);
    }

	/**
	 * RECURSIVE PRETTY PRINT.
	 * We print right first so that we can see the tree the way we draw on paper 
	 * @param prefix
	 * @param node
	 */
    private void prettyPrint(String prefix, Node node) {
    	if(node == null) {
    		return;
    	}
    	boolean isTail = (node.left == null && node.right == null);
        
        prettyPrint(prefix + (isTail ? "    " : "    "), node.right);
        System.out.println(prefix + (isTail ? "|--" : "|--") + node.data);
        prettyPrint(prefix + (isTail ? "    " : "    "), node.left);
        
    }
    
    
    /******************************************************************/
	/** PRINT INORDER **/
	/******************************************************************/
	
	public void printInorder() {
		printInorder(root);
	}
	
	/**
	 * RECURSIVE INORDER PRINT
	 * @param node
	 */
	private void printInorder(Node node) {
		if(node == null) {
			return;
		}
		printInorder(node.left);
		System.out.print(node.data + " ");
		printInorder(node.right);
	}
	
	/******************************************************************/
	/** SIZE **/
	/******************************************************************/
	
	public int size(){
		return size(root);
	}
	
	private int size(Node node) {
		if(node == null) {
			return 0;
		}
		return size(node.left) + 1 + size(node.right);
	}
	
	/******************************************************************/
	/** MAX DEPTH **/
	/******************************************************************/
	
	public int maxDepth() {
		return maxDepth(root);
	}
	
	private int maxDepth(Node node) {
		if(node == null) {
			return 0;
		}
		
		int l = maxDepth(node.left);
		int r = maxDepth(node.right);
		
		return (Math.max(l, r) + 1);
	}
	
	/******************************************************************/
	/** MIN VALUE **/
	/******************************************************************/
	
	/**
	 * MIN VALUE in a non empty binary tree
	 * @return
	 * @throws Exception  
	 */
	public int minValue() throws Exception {
		if(root == null) {
			throw new Exception("Empty tree !");
		}
		return minValue(root);
	}
	
	private int minValue(Node node) {
		if(node.left == null && node.right == null) {
			return node.data;
		}
		int l = Integer.MAX_VALUE, r = Integer.MAX_VALUE;
		if(node.left != null) {
			l = minValue(node.left);
		}
		
		if(node.right != null) {
			r = minValue(node.right);
		}
		return Math.min(node.data, Math.min(l, r));
	}
	
	/******************************************************************/
	/** HAS PATH SUM **/
	/******************************************************************/
	
	public boolean hasPathSum(int sum) {
		return hasPathSum(root, sum);
	}
	
	/**
	 * RECURSIVE HAS PATH SUM
	 * @param node
	 * @param sum
	 * @return
	 */
	private boolean hasPathSum(Node node, int sum) {
		if(node == null) {
			return false;
		}
		if(node.left == null && node.right == null) {
			return sum == node.data;
		}
		
		boolean l = hasPathSum(node.left, sum - node.data);
		boolean r = hasPathSum(node.right, sum - node.data);
		
		return l || r;
	}
	
	/******************************************************************/
	/** PRINT ALL ROOT->LEAF PATHS **/
	/******************************************************************/
	
	public void printAllPaths() {
		printAllPaths(root, new int[1000], 0);
	}
	
	/** 
	 * RECURSIVE PRINT ALL PATHS
	 * @param node
	 * @param paths
	 * @param pathLen
	 */
	private void printAllPaths(Node node, int[] paths, int pathLen) {
		if(node == null) {
			return;
		}
		
		paths[pathLen] = node.data;
		pathLen++;
		
		if(node.left == null && node.right == null) {
			printArray(paths, pathLen);
		}else {
			printAllPaths(node.left, paths, pathLen);
			printAllPaths(node.right, paths, pathLen);
		}
	}
	
	private void printArray(int[] ints, int len) {
		for(int i=0; i<len; i++) {
			System.out.print(ints[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * NON RECURSIVE PRINT ALL PATHS
	 * current = root
	 * path = root + ""
	 * Add current as well as path until now to the queue
	 * while(queue is not empty) {
	 *    pop next node and path
	 *    if node is leaf .. print path
	 *    else 
	 *    		add left node and path until left node to queue
	 *    		add right node and path until right node to queue
	 *    
	 */
	public void printAllPathsNR() {
		if (root == null) {
			return;
		}

		Queue<Object> queue = new LinkedList<Object>();
		queue.add(root);
		queue.add(root.data + "");

		while (!queue.isEmpty()) {

			Node head = (Node) queue.poll();
			String headPath = (String) queue.poll();

			if (head.isLeaf()) {
				System.out.println(headPath);
				continue;
			}

			if (head.left != null) {
				String leftStr = headPath + " " + head.left.data;
				queue.add(head.left);
				queue.add(leftStr);
			}

			if (head.right != null) {
				String rightStr = headPath + " " + head.right.data;
				queue.add(head.right);
				queue.add(rightStr);
			}
		}
		
	}
	
	private void printStack(Stack stack){
		Iterator<Node> iter = stack.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next().data + " ");
		}
		System.out.println();
	}
	
	/******************************************************************/
	/** PRINT ALL ROOT->LEAF PATHS **/
	/******************************************************************/
	
	public void mirror() {
		mirror(root);
	}
	
	private void mirror(Node node) {
		if(node != null) {
			mirror(node.left);
			mirror(node.right);
			
			Node temp = node.left;
			node.left= node.right;
			node.right = temp;
		}
	}
	
	/******************************************************************/
	/** SAME TREE **/
	/******************************************************************/
	
	public boolean sameTree(BinaryTree compare) {
		return sameTree(root, compare.root);
	}
	
	private boolean sameTree(Node nodeA, Node nodeB) {
		
		if(nodeA == null && nodeB == null) {
			return true;
		}else if(nodeA != null && nodeB != null) {
			return (nodeA.data == nodeB.data &&
					sameTree(nodeA.left, nodeB.left) &&
					sameTree(nodeA.right, nodeB.right));
		}else {
			return false;
		}
		
	}
	
	
	/******************************************************************/
	/** COUNT TREES **/
	/******************************************************************/
	
	public static int countBST(int numKeys) {
		if(numKeys <= 1) {
			return 1;
		}else {
			int sum = 0;
			int left, right, root;
			
			for(root = 1; root<=numKeys; root++) {
				left = countBST(root - 1);
				right = countBST(numKeys - root);
				
				sum += left * right;
			}
			return sum;
		}
	}
	
	/******************************************************************/
	/** IS BINARY SEARCH TREE **/
	/******************************************************************/
	
	public boolean isBST(){
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * RECURSIVE isBST
	 * @param node
	 * @param min
	 * @param max
	 * @return
	 */
	private boolean isBST(Node node, int min, int max) {
		
		if(node == null) {
			return true;
		}else {
			//Check Node
			boolean nodeOk = (node.data > min && node.data <= max);
			if(!nodeOk) {
				return false;
			}
			
			//Check Left
			boolean leftOk = isBST(node.left, min, node.data);
			if(!leftOk) {
				return false;
			}
			
			//Check Right
			boolean rightOk = isBST(node.right, node.data+1, max);
			return rightOk;
		}
	}
	
	
}
