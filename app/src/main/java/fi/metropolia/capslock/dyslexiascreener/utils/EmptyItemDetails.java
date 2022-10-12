package fi.metropolia.capslock.dyslexiascreener.utils;

import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * Extension {@link ItemDetailsLookup.ItemDetails} for handling empty space;
 *
 * @author Peetu Saarinen
 */
public class EmptyItemDetails extends ItemDetailsLookup.ItemDetails<Long> {
    public static final long EMPTY_ITEM_KEY = Integer.MAX_VALUE;

    @Override
    public int getPosition() {
        return (int) EMPTY_ITEM_KEY;
    }

    @Nullable
    @Override
    public Long getSelectionKey() {
        return EMPTY_ITEM_KEY;
    }
}
