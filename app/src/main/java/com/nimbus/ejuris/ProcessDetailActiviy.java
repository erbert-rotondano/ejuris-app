package com.nimbus.ejuris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.nimbus.ejuris.model.Process;

import io.realm.Realm;

public class ProcessDetailActiviy extends AppCompatActivity {

    Realm realm;
    Process process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_detail_activiy);

        TextView name = (TextView) findViewById(R.id.process_name_display);
        TextView description = (TextView) findViewById(R.id.process_description_display);
        ImageView image = (ImageView) findViewById(R.id.proccess_completed_display);
        Switch status = (Switch) findViewById(R.id.process_status_display);


        realm = Realm.getDefaultInstance();

        if( getIntent() != null && getIntent().getLongExtra( Process.ID, 0 ) > 0 ){
            long process_id = getIntent().getLongExtra( Process.ID, 0 );

            realm = Realm.getDefaultInstance();
            realm.isAutoRefresh();
            process = realm.where(Process.class).equalTo("id", process_id).findFirst();

        }
        name.setText(process.getName());
        description.setText(process.getDescription());
        status.setChecked(process.isProcess_completed());
        if(process.isProcess_completed()){
            image.setImageResource(R.drawable.check_yes);
        } else {
            image.setImageResource(R.drawable.check_false);
        }
    }
}
