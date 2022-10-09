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

import java.util.Random;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.test.ExerciseFragment;

/**
 * @author Joel Tikkanen
 */
public class TextRecognitionFragment extends ExerciseFragment {
    private static final Random random = new Random();
    private static final int totalTests = 2;

    private final TextRecognitionImages recognitionClass = new TextRecognitionImages();
    private int testPoints = 0;
    private int testAnswered = 0;

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

        ImageView imageViewWord = view.findViewById(R.id.imageViewWord);

        int selectedIndex = random.nextInt(4);
        imageViewWord.setImageResource(recognitionClass.getImageIDs()[selectedIndex]);

        EditText editTextWord = view.findViewById(R.id.editTextWord);

        FloatingActionButton floatingActionButtonNext = view.findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext.setOnClickListener(v -> {
            testAnswered++;
            if (testAnswered < totalTests) {
                recognitionClass.removeImageID(selectedIndex);
                recognitionClass.removeImageText(selectedIndex);
                int selectedIndex1 = random.nextInt(4 - testAnswered);
                imageViewWord.setImageResource(recognitionClass.getImageIDs()[selectedIndex1]);
                editTextWord.setText("");

            }
            if (editTextWord.getText().toString().equals(recognitionClass.getImageTexts()[selectedIndex])) {
                //correct answer
                testPoints++;
            }

            if (testAnswered == totalTests) {
                viewModel.getExerciseCompleted().postValue(null);

                // TODO: Count score
            }
        });
    }

    @Override
    public int getAvailablePoints() {
        return 0;
    }

    @Override
    public int getScoredPoints() {
        return 0;
    }
}
