package com.zoopark.permission.demo.permissions;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.zoopark.permission.RafikiPermissions;
import com.zoopark.permission.demo.R;
import com.zoopark.permission.demo.base.BaseActivity;
import com.zoopark.permission.demo.utils.ToastUtils;
import com.zoopark.permission.result.code.RafikiResultCode;
import com.zoopark.permission.result.listener.OnPermissionResultListener;
import com.zoopark.permission.result.strategy.impl.PermissionResultCustomStrategy;

public class CalendarActivity extends BaseActivity implements
        OnPermissionResultListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        findViewById(R.id.tv_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAppSettingClick();
            }
        });
    }

    public void onReadCalendarClick(View view) {
        if (RafikiPermissions.getInstance(this)
                .setResultStrategy(new PermissionResultCustomStrategy(this))
                .requestReadCalendar()) {
            Toast.makeText(this, "Already granted read calendar permission", Toast.LENGTH_SHORT).show();
        }
    }

    public void onWriteCalendarClick(View view) {
        if (RafikiPermissions.getInstance(this)
                .setResultStrategy(new PermissionResultCustomStrategy(this))
                .requestWriteCalendar()) {
            Toast.makeText(this, "Already granted write calendar permission", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionsResultGrant(int requestCode) {
        switch (requestCode) {
            case RafikiResultCode.RESULT_CODE_READ_CALENDAR:
                ToastUtils.showToast(this, "Read Calendar Granted");
                break;
            case RafikiResultCode.RESULT_CODE_WRITE_CALENDAR:
                ToastUtils.showToast(this, "Write Calendar Granted");
                break;
        }
    }

    @Override
    public void onPermissionsResultDenied(int requestCode) {
        switch (requestCode) {
            case RafikiResultCode.RESULT_CODE_READ_CALENDAR:
                ToastUtils.showToast(this, "Read Calendar Denied");
                break;
            case RafikiResultCode.RESULT_CODE_WRITE_CALENDAR:
                ToastUtils.showToast(this, "Write Calendar Denied");
                break;
        }
    }
}
