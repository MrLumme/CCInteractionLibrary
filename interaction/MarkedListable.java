package consoleCommander.interaction;

/**
 * Interface to define what methods, markable objects that can be used in lists,
 * must have. Extends the Listable interface.
 * 
 * @author Lumme
 * @version 0.1
 * @since 19-08-2018
 */

public interface MarkedListable extends Listable {

	/**
	 * @return boolean Whether it is marked or not.
	 */

	public boolean isMarked();

	/**
	 * @param marked
	 *            Sets marked.
	 */

	public void setMarked(boolean marked);

}
