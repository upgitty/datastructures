package ds.BinaryTree;

public class Solution {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
//		int[] values = {50, 40, 30, 20, 60, 70, 80, 10};
		int[] values = {50, 40, 60, 30, 45, 55, 70};
		tree.build(values);
		
		System.out.println("INORDER : ");
		tree.printInorder();
		System.out.println("\n");
		
		System.out.println("SIZE : " + tree.size());
		System.out.println("\n");
		
		System.out.println("MAX DEPTH : " + tree.maxDepth());
		System.out.println("\n");
		
		System.out.println("TREE : ");
		tree.prettyPrint();
		System.out.println("\n");
		
		try {
			System.out.println("MIN VALUE : " + tree.minValue());
		} catch (Exception e) {
			System.out.println("Min value cannot be found in an empty tree !");
			e.printStackTrace(System.out);
		}

		System.out.print("HAS PATH SUM : ");
		for(int i=0; i<500; i++) {
			if(tree.hasPathSum(i))
			System.out.print(i + " ");
		}
		System.out.println("\n");
		
		System.out.println("PRINT ALL PATHS : ");
		tree.printAllPaths();
		System.out.println();
		
		System.out.println("PRINT ALL PATHS NR : ");
		tree.printAllPathsNR();
		System.out.println();
		
		System.out.println("MIRROR : ");
		tree.mirror();
		tree.prettyPrint();
		tree.mirror();
		System.out.println();
		
		System.out.println("SAME TREE : " + tree.sameTree(tree));
		int[] vals = {50, 40, 60, 30, 45, 55};
		BinaryTree t2 = new BinaryTree();
		t2.build(vals);
		System.out.println("NOT SAME TREE : " + tree.sameTree(t2));
		System.out.println();
		
		System.out.println("COUNT TREES : ");
		for(int i=1; i<10; i++) {
			System.out.println(i + " ==> " + BinaryTree.countBST(i));
		}
		System.out.println();
		
		System.out.println("IS-BST : " + tree.isBST());
		BinaryTree bt = new BinaryTree();
		int[] btv = {50, 40, 30, 20, 60, 70, 80, 10};
		bt.build(btv);
		bt.mirror();
		System.out.println("IS-NOT-BST : " + bt.isBST());
	}
}
