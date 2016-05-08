package com.vapenaysh.jace.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;

import Utility.ProgressGenerator;

/**
 *
 * This is for milestone 2 perhaps
 *
 */

public class CreateAccount extends Activity implements ProgressGenerator.OnCompleteListener{

    private EditText username;
    private EditText email;
    private EditText password;
    private ProgressGenerator progressGenerator;
    private ActionProcessButton createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


        progressGenerator = new ProgressGenerator(this);
        createAccount = (ActionProcessButton) findViewById(R.id.create_account);

        createAccount.setMode(ActionProcessButton.Mode.PROGRESS);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }


    private void createAccount(){

        final String emailAddress = email.getText().toString();
        final String userName = username.getText().toString();
        final String passWord = password.getText().toString();

        if(emailAddress.equals("") || userName.equals("") || passWord.equals("")){
            AlertDialog.Builder dialog = new AlertDialog.Builder(CreateAccount.this);
            dialog.setTitle("Empty Fields");
            dialog.setMessage("Please complete the form");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            dialog.show();
        }
        else{
            //Store to the dataBase

            progressGenerator.start(createAccount);
            createAccount.setEnabled(false);
            email.setEnabled(false);
            username.setEnabled(false);
            password.setEnabled(false);
            //Create a user object
            User user = new User(userName, emailAddress, passWord);

            //log them in right away
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComplete() {

    }
}
