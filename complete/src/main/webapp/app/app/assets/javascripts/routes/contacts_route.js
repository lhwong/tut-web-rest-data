App.ContactsRoute = Ember.Route.extend({
  model: function() {
    // request all contacts from adapter
	return this.store.find('contact');
    /*var store = this.store;
    
    // filter contacts to exclude new ones
    return this.store.filter('contact', function(contact) {
      return !contact.get('isNew');
      
    });*/
  }
});
