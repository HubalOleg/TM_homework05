package oleg.hubal.com.tm_homework05;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText etUserEmail, etEmailSubject, etEmailText;
    private Button btnSendEmail, btnCall;
    private String userEmail, emailSubject, emailText;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendEmail_AM:
                getStrings();
//                Проверяем поля и email, если ошибки не найдено - отправляем письмо
                if (Methods.isEmptyFields(userEmail, emailSubject, emailText)) {
                    Toast.makeText(this, Constants.ERROR_EMPTY_FIELD, Toast.LENGTH_LONG).show();
                } else if (!Methods.isValidEmailAdress(userEmail)) {
                    Toast.makeText(this, Constants.ERROR_INCORECT_EMAIL, Toast.LENGTH_LONG).show();
                } else {
                    sendEmail();
                }
                break;
            case R.id.btnCall_AM:
                makeCall();
                break;
        }
    }

    //    Получаем текст из TextView и присваиваем в переменные
    private void getStrings() {
        emailSubject = etEmailSubject.getText().toString();
        emailText = etEmailText.getText().toString();
        userEmail = etUserEmail.getText().toString();
    }

    private void findViews() {
        etEmailSubject = (EditText) findViewById(R.id.etEmailSubject_AM);
        etUserEmail = (EditText) findViewById(R.id.etUserEmail_AM);
        etEmailText = (EditText) findViewById(R.id.etEmailText_AM);

        btnSendEmail = (Button) findViewById(R.id.btnSendEmail_AM);
        btnCall = (Button) findViewById(R.id.btnCall_AM);
    }

    private void setListeners() {
        btnSendEmail.setOnClickListener(this);
        btnCall.setOnClickListener(this);
    }

    private void sendEmail() {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
//        Передаем значения с полей и предлагаем приложения для отправки на выбор
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{userEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
        startActivity(Intent.createChooser(emailIntent, Constants.CHOOSER_TITLE));
    }


    private void makeCall() {
        number = String.format("tel:%s", Constants.EXTRA_NUMBER);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Constants.PERMISSIONS_REQUEST_CALL_PHONE);
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(number)));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    makeCall();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "ACTION_CALL Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
