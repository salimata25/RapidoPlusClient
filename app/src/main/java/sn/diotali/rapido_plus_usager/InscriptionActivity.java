package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sn.diotali.rapido_plus_usager.services.ServiceResult;
import sn.diotali.rapido_plus_usager.types.User;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class InscriptionActivity extends DiotaliMain {
    Button btn_valider;
    EditText txt_carte_rapido, txt_nom, txt_prenom, txt_email, txt_tel, txt_adresse, txt_pwd, txt_confirm_pwd;
    TextView txt_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        txt_carte_rapido = findViewById(R.id.txt_carte_rapido);
        txt_nom = findViewById(R.id.txt_nom);
        txt_prenom = findViewById(R.id.txt_prenom);
        txt_email = findViewById(R.id.txt_email);
        txt_tel = findViewById(R.id.txt_tel);
        txt_adresse = findViewById(R.id.txt_adresse);
        txt_pwd = findViewById(R.id.txt_pwd);
        txt_confirm_pwd = findViewById(R.id.txt_confirm_pwd);
        txt_error = findViewById(R.id.txt_v_error);
        txt_error.setVisibility(View.INVISIBLE);


        btn_valider = findViewById(R.id.btn_valider);
        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_error.setVisibility(View.INVISIBLE);

                String carte_rapido = txt_carte_rapido.getText().toString();
                String nom = txt_nom.getText().toString();
                String prenom = txt_prenom.getText().toString();
                String email = txt_email.getText().toString();
                String tel = txt_tel.getText().toString();
                String adresse = txt_adresse.getText().toString();
                String pwd = txt_pwd.getText().toString();
                String confirm_pwd = txt_confirm_pwd.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String regex = " ";
                String replacement = "";

                if (carte_rapido.isEmpty() || carte_rapido.replaceAll(regex, replacement).isEmpty()) {
                    txt_carte_rapido.setError("Numéro de la carte rapido");
                } else if (carte_rapido.length() != 13) {
                    txt_carte_rapido.setError("Veuillez saisir un numéro valide !");
                } else if (nom.isEmpty() || nom.replaceAll(regex, replacement).isEmpty()) {
                    txt_nom.setError("Nom");
                } else if (prenom.isEmpty() || prenom.replaceAll(regex, replacement).isEmpty()) {
                    txt_prenom.setError("Prénom");
                } else if (email.isEmpty() || email.replaceAll(regex, replacement).isEmpty()) {
                    txt_email.setError("Email");
                }else if (!email.matches(emailPattern)) {
                    txt_email.setError("Email invalide");
                } else if (tel.isEmpty() || tel.replaceAll(regex, replacement).isEmpty()) {
                    txt_tel.setError("Téléphone");
                } else if (tel.length() < 9 || tel.length() > 14) {
                    txt_tel.setError("Veuillez saisir un numéro valide !");
                } else if (adresse.isEmpty() || adresse.replaceAll(regex, replacement).isEmpty()) {
                    txt_adresse.setError("Adresse");
                } else if (pwd.isEmpty() || pwd.replaceAll(regex, replacement).isEmpty()) {
                    txt_pwd.setError("Mot de passe");
                } else if (confirm_pwd.isEmpty() || confirm_pwd.replaceAll(regex, replacement).isEmpty()) {
                    txt_confirm_pwd.setError("Confirmer");
                } else if (!(confirm_pwd.equals(pwd))) {
                    txt_error.setText("Veuillez confirmer le mot de passe !");
                    txt_error.setVisibility(View.VISIBLE);
                } else {
                    User response = new User();
                    response.setStatus(0);
                    response.setMessage("SUCCESS");
                    response.setFirstName(prenom);
                    response.setLastName(nom);
                    response.setAddress(adresse);
                    response.setPhone(tel);
                    response.setNin(carte_rapido);
                    response.setEmail(email);
                    Constants.newUser = response;

                    Intent intent = new Intent(InscriptionActivity.this, CodeSmsActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

    @Override
    public void sendTaskResponse(ServiceResult serviceResult) {

        try {
            Log.d("DIOTALI LOGIN", "receiving response");
            if (Constants.Methods.INSCRIRE.equals(serviceResult.getMethod())) {
                if (Constants.ResponseStatus.OK == serviceResult.getStatus()) {
                    User response = (User) serviceResult;
                    Intent intent = new Intent(this, CodeSmsActivity.class);
                    Constants.newUser = response;
                    startActivity(intent);
                } else {
                    Log.d("LOGIN", "login error");
                    txt_error.setText(serviceResult.getMessage());
                    txt_error.setVisibility(View.VISIBLE);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
