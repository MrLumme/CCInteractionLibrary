package consoleCommander.interaction;

import consoleCommander.entities.Color;

public interface OverColorable extends BaseColorable {

	/**
	 * @return Color
	 */

	public Color getOver();

	/**
	 * @param over
	 */

	public void setOver(Color over);
}
