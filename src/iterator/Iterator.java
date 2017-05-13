package iterator;

public interface Iterator {

//	public boolean hasNext();
//	public boolean hasPrevious();
	public boolean isEmpty();
//	public boolean contains(Object object);
	
	public int getSize();
//	public int getIndex(Object object);
	
	/**get an object and removes it from the list and switch all element to 
	 * fill its place
	 * @param index 
	 * @return
	 */
	public Object remove(int index);
	/**get an object and removes it from the list and switch all element to 
	 * fill its place
	 * @return
	 */
	public Object removeFirst();
	/**get an object and removes it from the list and switch all element to 
	 * fill its place
	 * @return
	 */
	public Object removeLast();
	
	public void addFirst(Object object);
	public void addLast(Object object);
	/**adds elements in specific place 
	 * all other elements are shifted backward
	 * @param object
	 * @param index
	 */
	public void add(Object object, int index);
}
