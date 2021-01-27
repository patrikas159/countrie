package com.countries.countries;

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
        Countries countries = (Countries) intent.getSerializableExtra(Adapter.ENTRY);

        // Pasemame is vaizdo visus elementus su kuriais dirbsime.
        final CheckBox checkBoxLithuania = findViewById(R.id.country_lithuania);
        final CheckBox checkBoxLatvia = findViewById(R.id.country_latvia);
        final CheckBox checkBoxEstonia = findViewById(R.id.country_estonia);
        final CheckBox checkBoxPoland = findViewById(R.id.country_poland);

        final RadioGroup groupPopulation = findViewById(R.id.population);
        RadioButton button2k = findViewById(R.id.two_thousand);

        final Spinner spinnerCapital = findViewById(R.id.capital);
        ArrayList<String> updateList = new ArrayList<String>();
        updateList.add(countries.getCapital());
        updateList.add(getResources().getString(R.string.new_entry_capital_2));
        updateList.add(getResources().getString(R.string.new_entry_capital_3));
        updateList.add(getResources().getString(R.string.new_entry_capital_4));
        // Adapteris reikalingas sujungti, isdestyma su stringArray.
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                updateList
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adapteri idedame i musu spinneri.
        spinnerCapital.setAdapter(dataAdapter);


        final EditText editTextRegion = findViewById(R.id.region);

        Button buttonDisplaySelected = findViewById(R.id.display_selected_btn);

        // Uzpildome elementus (Coronos) informacija.

        checkBoxPoland.setText(countries.getCountry());
        button2k.setText(String.valueOf(countries.getCapital()));
        editTextRegion.setText(String.valueOf(countries.getRegion()));

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
                int selectedId = groupPopulation.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);
                int population = Integer.parseInt(selectedButton.getText().toString());

                String capital = String.valueOf(spinnerCapital.getSelectedItem());

                String region = editTextRegion.getText().toString();

                editTextRegion.setError(null);
                if (Validation.isValidNumber(region)){
                    // Sukuriamas korona objektas is GUI elementu.
                    // public Corona(String lastUpdate, String keyId, int region, int capital)
                    Countries countrie = new Countries(capital,countries, region, population);


                    // Atvaizduojamas vartotojui objekto informacija.
                    Toast.makeText(
                            NewEntryActivity.this,
                            "Name: " + countrie.getCountry() + "\n " +
                                    "Population: " + countrie.getPopulation() + "\n " +
                                    "Region: " + countrie.getRegion() + "\n " +
                                    "Capital: " + countrie.getCapital(),
                            Toast.LENGTH_SHORT
                    ).show();
                } else { // blogai įvesti region duomenys
                    editTextRegion.setError(getResources().getString(R.string.new_entry_invalid_confirmed));
                    editTextRegion.requestFocus();
                }
            }
        });
    }
}
