package consoleCommander.interaction;

import consoleCommander.entities.Color;
import processing.core.PFont;

public interface Textable {

	/**
	 * @return Color
	 */

	public Color getTextColor();

	/**
	 * @param txtc
	 */

	public void setTextColor(Color txtc);

	/**
	 * @return String
	 */

	public String getText();

	/**
	 * @param text
	 */

	public void setText(String text);

	/**
	 * @return int
	 */

	public int getTextSize();

	/**
	 * @param textSize
	 */

	public void setTextSize(int textSize);

	/**
	 * @return PFont
	 */

	public PFont getFont();

	/**
	 * @param font
	 */

	public void setFont(PFont font);
}
