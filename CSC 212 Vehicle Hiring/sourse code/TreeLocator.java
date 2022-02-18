
public class TreeLocator<T> implements Locator<T> {
	TreeLocNode<T> root=null,cur=null;

	@Override
	public int add(T e, Location loc) {
		TreeLocNode<T> p = new TreeLocNode<>(e ,loc);
		if (loc==null)
			return 0;
		if(root==null){
			root=cur=p;
			return 0;
		}

		Pair<List<T>, Integer> pair=get(loc);

		if(cur.loc.x== loc.x && cur.loc.y== loc.y) {
			pair.first.insert(e);
		return pair.second;
		}

	 if(p.loc.x < cur.loc.x && p.loc.y<=cur.loc.y){
			if(cur.one!=null) {
				cur=cur.one;
			}
			cur.one=p;
		}
		if(p.loc.x <=cur.loc.x && p.loc.y>cur.loc.y){
			if(cur.tow!=null) {
				cur=cur.tow;
			}
			cur.tow=p;
		}
		if(p.loc.x > cur.loc.x &&  p.loc.y>=cur.loc.y){
			if(cur.three!=null) {

				cur=cur.three;

			}
			cur.three=p;
		}
		if(p.loc.x>=cur.loc.x &&  p.loc.y<cur.loc.y){
			if(cur.four!=null) {

				cur=cur.four;
			}
			cur.four=p;
		}

			return pair.second;
	}




		@Override
	public Pair<List<T>, Integer> get(Location loc) {
		int count=0;
		cur=root;
		List<T> list=new LinkedList<>();
		if(loc==null)
			return new Pair<>(list,0);
		if(loc.x==cur.loc.x && loc.y==cur.loc.y) {
			count++;
			return new Pair<>(cur.l,count);
		}
		while (cur!=null) {

			if (loc.x < cur.loc.x && loc.y <= cur.loc.y) {
				count++;
				if(cur.one==null) break;
				if (cur.one.loc.x == loc.x && cur.one.loc.y == loc.y) {
					cur=cur.one;
					return new Pair<>(cur.l, count);
				}
				cur=cur.one;
				continue;
			}
			if (loc.x <= cur.loc.x && loc.y > cur.loc.y) {
				count++;
				if(cur.tow==null)break;
				if (cur.tow.loc.x == loc.x && cur.tow.loc.y == loc.y) {
					cur=cur.tow;
					return new Pair<>(cur.l, count);
				}
				cur=cur.tow;
				continue;
			}
			if (loc.x > cur.loc.x && loc.y >= cur.loc.y) {
				count++;
				if(cur.three==null)break;
				if (cur.three.loc.x == loc.x && cur.three.loc.y == loc.y) {
					cur=cur.three;
					return new Pair<>(cur.l, count);
				}
				cur=cur.three;
				continue;
			}

			if (loc.x >= cur.loc.x && loc.y < cur.loc.y) {
				count++;
				if(cur.four==null)break;
				if (cur.four.loc.x == loc.x && cur.four.loc.y == loc.y){
					cur=cur.four;
					return new Pair<>(cur.l, count);
				}
				cur=cur.four;

			}

		}

		return new Pair<>(list,count);
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {

		Pair<List<T>, Integer> pair=get(loc);
		if(cur.loc.x!=loc.x && cur.loc.y!= loc.y)
			return new Pair<>(false,pair.second);
		boolean flag=false;
		if(!pair.first.empty()) {
			pair.first.findFirst();
			while (!pair.first.last()) {
				if (pair.first.retrieve().equals(e)) {
					pair.first.remove();
				flag=true;}
				else
					pair.first.findNext();
			}
			if (pair.first.retrieve().equals(e)) {
				pair.first.remove();
				flag=true;}
			else
				pair.first.findNext();
		}
		return new Pair<>(flag,pair.second);
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		cur=root;
		List<Pair<Location, List<T>>> pairList=new LinkedList<>();
		if(root==null)
			return pairList;


		Pair<Location, List<T>> p=new Pair<>(root.loc,root.l);
		pairList.insert(p);
		rec(pairList,cur);
		return pairList;
	}

	private void rec(List<Pair<Location, List<T>>> pairList,TreeLocNode<T> cur){
	if (cur==null)
		return;
	if(cur.one!=null){
		Pair<Location, List<T>> p=new Pair<>(cur.one.loc,cur.one.l);
		pairList.insert(p);
		rec(pairList,cur.one);
	}
	 if(cur.tow!=null){
		Pair<Location, List<T>> p=new Pair<>(cur.tow.loc,cur.tow.l);
		pairList.insert(p);

		rec(pairList,cur.tow);

	}
	 if (cur.three!=null){
		Pair<Location, List<T>> p=new Pair<>(cur.three.loc,cur.three.l);
		pairList.insert(p);

		rec(pairList,cur.three);
	}
	 if (cur.four!=null){
		Pair<Location, List<T>> p=new Pair<>(cur.four.loc,cur.four.l);
		pairList.insert(p);

		rec(pairList,cur.four);
	}

	}
	int countForRange=0;
	@Override

	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		Location upperLeft=new Location(lowerLeft.x, upperRight.y);
		Location lowerRight=new Location(upperRight.x, lowerLeft.y);

		cur=root;
		countForRange=0;
		List<Pair<Location, List<T>>> pairList=new LinkedList<>();
		if (cur.loc.x <= upperRight.x && cur.loc.y <= upperRight.y &&
				cur.loc.x >= upperLeft.x && cur.loc.y <= upperLeft.y &&
				cur.loc.x >= lowerLeft.x && cur.loc.y >= lowerLeft.y &&
				cur.loc.x <= lowerRight.x && cur.loc.y >= lowerRight.y)
			pairList.insert(new Pair<>(cur.loc, cur.l));
		RecInrange(lowerLeft, upperRight, pairList, cur);
		countForRange++;
		return new Pair<>(pairList,countForRange);
	}
	private void RecInrange(Location lowerLeft, Location upperRight, List<Pair<Location, List<T>>> pairList,TreeLocNode<T> cur){
		Location upperLeft=new Location(lowerLeft.x, upperRight.y);
		Location lowerRight=new Location(upperRight.x, lowerLeft.y);

		boolean blue=false;
		boolean red=false;
		boolean green=false;
		boolean yellow=false;

		if (upperRight.x< cur.loc.x && upperRight.y <= cur.loc.y ||
				lowerLeft.x < cur.loc.x && lowerLeft.y <= cur.loc.y ||
				upperLeft.x < cur.loc.x && upperLeft.y <= cur.loc.y ||
				lowerRight.x < cur.loc.x && lowerRight.y <= cur.loc.y)
			red=true;
		if (upperRight.x<= cur.loc.x && upperRight.y > cur.loc.y ||
				lowerLeft.x <= cur.loc.x && lowerLeft.y > cur.loc.y ||
				upperLeft.x <= cur.loc.x && upperLeft.y > cur.loc.y ||
				lowerRight.x <= cur.loc.x && lowerRight.y > cur.loc.y)
			green=true;
		if (upperRight.x > cur.loc.x && upperRight.y >= cur.loc.y ||
				lowerLeft.x > cur.loc.x && lowerLeft.y >= cur.loc.y ||
				upperLeft.x > cur.loc.x && upperLeft.y >= cur.loc.y ||
				lowerRight.x > cur.loc.x && lowerRight.y >= cur.loc.y)
			blue=true;
		if (upperRight.x >= cur.loc.x && upperRight.y < cur.loc.y ||
				lowerLeft.x >= cur.loc.x && lowerLeft.y < cur.loc.y ||
				upperLeft.x >= cur.loc.x && upperLeft.y < cur.loc.y ||
				lowerRight.x >= cur.loc.x && lowerRight.y < cur.loc.y)
			yellow=true;

		if(red && cur.one!=null) {
			if (cur.one.loc.x <= upperRight.x && cur.one.loc.y <= upperRight.y &&
					cur.one.loc.x >= upperLeft.x && cur.one.loc.y <= upperLeft.y &&
					cur.one.loc.x >= lowerLeft.x && cur.one.loc.y >= lowerLeft.y &&
					cur.one.loc.x <= lowerRight.x && cur.one.loc.y >= lowerRight.y)
				pairList.insert(new Pair<>(cur.one.loc, cur.one.l));
			countForRange++;
		RecInrange(lowerLeft,upperRight,pairList,cur.one);
		}
		if(green && cur.tow!=null) {
			if (cur.tow.loc.x <= upperRight.x && cur.tow.loc.y <= upperRight.y &&
					cur.tow.loc.x >= upperLeft.x && cur.tow.loc.y <= upperLeft.y &&
					cur.tow.loc.x >= lowerLeft.x && cur.tow.loc.y >= lowerLeft.y &&
					cur.tow.loc.x <= lowerRight.x && cur.tow.loc.y >= lowerRight.y)
				pairList.insert(new Pair<>(cur.tow.loc, cur.tow.l));
			countForRange++;
			RecInrange(lowerLeft,upperRight,pairList,cur.tow);
		}
		if(blue && cur.three!=null) {
			if (cur.three.loc.x <= upperRight.x && cur.three.loc.y <= upperRight.y &&
					cur.three.loc.x >= upperLeft.x && cur.three.loc.y <= upperLeft.y &&
					cur.three.loc.x >= lowerLeft.x && cur.three.loc.y >= lowerLeft.y &&
					cur.three.loc.x <= lowerRight.x && cur.three.loc.y >= lowerRight.y)
				pairList.insert(new Pair<>(cur.three.loc, cur.three.l));
			countForRange++;
			RecInrange(lowerLeft,upperRight,pairList,cur.three);
		}
		if(yellow && cur.four!=null) {
			if (cur.four.loc.x <= upperRight.x && cur.four.loc.y <= upperRight.y &&
					cur.four.loc.x >= upperLeft.x && cur.four.loc.y <= upperLeft.y &&
					cur.four.loc.x >= lowerLeft.x && cur.four.loc.y >= lowerLeft.y &&
					cur.four.loc.x <= lowerRight.x && cur.four.loc.y >= lowerRight.y)
				pairList.insert(new Pair<>(cur.four.loc, cur.four.l));
			countForRange++;
			RecInrange(lowerLeft,upperRight,pairList,cur.four);
		}

	}

}

