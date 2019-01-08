package ceuilisa.mirai.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.io.IOException;

import ceuilisa.mirai.R;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.ImeiUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RenzhenActivity extends AppCompatActivity {

    private EditText nameEdit, pwdEdit;
    private Button mButton;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzhen);
        mContext = this;
        nameEdit = findViewById(R.id.username);
        pwdEdit = findViewById(R.id.password);
        mButton = findViewById(R.id.login_now);
        mButton.setOnClickListener(v -> checkInput());
    }

    private void checkInput(){
        if(nameEdit.getText().toString().length() != 0){
            if(pwdEdit.getText().toString().length() != 0){
                login(nameEdit.getText().toString(), pwdEdit.getText().toString());
            }else {
                Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
            }
        }else {
             Toast.makeText(mContext, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }
    }



}
