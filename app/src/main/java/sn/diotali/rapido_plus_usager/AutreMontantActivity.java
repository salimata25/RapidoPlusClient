package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import sn.diotali.rapido_plus_usager.types.TransactionRequest;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class AutreMontantActivity extends AppCompatActivity {

    private Button btn_droit_enregistrement;
    private Button btn_mutation;
    private ImageView menu_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autre_montant);

        Constants.newTransaction = new TransactionRequest();
        Constants.newTransaction.setTransactionType(Constants.Menu.TIMBRE);

        btn_droit_enregistrement = findViewById(R.id.choix_droit_enregistrement);
        btn_mutation = findViewById(R.id.choix_mutation);
        menu_bar = findViewById(R.id.menu_bar);

        menu_bar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        btn_droit_enregistrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.newTransaction.setAutreMontant("Droits d'enregistrements");
                Constants.newTransaction.setTransactionType("Timbre fiscal");
                Intent intent = new Intent(AutreMontantActivity.this, InsererMontantActivity.class);
                startActivityForResult(intent,Constants.ActivityRequest.INSERER_MONTANT);
            }
        });

        btn_mutation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.newTransaction.setAutreMontant("Mutation de véhicule");
                Constants.newTransaction.setTransactionType("Timbre fiscal");
                Intent intent = new Intent(AutreMontantActivity.this, InsererMontantActivity.class);
                startActivityForResult(intent,Constants.ActivityRequest.INSERER_MONTANT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            switch(resultCode){
                case Constants.ResponseActivty.OK :{
                    switch(requestCode){
                        case Constants.ActivityRequest.INSERER_MONTANT :{
                            Intent intent = new Intent(this,ValidationAchatTimbreActivity.class);
                            startActivityForResult(intent,Constants.ActivityRequest.VALIDATION);
                            break;
                        }
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
