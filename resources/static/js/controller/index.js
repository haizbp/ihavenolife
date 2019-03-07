SCREEN_APP.indexVue = new Vue({
    el: "#id-container",
    data: {
		page: {
			current: 0,
			showMore: true,
			q: ''
		},
      	notifications: {
			data: [],
			show: true
		},
      	tags: [],
		cats: [],
		viewMode: [],
		post: []
    },
	methods: {
		loadMore: function(searchVal){
			var self = this;
			self.page.current = self.page.current + 1;
			self.page.q = searchVal;
			
			if(self.page.current == 1){
				self.post = [];
			}
			
			if(self.page.q == ''){
				Func.loadPost(self.page.current, function(data){
					self.post = self.post.concat( data.content );
					self.page.showMore = !(data.totalRecords == self.post.length);
					self.notifications.show = true;
				});
			}else{
				var param = "?q="+searchVal+"&page="+self.page.current;
				Func.loadHeaderPost(param, function(data){
					self.post = self.post.concat( data.content );
					self.page.showMore = !(data.totalRecords == self.post.length);
					self.notifications.show = false;
					$(window).scrollTop(0);
				});
			}
			
		},
		dataLoading: function () {
			var self = this;

			Func.loadMainNotification(function(data){
				self.notifications.data = data;
			});

			Func.loadTags(function(data){
				self.tags = data;
			});
			Func.loadCats(function(data){
				self.cats = data;
			});
			Func.loadFilterMode(function(data){
				self.viewMode = data;
			});
			Func.loadPost(self.page.current, function(data){
				self.loadMore('');
			});
		},
		dropdown(data, e){
			Func.dropdown(data, e);

	        e.preventDefault();
	        return false;
		},
		openMobileSearch(e){
			Func.openMobileSearch(e);
			e.preventDefault();
	        return false;
		},
		dropdownSelect(e){
			Func.dropdownSelect(e);
			e.preventDefault();
	        return false;
		},
		modeFilter(e){
			Func.modeFilter(e);
			e.preventDefault();
	        return false;
		}
	},
	mounted: function(){
		var self = this;
		self.dataLoading();
	}
});