package com.example.binht.testvocabulary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class FragmentPhraseSpeech extends Fragment {
    Vocabulary vocabulary;
    TextView word1, word2;
    TextView resultText1, resultText2;
    ImageButton speechBtn1, speechBtn2;
    int answer = 0;
    String firstPhrase, secondPhrase;
    TextToSpeech textToSpeech;
    int resultSpeech;
    public boolean result = false;
    FragmentPhraseSpeech.TestPhraseSpeechInterface mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_word_speech_test, container, false);

        word1 = (TextView) rootView.findViewById(R.id.test_compare_word1);
        word2 = (TextView) rootView.findViewById(R.id.test_compare_word2);
        speechBtn1 = (ImageButton)rootView.findViewById(R.id.speech_btn);
        speechBtn2 = (ImageButton)rootView.findViewById(R.id.speech_btn2);
        resultText1 =(TextView)rootView.findViewById(R.id.result_textview);
        resultText2 =(TextView)rootView.findViewById(R.id.result_textview2);

        Bundle arguments = getArguments();
        if (arguments != null) {
            vocabulary = (Vocabulary)arguments.getSerializable("WordTest");
            if(vocabulary == null){
                vocabulary= (Vocabulary)arguments.getSerializable("WordTest2");
            }
            else {
                //Toast.makeText(getContext(),"Phrase 1",Toast.LENGTH_SHORT).show();
                word1.setText(vocabulary.getPhrase1()[2]);
                word2.setText(vocabulary.getPhrase2()[2]);
                firstPhrase = vocabulary.getPhrase1()[2];
                secondPhrase = vocabulary.getPhrase2()[2];
            }
        }
        else {
            return rootView;
        }

        speechBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...!");
                    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                    startActivityForResult(intent, 1);
                }
                catch(ActivityNotFoundException e)
                {
                    Intent your_browser_intent = new Intent(Intent.ACTION_VIEW,

                            Uri.parse("https://market.android.com/details?id=APP_PACKAGE_NAME"));
                    startActivity(your_browser_intent);

                }
            }
        });
        speechBtn2.setOnClickListener(new View.OnClickListener() {
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
                }
                catch(ActivityNotFoundException e)
                {
                    Intent your_browser_intent = new Intent(Intent.ACTION_VIEW,

                            Uri.parse("https://market.android.com/details?id=APP_PACKAGE_NAME"));
                    startActivity(your_browser_intent);

                }
            }
        });

        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    this.resultText1.setText(result.get(0));

                    if((firstPhrase.toLowerCase()).equals(result.get(0).toLowerCase())){
                        this.resultText1.setTextColor(Color.parseColor("green"));
                        answer++;
                        if(answer >= 2) mCallback.onReturnPhraseSpeechAnswer(true);
                    }
                    else {
                        this.resultText1.setTextColor(Color.parseColor("red"));
                        answer++;
                        if(answer >= 2) mCallback.onReturnPhraseSpeechAnswer(false);
                    }
                }
                break;
            }
            case 2: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    this.resultText2.setText(result.get(0));

                    if((secondPhrase.toLowerCase()).equals(result.get(0).toLowerCase())){
                        this.resultText2.setTextColor(Color.parseColor("green"));
                        answer++;
                        if(answer >= 2) mCallback.onReturnPhraseSpeechAnswer(true);
                    }
                    else {
                        this.resultText2.setTextColor(Color.parseColor("red"));
                        answer++;
                        if(answer >= 2) mCallback.onReturnPhraseSpeechAnswer(false);

                    }
                }
                break;
            }
            default:
                Toast.makeText(getContext(),"Error occur!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
    // Container Activity must implement this interface
    public interface TestPhraseSpeechInterface {
        public boolean onReturnPhraseSpeechAnswer(boolean answer);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (FragmentPhraseSpeech.TestPhraseSpeechInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TestWordInterface");
        }
    }

}