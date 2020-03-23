package com.example.mydatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    HelpDataBase db;
    EditText idtxt,ntxt,ftxt,stxt,mtxt;
    Button btn,btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new HelpDataBase(this);
        idtxt = findViewById(R.id.idtxt);
        ntxt = findViewById(R.id.name);
        ftxt = findViewById(R.id.firstname);
        stxt = findViewById(R.id.surename);
        mtxt = findViewById(R.id.marks);
        btn = findViewById(R.id.button);
        btn1 = findViewById(R.id.alldata);
        btn2 = findViewById(R.id.update);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertdata();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alldata();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedata();
            }
        });
    }

    private void updatedata() {
        String id = idtxt.getText().toString();
        String name = ntxt.getText().toString();
        String fname = ftxt.getText().toString();
        String sname = stxt.getText().toString();
        String marks = mtxt.getText().toString();
        boolean isinsert = db.update(id,name,fname,sname,marks);
        if (isinsert == true) {
            Toasty.success(this, "Update!", Toast.LENGTH_SHORT, true).show();
        }
        else {
            Toasty.error(this, "Update error.", Toast.LENGTH_SHORT, true).show();
        }
    }

    private void alldata() {
        Cursor res = db.getalldata();
        if (res.getCount() == 0 ) {
            viewalldata("Error","Nothing found");
            return;
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID:"+res.getString(0)+"\n");
                buffer.append("Name"+res.getString(1)+"\n");
                buffer.append("FirstName"+res.getString(2)+"\n");
                buffer.append("SureName"+res.getString(3)+"\n");
                buffer.append("Marks"+res.getString(4)+"\n\n");
            }
            //all data show
            viewalldata("Get data",buffer.toString());
        }
    }

    private void insertdata() {
        String name = ntxt.getText().toString();
        String fname = ftxt.getText().toString();
        String sname = stxt.getText().toString();
        String marks = mtxt.getText().toString();
        boolean isinsert = db.insert(name,fname,sname,marks);
        if (isinsert == true) {
            Toasty.success(this, "Success!", Toast.LENGTH_SHORT, true).show();
        }
        else {
            Toasty.error(this, "error.", Toast.LENGTH_SHORT, true).show();
        }

    }
    public void viewalldata(String title,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
