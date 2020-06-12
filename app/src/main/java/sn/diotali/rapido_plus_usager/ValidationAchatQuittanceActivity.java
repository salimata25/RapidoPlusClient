package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import sn.diotali.rapido_plus_usager.utils.Constants;


/**
 * Created by Cheikhouna on 27/02/2018.
 */

public class ValidationAchatQuittanceActivity extends AppCompatActivity {
    private ImageView menu_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_achat);

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
        montant_global.setText("20.000 XOF");
        btnValider.setOnClickListener(new ValidationAchatQuittanceActivity.DetailsButton());

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
