package ais.co.th.readsecret;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    Button btnGET;
    TextView txtSecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGET = (Button) findViewById(R.id.btn_get);
        txtSecret = (TextView) findViewById(R.id.txt_secret);
        btnGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("ais.co.th.writesecret", "ais.co.th.writesecret.MainActivity"));
                startActivityForResult(intent,200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (200 == requestCode) {
            if (resultCode == RESULT_OK) {
                txtSecret.setText(data.getStringExtra("Secret"));
            }
        }
    }
}
