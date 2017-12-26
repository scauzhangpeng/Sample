package com.xyz.sample.notification;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.xyz.sample.R;
import com.xyz.util.notification.NotificationUtil;

import java.util.Arrays;

/**
 * Created by ZP on 2017/12/21.
 */

public class NotificationActivity extends AppCompatActivity {


    private Spinner mPresetSpinner;
    private Spinner mPrioritySpinner;
    private Spinner mActionsSpinner;

    private EditText mTitleEditor;
    private EditText mTextEditor;
    private EditText mSubTextEditor;
    private EditText mTickerEditor;

    private CheckBox mIncludeLargeIconCheckbox;
    private CheckBox mLocalOnlyCheckbox;
    private CheckBox mIncludeContentIntentCheckbox;
    private CheckBox mVibrateCheckbox;
    private CheckBox mSoundCheckbox;
    private CheckBox mDefaultCheckbox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_notification);
        initView();
    }

    private void initView() {
        initEditor();
        initCheckBox();
        initSpinner();
    }

    private void initSpinner() {
        mActionsSpinner = (Spinner) findViewById(R.id.actions_spinner);
        initPresetSpinner();
        initPrioritySpinner();

    }

    private void initPrioritySpinner() {
        mPrioritySpinner = (Spinner) findViewById(R.id.priority_spinner);
        mPrioritySpinner.setAdapter(new SpinnerAdapter(this, PriorityPresets.PRESETS));
        mPrioritySpinner.setSelection(Arrays.asList(PriorityPresets.PRESETS).indexOf(PriorityPresets.DEFAULT));
        mPrioritySpinner.post(new Runnable() {
            @Override
            public void run() {
                mPrioritySpinner.setOnItemSelectedListener(new UpdateNotificationOnItemClick());
            }
        });
    }

    private void initPresetSpinner() {
        mPresetSpinner = (Spinner) findViewById(R.id.preset_spinner);
        mPresetSpinner.setAdapter(new SpinnerAdapter(this,
                NotificationPresets.PRESETS));
        mPresetSpinner.post(new Runnable() {
            @Override
            public void run() {
                mPresetSpinner.setOnItemSelectedListener(new UpdateNotificationOnItemClick());
            }
        });
    }

    private void initEditor() {
        mTitleEditor = (EditText) findViewById(R.id.title_editor);
        mTextEditor = (EditText) findViewById(R.id.text_editor);
        mSubTextEditor = (EditText) findViewById(R.id.sub_text_editor);
        mTickerEditor = (EditText) findViewById(R.id.ticker_editor);

        mTitleEditor.setText(getString(R.string.example_content_title));
        mTextEditor.setText(getString(R.string.example_content_text));
        mSubTextEditor.setText(getString(R.string.example_content_sub));
        mTickerEditor.setText(getString(R.string.example_ticker));
    }

    private void initCheckBox() {
        mIncludeLargeIconCheckbox = (CheckBox) findViewById(R.id.include_large_icon_checkbox);
        mLocalOnlyCheckbox = (CheckBox) findViewById(R.id.local_only_checkbox);
        mIncludeContentIntentCheckbox = (CheckBox) findViewById(R.id.include_content_intent_checkbox);
        mVibrateCheckbox = (CheckBox) findViewById(R.id.vibrate_checkbox);
        mSoundCheckbox = (CheckBox) findViewById(R.id.sound_checkbox);
        mDefaultCheckbox = (CheckBox) findViewById(R.id.default_all_checkbox);

        mIncludeLargeIconCheckbox.setOnCheckedChangeListener(new UpdateNotificationsOnCheckedChangeListener());
        mLocalOnlyCheckbox.setOnCheckedChangeListener(new UpdateNotificationsOnCheckedChangeListener());
        mIncludeContentIntentCheckbox.setOnCheckedChangeListener(new UpdateNotificationsOnCheckedChangeListener());
        mVibrateCheckbox.setOnCheckedChangeListener(new UpdateNotificationsOnCheckedChangeListener());
        mSoundCheckbox.setOnCheckedChangeListener(new UpdateNotificationsOnCheckedChangeListener());
        mDefaultCheckbox.setOnCheckedChangeListener(new UpdateNotificationsOnCheckedChangeListener());
    }

    private class UpdateNotificationsOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            postNotification();
        }
    }

    private class UpdateNotificationOnItemClick implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            postNotification();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class SpinnerAdapter extends ArrayAdapter<NamedPreset> {
        public SpinnerAdapter(Context context, NamedPreset[] presets) {
            super(context, R.layout.simple_spinner_item, presets);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setText(getString(getItem(position).nameResId));
            return view;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) getLayoutInflater().inflate(
                    android.R.layout.simple_spinner_item, parent, false);
            view.setText(getString(getItem(position).nameResId));
            return view;
        }
    }

    private void postNotification() {

        Notification[] notifications = buildNotifications();

        // Post new notifications
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        for (int i = 0; i < notifications.length; i++) {
            NotificationUtil.showNotify(this, i, notifications[i]);
        }
    }

    /**
     * Build the sample notifications
     */

    private Notification[] buildNotifications() {
        NotificationPreset preset = NotificationPresets.PRESETS[
                mPresetSpinner.getSelectedItemPosition()];
        PriorityPresets.SimplePriorityPreset priorityPreset =
                PriorityPresets.PRESETS[mPrioritySpinner.getSelectedItemPosition()];
//        ActionsPreset actionsPreset = ActionsPresets.PRESETS[
//                mActionsSpinner.getSelectedItemPosition()];
//        if (preset.actionsRequired() && actionsPreset == ActionsPresets.NO_ACTIONS_PRESET) {
//            // If actions are required, but the no-actions preset was selected, change presets.
//            actionsPreset = ActionsPresets.SINGLE_ACTION_PRESET;
//            mActionsSpinner.setSelection(Arrays.asList(ActionsPresets.PRESETS).indexOf(
//                    actionsPreset), true);
//        }
        NotificationPreset.BuildOptions options = new NotificationPreset.BuildOptions(
                mTitleEditor.getText(),
                mTextEditor.getText(),
                mSubTextEditor.getText(),
                mTickerEditor.getText().toString(),
                priorityPreset,
                mIncludeLargeIconCheckbox.isChecked(),
                mLocalOnlyCheckbox.isChecked(),
                mIncludeContentIntentCheckbox.isChecked(),
                mVibrateCheckbox.isChecked(),
                mSoundCheckbox.isChecked(),
                mDefaultCheckbox.isChecked());
        return preset.buildNotifications(this, options);
    }

    @Override
    protected void onResume() {
        super.onResume();
        postNotification();
    }

    public void percentProgress(View view) {
        int current = 0;
        final NotificationUtil.Builder builder = new NotificationUtil.Builder(this);
        builder.setContentTitle("下载进度条标题");
        builder.setContentText("下载进度条内容");
        builder.setSmallIcon(R.mipmap.ic_notification);
        builder.setLargeIcon(R.drawable.example_large_icon);
        for (int i = 1; i < 100; i++) {
            current += i;
            builder.updateProgress(current);
            NotificationUtil.showNotify(NotificationActivity.this, 2, builder.build());
        }
    }
}
