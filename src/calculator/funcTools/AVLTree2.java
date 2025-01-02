package calculator.funcTools;

import calculator.ErrorCodes;
import calculator.funcTools.MyMap.Entry;

public class AVLTree2<E extends Comparable<E>> {
	public static class AVLNode<E extends Comparable<E>>{
		public Object val;
		public E key;
		public AVLNode<E> left;
		public AVLNode<E> right;
		private int height=1;
		
		public AVLNode() {}
		public AVLNode(E key, Object val) {
			this.key=key;
			this.val=val;
		}
	}
	
	private AVLNode<E> root;
	private int size=1;

	public AVLTree2(E key, Object val) {
		root=new AVLNode<>(key,val);
	}
	
	public AVLTree2(AVLNode<E> root) {
		this.root=root;
	}
	
	public AVLTree2() {
		this.root=null;
		this.size=0;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isLeafNode(AVLNode<E> node) {
		return node.left==null && node.right==null;
	}
	
	public int setHeights(AVLNode<E> node) {
		if(node==null) {
			return 0;
		}
		node.height=Integer.max(setHeights(node.left),setHeights(node.right))+1;
		return node.height;
	}
	
	public int height(AVLNode<E> node) {
		return node==null?0:node.height;
	}
	
	public void updateHeight(AVLNode<E> node) {
		node.height=Integer.max(height(node.left),height(node.right))+1;
	}
	
	private int bf(AVLNode<E> node) {
		return height(node.left)-height(node.right);
	}
	
	private AVLNode<E> RRotate(AVLNode<E> node){
		AVLNode<E> top=node.left;
		AVLNode<E> temp=top.right;
		top.right=node;
		node.left=temp;
		updateHeight(node);
		updateHeight(top); 
		return top;
	}
	
	private AVLNode<E> LRotate(AVLNode<E> node){
		AVLNode<E> top=node.right;
		AVLNode<E> temp=top.left;
		top.left=node;
		node.right=temp;
		updateHeight(node);
		updateHeight(top);
		return top;
	}

	public AVLNode<E> balance(AVLNode<E> node){
		if(node==null) {
			return null;
		}
		int bf=bf(node);
		AVLNode<E> temp=node;
		if(bf>1 && bf(node.left)>=0) {
			temp=RRotate(node);
		}
		else if(bf>1 && bf(node.left)<0) {
			node.left=LRotate(node.left);
			temp=RRotate(node);
		}
		else if(bf<-1 && bf(node.right)>0) {
			node.right=RRotate(node.right);
			temp=LRotate(node);
		}
		else if(bf<-1 && bf(node.right)<=0) {
			temp=LRotate(node);
		}
		return temp;
	}
	
	public Object get(E key) {
		if(size>0) {
			return doGet(root,key);
		}
		return null;
	}
	
	public Object doGet(AVLNode<E> node, E key) {
		if(node==null) {
			return null;
		}
		int result=key.compareTo(node.key);
		if(result<0) {
			return doGet(node.left,key);
		}
		else if(result>0) {
			return doGet(node.right,key);
		}
		else {
			return node.val;
		}
	}
	
	public void remove(E key) {
		if(size>1) {
			size-=1;
			root=doRemove(root,key);
		}
	}
	
	private AVLNode<E> doRemove(AVLNode<E> node,E key){
		if(node==null) {
			return null;
		}
		int result=key.compareTo(node.key);
		if(result<0) {
			node.left=doRemove(node.left,key);
		}
		else if(result>0) {
			node.right=doRemove(node.left,key);
		}
		else {//if the node is found
			if(isLeafNode(node)) {
				return null;
			}
			else if(node.left==null) {
				node=node.right;
			}
			else if(node.right==null) {
				node=node.left;
			}
			//when it has both the left and the right child
			else {
				AVLNode<E> successor=node.right;
				while(successor.left!=null) {
					successor=successor.left;
				}
				node.val=successor.val;
				node.key=successor.key;
				node.right=doRemove(node.right,successor.key);
			}
		}
		updateHeight(node);
		return balance(node);
	}
	
	public void put(E key, Object val) {
		size++;
		root=doPut(root,key,val);
	}
	
	private AVLNode<E> doPut(AVLNode<E> node, E key, Object val){
		if(node==null) {
			return new AVLNode<>(key,val);
		}
		int result=key.compareTo(node.key);
		if(result==0) {
			node.val=val;
			return node;
		}
		else if(result<0) {
			node.left=doPut(node.left,key,val);
		}
		else if(result>0) {
			node.right=doPut(node.right,key,val);
		}
		updateHeight(node);
		return balance(node);
	}
	
	public void inorder(List<AVLNode<E>> llk,AVLNode<E> node) {
		if(node==null) {
			return;
		}
		inorder(llk,node.left);
		llk.add(node);
		inorder(llk,node.right);
	}
	
	public void preorder(List<AVLNode<E>> llk,AVLNode<E> node) {
		if(node==null) {
			return;
		}
		llk.add(node);
		preorder(llk,node.left);
		preorder(llk,node.right);
	}
	
	public void postorder(List<AVLNode<E>> llk,AVLNode<E> node) {
		if(node==null) {
			return;
		}
		postorder(llk,node.left);
		postorder(llk,node.right);
		llk.add(node);
	}
	
	public void traverse(List<AVLNode<E>> llk, int option) {
		if(option==1) {
			inorder(llk,root);
		}
		else if(option==0) {
			preorder(llk,root);
		}
		else if(option==2) {
			postorder(llk,root);
		}
	}
	
	public static <E extends Comparable<E>>AVLTree2<E> createAVLTree(E[] keys, Object[] values) {
		if(keys.length!=values.length) {
			System.out.println(ErrorCodes.get("21")+"when creating an AVLTree");
			System.exit(0);
		}
		if(keys.length==0) {
			return new AVLTree2<>();
		}
		AVLTree2<E> avlTree=new AVLTree2<>(keys[0],values[0]);
		for(int i=1;i<keys.length;i++) {
			avlTree.put(keys[i],values[i]);
		}
		return avlTree;
	}
	
	public static <E extends Comparable<E>,T> AVLTree2<E> createAVLTree(Entry<E,T>[] pairs){
		if(pairs==null) {
			return null;
		}
		AVLTree2<E> avlTree=new AVLTree2<>(pairs[0].key,pairs[0].val);
		for(int i=1;i<pairs.length;i++) {
			avlTree.put(pairs[i].key, pairs[i].val);
		}
		return avlTree;
	}
}
