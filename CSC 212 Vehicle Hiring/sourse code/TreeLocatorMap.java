
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {

	Map <K,Location> map;
	Locator <K> locator;


	public TreeLocatorMap()
	{
		map = new BST<> () ;
		locator = new TreeLocator <>();
	}


	@Override
	public Map<K, Location> getMap() {
		return map;
	}

	@Override
	public Locator<K> getLocator() {
		return locator;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair<Boolean, Integer> pair=map.insert(k,loc);
		if(!pair.first)
			return pair;
		int countComp=locator.add(k,loc);
		pair.second+=countComp;
	return pair;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {

		Pair<Boolean, Integer> pair=map.find(k);
		Pair<List<K>, Integer> pair1=locator.get(loc);

		if(!pair.first) {
			return new Pair<>(false, pair.second);
		}
		boolean flag=false;
		List<Pair<Location, List<K>>> locations=locator.getAll();
		locations.findFirst();
		while (!locations.last()){
			if(locations.retrieve().first.x==loc.x && locations.retrieve().first.y==loc.y) {
				flag = true;
			break;
			}
			locations.findNext();
		}
		if(locations.retrieve().first.x==loc.x && locations.retrieve().first.y==loc.y) {
			flag = true;
		}
		if(!flag) {//the key is not in the tree
			locator.remove(k,map.retrieve());
			locator.add(k, loc);
			map.update(loc);
		}
		else {//the key in the Tree
			locator.remove(k,map.retrieve());
			map.update(loc);
			pair1.first.insert(k);
		}


		return new Pair<>(true, pair.second);
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair<Boolean, Integer> pair=map.find(k);
		if (!pair.first){
			return new Pair<>(null, pair.second);
		}

		Location loc;
		loc=map.retrieve();

		return new Pair<>(loc, pair.second);
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		Pair<Boolean, Integer> pair=map.find(k);
		if (!pair.first) {
			pair.first=false;
			return pair;
		}

		locator.remove(k,map.retrieve());
		map.remove(k);
		return pair;
	}

	@Override
	public List<K> getAll() {
		return map.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		Pair <List<Pair <Location,List<K>> >, Integer> pair = locator.inRange(lowerLeft, upperRight);
		List<K> Keys = new LinkedList<>();

		if (pair.first.empty())
		return new Pair<>(Keys, pair.second);

			pair.first.findFirst();
			while (!pair.first.last())
			{
				if (!pair.first.retrieve().second.empty())
				{
					pair.first.retrieve().second.findFirst();
					while ( !pair.first.retrieve().second.last())
					{

						Keys.insert(pair.first.retrieve().second.retrieve());
						pair.first.retrieve().second.findNext();
					}
					Keys.insert(pair.first.retrieve().second.retrieve());
				}
				pair.first.findNext();
			}

			//add last node
			if (!pair.first.retrieve().second.empty())
			{
				pair.first.retrieve().second.findFirst();
				while (!pair.first.retrieve().second.last())
				{
					Keys.insert(pair.first.retrieve().second.retrieve());
					pair.first.retrieve().second.findNext();
				}
				Keys.insert(pair.first.retrieve().second.retrieve());
			}

		return new Pair<>(Keys, pair.second);
	}
}


