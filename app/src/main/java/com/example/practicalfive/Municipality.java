package com.example.practicalfive;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private static final int[] IMAGES = {
            R.drawable.beijing,
            R.drawable.shanghai,
            R.drawable.tianjin,
            R.drawable.chongqing
    };

    private static final int[] COLORS = {
            R.color.municipality_beijing,
            R.color.municipality_shanghai,
            R.color.municipality_tianjin,
            R.color.municipality_chongqing
    };

    private int municipalityIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        String municipality = new TextProcessor()
                .processMunicipality(getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY));

        if (municipality == null) {
            Toast.makeText(this, R.string.error_no_municipality, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        municipalityIndex = indexOf(municipality);

        TextView nameView = findViewById(R.id.textMunicipalityName);
        TextView infoView = findViewById(R.id.textMunicipalityInfo);
        ImageView imageView = findViewById(R.id.imageMunicipality);
        Button visitButton = findViewById(R.id.buttonVisitSite);

        int accent = ContextCompat.getColor(this, COLORS[municipalityIndex]);
        int textOnAccent = ContextCompat.getColor(this, R.color.color_text_light);

        nameView.setText(municipality);
        nameView.setBackgroundColor(accent);
        nameView.setTextColor(textOnAccent);

        imageView.setImageResource(IMAGES[municipalityIndex]);
        imageView.setContentDescription(
                getString(R.string.municipality_image_description, municipality));

        infoView.setText(municipalitySummary());
        infoView.setTextColor(accent);

        visitButton.setTextColor(textOnAccent);
        visitButton.setBackgroundTintList(ColorStateList.valueOf(accent));
    }

    private String municipalitySummary() {
        return getResources().getStringArray(R.array.municipalities_array)[municipalityIndex];
    }

    private int indexOf(String municipality) {
        switch (municipality) {
            case "Shanghai":
                return 1;
            case "Tianjin":
                return 2;
            case "Chongqing":
                return 3;
            case "Beijing":
            default:
                return 0;
        }
    }
}
