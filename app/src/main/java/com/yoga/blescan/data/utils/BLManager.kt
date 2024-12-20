package com.yoga.blescan.data.utils

// BLEManager.kt
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule

class BLEManager(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule() {

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager = reactContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }
    private val handler = Handler(Looper.getMainLooper())

    override fun getName(): String = "BLEManager"

    @ReactMethod
    fun startScan() {
        bluetoothAdapter.bluetoothLeScanner?.startScan { result ->
            sendEvent("BleManagerDiscoverPeripheral", result.device.name ?: "Unknown")
        }
    }

    @ReactMethod
    fun stopScan() {
        bluetoothAdapter.bluetoothLeScanner?.stopScan { result ->
            sendEvent("BleManagerScanStopped", "Scan stopped")
        }
    }

    private fun sendEvent(eventName: String, data: String) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, data)
    }
}
