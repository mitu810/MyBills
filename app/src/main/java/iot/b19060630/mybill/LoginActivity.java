package iot.b19060630.mybill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import iot.b19060630.mybill.database.DBManager;
import iot.b19060630.mybill.database.DBOpenHelper;
import iot.b19060630.mybill.database.UserBean;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private DBManager dbManager;
    private DBOpenHelper DBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        DBOpenHelper = new DBOpenHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    // 验证登录凭据
                    if (validateCredentials(username, password)) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败，请检查用户名和密码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到注册界面的代码
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateCredentials(String username, String password) {
//        SQLiteDatabase db = DBOpenHelper.getReadableDatabase();
//        String[] projection = {"id"};
//        String selection = "username = ? AND password = ?";
//        String[] selectionArgs = {username, password};
//        Cursor cursor = db.query("users", projection, selection, selectionArgs, null, null, null);
//        boolean result = cursor.getCount() > 0;
//        cursor.close();
//        return result;
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        UserBean userBean = dbManager.getUserByUsername(username,password);
        if (userBean == null){
            return false;
    }else {
            return true;
        }
    }
}

