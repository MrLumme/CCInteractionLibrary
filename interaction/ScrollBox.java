package consoleCommander.interaction;

import java.util.ArrayList;
import java.util.Collections;

import consoleCommander.core.DrawingHelper;
import consoleCommander.core.Settings;
import consoleCommander.entities.Color;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * Abstract class to define a scroll box
 * 
 * @author Lumme
 * @version 0.1
 * @since 24-08-2018
 */

public abstract class ScrollBox<T extends Listable> extends Interactable implements LockTextable, OverColorable {
	private ArrayList<T> options;
	private int amountShown;

	private final Button down, pDown, up, pUp;
	private int selected = -1;
	private int showIndex = 0;

	private boolean locked = false;
	private boolean disabled = false;

	private int textSize = Settings.DEFAULT_TEXT_SIZE;
	private PFont font;

	private Color line = Settings.DEFAULT_LINE_COLOR;
	private Color over = Settings.DEFAULT_OVER_COLOR;
	private Color back = Settings.DEFAULT_BACK_COLOR;
	private Color txtc = Settings.DEFAULT_TXTC_COLOR;
	private Color mark = Settings.DEFAULT_MARK_COLOR;
	private Color darkTxtc = Settings.DEFAULT_DARK_TXTC_COLOR;
	private Color darkBack = Settings.DEFAULT_DARK_BACK_COLOR;
	private Color darkLine = Settings.DEFAULT_DARK_LINE_COLOR;

	private boolean displayNameShown;

	/**
	 * Overloaded version of ScrollBox(x, y, w, h, options, amountShown,
	 * displayNameShown) where displayName defaults to true. Height should not be
	 * less than 80, and amountShown should not be less than 4.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param options
	 * @param amountShown
	 */

	public ScrollBox(int x, int y, int w, int h, PApplet p, PFont font, ArrayList<T> options, int amountShown) {
		this(x, y, w, h, p, font, options, amountShown, true);
	}

	/**
	 * Constructor. The inputs x, y, w and h are assigned to the super constructor.
	 * The inputs options, amountShown and displayNameShown is assigned to the
	 * variables. The variable p is set to the public variable instance in the Main
	 * class. Height should not be less than 80, and amountShown should not be less
	 * than 4.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param options
	 * @param amountShown
	 * @param displayNameShown
	 */

	public ScrollBox(int x, int y, int w, int h, PApplet p, PFont font, ArrayList<T> options, int amountShown,
			boolean displayNameShown) {
		super(x, y, w, h, p);
		this.options = options;
		this.amountShown = amountShown;
		this.displayNameShown = displayNameShown;
		this.font = font;

		down = new Button(x + w - 20, y + h - 20, 20, 20, p) {
			@Override
			public void action() {
			}
		};
		pDown = new Button(x + w - 20, y + h - 40, 20, 20, p) {
			@Override
			public void action() {
			}
		};
		up = new Button(x + w - 20, y, 20, 20, p) {
			@Override
			public void action() {
			}
		};
		pUp = new Button(x + w - 20, y + 20, 20, 20, p) {
			@Override
			public void action() {
			}
		};
	}

	// DRAW

	/**
	 * Draws interactable when the super class variable active is true. It draws a
	 * rectangle using the x, y, w and h variables from the super object. It is uses
	 * the default colors from the Settings class for outline, mouse over, and
	 * background. It draws some of the strings in the options variable with
	 * mainFont from the Main class as the font, textSize as the text size and the
	 * default text color from the Settings class as the color. The text is centered
	 * both vertically and horizontally.
	 */

	@Override
	public void internalDraw() {
		try {
			if (isDrawn()) {
				if (!locked && !disabled) {
					getP().stroke(DrawingHelper.getPColor(getP(), line, getTransparency()));
					getP().fill(back.getR(), back.getG(), back.getB(), (float) (back.getA() * getTransparency()));
				} else {
					getP().stroke(DrawingHelper.getPColor(getP(), darkLine, getTransparency()));
					getP().fill(DrawingHelper.getPColor(getP(), darkBack, getTransparency()));
				}

				getP().rect(getX(), getY(), getW(), getH());

				getP().textFont(font);
				getP().textAlign(PConstants.LEFT, PConstants.CENTER);
				getP().textSize(textSize);

				float hor = getH() / (amountShown);
				float off = hor / 2;

				if (options.size() < amountShown) {
					for (int i = 0; i < options.size(); i++) {
						if (i == selected && !locked && !disabled) {
							getP().fill(DrawingHelper.getPColor(getP(), over, getTransparency()));
							getP().rect(getX(), getY() + (hor * i), getW(), hor);
						}

						if (options.get(i) instanceof MarkedListable) {
							if (((MarkedListable) options.get(i)).isMarked()) {
								getP().fill(DrawingHelper.getPColor(getP(), mark, getTransparency()));
							} else {
								if (!locked && !disabled) {
									getP().fill(DrawingHelper.getPColor(getP(), txtc, getTransparency()));
								} else {
									getP().fill(DrawingHelper.getPColor(getP(), darkTxtc, getTransparency()));
								}
							}
						} else {
							if (!locked && !disabled) {
								getP().fill(DrawingHelper.getPColor(getP(), txtc, getTransparency()));
							} else {
								getP().fill(DrawingHelper.getPColor(getP(), darkTxtc, getTransparency()));
							}
						}

						if (displayNameShown) {
							getP().text(options.get(i).getDisplayName(), getX() + 10,
									(int) (getY() + (hor * i) + off + 1));
						} else {
							getP().text(options.get(i).getName(), getX() + 10, (int) (getY() + (hor * i) + off + 1));
						}
					}
				} else {
					for (int i = showIndex; (i - showIndex) < amountShown; i++) {
						if (i == selected && !locked) {
							getP().fill(DrawingHelper.getPColor(getP(), over, getTransparency()));
							getP().rect(getX(), getY() + (hor * (i - showIndex)), getW(), hor);
						}

						if (options.get(i) instanceof MarkedListable) {
							if (((MarkedListable) options.get(i)).isMarked()) {
								getP().fill(DrawingHelper.getPColor(getP(), mark, getTransparency()));
							} else {
								if (!locked && !disabled) {
									getP().fill(DrawingHelper.getPColor(getP(), txtc, getTransparency()));
								} else {
									getP().fill(DrawingHelper.getPColor(getP(), darkTxtc, getTransparency()));
								}
							}
						} else {
							if (!locked && !disabled) {
								getP().fill(DrawingHelper.getPColor(getP(), txtc, getTransparency()));
							} else {
								getP().fill(DrawingHelper.getPColor(getP(), darkTxtc, getTransparency()));
							}
						}

						if (displayNameShown) {
							getP().text(options.get(i).getDisplayName(), getX() + 10,
									(int) (getY() + (hor * (i - showIndex) + off + 1)));
						} else {
							getP().text(options.get(i).getName(), getX() + 10,
									(int) (getY() + (hor * (i - showIndex) + off + 1)));
						}
					}
				}

				if (options.size() > amountShown) {
					up.draw();
					down.draw();

					getP().line(up.getX() + up.getW() / 6, up.getY() + up.getH() / 4 * 3, up.getX() + up.getW() / 2,
							up.getY() + up.getH() / 4);
					getP().line(up.getX() + up.getW() / 2, up.getY() + up.getH() / 4,
							up.getX() + (up.getW() / 6 * 5) + 2, up.getY() + up.getH() / 4 * 3);

					getP().line(down.getX() + down.getW() / 2, down.getY() + down.getH() / 4 * 3,
							down.getX() + down.getW() / 6, down.getY() + down.getH() / 4);
					getP().line(down.getX() + down.getW() / 6 * 5 + 2, down.getY() + down.getH() / 4,
							down.getX() + down.getW() / 2, down.getY() + down.getH() / 4 * 3);

					if (down.getY() - (up.getY() + up.getH()) >= pUp.getH() + pDown.getH()) {
						pUp.draw();
						pDown.draw();

						getP().line(pUp.getX() + pUp.getW() / 6, pUp.getY() + pUp.getH() / 4 * 3 - 2,
								pUp.getX() + pUp.getW() / 2, pUp.getY() + pUp.getH() / 4 - 2);
						getP().line(pUp.getX() + pUp.getW() / 2, pUp.getY() + pUp.getH() / 4 - 2,
								pUp.getX() + (pUp.getW() / 6 * 5) + 2, pUp.getY() + pUp.getH() / 4 * 3 - 2);
						getP().line(pUp.getX() + pUp.getW() / 6, pUp.getY() + pUp.getH() / 4 * 3 + 2,
								pUp.getX() + pUp.getW() / 2, pUp.getY() + pUp.getH() / 4 + 2);
						getP().line(pUp.getX() + pUp.getW() / 2, pUp.getY() + pUp.getH() / 4 + 2,
								pUp.getX() + (pUp.getW() / 6 * 5) + 2, pUp.getY() + pUp.getH() / 4 * 3 + 2);

						getP().line(pDown.getX() + pDown.getW() / 2, pDown.getY() + pDown.getH() / 4 * 3 - 2,
								pDown.getX() + pDown.getW() / 6, pDown.getY() + pDown.getH() / 4 - 2);
						getP().line(pDown.getX() + pDown.getW() / 6 * 5 + 2, pDown.getY() + pDown.getH() / 4 - 2,
								pDown.getX() + pDown.getW() / 2, pDown.getY() + pDown.getH() / 4 * 3 - 2);
						getP().line(pDown.getX() + pDown.getW() / 2, pDown.getY() + pDown.getH() / 4 * 3 + 2,
								pDown.getX() + pDown.getW() / 6, pDown.getY() + pDown.getH() / 4 + 2);
						getP().line(pDown.getX() + pDown.getW() / 6 * 5 + 2, pDown.getY() + pDown.getH() / 4 + 2,
								pDown.getX() + pDown.getW() / 2, pDown.getY() + pDown.getH() / 4 * 3 + 2);
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// Do nothing
		}
	}

	/**
	 * Selects the element which is under the mouse or scrolls, both only if mouse
	 * over scrollbox and is active
	 */

	@Override
	public void mouseClicked() {
		if (isMouseOver() && isDrawn() && !locked && !disabled) {

			if (up.isMouseOver()) {
				if (showIndex > 0) {
					showIndex--;
				}
			} else if (pUp.isMouseOver()) {
				if (showIndex > amountShown) {
					showIndex -= amountShown;
				} else {
					showIndex = 0;
				}
			} else if (pDown.isMouseOver()) {
				if ((showIndex + amountShown) < (options.size() - amountShown)) {
					showIndex += amountShown;
				} else {
					showIndex = (options.size() - amountShown);
				}
			} else if (down.isMouseOver()) {
				if ((showIndex + amountShown) < options.size()) {
					showIndex++;
				}
			} else {
				float hor = getH() / (amountShown);

				if (options.size() < amountShown) {
					for (int i = 0; i < options.size(); i++) {
						if (getP().mouseY > (getY() + (hor * i)) && getP().mouseY < (getY() + (hor * (i + 1)))) {
							selected = i;
						}
					}
				} else {
					for (int i = showIndex; (i - showIndex) < amountShown; i++) {
						if (getP().mouseY > (getY() + (hor * (i - showIndex)))
								&& getP().mouseY < (getY() + (hor * ((i - showIndex) + 1)))) {
							selected = i;
						}
					}
				}
			}

			if (selected != -1) {
				update();
			}
		}
	}

	/**
	 * Called when an element is selected
	 */

	public abstract void update();

	/**
	 * Scrolls one element up
	 */

	public void scrollUp() {
		if (showIndex > 0 && isDrawn()) {
			showIndex -= 1;
		}
	}

	/**
	 * Scrolls one element down
	 */

	public void scrollDown() {
		if ((showIndex + amountShown) < (options.size()) && isDrawn()) {
			showIndex += 1;
		}
	}

	// SORTING

	/**
	 * Sorts scroll box list
	 */

	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(options);
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the selected element as it's listable id or -1 if nothing is
	 * selected.
	 * 
	 * @return int
	 */

	public int getSelectedAsIndex() {
		T o = getSelectedAsObject();
		return o == null ? null : options.get(selected).getId();
	}

	/**
	 * Returns the selected element as it's listable name or null if nothing is
	 * selected.
	 * 
	 * @return String
	 */

	public String getSelectedAsName() {
		T o = getSelectedAsObject();
		return o == null ? null : options.get(selected).getName();
	}

	/**
	 * Returns the selected element as it's listable display name or null if nothing
	 * is selected.
	 * 
	 * @return String
	 */

	public String getSelectedAsDisplayName() {
		T o = getSelectedAsObject();
		return o == null ? null : options.get(selected).getDisplayName();
	}

	/**
	 * Returns the selected element as an object, or null if no item is selected.
	 * 
	 * @return Object
	 */

	public T getSelectedAsObject() {
		if (selected == -1 || selected < 0 || selected >= options.size()) {
			selected = -1;
			return null;
		} else {
			return options.get(selected);
		}
	}

	/**
	 * Sets the selected entry if the input is less than the total list amount
	 * 
	 * @param selected
	 */

	public void setSelected(int selected) {
		if (selected < options.size()) {
			this.selected = selected;
		}
	}

	/**
	 * Sets the selected entry to an entry containing the input if any exists
	 * 
	 * @param name
	 */

	public void setSelected(String name) {
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getName().contains(name)) {
				selected = i;
			}
		}
	}

	/**
	 * Resets the selected entry to -1
	 * 
	 * @param options
	 */

	public void setOptions(ArrayList<T> options) {
		this.options = options;
		selected = -1;
	}

	/**
	 * @return ArrayList<? extends Listable>
	 */

	public ArrayList<T> getOptions() {
		return options;
	}

	/**
	 * @return boolean
	 */

	public boolean isDisplayNameShown() {
		return displayNameShown;
	}

	/**
	 * @param displayName
	 */

	public void setDisplayNameShown(boolean displayName) {
		this.displayNameShown = displayName;
	}

	@Override
	public void setP(PApplet p) {
		super.setP(p);
		pUp.setP(p);
		pDown.setP(p);
		up.setP(p);
		down.setP(p);
	}

	/**
	 * @return boolean
	 */

	@Override
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 */

	@Override
	public void setLocked(boolean locked) {
		this.locked = locked;
		pUp.setLocked(locked);
		pDown.setLocked(locked);
		up.setLocked(locked);
		down.setLocked(locked);
	}

	/**
	 * @return boolean
	 */

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 */

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		pUp.setDisabled(disabled);
		pDown.setDisabled(disabled);
		up.setDisabled(disabled);
		down.setDisabled(disabled);
	}

	@Override
	public String getText() {
		return null;
	}

	@Override
	public void setText(String text) {
	}

	@Override
	public int getTextSize() {
		return textSize;
	}

	@Override
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	@Override
	public PFont getFont() {
		return font;
	}

	@Override
	public void setFont(PFont font) {
		this.font = font;
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
		up.setLine(line);
		down.setLine(line);
		pUp.setLine(line);
		pDown.setLine(line);
	}

	@Override
	public Color getOver() {
		return over;
	}

	@Override
	public void setOver(Color over) {
		this.over = over;
		up.setOver(over);
		down.setOver(over);
		pUp.setOver(over);
		pDown.setOver(over);
	}

	@Override
	public Color getBack() {
		return back;
	}

	@Override
	public void setBack(Color back) {
		this.back = back;
		up.setBack(back);
		down.setBack(back);
		pUp.setBack(back);
		pDown.setBack(back);
	}

	@Override
	public Color getTextColor() {
		return txtc;
	}

	@Override
	public void setTextColor(Color txtc) {
		this.txtc = txtc;
		up.setTextColor(txtc);
		down.setTextColor(txtc);
		pUp.setTextColor(txtc);
		pDown.setTextColor(txtc);
	}

	@Override
	public Color getDarkTextColor() {
		return darkTxtc;
	}

	@Override
	public void setDarkTextColor(Color darkTxtc) {
		this.darkTxtc = darkTxtc;
		up.setDarkTextColor(darkTxtc);
		down.setDarkTextColor(darkTxtc);
		pUp.setDarkTextColor(darkTxtc);
		pDown.setDarkTextColor(darkTxtc);
	}

	@Override
	public Color getDarkBack() {
		return darkBack;
	}

	@Override
	public void setDarkBack(Color darkBack) {
		this.darkBack = darkBack;
		up.setDarkBack(darkBack);
		down.setDarkBack(darkBack);
		pUp.setDarkBack(darkBack);
		pDown.setDarkBack(darkBack);
	}

	@Override
	public Color getDarkLine() {
		return darkLine;
	}

	@Override
	public void setDarkLine(Color darkLine) {
		this.darkLine = darkLine;
		up.setDarkLine(darkLine);
		down.setDarkLine(darkLine);
		pUp.setDarkLine(darkLine);
		pDown.setDarkLine(darkLine);
	}

	public Color getMark() {
		return mark;
	}

	public void setMark(Color mark) {
		this.mark = mark;
	}

	public int getAmountShown() {
		return amountShown;
	}

	public void setAmountShown(int amountShown) {
		this.amountShown = amountShown;
	}

	/**
	 * Overrides method in Interactable class, in order to apply the transparency to
	 * ScrollBox buttons as well as it self.
	 * 
	 * @param transparency
	 */

	@Override
	public void setTransparency(double transparency) {
		super.setTransparency(transparency);
		up.setTransparency(transparency);
		pUp.setTransparency(transparency);
		down.setTransparency(transparency);
		pDown.setTransparency(transparency);
	}

	@Override
	public void setX(int x) {
		up.setX((up.getX() - getX()) + x);
		pUp.setX((pUp.getX() - getX()) + x);
		down.setX((down.getX() - getX()) + x);
		pDown.setX((pDown.getX() - getX()) + x);
		super.setX(x);
	}

	@Override
	public void setY(int y) {
		up.setY((up.getY() - getY()) + y);
		pUp.setY((pUp.getY() - getY()) + y);
		down.setY((down.getY() - getY()) + y);
		pDown.setY((pDown.getY() - getY()) + y);
		super.setY(y);
	}

	@Override
	public void setW(int w) {
		up.setX((up.getX() - getW()) + w);
		pUp.setX((pUp.getX() - getW()) + w);
		down.setX((down.getX() - getW()) + w);
		pDown.setX((pDown.getX() - getW()) + w);
		super.setW(w);
	}

	@Override
	public void setH(int h) {
		up.setY((up.getY() - getH()) + h);
		pUp.setY((pUp.getY() - getH()) + h);
		down.setY((down.getY() - getH()) + h);
		pDown.setY((pDown.getY() - getH()) + h);
		super.setH(h);
	}
}
