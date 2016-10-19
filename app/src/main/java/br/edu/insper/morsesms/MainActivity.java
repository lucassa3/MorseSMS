package br.edu.insper.morsesms;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    EditText sendBtn;
    EditText moarsetap;
    EditText notsensitive3;
    EditText notsensitive4;
    EditText notsensitive1;
    EditText notsensitive2;
    EditText txtphoneNo;
    EditText txtMessage;
    ListView quickMessages;

    ArrayAdapter<String> adapter;
    ArrayList<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (EditText) findViewById(R.id.btnSendSMS);
        moarsetap = (EditText) findViewById(R.id.moarsetap);
        notsensitive1= (EditText) findViewById(R.id.notsensitive1);
        notsensitive2 = (EditText) findViewById(R.id.notsensitive2);
        notsensitive3= (EditText) findViewById(R.id.notsensitive3);
        notsensitive4 = (EditText) findViewById(R.id.notsensitive4);
        txtphoneNo = (EditText) findViewById(R.id.editText);
        txtMessage = (EditText) findViewById(R.id.editText2);


        notsensitive1.setEnabled(false);
        notsensitive2.setEnabled(false);
        notsensitive3.setEnabled(false);
        notsensitive4.setEnabled(false);





        quickMessages = (ListView) findViewById(R.id.QuickMessages);

        listItems = new ArrayList<String>();
        listItems.add("SOS");
        listItems.add("Sim");
        listItems.add("Não");
        listItems.add("Estou com fome!");
        listItems.add("Estou com sede!");
        listItems.add("Ir ao Banheiro");
        listItems.add("Obrigado!");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        quickMessages.setAdapter(adapter);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
                txtMessage.setText("");
            }
        });

        moarsetap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //moarse aqui
            }
        });

        quickMessages.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String escolha = listItems.get(position).toString();
                txtMessage.setText(escolha);
            }
        });
    }
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String phoneNo = txtphoneNo.getText().toString();
        String message = txtMessage.getText().toString();
        System.out.println(message);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}