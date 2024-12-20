package com.yoga.blescan


import android.app.Application
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
import java.util.Arrays

class MainApplication : Application(), ReactApplication {
    val packages: List<Any>
        get() = Arrays.asList<ReactPackage>(
            MainReactPackage(),
            BLEManagerPackage() // Register the BLEManager package
        )
}
