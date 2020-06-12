package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class TfeMenu extends AppCompatActivity {

    ImageView menu_historique;
    ImageView menu_solde, menu_bar, btn_retour;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfe_menu);

        getObjectById();


        menu_bar.setOnClickListener(onButtonClickListener);

        menu_solde.setOnClickListener(onButtonClickListener);
        menu_historique.setOnClickListener(onButtonClickListener);
        btn_retour.setOnClickListener(onButtonClickListener);
    }


    private View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.menu_solde:
                    intent = new Intent (getApplicationContext(), TfeQuittanceActivity.class);
                    startActivity(intent);
                    break;

                case R.id.menu_historique:
                    intent = new Intent (getApplicationContext(), ExpandableHistoriqueActivity.class);
                    startActivity(intent);
                    break;

                case R.id.btn_retour:
                    finish();
                    break;

                case R.id.menu_bar:
                    intent = new Intent (getApplicationContext(), NavBarActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;
            }
        }
    };



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), DiotaliLogin.class);
        startActivity(intent);
        finish();
    }

    public void getObjectById(){
        menu_solde = findViewById(R.id.menu_solde);
        menu_historique = findViewById(R.id.menu_historique);
        menu_bar = findViewById(R.id.menu_bar);
        btn_retour = findViewById(R.id.btn_retour);
    }

}
