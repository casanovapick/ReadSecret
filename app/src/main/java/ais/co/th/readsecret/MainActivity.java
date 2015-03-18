package ais.co.th.readsecret;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    Button btnGET;
    TextView txtSecret;
    Button btnOut;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs =  PreferenceManager.getDefaultSharedPreferences(this);

        btnGET = (Button) findViewById(R.id.btn_get);
        btnOut = (Button) findViewById(R.id.btn_logout);
        txtSecret = (TextView) findViewById(R.id.txt_secret);

        btnGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("ais.co.th.writesecret", "ais.co.th.writesecret.ConfirmActivity"));
                startActivityForResult(intent,200);
            }
        });

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().putString("Secret","").apply();
                finish();
            }
        });
        if(prefs.getString("Secret","").matches("")){
            btnOut.setVisibility(View.GONE);
            btnGET.setVisibility(View.VISIBLE);

        }else{
            txtSecret.setText("Login with "+prefs.getString("Secret",""));

            btnGET.setVisibility(View.GONE);
            btnOut.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (200 != requestCode || resultCode != RESULT_OK)
            return;

        txtSecret.setText("Login with " + data.getStringExtra("Secret"));
        prefs.edit().putString("Secret", data.getStringExtra("Secret")).apply();
        btnGET.setVisibility(View.GONE);
        btnOut.setVisibility(View.VISIBLE);

    }
}
