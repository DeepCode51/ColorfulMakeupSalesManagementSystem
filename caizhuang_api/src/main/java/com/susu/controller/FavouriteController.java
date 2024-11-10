package com.susu.controller;
import com.susu.entity.Favourite;
import com.susu.entity.Msg;
import com.susu.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/like")
public class FavouriteController {
    @Autowired
    private FavouriteService favouriteService;
    //添加收藏
    @RequestMapping("/addLike")
    public Msg addLike(@RequestBody Favourite favourite){
        favouriteService.addLike(favourite);
        return  Msg.success();
//        "userId":2,
//        "goodsId":1
    }

    //判断是否收藏
    @RequestMapping("/isLiked")
    public  Msg isLiked(@RequestBody Favourite favourite){
        Integer count=favouriteService.selectByUidAndPid(favourite);
        return Msg.success().add("isLike",count);
    }

    //查看收藏夹（这个接口不用了）
//    @RequestMapping("/showLike")
//    public Msg showLike(@RequestBody Favourite favourite){
//        int userId=favourite.getUserId();
//        List<Favourite> likeList=favouriteService.showLike(userId);
//        return  Msg.success().add("likeList",likeList);
////        "userId":1
//    }
    //取消收藏
//    http://localhost:8080/hf/like/cancelLike
    @RequestMapping("/cancelLike")
    public Msg cancelLike(@RequestBody Favourite favourite){
        int id=favourite.getId();
        favouriteService.cancelLike(id);
        return  Msg.success();
//        "id":4
    }

    //清空收藏夹
//    http://localhost:8080/hf/like/clearLike
    @RequestMapping("/clearLike")
    public Msg clearLike(@RequestBody Favourite favourite){
        int userId=favourite.getUserId();
        favouriteService.clearLike(userId);
        return  Msg.success();
//        "userId":1
    }
    //批量删除
    @RequestMapping("/deleteByBatch")
    public  Msg deleteByBatch(String ids){
        System.out.println(ids);
        String[] d=ids.split(",");//把数组里的值逗号隔开
        favouriteService.deleteByBatch(d);
        return Msg.success();
//        ?ids=4,5
    }


    //搜索收藏夹的商品 当搜索为空的时候 显示所有的商品即是查看收藏夹的功能
    @RequestMapping("/likeSearch")
    public  Msg likeSearch(int userId,String searchname){
        List<Favourite> likeList=favouriteService.likeSearch(userId,searchname);
        return Msg.success().add("likeList",likeList);
    }

}
