App.ApplicationSerializer = DS.RESTSerializer.extend({
  serializeIntoHash: function(hash, type, record, options) {
    Ember.merge(hash, this.serialize(record, options));
  }
});

App.ApplicationAdapter = DS.RESTAdapter.extend({
  //bulkCommit: false,
  host: 'http://localhost:8080',
  //namespace: 'spring-music-master',
  //serializer: App.ApplicationSerializer
  
  /**
  The ActiveModelAdapter overrides the `ajaxError` method
  to return a DS.InvalidError for all 422 Unprocessable Entity
  responses.

  A 422 HTTP response from the server generally implies that the request
  was well formed but the API was unable to process it because the
  content was not semantically correct or meaningful per the API.

  For more information on 422 HTTP Error code see 11.2 WebDAV RFC 4918
  https://tools.ietf.org/html/rfc4918#section-11.2

  @method ajaxError
  @param jqXHR
  @returns error
  */
    ajaxError: function(jqXHR) {
      var error = this._super(jqXHR);
      
      if (jqXHR && jqXHR.status === 422) {
        var jsonErrors = Ember.$.parseJSON(jqXHR.responseText)["errors"],
            errors = {};

        Ember.keys(jsonErrors).forEach(function(key) {
            	errors[Ember.String.camelize(key)] = jsonErrors[key];
        	});


        return new DS.InvalidError(errors);
      } else {
        return error;
      }
    }
  
  
  
});

/*App.Adapter.map('App.Contact', {
  phoneNumbers: {embedded: 'always'}
});*/

App.Store = DS.Store.extend({
  revision: 12,
  adapter:  App.ApplicationAdapter
});
