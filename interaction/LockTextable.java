package consoleCommander.interaction;

import consoleCommander.entities.Color;

public interface LockTextable extends Textable, Lockable {

	public Color getDarkTextColor();

	public void setDarkTextColor(Color darkTxtc);
}
