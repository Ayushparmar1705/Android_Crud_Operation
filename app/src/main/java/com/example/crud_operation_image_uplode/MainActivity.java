package com.example.crud_operation_image_uplode;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


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

        ArrayAdapter country_adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line,countries);
        country.setAdapter(country_adapter);
        Button button = findViewById(R.id.buttonSubmit);

        database_helper helper = new database_helper(MainActivity.this);
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
                helper.AddStudent(student);
                Toast.makeText(MainActivity.this,"Student Added",Toast.LENGTH_SHORT).show();
            }
        });
        Button button1 = findViewById(R.id.golist);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Student.class);
                startActivity(intent);
            }
        });
    }


}