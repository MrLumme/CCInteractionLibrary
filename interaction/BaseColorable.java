package consoleCommander.interaction;

import consoleCommander.entities.Color;

public interface BaseColorable {

	/**
	 * @return Color
	 */

	public Color getLine();

	/**
	 * @param line
	 */

	public void setLine(Color line);

	/**
	 * @return Color
	 */

	public Color getBack();

	/**
	 * @param back
	 */

	public void setBack(Color back);
}
