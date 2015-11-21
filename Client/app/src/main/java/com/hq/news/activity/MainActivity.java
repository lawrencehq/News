package com.hq.news.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.hq.news.R;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

public class MainActivity extends KJActivity {

    @BindView(id = R.id.jumpto_login, click = true)
    private Button mBtnJumptoLogin;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.jumpto_login:
                Intent intent = new Intent();
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
