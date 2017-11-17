package com.demand.goodbuddy.etc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demand.goodbuddy.R;

/**
 * Created by Dean on 01/08/2016.
 */
public class ViewMethod {

    double targetWidth = 1080.;

    Typeface bold, light, medium, regular, thin, helvet;

    int dpWidth(){

        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();

        return metrics.widthPixels;
    }

    void typeFace(Context c, String font){

        switch(font){
            case "bold" :
                bold = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Bold-Hestia.otf");
                break;
            case "light" :
                light = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Light-Hestia.otf");
                break;
            case "regular" :
                regular = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Regular-Hestia.otf");
                break;
            case "medium" :
                medium = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Medium-Hestia.otf");
                break;
            case "thin" :
                thin = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Thin-Hestia.otf");
                break;
        }
    }

    void typeFace(Context c, TextView tv, String font){

        switch(font){
            case "bold" :
                bold = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Bold-Hestia.otf");
                tv.setTypeface(bold);
                break;
            case "light" :
                light = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Light-Hestia.otf");
                tv.setTypeface(light);
                break;
            case "regular" :
                regular = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Regular-Hestia.otf");
                tv.setTypeface(regular);
                break;
            case "medium" :
                medium = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Medium-Hestia.otf");
                tv.setTypeface(medium);
                break;
            case "thin" :
                thin = Typeface.createFromAsset(c.getAssets(), "NotoSansKR-Thin-Hestia.otf");
                tv.setTypeface(thin);
                break;
            case "helvet" :
                helvet = Typeface.createFromAsset(c.getAssets(), "HelveticaRounded-BoldCond.otf");
                tv.setTypeface(helvet);
                break;
            case "" :
                break;

        }
    }

    public int adjustedValue(int originalValue){

        int changedValue;

        changedValue = (int)(dpWidth()*(originalValue/targetWidth));

        return changedValue;
    }

    int[] viewSizeParameters(int width, int height){

        int params[] = new int[2];

        int resizedWidth;
        int resizedHeight;

        double aspectRatio = (height*1.)/(width*1.);

        resizedWidth = adjustedValue(width);
        resizedHeight = (int) (resizedWidth*(aspectRatio));

        params[0] = resizedWidth;
        params[1] = resizedHeight;

        Log.d("WIDTH", "" + params[0]);
        Log.d("HEIGHT", "" + params[1]);

        return params;
    }

    int[] viewAdditionalParameters(int left, int top, int right, int bottom){

        int params[] = new int[4];

        int adjustedLeft;
        int adjustedTop;
        int adjustedRight;
        int adjustedBottom;

        adjustedLeft = adjustedValue(left);
        adjustedTop = adjustedValue(top);
        adjustedRight = adjustedValue(right);
        adjustedBottom = adjustedValue(bottom);

        params[0] = adjustedLeft;
        params[1] = adjustedTop;
        params[2] = adjustedRight;
        params[3] = adjustedBottom;

        Log.d("LEFT", ""+params[0]);
        Log.d("TOP", ""+params[1]);
        Log.d("RIGHT", ""+params[2]);
        Log.d("BOTTOM", ""+params[3]);

        return params;
    }

    public void findView(String frontId, View[] v, int length, Activity activity){
        String resName;
        int viewId;
        for (int i = 0; i < length; i++){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());

            v[i] = activity.findViewById(viewId);
        }
    }

    public void resizeSingleView(View v, String layoutType, int width, int height){

        LinearLayout.LayoutParams llParams;
        FrameLayout.LayoutParams flParams;
        RelativeLayout.LayoutParams rlParams;

        int size[];

        size = viewSizeParameters(width, height);

        switch(layoutType){

            case "linear" :
                llParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                if(width != 0) llParams.width = size[0];
                if(height != 0) llParams.height = size[1];
                v.setLayoutParams(llParams);
                break;

            case "frame" :
                flParams = (FrameLayout.LayoutParams) v.getLayoutParams();
                if(width != 0) flParams.width = size[0];
                if(height != 0) flParams.height = size[1];
                v.setLayoutParams(flParams);
                break;

            case "relative" :
                rlParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                if(width != 0) rlParams.width = size[0];
                if(height != 0) rlParams.height = size[1];
                v.setLayoutParams(rlParams);
                break;
        }
    }

    public void resizeSingleView(View v, String layoutType, int width, int height,
                          int marginLeft, int marginTop, int marginRight, int marginBottom){

        LinearLayout.LayoutParams llParams;
        FrameLayout.LayoutParams flParams;
        RelativeLayout.LayoutParams rlParams;

        int size[];
        int margin[];

        size = viewSizeParameters(width, height);
        margin = viewAdditionalParameters(marginLeft, marginTop, marginRight, marginBottom);

        switch(layoutType){

            case "linear" :
                llParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                if(width != 0) llParams.width = size[0];
                if(height != 0) llParams.height = size[1];
                llParams.setMargins(margin[0], margin[1], margin[2], margin[3]);
                v.setLayoutParams(llParams);
                break;

            case "frame" :
                flParams = (FrameLayout.LayoutParams) v.getLayoutParams();
                if(width != 0) flParams.width = size[0];
                if(height != 0) flParams.height = size[1];
                flParams.setMargins(margin[0], margin[1], margin[2], margin[3]);
                v.setLayoutParams(flParams);
                break;

            case "relative" :
                rlParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                if(width != 0) rlParams.width = size[0];
                if(height != 0) rlParams.height = size[1];
                rlParams.setMargins(margin[0], margin[1], margin[2], margin[3]);
                v.setLayoutParams(rlParams);
                break;
        }
    }

    public void resizeSingleView(View v, String layoutType, int width, int height,
                          int marginLeft, int marginTop, int marginRight, int marginBottom,
                          int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {

        int padding[];

        padding = viewAdditionalParameters(paddingLeft, paddingTop, paddingRight, paddingBottom);

        resizeSingleView(v, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom);

        v.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public void resizeMultipleView(Activity activity, int[] index, String frontId, View[] v, String layoutType, int width, int height){

        int viewId;
        String resName;

        for(int i : index){

            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            v[i] = activity.findViewById(viewId);
            resizeSingleView(v[i], layoutType, width, height);
        }
    }

    public void resizeMultipleView(Activity activity, int[] indexes, String frontId, View[] v, String layoutType, int width, int height,
                                   int marginLeft, int marginTop, int marginRight, int marginBottom){
        int viewId;
        String resName;

        for(int i : indexes){

            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            v[i] = activity.findViewById(viewId);
            resizeSingleView(v[i], layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom);
        }
    }

    public void resizeMultipleView(Activity activity, int[] indexes, String frontId, View[] v, String layoutType, int width, int height,
                                   int marginLeft, int marginTop, int marginRight, int marginBottom,
                                   int paddingLeft, int paddingTop, int paddingRight, int paddingBottom){
        int viewId;
        String resName;

        for(int i : indexes){

            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            v[i] = activity.findViewById(viewId);
            resizeSingleView(v[i], layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom, paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    public void reformSingleTextBasedView(Context c, TextView tv, int fontSize, String font){

        float size;

        size = (float) (dpWidth() * (fontSize/targetWidth));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        typeFace(c, tv, font);

    }

    public void reformSingleTextBasedView(Context c, TextView tv, int fontSize, String font,
                                       String layoutType, int width, int height){

        reformSingleTextBasedView(c, tv, fontSize, font);

        resizeSingleView(tv, layoutType, width, height);
    }

    public void reformSingleTextBasedView(Context c, TextView tv, int fontSize, String font,
                                     String layoutType, int width, int height,
                                     int marginLeft, int marginTop, int marginRight, int marginBottom){

        reformSingleTextBasedView(c, tv, fontSize, font);

        resizeSingleView(tv, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom);
    }

    public void reformSingleTextBasedView(Context c, TextView tv, int fontSize, String font,
                                     String layoutType, int width, int height,
                                     int marginLeft, int marginTop, int marginRight, int marginBottom,
                                     int paddingLeft, int paddingTop, int paddingRight, int paddingBottom){

        reformSingleTextBasedView(c, tv, fontSize, font);

        resizeSingleView(tv, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom, paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public void reformMultipleTextView(Activity activity, Context c, String frontId, TextView[] tv, int[] index, int fontSize, String font){

        String resName;

        int viewId;

        for(int i : index){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            tv[i] = (TextView) activity.findViewById(viewId);
            reformSingleTextBasedView(c, tv[i], fontSize, font);
        }
    }

    public void reformMultipleTextView(Activity activity, Context c, String frontId, TextView[] tv, int[] index, int fontSize, String font,
                                       String layoutType, int width, int height){

        String resName;

        int viewId;

        for(int i : index){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            tv[i] = (TextView) activity.findViewById(viewId);
            reformSingleTextBasedView(c, tv[i], fontSize, font, layoutType, width, height);
        }
    }

    public void reformMultipleTextView(Activity activity, Context c, String frontId, TextView[] tv, int[] index, int fontSize, String font,
                                       String layoutType, int width, int height,
                                       int marginLeft, int marginTop, int marginRight, int marginBottom){

        String resName;

        int viewId;

        for(int i : index){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            tv[i] = (TextView) activity.findViewById(viewId);
            reformSingleTextBasedView(c, tv[i], fontSize, font, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom);
        }
    }

    public void reformMultipleTextView(Activity activity, Context c, String frontId, TextView[] tv, int[] index, int fontSize, String font,
                                       String layoutType, int width, int height,
                                       int marginLeft, int marginTop, int marginRight, int marginBottom,
                                       int paddingLeft, int paddingTop, int paddingRight, int paddingBottom){

        String resName;

        int viewId;

        for(int i : index){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            tv[i] = (TextView) activity.findViewById(viewId);
            reformSingleTextBasedView(c, tv[i], fontSize, font, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom, paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    public void reformMultipleTextView(Activity activity, Context c, String frontId, EditText[] et, int[] index, int fontSize, String font){

        String resName;

        int viewId;

        for(int i : index){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            et[i] = (EditText) activity.findViewById(viewId);
            reformSingleTextBasedView(c, et[i], fontSize, font);
        }
    }

    public void reformMultipleTextView(Activity activity, Context c, String frontId, EditText[] et, int[] index, int fontSize, String font,
                                       String layoutType, int width, int height){

        String resName;

        int viewId;

        for(int i : index){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            et[i] = (EditText) activity.findViewById(viewId);
            reformSingleTextBasedView(c, et[i], fontSize, font, layoutType, width, height);
        }
    }

    public void reformMultipleTextView(Activity activity, Context c, String frontId, EditText[] et, int[] index, int fontSize, String font,
                                       String layoutType, int width, int height,
                                       int marginLeft, int marginTop, int marginRight, int marginBottom){

        String resName;

        int viewId;

        for(int i : index){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            et[i] = (EditText) activity.findViewById(viewId);
            reformSingleTextBasedView(c, et[i], fontSize, font, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom);
        }
    }

    public void reformMultipleTextView(Activity activity, Context c, String frontId, EditText[] et, int[] index, int fontSize, String font,
                                       String layoutType, int width, int height,
                                       int marginLeft, int marginTop, int marginRight, int marginBottom,
                                       int paddingLeft, int paddingTop, int paddingRight, int paddingBottom){

        String resName;

        int viewId;

        for(int i : index){
            resName = frontId + i;
            viewId = activity.getResources().getIdentifier(resName, "id", activity.getPackageName());
            et[i] = (EditText) activity.findViewById(viewId);
            reformSingleTextBasedView(c, et[i], fontSize, font, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom, paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }
    public void reformSingleTextBasedViewWithDifferentTextSize(TextView tv, String str, int absSize_0, int absSize_1, int startPoint_0, int endPoint_0, int startPoint_1, int endPoint_1,
                                                               Context c, String font, String layoutType, int width, int height){
        SpannableString sstr = new SpannableString(str);
        sstr.setSpan(new AbsoluteSizeSpan(absSize_0), startPoint_0, endPoint_0, 0);
        sstr.setSpan(new AbsoluteSizeSpan(absSize_1), startPoint_1, endPoint_1, 0);
        tv.setText(sstr);
        typeFace(c, tv, font);
        resizeSingleView(tv, layoutType, width, height);
    }

    public void reformSingleTextBasedViewWithDifferentTextSize(TextView tv, String str, int absSize_0, int absSize_1, int startPoint_0, int endPoint_0, int startPoint_1, int endPoint_1,
                                                               Context c, String font, String layoutType, int width, int height, int marginLeft, int marginTop, int marginRight, int marginBottom){
        SpannableString sstr = new SpannableString(str);
        sstr.setSpan(new AbsoluteSizeSpan(absSize_0), startPoint_0, endPoint_0, 0);
        sstr.setSpan(new AbsoluteSizeSpan(absSize_1), startPoint_1, endPoint_1, 0);
        tv.setText(sstr);

        typeFace(c, tv, font);
        resizeSingleView(tv, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom);
    }

    public void reformSingleTextBasedViewWithDifferentTextSize(TextView tv, String str, int absSize_0, int absSize_1, int startPoint_0, int endPoint_0, int startPoint_1, int endPoint_1,
                                                               Context c, Activity activity, String font, String layoutType, int width, int height, int marginLeft, int marginTop, int marginRight, int marginBottom,
                                                               int paddingLeft, int paddingTop, int paddingRight, int paddingBottom){
        SpannableString sstr = new SpannableString(str);
        sstr.setSpan(new AbsoluteSizeSpan(absSize_0), startPoint_0, endPoint_0, 0);
        sstr.setSpan(new AbsoluteSizeSpan(absSize_1), startPoint_1, endPoint_1, 0);
        //sstr.setSpan(new TypefaceSpan(this, "NotoSansKR-Medium-Hestia.otf"), startPoint_0, endPoint_0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(sstr);


        typeFace(c, tv, font);
        resizeSingleView(tv, layoutType, width, height, marginLeft, marginTop, marginRight, marginBottom, paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public void textFocus(TextView tv, int status){
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_pressed}, // pressed
                new int[] { android.R.attr.state_focused},
                new int[] { android.R.attr.state_enabled}
        };

        if(status == 0) {
            int[] colors = new int[]{
                    Color.parseColor("#ffffff"),
                    Color.parseColor("#585858"),
                    Color.parseColor("#585858")
            };

            ColorStateList list = new ColorStateList(states, colors);
            tv.setTextColor(list);
        }
        else{
            int[] colors = new int[]{
                    Color.parseColor("#585858"),
                    Color.parseColor("#ffffff"),
                    Color.parseColor("#ffffff")
            };

            ColorStateList list = new ColorStateList(states, colors);
            tv.setTextColor(list);
        }
    }

    public void imageClickEffect(final View v, final ImageView iv, final Class cls, final Activity activity, final String pagetitle, final String id){

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN :
                        iv.getDrawable().setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case MotionEvent.ACTION_UP :
                        iv.getDrawable().clearColorFilter();
                        iv.invalidate();
                        //nextActivity(cls);
                        Intent intent = new Intent(activity, cls);
                        intent.putExtra("pagetitle", pagetitle);
                        intent.putExtra("id", id);
                        activity.startActivity(intent);
                        activity.finish();
                        activity.overridePendingTransition(R.anim.appear_from_right, R.anim.disappear_to_left);
                        break;
                    case MotionEvent.ACTION_CANCEL :
                        iv.getDrawable().clearColorFilter();
                        iv.invalidate();
                        break;
                }
                return true;
            }
        });
    }

    public void imageClickEffect(final ImageView iv, final Class cls, final Activity activity, final String pagetitle, final String id){

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN :
                        iv.getDrawable().setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case MotionEvent.ACTION_UP :
                        iv.getDrawable().clearColorFilter();
                        iv.invalidate();
                        //nextActivity(cls);
                        Intent intent = new Intent(activity, cls);
                        intent.putExtra("pagetitle", pagetitle);
                        intent.putExtra("id", id);
                        activity.startActivity(intent);
                        activity.finish();
                        activity.overridePendingTransition(R.anim.appear_from_left, R.anim.disappear_to_right);
                        break;
                    case MotionEvent.ACTION_CANCEL :
                        iv.getDrawable().clearColorFilter();
                        iv.invalidate();
                        break;
                }
                return true;
            }
        });
    }

    public void imageClickEffect(final ImageView iv, final Class cls, final Activity activity, final String id){

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN :
                        iv.getDrawable().setColorFilter(Color.parseColor("#EF5350"), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case MotionEvent.ACTION_UP :
                        iv.getDrawable().clearColorFilter();
                        iv.invalidate();
                        //nextActivity(cls);
                        Intent intent = new Intent(activity, cls);
                        intent.putExtra("id", id);
                        activity.startActivity(intent);
                        activity.finish();
                        activity.overridePendingTransition(R.anim.appear_from_right, R.anim.disappear_to_left);
                        break;
                    case MotionEvent.ACTION_CANCEL :
                        iv.getDrawable().clearColorFilter();
                        iv.invalidate();
                        break;
                }
                return true;
            }
        });
    }
}