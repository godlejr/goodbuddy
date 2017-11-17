package com.demand.goodbuddy.etc;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 * Created by Dean on 2/18/2016.
 */
public class functions {

    static Typeface bold, light, medium, regular, thin;
    static DisplayMetrics metrics;

    static public int[] allParameter(int dpWidth, double width_ratio, double aspect_ratio, double mL, double mT, double mR, double mB) {

        int params[] = new int[6];

        int width;
        int height;
        int marginLeft;
        int marginTop;
        int marginRight;
        int marginBottom;

        width = (int) ((dpWidth) * width_ratio);
        height = (int) (width * aspect_ratio);

        marginLeft = (int) ((dpWidth) * mL);
        marginTop = (int) ((dpWidth) * mT);
        marginRight = (int) ((dpWidth) * mR);
        marginBottom = (int) ((dpWidth) * mB);

        params[0] = width;
        params[1] = height;
        params[2] = marginLeft;
        params[3] = marginTop;
        params[4] = marginRight;
        params[5] = marginBottom;


        return params;

    }

    static public void resize_view(View v, String layoutType, double wR, double aR, double mL, double mT, double mR, double mB) {

        RelativeLayout.LayoutParams rl_params;
        LinearLayout.LayoutParams ll_params;
        FrameLayout.LayoutParams fl_params;

        int params[];

        params = allParameter(dpWidth(), wR, aR, mL, mT, mR, mB);

        if (layoutType == "relative") {
            rl_params = (RelativeLayout.LayoutParams) v.getLayoutParams();
            if (wR != 0) rl_params.width = params[0];
            if (aR != 0) rl_params.height = params[1];
            rl_params.setMargins(params[2], params[3], params[4], params[5]);
            v.setLayoutParams(rl_params);
        } else if (layoutType == "linear") {
            ll_params = (LinearLayout.LayoutParams) v.getLayoutParams();
            if (wR != 0) ll_params.width = params[0];
            if (aR != 0) ll_params.height = params[1];
            ll_params.setMargins(params[2], params[3], params[4], params[5]);
            v.setLayoutParams(ll_params);
        } else if (layoutType == "frame") {
            fl_params = (FrameLayout.LayoutParams) v.getLayoutParams();
            if (wR != 0) fl_params.width = params[0];
            if (aR != 0) fl_params.height = params[1];
            fl_params.setMargins(params[2], params[3], params[4], params[5]);
            v.setLayoutParams(fl_params);
        }
    }



    static public void textForm(TextView tv, double tR, String font, Context c) {

        float textSize;

        textSize = (float) (dpWidth() * tR);

        if (tR != 0) tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        typeFace(tv, font, c);

    }

    static public void typeFace(TextView tv, String font, Context c){
        if (font == "bold") {
            bold = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Bold-Hestia.otf");
            tv.setTypeface(bold);
        } else if (font == "light") {
            light = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Light-Hestia.otf");
            tv.setTypeface(light);
        } else if (font == "regular") {
            regular = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Regular-Hestia.otf");
            tv.setTypeface(regular);
        } else if (font == "medium") {
            medium = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Medium-Hestia.otf");
            tv.setTypeface(medium);
        } else if (font == "thin") {
            thin = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Thin-Hestia.otf");
            tv.setTypeface(thin);
        }
    }

    static public int dpWidth(){

        int displayWidth;

        metrics = Resources.getSystem().getDisplayMetrics();
        displayWidth = metrics.widthPixels;

        return  displayWidth;
    }

    static public int dpHeight(){
        int displayHeight;

        metrics = Resources.getSystem().getDisplayMetrics();
        displayHeight = metrics.heightPixels;

        return displayHeight;

    }



    static public int randNum(){

        int num = (int)(Math.random()*6) + 1;

        return num;
    }

    static public void formTextView(String frontId, TextView[] tv, int length, double tR, String type, Activity activity, Context context){
        String resName;
        //v = new TextView[1];
        int viewId;
        for (int i = 0; i < length; i++){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());

            tv[i] = (TextView) activity.findViewById(viewId);

            textForm(tv[i], tR, type, context);
        }
    }




    static public void formEditText(String frontId, EditText[] et, int length, double tR, String type, Activity activity, Context context){
        String resName;
        //v = new TextView[1];
        int viewId;
        for (int i = 0; i < length; i++){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());

            et[i] = (EditText) activity.findViewById(viewId);

            textForm(et[i], tR, type, context);
        }
    }

    static public void formView(String frontId, View[] v, int length, Activity activity, String type, double wR, double aR, double mL, double mT, double mR, double mB){
        String resName;
        int viewId;
        for (int i = 0; i < length; i++){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());

            v[i] = activity.findViewById(viewId);

            resize_view(v[i], type, wR, aR, mL, mT, mR, mB);

        }
    }

    static public void resizeViewAll(View v[], int length, String type, double wR, double aR, double mL, double mT, double mR, double mB){
        for(int i = 0; i < length; i++){
            resize_view(v[i], type, wR, aR, mL, mT, mR, mB);
        }
    }


    static public void findView(String frontId, View[] v, int length, Activity activity){
        String resName;
        int viewId;
        for (int i = 0; i < length; i++){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());

            v[i] = activity.findViewById(viewId);

        }
    }

    static public void formViewWithPadding(String frontId, View[] v, int length, Activity activity, String type, double wR, double aR, double mL, double mT, double mR, double mB, double pL, double pT, double pR, double pB){
        String resName;
        int viewId;
        for (int i = 0; i < length; i++){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());

            v[i].findViewById(viewId);

            resize_view(v[i], type, wR, aR, mL, mT, mR, mB);

            viewPadding(v[i], pL, pT, pR, pB);

        }
    }

    static public void viewPadding(View v, double pL, double pT, double pR, double pB){
        int l, t, r, b;

        l = (int)(dpWidth()*pL);
        t = (int)(dpWidth()*pT);
        r = (int)(dpWidth()*pR);
        b = (int)(dpWidth()*pB);

        v.setPadding(l, t, r, b);
    }

    static public void drawLineGraph(){

    }
    static public void drawPlotGraph(){

    }

    ////////////////////////////Hyunin
    static public String getDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        date = sdf.format(cal.getTime());
        return date;
    }

//    static public String searchDate(String id, String tableName, String col, String result) {
//        PHPReader php = new PHPReader();
//        php.addVariable("id", id);
//        php.addVariable("table", tableName);
//        php.addVariable("col", col);
//        php.execute("http://1.234.63.165/goodBuddy/goodBuddy_searchDate.php");
//        try {
//            result = php.get().trim();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }



    static public void allocateText(TextView[] tv_1, TextView[] tv_2, TextView[] tv_3, int length_1, int length_2, int length_3, int sPointArr_1, int sPointArr_2, int sPointArr_3){
        for(int i = 0; i < length_1; i++){
            tv_1[i].setText(notes.category_1(sPointArr_1+i));
        }
        for(int j = 0; j < length_2; j++){
            tv_2[j].setText(notes.category_2(sPointArr_2+j));
        }
        for(int k = 0; k < length_3; k++){
            tv_3[k].setText(notes.category_3(sPointArr_3+k));
        }
    }

//    static public String searchDateEndData(String id, String tableName, String col, String result) {
//        PHPReader php = new PHPReader();
//        php.addVariable("id", id);
//        php.addVariable("table", tableName);
//        php.addVariable("col", col);
//        php.execute("http://1.234.63.165/admin/admin_searchAmount.php");
//        try {
//            result = php.get().trim();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }

//    static public String searchActEndData(String id, String tableName, String col, String result) {
//        PHPReader php = new PHPReader();
//        php.addVariable("id", id);
//        php.addVariable("table", tableName);
//        php.addVariable("col", col);
//        php.execute("http://1.234.63.165/goodBuddy/goodBuddy_searchActive.php");
//        try {
//            result = php.get().trim();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }

    /////////////////////////////////////////////0602
//    public static String searchInitData(String id, String tableName, String col, String result){
//        PHPReader php = new PHPReader();
//        php.addVariable("id", id);
//        php.addVariable("table", tableName);
//        php.addVariable("col", col);
//        php.execute("http://1.234.63.165/goodBuddy/goodBuddy_searchInit.php");
//
//        try{
//            result = php.get().trim();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return result;
//    }
}
