package consoleCommander.interaction;

import consoleCommander.core.DrawingHelper;
import consoleCommander.core.Settings;
import consoleCommander.entities.Color;
import processing.core.PApplet;

/**
 * Class to define a check box
 * 
 * @author Lumme
 * @version 0.1
 * @since 19-08-2018
 */

public class CheckBox extends Interactable implements Lockable, OverColorable {
	private boolean selected = false;

	private Color line = Settings.DEFAULT_LINE_COLOR;
	private Color over = Settings.DEFAULT_OVER_COLOR;
	private Color back = Settings.DEFAULT_BACK_COLOR;
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

	public CheckBox(int x, int y, int w, int h, PApplet p) {
		super(x, y, w, h, p);
	}

	// DRAW

	/**
	 * Draws interactable when the super class variable active is true. It draws a
	 * rectangle using the x, y, w and h variables from the super object. It is uses
	 * the default colors from the Settings class for outline, mouse over, and
	 * background. If the variable selected is true, an 'x' will be drawn.
	 */

	@Override
	public void internalDraw() {
		if (isDrawn()) {
			if (!locked && !disabled) {
				getP().stroke(DrawingHelper.getPColor(getP(), line, getTransparency()));
				if (isMouseOver()) {
					getP().fill(DrawingHelper.getPColor(getP(), over, getTransparency()));
				} else {
					getP().fill(back.getR(), back.getG(), back.getB(), (float) (back.getA() * getTransparency()));
				}
			} else {
				getP().stroke(DrawingHelper.getPColor(getP(), darkLine, getTransparency()));
				getP().fill(DrawingHelper.getPColor(getP(), darkBack, getTransparency()));
			}

			getP().rect(getX(), getY(), getW(), getH());

			if (selected) {
				getP().line(getX() + (getW() / 8) + (getW() / 8) + 1, getY() + (getH() / 8) + (getH() / 8) + 1,
						getX() + (getW() / 8 * 6) + (getW() / 8) + 1, getY() + (getH() / 8 * 6) + (getH() / 8) + 1);

				getP().line(getX() + (getW() / 8 * 6) + (getW() / 8) + 1, getY() + (getH() / 8) + (getH() / 8) + 1,
						getX() + (getW() / 8) + (getW() / 8) + 1, getY() + (getH() / 8 * 6) + (getH() / 8) + 1);
			}
		}
	}

	// ON UPDATE

	/**
	 * Called when updating the text box. Override when constructing to use.
	 */

	public void onUpdate() {
	}

	// INTERACTIONS

	/**
	 * Sets the selected variable to its opposite if both the isMouseOver and
	 * isActive methods are true.
	 */

	@Override
	public void mouseClicked() {
		if (isMouseOver() && isDrawn() && !disabled && !locked) {
			selected = !selected;
			onUpdate();
		}
	}

	// GETTERS AND SETTERS

	/**
	 * @return boolean
	 */

	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected
	 */

	public void setSelected(boolean selected) {
		this.selected = selected;
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

	@Override
	public Color getDarkBack() {
		return darkBack;
	}

	@Override
	public void setDarkBack(Color darkBack) {
		this.darkBack = darkBack;
	}

	@Override
	public Color getDarkLine() {
		return darkLine;
	}

	@Override
	public void setDarkLine(Color darkLine) {
		this.darkLine = darkLine;
	}

	@Override
	public boolean isLocked() {
		return locked;
	}

	@Override
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
