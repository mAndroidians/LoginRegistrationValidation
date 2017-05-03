package in.co.loginregistrationvalidation;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private View loginLayout, registrationLayout;
    private Button btnLogin, btnSignUp;


    //login
    private TextInputEditText loginMobileNo, loginPass;

    //signUp
    private TextInputEditText regFirstName, regEmail, regMobileNo, regPass, regConfirmPas;
    private String firstName,email,mobile,pass,confirm_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginLayout = findViewById(R.id.include_login_layout);
        registrationLayout = findViewById(R.id.include_sign_up_layout);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        findViewById(R.id.text_view_skip).setOnClickListener(this);


        //login
        loginMobileNo = (TextInputEditText) findViewById(R.id.txt_input_edt_txt_mobile_no);
        loginPass = (TextInputEditText) findViewById(R.id.txt_input_edt_txt_pass);
        findViewById(R.id.btn_layout_log_in).setOnClickListener(this);


        //signUp
        regFirstName = (TextInputEditText) findViewById(R.id.tx_full_name);
        regEmail = (TextInputEditText) findViewById(R.id.tx_email);
        regMobileNo = (TextInputEditText) findViewById(R.id.tx_mobile_no);
        regPass = (TextInputEditText) findViewById(R.id.tx_password);
        regConfirmPas = (TextInputEditText) findViewById(R.id.tx_confirm_password);
        findViewById(R.id.bt_layout_sign_up).setOnClickListener(this);


        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                btnLogin.setBackgroundColor(getResources().getColor(R.color.login_layout_bg));
                btnSignUp.setBackgroundColor(getResources().getColor(R.color.white));
                loginLayout.setVisibility(View.VISIBLE);
                registrationLayout.setVisibility(View.INVISIBLE);

                break;
            case R.id.btn_sign_up:
                btnLogin.setBackgroundColor(getResources().getColor(R.color.white));
                btnSignUp.setBackgroundColor(getResources().getColor(R.color.login_layout_bg));
                loginLayout.setVisibility(View.INVISIBLE);
                registrationLayout.setVisibility(View.VISIBLE);

                break;
            case R.id.text_view_skip:

                finish();
                break;

            case R.id.btn_layout_log_in:
                login();

                break;

            case R.id.bt_layout_sign_up:
                if(validate()) {
                    //signup();
                    // Toast.makeText(MainActivity.this, "SignUp", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private boolean validate() {
        firstName = regFirstName.getText().toString().trim();
        email = regEmail.getText().toString().trim();
        mobile = regMobileNo.getText().toString().trim();
        pass = regPass.getText().toString();
        confirm_pass = regConfirmPas.getText().toString();
        View focusView = null;

        if(TextUtils.isEmpty(firstName) || firstName.length() < 0 || firstName.length() > 51)
        {
            YoYo.with(Techniques.Shake).duration(700).playOn(regFirstName);
            regFirstName.setError(getString(R.string.ERR_FIRSTNAME));
            focusView = regFirstName;
            focusView.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(email) || !Utils.emailValidator(email))
        {
            YoYo.with(Techniques.Shake).duration(700).playOn(regEmail);
            regEmail.setError(getString(R.string.ERR_INCORRECT_EMAIL));
            focusView = regEmail;
            focusView.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(mobile)) {
            YoYo.with(Techniques.Shake).duration(700).playOn(regMobileNo);
            regMobileNo.setError(getString(R.string.ERR_INCORRECT_MOBILE_NO));
            focusView = regMobileNo;
            focusView.requestFocus();
            return false;
        }else if (TextUtils.isEmpty(pass) || pass.length() < 5 || pass.length() > 31)
        {
            YoYo.with(Techniques.Shake).duration(700).playOn(regPass);
            regPass.setError(getString(R.string.error_short_password));
            focusView = regPass;
            focusView.requestFocus();
            return false;
        }
        else if (!TextUtils.isEmpty(pass) && !Utils.passwordValidator(pass))
        {
            YoYo.with(Techniques.Shake).duration(700).playOn(regPass);
            regPass.setError(getString(R.string.error_invalid_password));
            focusView = regPass;
            focusView.requestFocus();
            return false;
        }
        else if (!pass.equals(confirm_pass))
        {
            YoYo.with(Techniques.Shake).duration(700).playOn(regConfirmPas);
            regConfirmPas.setError(getString(R.string.ERR_PASSWORD_MISMATCH));
            focusView = regConfirmPas;
            focusView.requestFocus();
            return false;
        } else
            return true;
    }



    private void login() {
        loginMobileNo.setError(null);
        loginPass.setError(null);

        // Store values at the time of the login attempt.
        String phone_number = loginMobileNo.getText().toString().trim();
        String password = loginPass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // for add

        // Check for a valid password, if the user entered one.
        // Check for a valid email address.
        if (TextUtils.isEmpty(phone_number)) {
            YoYo.with(Techniques.Shake).duration(700).playOn(loginMobileNo);
            loginMobileNo.setError(getString(R.string.ERR_BLANKEMAIL));
            focusView = loginMobileNo;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            YoYo.with(Techniques.Shake).duration(700).playOn(loginPass);
            loginPass.setError(getString(R.string.ERR_BLANKPASS));
            focusView = loginPass;
            cancel = true;
        } else {
            // this is work
            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {

                Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
