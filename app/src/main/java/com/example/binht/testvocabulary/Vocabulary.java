package com.example.binht.testvocabulary;

import java.io.Serializable;

/**
 * Created by binht on 5/13/2017.
 */

public class Vocabulary implements Serializable {

     private String word[];
     private String mean[];
     private String words[];
     private String phrase1[];
     private String phrase2[];
     private String type[];
     private String pronounce[];
     private boolean isOneWord;

    public boolean isOneWord() {
        return isOneWord;
    }

    public void setOneWord(boolean oneWord) {
        isOneWord = oneWord;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    private String youtube;

    public String[] getWord() {
        return word;
    }

    public void setWord(String[] word) {
        this.word = word;
    }

    public String[] getMean() {
        return mean;
    }

    public void setMean(String[] mean) {
        this.mean = mean;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public String[] getPhrase() {
        return phrase1;
    }

    public void setPhrase(String[] phrase) {
        this.phrase1 = phrase;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String[] getPronounce() {
        return pronounce;
    }

    public void setPronounce(String[] pronounce) {
        this.pronounce = pronounce;
    }
    public String[] getPhrase1() {
        return phrase1;
    }

    public void setPhrase1(String[] phrase1) {
        this.phrase1 = phrase1;
    }

    public String[] getPhrase2() {
        return phrase2;
    }

    public void setPhrase2(String[] phrase2) {
        this.phrase2 = phrase2;
    }

    public Vocabulary(String word[], String words[], String mean[], String phrase1[],
                      String phrase2[], String pronounce[], String type[], String youtube, boolean isOneWord){
        this.mean = mean;  //mảng chứa nghĩa của 2 từ, 1 là từ đầu, vị trí 2 là từ sô 2
        this.word = word;  //mảng chứa 2 từ, vtri 1 từ 1, vtri 2 từ 2
        this.words = words;   //mảng chứa 2 từ biến thể, là dạng số nhiều của 2 từ
        this.phrase1 = phrase1;    //mảng chứa 2 ví dụ của từ 1, ví dụ là câu.
        this.phrase2 = phrase2;         //mảng chứa 2 câu ví dụ của từ 2
        this.pronounce = pronounce;        // mảng chứa cách phát âm của 2 từ
        this.type = type;               //mảng chứa kiểu từ
        this.youtube = youtube;//mảng chứa link youtube
        this.isOneWord = isOneWord;
    }




//    public Vocabulary(String word, String words, String mean[], String phrase[], String type){
//        this.mean = mean;
//        this.word = word;
//        this.words = words;
//        this.phrase = phrase;
//        this.type = type;
//    }
}
