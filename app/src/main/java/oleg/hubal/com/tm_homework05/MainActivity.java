package oleg.hubal.com.tm_homework05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText etUserEmail, etEmailSubject, etEmailText;
    private Button btnSendEmail, btnCall;
    private String userEmail, emailSubject, emailText;

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
                    Toast.makeText(this, "Email correct", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCall_AM:

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
}
