package br.edu.insper.morsesms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    Button sendSMSBtn;
    Button backspaceBtn;
    Button quickMessagesBtn;
    Button contactsBtn;
    
    TextView smsView;
    TextView morseCodeView;
    TextView contactSelected;

    EditText morseTap;

    ListView quickMessages;
    ListView contacts;

    ArrayAdapter<String> adapter;
    ArrayList<String> listItems;

    Cursor temp_cursor;

    String contactName;
    String contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }

        sendSMSBtn = (Button) findViewById(R.id.SendSMSBtn);
        backspaceBtn = (Button) findViewById(R.id.BackspaceBtn);
        quickMessagesBtn = (Button) findViewById(R.id.QuickMessagesBtn);
        contactsBtn = (Button) findViewById(R.id.ContactsBtn);
        
        smsView = (TextView) findViewById(R.id.SMSView);
        morseCodeView = (TextView)  findViewById(R.id.MorseCodeView);
        contactSelected = (TextView)  findViewById(R.id.ContactSelected);
        
        morseTap = (EditText) findViewById(R.id.MorseTap);

        quickMessages = (ListView) findViewById(R.id.QuickMessages);
        contacts = (ListView) findViewById(R.id.Contacts);

        final CharacterNodeTree charTree = new CharacterNodeTree();
        final Msg sms_view =  new Msg();
        final Msg morse_code_view =  new Msg();

        listItems = new ArrayList<String>();
        listItems.add("SOS");
        listItems.add("Yes");
        listItems.add("No");
        listItems.add("I'm hungry!");
        listItems.add("I'm thirsty!");
        listItems.add("I need to go to the bathroom!");
        listItems.add("Thank you!");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        quickMessages.setAdapter(adapter);

        temp_cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);startManagingCursor(temp_cursor);
        String[] s = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone._ID};
        int[] i = {android.R.id.text1, android.R.id.text2};
        final SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, temp_cursor, s, i);
        contacts.setAdapter(listAdapter);






        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) listAdapter.getItem(position);
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contactPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactSelected.setText(contactName);
                quickMessages.setVisibility(View.GONE);
                contacts.setVisibility(View.GONE);
                morseTap.setVisibility(View.VISIBLE);
            }
        });

        contactsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                quickMessages.setVisibility(View.GONE);
                morseTap.setVisibility(View.GONE);
                contacts.setVisibility(View.VISIBLE);
            }
        });

        sendSMSBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
                sms_view.eraseMorse_message();
                smsView.setText("");
                contactSelected.setText("");
            }
        });

        quickMessagesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                quickMessages.setVisibility(View.VISIBLE);
                morseTap.setVisibility(View.GONE);
                contacts.setVisibility(View.GONE);
            }
        });

        quickMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String escolha = listItems.get(position).toString();
                sms_view.eraseMorse_message();
                smsView.setText(escolha);
                quickMessages.setVisibility(View.GONE);
                contacts.setVisibility(View.GONE);
                morseTap.setVisibility(View.VISIBLE);

            }
        });

        backspaceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (sms_view.getMorse_message().length()>0) {
                    sms_view.removeChar();
                }
                System.out.println(sms_view.getMorse_message());

                if (smsView.getText().toString().length()>0) {
                    smsView.setText(smsView.getText().toString().substring(0, smsView.getText().toString().length() - 1));
                }
            }
        });

        morseTap.setOnTouchListener(new View.OnTouchListener() {
            
            CharacterNode characterNode = charTree.Tree[0];

            Timer final_letter;
            Timer final_word;

            boolean check = false;
            boolean next_letter = true;

            long action_down_moment;
            
            @Override
            public boolean onTouch (View arg0, MotionEvent arg1){
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        morseTap.setBackgroundColor(Color.parseColor("#1A9FC8"));

                        action_down_moment = System.currentTimeMillis();

                        if (check==true) {
                            final_letter.cancel();
                            final_word.cancel();
                        }
                        else {
                            check=true;
                        }

                        return true;

                    case MotionEvent.ACTION_UP:

                        morseTap.setBackgroundColor(Color.parseColor("#3ABFE8"));

                        long pressedPeriod = System.currentTimeMillis() - action_down_moment;

                        if (pressedPeriod <= 200) {
                            if(characterNode.getLeft() != null) {
                                characterNode = characterNode.getLeft();
                                System.out.println(characterNode.getCharacter());

                            }
                            else {
                                Toast.makeText(getApplicationContext(), "This letter does not exist!", Toast.LENGTH_LONG).show();
                                next_letter = false;

                            }

                            morse_code_view.addChar('.');
                            morseCodeView.setText(morse_code_view.getMorse_message());

                        } else {

                            if(characterNode.getLeft() != null) {
                                characterNode = characterNode.getRight();
                                System.out.println(characterNode.getCharacter());

                            }
                            else {
                                Toast.makeText(getApplicationContext(), "This letter does not exist!", Toast.LENGTH_LONG).show();
                                next_letter = false;
                            }

                            morse_code_view.addChar('-');
                            morseCodeView.setText(morse_code_view.getMorse_message());
                        }

                        final Handler mHandler = new Handler() {
                            public void handleMessage(Message msg) {

                                if (next_letter==true) {
                                    smsView.setText(sms_view.getMorse_message());
                                }

                                else {
                                    next_letter = true;
                                }
                                morseCodeView.setText(morse_code_view.getMorse_message());
                            }
                        };

                        final_letter = new Timer();
                        final_letter.schedule(new TimerTask() {
                            @Override
                            public void run() {

                                if (next_letter==true) {
                                    sms_view.addChar(characterNode.getCharacter());
                                }
                                morse_code_view.eraseMorse_message();
                                mHandler.obtainMessage(1).sendToTarget();
                                characterNode = charTree.Tree[0];
                            }
                        }, 800);

                        final_word = new Timer();
                        final_word.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                sms_view.addChar(' ');
                            }
                        }, 3000);

                        return true;
                    }
                return false;
            }
        });
    }

    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String phoneNo = contactPhone;
        String message = smsView.getText().toString();
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