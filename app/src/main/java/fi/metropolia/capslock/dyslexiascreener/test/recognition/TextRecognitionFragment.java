package fi.metropolia.capslock.dyslexiascreener.test.recognition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import fi.metropolia.capslock.dyslexiascreener.*;
import fi.metropolia.capslock.dyslexiascreener.test.ExerciseFragment;

/**
 * {@link ExerciseFragment} where the user has type the obscured word they see in the image.
 *
 * @author Joel Tikkanen
 */
public class TextRecognitionFragment extends ExerciseFragment {
    private static final String STATE_ITEM = "item";
    private static final String STATE_TEXT = "textWord";

    private ImageView imageView;
    private EditText editText;

    private RecognizableWord item;

    public TextRecognitionFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            item = new Gson().fromJson(savedInstanceState.getString(STATE_ITEM), RecognizableWord.class);
        }

        item = viewModel.getRecognizableWords().removeFirst();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text_recognition, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.imageViewWord);
        editText = view.findViewById(R.id.editTextWord);

        if (savedInstanceState != null) {
            String text = savedInstanceState.getString(STATE_TEXT);

            if (text != null)
                editText.setText(text);
        }

        imageView.setImageResource(item.getDrawableResId());

        FloatingActionButton floatingActionButtonNext = view.findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext.setOnClickListener(v -> {
            if (editText.getText().toString().isBlank()) {
                editText.setError(getResources().getString(R.string.answer_empty));
                return;
            }
            viewModel.getExerciseCompleted().postValue(null);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(STATE_ITEM, new Gson().toJson(item));
        outState.putString(STATE_TEXT, editText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public int getAvailablePoints() {
        return 1;
    }

    @Override
    public int getScoredPoints() {
        String expected = getResources().getString(item.getStringResId());
        if (editText.getText().toString().equalsIgnoreCase(expected))
            return 1;
        return -1;
    }
}
