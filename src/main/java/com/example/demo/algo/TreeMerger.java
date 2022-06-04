package com.example.demo.algo;

import java.util.Stack;

public class TreeMerger {

  public static void main(String[] args) {
    TreeMerger merger = new TreeMerger();
    final var treeNode1 = new TreeNode(1);
    treeNode1.left = new TreeNode(-2, new TreeNode(7), new TreeNode(10));
    treeNode1.right = new TreeNode(3, new TreeNode(4), new TreeNode(3));
    final var treeNode2 = new TreeNode(3);
    treeNode2.left = new TreeNode(3, new TreeNode(1), new TreeNode(5));
    treeNode2.right = new TreeNode(4, new TreeNode(9), new TreeNode(2));
    final var treeNode = merger.mergeTrees(treeNode1, treeNode2);
    System.out.println(treeNode);
  }

  public TreeNode mergeTrees(TreeNode firstTree, TreeNode secondTree) {
    if (firstTree == null) {
      return secondTree;
    }
    if (secondTree == null) {
      return firstTree;
    }

    firstTree.val += secondTree.val;
    firstTree.left = mergeTrees(firstTree.left, secondTree.left);
    firstTree.right = mergeTrees(firstTree.right, secondTree.right);
    return firstTree;
  }

  public TreeNode mergeTreesStack(TreeNode firstTree, TreeNode secondTree) {
    if (firstTree == null) {
      return secondTree;
    }
    Stack<TreeNode[]> stack = new Stack<>();
    stack.push(new TreeNode[] {firstTree, secondTree});
    while (!stack.isEmpty()) {
      final var trees = stack.pop();
      if (trees[0] == null || trees[1] == null) {
        continue;
      }

      trees[0].val += trees[1].val;

      if (trees[0].left == null) {
        trees[0].left = trees[1].left;
      } else {
        stack.push(new TreeNode[] {trees[0].left, trees[1].left});
      }

      if (trees[0].right == null) {
        trees[0].right = trees[1].right;
      } else {
        stack.push(new TreeNode[] {trees[0].right, trees[1].right});
      }
    }

    return firstTree;
  }

  // Definition for a binary tree node.
  private static class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return String.join(
          "\n",
          String.valueOf(val),
          String.valueOf(left == null ? "null" : left),
          String.valueOf(right == null ? "null" : right));
    }
  }
}
