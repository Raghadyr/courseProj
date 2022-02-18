public class TreeLocNode <T> {
    public Location loc;
    public TreeLocNode<T> one,tow,three,four;
    List<T> l=new LinkedList<>();

    /** Creates a new instance of BSTNode */
    public TreeLocNode(T val, Location k) {
        loc = k;
        l.insert(val);
        one = tow = three=four=null;
    }


}