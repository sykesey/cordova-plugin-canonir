      var CanonIr = {

        instant_shutter: function(successCallback, errorCallback){
          cordova.exec(successCallback,
              errorCallback,
              "CanonIr",
              "instant_shutter",
                       []
          );
          console.log("Instant shutter run");
        },
	delayed_shutter: function(successCallback, errorCallback){
          cordova.exec(successCallback,
              errorCallback,
              "CanonIr",
              "delayed_shutter",
                       []
          );
        
          console.log("Delayed shutter run");
        }
      };
      module.exports = CanonIr;
