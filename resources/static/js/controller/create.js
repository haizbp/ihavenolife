SCREEN_APP.createVue = new Vue({
    el: "#id-container",
    data: {
		model: {
			title: '',
			content: '',
			bColor: '',
			tag: '',
			tags: [],
			categories: []
		},
		categories: []
    },
	methods: {
		dataLoading: function () {
			var self = this;

			Func.loadCats(function(data){
				self.categories = data;
			});
		},
		catToggle: function(itm, e){
			var self = this;
			
			if($(e.target).hasClass('active')){
				 self.model.categories = $.grep(self.model.categories, function (e) { 
					if(e.id == itm.id) {
						return false;
					} else {
						return true;
					}
				});
				
				$(e.target).removeClass('active');
				$(e.target).removeClass('color-white');
				$(e.target).removeClass(itm.color);
			}else{
				self.model.categories.push(itm);
				$(e.target).addClass('active');
				$(e.target).addClass('color-white');
				$(e.target).addClass(itm.color);
			}
			
		},
		addTag: function(){
			var self = this;
			
			self.model.tags.push({
			      "color": Func.randomColor(),
			      "value": self.model.tag,
			      "key": self.model.tag
			    });
			
			self.model.tag = '';
		},
		doSubmit: function(searchVal){
			var self = this;
			
			if(self.model.tag !== ''){
				self.model.tags.push({
				      "color": Func.randomColor(),
				      "value": self.model.tag,
				      "key": self.model.tag
				    });
				self.model.tag = '';
			}
			
			self.model.bColor = COLOR[Math.floor(Math.random()*COLOR.length)];
			Func.addTopic(self.model, function(data){
				console.log(data);
			});
			
		},
		doReset: function(){
			var self = this;
			
			self.model.title = '';
			self.model.content = '';
			self.model.bColor = '';
			self.model.tags = [];
			self.model.categories = [];
		}
	},
	mounted: function(){
		var self = this;
		self.dataLoading();
	}
});