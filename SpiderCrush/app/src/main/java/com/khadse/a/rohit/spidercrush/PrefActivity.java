package com.khadse.a.rohit.spidercrush;

import android.os.Build;
import android.preference.PreferenceActivity;

import java.util.List;

public class PrefActivity extends PreferenceActivity {
    @Override
    protected boolean isValidFragment(String fragmentName){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            return true;
        else if (PrefFragmentSetting.class.getName().equals(fragmentName))
            return true;
        return false;
    }
    @Override
    public void onBuildHeaders (List<Header> target){
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PrefFragmentSetting()).commit();
    }
}
