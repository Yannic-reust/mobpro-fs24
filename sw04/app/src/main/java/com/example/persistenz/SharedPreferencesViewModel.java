package com.example.persistenz;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesViewModel extends AndroidViewModel {
    private final SharedPreferences defaultSharedPreferences;
    private Resources resources;

    public SharedPreferencesViewModel(@NonNull Application application) {
        super(application);

        defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        resources = application.getResources();
    }

    public String getTeaPreference() {
        return defaultSharedPreferences.getString("teaPreferred", "");
    }

    public String getTeaSweetener() {
        return defaultSharedPreferences.getString("teaSweetener", "");
    }

    public String getTeaSweetenerDisplayValues() {
        String value = getTeaSweetener();

        String[] sweetenerValues = resources.getStringArray(R.array.teaSweetenerValues);
        String[] sweetenerEntries = resources.getStringArray(R.array.teaSweetener);

        Map<String, String> sweetenerMap = new HashMap<>();
        for (int i = 0; i < sweetenerValues.length; i++) {
            sweetenerMap.put(sweetenerValues[i], sweetenerEntries[i]);
        }

        return sweetenerMap.getOrDefault(value, "");
    }

    public Boolean isTeaWithSugar() {
        return defaultSharedPreferences.getBoolean("teaWithSugar", false);
    }

    public void setDefaultTeaPreferences() {
        SharedPreferences.Editor editor = defaultSharedPreferences.edit();
        editor.putBoolean("teaWithSugar", true);
        editor.putString("teaSweetener", "natural");
        editor.putString("teaPreferred", "Lipton");
        editor.apply();
    }

}
