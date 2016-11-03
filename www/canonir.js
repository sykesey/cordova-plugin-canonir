      var canonir = {

        instant_shutter: function(successCallback, errorCallback){
          cordova.exec(successCallback,
              errorCallback,
              "CanonIr",
              "instant_shutter",
          );

        },

	delayed_shutter: function(successCallback, errorCallback){
          cordova.exec(successCallback,
              errorCallback,
              "CanonIr",
              "delayed_shutter",
          );

        }

        
        
      }
      module.exports = canonir;
