package com.example.databaseapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DbHelper myDb;
    EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DbHelper(this);
        final Button button = findViewById(R.id.save_data);
        final Button button2 = findViewById(R.id.show_data);
        e1=findViewById(R.id.fname);
        e2=findViewById(R.id.sname);
        e3=findViewById(R.id.marks);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(myDb.inputData(e1.getText().toString(),e2.getText().toString(),e3.getText().toString())){
                    Toast.makeText(MainActivity.this, "DataInserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "SomeError", Toast.LENGTH_LONG).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                StringBuffer sb = new StringBuffer();
                while (res.moveToNext()) {
                    sb.append("id: "+res.getString(0) + "\n");
                    sb.append("Name: "+res.getString(1) + "\n");
                    sb.append("Surname: "+res.getString(2) + "\n");
                    sb.append("Marks: "+res.getString(3) + "\n\n");
                }
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
