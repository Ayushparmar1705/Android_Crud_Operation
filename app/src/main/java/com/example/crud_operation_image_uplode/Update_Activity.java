package com.example.crud_operation_image_uplode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Update_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        database_helper h = new database_helper(this);
        int id = getIntent().getIntExtra("id",0);
        String iname = getIntent().getStringExtra("name");
        String iage = getIntent().getStringExtra("iage");
        String icountry = getIntent().getStringExtra("country");

        EditText name = findViewById(R.id.editTextName);
        EditText age = findViewById(R.id.editTextAge);



        AutoCompleteTextView country = findViewById(R.id.autoCompleteCountry);
        String[] countries = {
                "United States",
                "Canada",
                "United Kingdom",
                "Australia",
                "India",
                "Germany",
                "France",
                "Italy",
                "Brazil",
                "Japan",
                "China",
                "South Korea",
                "Mexico",
                "Spain",
                "Russia",
                "Netherlands",
                "Sweden",
                "South Africa",
                "New Zealand",
                "Singapore"
        };
        name.setText(iname);
        age.setText(iage);
        country.setText(icountry);
        ArrayAdapter country_adapter = new ArrayAdapter(Update_Activity.this, android.R.layout.simple_dropdown_item_1line,countries);
        country.setAdapter(country_adapter);
        Button button = findViewById(R.id.buttonSubmit);

        database_helper helper = new database_helper(Update_Activity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker date = findViewById(R.id.datePicker);
                int month = date.getMonth();
                int year = date.getYear();
                int mdate = date.getDayOfMonth();
                String current_date = mdate + " / " + (month+1) + " / " + year;
                RadioGroup gender = findViewById(R.id.radioGroupGender);
                String genderValue = "";
                int getGenderId = gender.getCheckedRadioButtonId();
                if(getGenderId!=-1){

                    RadioButton getRadioButton = findViewById(getGenderId);
                    genderValue = getRadioButton.getText().toString();
                }
                String hobby = "";
                CheckBox h1 = findViewById(R.id.checkBoxSports);
                CheckBox h2= findViewById(R.id.checkBoxReading);
                CheckBox h3 = findViewById(R.id.checkBoxMusic);
                if(h1.isChecked()){
                    hobby +=h1.getText().toString();
                }
                if(h2.isChecked()){
                    hobby +=h2.getText().toString();
                }
                if(h3.isChecked()){
                    hobby +=h3.getText().toString();
                }
                String getName = name.getText().toString();
                String getAge = age.getText().toString();
                String getCountry = country.getText().toString();
                Student student = new Student(getName , Integer.parseInt(getAge) , getCountry , genderValue , hobby , current_date);
                helper.updateStudent(student , id);
                Toast.makeText(Update_Activity.this,"Student Updated",Toast.LENGTH_SHORT).show();
            }
        });
        Button button1 = findViewById(R.id.golist);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update_Activity.this, List_Student.class);
                startActivity(intent);
            }
        });
    }


}