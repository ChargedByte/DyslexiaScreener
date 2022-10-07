package fi.metropolia.capslock.dyslexiascreener.test.recognition;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import fi.metropolia.capslock.dyslexiascreener.R;

/**
 * Activity for multiple text recognition tests.
 *
 * @author Joel Tikkanen
 */
public class TextRecognition extends AppCompatActivity {

    private static final Random random = new Random();
    private static final int totalTests = 2;
    ImageView textImage;
    EditText testInput;
    TextRecognitionImages recognitionClass = new TextRecognitionImages();
    private int testPoints = 0;
    private int testAnswered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recognition);


        textImage = findViewById(R.id.textImage);
        testInput = findViewById(R.id.editTextInput);

        int selectedIndex = random.nextInt(4);
        textImage.setImageResource(recognitionClass.getImageIDs()[selectedIndex]);


//        nextButton.setOnClickListener((View.OnClickListener) v -> {
//            testAnswered++;
//            if (testAnswered < totalTests) {
//                recognitionClass.removeImageID(selectedIndex);
//                recognitionClass.removeImageText(selectedIndex);
//                int selectedIndex1 = random.nextInt(4 - testAnswered);
//                textImage.setImageResource(recognitionClass.getImageIDs()[selectedIndex1]);
//                testInput.setText("");
//
//            }
//            if (testInput.getText().toString().equals(recognitionClass.getImageTexts()[selectedIndex])) {
//                //correct answer
//                testPoints++;
//            }
//
//
//            if (testAnswered == totalTests) {
//                //intent to next test item
//
//            }
//
//
//        });


    }


}
