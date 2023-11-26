package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Dao.AdminDAO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edUserName, edPassword;
    TextInputLayout in_user, in_pass;
    Button btnLogin;
    TextView btnDangky;
    CheckBox chkRememberPass;
    AdminDAO adminDAO = new AdminDAO(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        in_user = findViewById(R.id.in_User);
        in_pass = findViewById(R.id.in_Pass);
        btnLogin = findViewById(R.id.btnLogin);
        btnDangky = findViewById(R.id.btnDangky);
        chkRememberPass = findViewById(R.id.chkRememberPass);

        // doc user, pass trong SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean rem = pref.getBoolean("REMEMBER",false);

        edUserName.setText(user);
        edPassword.setText(pass);
        chkRememberPass.setChecked(rem);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkLogin();
            }
        });

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Dangky.class));

            }
        });

    }

    public  void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            //xoa tinh trang luu tru truoc do
            edit.clear();
        }else{
            //luu du lieu
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        //luu lai toan bo
        edit.commit();
    }

    public void  checkLogin(){
        String user = edUserName.getText().toString();
        String pass = edPassword.getText().toString();
        if (user.isEmpty() || pass.isEmpty()){
            if (user.equals("")){
                in_user.setError("Không được để trống tên đăng nhập");
            }else{
                in_user.setError(null);
            }
            if (pass.equals("")){
                in_pass.setError("Không được để trống mật khẩu");
            }else {
                in_pass.setError(null);
            }
        }else {
            if (adminDAO.checkLogin(user,pass)){
//                if (adminDAO.checkRoleAdmin(user) == 0){
//                    Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();
//                } else if (adminDAO.checkRoleAdmin(user) == 1){
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                }
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(user,pass,chkRememberPass.isChecked());
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("maTT",user);
                startActivity(i);
                finish();
            }else {
                in_user.setError("Tên đăng nhập hoặc mật khẩu không đúng");
                in_pass.setError("Tên đăng nhập hoặc mật khẩu không đúng");
            }
        }
    }
}