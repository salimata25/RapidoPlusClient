package sn.diotali.rapido_plus_usager.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import sn.diotali.rapido_plus_usager.R;

public class DiotaliUtils {

    public static void showProgressDialog(final Context context, final AlertDialog alertDialog, String title, String message, boolean hasCancel) {

        View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        if (message != null) {
            final TextView messageTv = view.findViewById(R.id.progressbar_msg);
            messageTv.setText(message);
        }

        if (alertDialog == null) {
            return;
        }

        alertDialog.setTitle(title);
        if (view != null) {
            alertDialog.setView(view);
        }
        alertDialog.setCancelable(false);
        if (hasCancel) {
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.dialog_cancel),
                    (dialog, which) -> alertDialog.cancel());
        }
        alertDialog.show();
    }

    public static  void displayInfoDialog(final Context context,String message){
         AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage(message);
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            (dialog, id) -> dialog.cancel());
                    builder1.show();
    }

    public static String hashPassword(String login,String password) {

        try {
            MessageDigest msgDiges = MessageDigest.getInstance("MD5");
            String cred = login+":"+ password;
            String digestPassword =Base64.encodeToString(msgDiges.digest(cred.getBytes(Charset.forName("UTF-8"))),Base64.DEFAULT);
            return digestPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void loggerView(String classStr, String logString){
        Log.e(classStr +": ", logString);
    }

    private static String[] binaryArray = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111" };

    private static String hexStr = "0123456789ABCDEF";

    public static byte[] decodeBitmap(Bitmap bmp){
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();

        List<String> list = new ArrayList<>(); //binaryString list
        StringBuffer sb;


        int bitLen = bmpWidth / 8;
        int zeroCount = bmpWidth % 8;

        String zeroStr = "";
        if (zeroCount > 0) {
            bitLen = bmpWidth / 8 + 1;
            for (int i = 0; i < (8 - zeroCount); i++) {
                zeroStr = zeroStr + "0";
            }
        }

        for (int i = 0; i < bmpHeight; i++) {
            sb = new StringBuffer();
            for (int j = 0; j < bmpWidth; j++) {
                int color = bmp.getPixel(j, i);

                int r = (color >> 16) & 0xff;
                int g = (color >> 8) & 0xff;
                int b = color & 0xff;

                // if color close to white，bit='0', else bit='1'
                if (r > 160 && g > 160 && b > 160)
                    sb.append("0");
                else
                    sb.append("1");
            }
            if (zeroCount > 0) {
                sb.append(zeroStr);
            }
            list.add(sb.toString());
        }

        List<String> bmpHexList = binaryListToHexStringList(list);
        String commandHexString = "1D763000";
        String widthHexString = Integer
                .toHexString(bmpWidth % 8 == 0 ? bmpWidth / 8
                        : (bmpWidth / 8 + 1));
        if (widthHexString.length() > 2) {
            Log.e("decodeBitmap error", " width is too large");
            return null;
        } else if (widthHexString.length() == 1) {
            widthHexString = "0" + widthHexString;
        }
        widthHexString = widthHexString + "00";

        String heightHexString = Integer.toHexString(bmpHeight);
        if (heightHexString.length() > 2) {
            Log.e("decodeBitmap error", " height is too large");
            return null;
        } else if (heightHexString.length() == 1) {
            heightHexString = "0" + heightHexString;
        }
        heightHexString = heightHexString + "00";

        List<String> commandList = new ArrayList<String>();
        commandList.add(commandHexString+widthHexString+heightHexString);
        commandList.addAll(bmpHexList);

        return hexList2Byte(commandList);
    }

    public static List<String> binaryListToHexStringList(List<String> list) {
        List<String> hexList = new ArrayList<String>();
        for (String binaryStr : list) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < binaryStr.length(); i += 8) {
                String str = binaryStr.substring(i, i + 8);

                String hexString = myBinaryStrToHexString(str);
                sb.append(hexString);
            }
            hexList.add(sb.toString());
        }
        return hexList;
    }

    public static String myBinaryStrToHexString(String binaryStr) {
        String hex = "";
        String f4 = binaryStr.substring(0, 4);
        String b4 = binaryStr.substring(4, 8);
        for (int i = 0; i < binaryArray.length; i++) {
            if (f4.equals(binaryArray[i]))
                hex += hexStr.substring(i, i + 1);
        }
        for (int i = 0; i < binaryArray.length; i++) {
            if (b4.equals(binaryArray[i]))
                hex += hexStr.substring(i, i + 1);
        }

        return hex;
    }

    public static byte[] hexList2Byte(List<String> list) {
        List<byte[]> commandList = new ArrayList<byte[]>();

        for (String hexStr : list) {
            commandList.add(hexStringToBytes(hexStr));
        }
        byte[] bytes = sysCopy(commandList);
        return bytes;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static byte[] sysCopy(List<byte[]> srcArrays) {
        int len = 0;
        for (byte[] srcArray : srcArrays) {
            len += srcArray.length;
        }
        byte[] destArray = new byte[len];
        int destLen = 0;
        for (byte[] srcArray : srcArrays) {
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }
        return destArray;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String byteToString(byte[] b)  {

        char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }

        return sb.toString();
    }

    public static String  encryptData(String key , String data){

        try {
            MessageDigest msgDiges =  MessageDigest.getInstance("MD5");
            byte [] keyb = Arrays.copyOf(msgDiges.digest(key.getBytes(Charset.forName("UTF-8"))),24);
            System.arraycopy(keyb, 0, keyb,16, 8);
            final SecretKey secKey = new SecretKeySpec(keyb, "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secKey, iv);
            final byte[] encryptText = cipher.doFinal(data.getBytes(Charset.forName("UTF-8")));

            return Base64.encodeToString(encryptText,Base64.DEFAULT);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String  decryptData(String key, String data){

        try {
            MessageDigest msgDiges =  MessageDigest.getInstance("MD5");
            byte [] keyb = Arrays.copyOf(msgDiges.digest(key.getBytes(Charset.forName("UTF-8"))),24);
            System.arraycopy(keyb, 0, keyb,16, 8);
            System.out.println("keyL ="+keyb.length);
            final SecretKey secKey = new SecretKeySpec(keyb, "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

            decipher.init(Cipher.DECRYPT_MODE, secKey, iv);
            final byte[] plain = decipher.doFinal(Base64.decode(data,Base64.DEFAULT));

            return new String(plain, Charset.forName("UTF-8"));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return The device's serial number, visible to the user in {@code Settings > About phone/tablet/device > Status
     * > Serial number}, or {@code null} if the serial number couldn't be found
     */
    public static String getSerialNumber() {
        String serialNumber;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);

            // (?) Lenovo Tab (https://stackoverflow.com/a/34819027/1276306)
            serialNumber = (String) get.invoke(c, "gsm.sn1");

            if (serialNumber.equals(""))
                // Samsung Galaxy S5 (SM-G900F) : 6.0.1
                // Samsung Galaxy S6 (SM-G920F) : 7.0
                // Samsung Galaxy Tab 4 (SM-T530) : 5.0.2
                // (?) Samsung Galaxy Tab 2 (https://gist.github.com/jgold6/f46b1c049a1ee94fdb52)
                serialNumber = (String) get.invoke(c, "ril.serialnumber");

            if (serialNumber.equals(""))
                // Archos 133 Oxygen : 6.0.1
                // Google Nexus 5 : 6.0.1
                // Hannspree HANNSPAD 13.3" TITAN 2 (HSG1351) : 5.1.1
                // Honor 5C (NEM-L51) : 7.0
                // Honor 5X (KIW-L21) : 6.0.1
                // Huawei M2 (M2-801w) : 5.1.1
                // (?) HTC Nexus One : 2.3.4 (https://gist.github.com/tetsu-koba/992373)
                serialNumber = (String) get.invoke(c, "ro.serialno");

            if (serialNumber.equals(""))
                // (?) Samsung Galaxy Tab 3 (https://stackoverflow.com/a/27274950/1276306)
                serialNumber = (String) get.invoke(c, "sys.serialnumber");

            if (serialNumber.equals(""))
                // Archos 133 Oxygen : 6.0.1
                // Hannspree HANNSPAD 13.3" TITAN 2 (HSG1351) : 5.1.1
                // Honor 9 Lite (LLD-L31) : 8.0
                // Xiaomi Mi 8 (M1803E1A) : 8.1.0
                serialNumber = Build.SERIAL;

            // If none of the methods above worked
            if (serialNumber.equals(""))
                serialNumber = null;
        } catch (Exception e) {
            e.printStackTrace();
            serialNumber = null;
        }

        return serialNumber;
    }

}
