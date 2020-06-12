package sn.diotali.rapido_plus_usager.utils;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sn.diotali.rapido_plus_usager.R;
import sn.diotali.rapido_plus_usager.types.HistoriquePassage;


public class ExpandableHistoriqueListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<HistoriquePassage>> expandableListDetail;

    public ExpandableHistoriqueListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<HistoriquePassage>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
//        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {

            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder.num_carte = convertView.findViewById(R.id.num_carte);
            holder.sens = convertView.findViewById(R.id.sens);
            holder.gare = convertView.findViewById(R.id.gare);
            holder.credit_initial = convertView.findViewById(R.id.credit_initial);
            holder.montant = convertView.findViewById(R.id.montant);
            holder.credit_restant = convertView.findViewById(R.id.credit_restant);
            holder.solde = convertView.findViewById(R.id.solde);
            holder.voie = convertView.findViewById(R.id.voie);
            convertView.setTag(holder);
        }else {

            holder = (ViewHolder) convertView.getTag();
        }
        HistoriquePassage passage = this.expandableListDetail.get(getGroup(listPosition)).get(expandedListPosition);
        holder.num_carte.setText(passage.getNum_carte());
        holder.sens.setText(passage.getSens());
        holder.gare.setText(passage.getGare());
        holder.voie.setText(passage.getVoie());
        holder.credit_initial.setText(passage.getCredit_initial() + "FCFA");
        holder.montant.setText(passage.getMontant() + "FCFA");
        holder.credit_restant.setText(passage.getCredit_restant() + "FCFA");
        holder.solde.setText(passage.getSolde() + "FCFA");

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    public static class ViewHolder {
        TextView num_carte, sens, gare, voie, credit_initial, montant, credit_restant, solde;
    }
}
