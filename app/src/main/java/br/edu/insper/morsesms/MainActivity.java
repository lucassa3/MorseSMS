package br.edu.insper.morsesms;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    EditText sendBtn;
    EditText morsetap;
    EditText notsensitive3;
    EditText notsensitive4;
    EditText notsensitive1;
    EditText notsensitive2;
    EditText txtMessage;
    ListView quickMessages;

    ArrayAdapter<String> adapter;
    ArrayList<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (EditText) findViewById(R.id.btnSendSMS);
        morsetap = (EditText) findViewById(R.id.moarsetap);
        notsensitive1 = (EditText) findViewById(R.id.notsensitive1);
        notsensitive2 = (EditText) findViewById(R.id.notsensitive2);
        notsensitive3 = (EditText) findViewById(R.id.notsensitive3);
        notsensitive4 = (EditText) findViewById(R.id.notsensitive4);
        txtMessage = (EditText) findViewById(R.id.editText2);

        notsensitive1.setEnabled(false);
        notsensitive2.setEnabled(false);
        notsensitive3.setEnabled(false);
        notsensitive4.setEnabled(false);
        txtMessage.setEnabled(false);

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

        morsetap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //moarse aqui
            }
        });

        quickMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String escolha = listItems.get(position).toString();
                txtMessage.setText(escolha);
            }
        });


        final CharactersTree charTree = new CharactersTree();
        final Press botao = new Press();
        final Press release = new Press();


        morsetap.setOnTouchListener(new View.OnTouchListener() {
            Characters characters = charTree.charactersTree[0];
            String mensagem = "";
            boolean check = false;
            Timer timer;
            Timer timer2;

            @Override
            public boolean onTouch (View arg0, MotionEvent arg1){
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        botao.setActivationTime();


                        long releasedPeriod = System.currentTimeMillis() - release.getActivationTime();
                        System.out.println(releasedPeriod);

                        /*if (releasedPeriod >= 1000) {
                            mensagem += characters.getCharacter();
                            txtMessage.setText(mensagem);
                            characters = charTree.charactersTree[0];
                        }

                        if (releasedPeriod >= 4000) {
                            mensagem += " ";
                        }*/

                        if (check==true) {
                            timer.cancel();
                            timer2.cancel();

                        }
                        else {
                            check=true;

                        }

                        return true;

                    case MotionEvent.ACTION_UP:
                        release.setActivationTime();

                        long pressedPeriod = System.currentTimeMillis() - botao.getActivationTime();

                        if (pressedPeriod <= 200) {
                            characters = characters.getLeft();
                            System.out.println(characters.getCharacter());
                            //txtMessage.setText("curto");
                        } else {
                            characters = characters.getRight();
                            System.out.println(characters.getCharacter());
                            //txtMessage.setText("longo");
                        }

                        final Handler mHandler = new Handler() {
                            public void handleMessage(Message msg) {
                                txtMessage.setText(mensagem); //this is the textview
                            }
                        };

                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                mensagem += characters.getCharacter();
                                mHandler.obtainMessage(1).sendToTarget();
                                characters = charTree.charactersTree[0];
                            }
                        }, 1000); //time out 1s


                        timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                mensagem += " ";
                            }
                        }, 3000); //time out 3s



                        return true;
                    }
                return false;
            }
        });
    }

    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String phoneNo = "+5511972217872";
        String message = txtMessage.getText().toString();
        System.out.println(message);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}