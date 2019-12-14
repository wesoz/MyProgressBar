package com.example.myprogressbar;

import android.widget.ProgressBar;

public class CustomProgressBar {

    private ProgressBar progressBar;
    private int progressStatus;
    private CustomProgressBar nextProgressBar;

    public CustomProgressBar (ProgressBar progressBar, CustomProgressBar nextProgressBar) {
        this.progressBar = progressBar;
        this.nextProgressBar = nextProgressBar;
        this.progressStatus = 0;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void addProgressStatus(int value) {
        progressStatus += value;
    }

    public int getProgressStatus() {
        return progressStatus;
    }

    public CustomProgressBar getNextProgressBar() {
        return nextProgressBar;
    }

}
