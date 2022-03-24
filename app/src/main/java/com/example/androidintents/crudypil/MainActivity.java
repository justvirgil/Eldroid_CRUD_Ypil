package com.example.androidintents.crudypil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    TextInputEditText idnumber,firstname,lastname,address,phonenumber,password;
    MaterialButton insert,update,delete,view;
    DPhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idnumber = findViewById(R.id.idnumber);
        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        address = findViewById(R.id.address);
        phonenumber = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);

        insert = findViewById(R.id.btninsert);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btndelete);
        view = findViewById(R.id.btnView);

        DB = new DPhelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idnumberTXT = idnumber.getText().toString();
                String firstnameTXT = firstname.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String addressTXT = address.getText().toString();
                String phonenumberTXT = phonenumber.getText().toString();
                String passwordTXT = password.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(idnumberTXT,firstnameTXT,lastnameTXT,addressTXT,phonenumberTXT,passwordTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idnumberTXT =  idnumber.getText().toString();
                String firstnameTXT = firstname.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String addressTXT = address.getText().toString();
                String phonenumberTXT = phonenumber.getText().toString();
                String passwordTXT = password.getText().toString();

                Boolean checkupdatetdata = DB.updateuserdata(idnumberTXT,firstnameTXT,lastnameTXT,addressTXT,phonenumberTXT,passwordTXT);
                if(checkupdatetdata==true)
                    Toast.makeText(MainActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idnumberTXT = idnumber.getText().toString();


                Boolean checkdeletetdata = DB.deletedata(idnumberTXT);
                if(checkdeletetdata==true)
                    Toast.makeText(MainActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID Number: "+res.getString(0)+"\n");
                    buffer.append("First Name: "+res.getString(1)+"\n");
                    buffer.append("Last Name: "+res.getString(2)+"\n");
                    buffer.append("Address: "+res.getString(3)+"\n");
                    buffer.append("Phonenumber: "+res.getString(4)+"\n");
                    buffer.append("Password: "+res.getString(5)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}