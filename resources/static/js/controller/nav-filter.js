$.ajaxSetup({
  headers: {
    'secret-key': '$2a$10$e.SCGqA6Ms01whmgoDhEyeYIfk42tpMn4F1KQy1qtVsTeGaIyo6M6'
  }
});

var app = new Vue({
    el: "#id-nav-filter",
    data: {
		msg: 'test',
        tags: [],
		cats: [],
		viewMode: []
    },
	methods: {
		dataLoading: function () {
			var self = this;

			Func.loadTags(function(data){
				self.tags = data;
			});
			Func.loadCats(function(data){
				self.cats = data;
			});
			Func.loadFilterMode(function(data){
				self.viewMode = data;
			});
		},
		dropdown(data, e){
			Func.dropdown(data, e);
		},
		dropdownSelect(e){
			Func.dropdownSelect(e);
		},
		modeFilter(e){
			Func.modeFilter(e);
		}
	},
	mounted: function(){
		var self = this;
		self.dataLoading();
	}
});