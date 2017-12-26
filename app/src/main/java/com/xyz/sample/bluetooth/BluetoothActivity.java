package com.xyz.sample.bluetooth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xyz.sample.R;
import com.xyz.util.IntentUtil;
import com.xyz.util.bluetooth.BluetoothUtil;

/**
 * Created by ZP on 2017/11/14.
 */

public class BluetoothActivity extends Activity {

    private static final String TAG = "BluetoothActivity";
    private EditText et_visibility_time;

    private TextView tv_mac;
    private RadioButton rbtn_ble;
    private RadioButton rbtn_clazz;

    private Dialog mScanDialog;
    //    private ScanDevicesAdapter mAdapter;
//    private List<WrapperBlueDevice> mDevices;
    private ListView lv_scan_list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_bluetooth);
        initView();
//        if (BluetoothUtil.isBluetoothExit()) {
//            BleManager.getInstance().initBluetooth(this);
//            BleManager.getInstance().setBleListener(new BleContract.BleCallback() {
//                @Override
//                public void isBluetoothEnable(boolean isEnable) {
//                    Toast.makeText(BluetoothActivity.this, "蓝牙未开启", Toast.LENGTH_LONG).show();
//                }
//            });
//        } else {
//            Toast.makeText(BluetoothActivity.this, "不支持蓝牙", Toast.LENGTH_LONG).show();
//        }
//        仅仅扫描beacon设备
//        BleManager.getInstance().setOnlyBeacon(true);
    }

    private void initView() {

        et_visibility_time = (EditText) findViewById(R.id.et_visibility_time);
        tv_mac = (TextView) findViewById(R.id.tv_mac);
        initData();
//        rbtn_clazz = (RadioButton) findViewById(R.id.rbtn_clazz);
//        rbtn_ble = (RadioButton) findViewById(R.id.rbtn_ble);
//        lv_scan_list = (ListView) findViewById(R.id.lv_scan_list);
        initListView();
    }

    private void initListView() {
//        mDevices = new ArrayList<>();
//        mAdapter = new ScanDevicesAdapter(this, mDevices);
//        lv_scan_list.setAdapter(mAdapter);
    }


    private void initData() {
        tv_mac.setText("蓝牙地址:" + BluetoothUtil.getBluetoothMacAddress(this));
    }

    public void toBluetoothSetting(View view) {
        IntentUtil.toBluetoothSetting(BluetoothActivity.this);
    }

    public void openBluetoothSilent(View view) {
        boolean b = BluetoothUtil.enableBluetooth();
        if (b) {
            Toast.makeText(BluetoothActivity.this, "蓝牙启动成功", Toast.LENGTH_LONG).show();
        } else {
            //有可能用户禁止权限
            Toast.makeText(BluetoothActivity.this, "蓝牙启动失败", Toast.LENGTH_LONG).show();
        }
    }

    public void openBluetoothTip(View view) {
        BluetoothUtil.enableBluetooth(BluetoothActivity.this, 1000);
    }

    public void setVisibility(View view) {
        try {
            String s = et_visibility_time.getText().toString();
            int time = Integer.parseInt(s);
            BluetoothUtil.setVisibilityTime(BluetoothActivity.this, time, 1001);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void startScan(View view) {
//        mDevices.clear();
////        showScanDialog();
//        if (rbtn_ble.isChecked()) {
//            BleManager.getInstance().scanLeDevice(new BleContract.ScanCallback() {
//
//                @Override
//                public void isBluetoothEnable(boolean isEnable) {
//                    if (!isEnable) {
////                        dismissScanDialog();
//                        Toast.makeText(BluetoothActivity.this, "startScan->蓝牙未开启", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onScan(WrapperBlueDevice device) {
//                    mDevices.add(device);
//                    mAdapter.notifyDataSetChanged();
//                }
//            });
//        }
//
//        if (rbtn_clazz.isChecked()) {
//            BleManager.getInstance().scanClazzDevice(new BleContract.ScanCallback() {
//
//                @Override
//                public void isBluetoothEnable(boolean isEnable) {
//                    if (!isEnable) {
////                        dismissScanDialog();
//                        Toast.makeText(BluetoothActivity.this, "startScan->蓝牙未开启", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onScan(WrapperBlueDevice device) {
//                    mDevices.add(device);
//                    mAdapter.notifyDataSetChanged();
//                }
//            });
//        }
//
//    }
//
//    public void stopScan(View view) {
//        BleManager.getInstance().stopScan();
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(BluetoothActivity.this, "蓝牙启动成功", Toast.LENGTH_LONG).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(BluetoothActivity.this, "蓝牙启动取消", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 1001) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(BluetoothActivity.this, "蓝牙可见设置成功", Toast.LENGTH_LONG).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(BluetoothActivity.this, "蓝牙可见设置失败", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(BluetoothActivity.this, "蓝牙可见设置,resultCode:" + resultCode, Toast.LENGTH_LONG).show();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        BleManager.getInstance().onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
//        BleManager.getInstance().onPause();
//        dismissScanDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        BleManager.getInstance().onDestroy();
    }

//    private void showScanDialog() {
//        dismissScanDialog();
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_scan_device, null);
//        ListView lv = (ListView) view.findViewById(R.id.lv_scan_devices);
//        mAdapter = new ScanDevicesAdapter(this, mDevices);
//        lv.setAdapter(mAdapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                WrapperBlueDevice device = (WrapperBlueDevice) mAdapter.getItem(position);
//                String address = device.getBluetoothDevice().getAddress();
//                BleManager.getInstance().connectDevice(address);
//            }
//        });
//        mScanDialog = new AlertDialog.Builder(this).setView(view).
//                create();
//        mScanDialog.show();
//    }
//
//    private void dismissScanDialog() {
//        if (mScanDialog != null) {
//            if (mScanDialog.isShowing()) {
//                mScanDialog.dismiss();
//                mScanDialog = null;
//            }
//        }
//    }
}
