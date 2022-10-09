package fi.metropolia.capslock.dyslexiascreener.test.recognition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.test.ExerciseFragment;

/**
 * Fragment where the user has type the obscured word they see in the image.
 *
 * @author Joel Tikkanen
 */
public class TextRecognitionFragment extends ExerciseFragment {
    private static final String IS_TEXT = "text";

    private EditText editText;

    public TextRecognitionFragment() {
    }

    @NonNull
    public static TextRecognitionFragment newInstance() {
        TextRecognitionFragment fragment = new TextRecognitionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text_recognition, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = view.findViewById(R.id.editTextWord);

        if (savedInstanceState != null) {
            String text = savedInstanceState.getString(IS_TEXT);

            if (text != null)
                editText.setText(text);
        }

        FloatingActionButton floatingActionButtonNext = view.findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext.setOnClickListener(v -> viewModel.getExerciseCompleted().postValue(null));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String text = editText.getText().toString();

        if (!text.isBlank())
            outState.putString(IS_TEXT, text);
    }

    @Override
    public int getAvailablePoints() {
        return 1;
    }

    @Override
    public int getScoredPoints() {
        return 0;
    }
}
