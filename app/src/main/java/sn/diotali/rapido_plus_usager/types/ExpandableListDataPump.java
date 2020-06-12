package sn.diotali.rapido_plus_usager.types;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<HistoriquePassage>> getData() {
        HashMap<String, List<HistoriquePassage>> expandableListDetail = new HashMap<String, List<HistoriquePassage>>();

        HistoriquePassage historiquePassage1 = new HistoriquePassage("00675467654", "Dakar - AIBD", "Gare de Pikine", "Rapido - 3", 2200, 400, 1800, 1800);
        HistoriquePassage historiquePassage2 = new HistoriquePassage("00675467654", "Dakar - AIBD", "Gare de Thiaroye", "Rapido - 2", 1800, 1000, 800, 800);

        List<HistoriquePassage> cricket = new ArrayList<HistoriquePassage>();
        cricket.add(historiquePassage1);


        List<HistoriquePassage> football = new ArrayList<HistoriquePassage>();
        football.add(historiquePassage2);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String date = format.format(new Date());

        expandableListDetail.put(date, cricket);
        expandableListDetail.put("10/06/2020", football);
        return expandableListDetail;
    }
}
