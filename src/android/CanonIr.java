package com.obtaine.canonir;

import org.apache.cordova.CordovaPlugin;

import android.hardware.ConsumerIrManager;
import android.hardware.ConsumerIrManager.CarrierFrequencyRange;

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
    
    public static final String LOG_TAG = "CanonIr";


    @Override
    public boolean execute(String action, JSONArray jsonArgs, final CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_TRANSMIT_INSTANT_SHUTTER.equals(action)) {
                //frequency = 32768 (30 microsec per cycle)
                //16 pulses
                //delay = 7.33msec
                //16 pulses
                /*int[] pulse = [15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,
                               7330,
                               15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15];
*/
                
                final Context context = this.cordova.getActivity().getApplicationContext();
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        int[] pulse = new int[] {489, 7330, 489};
                        Log.d(LOG_TAG, "Set up pulse");
                        
                        
                        ConsumerIrManager irService = (ConsumerIrManager) context.getSystemService(context.CONSUMER_IR_SERVICE);
                        Log.d(LOG_TAG, "Got IR Service");
                        Log.d(LOG_TAG, "hasIrEmitter: " + irService.hasIrEmitter() );
                        Log.d(LOG_TAG, "About to get carrier freqs");
                        CarrierFrequencyRange[] range = irService.getCarrierFrequencies();
                        for(int i = 0; i < range.length; i++)
                        {
                            CarrierFrequencyRange r = range[i];
                            Log.d(LOG_TAG, "Carrier Frequency Range Min:" + r.getMinFrequency() + " Max: " + r.getMaxFrequency());
                        }
                        
                        
                        
                        irService.transmit( 32768, pulse );
                      
                        callbackContext.success("Ran instant_shutter");
                    }
                });
            }
            if (ACTION_TRANSMIT_DELAYED_SHUTTER.equals(action)) {
                //frequency = 32768 (30 microsec per cycle)
                //16 pulses
                //delay = 5.36msec
                //16 pulses 
                /*int[] pulse = [15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,
                               5360,
                               15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15];
*/
                
                
                final Context context = this.cordova.getActivity().getApplicationContext();
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        
                        int[] pulse = new int[] {489, 5360, 489};
                        Log.d(LOG_TAG, "Set up pulse");
                        
                        
                        ConsumerIrManager irService = (ConsumerIrManager) context.getSystemService(context.CONSUMER_IR_SERVICE);
                        Log.d(LOG_TAG, "Got IR Service");
                        
                        irService.transmit( 32768, pulse );
                        
                        Log.d(LOG_TAG, "Ran delayed shutter");
                        
                       
                        callbackContext.success("Ran delayed_shutter");
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

