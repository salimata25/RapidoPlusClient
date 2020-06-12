package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import sn.diotali.rapido_plus_usager.services.ServiceResult;
import sn.diotali.rapido_plus_usager.types.HistoriqueAchat;
import sn.diotali.rapido_plus_usager.types.TransactionResponse;
import sn.diotali.rapido_plus_usager.utils.Constants;
import sn.diotali.rapido_plus_usager.utils.HistoriqueListAdapter;

public class HistoriqueActivity extends DiotaliMain implements View.OnClickListener{

    List<HistoriqueAchat> listTimbre;
    ListView listTimbreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        Log.d(this.getClass().getName(), "HistoriqueActivity ");

        findViewById(R.id.menu_bar).setOnClickListener(this);

        listTimbreView = findViewById(R.id.list_timbre);
        listTimbre = getLisData();
        listTimbreView.setAdapter(new HistoriqueListAdapter(this, listTimbre));
        listTimbreView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), InfoTimbreActivity.class);
                intent.putExtra("INFO_TIMBRE", listTimbre.get(i));
                startActivity(intent);
            }
        });


    }

    private List<HistoriqueAchat> getLisData() {


        return Constants.list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_bar:
                Intent intent = new Intent (getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }


    @Override
    public void sendTaskResponse(ServiceResult serviceResult) {
        try {
            if(Constants.ResponseStatus.OK == serviceResult.getStatus()){
                TransactionResponse response = (TransactionResponse) serviceResult;

                Log.d(this.getClass().getName(), "sendTaskResponse success " +response.toString());
                listTimbre = response.getListUsers();
                listTimbreView.setAdapter(new HistoriqueListAdapter(this, listTimbre));
            } else {
                Log.d(this.getClass().getName(), "sendTaskResponse error " +serviceResult);


            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
