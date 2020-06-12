package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import sn.diotali.rapido_plus_usager.services.ServiceResult;
import sn.diotali.rapido_plus_usager.types.HistoriqueAchat;
import sn.diotali.rapido_plus_usager.types.TransactionResponse;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class PaiementMobileActivity  extends DiotaliMain implements View.OnClickListener {
    TextView txt_montant, txt_frais_supp, txt_montant_net, txt_error;
    EditText edt_tel, edt_code;
    HistoriqueAchat achat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_mobile);
        Log.d(this.getClass().getName(),"DONNEES ACHAT: "+Constants.newTransaction.toString());
        findViewById(R.id.menu_bar).setOnClickListener(this);
        findViewById(R.id.btn_valider).setOnClickListener(this);

        txt_montant = findViewById(R.id.txt_montant_total);
        txt_frais_supp = findViewById(R.id.txt_frais_supp);
        txt_montant_net = findViewById(R.id.txt_prix_net);
        edt_tel = findViewById(R.id.txt_n_tel);
        edt_code = findViewById(R.id.txt_code_auto);
        txt_error = findViewById(R.id.txt_v_error);
        txt_error.setVisibility(View.INVISIBLE);

        txt_montant.setText(" "+Constants.newTransaction.getMontantTotal() + " FCFA");
        if(Constants.newTransaction.getTransactionType().equalsIgnoreCase(Constants.Menu.TIMBRE)){
            TextView txt_timbre = findViewById(R.id.txt_timbre);
            txt_timbre.setText("Achat de timbre fiscal");
            if (Constants.newTransaction.getAutreMontant() == "Droits d'enregistrements" || Constants.newTransaction.getAutreMontant() == "Mutation de véhicule") {
                txt_timbre.setText("Autres montants");
            }

            double frais = Constants.newTransaction.getMontantTotal() * 0.025;
            double net = Constants.newTransaction.getMontantTotal() + frais;

            txt_frais_supp.setText(" "+frais + " FCFA");
            txt_montant_net.setText(" "+net + " FCFA");
        }else {
            double frais = Constants.newTransaction.getMontantTotal() * 0.05;
            double net = Constants.newTransaction.getMontantTotal() + frais;

            txt_frais_supp.setText(" "+frais + " FCFA");
            txt_montant_net.setText(" "+net + " FCFA");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_bar:
                Intent intent = new Intent(getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.btn_valider:

                String regex = " ";
                String replacement = "";

                if (edt_tel.getText().toString().isEmpty() || edt_tel.getText().toString().replaceAll(regex, replacement).isEmpty()) {
                    edt_tel.setError("Numéro de téléphone");
                } else if (edt_code.getText().toString().isEmpty() || edt_code.getText().toString().replaceAll(regex, replacement).isEmpty()) {
                    edt_code.setError("Code d'autorisation");
                } else {

                    Constants.newTransaction.setTelephonePaiement(edt_tel.getText().toString());
                    Constants.newTransaction.setCodeAutoristionPaiement(edt_code.getText().toString());

                    Constants.newAchat = new HistoriqueAchat();
                    Constants.newAchat.setTransactionType(Constants.newTransaction.getTransactionType());
                    Constants.newAchat.setAmount(Constants.newTransaction.getMontantTotal());
                    Date dateT = new Date();
                    Constants.newAchat.setTransactionDate(dateT);
                    Constants.newAchat.setStatus("non consommé");
                    Constants.list.add(0, Constants.newAchat);
                    Log.d(this.getClass().getName(),"DONNEES ACHAT: "+Constants.newTransaction.toString());
                    intent = new Intent(getApplicationContext(), FinishActivity.class);
                    startActivity(intent);
                    finish();

                }



        }
    }


    @Override
    public void sendTaskResponse(ServiceResult serviceResult) {
        try {
            if(Constants.ResponseStatus.OK == serviceResult.getStatus()){
                TransactionResponse response = (TransactionResponse) serviceResult;

                Log.d(this.getClass().getName(), "sendTaskResponse success " +response.toString());
                Log.d(this.getClass().getName(), response.toString());
                Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
                startActivity(intent);
            } else {
                Log.d(this.getClass().getName(), "sendTaskResponse error");
                txt_error.setText(serviceResult.getMessage());
                txt_error.setVisibility(View.VISIBLE);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
