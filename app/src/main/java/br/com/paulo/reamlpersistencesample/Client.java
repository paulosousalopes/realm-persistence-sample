package br.com.paulo.reamlpersistencesample;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.annotations.PrimaryKey;


/**
 * Created by catolica on 24/09/16.
 */
public class Client extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private String email;
    private String phone;
    private static Context contextClient;
    private static RealmConfiguration realmConfiguration;

    public Client(){

    };

    public Client(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Client(Context context) {
        this.contextClient = context;
        this.realmConfiguration = new RealmConfiguration.Builder(this.contextClient).build();
    }

    private int autoIncrementId() {
        int key = 1;

        Realm realm = Realm.getInstance(this.realmConfiguration);
        try {
            RealmResults<Client> items = realm.where(Client.class).findAll();
            items = items.sort("id", Sort.DESCENDING);
            key = items.size() == 0? 1 : items.get(0).getId() + 1;
        }
        catch(NullPointerException e) {
            e.printStackTrace();
        }
        return key;
    }

    public void save() {
        Realm realm = Realm.getInstance(this.realmConfiguration);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Client person = realm.createObject(Client.class);
                person.setId(autoIncrementId());
                person.setEmail(getEmail());
                person.setName(getName());
                person.setPhone(getPhone());

            }
        });
    }

    public RealmResults<Client> getAllClients() {
        Realm realm = Realm.getInstance(this.realmConfiguration);
        RealmResults<Client> clients = realm.where(Client.class).findAll();
        return clients;
    }

    public long count() {
        Realm realm = Realm.getInstance(this.realmConfiguration);
        return realm.where(Client.class).count();
    }

    public Client getClient(int id) {
        Realm realm = Realm.getInstance(this.realmConfiguration);
        return realm.where(Client.class).equalTo("id", id).findFirst();
    }

    public void delete() {
        Realm realm = Realm.getInstance(this.realmConfiguration);
        final Client client = getClient(getId());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                client.deleteFromRealm();
            }
        });
    }

    public void update(int id, Client updateClient) {
        Realm realm = Realm.getInstance(this.realmConfiguration);
        realm.beginTransaction();
        Client client = getClient(id);

        client.setName(updateClient.getName());
        client.setPhone(updateClient.getPhone());
        client.setEmail(updateClient.getEmail());

        realm.copyToRealmOrUpdate(client);
        realm.commitTransaction();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static Context getContextClient() {
        return contextClient;
    }

    public static void setContextClient(Context contextClient) {
        Client.contextClient = contextClient;
    }

    public static RealmConfiguration getRealmConfiguration() {
        return realmConfiguration;
    }

    public static void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        Client.realmConfiguration = realmConfiguration;
    }
}
