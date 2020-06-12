package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import sn.diotali.rapido_plus_usager.types.Timbre;
import sn.diotali.rapido_plus_usager.utils.Constants;


/**
 * Created by Cheikhouna on 27/02/2018.
 */
public class ValidationAchatTimbreActivity extends AppCompatActivity {
    private ImageView menu_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_achat);

        TextView txt_timbre = findViewById(R.id.txt_timbre);
        txt_timbre.setText("Achat de timbre fiscal");
        TextView title_montant_global = findViewById(R.id.txt_title_montant_global);
        title_montant_global.setText(getResources().getString(R.string.timbre_fe_tittre_montant_global));
        if (Constants.newTransaction.getAutreMontant() == "Droits d'enregistrements" || Constants.newTransaction.getAutreMontant() == "Mutation de véhicule") {
            title_montant_global.setText(Constants.newTransaction.getAutreMontant());
            txt_timbre.setText("Autres montants");
        }

        menu_bar = findViewById(R.id.menu_bar);
        menu_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        Button btnValider =  (Button)findViewById(R.id.btn_achat_fe_valider);
        TextView montant_global = (TextView)findViewById(R.id.txt_montant_global);

        List<Timbre> panierTimbresFilter = new ArrayList<>();
        int montantGlobal = 0;
        for ( Timbre timbrePanier: Constants.newTransaction.getPanierTimbres() ) {
            if(timbrePanier.getQuantite() != 0){
                montantGlobal += timbrePanier.getQuantite() * timbrePanier.getPrixU();
                panierTimbresFilter.add(timbrePanier);
            }
        }
        Constants.newTransaction.setPanierTimbres(panierTimbresFilter);
        montant_global.setText(" "+String.valueOf(montantGlobal)+" XOF");

        btnValider.setOnClickListener(new DetailsButton());
    }

    private class DetailsButton implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_achat_fe_valider : {
                    Intent intent = new Intent();
                    setResult(Constants.ResponseActivty.OK,intent);
                    finish();
                    break;
                }
            }
        }
    }


}
