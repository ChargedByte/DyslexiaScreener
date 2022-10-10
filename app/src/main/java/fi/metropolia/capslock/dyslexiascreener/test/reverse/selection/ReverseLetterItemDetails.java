package fi.metropolia.capslock.dyslexiascreener.test.reverse.selection;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

import fi.metropolia.capslock.dyslexiascreener.test.reverse.ReverseLettersFragment;

/**
 * {@link ItemDetailsLookup.ItemDetails} for the {@link ReverseLettersFragment}'s selection functionality.
 *
 * @author Peetu Saarinen
 */
public class ReverseLetterItemDetails extends ItemDetailsLookup.ItemDetails<Long> {
    private final int position;

    public ReverseLetterItemDetails(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Nullable
    @Override
    public Long getSelectionKey() {
        return (long) position;
    }

    @Override
    public boolean inSelectionHotspot(@NonNull MotionEvent e) {
        return true;
    }
}
