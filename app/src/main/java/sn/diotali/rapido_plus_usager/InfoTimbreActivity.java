package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.SimpleDateFormat;

import sn.diotali.rapido_plus_usager.types.HistoriqueAchat;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class InfoTimbreActivity extends AppCompatActivity implements View.OnClickListener {
    HistoriqueAchat timbre;
    Bitmap bmpQrCode = null;
    ImageView qrCode;
    private String url = Constants.BASE_URL +  Constants.Methods.URL_QRCODE +  "qrCodeImage/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_timbre);



        findViewById(R.id.menu_bar).setOnClickListener(this);

        timbre = (HistoriqueAchat) getIntent().getExtras().getSerializable("INFO_TIMBRE");

        TextView libelle = findViewById(R.id.title_libele);
        TextView montant = findViewById(R.id.title_montant);
        TextView date = findViewById(R.id.title_date);
        TextView notif = findViewById(R.id.txt_notif);
        qrCode = findViewById(R.id.qr_code);
        /*Log.e(this.getClass().getName(), url+timbre.getTransactionNumber());
        new DownloadImageTask(bmpQrCode).execute(url+timbre.getTransactionNumber());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("Handler1.postDelayed", " bmpQrCode status");
                if(bmpQrCode == null){
                    Log.d(DiotaliLogin.class.getName()+" bmpQrCode status- - -", "handler2 null DiotaliUtils.encryptData(Constants.QR_CODE_KEY,qrCodeContent)");
                }else{
                    Log.d(DiotaliLogin.class.getName()+" bmpQrCode status- - -", "handler2 not null bmpQrCode)");
                }
                bmpQrCode = Bitmap.createScaledBitmap(bmpQrCode,(int)(bmpQrCode.getWidth()*3), (int)(bmpQrCode.getHeight()*3), true);
                qrCode.setImageBitmap(bmpQrCode);
            }
        }, 1000);*/

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        libelle.setText(timbre.getTransactionType());
        montant.setText(timbre.getAmount()+" FCFA");
        date.setText(format.format(timbre.getTransactionDate())+"");
        if (timbre.getStatus().equals("consommé")) {
            qrCode.setImageDrawable(getResources().getDrawable(R.drawable.qr_code_consomme));
            if (timbre.getTransactionType().equals("Quittance")){
                notif.setText("Cette quittance a déjà été utilisée !");
            }else {
                notif.setText("Ce timbre a déjà été utilisé !");
            }

        }else {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.screenBrightness = 0.8f; // 0.0 - 1.0
            getWindow().setAttributes(lp);
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
        }
    }




    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        Bitmap bmImage;
        public DownloadImageTask(Bitmap bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error doInBackground", e.getMessage());
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage = result;
            processValue(bmImage);
        }
    }

    void processValue(Bitmap bmpQr) {
        bmpQrCode = bmpQr;
        Log.e("Handler2.processValue", " bmpQrCode status");
        if(bmpQrCode == null){
            Log.d(DiotaliLogin.class.getName()+" bmpQrCode status- - -", "processValue null DiotaliUtils.encryptData(Constants.QR_CODE_KEY,qrCodeContent)");
        }else{
            Log.d(DiotaliLogin.class.getName()+" bmpQrCode status- - -", "processValue not null bmpQrCode)");
        }
    }
}
