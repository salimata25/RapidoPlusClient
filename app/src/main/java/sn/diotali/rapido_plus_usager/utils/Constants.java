package sn.diotali.rapido_plus_usager.utils;

import java.util.List;

import sn.diotali.rapido_plus_usager.types.HistoriqueAchat;
import sn.diotali.rapido_plus_usager.types.TransactionRequest;
import sn.diotali.rapido_plus_usager.types.TransactionResponse;
import sn.diotali.rapido_plus_usager.types.User;

public class Constants {

    public static String BASE_URL = "http://192.168.1.52:8031/tfe/";

    public static void newURL(final String ADDRESS_IP){
        BASE_URL = "http://" + ADDRESS_IP +":8031/tfe/";
    }


    public static  class Methods{
        public static final String HISTORIQUE_ACHAT = "access/mobile/usager/historique/transaction";
        public static final String ACHETER_TIMBRE = "clientmobile/acheter/timbre";
        public static final String ACHETER_QUITTANCE = "clientmobile/acheter/quittance";
        public static final String LOGIN = "access/mobile/connexion";
        public static final String INSCRIRE = "access/mobile/usager/inscrire";
        public static final String ACTIVERCOMPTE = "access/mobile/usager/inscrire/validation";
        public static final String UPDATE_INFO = "access/mobile/usager/updateinfouser";
        public static final String UPDATE_PWD = "access/mobile/usager/updatemepwd";
        public static final String PWD_OUBLIE = "access/mobile/usager/acces/verification";
        public static final String NEW_PWD = "access/mobile/usager/newpwd";
        public static final String URL_QRCODE = "clientmobile/";
    }

    public static class Menu {
        public static final String TIMBRE = "TIMBRE";
        public static final String QUITTANCE = "QUITTANCE";
        public static final String AUTRES_MONTANTS = "AUTRES";
    }

    public static class ResponseStatus {
        public static final int OK = 0;
    }

    public class ExtrasName {
        public static final String STATUS = "STATUS";
        public static final String MESSAGE = "MESSAGE";
        public static final String TICKET_INFOS = "TICKET_INFOS";
    }

    public class PaymentMeans {
        public static final String CASH = "CASH";
    }

    public class ActivityRequest {
        public static final int DETAILQUITTANCE = 1;
        public static final int DETAILTIMBRE = 2;
        public static final int VALIDATION = 3;
        public static final int AUTRES_MONTANTS = 4;
        public static final int INSERER_MONTANT = 5;
        public static final int PAIEMENTMODE = 6;
        public static final int PAIEMENTMOBILE = 7;
        public static final int PAIEMENTCARTE = 8;
        public static final int FINAL_DISPLAY = 9;
        public static final int CODE_PWD = 10;
        public static final int NEW_PWD = 11;
    }

    public class ResponseActivty {
        public static final int OK = 1;
        public static final int NOK = 0;
        public static final int FAILED = -999;
    }

    public static User newUser;
    public static TransactionRequest newTransaction;
    public static HistoriqueAchat newAchat;
    public static TransactionResponse transactionResponse;
    public static  List<HistoriqueAchat> list;
    public static String PROFILE = "";


}
