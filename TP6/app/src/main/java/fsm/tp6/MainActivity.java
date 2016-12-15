package fsm.tp6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    TextView tvInfo;
    Thread testThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerThread();
            }
        });
    }

    private void lancerThread(){
        if(testThread == null){
            MyRunnableTask task = new MyRunnableTask();
            task.setParams(this);
            testThread = new Thread(task);
            testThread.start();

            tvInfo.setText("Thread lancé...");
        }
        else{
            Toast.makeText(this, "Opération en cours...", Toast.LENGTH_SHORT).show();
        }
    }

    private void finThread(){
        try{
            testThread.join();
        }
        catch (InterruptedException e){}

        testThread = null;
        tvInfo.setText("fin Thread");
    }

    class MyRunnableTask implements Runnable{
        MainActivity activity;

        public void setParams(MainActivity activity){
            this.activity = activity;
        }

        public void run(){
            activity.fonction1();

            Runnable textWriter = new Runnable() {
                @Override
                public void run() {
                    activity.finThread();
                }
            };

            activity.runOnUiThread(textWriter);
        }
    }

    private void fonction1(){
        System.out.println("---- Début -----");
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("---- Fin ----");
    }

    private void playFile(){
        // todo
    }

    private void downloadFile(){
        // todo
    }
}


