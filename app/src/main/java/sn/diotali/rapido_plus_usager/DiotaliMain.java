package sn.diotali.rapido_plus_usager;

import android.support.v7.app.AppCompatActivity;
import sn.diotali.rapido_plus_usager.services.ServiceResult;


public abstract class  DiotaliMain extends AppCompatActivity {

    public abstract void sendTaskResponse(ServiceResult serviceResult);


}
