package com.stpprojects.einscriptionslms.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.stpprojects.einscriptionslms.R;
import com.stpprojects.einscriptionslms.interfaces.AlertDialog_OnClickInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppUtil {
    private static final String LOG_TAG = AppUtil.class.getSimpleName();
    public static final String DeviceType = "android";

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    SessionManager sessionManager;
    Context context;

    public static void showToast(Context context, String strToastMessage, boolean isShownShort) {
        Toast toast;
        if (isShownShort) {
            toast = Toast.makeText(context, strToastMessage, Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.setBackgroundResource(R.drawable.toast_button);
            TextView text = (TextView) view.findViewById(android.R.id.message);
            text.setTextColor(Color.parseColor("#ffffff"));
            toast.show();
        }
        else
        toast = Toast.makeText(context, strToastMessage, Toast.LENGTH_LONG);
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.toast_button);
        TextView text = (TextView) view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#ffffff"));
        toast.show();
    }

    public static void showAlertDialogWith1Button(Context context, String messageToShowOnAlert, final AlertDialog_OnClickInterface
            mAlertDialog_OnClickListener, String buttonText, final String strTAG, boolean isCancellable) {

        if (context == null) {
            return;
        }
        AlertDialog.Builder alertDialog_builder = new AlertDialog.Builder(context);
        alertDialog_builder.setCancelable(isCancellable);
        alertDialog_builder.setMessage(messageToShowOnAlert);

        if (buttonText == null) {
            buttonText = context.getString(android.R.string.ok);
        }
        final String finalButtonText = buttonText;

        alertDialog_builder.setPositiveButton(buttonText, new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mAlertDialog_OnClickListener != null && strTAG != null) {
                            mAlertDialog_OnClickListener.onAlertDialogButtonClicked(
                                    finalButtonText, strTAG);
                        }


                    }
                });
        alertDialog_builder.show();
    }

    public static void openSetting(Context context) {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + context.getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myAppSettings);
    }


    public static Typeface getMontserratBlack(Context context) {
        Typeface RobotoSlabBold = Typeface.createFromAsset(context.getAssets(), "Montserrat-Black.ttf");
        return RobotoSlabBold;

    }

    public static Typeface getMontserratBlackItalic(Context context) {
        Typeface RobotoSlabRegular = Typeface.createFromAsset(context.getAssets(), "Montserrat-BlackItalic.ttf");
        return RobotoSlabRegular;
    }

    public static Typeface getMontserratBold(Context context) {
        Typeface SourceSansProBlack = Typeface.createFromAsset(context.getAssets(), "Montserrat-Bold.ttf");
        return SourceSansProBlack;
    }

    public static Typeface getMontserratBoldItalic(Context context) {
        Typeface SourceSansProBlackItalic = Typeface.createFromAsset(context.getAssets(), "Montserrat-BoldItalic.ttf");
        return SourceSansProBlackItalic;
    }


    /**
     * method to check if internet is connected or not
     *
     * @param mContext Context
     * @return true if connected else false
     */
    public static boolean isInternetAvailable(Context mContext) {

        if (mContext!=null){
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo wifiNetwork = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null && wifiNetwork.isConnected()) {
                return true;
            }

            NetworkInfo mobileNetwork = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null && mobileNetwork.isConnected()) {
                return true;
            }

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            //activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
            if (activeNetwork != null && activeNetwork.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static String getDeviceID(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return deviceId != null ? deviceId : "";
    }


    public static String getDensityName(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        if (density >= 4.0) {
            return "xxxhdpi";
        }
        if (density >= 3.0) {
            return "xxhdpi";
        }
        if (density >= 2.0) {
            return "xhdpi";
        }
        if (density >= 1.5) {
            return "hdpi";
        }
        if (density >= 1.0) {
            return "mdpi";
        }
        return "ldpi";
    }

    /**
     * check if Camera Permission is available to the app then returns camera Intent object
     *
     * @param activity
     * @param cameraPermissionIntentRequestCode
     * @return if permission is available then returns camera Intent object
     */
    public static Intent checkCameraPermission(Activity activity, int cameraPermissionIntentRequestCode) {
        //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    return takePictureIntent;

                } else {
                    if (activity.shouldShowRequestPermissionRationale(android.Manifest.permission.
                            CAMERA)) {
                        AppUtil.showToast(activity, "No Permission to use the Camera services", true);
                    }
                    activity.requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                            cameraPermissionIntentRequestCode);
                }

            } else {
                return takePictureIntent;
            }
        } else {
//            AppUtil.showAlertDialogWith1Button(activity, activity.getString(R.string.
//                    YourDeviceDoesntHaveASupportedCameraApp), null, null, LOG_TAG, false);
        }
        return null;
    }

    public static String saveBitmapOfImageToFile(Context context, Bitmap bitmapToSaveToFile) throws IOException {
        FileOutputStream out = null;
        String imageFileName = getANewImageFileName(context);
        try {
            out = new FileOutputStream(imageFileName);
            if (bitmapToSaveToFile.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                return imageFileName;
            }
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imageFileName;
    }

    private static String getANewImageFileName(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
//mCurrentPhotoPath:/storage/emulated/0/Android/data/trustid.interviewapp/files/Pictures/JPEG_20160727_223710_-1435487978.jpg
        return String.valueOf(image);
    }

    /**
     * Hide Soft Keyboard
     *
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(
                    Activity.INPUT_METHOD_SERVICE);
            View focusView = activity.getCurrentFocus();
            if (focusView != null) {
                inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
            }
        }
    }

    public static void dismissProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public static float roundFloat(float floatValue, int decimalPlaces) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(floatValue));
        bigDecimal = bigDecimal.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.floatValue();
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        AppLog.v(LOG_TAG, "getCurrentTime() timeFormat.format(calendar.getTime():" + timeFormat.format(calendar.getTime()));
        return timeFormat.format(calendar.getTime());
    }


    /**
     * Get IP address from first non-localhost interface
     *
     * @return address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac == null) return "";
                StringBuilder buf = new StringBuilder();
                for (int idx = 0; idx < mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));
                if (buf.length() > 0) buf.deleteCharAt(buf.length() - 1);
                return buf.toString();
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";

    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    //--------------------------deleteImageFromGallery-------------------------------------------------
    public static void deleteImageFromGallery(Activity context, String captureimageid) {
        Cursor c = null;
        Uri u = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, BaseColumns._ID + "=?", new String[]{captureimageid});

        String[] projection = {MediaStore.Images.ImageColumns.SIZE,
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATA, BaseColumns._ID,};

        try {
            if (u != null) {
                c = context.managedQuery(u, projection, null, null, null);
            }
            if ((c != null) && (c.moveToLast())) {
                ContentResolver cr = context.getContentResolver();
                int i = cr.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, BaseColumns._ID + "=" + c.getString(3), null);

            }
        } finally {/*
            if (c != null) {
                c.close();

            }
            c=null;*/
        }
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        //  final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        //final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}$";
        //final String PASSWORD_PATTERN2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$";
        final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static String gettime(String unixtime) {
        //long num = Long.parseLong(unixtime);
        int unitime1 = Integer.parseInt(unixtime);
        Date date1 = new Date(unitime1 * 1000L); // *1000 is to convert seconds to milliseconds
        //SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy  hh:mm a"); // the format of your date
        //Date date1 = new Date(num);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getDefault()); // give a timezone reference for formating (see comment at the bottom
        String formattedTime = sdf.format(date1);
        return formattedTime;
    }

    public static String getcurrentyear(String unixtime) {
        //long num = Long.parseLong(unixtime);
         int unitime1 = Integer.parseInt(unixtime);
        Date date1 = new Date(unitime1 * 1000L); // *1000 is to convert seconds to milliseconds
        //SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy  hh:mm a"); // the format of your date
        //Date date1 = new Date(num);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getDefault()); // give a timezone reference for formating (see comment at the bottom
        String formattedTime = sdf.format(date1);
        return formattedTime.substring(0,4);
    }

    public static String getcurrentmonth(String unixtime) {
        //long num = Long.parseLong(unixtime);
         int unitime1 = Integer.parseInt(unixtime);
        Date date1 = new Date(unitime1 * 1000L); // *1000 is to convert seconds to milliseconds
        //SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy  hh:mm a"); // the format of your date
       // Date date1 = new Date(num);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getDefault()); // give a timezone reference for formating (see comment at the bottom
        String formattedTime = sdf.format(date1);
        return formattedTime.substring(5,7);
    }

    public static String getcurrentday(String unixtime) {
        //long num = Long.parseLong(unixtime);
         int unitime1 = Integer.parseInt(unixtime);
        Date date1 = new Date(unitime1 * 1000L); // *1000 is to convert seconds to milliseconds
        //SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy  hh:mm a"); // the format of your date
        //Date date1 = new Date(num);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getDefault()); // give a timezone reference for formating (see comment at the bottom
        String formattedTime = sdf.format(date1);
        return formattedTime.substring(8,10);
    }

    public static String getTimeAgo(String datetime) {

        long time;
        String shortTimeStr;
        String dateontime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdf1 = new SimpleDateFormat("h:mm a");
        SimpleDateFormat sdfdate = new SimpleDateFormat("MMM dd yyyy");

        try {
            Date mDate = sdf.parse(datetime);
            time = mDate.getTime();

            shortTimeStr = sdf1.format(mDate);
            dateontime = sdfdate.format(mDate);
        } catch (ParseException e) {
            return "";
        }


        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            // time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "a second ago";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " min ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hr ago" + " at " + shortTimeStr;
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday" + " at " + shortTimeStr;
        } else {
            //return diff / DAY_MILLIS + " day ago" +" at "+shortTimeStr;
            return dateontime + " at " + shortTimeStr;
        }
    }

    public static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {

        AppLog.v("latlong", lat1 + "->" + lon1 + "->" + lat2 + "->" + lon2);

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        AppLog.v("diatcnce", "" + dist);
        DecimalFormat df2 = new DecimalFormat(".##");
        String parse = df2.format(dist);
        AppLog.v("parse", "yeh" + parse);
        String result = parse.replaceAll("[-+.^:,]", "");
        AppLog.v("parse", "yeh" + result);
        dist = Double.parseDouble(result);
        return (dist);
    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static String getCountOfDays(String createdDateString, String expireDateString) {


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date createdConvertedDate = null, expireCovertedDate = null, todayWithZeroTime = null;
        try {

            String cretedate = dateconvert(createdDateString);
            String enddate = dateconvert(expireDateString);

            createdConvertedDate = dateFormat.parse(cretedate);
            expireCovertedDate = dateFormat.parse(enddate);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int cYear = 0, cMonth = 0, cDay = 0;

        if (createdConvertedDate.after(todayWithZeroTime)) {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(createdConvertedDate);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);

        } else {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(todayWithZeroTime);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);
        }

        Calendar eCal = Calendar.getInstance();
        eCal.setTime(expireCovertedDate);

        int eYear = eCal.get(Calendar.YEAR);
        int eMonth = eCal.get(Calendar.MONTH);
        int eDay = eCal.get(Calendar.DAY_OF_MONTH);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.clear();
        date1.set(cYear, cMonth, cDay);
        date2.clear();
        date2.set(eYear, eMonth, eDay);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        return ("" + (int) dayCount);
    }

    private static String dateconvert(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getDefault());
        String newDate = "";
        try {
            Date dt = sdf.parse(date);

            SimpleDateFormat sd2 = new SimpleDateFormat("dd/MM/yyyy");
            newDate = sd2.format(dt);
            // System.out.println("hello->" + newDate);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;

    }

    public static String convertServerDateToUserTimeZone(String serverDate) {
        String ourdate;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(serverDate);

            TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM d, yyyy h:mm a", Locale.UK); //this format changeable
            dateFormatter.setTimeZone(timeZone);
            ourdate = dateFormatter.format(value);

            // TimeZone timeZone1 = TimeZone.getTimeZone("Asia/Kolkata");
            // SimpleDateFormat dateFormatter1 = new SimpleDateFormat("MMM d, yyyy h:mm a", Locale.UK); //this format changeable
            // dateFormatter1.setTimeZone(timeZone1);

            //  System.out.println("localAsia/Kolkata  " + dateFormatter1.format(value));


            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            ourdate = "0000-00-00 00:00:00";
        }
        return ourdate;
    }

    public static void showSnackbar(Context mcontext, final View coordinatorLayout, String strToastMessage, boolean isShownShort){
        Snackbar snackbar = null;
        final Snackbar finalSnackbar = snackbar;
        if(isShownShort){
            snackbar = Snackbar
                    .make(coordinatorLayout, strToastMessage, Snackbar.LENGTH_LONG)
                    .setAction(mcontext.getResources().getText(R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            /*Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                            snackbar1.show();*/
                        }
                    });

            snackbar.show();
        }else {
             snackbar = Snackbar
                    .make(coordinatorLayout, strToastMessage, Snackbar.LENGTH_SHORT)
                    .setAction(mcontext.getResources().getText(R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            /*Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                            snackbar1.show();*/
                        }
                    });

            snackbar.show();
        }

    }

    public static void showlogoutalert(final Context mContext) {
        final SessionManager sessionManager = new SessionManager(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(mContext.getResources().getString(R.string.session_expire_msg))
                .setCancelable(false)
                .setPositiveButton(mContext.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        sessionManager.logoutUser((Activity) mContext);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public static Point getDisplaySize(WindowManager windowManager) {
        try {
            if (Build.VERSION.SDK_INT > 16) {
                Display display = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                display.getMetrics(displayMetrics);
                return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
            } else {
                return new Point(0, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Point(0, 0);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}






