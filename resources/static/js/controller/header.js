SCREEN_APP.headerVue = new Vue({
    el: "#id-header",
    data: {
		msg: 'test',
        tags: [],
		cats: [],
		viewMode: [],
		headerNav: [],
		aboutUs: [],
		logoMenu: [],
		notification: {
			count: 0,
			list: []
		},
		search: {
			value: ''
		}
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
			Func.loadHeaderNavs(function(data){
				self.headerNav = data;
			});
			Func.loadAboutus(function(data){
				self.aboutUs = data;
			});
			Func.loadLogoMenu(function(data){
				self.logoMenu = data;
			});
			Func.loadNotification(function(data){
				self.notification.list = data.list;
				self.notification.count = data.count;
			});
		},
		dropdown(data, e){
			Func.dropdown(data, e);
		},
		openMobileSearch(e){
			Func.openMobileSearch(e);
		},
		dropdownSelect(e){
			Func.dropdownSelect(e);
		},
		doSearch(e){
			var self = this;
			SCREEN_APP.indexVue.page.current = 0;
			SCREEN_APP.indexVue.loadMore(self.search.value);
			e.preventDefault();
	        return false;
		}
	},
	mounted: function(){
		var self = this;
		self.dataLoading();
	}
});