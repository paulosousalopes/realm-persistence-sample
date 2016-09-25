package br.com.paulo.reamlpersistencesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {
final static String TAG = "REALM";
    private Realm realm;
    private RealmConfiguration realmConfig;
    private Button btnSave, btnRecyclerView, btnListView;
    private EditText editTextName, editTextEmail, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realmConfig = new RealmConfiguration.Builder(this).build();
        realm       = Realm.getInstance(realmConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnSave = (Button) findViewById(R.id.button);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client client = new Client(getApplicationContext());
                String name = String.valueOf(((EditText)findViewById(R.id.editTextName)).getText());
                String email = String.valueOf(((EditText)findViewById(R.id.editTextEmail)).getText());
                String phone = String.valueOf(((EditText)findViewById(R.id.editTextPhone)).getText());

                client.setName(name);
                client.setName(email);
                client.setName(phone);

                client.save();
                long count = client.count();
                Toast.makeText(getApplicationContext(), "You have " + count + " client" + (count > 1 ? "s" : ""), Toast.LENGTH_SHORT).show();
            }
        });

        btnRecyclerView  = (Button) findViewById(R.id.buttonRecycler);

        btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

                Intent recyclerViewActivity = new Intent(getApplicationContext(), ClientRecyclerViewActivity.class);
                startActivity(recyclerViewActivity);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}
