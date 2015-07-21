package org;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.beans.beancontext.BeanContext;
import java.util.*;

/**
 * Created by nikko on 7/20/15.
 * from book landind your interview
 */


    enum SearchType { BFS, DFS, BST, INORDER,PREORDER,POSTORDER};

    interface TreeNode<T> {

        boolean Add(TreeNode n);
        boolean AddLeft(TreeNode n);
        boolean AddRight(TreeNode n);
        T getValue();
        void setValue(T value);
        TreeNode<T>[] getChildrens();
    }
    // binary tree for instance
    class BinaryNode<T> implements TreeNode<T> {

        private T value;
        private BinaryNode<T> left;
        private BinaryNode<T> right;

        public BinaryNode(T value) {
            super();
            this.value = value;
            left = null;
            right = null;
        }

        public BinaryNode(T value, BinaryNode<T> left) {
            this(value);
            this.left = left;
        }
        public BinaryNode(T value,BinaryNode<T> left, BinaryNode<T> right) {
            this(value,left);
            this.right = right;
        }

        @Override
        public boolean Add(TreeNode n) {
            BinaryNode<T> bn = (BinaryNode<T>) n;
            if (left == null) {
                left = bn;
            } else if (right == null){
                right = bn;
            } else {
                return false;
            }
            return true;
        }

        @Override
        public boolean AddLeft(TreeNode n) {
            BinaryNode bn = (BinaryNode) n;
            if (left == null) {
                left = bn;
                return true;
            }
            return false;
        }

        @Override
        public boolean AddRight(TreeNode n) {
            BinaryNode bn = (BinaryNode) n;
            if(right == null) {
                right = bn;
                return true;
            }
            return false;
        }


        @Override
        public T getValue() {
            return this.value;
        }

        @Override
        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public BinaryNode<T>[] getChildrens() {
            return new BinaryNode[]{left,right};
        }

        public BinaryNode<T> getLeftNode() {
            return left;
        }
        public BinaryNode<T> getRightNode() {
            return right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
        public String toString() {
            return value.toString();
        }
    }

    class BinaryTree<T> {
        protected BinaryNode<T> root;

        public BinaryTree() {
            root = null;
        }

        public int Height() {
            return HeightNode(root, 0);
        }

        private int HeightNode(BinaryNode node,int depth) {
            if (node == null) return depth;
            BinaryNode left = node.getLeftNode();
            BinaryNode right = node.getRightNode();
            if (left == null && right == null) {
                // leave
                return depth + 1;
            }
            int leftDepth = HeightNode(left,depth+1);
            int rightDepth = HeightNode(right,depth+1);
            return Integer.max(leftDepth, rightDepth);
        }
        public String Print(SearchType type) {
            switch(type) {
                case INORDER:
                    return PrintInOrder(root);
                case POSTORDER:
                    return PrintPostOrder(root);
                case PREORDER:
                    return PrintPreOrder(root);
                case BFS:
                    return PrintBFS(root);
                case DFS:
                    return PrintDFS(root);
            }
            return "";
        }


        private String PrintDFS(BinaryNode node) {
            if (node == null) return "";

            Stack<BinaryNode> stack = new Stack<>();
            StringBuffer buf = new StringBuffer();
            stack.push(node);
            while(!stack.empty()) {
                BinaryNode n = stack.pop();
                buf.append(n.getValue().toString() + " - ");
                BinaryNode left = n.getLeftNode();
                BinaryNode right = n.getRightNode();
                if (right != null) stack.push(right);
                if (left != null) stack.push(left);

            }
            return buf.toString();
        }
        private String PrintBFS(BinaryNode<T> node) {
            if (node == null) return "";

            Queue<BinaryNode> queue = new LinkedList<>();
            StringBuffer buf = new StringBuffer();
            queue.add(node);
            while(!queue.isEmpty()) {
                BinaryNode n = queue.poll();
                buf.append(n.getValue().toString() + " - ");
                BinaryNode left = n.getLeftNode();
                BinaryNode right = n.getRightNode();
                if (left != null) queue.add(left);
                if (right != null) queue.add(right);
            }
            return buf.toString();
        }
        private String PrintPreOrder(BinaryNode<T> node) {
            if( node == null) return "";

            StringBuffer buf = new StringBuffer();
            buf.append(node.getValue().toString() + " - ");
            buf.append(PrintPreOrder(node.getLeftNode()));
            buf.append(PrintPreOrder(node.getRightNode()));
            return buf.toString();
        }
        private String PrintPostOrder(BinaryNode<T> node) {
            if (node == null) return "";

            StringBuffer buf = new StringBuffer();
            buf.append(PrintPostOrder(node.getLeftNode()));
            buf.append(PrintPostOrder(node.getRightNode()));
            buf.append(node.getValue().toString() + " - ");
            return buf.toString();
        }
        private String PrintInOrder(BinaryNode<T> node) {
            if (node == null) return "";
            StringBuffer buf = new StringBuffer(   );
            buf.append(PrintInOrder(node.getLeftNode()));
            buf.append(node.getValue().toString() + " - ");
            buf.append(PrintInOrder(node.getRightNode()));
            return buf.toString();
        }
        public void Add(T value) {
            BinaryNode<T> node = new BinaryNode<T>(value);
            if (root == null) {
                root = node;
                return;
            }
            LinkedList<BinaryNode<T>> stack = new LinkedList<BinaryNode<T>>();
            stack.add(root);
            while(!stack.isEmpty()) {
                BinaryNode<T> tmp = stack.poll();
                if (tmp.Add(node) != false) break;
                for( BinaryNode<T> b : tmp.getChildrens()) {
                    stack.add(b);
                }
            }
        }

        public class AncestorData {
            BinaryNode<T> ancestor = null;
            boolean foundV1 = false;
            boolean foundV2 = false;
        }

        public T FindCommonAncestor(T v1,T v2) {
            AncestorData ad = WalkAncestor(root,v1,v2);
            if (ad.ancestor == null)
                return null;
            return ad.ancestor.getValue();
        }

        private AncestorData WalkAncestor(BinaryNode<T> node,T v1,T v2) {
            AncestorData ad = new AncestorData();
            if (node == null) {
                return ad;
            }

            if (node.getValue() == v1) {
                ad.foundV1 = true;
            }

            if (node.getValue() == v2) {
                ad.foundV2 = true;
            }

            if (ad.foundV1 && ad.foundV2) {
                ad.ancestor = node;
                return ad;
            }

            AncestorData left = WalkAncestor(node.getLeftNode(),v1,v2);
            AncestorData right = WalkAncestor(node.getRightNode(),v1,v2);
            ad.foundV1 |= left.foundV1 || right.foundV1;
            ad.foundV2 |= left.foundV2 || right.foundV2;
            ad.ancestor = left.ancestor != null ? left.ancestor : (right.ancestor != null ? right.ancestor : null);
            if (ad.foundV1 && ad.foundV2) {
                if (ad.ancestor == null) ad.ancestor = node;
                return ad;
            }
            return ad;
        }

        public BinaryNode<T> Search(T value,SearchType type) {
            return null;
        }

        public TreeNode Delete(T value,SearchType type) {
            if (type == SearchType.BFS) {
                //DeleteBFS
            } else if (type == SearchType.DFS) {
                //DeleteDFS
            }
            return null;
        }

    }

class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree(){
        super();
    }
    @Override
    public void Add(T value) {
        if (this.root == null) {
            this.root = new BinaryNode<T>(value);
            return;
        }
        AddToNode(value,root);
    }

    public void AddToNode(T value, BinaryNode<T> node) {
        if (value.compareTo(node.getValue()) == 0 || value.compareTo(node.getValue()) == - 1){
                if (node.getLeftNode() == null){
                    node.AddLeft(new BinaryNode(value));
                } else {
                    AddToNode(value,node.getLeftNode());
                }
            } else {
                if (node.getRightNode() == null) {
                    node.AddRight(new BinaryNode<T>(value));
                } else {
                    AddToNode(value,node.getRightNode());
                }
            }
    }

}

public class TreeGraph {

    public static void main(String[] args){
        System.out.println("[+] Trees & Graphs chapter");
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.Add(10);
        tree.Add(15);
        tree.Add(20);
        tree.Add(16);
        tree.Add(17);
        tree.Add(18);
        tree.Add(19);
        System.out.println("[+] Inorder traversal : " + tree.Print(SearchType.INORDER));
        System.out.println("[+] Preorder traversal : " + tree.Print(SearchType.PREORDER));
        System.out.println("[+] Postorder traversal : " + tree.Print(SearchType.POSTORDER));
        System.out.println("[+] BFS Traversal : " + tree.Print(SearchType.BFS));
        System.out.println("[+] DFS Traversal : " + tree.Print(SearchType.DFS));
        System.out.println("[+] Height of the tree : " + tree.Height());
        tree.Add(21);
        tree.Add(22);
        System.out.println("[+] Height of the tree (+21,22) : " + tree.Height());

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.Add(5);
        bst.Add(10);
        bst.Add(3);
        bst.Add(4);
        bst.Add(8);
        bst.Add(9);
        System.out.println("[+] Binary Search Tree (BFS traversal) : " + bst.Print(SearchType.BFS));
        System.out.println("[+] List : " + tree.Print(SearchType.BFS));
        int v1 = 17,v2 = 25;
        Integer anc = tree.FindCommonAncestor(v1,v2);
        System.out.println("[+] ==> Common Ancestor of (" + v1 +","+v2+") : " + (anc == null ? -1 : anc));
    }
}
