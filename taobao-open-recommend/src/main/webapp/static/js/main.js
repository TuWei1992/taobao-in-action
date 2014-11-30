require.config({
　　　　baseUrl: "/static/js/",
　　　　paths: {
          "jquery": "jquery-1.11.0",
　　　    "jquery.lazyload": "plugins/jquery-lazyload/jquery.lazyload",
          "jquery.validate": "plugins/jquery-validation/jquery.validate",
          "bootstrap": "bootstrap",
          "bootstrap-switch": "bootstrap-switch",
          "bootstrapValidator": "plugins/bootstrapvalidator/js/bootstrapvalidator",
          "metisMenu": "plugins/metisMenu/metisMenu",
          "sb-admin-2": "sb-admin-2",
          "gotop","gotop"
　　　　},
    shim: {
        'jquery.lazyload': {
                deps: ['jquery'],
                exports: 'jQuery.fn.lazyload'
        },
        'jquery.validate': {
              deps: ['jquery'],
              exports: 'jQuery.fn.validate'
          }
　　　　},
        'bootstrap': {
              deps: ['jquery'],
              exports: 'bootstrap'
          }
　　　　},
        'bootstrap-switch': {
              deps: ['jquery'],
              exports: 'bootstrap-switch'
          }
　　　　},
        'bootstrapValidator': {
              deps: ['jquery'],
              exports: 'bootstrapValidator'
          }
　　　　},
        'metisMenu': {
              deps: ['jquery'],
              exports: 'metisMenu'
          }
　　　　},
        'sb-admin-2': {
              deps: ['jquery'],
              exports: 'sb-admin-2'
          }
　　　　},
        'gotop': {
              deps: ['jquery'],
              exports: 'gotop'
          }
　　　　}
});

require(['jquery'], function($) {
  alert($().jquery);

  $(document).goTop({
        showAfter:'#goTop', //必选 需执行返回顶部的元素
        debug:true,         //开启调试状态
        showPixels:10,     //滚动条滚动高度 单位 px
        scrollSpeed:1000,   //返回顶部速度
        callback:function(e){ //回调执行函数
            //alert(e);
        }
  });
  
});