package com.example.crud_operation_image_uplode;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.animation.AnimatableView;

import java.util.ArrayList;
import java.util.List;

public class List_Student extends AppCompatActivity {
    TableLayout tableLayout;

    public void getStudent(){
        database_helper helper = new database_helper(this);
        Cursor cursor = helper.getStudent();
        tableLayout = findViewById(R.id.tableLayout);
        TableRow row = new TableRow(this);

        String[] headers = {"Id" , "Name","Age","Country","Gender","Hobby","Date","Action"};
        for(String col : headers){
            TextView tv = new TextView(this);
            tv.setText(col);
            tv.setPadding(10,10,10,10);
            row.addView(tv);
        }
        tableLayout.addView(row);
        tableLayout.removeAllViews();

        while(cursor.moveToNext()){
            TableRow row1 =  new TableRow(this);
            for(int i=0;i<7;i++){
                TextView tv1 = new TextView(this);
                tv1.setText(cursor.getString(i));
                tv1.setPadding(10,10,10,10);
                row1.addView(tv1);
            }

            Button deleteButton = new Button(this);
            deleteButton.setText("delete");
            deleteButton.setPadding(5,5,5,5);

            row1.addView(deleteButton);

            Button updateButton = new Button(this);
            updateButton.setText("update");
            updateButton.setPadding(5,5,5,5);
            row1.addView(updateButton);
            tableLayout.addView(row1);
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int row = helper.deleteStudent(id);
                    if(row>0){
                        getStudent();
                        Toast.makeText(List_Student.this,"Student deleted succesfully",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(List_Student.this,"Student Not deleted",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor cursor = helper.selectOneData(id);
                    if(cursor.moveToNext() && cursor!=null){
                        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                        int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                        String country = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                        String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
                        String hobby = cursor.getString(cursor.getColumnIndexOrThrow("hobby"));
                        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                        Intent intent = new Intent(List_Student.this, Update_Activity.class);
                        intent.putExtra("id",id);
                        intent.putExtra("name",name);
                        intent.putExtra("age",age);
                        intent.putExtra("gender",gender);
                        intent.putExtra("hobby",hobby);
                        intent.putExtra("date",date);
                        intent.putExtra("country",country);
                        startActivity(intent);

                    }
                }
            });


        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        getStudent();
    }
}