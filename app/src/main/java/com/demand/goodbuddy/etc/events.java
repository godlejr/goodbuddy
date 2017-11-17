package com.demand.goodbuddy.etc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demand.goodbuddy.R;

import java.util.concurrent.atomic.AtomicIntegerArray;


/**
 * Created by Dean on 2/23/2016.
 */
public class events {

    static public void toastMessage(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
    static public void notYet(ImageView iv, final Context context, final int event){
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toast = "";
                if(event == 1) {toast = "로그인 후 이용 가능합니다.";
                //else if(event == 2) toast = "추후 업데이트 예정입니다.";
                Toast.makeText(context, toast, Toast.LENGTH_SHORT)
                        .show();}
            }
        });
    }


    static public void loadEvent(final FrameLayout fl_1, final FrameLayout fl_2, final Animation anim_1, final Animation anim_2, final Handler hd){
        fl_1.setVisibility(View.VISIBLE);
        fl_1.startAnimation(anim_1);
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                fl_1.startAnimation(anim_2);
                hd.removeCallbacksAndMessages(null);
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fl_1.setVisibility(View.GONE);
                        fl_2.setVisibility(View.VISIBLE);
                        fl_2.startAnimation(anim_1);
                        hd.removeCallbacksAndMessages(null);
                    }
                }, 1500);
            }
        }, 1500);

    }

    static public void textClickEvent(final TextView tv){
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    tv.setTextColor(Color.parseColor("#4cb3b6"));
                    tv.setPressed(true);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    tv.setTextColor(Color.parseColor("#232323"));
                    tv.setPressed(false);
                }

                return true;
            }
        });
    }

    static public void appearSlideEvent(ImageView iv, final FrameLayout fl_1, final FrameLayout fl_2, final LinearLayout ll, final Animation anim_1, final Animation anim_2){
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_1.setVisibility(View.VISIBLE);
                fl_2.startAnimation(anim_2);
                ll.startAnimation(anim_1);

            }
        });
    }

    static public void removeSlideEvent(final FrameLayout fl_1, final FrameLayout fl_2, final LinearLayout ll, final Handler hd, final Animation anim_1, final Animation anim_2){
        fl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.startAnimation(anim_1);
                fl_2.startAnimation(anim_2);
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fl_1.setVisibility(View.GONE);
                        hd.removeCallbacksAndMessages(null);
                    }
                }, 300);
            }
        });
    }


    static public void initMovePage(ImageView iv, final Intent intent, final String str, final Activity activity){
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMovePage(intent, str, activity);
            }
        });
    }

    static public void setMovePage(Intent intent, String str, Activity activity){
        intent.putExtra("id", str);
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.appear_from_right, R.anim.disappear_to_left);
    }

    static public void initViewLogOut(ImageView iv, final FrameLayout fl, final FrameLayout fl_1){
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_1.setVisibility(View.GONE);
                fl.setVisibility(View.VISIBLE);
            }
        });
    }
    static public void logoutEvent(TextView tv, final Activity activity, final Intent intent){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesOut(activity, intent);
            }
        });
    }
    static public void yesOut(Activity activity, Intent intent){
        activity.finish();
        activity.startActivity(intent);
    }
    static public void stayEvent(TextView tv, final FrameLayout fl){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noStay(fl);
            }
        });
    }
    static public void noStay(FrameLayout fl){
        fl.setVisibility(View.GONE);
    }
    static public void backPage(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.appear_from_left, R.anim.disappear_to_right);
    }
    static public void backClick(final Activity activity, ImageView iv){
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage(activity);
            }
        });
    }
    static public void assignBool(AtomicIntegerArray aA, int length){
        for(int i = 0; i < length; i++){
            aA.compareAndSet(i, 0, 1);
        }
    }

    static  public void dropContent(LinearLayout ll_1, final LinearLayout ll_2, final AtomicIntegerArray aA, final int layout_num, final ImageView iv){
        ll_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(""+aA, " ");
                if(aA.compareAndSet(layout_num, 0, 1)){
                    removeLayout(ll_2);
                    iv.setImageResource(R.drawable.closed);
                }
                else if(aA.compareAndSet(layout_num, 1, 0)){
                    showLayout((ll_2));
                    iv.setImageResource(R.drawable.opened);
                }
            }
        });
    }
    static  public void showLayout(LinearLayout ll){
        ll.setVisibility(View.VISIBLE);
    }
    static  public void removeLayout(LinearLayout ll){
        ll.setVisibility(View.GONE);
    }
    static public void applyDropContentAll(LinearLayout[] ll_1, final LinearLayout[] ll_2, ImageView[] iv, final AtomicIntegerArray aA, int length) {
        for (int i = 0; i < length; i++) {
            dropContent(ll_1[i], ll_2[i], aA, i, iv[i]);
        }
    }


    static public void noFinishMoveActivity(final Activity activity, final Intent intent){
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.appear_from_right, R.anim.disappear_to_left);
    }



    static public void keyBackEvent(Intent intent, Activity activity){
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.appear_from_left, R.anim.disappear_to_right);
    }


}
