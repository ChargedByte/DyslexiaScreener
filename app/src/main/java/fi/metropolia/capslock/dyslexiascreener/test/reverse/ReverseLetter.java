package fi.metropolia.capslock.dyslexiascreener.test.reverse;

import androidx.annotation.DrawableRes;

/**
 * Class for holding an image resource of a letter and whether that image is reversed.
 *
 * @author Joonas Jouttijärvi
 */
public class ReverseLetter {
    @DrawableRes
    private final int resId;
    private final boolean isReversed;

    public ReverseLetter(@DrawableRes int resId, boolean isReversed) {
        this.resId = resId;
        this.isReversed = isReversed;
    }

    @DrawableRes
    public int getResId() {
        return resId;
    }

    public boolean isReversed() {
        return isReversed;
    }
}
