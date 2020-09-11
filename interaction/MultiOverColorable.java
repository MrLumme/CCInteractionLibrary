package consoleCommander.interaction;

import consoleCommander.entities.Color;

public interface MultiOverColorable extends BaseColorable {

	/**
	 * @return Color
	 */

	public Color[] getOver();

	/**
	 * @param over
	 */

	public void setOver(Color over[]);
}
