package br.com.paulo.reamlpersistencesample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by catolica on 25/09/16.
 */
public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {
    private static final String TAG = "recyclerview";
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Client> clients;
    private ClientOnClickListener clientOnClickListener;

    public ClientAdapter(Context context, List<Client> clients, ClientOnClickListener listener) {
        this.clients = clients;
        this.context = context;
        this.clientOnClickListener = listener;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(TAG, "ClientAdapter");
    }

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.list_item_client, viewGroup, false);
        ClientViewHolder clientViewHolder = new ClientViewHolder(view);
        Log.d(TAG, "onCreateViewHolder");
        return clientViewHolder;
    }


    @Override
    public void onBindViewHolder(final ClientViewHolder holder, final int position) {
        holder.name.setText(clients.get(position).getName());
        holder.email.setText(clients.get(position).getEmail());
        holder.phone.setText(clients.get(position).getPhone());

        Log.d(TAG, "onCreateViewHolder");
        if (clientOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener(){
               @Override
                public void onClick(View view) {
                   clientOnClickListener.onClickEvent( holder.itemView, position);
               }
            });
        }
    }

    public interface ClientOnClickListener {
        public void onClickEvent(View view, int index);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return this.clients.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, phone;

        public ClientViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.textViewNameRecycler);
            email = (TextView) itemView.findViewById(R.id.textViewEmailRecycler);
            phone = (TextView) itemView.findViewById(R.id.textViewPhoneRecycler);
        }
    }
}


