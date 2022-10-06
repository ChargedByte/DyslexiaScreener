package fi.metropolia.capslock.dyslexiascreener;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

/** Activity for multiple text recognition tests.
 *
 * @author Joel Tikkanen
 */
public class TextRecognition extends AppCompatActivity {

    private static final Random random = new Random();
    private static final int totalTests = 2;
    private int testPoints = 0;
    private int testAnswered = 0;

    ImageView textImage;
    EditText testInput;
    FloatingActionButton nextButton;


    TextRecognitionImages recognitionClass = new TextRecognitionImages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recognition);


        textImage = (ImageView) findViewById(R.id.textImage);
        testInput = (EditText) findViewById(R.id.editTextInput);
        nextButton = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        int selectedIndex = random.nextInt(4);
        textImage.setImageResource(recognitionClass.getImageIDs()[selectedIndex]);



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               testAnswered++;
                if (testAnswered < totalTests) {
                    recognitionClass.removeImageID(selectedIndex);
                    recognitionClass.removeImageText(selectedIndex);
                    int selectedIndex = random.nextInt(4-testAnswered);
                    textImage.setImageResource(recognitionClass.getImageIDs()[selectedIndex]);
                    testInput.setText("");

                }
                if (testInput.getText().toString().equals(recognitionClass.getImageTexts()[selectedIndex])){
                    //correct answer
                    testPoints++;
                }


                if (testAnswered == totalTests) {
                    //intent to next test item

                }


            }
        });









    }



}
