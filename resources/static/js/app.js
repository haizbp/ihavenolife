const TAGS_LISTING_APPI = "/v1/tag";
const CATS_LISTING_API = "/v1/category";
const ADD_NEW_TOPIC_API = "/v1/post";
const VIEWMODE_LISTING_API = "https://api.jsonbin.io/b/5c18a80633a8fe76ff4e5a4a/latest ";
const HEADER_NAV_LISTING_API = "https://api.jsonbin.io/b/5c19c613412d482eae511688/latest ";
const ABOUT_US_API = "https://api.jsonbin.io/b/5c19cfc93f8bd92e4cbf0b07/latest ";
const LOGO_MANU_API = "https://api.jsonbin.io/b/5c19deb6412d482eae512be4/latest ";
const NOTIFICATION_API = "https://api.jsonbin.io/b/5c19e341412d482eae512f83/latest ";
const MAIN_NOTIFICATION_API = "https://api.jsonbin.io/b/5c1aed60412d482eae51e92c/latest ";
const MAIN_POST_API = "/v1/post/";
const HEADER_POST_API = "/v1/post/search";
const DROPDOWN_OPEN_CLASS = "dropdown--open";
const SEARCH_PANEL_OPEN_CLASS = "header--search";
const COLOR = [
'bg-f9bc64',
'bg-348aa7',
'bg-4436f8',
'bg-5dd39e',
'bg-ff755a',
'bg-bce784',
'bg-83253f',
'bg-c49bbb',
'bg-3ebafa',
'bg-c6b38e',
'bg-a7cdbd',
'bg-525252',
'bg-777da7',
'bg-368f8b',
'bg-fef2e0',
'bg-f2f4f6',
'bg-4f80b0',
'bg-424ee8',
'bg-36b7d7',
'bg-ec008c',
'bg-7cc576',
'bg-3a3a17',
'bg-6f7e9c',
'bg-f26522',
'bg-a3d39c',
'bg-6f7e9c',
'bg-92278f',
'bg-8781bd',
'bg-f1ab32',
'bg-3b96ca',
'bg-00bd9d',
'bg-218380'];
const SCREEN_APP = {};

$.ajaxSetup({
    headers: {
        'secret-key': '$2a$10$e.SCGqA6Ms01whmgoDhEyeYIfk42tpMn4F1KQy1qtVsTeGaIyo6M6'
    }
});

$(document).ajaxStart(function(){

});

$( document ).ajaxError(function() {

});

$( document ).ajaxStop(function() {

});

const Func = {
	randomColor: function(){
		return COLOR[Math.floor(Math.random()*COLOR.length)];
	},
	scrollTo: function(e, delay){
		$([document.documentElement, document.body]).animate({
			scrollTop: ($(e).offset().top )
		}, delay);
	},
	addTopic: function(body, callback){
		$.ajax({
            type: "POST",
            url: ADD_NEW_TOPIC_API, 
            data: JSON.stringify(body), 
			contentType: "application/json",
            success: function(data) {
                callback(data.data);
            }
        });
	},
    loadTags: function(callback) {
        $.get(TAGS_LISTING_APPI, function(data, status) {
            callback(data.data);
        });
    },
    loadCats: function(callback) {
        $.get(CATS_LISTING_API, function(data, status) {
            callback(data.data);
        });
    },
    loadFilterMode: function(callback) {
        $.get(VIEWMODE_LISTING_API, function(data, status) {
            callback(data.data);
        });
    },
    loadHeaderNavs: function(callback) {
        $.get(HEADER_NAV_LISTING_API, function(data, status) {
            callback(data.data);
        });
    },
    loadAboutus: function(callback) {
        $.get(ABOUT_US_API, function(data, status) {
            callback(data.data);
        });
    },
    loadLogoMenu: function(callback) {
        $.get(LOGO_MANU_API, function(data, status) {
            callback(data.data);
        });
    },
    loadNotification: function(callback) {
        $.get(NOTIFICATION_API, function(data, status) {
            callback(data.data);
        });
    },
    loadMainNotification: function(callback) {
        $.get(MAIN_NOTIFICATION_API, function(data, status) {
            callback(data.data);
        });
    },
    loadPost: function(page, callback) {
        $.get(MAIN_POST_API+page, function(data, status) {
            callback(data.data);
        });
    },
	loadHeaderPost: function(param, callback) {
        $.get(HEADER_POST_API+param, function(data, status) {
            callback(data.data);
        });
    },
    dropdown(data, e) {
        var list = $(e.target).parents('.' + data).find('[data-dropdown-list]');
        Func.toggleDropdown(list);
    },
    hideAllDropdown(){
        $("."+DROPDOWN_OPEN_CLASS).removeClass(DROPDOWN_OPEN_CLASS);
    },
    openMobileSearch(e) {
        var $t = $(e.target);
        var parent = $t.parents('.header');

        if (parent.hasClass(SEARCH_PANEL_OPEN_CLASS)) {
            parent.removeClass(SEARCH_PANEL_OPEN_CLASS)
        } else {
            parent.addClass(SEARCH_PANEL_OPEN_CLASS)
        }
    },
    dropdownSelect(e) {
        var $t = $(e.target);

        var value = $t.attr("data-value");
        var display = $t.attr("data-display");

        var $btn = $t.parents('.nav__select').find('[data-dropdown-btn]'),
        $value = $t.parents('.nav__select').find('[data-dropdown-value]'),
        $list = $t.parents('.nav__select').find('[data-dropdown-list]');

        $($value).val(value);
        $($btn).html(display);

        Func.toggleDropdown($list);
    },
    modeFilter(e) {
        var $t = $(e.target);
        var ul = $t.parents('ul').find('li').removeClass("active");
        $t.parents('li').addClass('active');
        $t.parents('ul').find('input[type="hidden"]').val($t.attr('data-value'));
    },
    toggleDropdown(e) {
        if (e.hasClass(DROPDOWN_OPEN_CLASS)) {
            e.removeClass(DROPDOWN_OPEN_CLASS)
            $('body').removeClass('in-show-dopdown');
        } else {
            e.addClass(DROPDOWN_OPEN_CLASS)
            $('body').addClass('in-show-dopdown');
        }
    }
}

/*
(function($, namespace) {
    'use strict';

    var app = {
        settins: {
            bp: 1024
        },
        dom: {
            $header: $('.js-header')
        },
        crnt: {
            init: function() {
                $(window).on('resize resizecurrent', function() {
                    app.crnt.m = window.innerWidth <= app.settins.bp;
                    app.crnt.d = !app.crnt.m;
                });

                $(window).trigger('resizecurrent');
            }
        },
        methods: {
            responsiveHandlers: function(obj) {
                $(window).on('resize.responsivehandlers responsivehandlers', function() {
                    if(obj.desktopBp === app.crnt.d) return;
                    else obj.desktopBp = app.crnt.d;

                    var $elem = $(obj.elem),
                        ns = obj.namespace ? '.' + obj.namespace : '';

                    if(obj.onDesktop === app.crnt.d) {
                        $.each(obj.events, function(event, func) {
                            $elem.on(event + ns, obj.delegate, func);
                        });
                    } else {
                        $.each(obj.events, function(key) {
                            $elem.unbind(key + ns);
                        });
                    }
                });

                $(window).trigger('responsivehandlers');
            }
        },
        searchVisibleOnMobileBtn: function() {
            var $header = app.dom.$header,
                $btn_open = app.dom.$header.find('.js-header-search-btn-open'),
                $btn_close = app.dom.$header.find('.js-header-search-btn-close');

            app.methods.responsiveHandlers({
                elem: $btn_open,
                namespace: 'searchopen',
                onDesktop: false,
                events: {
                    'click': function() {
                        $header.addClass('header--search');
                    }
                }
            });

            app.methods.responsiveHandlers({
                elem: $btn_close,
                namespace: 'searchclose',
                onDesktop: false,
                events: {
                    'click': function() {
                        $header.removeClass('header--search');
                    }
                }
            });
        },
        dropdowns: function() {

            function toggleList($list, $btn) {
                if(!$list.hasClass('js-dropdown-process')) {
                    $list.addClass('js-dropdown-process');

                    var process = $list.hasClass('dropdown--open') ? 'slideUp' : 'slideDown';

                    $btn = $btn || $list.parents('.js-dropdown').find('[data-dropdown-btn="' + $list.data('dropdown-btn') + '"]');

                    $list.velocity(process, {
                        complete: function() {
                            $list[process === 'slideUp' ? 'removeClass' : 'addClass']('dropdown--open');
                            $list.removeAttr('style').removeClass('js-dropdown-process');
                        }
                    });

                    $btn[process === 'slideUp' ? 'removeClass' : 'addClass']('dropdown__btn--open');
                }
            };

            function closeOpenLists($t) {
                var $list_close = $('[data-dropdown-list].dropdown--open');

                if($t) {
                    var $list_parent = $t.closest('[data-dropdown-list]');

                    $list_close = $list_close.not($list_parent)
                }

                toggleList($list_close);
            };

            $(document).on('click', '.js-dropdown [data-dropdown-btn]', function(e) {
                var $this = $(this),
                    namespace = $this.data('dropdown-btn'),
                    $list = $this.parents('.js-dropdown').find('[data-dropdown-list="' + namespace + '"]');

                closeOpenLists($list);

                toggleList($list, $this);

                e.preventDefault();
                return false;
            });

            $(document).on('click', function(e) {
                var $t = $(e.target);
				
				var value = $t.attr("data-value");
				var display = $t.attr("data-display");
				
				var $btn = $t.parents('.nav__select').find('[data-dropdown-btn]'),
					$value = $t.parents('.nav__select').find('[data-dropdown-value]');
				
				$($value).val(value);
				$($btn).html(display);
				
                closeOpenLists($t);
				e.preventDefault();
                return false;
            });
        },
        init: function() {
            for (var key in this) {
                delete this.init;

                if (typeof this[key] === 'function') {
                    this[key]();
                } else if (this[key].init && typeof this[key].init === 'function') {
                    this[key].init();
                }
            }

            return this;
        }
    };

    window[namespace] = app.init();
})(jQuery, 'app');
*/