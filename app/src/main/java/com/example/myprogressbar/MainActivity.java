package com.example.myprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the widgets reference from XML layout
        final Button btn = findViewById(R.id.btn);

        final CustomProgressBar pbLeft = new CustomProgressBar((ProgressBar) findViewById(R.id.pbLeft), null);
        final CustomProgressBar pbBottom = new CustomProgressBar((ProgressBar) findViewById(R.id.pbBottom), pbLeft);
        final CustomProgressBar pbRight = new CustomProgressBar((ProgressBar) findViewById(R.id.pbRight), pbBottom);
        final CustomProgressBar pbTop = new CustomProgressBar((ProgressBar) findViewById(R.id.pbTop), pbRight);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.buildProgressBarThread(pbTop);
            }
        });
    }

    private void buildProgressBarThread(final CustomProgressBar progressBar) {
        // Start the lengthy operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.startProgressBar(progressBar);
            }
        }).start(); // Start the operation
    }

    private void startProgressBar(final CustomProgressBar progressBar) {

        while(progressBar.getProgressStatus() < 100){
            // Update the progress status
            progressBar.addProgressStatus(1);

            // Try to sleep the thread for 20 milliseconds
            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            // Update the progress bar
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.getProgressBar().setProgress(progressBar.getProgressStatus());
                    // If task execution completed
                    if(progressBar.getProgressStatus() == 100){
                        if (progressBar.getNextProgressBar() != null) {
                            MainActivity.this.buildProgressBarThread(progressBar.getNextProgressBar());
                        }
                    }
                }
            });
        }
    }
}
