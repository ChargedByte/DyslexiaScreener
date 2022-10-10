package fi.metropolia.capslock.dyslexiascreener.test.selection.selection;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

import fi.metropolia.capslock.dyslexiascreener.test.reverse.ReverseLettersFragment;
import fi.metropolia.capslock.dyslexiascreener.test.selection.SelectionFragment;

/**
 * {@link ItemDetailsLookup.ItemDetails} for the {@link SelectionFragment}'s selection functionality.
 *
 * @author Peetu Saarinen
 */
public class SelectionItemDetails extends ItemDetailsLookup.ItemDetails<Long> {
    private final int position;

    public SelectionItemDetails(int position) {
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
