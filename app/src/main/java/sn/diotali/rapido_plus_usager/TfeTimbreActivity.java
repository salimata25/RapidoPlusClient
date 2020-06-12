package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import sn.diotali.rapido_plus_usager.types.Timbre;
import sn.diotali.rapido_plus_usager.types.TransactionRequest;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class TfeTimbreActivity extends AppCompatActivity {

    private Button btn_timbre_1000;
    private Button btn_timbre_2000;
    private Button btn_timbre_5000;
    private Button btn_timbre_10000;
    ImageView menu_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfe_timbre);

        Constants.newTransaction = new TransactionRequest();
        Constants.newTransaction.setTransactionType(Constants.Menu.TIMBRE);

        menu_bar = findViewById(R.id.menu_bar);

        menu_bar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        btn_timbre_1000 = findViewById(R.id.choix_timbre_1000);
        btn_timbre_2000 = findViewById(R.id.choix_timbre_2000);
        btn_timbre_5000 = findViewById(R.id.choix_timbre_5000);
        btn_timbre_10000 = findViewById(R.id.choix_timbre_10000);

        btn_timbre_1000.setOnClickListener(new ClickTfeButton());
        btn_timbre_2000.setOnClickListener(new ClickTfeButton());
        btn_timbre_5000.setOnClickListener(new ClickTfeButton());
        btn_timbre_10000.setOnClickListener(new ClickTfeButton());


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

    }

    private class ClickTfeButton implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            List<Timbre> panierTimbres = new ArrayList<>();
            switch (v.getId()){
                case R.id.choix_timbre_1000 : {
                    panierTimbres.add(new Timbre("Timbre quotite", "Timbre 1000", 1000, 1));
                    Constants.newTransaction.setMontantTotal(1000);
                    break;
                }
                case R.id.choix_timbre_2000 : {
                    panierTimbres.add(new Timbre("Timbre quotite", "Timbre 2000", 2000, 1));
                    Constants.newTransaction.setMontantTotal(2000);
                    break;
                }
                case R.id.choix_timbre_5000 : {
                    panierTimbres.add(new Timbre("Timbre quotite", "Timbre 5000", 5000, 1));
                    Constants.newTransaction.setMontantTotal(5000);
                    break;
                }
                case R.id.choix_timbre_10000 : {
                    panierTimbres.add(new Timbre("Timbre quotite", "Timbre 10000", 10000, 1));
                    Constants.newTransaction.setMontantTotal(10000);
                    break;
                }

            }
            Constants.newTransaction.setPanierTimbres(panierTimbres);
            Constants.newTransaction.setTransactionType("Timbre fiscal");
            Intent intent = new Intent(TfeTimbreActivity.this, ValidationAchatTimbreActivity.class);
            startActivityForResult(intent,Constants.ActivityRequest.VALIDATION);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            switch(resultCode){
                case Constants.ResponseActivty.OK :{
                    switch(requestCode){
                        case Constants.ActivityRequest.VALIDATION :{
                            Intent intent = new Intent(this,ModePaiementActivity.class);
                            startActivityForResult(intent,Constants.ActivityRequest.PAIEMENTMODE);
                            break;
                        }
                        case Constants.ActivityRequest.PAIEMENTMODE :{
                            Intent intent = new Intent(this,PaiementMobileActivity.class);
                            startActivityForResult(intent,Constants.ActivityRequest.PAIEMENTMOBILE);
                            break;
                        }


                    }
                }
                default:{
                    switch (requestCode) {
                        case Constants.ActivityRequest.FINAL_DISPLAY: {
                            break;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
