package sn.diotali.rapido_plus_usager.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import sn.diotali.rapido_plus_usager.R;
import sn.diotali.rapido_plus_usager.types.HistoriqueAchat;

public class HistoriqueListAdapter extends BaseAdapter {

    private List<HistoriqueAchat> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public HistoriqueListAdapter(Context context, List<HistoriqueAchat> listData) {
        Log.d(this.getClass().getName(), "sendTaskResponse success 1 " +listData.toString());
        this.listData = listData;
        Log.d(this.getClass().getName(), "sendTaskResponse success 2 " +this.listData.toString());
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.icone = view.findViewById(R.id.icone_qrcode);
            holder.libelle = view.findViewById(R.id.txt_libelle);
            holder.date = view.findViewById(R.id.txt_date);
            holder.prix = view.findViewById(R.id.txt_prix);
            holder.statut = view.findViewById(R.id.txt_statut);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");//yyyy-MM-dd
        HistoriqueAchat timbre = this.listData.get(i);
        holder.libelle.setText(timbre.getTransactionType());
        holder.date.setText(format.format(timbre.getTransactionDate()));
        holder.prix.setText(timbre.getAmount() + " FCFA");
        if (timbre.getStatus().equals("non consommé")) {
            holder.statut.setText("Utilisable");
            holder.statut.setTextColor(Color.GREEN);
        }else {
            holder.statut.setText("Consommé");
            holder.statut.setTextColor(Color.RED);
        }

        return view;
    }

    public static class ViewHolder {
        ImageView icone;
        TextView libelle, date, prix, statut;
    }
}
