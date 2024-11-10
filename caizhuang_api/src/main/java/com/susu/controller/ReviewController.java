package com.susu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.susu.entity.*;
import com.susu.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    //生成评价
    @RequestMapping("insertReview")
    public Msg insertReview(@RequestBody Review review){
        reviewService.insertReview(review);
        return Msg.success();
    }
    @RequestMapping("/upload")
    public Msg upload(MultipartFile file, ModelMap map)throws IOException {
        //图片上传成功后，将图片的地址写到数据库
        String filePath = "D:\\shop\\img\\review";//保存图片的路径
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字
        String newFileName = UUID.randomUUID()+".jpg";//不要加原始文件名称了
        //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        return Msg.success().add("imgData",newFileName);
    }

    //查看个人评价
    @RequestMapping("showReview")
    public Msg showReview(int pageNum,int pageSize,int userId,String degree){
        PageHelper.startPage(pageNum,pageSize);
        List<Review> reviewList=reviewService.showReview(userId,degree);
        PageInfo<Review> pageInfo=new PageInfo<Review>(reviewList);
        return Msg.success().add("pageInfo",pageInfo);
    }

    //评价id 查看评价内容
    //更改评价
    @RequestMapping("updateReview")
    public Msg updateReview(@RequestBody Review review){
        System.out.println("更改的评价review"+review);
        reviewService.updateReview(review);
        return Msg.success();
    }

    //（后台）查看所有的评价
    //可以选择好评 中评 差评==》degree ，订单id
    @RequestMapping("showBgReview")
    public Msg showBgReview(int pageNum,int pageSize,String degree,String isReply,String orderId){
        PageHelper.startPage(pageNum,pageSize);
        List<Review> reviewList=reviewService.showBgReview(degree,isReply,orderId);
        System.out.println(reviewList.toString());
        PageInfo<Review> pageInfo=new PageInfo<Review>(reviewList);
        return Msg.success().add("pageInfo",pageInfo);
    }

    //toBgDetail
    @RequestMapping("/toBgDetail")
    public Msg toBgDetail(@RequestBody Review review){
        Review review1=reviewService.toBgDetail(review);
        return Msg.success().add("review",review1);
    }

    //后台插入评价
    @RequestMapping("/insertBgReview")
    public Msg insertBgReview(@RequestBody BgReview bgReview){
        reviewService.insertBgReview(bgReview);
        return Msg.success();
    }

    //前台查看商家给的评价
    @RequestMapping("/reviewByBg")
    public Msg reviewByBg(int pageNum,int pageSize,Integer userId,String degree){
        PageHelper.startPage(pageNum,pageSize);
        List<BgReview> bgReviewList=reviewService.reviewByBg(userId,degree);
        PageInfo<BgReview> pageInfo=new PageInfo<BgReview>(bgReviewList);
        return Msg.success().add("pageInfo",pageInfo);
    }



    //根据商品id查看商品的评价（商品详情页）
    @RequestMapping("getGoodsReview")
    public Msg getGoodsReview(int pageNum,int pageSize,int goodsId,String degree){
        PageHelper.startPage(pageNum,pageSize);
        List<Review> reviewList=reviewService.getGoodsReview(goodsId,degree);
        PageInfo<Review> pageInfo=new PageInfo<Review>(reviewList);
        return Msg.success().add("pageInfo",pageInfo);

    }


    //删除商家回复的评价
    @RequestMapping("deleteByBatch")
    public Msg deleteByBatch(String ids) {
        System.out.println("ids----------->"+ids);

        //把数组里的值逗号隔开
//        String[] d = ids.split(",");
        int i = reviewService.deleteByPrimaryKey(Integer.valueOf(ids));

        if (i>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }

    //删除用户的评价
    @RequestMapping("deleteUserReviewById")
    public Msg deleteUserReviewById(String ids) {
        System.out.println("ids----------->"+ids);

        int i = reviewService.deleteUserReviewById(Integer.valueOf(ids));

        if (i>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
}
