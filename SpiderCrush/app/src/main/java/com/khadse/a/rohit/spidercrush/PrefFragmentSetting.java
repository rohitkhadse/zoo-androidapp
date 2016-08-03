package com.khadse.a.rohit.spidercrush;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

public class PrefFragmentSetting extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static int highscore;
    public PrefFragmentSetting () {}

    @Override
    public void onCreate (Bundle b) {
        super.onCreate(b);

        addPreferencesFromResource(R.xml.pref_fragment_setting);
        Preference pref = getPreferenceScreen().findPreference("score_key");

        Assets.setScore();
        pref.setSummary("The high Score is "+Assets.oldScore);
        if (Assets.newScore > Assets.oldScore)
        Assets.oldScore = Assets.newScore;
        Assets.newScore = 0;

    }
    @Override
    public void onResume () {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        Preference pref = getPreferenceScreen().findPreference("key_share");
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick (Preference preference) {

                Intent sharingIntent = new Intent (android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "http://play.google.com/store/apps/details?id=com.blackboard.android.central.lamar";
                sharingIntent.putExtra (android.content.Intent.EXTRA_SUBJECT, "Check out \"Lamar\"");
                sharingIntent.putExtra (android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;
            }
        });

    }
    public void onSharedPreferenceChanged (SharedPreferences sharedPreferences, String key) {

    }

}
