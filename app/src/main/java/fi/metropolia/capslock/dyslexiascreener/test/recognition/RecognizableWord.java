package fi.metropolia.capslock.dyslexiascreener.test.recognition;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * Class for holding an image resource and the associated string resource.
 *
 * @author Joel Tikkanen
 */
public class RecognizableWord {
    @DrawableRes
    private final int drawableResId;
    @StringRes
    private final int stringResId;

    public RecognizableWord(@DrawableRes int drawableResId, @StringRes int stringResId) {
        this.drawableResId = drawableResId;
        this.stringResId = stringResId;
    }

    @DrawableRes
    public int getDrawableResId() {
        return drawableResId;
    }

    @StringRes
    public int getStringResId() {
        return stringResId;
    }
}
