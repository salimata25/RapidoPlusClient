package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import sn.diotali.rapido_plus_usager.types.Timbre;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class InsererMontantActivity extends AppCompatActivity {

    private Button btn_achat_valider;
    private ImageView menu_bar;
    private EditText txt_montant;
    private String libelle = "";
    private int montant = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserer_montant);

        txt_montant = findViewById(R.id.montant);
        libelle = Constants.newTransaction.getAutreMontant();



        menu_bar = findViewById(R.id.menu_bar);

        menu_bar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        btn_achat_valider = findViewById(R.id.btn_achat_valider);

        btn_achat_valider.setOnClickListener(new ClickTfeButton());

    }

    private class ClickTfeButton implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_achat_valider : {
                    if (txt_montant.getText().toString() == null || txt_montant.getText().toString().replaceAll(" ", "").isEmpty()) {
                        txt_montant.setError("Montant");
                    }else {
                        montant = Integer.parseInt(txt_montant.getText().toString());
                        Constants.newTransaction.setMontantTotal(montant);
                        List<Timbre> panierTimbres = new ArrayList<>();
                        panierTimbres.add(new Timbre("Timbre quotite", libelle, montant, 1));

                        Constants.newTransaction.setPanierTimbres(panierTimbres);

                        Intent intent = new Intent();
                        setResult(Constants.ResponseActivty.OK,intent);
                        finish();
                    }
                    break;
                }


            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            switch(resultCode){
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
