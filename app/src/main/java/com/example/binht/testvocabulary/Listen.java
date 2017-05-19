package com.example.binht.testvocabulary;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Listen extends AppCompatActivity {
    TextView pronounce, meaning, result, englishWord;
    Button speech;
    ImageButton listen;
    TextToSpeech textToSpeech;
    int resultSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        speech = (Button) findViewById(R.id.speech_btn);
        result = (TextView) findViewById(R.id.result_textview);
        meaning = (TextView) findViewById(R.id.meaning_textview);
        englishWord = (TextView) findViewById(R.id.english_word_textview);
        pronounce = (TextView) findViewById(R.id.pronounce_textview);

        textToSpeech = new TextToSpeech(Listen.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    resultSpeech = textToSpeech.setLanguage(Locale.US);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "You device is not support feature!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent i1 = getIntent();
        final Vocabulary vocabulary1 = (Vocabulary) i1.getSerializableExtra("Word1");

        Intent i2 = getIntent();
        final Vocabulary vocabulary2 = (Vocabulary) i2.getSerializableExtra("Word2");
        if(vocabulary1 != null || vocabulary2 != null){
            if (vocabulary1 != null) {
                doVocabulary(vocabulary1, true);
            }
            if (vocabulary2 != null) {
                doVocabulary(vocabulary2, false);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Vocabulary not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void doVocabulary(Vocabulary vocabulary, boolean num) {
        if (num) {
            englishWord.setText(vocabulary.getWord()[0]);
            pronounce.setText(vocabulary.getPronounce()[0]);
            meaning.setText(vocabulary.getMean()[0]);
        } else {
            englishWord.setText(vocabulary.getWord()[1]);
            pronounce.setText(vocabulary.getPronounce()[1]);
            meaning.setText(vocabulary.getMean()[1]);
        }

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...!");
                    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                    startActivityForResult(intent, 2);
                } catch (ActivityNotFoundException e) {
                    Intent your_browser_intent = new Intent(Intent.ACTION_VIEW,

                            Uri.parse("https://market.android.com/details?id=APP_PACKAGE_NAME"));
                    startActivity(your_browser_intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    this.result.setText(result.get(0));

                    if (result.get(0).toString().toLowerCase().equals(englishWord.getText().toString().toLowerCase())) {
                        this.result.setTextColor(Color.parseColor("green"));
                    } else {
                        this.result.setTextColor(Color.parseColor("red"));
                    }
                }
                break;
            }
        }
    }

    public void doTextToVoice(View v){
        switch (v.getId()){
            case R.id.listenBtn:
                if(resultSpeech == TextToSpeech.LANG_NOT_SUPPORTED || resultSpeech == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(),
                            "You device is not support feature!", Toast.LENGTH_SHORT).show();
                }
                else {
                    textToSpeech.speak(englishWord.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            default:
                Toast.makeText(getApplicationContext(),
                        "Error! You device cant speak this word!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
