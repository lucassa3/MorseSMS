package br.edu.insper.morsesms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
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
    Button returnBtn;
    
    EditText smsView;
    TextView morseCodeView;
    TextView contactSelected;

    EditText morseTap;

    ListView quickMessages;
    ListView contacts;
    ListView morseToAlphabetList;
    ListView alphabetToMorseList;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    ArrayList<String> listItems;

    Cursor temp_cursor;

    String contactName;
    String contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendSMSBtn = (Button) findViewById(R.id.SendSMSBtn);
        backspaceBtn = (Button) findViewById(R.id.BackspaceBtn);
        quickMessagesBtn = (Button) findViewById(R.id.QuickMessagesBtn);
        contactsBtn = (Button) findViewById(R.id.ContactsBtn);
        returnBtn = (Button) findViewById(R.id.ReturnBtn);

        smsView = (EditText) findViewById(R.id.SMSView);
        morseCodeView = (TextView)  findViewById(R.id.MorseCodeView);
        contactSelected = (TextView)  findViewById(R.id.ContactSelected);
        
        morseTap = (EditText) findViewById(R.id.MorseTap);

        quickMessages = (ListView) findViewById(R.id.QuickMessages);
        contacts = (ListView) findViewById(R.id.Contacts);
        morseToAlphabetList = (ListView) findViewById(R.id.MorseToAlphabetList);
        alphabetToMorseList = (ListView) findViewById(R.id.AlphabetToMorseList);

        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;

        final CharacterNodeTree charTree = new CharacterNodeTree();
        final Msg sms_view = new Msg();
        final Msg morse_code_view =  new Msg();
        final MADictionary md = new MADictionary(charTree);
        final AMDictionary ad = new AMDictionary(charTree);

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, md.MAList);
        morseToAlphabetList.setAdapter(adapter2);

        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ad.MAList);
        alphabetToMorseList.setAdapter(adapter3);

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

        smsView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch (View arg0, MotionEvent arg1){
                return true; // the listener has consumed the event
            }
        });

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
                sendSMSBtn.setVisibility(View.VISIBLE);
                backspaceBtn.setVisibility(View.VISIBLE);
                quickMessagesBtn.setVisibility(View.VISIBLE);
                contactsBtn.setVisibility(View.VISIBLE);
                returnBtn.setVisibility(View.GONE);
            }
        });

        contactsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                quickMessages.setVisibility(View.GONE);
                morseTap.setVisibility(View.GONE);
                contacts.setVisibility(View.VISIBLE);
                returnBtn.setVisibility(View.VISIBLE);
                sendSMSBtn.setVisibility(View.GONE);
                backspaceBtn.setVisibility(View.GONE);
                quickMessagesBtn.setVisibility(View.GONE);
                contactsBtn.setVisibility(View.GONE);
                returnBtn.setVisibility(View.VISIBLE);
            }
        });

        sendSMSBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
                sms_view.eraseMorse_message();
                smsView.setText("");
                smsView.setSelection(smsView.getText().length());
                contactSelected.setText("");
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                quickMessages.setVisibility(View.GONE);
                contacts.setVisibility(View.GONE);
                morseTap.setVisibility(View.VISIBLE);
                sendSMSBtn.setVisibility(View.VISIBLE);
                backspaceBtn.setVisibility(View.VISIBLE);
                quickMessagesBtn.setVisibility(View.VISIBLE);
                contactsBtn.setVisibility(View.VISIBLE);
                returnBtn.setVisibility(View.GONE);
            }
        });



        quickMessagesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                quickMessages.setVisibility(View.VISIBLE);
                morseTap.setVisibility(View.GONE);
                contacts.setVisibility(View.GONE);
                returnBtn.setVisibility(View.VISIBLE);
                sendSMSBtn.setVisibility(View.GONE);
                backspaceBtn.setVisibility(View.GONE);
                quickMessagesBtn.setVisibility(View.GONE);
                contactsBtn.setVisibility(View.GONE);

            }
        });

        quickMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String escolha = listItems.get(position).toString();
                sms_view.eraseMorse_message();
                smsView.setText(escolha);
                smsView.setSelection(smsView.getText().length());
                quickMessages.setVisibility(View.GONE);
                contacts.setVisibility(View.GONE);
                morseTap.setVisibility(View.VISIBLE);
                sendSMSBtn.setVisibility(View.VISIBLE);
                backspaceBtn.setVisibility(View.VISIBLE);
                quickMessagesBtn.setVisibility(View.VISIBLE);
                contactsBtn.setVisibility(View.VISIBLE);
                returnBtn.setVisibility(View.GONE);
            }
        });

        backspaceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                vibe.vibrate(20);
                if (sms_view.getMorse_message().length()>0) {
                    sms_view.removeChar();
                }
                System.out.println(sms_view.getMorse_message());

                if (smsView.getText().toString().length()>0) {
                    smsView.setText(smsView.getText().toString().substring(0, smsView.getText().toString().length() - 1));
                    smsView.setSelection(smsView.getText().length());
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

                        if (pressedPeriod <= 250) {
                            if(characterNode.getLeft() != null) {
                                characterNode = characterNode.getLeft();
                                System.out.println(characterNode.getCharacter());
                            }
                            else {
                                Toast toast= Toast.makeText(getApplicationContext(), "This letter does not exist!", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                next_letter = false;
                            }

                            morse_code_view.addChar('.');
                            morseCodeView.setText(morse_code_view.getMorse_message());

                        } else {
                            if(characterNode.getRight() != null) {
                                characterNode = characterNode.getRight();
                                System.out.println(characterNode.getCharacter());

                            }
                            else {
                                Toast toast= Toast.makeText(getApplicationContext(),  "This letter does not exist!", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                next_letter = false;
                            }

                            morse_code_view.addChar('-');
                            morseCodeView.setText(morse_code_view.getMorse_message());
                        }

                        final Handler mHandler = new Handler() {
                            public void handleMessage(Message msg) {

                                if (next_letter==true) {
                                    smsView.setText(sms_view.getMorse_message());
                                    smsView.setSelection(smsView.getText().length());
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
                        }, 750);

                        final_word = new Timer();
                        final_word.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                sms_view.addChar(' ');
                                mHandler.obtainMessage(1).sendToTarget();
                            }
                        }, 2250);

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

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast toast= Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
            toast.show();
        }

        catch (Exception e) {
            if (smsView.getText().toString().length()==0) {
                Toast toast= Toast.makeText(getApplicationContext(), "Please, insert a body message", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            else if (contactPhone == null) {
                Toast toast= Toast.makeText(getApplicationContext(), "Please, insert the destination contact", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            e.printStackTrace();
        }

    }

}