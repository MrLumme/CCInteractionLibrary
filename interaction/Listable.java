package consoleCommander.interaction;

/**
 * Interface to define what methods, objects that can be used in lists, must
 * have. And forces these object to be sortable.
 * 
 * @author Lumme
 * @version 0.1
 * @since 12-08-2020
 */

public interface Listable extends Comparable<Listable> {

	/**
	 * @return String The name that should be displayed in the list.
	 */

	public String getDisplayName();

	/**
	 * @return String The actual name of the object.
	 */

	public String getName();

	/**
	 * @return int The id of the object.
	 */

	public int getId();

}
