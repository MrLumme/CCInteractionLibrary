package consoleCommander.interaction;

import consoleCommander.entities.Color;

/**
 * Interface to define characteristics of interactables that use the dark view
 * system.
 * 
 * @author Lumme
 * @version 0.1
 * @since 07-09-2018
 */

public interface Lockable {

	/**
	 * @return boolean
	 */

	public boolean isLocked();

	/**
	 * @param lock
	 */

	public void setLocked(boolean lock);

	/**
	 * @return boolean
	 */

	public boolean isDisabled();

	/**
	 * @param disable
	 */

	public void setDisabled(boolean disable);

	/**
	 * @return Color
	 */

	public Color getDarkBack();

	/**
	 * @param darkBack
	 */

	public void setDarkBack(Color darkBack);

	/**
	 * @return Color
	 */

	public Color getDarkLine();

	/**
	 * @param darkLine
	 */

	public void setDarkLine(Color darkLine);
}
