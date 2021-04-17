package org.hsmak.letit;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            TreeNode currNode = nodes.remove();

            if (currNode != null) {
                TreeNode tempNode = currNode.left;
                currNode.left = currNode.right;
                currNode.right = tempNode;

                if (currNode.left != null)
                    nodes.add(currNode.left);
                if (currNode.right != null)
                    nodes.add(currNode.right);
            }
        }

        return root;
        // return invertNode(root);
    }

    public TreeNode invertTreeRecurse(TreeNode root) {
        if (root == null)
            return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.right = left;
        root.left = right;

        return root;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

