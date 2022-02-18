
public class BST<K extends Comparable<K>, T> implements Map<K,T> {
	BSTNode<T, K> root, current, pivot;

	@Override
	public boolean empty() {
		return root == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
		current.data = e;

	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		Pair<Boolean, Integer> pair;
		if (empty()) {
			pair = new Pair<>(false,0);
			return pair;
		}
		int count = 0;
		BSTNode<T, K> p=root,q = root;

		while (p != null) {
			K k = p.key;
			q=p;
			count++;
			if (k.compareTo(key) == 0) {
				current = p;
				pivot=p;
				pair = new Pair<>(true,count);
				return pair;
			} else if (k.compareTo(key) > 0)
				p = p.left;
			else
				p = p.right;
		}
		pivot=q;
		pair = new Pair<>(false,count);
		return pair;
	}


	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		BSTNode<T, K> p, q = current;
		Pair<Boolean, Integer> pair = find(key);
		if (pair.first) {
			current = q;
			pair.first=false;
			return pair; // key already in the BST
		}

		p = new BSTNode<>(key, data);
		if (empty()) {
			root=current=pivot= p;
		} else {
			// current is pointing to parent of the new key
			if (key.compareTo(pivot.key) < 0)
				pivot.left = p;
			else
				pivot.right = p;
			current=pivot=p;
		}
		pair.first=true;
		return pair;
	}

	@Override
	public Pair<Boolean, Integer> remove(K key) {//its similar to the slides
		Pair<Boolean, Integer> pair=find(key);
		if(!pair.first)
			return pair;
		BSTNode<T, K> p;
		p = remove_aux(key, root);
		current = root = p;
		pair.first=true;
		return pair;
	}

	@Override
	public List<K> getAll() {
		List <K> list = new LinkedList<> ();
		List<K> l=new LinkedList<>();
		BSTNode<T,K> cur = root;
		recGet(cur,list);
		return list;
	}
	private void recGet(BSTNode<T,K> cur ,List<K> list){
		if (cur==null)
			return;

		if(cur.left != null)
		recGet(cur.left,list);

		list.insert(cur.key);

		if(cur.right != null)
		recGet(cur.right,list);

	}

	private BSTNode<T, K> remove_aux(K key, BSTNode<T, K> p) {
		BSTNode<T, K> q, child = null;
		if (p == null)
			return null;
		if (key.compareTo(p.key) < 0)
			p.left = remove_aux(key, p.left); //go left
		else if (key.compareTo(p.key) > 0)
			p.right = remove_aux(key, p.right); //go right
		else {
			if (p.left != null && p.right != null) { //two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right);

			}
			else {
				if (p.right == null) //one child
					child = p.left;
				else
					child = p.right;
				return child;
			}
		}
		return p;
	}

	private BSTNode<T,K> find_min(BSTNode<T,K> p){
		if(p == null)
			return null;

		while(p.left != null){
			p = p.left;
		}

		return p;
	}

}


