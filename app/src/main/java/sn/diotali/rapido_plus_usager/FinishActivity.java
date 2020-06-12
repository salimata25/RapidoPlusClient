package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sn.diotali.rapido_plus_usager.utils.Constants;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish2);

        if(Constants.newTransaction.getTransactionType().equalsIgnoreCase(Constants.Menu.TIMBRE)){
            TextView txt_timbre = findViewById(R.id.txt_timbre);
            txt_timbre.setText("Achat de timbre fiscal");
            if (Constants.newTransaction.getAutreMontant() == "Droits d'enregistrements" || Constants.newTransaction.getAutreMontant() == "Mutation de véhicule") {
                txt_timbre.setText("Autres montants");
            }
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), InfoTimbreFinishActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
