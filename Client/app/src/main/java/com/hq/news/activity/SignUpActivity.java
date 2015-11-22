package com.hq.news.activity;

import android.content.Intent;
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

public class SignUpActivity extends KJActivity {

    @BindView(id = R.id.sign_up_email)
    private AutoCompleteTextView mEtEmail;
    @BindView(id = R.id.sign_up_password)
    private EditText mEtPass;
    @BindView(id = R.id.sign_up_username)
    private EditText mEtUsername;
    @BindView(id = R.id.email_sign_up_button, click = true)
    private Button signUp;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch(v.getId()) {
            case R.id.email_sign_up_button:
                doSignUp(mEtEmail.getText().toString(), mEtPass.getText().toString(),
                        mEtUsername.getText().toString());
                break;
            default:
                break;
        }
    }

    /**
     * Check input
     */
    private boolean inputCheck(String email, String password, String username) {
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
        if (StringUtils.isEmpty(username)) {
            ViewInject.toast(getString(R.string.error_field_required));
            return false;
        } else if (password.length() > 25) {
            ViewInject.toast(getString(R.string.username_too_long));
            return false;
        }
        return true;
    }

    private void doSignUp(String email, String password, String username) {
        if (!inputCheck(email, password, username)) {
            return;
        }

        UserApi.signUp(email, password, username, new HttpTaskListener() {
            @Override
            public void onSuccess(String content) throws JSONException {
                UIHelper.saveUser(aty, new Gson().fromJson(content, User.class));
                ViewInject.toast("Sign up success. Welcome " + UIHelper.getUser(aty).getUsername());

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
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
