package com.hq.news.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hq.news.R;
import com.hq.news.bean.User;
import com.hq.news.network.HttpTaskListener;
import com.hq.news.network.api.UserApi;
import com.hq.news.utils.InputCheck;
import com.hq.news.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.StringUtils;


/**
 * The login view of app.
 * @author hq
 * @date 20/11/2015
 * @since 1.0
 */
public class LoginActivity extends KJActivity {

    @BindView(id = R.id.email)
    private AutoCompleteTextView mEtEmail;
    @BindView(id = R.id.password)
    private EditText mEtPass;
    @BindView(id = R.id.email_sign_in_button, click = true)
    private Button mBtnSignIn;
    @BindView(id = R.id.email_sign_up_button, click = true)
    private Button mBtnSignUp;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_login);
    }

    /**
     * Initiate email input widget
     */
    private void initEtEmail() {
        mEtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch(v.getId()) {
            case R.id.email_sign_in_button:
                doLogin(mEtEmail.getText().toString(), mEtPass.getText().toString());
                break;
            case R.id.email_sign_up_button:
                doSignUp(mEtEmail.getText().toString(), mEtPass.getText().toString());
                break;
            default:
                break;
        }
    }

    /**
     * Check input
     */
    private boolean inputCheck(String email, String password) {
        if (StringUtils.isEmpty(email)) {
            ViewInject.toast(getString(R.string.error_field_required));
            return false;
        } else if (!InputCheck.checkEmail(email)) {
            ViewInject.toast(getString(R.string.error_invalid_email));
            return false;
        }
        if (StringUtils.isEmpty(password)) {
            ViewInject.toast(getString(R.string.error_field_required));
            return false;
        } else if (password.length() < 6) {
            ViewInject.toast(getString(R.string.password_too_short));
            return false;
        }
        return true;
    }

    private void doLogin(String email, String password) {
        if (!inputCheck(email, password)) {
            return;
        }

        UserApi.login(email, password, new HttpTaskListener() {
            @Override
            public void onSuccess(String content) throws JSONException {
                UIHelper.saveUser(aty, new Gson().fromJson(content, User.class));
                ViewInject.toast("Login success. Welcome " + UIHelper.getUser(aty).getUsername());

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFaileure(String content) {
                ViewInject.toast(content);
            }
        });
    }

    private void doSignUp(String email, String password) {
        if (!inputCheck(email, password)) {
            return;
        }

        UserApi.signUp(email, password, new HttpTaskListener() {
            @Override
            public void onSuccess(String content) throws JSONException {
                UIHelper.saveUser(aty, new Gson().fromJson(content, User.class));
                ViewInject.toast("Sign up success. Welcome " + UIHelper.getUser(aty).getUsername());

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFaileure(String content) {
                ViewInject.toast(content);
            }
        });
    }
}
