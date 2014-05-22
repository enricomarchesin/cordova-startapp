package com.phonegap.plugins.startapp;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;


/**
 * Launches an external application.
 *
 * @author Dmitry Medvinsky <dmedvinsky@gmail.com>
 * @license MIT/X11
 */
public class StartApp extends CordovaPlugin {
    public StartApp() {
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("start")) {
			if (args.length() != 1) {
				callbackContext.error("invalid action");
				return false;
			}
			String component = args.getString(0);
			startActivity(component);
			callbackContext.success();
		} else {
			return false;
		}
        return true;
    }

    /**
     * Starts an activity.
     *
     * @param component
     *            Activity ComponentName.
     *            E.g.: com.mycompany.myapp/com.mycompany.myapp.MyActivity
     */
    void startActivity(String component) {
      Context context=this.cordova.getActivity();
      Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(component);
      context.startActivity(LaunchIntent);        
    }
}
