package labs.sammy.mylab11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    public static GoogleAnalytics analytics;
    public static Tracker tracker;
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        tracker = analytics.newTracker("UA-84477395-1");
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        button1 = (Button)this.findViewById(R.id.button1);
        button2 = (Button)this.findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Botones")
                        .setAction("Press")
                        .setLabel("Button 1")
                        .build());
                Toast.makeText(MainActivity.this, "Evento enviado: Boton 1", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Botones")
                        .setAction("Press")
                        .setLabel("Button 2")
                        .build());
                Toast.makeText(MainActivity.this, "Evento enviado: Boton 2", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
