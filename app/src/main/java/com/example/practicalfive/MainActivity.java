package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY = "com.example.practicalfive.EXTRA_MUNICIPALITY";

    private final TextProcessor textProcessor = new TextProcessor();

    private EditText editMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMunicipality = findViewById(R.id.editMunicipality);

        editMunicipality.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                showMunicipality();
                return true;
            }
            return false;
        });
    }

    private void showMunicipality() {
        String input = editMunicipality.getText().toString();

        if (input.trim().isEmpty()) {
            editMunicipality.setError(getString(R.string.error_empty));
            return;
        }

        String municipality = textProcessor.processMunicipality(input);

        if (municipality == null) {
            editMunicipality.setError(getString(R.string.error_invalid));
            return;
        }

        editMunicipality.setError(null);

        Intent intent = new Intent(this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }
}
