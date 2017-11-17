package com.demand.goodbuddy.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.demand.goodbuddy.dto.User;

/**
 * Created by Dev-0 on 2017-04-12.
 */

public class PreferenceUtil {
    private Context context;

    private SharedPreferences sharedUserPreferences;
    private SharedPreferences sharedBadgePreferences;
    private SharedPreferences sharedPedometerPreferences;
    private SharedPreferences sharedRecommendPreferences;

    public PreferenceUtil(Context context) {
        this.context = context;
    }


    public SharedPreferences getUserSharedPreferences() {
        if (sharedUserPreferences == null) {
            sharedUserPreferences = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        }
        return sharedUserPreferences;
    }

    public SharedPreferences getBadgeSharePreference() {
        if (sharedBadgePreferences == null) {
            sharedBadgePreferences = context.getSharedPreferences("badge", Context.MODE_PRIVATE);
        }

        return sharedBadgePreferences;
    }

    public SharedPreferences getPedomterSharedPreferences() {
        if (sharedPedometerPreferences == null) {
            sharedPedometerPreferences = context.getSharedPreferences("pedometer", Context.MODE_PRIVATE);
        }
        return sharedPedometerPreferences;
    }

    public SharedPreferences getRecommendSharedPreferences() {
        if (sharedRecommendPreferences == null) {
            sharedRecommendPreferences = context.getSharedPreferences("recommend", Context.MODE_PRIVATE);
        }
        return sharedRecommendPreferences;
    }

    public void setAverageActive(Float active) {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();
        SharedPreferences.Editor editor = recommendInfo.edit();

        editor.putFloat("active", active);
        editor.apply();

        Log.e("추천", active + "");
    }

    public void setAverageSugar(Float sugar) {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();
        SharedPreferences.Editor editor = recommendInfo.edit();

        editor.putFloat("sugar", sugar);
        editor.apply();

        Log.e("추천", sugar + "");
    }

    public void setAverageMaxPressure(Float maxPressure) {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();
        SharedPreferences.Editor editor = recommendInfo.edit();

        editor.putFloat("maxPressure", maxPressure);
        editor.apply();

        Log.e("추천", maxPressure + "");
    }

    public void setAverageMinPressure(Float minPressure) {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();
        SharedPreferences.Editor editor = recommendInfo.edit();

        editor.putFloat("minPressure", minPressure);
        editor.apply();

        Log.e("추천", minPressure + "");
    }

    public void setAverageBmi(Float bmi) {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();
        SharedPreferences.Editor editor = recommendInfo.edit();

        editor.putFloat("bmi", bmi);
        editor.apply();

        Log.e("추천", bmi + "");
    }

    public void setAverageDiet(Float diet) {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();
        SharedPreferences.Editor editor = recommendInfo.edit();

        editor.putFloat("diet", diet);
        editor.apply();

        Log.e("추천", diet + "");
    }

    public Float getAverageActive() {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();

        return recommendInfo.getFloat("active", 0);
    }

    public Float getAverageSugar() {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();

        return recommendInfo.getFloat("sugar", 0);
    }

    public Float getAverageMaxPressure() {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();

        return recommendInfo.getFloat("maxPressure", 0);
    }

    public Float getAverageMinPressure() {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();

        return recommendInfo.getFloat("minPressure", 0);
    }


    public Float getAverageBmi() {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();

        return recommendInfo.getFloat("bmi", 0);
    }

    public Float getAverageDiet() {
        SharedPreferences recommendInfo = getRecommendSharedPreferences();

        return recommendInfo.getFloat("diet", 0);
    }



    public void setPedometer(int count) {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();
        SharedPreferences.Editor editor = pedometerInfo.edit();

        editor.putInt("count", count);
        editor.apply();

        Log.e("만보기", count + "");
    }

    public void setPedometerDate(String date) {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();
        SharedPreferences.Editor editor = pedometerInfo.edit();

        editor.putString("date", date);
        editor.apply();

        Log.e("만보기 date", date);
    }

    public String getPedometerDate() {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();

        return pedometerInfo.getString("date", null);
    }

    public void setPedometerPlay(boolean isPlaying) {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();
        SharedPreferences.Editor editor = pedometerInfo.edit();

        editor.putBoolean("isPlaying", isPlaying);
        editor.apply();

        Log.e("만보기 isPlaying", isPlaying + "");
    }

    public boolean getPedometerPlay() {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();

        return pedometerInfo.getBoolean("isPlaying", false);
    }

    public void setPedometerFirst(boolean isFirst) {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();
        SharedPreferences.Editor editor = pedometerInfo.edit();

        editor.putBoolean("isFirst", isFirst);
        editor.apply();

        Log.e("만보기 isFirst", isFirst + "");
    }

    public boolean getPedometerisFirst() {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();
        return pedometerInfo.getBoolean("isFirst", true);
    }

    public int getPedometer() {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();

        return pedometerInfo.getInt("count", 0);
    }


    public void setPedometerSpeed(int level) {
        int speed;
        if (level > 7) {
            speed = 505;
        } else if (level > 6) {
            speed = 525;
        } else if (level > 5) {
            speed = 555;
        } else if (level > 4) {
            speed = 575;
        } else if (level > 3) {
            speed = 595;
        } else if (level > 2) {
            speed = 615;
        } else if (level > 1) {
            speed = 635;
        } else if (level > 0) {
            speed = 655;
        } else {
            speed = 675;
        }

        SharedPreferences pedometerInfo = getPedomterSharedPreferences();
        SharedPreferences.Editor editor = pedometerInfo.edit();

        editor.putInt("speed", speed);
        editor.putInt("level", level);
        editor.apply();

        Log.e("만보기 속도", speed + "");
    }


    public int getPedometerSpeed() {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();
        return pedometerInfo.getInt("speed", 595);
    }

    public int getPedometerLevel() {
        SharedPreferences pedometerInfo = getPedomterSharedPreferences();
        return pedometerInfo.getInt("level", 4);
    }

    public User getUserInfo() {
        User user = new User();
        SharedPreferences userInfo = getUserSharedPreferences();
        user.setId(userInfo.getInt("id", 0));
        user.setLevel(userInfo.getInt("level", 0));
        user.setName(userInfo.getString("name", null));
        user.setEmail(userInfo.getString("email", null));
        user.setBirth(userInfo.getString("birth", null));
        user.setAvatar(userInfo.getString("avatar", null));
        user.setAccessToken(userInfo.getString("accessToken", null));
        user.setDeviceId(userInfo.getString("deviceId", null));
        user.setGender(userInfo.getInt("gender", 0));
        user.setFcmToken(userInfo.getString("fcmToken", null));
        user.setAdminId(userInfo.getInt("adminId", 0));

        return user;
    }

    public void setUserInfo(User user, String deviceId, String token, String accessToken) {
        SharedPreferences userInfo = getUserSharedPreferences();
        SharedPreferences.Editor editor = userInfo.edit();
        editor.putInt("id", user.getId());
        editor.putInt("level", user.getLevel());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("birth", user.getBirth());
        editor.putString("avatar", user.getAvatar());
        editor.putString("accessToken", accessToken);
        editor.putString("deviceId", deviceId);
        editor.putInt("gender", user.getGender());
        editor.putString("token", token);
        editor.putInt("adminId", user.getAdminId());

        editor.commit();
    }

    public void removeUserInfo() {
        SharedPreferences userInfo = getUserSharedPreferences();
        SharedPreferences.Editor editor = userInfo.edit();
        editor.remove("id");
        editor.remove("level");
        editor.remove("name");
        editor.remove("email");
        editor.remove("birth");
        editor.remove("avatar");
        editor.remove("accessToken");
        editor.remove("deviceId");
        editor.remove("gender");
        editor.remove("token");

        editor.commit();
    }

    public int getBadgeCount() {
        SharedPreferences badgeInfo = getBadgeSharePreference();
        int badgeCount = badgeInfo.getInt("badgeCount", 0);

        return badgeCount;
    }

    public void setBadgeCount(int count) {
        SharedPreferences badgeInfo = getBadgeSharePreference();
        SharedPreferences.Editor editor = badgeInfo.edit();

        editor.putInt("badgeCount", count);
        editor.apply();
    }

    public boolean getNotificationCheck() {
        SharedPreferences userInfo = getUserSharedPreferences();
        boolean isCheck = userInfo.getBoolean("notification", true);

        return isCheck;
    }

    public void setNotificationCheck(boolean isCheck) {
        SharedPreferences userInfo = getUserSharedPreferences();
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putBoolean("notification", isCheck);
        editor.apply();
    }

    public void setActiveMassForReceiver(Context context, int activeMass) {
        SharedPreferences userInfo = context.getApplicationContext().getSharedPreferences("loginInfo", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putInt("activeMass", activeMass);
        editor.apply();
    }

    public void setActiveMass(int activeMass) {
        SharedPreferences userInfo = getUserSharedPreferences();
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putInt("activeMass", activeMass);
        editor.apply();
    }


    public User getUserInfoForService(Context context) {
        SharedPreferences userInfo = context.getApplicationContext().getSharedPreferences("loginInfo", Context.MODE_MULTI_PROCESS);
        User user = new User();
        user.setId(userInfo.getInt("id", 0));
        user.setLevel(userInfo.getInt("level", 0));
        user.setName(userInfo.getString("name", null));
        user.setEmail(userInfo.getString("email", null));
        user.setBirth(userInfo.getString("birth", null));
        user.setAvatar(userInfo.getString("avatar", null));
        user.setAccessToken(userInfo.getString("accessToken", null));
        user.setDeviceId(userInfo.getString("deviceId", null));
        user.setGender(userInfo.getInt("gender", 0));
        user.setFcmToken(userInfo.getString("fcmToken", null));

        return user;
    }

}
