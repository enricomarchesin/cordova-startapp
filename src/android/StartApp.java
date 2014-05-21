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


/**
 * Launches an external application.
 *
 * @author Dmitry Medvinsky <dmedvinsky@gmail.com>
 * @license MIT/X11
 */
public class StartApp extends CordovaPlugin
{
    /**
     * Executes the request and returns PluginResult.
     *
     * @param action
     *          Action to perform.
     * @param args
     *          Arguments to the action.
     * @param callbackId
     *          JavaScript callback ID.
     * @return A PluginResult object with a status and message.
     */

    /**
     * Constructor.
     */
    public StartApp() {
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    {
        try {
            if (action.equals("startApp")) {
                if (args.length() != 1) {
		            callbackContext.error("invalid action");
                }
                String component = args.getString(0);
                startActivity(component);
                callbackContext.success();
		        return true;
            } else {
	            callbackContext.error("invalid action");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("invalid exception");
        }
		return false;
    }

    /**
     * Starts an activity.
     *
     * @param component
     *            Activity ComponentName.
     *            E.g.: com.mycompany.myapp/com.mycompany.myapp.MyActivity
     */
    void startActivity(String component) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(ComponentName.unflattenFromString(component));
        Context context=this.cordova.getActivity().getApplicationContext();
        context.startActivity(intent);
    }
}
