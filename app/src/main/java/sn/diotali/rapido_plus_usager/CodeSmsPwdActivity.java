package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sn.diotali.rapido_plus_usager.services.ServiceResult;
import sn.diotali.rapido_plus_usager.types.User;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class CodeSmsPwdActivity extends DiotaliMain implements View.OnClickListener{

    EditText edt_code;
    TextView txt_error;
    Button btn_valider;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_sms_pwd);

        edt_code = findViewById(R.id.edt_code);

        txt_error = findViewById(R.id.txt_v_error);
        txt_error.setVisibility(View.INVISIBLE);

        Log.d("USER PHONE", Constants.newUser.toString());

        btn_valider = findViewById(R.id.btn_valider);
        btn_valider.setOnClickListener(this);
        findViewById(R.id.btn_renvoie).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_valider:
                txt_error.setVisibility(View.INVISIBLE);

                String code = edt_code.getText().toString();

                String regex = " ";
                String replacement = "";

                if (code.isEmpty() || code.replaceAll(regex, replacement).isEmpty()) {
                    edt_code.setError("Code de vérification");
                } else {
                    Intent intent = new Intent(this, PwdOublieActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn_renvoie:
                break;
        }
    }

    @Override
    public void sendTaskResponse(ServiceResult serviceResult) {

        try {
            Log.d("DIOTALI LOGIN", "receiving response");
            if (Constants.Methods.ACTIVERCOMPTE.equals(serviceResult.getMethod())) {
                if (Constants.ResponseStatus.OK == serviceResult.getStatus()) {
                    User response = (User) serviceResult;

                    sharedPreferences = getSharedPreferences("MyConfigMarchand", MODE_PRIVATE);
                    sharedPreferences.edit().putString("RoleStorage", response.getRole()).commit();
                    sharedPreferences.edit().putString("FirstNameStorage", response.getFirstName()).commit();
                    sharedPreferences.edit().putString("LastNameStorage", response.getLastName()).commit();
                    sharedPreferences.edit().putString("AddressStorage", response.getAddress()).commit();
                    sharedPreferences.edit().putString("PhoneStorage", response.getPhone()).commit();
                    sharedPreferences.edit().putString("NinStorage", response.getNin()).commit();
                    sharedPreferences.edit().putString("EmailStorage", response.getEmail()).commit();

                    Intent intent = new Intent(this, PwdOublieActivity.class);
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
