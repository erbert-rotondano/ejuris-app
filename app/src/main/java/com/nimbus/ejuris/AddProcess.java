package com.nimbus.ejuris;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.nimbus.ejuris.model.Process;

import io.realm.Realm;
import mehdi.sakout.fancybuttons.FancyButton;

public class AddProcess extends AppCompatActivity {
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_process);

        realm = Realm.getDefaultInstance();
        final EditText name = (EditText) findViewById(R.id.add_process_name);
        final EditText description = (EditText) findViewById(R.id.process_description);
        final Switch process_status = (Switch) findViewById(R.id.process_status);
        FancyButton btn_save = (FancyButton) findViewById(R.id.btn_save_process);
        final Intent i = new Intent(getApplicationContext(), MainActivity.class);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = (realm.where(Process.class).max("id").intValue());
                Process process = new Process();
                process.setName(name.getText().toString());
                process.setDescription(description.getText().toString());
                process.setId(id+1);
                process.setProcess_completed(process_status.isChecked());
                realm.beginTransaction();
                    realm.copyToRealmOrUpdate(process);
                realm.commitTransaction();


                startActivity(i);
            }
        });

    }
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, "The Switch is " + (isChecked ? "on" : "off"),
                Toast.LENGTH_SHORT).show();
        if(isChecked) {
            //do stuff when Switch is ON
        } else {
            //do stuff when Switch if OFF
        }
    }
}
