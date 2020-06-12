package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sn.diotali.rapido_plus_usager.utils.Constants;

public class ModePaiementActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView btn_omoney;
    TextView txt_montant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_paiement);

        SharedPreferences sharedPreferences = getSharedPreferences("MyConfigMarchand", MODE_PRIVATE);

        String tokenStorage = sharedPreferences.getString("TokenStorage","tokenStorage");
        Constants.newTransaction.setToken(tokenStorage);

        if(Constants.newTransaction.getTransactionType().equalsIgnoreCase(Constants.Menu.TIMBRE)){
            TextView txt_timbre = findViewById(R.id.txt_timbre);
            txt_timbre.setText("Achat de timbre fiscal");
            if (Constants.newTransaction.getAutreMontant() == "Droits d'enregistrements" || Constants.newTransaction.getAutreMontant() == "Mutation de véhicule") {
                txt_timbre.setText("Autres montants");
            }
        }

        btn_omoney = findViewById(R.id.btn_omoney);
        btn_omoney.setOnClickListener(this);
        findViewById(R.id.menu_bar).setOnClickListener(this);

        txt_montant = findViewById(R.id.txt_prix_total);

        txt_montant.setText(" "+Constants.newTransaction.getMontantTotal().toString() + " FCFA");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_omoney:
                Constants.newTransaction.setModePaiement("mobile");
                Constants.newTransaction.setMoyenPaiement("Orange Money");
                Log.d(this.getClass().getName(),"DONNEES ACHAT: "+Constants.newTransaction.toString());
                Intent intent = new Intent();
                setResult(Constants.ResponseActivty.OK,intent);
                finish();
                break;
            case R.id.menu_bar:
                intent = new Intent(getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }
}
