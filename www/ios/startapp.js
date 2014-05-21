var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var StartApp = function() { 
};

StartApp.start = function(params, success, fail) {
    var component = params.ios;
    exec(success, fail, "StartApp", "start", [component]);
};

module.exports = StartApp;
