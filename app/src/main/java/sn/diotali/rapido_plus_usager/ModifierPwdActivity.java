package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import sn.diotali.rapido_plus_usager.services.ServiceResult;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class ModifierPwdActivity extends DiotaliMain implements View.OnClickListener{

    EditText ancien_pwd, nouveau_pwd, confim_pwd;
    TextView txt_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_pwd);

        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_modifier).setOnClickListener(this);

        txt_error = findViewById(R.id.txt_v_error);
        txt_error.setVisibility(View.INVISIBLE);

        ancien_pwd = findViewById(R.id.txt_ancien_pwd);
        nouveau_pwd = findViewById(R.id.txt_nouveau_pwd);
        confim_pwd = findViewById(R.id.txt_confirmer);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.btn_modifier:
                String ancien = ancien_pwd.getText().toString();
                String nouveau = nouveau_pwd.getText().toString();
                String confirmer = confim_pwd.getText().toString();

                String regex = " ";
                String replacement = "";

                if (ancien.isEmpty() || ancien.replaceAll(regex, replacement).isEmpty()) {
                    ancien_pwd.setError("Ancien mot de passe");
                } else if (nouveau.isEmpty() || nouveau.replaceAll(regex, replacement).isEmpty()) {
                    nouveau_pwd.setError("Nouveau mot de pass");
                } else if (confirmer.isEmpty() || confirmer.replaceAll(regex, replacement).isEmpty()) {
                    confim_pwd.setError("Confirmer le mot de passe");
                }else if (!(confirmer.equals(nouveau))) {
                    txt_error.setText("Veuillez confirmer le nouveau mot de passe !");
                    txt_error.setVisibility(View.VISIBLE);
                } else {
                    txt_error.setTextColor(Color.GREEN);
                    txt_error.setText("Mot de passe modifié avec succés");
                    txt_error.setVisibility(View.VISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), TfeMenu.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 2000);

                }
        }
    }

    @Override
    public void sendTaskResponse(ServiceResult serviceResult) {
        try {
            Log.d("DIOTALI LOGIN", "receiving response");
            if (Constants.Methods.UPDATE_PWD.equals(serviceResult.getMethod())) {
                if (Constants.ResponseStatus.OK == serviceResult.getStatus()) {

                    Log.d(this.getClass().getName(), "update sendTaskResponse 1");

                    txt_error.setTextColor(Color.GREEN);
                    txt_error.setText("Mot de passe modifié avec succés");
                    txt_error.setVisibility(View.VISIBLE);
                    Log.d(this.getClass().getName(), "update sendTaskResponse 4");
                } else {
                    Log.d(this.getClass().getName(), "sendTaskResponse error");
                    txt_error.setText(serviceResult.getMessage());
                    txt_error.setVisibility(View.VISIBLE);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
