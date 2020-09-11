package consoleCommander.interaction;

import consoleCommander.core.DrawingHelper;
import consoleCommander.core.Settings;
import consoleCommander.entities.Color;
import consoleCommander.soundHandling.SoundKeeper;
import consoleCommander.soundHandling.types.SfxType;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * Abstract class to define buttons.
 * 
 * @author Lumme
 * @version 0.1
 * @since 20-08-2018
 */

public abstract class Button extends Interactable implements LockTextable, OverColorable {
	private String text = "";
	private int textSize = Settings.DEFAULT_TEXT_SIZE;
	private PFont font;

	private Color line = Settings.DEFAULT_LINE_COLOR;
	private Color over = Settings.DEFAULT_OVER_COLOR;
	private Color back = Settings.DEFAULT_BACK_COLOR;
	private Color txtc = Settings.DEFAULT_TXTC_COLOR;
	private Color darkTxtc = Settings.DEFAULT_DARK_TXTC_COLOR;
	private Color darkBack = Settings.DEFAULT_DARK_BACK_COLOR;
	private Color darkLine = Settings.DEFAULT_DARK_LINE_COLOR;

	private boolean locked = false;
	private boolean disabled = false;

	/**
	 * Constructor. The inputs x, y, w and h are assigned to the super constructor.
	 * The variable p is set to the public variable instance in the Main class.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */

	public Button(int x, int y, int w, int h, PApplet p) {
		super(x, y, w, h, p);
	}

	/**
	 * Constructor. The inputs x, y, w and h are assigned to the super constructor.
	 * The input label is assigned to the variable. The variable p is set to the
	 * public variable instance in the Main class.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param label
	 */

	public Button(int x, int y, int w, int h, PApplet p, String label, PFont font) {
		super(x, y, w, h, p);
		this.text = label;
		this.font = font;
	}

	// DRAW

	/**
	 * Draws interactable when the super class variable active is true. It draws a
	 * rectangle using the x, y, w and h variables from the super object. It is uses
	 * the default colors from the Settings class for outline, mouse over, and
	 * background. It draws the text in the label variable with mainFont from the
	 * Main class as the font, textSize as the text size and the default text color
	 * from the Settings class as the color. The text is centered both vertically
	 * and horizontally.
	 */

	@Override
	public void internalDraw() {
		if (isDrawn()) {
			if (!locked && !disabled) {
				getP().stroke(line);
				if (isMouseOver()) {
					getP().fill(over);
				} else {
					getP().fill(back.getR(), back.getG(), back.getB(), (float) (back.getA() * getTransparency()));
				}
			} else {
				getP().stroke(darkLine);
				getP().fill(darkBack);
			}

			getP().rect(getX(), getY(), getW(), getH());

			if (text != null && !text.equals("")) {
				getP().textFont(font);
				getP().textSize(textSize);
				getP().textAlign(PConstants.CENTER, PConstants.CENTER);
				if (!locked && !disabled) {
					getP().fill(txtc);
				} else {
					getP().fill(darkTxtc);
				}
				getP().text(text, getX() + getW() / 2 + 2, getY() + getH() / 2 + 1);
			}
		}
	}

	// INTERACTIONS

	/**
	 * Method for behaviour when mouse button is clicked.
	 */

	@Override
	public void mouseClicked() {
		if (isMouseOver() && isDrawn() && !locked && !disabled) {
			SoundKeeper.playSFX(SfxType.BUTTON, 0.4);
			action();
		}
	}

	/**
	 * Abstract method for behaviour. Is being run when mouse button is clicked.
	 */

	public abstract void action();

	// GETTERS AND SETTERS

	/**
	 * @return String
	 */

	@Override
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 */

	@Override
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return boolean
	 */

	@Override
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param lock
	 */

	@Override
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return boolean
	 */

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param locked
	 */

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return int
	 */

	@Override
	public int getTextSize() {
		return textSize;
	}

	/**
	 * @param textSize
	 */

	@Override
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getLine() {
		return line;
	}

	/**
	 * @param line
	 */

	@Override
	public void setLine(Color line) {
		this.line = line;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getOver() {
		return over;
	}

	/**
	 * @param over
	 */

	@Override
	public void setOver(Color over) {
		this.over = over;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getBack() {
		return back;
	}

	/**
	 * @param back
	 */

	@Override
	public void setBack(Color back) {
		this.back = back;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getTextColor() {
		return txtc;
	}

	/**
	 * @param txtc
	 */

	@Override
	public void setTextColor(Color txtc) {
		this.txtc = txtc;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getDarkTextColor() {
		return darkTxtc;
	}

	/**
	 * @param lockTxtc
	 */

	@Override
	public void setDarkTextColor(Color darkTxtc) {
		this.darkTxtc = darkTxtc;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getDarkBack() {
		return darkBack;
	}

	/**
	 * @param lockBack
	 */

	@Override
	public void setDarkBack(Color darkBack) {
		this.darkBack = darkBack;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getDarkLine() {
		return darkLine;
	}

	/**
	 * @param lockLine
	 */

	@Override
	public void setDarkLine(Color darkLine) {
		this.darkLine = darkLine;
	}

	/**
	 * @param font
	 */

	@Override
	public void setFont(PFont font) {
		this.font = font;
	}

	/**
	 * @return PFont
	 */

	@Override
	public PFont getFont() {
		return font;
	}
}
