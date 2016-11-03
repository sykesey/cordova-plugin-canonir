package com.obtaine.canonir;

import org.apache.cordova.CordovaPlugin;

import android.hardware.ConsumerIrManager;

import org.apache.cordova.CallbackContext;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.lang.String;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Runnable;

public class CanonIr extends CordovaPlugin {
    public static final String ACTION_TRANSMIT_INSTANT_SHUTTER = "instant_shutter";
    public static final String ACTION_TRANSMIT_DELAYED_SHUTTER = "delayed_shutter";


    @Override
    public boolean execute(String action, JSONArray jsonArgs, final CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_TRANSMIT_INSTANT_SHUTTER.equals(action)) {
                //frequency = 32768 (30 microsec per cycle)
                //16 pulses
                //delay = 7.33msec
                //16 pulses
                int[] pulse = [15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,
                               7330,
                               15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15];

                final Context context = this.cordova.getActivity().getApplicationContext();
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        ConsumerIrManager irService = (ConsumerIrManager) context.getSystemService(context.CONSUMER_IR_SERVICE);
                        irService.transmit( 32768, pulse );
                        
                        /*if (android.os.Build.VERSION.SDK_INT == 19) {
                            int lastIdx = android.os.Build.VERSION.RELEASE.lastIndexOf(".");
                            int VERSION_MR = Integer.valueOf(android.os.Build.VERSION.RELEASE.substring(lastIdx + 1));
                            if (VERSION_MR < 3) {
                                int t = 1000000 / frequency;

                                for (int i = 0; i < signal.length; ++i) {
                                    signal[i] = signal[i] * t;
                                }
                                irService.transmit(32768, signal);
                            } else {
                                irService.transmit(38400, signal);
                            }

                        }*/
                        callbackContext.success("aja");
                    }
                });
            }
            if (ACTION_TRANSMIT_DELAYED_SHUTTER.equals(action)) {
                //frequency = 32768 (30 microsec per cycle)
                //16 pulses
                //delay = 5.36msec
                //16 pulses 
                int[] pulse = [15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,
                               5360,
                               15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15];

                final Context context = this.cordova.getActivity().getApplicationContext();
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        ConsumerIrManager irService = (ConsumerIrManager) context.getSystemService(context.CONSUMER_IR_SERVICE);
                        irService.transmit( 32768, pulse );
                        
                        /*if (android.os.Build.VERSION.SDK_INT == 19) {
                            int lastIdx = android.os.Build.VERSION.RELEASE.lastIndexOf(".");
                            int VERSION_MR = Integer.valueOf(android.os.Build.VERSION.RELEASE.substring(lastIdx + 1));
                            if (VERSION_MR < 3) {
                                int t = 1000000 / frequency;

                                for (int i = 0; i < signal.length; ++i) {
                                    signal[i] = signal[i] * t;
                                }
                                irService.transmit(32768, signal);
                            } else {
                                irService.transmit(38400, signal);
                            }

                        }*/
                        callbackContext.success("aja");
                    }
                });
            }

            return true;

        } catch (
                Exception e
                )

        {
            callbackContext.error("java ".concat(e.getMessage()));
            return false;
        }

    }
}

