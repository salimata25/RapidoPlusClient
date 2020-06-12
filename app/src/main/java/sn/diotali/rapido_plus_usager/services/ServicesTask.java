package sn.diotali.rapido_plus_usager.services;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

import sn.diotali.rapido_plus_usager.DiotaliMain;
import sn.diotali.rapido_plus_usager.R;
import sn.diotali.rapido_plus_usager.link.Dialog;
import sn.diotali.rapido_plus_usager.types.InscriptionRequest;
import sn.diotali.rapido_plus_usager.types.LoginRequest;
import sn.diotali.rapido_plus_usager.types.TransactionRequest;
import sn.diotali.rapido_plus_usager.types.TransactionResponse;
import sn.diotali.rapido_plus_usager.types.User;
import sn.diotali.rapido_plus_usager.utils.Constants;


/**
 * Created by Cheikhouna on 02/02/2018.
 */

public class ServicesTask extends AsyncTask<ServiceParams, Integer, ServiceResult> {

    private DiotaliMain currentActivity;
    private AlertDialog alertDialog;

    public ServicesTask(DiotaliMain currentActivity) {

        this.currentActivity = currentActivity;
    }


    @Override
    protected ServiceResult doInBackground(ServiceParams... serviceParams) {
        ServiceParams service = serviceParams[0];
        ServiceResult result = new ServiceResult();
        try {


            Log.d("doInBackground", service.getMethodName());

            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ClientHttpRequestInterceptor clientHttpRequestInterceptors = (request, body, execution) -> {
                return execution.execute(request, body);
            };
            restTemplate.setInterceptors(Collections.singletonList(clientHttpRequestInterceptors));

            if (Constants.Methods.ACHETER_TIMBRE.equals(service.getMethodName())) {
                Log.d("-REQ ACHETER_TIMBRE-",service.getMethodName());
                HttpEntity<TransactionRequest> req = new HttpEntity<>((TransactionRequest) service.getMethodParams());
                Log.d("-----credentials-------",req.toString());
                String url = Constants.BASE_URL + Constants.Methods.ACHETER_TIMBRE;
                result = restTemplate.postForObject(url, req, TransactionResponse.class);
                Log.d("-----RESPONSE------",result.toString());
            }
            else if (Constants.Methods.ACHETER_QUITTANCE.equals(service.getMethodName())) {
                Log.d("-REQ ACHETER_QUITTANCE-",service.getMethodName());
                HttpEntity<TransactionRequest> req = new HttpEntity<>((TransactionRequest) service.getMethodParams());
                Log.d("-----credentials-------",req.toString());
                String url = Constants.BASE_URL + Constants.Methods.ACHETER_QUITTANCE;
                result = restTemplate.postForObject(url, req, TransactionResponse.class);
                Log.d("-----RESPONSE------",result.toString());
            }
            else if (Constants.Methods.LOGIN.equals(service.getMethodName()))
            {
                Log.d("ServicesTask-REQ LOGIN-",service.getMethodName());
                HttpEntity<LoginRequest> credentials = new HttpEntity<>((LoginRequest) service.getMethodParams());
                Log.d("ServicesTaskcredentials",credentials.toString());
                String url = Constants.BASE_URL + Constants.Methods.LOGIN;
                result = restTemplate.postForObject(url, credentials, User.class);
                Log.d("ServicesTask---RESPONSE",result.toString());
            }
            else if (Constants.Methods.INSCRIRE.equals(service.getMethodName()))
            {
                Log.d("ServicesTask-INSCRIRE-",service.getMethodName());
                HttpEntity<InscriptionRequest> credentials = new HttpEntity<>((InscriptionRequest) service.getMethodParams());
                Log.d("ServicesTaskcredentials",credentials.toString());
                String url = Constants.BASE_URL + Constants.Methods.INSCRIRE;
                result = restTemplate.postForObject(url, credentials, User.class);
                Log.d("ServicesTask---RESPONSE",result.toString());
            }
            else if (Constants.Methods.ACTIVERCOMPTE.equals(service.getMethodName()))
            {
                Log.d("ServicesTask-INSCRIRE-",service.getMethodName());
                HttpEntity<InscriptionRequest> credentials = new HttpEntity<>((InscriptionRequest) service.getMethodParams());
                Log.d("ServicesTaskcredentials",credentials.toString());
                String url = Constants.BASE_URL + Constants.Methods.ACTIVERCOMPTE;
                result = restTemplate.postForObject(url, credentials, User.class);
                Log.d("ServicesTask---RESPONSE",result.toString());
            }
            else if (Constants.Methods.UPDATE_INFO.equals(service.getMethodName()))
            {
                Log.d("ServicesTask-UPDATE-",service.getMethodName());
                HttpEntity<InscriptionRequest> credentials = new HttpEntity<>((InscriptionRequest) service.getMethodParams());
                Log.d("ServicesTaskcredentials",credentials.toString());
                String url = Constants.BASE_URL + Constants.Methods.UPDATE_INFO;
                result = restTemplate.postForObject(url, credentials, User.class);
                Log.d("ServicesTask---RESPONSE",result.toString());
            }
            else if (Constants.Methods.UPDATE_PWD.equals(service.getMethodName()))
            {
                Log.d("ServicesTask-UPDATE-",service.getMethodName());
                HttpEntity<InscriptionRequest> credentials = new HttpEntity<>((InscriptionRequest) service.getMethodParams());
                Log.d("ServicesTaskcredentials",credentials.toString());
                String url = Constants.BASE_URL + Constants.Methods.UPDATE_PWD;
                result = restTemplate.postForObject(url, credentials, User.class);
                Log.d("ServicesTask---RESPONSE",result.toString());
            }
            else if (Constants.Methods.PWD_OUBLIE.equals(service.getMethodName()))
            {
                Log.d("ServicesTask-UPDATE-",service.getMethodName());
                HttpEntity<InscriptionRequest> credentials = new HttpEntity<>((InscriptionRequest) service.getMethodParams());
                Log.d("ServicesTaskcredentials",credentials.toString());
                String url = Constants.BASE_URL + Constants.Methods.PWD_OUBLIE;
                result = restTemplate.postForObject(url, credentials, User.class);
                Log.d("ServicesTask---RESPONSE",result.toString());
            }
            else if (Constants.Methods.NEW_PWD.equals(service.getMethodName()))
            {
                Log.d("ServicesTask-UPDATE-",service.getMethodName());
                HttpEntity<InscriptionRequest> credentials = new HttpEntity<>((InscriptionRequest) service.getMethodParams());
                Log.d("ServicesTaskcredentials",credentials.toString());
                String url = Constants.BASE_URL + Constants.Methods.NEW_PWD;
                result = restTemplate.postForObject(url, credentials, User.class);
                Log.d("ServicesTask---RESPONSE",result.toString());
            }
            else if (Constants.Methods.HISTORIQUE_ACHAT.equals(service.getMethodName()))
            {
                Log.d("ServicesTask-HISTORIQ-",service.getMethodName());
                HttpEntity<TransactionRequest> req = new HttpEntity<>((TransactionRequest) service.getMethodParams());
                Log.d("-----credentials-------",req.toString());
                String url = Constants.BASE_URL + Constants.Methods.HISTORIQUE_ACHAT;
                result = restTemplate.postForObject(url, req, TransactionResponse.class);
                Log.d("ServicesTask---RESPONSE",result.toString());
            }
            result.setMethod(service.getMethodName());
            return result;
        } catch (HttpClientErrorException e) {
            ObjectMapper om = new ObjectMapper();
            ServiceResult error;
            try {
                error= om.readValue(e.getResponseBodyAsByteArray(),ServiceResult.class);
                error.setMethod(service.getMethodName());
            } catch (IOException e1) {
                e1.printStackTrace();
                Log.d("API ERROR", e.getMessage());
                result = new ServiceResult(1212, "Erreur de connexion ");
                result.setMethod(service.getMethodName());
                return result;
            }
            e.printStackTrace();
            result.setMethod(service.getMethodName());
            return error;
        } catch (Exception e) {
            e.printStackTrace();
            result = new ServiceResult(1211, "Internal Error");
            result.setMethod(service.getMethodName());
            return result;
        }
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(currentActivity, R.style.diotaliProcessingStyle).create();

        Dialog.showProgressDialog(currentActivity, alertDialog, currentActivity.getString(R.string.prompt_connecting),
            currentActivity.getString(R.string.prompt_wait), false);

    }

    @Override
    protected void onPostExecute(ServiceResult serviceResult) {
        try {
            alertDialog.dismiss();
            Log.d(serviceResult.getMethod(),serviceResult.getMessage());
            currentActivity.sendTaskResponse(serviceResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
