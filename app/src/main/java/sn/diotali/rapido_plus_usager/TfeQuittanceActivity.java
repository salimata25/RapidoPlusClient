package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import sn.diotali.rapido_plus_usager.types.Quittance;
import sn.diotali.rapido_plus_usager.types.TransactionRequest;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class TfeQuittanceActivity extends AppCompatActivity {

    ImageView menu_bar;
    Button btn_valider;
    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText nin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfe_quittance);

        getEllementsById();

        Constants.newTransaction = new TransactionRequest();
        Constants.newTransaction.setTransactionType(Constants.Menu.QUITTANCE);

        menu_bar = findViewById(R.id.menu_bar);

        menu_bar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        Log.d(this.getClass().getName(), "ClickTfeButton sendTaskResponse 1");
        btn_valider.setOnClickListener(new ClickTfeButton());
    }


    private class ClickTfeButton implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(this.getClass().getName(), "ClickTfeButton sendTaskResponse 2");
            switch (v.getId()){
                case R.id.btn_valider : {
                    Log.d(this.getClass().getName(), "ClickTfeButton sendTaskResponse 3");
                    if (firstName.getText().toString() == null || firstName.getText().toString().replaceAll(" ", "").isEmpty()) {
                        firstName.setError(getResources().getString(R.string.client_firstname));
                    } else if (lastName.getText().toString() == null || lastName.getText().toString().replaceAll(" ", "").isEmpty()) {
                        lastName.setError(getResources().getString(R.string.client_lastname));
                    } else if (nin.getText().toString() == null || nin.getText().toString().replaceAll(" ", "").isEmpty()) {
                        nin.setError(getResources().getString(R.string.client_nin));
                    } else if (nin.getText().toString().length() != 13) {
                        nin.setError("Veuillez saisir un NIN valide !");
                    } else if (phone.getText().toString() == null || phone.getText().toString().replaceAll(" ", "").isEmpty()) {
                        phone.setError(getResources().getString(R.string.client_phone));
                    }else if (phone.getText().toString().length() < 9 || phone.getText().toString().length() > 14) {
                        phone.setError("Veuillez saisir un numéro valide !");
                    }
                    else{
                        Log.d(this.getClass().getName(), "ClickTfeButton sendTaskResponse 4");
                        Quittance quittance = new Quittance();
                        quittance.setFirstName(firstName.getText().toString());
                        quittance.setLastName(lastName.getText().toString());
                        quittance.setPhone(phone.getText().toString());
                        quittance.setNin(nin.getText().toString());
                        quittance.setAddress("address");

                        Constants.newTransaction.addQuittance(quittance);
                        Constants.newTransaction.setTransactionType("Quittance");
                        Constants.newTransaction.setMontantTotal(20000);

                        Intent intent = new Intent(TfeQuittanceActivity.this, DetailsQuittanceActivity.class);
                        startActivityForResult(intent,Constants.ActivityRequest.DETAILQUITTANCE);
                        Log.d(this.getClass().getName(), "ClickTfeButton sendTaskResponse 5");
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
                case Constants.ResponseActivty.OK :{
                    switch(requestCode){
                        case Constants.ActivityRequest.DETAILQUITTANCE :{
                            Intent intent = new Intent(this,ValidationAchatQuittanceActivity.class);
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



    public void getEllementsById(){
        btn_valider = findViewById(R.id.btn_valider);

        firstName = (EditText) findViewById(R.id.labelFirstName);
        lastName = (EditText) findViewById(R.id.labelLastName);
        phone = (EditText) findViewById(R.id.labelPhone);
        nin = (EditText) findViewById(R.id.labelNin);
    }


}
