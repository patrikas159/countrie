package com.corona.coronazp202;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class NewEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        setTitle("New Entry");

        // Issitraukiame konkretu irasa is paspaustos korteles.
        Intent intent = getIntent();
        Corona corona = (Corona) intent.getSerializableExtra(Adapter.ENTRY);

        // Pasemame is vaizdo visus elementus su kuriais dirbsime.
        final CheckBox checkBoxLithuania = findViewById(R.id.country_lithuania);
        final CheckBox checkBoxLatvia = findViewById(R.id.country_latvia);
        final CheckBox checkBoxEstonia = findViewById(R.id.country_estonia);
        final CheckBox checkBoxPoland = findViewById(R.id.country_poland);

        final RadioGroup groupDeaths = findViewById(R.id.deaths);
        RadioButton button2k = findViewById(R.id.two_thousand);

        final Spinner spinnerUpdate = findViewById(R.id.last_update);
        ArrayList<String> updateList = new ArrayList<String>();
        updateList.add(corona.getLastUpdate());
        updateList.add(getResources().getString(R.string.new_entry_date_2));
        updateList.add(getResources().getString(R.string.new_entry_date_3));
        updateList.add(getResources().getString(R.string.new_entry_date_4));
        // Adapteris reikalingas sujungti, isdestyma su stringArray.
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                updateList
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adapteri idedame i musu spinneri.
        spinnerUpdate.setAdapter(dataAdapter);


        final EditText editTextConfirmed = findViewById(R.id.confirmed);

        Button buttonDisplaySelected = findViewById(R.id.display_selected_btn);

        // Uzpildome elementus (Coronos) informacija.

        checkBoxPoland.setText(corona.getKeyId());
        button2k.setText(String.valueOf(corona.getDeaths()));
        editTextConfirmed.setText(String.valueOf(corona.getConfirmed()));

        // Ant mygtuko paspaudimo parodyti visa vartotojo ivesta informacija.
        buttonDisplaySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countries = "";
                if (checkBoxLithuania.isChecked()) {
                    countries += checkBoxLithuania.getText().toString() + ", ";
                }
                if (checkBoxLatvia.isChecked()) {
                    countries += checkBoxLatvia.getText().toString() + ", ";
                }
                if (checkBoxEstonia.isChecked()) {
                    countries += checkBoxEstonia.getText().toString() + ", ";
                }
                if (checkBoxPoland.isChecked()) {
                    countries += checkBoxPoland.getText().toString() + ", ";
                }

                // get selected radio button from radioGroup
                int selectedId = groupDeaths.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);
                int deaths = Integer.parseInt(selectedButton.getText().toString());

                String updateDate = String.valueOf(spinnerUpdate.getSelectedItem());

                String confirmed = editTextConfirmed.getText().toString();

                editTextConfirmed.setError(null);
                if (Validation.isValidNumber(confirmed)){
                    // Sukuriamas korona objektas is GUI elementu.
                    // public Corona(String lastUpdate, String keyId, int confirmed, int deaths)
                    Corona corona = new Corona(updateDate,countries, Integer.parseInt(confirmed), deaths);

                    // Atvaizduojamas vartotojui objekto informacija.
                    Toast.makeText(
                            NewEntryActivity.this,
                            "Country(-ies): " + corona.getKeyId() + "\n " +
                                    "Last update: " + corona.getLastUpdate() + "\n " +
                                    "Confirmed: " + corona.getConfirmed() + "\n " +
                                    "Deaths: " + corona.getDeaths(),
                            Toast.LENGTH_SHORT
                    ).show();
                } else { // blogai įvesti confirmed duomenys
                    editTextConfirmed.setError(getResources().getString(R.string.new_entry_invalid_confirmed));
                    editTextConfirmed.requestFocus();
                }
            }
        });
    }
}
