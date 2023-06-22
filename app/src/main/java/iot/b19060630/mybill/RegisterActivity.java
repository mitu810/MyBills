package iot.b19060630.mybill;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import iot.b19060630.mybill.database.DBManager;
import iot.b19060630.mybill.database.DBOpenHelper;
import iot.b19060630.mybill.database.UserBean;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etEmail;
    private Button btnRegister;
    private DBManager dbManager;
    private DBOpenHelper DBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        btnRegister = findViewById(R.id.btn_register);

        DBOpenHelper = new DBOpenHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                UserBean userBean = new UserBean();
                userBean.setUsername(username);
                userBean.setPassword(password);
                userBean.setEmail(email);

                if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
                    // 插入用户信息到数据库
                    long userId = insertUser(userBean);

                    if (userId != -1) {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private long insertUser(UserBean userBean) {
//        SQLiteDatabase db = DBOpenHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("username", username);
//        values.put("password", password);
//        values.put("email", email);
        long log = dbManager.insertItemToUser(userBean);
        return log;
    }
}
