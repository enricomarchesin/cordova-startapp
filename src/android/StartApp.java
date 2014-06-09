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
import android.util.Log;


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
            Log.d("STARTAPP", "start");
			if (args.length() != 1) {
				callbackContext.error("invalid action");
				return false;
			}
			String component = args.getString(0);
			if (startActivity(component)) {
              callbackContext.success();
              return true;
            } else {
              callbackContext.error("application does not exist.");
		      return false;
            }
		} else {
			return false;
		}
    }

    /**
     * Starts an activity.
     *
     * @param component
     *            Activity ComponentName.
     *            E.g.: com.mycompany.myapp/com.mycompany.myapp.MyActivity
     */
    private boolean startActivity(String component) {
      Context context=this.cordova.getActivity();
      Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(component);
      
      if (launchIntent == null) {
        return false;
      }
      
      try {
        context.startActivity(launchIntent);
        return true;
      } catch (Exception e) {
        return false;
      }
    }
}

