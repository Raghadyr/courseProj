public class VehicleHiringManager {

	LocatorMap <String> LocMap;
	public VehicleHiringManager() {
		LocMap = new TreeLocatorMap <>();
	}

	public LocatorMap<String> getLocatorMap() {
		return LocMap;
	}

	public void setLocatorMap(LocatorMap<String> locatorMap) {
		List<String> cars = locatorMap.getMap().getAll();//to get all the elements in the BST
		String id;
		Pair<Location,Integer> loc;
		if (cars.empty())
			return;

			cars.findFirst();
			while (!cars.last())
			{
				id = cars.retrieve();
				loc = locatorMap.getLoc(id);
				LocMap.add(id, loc.first);

				cars.findNext();
			}//last one
			id = cars.retrieve();
			loc = locatorMap.getLoc(id);
			LocMap.add(id, loc.first);

	}


	public boolean addVehicle(String id, Location loc) {
		Pair<Boolean, Integer> pair = LocMap.add(id,loc);
		return pair.first ;
	}


	public boolean moveVehicle(String id, Location loc) {
		Pair<Boolean, Integer> pair = LocMap.move(id, loc);
		return pair.first ;
	}


	public boolean removeVehicle(String id) {
		Pair<Boolean, Integer> pair = LocMap.remove(id);
		return pair.first ;
	}


	public Location getVehicleLoc(String id) {
		Pair<Location, Integer> pair = LocMap.getLoc(id);
		return pair.first;
	}

	public List<String> getVehiclesInRange(Location loc, int r) {
		Location lowerLeft=new Location (loc.x - r,loc.y - r);
		Location upperLeft=new Location (loc.x + r,loc.y + r);

		Pair <List<String>,Integer> vehicles = LocMap.getInRange(lowerLeft,upperLeft);
		List <String> VecRange = new LinkedList<>();

		if (!vehicles.first.empty())
		{
			vehicles.first.findFirst();
			while (! vehicles.first.last())
			{
				VecRange.insert(vehicles.first.retrieve());
				vehicles.first.findNext();
			}
			//last one
			VecRange.insert(vehicles.first.retrieve());
		}
		return VecRange;

	}
}

