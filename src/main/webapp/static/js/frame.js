
//手机版
function screenSize(){
    if($(window).width() <= 768){
        $(".col-nav-list").hide();
        $(".btn-scroll-bar").hide();
        $(".btn-scroll-bar").parent().parent().removeClass("col-md-absolute");
        $(".btn-scroll-bar").parent().css("width","100%");
        $(".drop_show").show();
    }else{
        $(".col-nav-list").show();
        $(".btn-scroll-bar").show();
        $(".drop_show").hide();
    }
}
screenSize();
$(window).resize(function(){
    screenSize();
});
$(".drop_show").click(function(){
    $(".col-nav-list").slideToggle("slow");
});
//左侧菜单荷叶
$(function(){
    var $menu_title = $(".menu-title"),
        $next = $(".collapse-ul");
    $menu_title.each(function(i){
        $(this).click(function(){
            //console.log(i)//当前的索引号
            var $display = $($next[i]).css("display");
            if($display == "block"){
                $($next[i]).slideUp(300);
            }else{
                for(var j= 0,len = $next.length;j<len;j++){
                    $($next[j]).slideUp(300);
                }
                $($next[i]).slideDown(300);
            }
        });
    });
    //////当前项选中
    var $li = $(".menu-group li");
    $li.on("click",function(){
        for(var i= 0,len = $li.length;i<len ;i++){
            $li.eq(i).removeClass("collapse-active");
        }
        $(this).addClass("collapse-active");
    });
    ////
});
//隐藏显示左侧菜单栏
$(function(){
    var $scroll_bar = $(".btn-scroll-bar");
    $scroll_bar.click(function(){
        var $width =$(this).parent().css("width"),
            $parents = $(this).parent();
        if($width == "0px"){
            $parents.animate({width:"100%"},10,function(){
                $(this).parent().removeClass("col-md-absolute");
                $(this).parent().next().addClass("col-md-9");
                $(this).parent().next().removeClass("col-md-12");
            });

        }else{
            $parents.animate({width:"0px"},10,function(){
                $(this).parent().addClass("col-md-absolute");
                $(this).parent().next().addClass("col-md-12");
                $(this).parent().next().removeClass("col-md-9");
            });

        }

    });
});
