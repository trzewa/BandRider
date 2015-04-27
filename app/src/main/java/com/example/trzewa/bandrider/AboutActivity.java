package com.example.trzewa.bandrider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutActivity extends ActionBarActivity {

    static String VersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Brak informacji";
        }
    }

    public static void Show(Activity callingActivity) {
// Klasa SpannableString umożliwia wyróżnianie odnośników
        SpannableString aboutText = new SpannableString("Wersja " +
                VersionName(callingActivity) + "\n\n" +
                callingActivity.getString(R.string.about));
// Generowanie widoków przekazywanych do obiektu AlertDialog.Builder
// i używanych do ustawiania tekstu
        View about;
        TextView tvAbout;
        try {
// Rozwijanie niestandardowego układu do klasy
            LayoutInflater inflater = callingActivity.getLayoutInflater();

            about = inflater.inflate(R.layout.activity_about,
                    (ViewGroup) callingActivity.findViewById(R.id.aboutView));
            tvAbout = (TextView) about.findViewById(R.id.aboutText);
        } catch (InflateException e) {
// Mechanizm rozwijania układów do klas może zgłosić wyjątek. Jest to mało prawdopodobne,
// jeśli jednak się zdarzy, domyślnie stosowana jest kontrolka TextView
            about = tvAbout = new TextView(callingActivity);
        }
// Określanie tekstu okna O programie
        tvAbout.setText(aboutText);
// Przekształcenie odpowiednich fragmentów tekstu na odnośniki
        Linkify.addLinks(tvAbout, Linkify.ALL);
// Tworzenie i wyświetlanie okna dialogowego
        new AlertDialog.Builder(callingActivity)
                .setTitle("O programie " + callingActivity.getString(R.string.app_name))
                .setCancelable(true)
                        //.setIcon(R.drawable.icon)
                .setPositiveButton("OK", null)
                .setView(about)
                .show();
// Dane zwracane przez metody obiektu Builder umożliwiają łańcuchowe wywoływanie tych metod
    }
}
