package consoleCommander.core;

import consoleCommander.entities.Color;
import processing.core.PConstants;

/**
 * Static final class used to contain various hard coded settings.
 * 
 * @author Lumme
 * @version 0.1
 * @since 17-07-2019
 */

public final class Settings {
	public static final Color C_WHITE = new Color(255);
	public static final Color C_SEMI_WHITE = new Color(287);
	public static final Color C_LIGHT_GREY = new Color(191);
	public static final Color C_SEMI_LIGHT_GREY = new Color(159);
	public static final Color C_GREY = new Color(127);
	public static final Color C_SEMI_DARK_GREY = new Color(95);
	public static final Color C_DARK_GREY = new Color(63);
	public static final Color C_SEMI_BLACK = new Color(31);
	public static final Color C_BLACK = new Color(0);

	public static final Color C_BLUE = new Color(0, 0, 255);
	public static final Color C_DARK_BLUE = new Color(0, 0, 127);
	public static final Color C_GREEN = new Color(0, 255, 0);
	public static final Color C_DARK_GREEN = new Color(0, 127, 0);
	public static final Color C_RED = new Color(255, 0, 0);
	public static final Color C_DARK_RED = new Color(127, 0, 0);
	public static final Color C_MAGENTA = new Color(255, 0, 255);
	public static final Color C_DARK_MAGENTA = new Color(127, 0, 127);
	public static final Color C_CYAN = new Color(0, 255, 255);
	public static final Color C_DARK_CYAN = new Color(0, 127, 127);
	public static final Color C_YELLOW = new Color(255, 255, 0);
	public static final Color C_DARK_YELLOW = new Color(127, 127, 0);

	public static final Color C_TURQUOISE = new Color(0, 255, 127);
	public static final Color C_DARK_TURQUOISE = new Color(0, 127, 63);
	public static final Color C_PURPLE = new Color(127, 0, 255);
	public static final Color C_DARK_PURPLE = new Color(63, 0, 127);
	public static final Color C_ORANGE = new Color(255, 127, 0);
	public static final Color C_DARK_ORANGE = new Color(127, 63, 0);
	public static final Color C_LIME = new Color(127, 255, 0);
	public static final Color C_DARK_LIME = new Color(63, 127, 0);
	public static final Color C_NAVY = new Color(0, 127, 255);
	public static final Color C_DARK_NAVY = new Color(0, 63, 127);
	public static final Color C_PINK = new Color(255, 0, 127);
	public static final Color C_DARK_PINK = new Color(127, 0, 63);

	public static final Color DEFAULT_LINE_COLOR = new Color(223);
	public static final Color DEFAULT_OVER_COLOR = new Color(31);
	public static final Color DEFAULT_BACK_COLOR = C_BLACK;
	public static final Color DEFAULT_TXTC_COLOR = C_WHITE;
	public static final Color DEFAULT_FORE_COLOR = new Color(24);
	public static final Color DEFAULT_DARK_TXTC_COLOR = C_DARK_GREY;
	public static final Color DEFAULT_DARK_BACK_COLOR = C_BLACK;
	public static final Color DEFAULT_DARK_LINE_COLOR = C_GREY;
	public static final Color DEFAULT_DARK_FORE_COLOR = new Color(16);
	public static final Color DEFAULT_MARK_COLOR = C_RED;

	public static final int LABEL_VERT_ALIGN = PConstants.CENTER;
	public static final int LABEL_HOR_ALIGN = PConstants.CENTER;
	public static final float LABEL_LINE_SPACING = -1;

	public static final int TEXTBOX_VERT_ALIGN = PConstants.TOP;
	public static final int TEXTBOX_HOR_ALIGN = PConstants.LEFT;
	public static final float TEXTBOX_LINE_SPACING = -1;
	public static final int TEXTBOX_FRAME_SPACE = 8;

	public static final int BUTTON_MIN_SIZE = 24;
	public static final int BUTTON_LABEL_SIZE = 10;

	public static final int DEFAULT_TEXT_SIZE = 12;
}
