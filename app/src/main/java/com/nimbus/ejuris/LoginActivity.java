package com.nimbus.ejuris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nimbus.ejuris.model.Process;
import com.nimbus.ejuris.model.User;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends AppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences prefs = getSharedPreferences("userPrefs", MODE_PRIVATE);


        if(!(prefs.getString("email", "notLogged").equals("notLogged"))){
            Intent i = new Intent(getApplicationContext(), MainActivity.class );
            startActivity(i);
        }
        setContentView(R.layout.activity_login);

        // TODO: Test code, remove after completes user sign up
            User user = new User();
            user.setEmail("Admin@adm.com");
            user.setPassword("1234");
            user.setId(1);
            realm = realm.getDefaultInstance();
            realm.beginTransaction();
                realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();


        // TODO: until here

        final EditText email = (EditText) findViewById(R.id.login_email);
        final EditText password = (EditText) findViewById(R.id.login_password);
        final TextView error_text= (TextView) findViewById(R.id.login_error_msg);
        FancyButton login_btn = (FancyButton) findViewById(R.id.btn_login);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(email.getText().toString().equals("")) || !(password.getText().toString().equals(""))){

                    User user_requested = realm.where(User.class).
                            equalTo("email", email.getText().toString()).
                            equalTo("password", password.getText().toString()).
                            findFirst();
                    if(user_requested != null) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("email", email.getText().toString());
                        editor.putString("password", password.getText().toString());
                        //Confirma a gravação dos dados
                        editor.commit();

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                    else{
                        error_text.setText("Não foi possivel efetuar o login, corrija as inforamções e tente novamente.");
                        error_text.setVisibility(View.VISIBLE);
                    }
                } else {
                    error_text.setVisibility(View.VISIBLE);
                }

            }
        });



    }
}
