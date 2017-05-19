package com.example.binht.testvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

public class TestCompare extends FragmentActivity implements FragmentWordTest.TestWordInterface, FragmentPhraseTest.TestPhraseInterface,
FragmentWordSpeechTest.TestWordSpeechInterface, FragmentPhraseSpeech.TestPhraseSpeechInterface {
    Vocabulary vocabulary;
    boolean answer = false, wordTest = false, phraseTest = false, wordSpeechTest = false, phraseSpeechTest = false;
    int test[] = {0  //0.listen word
                , 0    //1. listen phrase1
                , 0    //2. listen phrase2
                , 0    //3. speech word
                , 0    //4. speech words
                , 0};  //5. speech phrase
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_compare);

        Intent i = getIntent();
        vocabulary = (Vocabulary)i.getSerializableExtra("Test");

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            Bundle args = new Bundle();
            args.putSerializable("WordTest", vocabulary);

            // Create a new Fragment to be placed in the activity layout
            FragmentWordTest firstFragment = new FragmentWordTest();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(args);

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }

    }
    public void changeFragment(View view){
        if(view == findViewById(R.id.nextBtn)){
            if(wordTest && test[0] == 1){
                test[0] = 0;
                FragmentPhraseTest newFragment = new FragmentPhraseTest();
                Bundle args = new Bundle();
                args.putSerializable("WordTest", vocabulary);

                newFragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
            else {
                if(phraseTest && test[1] == 1 && test[2] != -1 ){
                    test[1] = 0;
                    test[2] = -1;
                    FragmentPhraseTest newFragment = new FragmentPhraseTest();
                    Bundle args = new Bundle();
                    args.putSerializable("WordTest2", vocabulary);

                    newFragment.setArguments(args);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }
                else{
                    if(phraseTest && test[1] == 1 && test[2] == -1){
                        test[1] = 0;
                        test[2] = 0;
                        FragmentWordSpeechTest newFragment = new FragmentWordSpeechTest();
                        Bundle args = new Bundle();
                        args.putSerializable("WordTest", vocabulary);

                        newFragment.setArguments(args);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack so the user can navigate back
                        transaction.replace(R.id.fragment_container, newFragment);
                        transaction.addToBackStack(null);

                        // Commit the transaction
                        transaction.commit();
                    }
                    else {
                        if(wordSpeechTest && test[3] == 1 && test[4] != -1 && !vocabulary.isOneWord()){
                            test[3] = 0;
                            test[4] = -1;
                            FragmentWordSpeechTest newFragment = new FragmentWordSpeechTest();
                            Bundle args = new Bundle();
                            args.putSerializable("WordTest2", vocabulary);

                            newFragment.setArguments(args);
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                            // Replace whatever is in the fragment_container view with this fragment,
                            // and add the transaction to the back stack so the user can navigate back
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.addToBackStack(null);

                            // Commit the transaction
                            transaction.commit();
                        }
                        else {
                            if((wordSpeechTest && test[3] == 1 && test[4] == -1)||
                                    (wordSpeechTest && test[3] == 1 && test[4] != -1 && vocabulary.isOneWord())){
                                test[3] = 0;
                                test[4] = 0;
                                FragmentPhraseSpeech newFragment = new FragmentPhraseSpeech();
                                Bundle args = new Bundle();
                                args.putSerializable("WordTest", vocabulary);

                                newFragment.setArguments(args);
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                                // Replace whatever is in the fragment_container view with this fragment,
                                // and add the transaction to the back stack so the user can navigate back
                                transaction.replace(R.id.fragment_container, newFragment);
                                transaction.addToBackStack(null);

                                // Commit the transaction
                                transaction.commit();
                            }
                            else {
                                if(phraseSpeechTest && test[5] == 1){
                                    test[5] = 0;
                                    Toast.makeText(this,
                                            "You has completed the test!", Toast.LENGTH_LONG).show();
                                    CountDownTimer timer1 = new CountDownTimer(1000,1000) {
                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                        }

                                        @Override
                                        public void onFinish() {
                                            //setTrue(PICTURE_WORD,Topic);
                                        }
                                    }.start();

                                    Intent intent = new Intent(this, Compare.class);
                                    intent.putExtra("Vocabulary", vocabulary);
                                    startActivity(intent);

                                }
                                else {
                                    Toast.makeText(this,
                                            "You answer: "+answer, Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                    }

                }

            }

        }
    }

    @Override
    public boolean onReturnAnswer(boolean answer) {
        wordTest = answer;
        test[0] = 1;
        return wordTest;
    }

    @Override
    public boolean onReturnAnswerPhrase(boolean answer) {
        phraseTest = answer;
        test[1] = 1;
        return phraseTest;
    }

    @Override
    public boolean onReturnWordSpeechAnswer(boolean answer) {
        wordSpeechTest = answer;
        test[3] = 1;
        return wordSpeechTest;
    }

    @Override
    public boolean onReturnPhraseSpeechAnswer(boolean answer) {
        phraseSpeechTest = answer;
        test[5] = 1;
        return phraseSpeechTest;
    }
}