package br.com.paulo.reamlpersistencesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmResults;

public class ClientRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ClientAdapter clientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_recycler_view);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.client_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        final Client client = new Client(getApplicationContext());
        final RealmResults<Client> clients = client.getAllClients();
        clientAdapter = new ClientAdapter(getApplicationContext(), client.getAllClients(), new ClientAdapter.ClientOnClickListener() {
            @Override
            public void onClickEvent(View view, int index) {
                Intent intent = new Intent(getApplicationContext(), ClientRecyclerViewActivity.class);
                intent.putExtra("id", clients.get(index).getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(clientAdapter);
    }
}
