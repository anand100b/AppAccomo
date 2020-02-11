package com.example.myappaccomo.ui.send;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Contact Us on");
        mText.setValue("imanand.bohara@gmail.com");
        mText.setValue("HelpLine: 0404776185");
    }

    public LiveData<String> getText() {
        return mText;
    }
}