package com.example.rauliyrjana.raulinakusqlite;

import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends ListActivity{
    private AkuDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource=new AkuDataSource(this);
        dataSource.open();

        List<Aku> values=dataSource.getAllAku();
        ArrayAdapter<Aku> adapter=new ArrayAdapter<Aku>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }

    public void onClick(View view){
        ArrayAdapter<Aku> adapter=(ArrayAdapter<Aku>)getListAdapter();
        Aku aku=null;

        switch (view.getId()) {
            case R.id.add:
                EditText nro = findViewById(R.id.editTextNumero);
                EditText name = findViewById(R.id.editTextNimi);
                EditText hankinta = findViewById(R.id.editTextHankinta);
                EditText painos = findViewById(R.id.editTextPainos);

                aku = dataSource.createAku(nro.getText().toString(),name.getText().toString(),
                        hankinta.getText().toString(),painos.getText().toString());

                adapter.add(aku);
                nro.setText("");name.setText("");hankinta.setText("");painos.setText("");// tyhjät kentät
                break;

            case R.id.delete: //tuhoaa ensimmÃ¤isen listalta
                if (getListAdapter().getCount() > 0) {
                    aku = (Aku) getListAdapter().getItem(0);
                    dataSource.deleteAku(aku);
                    adapter.remove(aku);
                }
                break;

            case R.id.deleteActLine: //poistaa sellaisen rivin, jota on klikattu listalta ja tiedot menneet tietojen syöttökenttään
                if (getListAdapter().getCount() > 0) {
                    EditText no = findViewById(R.id.editTextNumero);// hakee poistettavan tekstin 'numerolaatikosta', on siis ensin klikattu listalta poistettavaa teosta

                    Toast.makeText(this, no + "  valittu", Toast.LENGTH_LONG).show();
                    aku = (Aku)getListAdapter().getItem(no.getId());// haetaan pos -arvoa vastaava aku
                    dataSource.deleteRow(aku);// poistetaan se tietokannasta
                    adapter.remove(aku);// ja adapterista
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){ //klikataan jotain riviä
        Aku aku=(Aku)getListAdapter().getItem(position); // haetaan sen positio
        Aku akuno = (Aku)getListAdapter().getItem((int) id);
        Toast.makeText(this, aku + "  valittu", Toast.LENGTH_SHORT).show();
        EditText nro = findViewById(R.id.editTextNumero); //
        EditText name = findViewById(R.id.editTextNimi);

        nro.setText(""+aku.getKirjanNumero());
        name.setText(aku.getKirjanNimi());

    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
