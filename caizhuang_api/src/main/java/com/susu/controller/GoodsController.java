package com.susu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.susu.entity.Goods;
import com.susu.entity.Msg;
import com.susu.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodService goodService;
    //    public List<Goods> getList(){
    //        return goodService.getList();
    //    }
    /*
    * 后台数据管理
    * */


    //分类查询的商品
    @RequestMapping("getListByType")
    public  Msg getListByType(@RequestBody Goods goods){
        int id=goods.getGoodstypeId();
        List<Goods> goodsList=goodService.getListByType(id);
        return Msg.success().add("goodsList",goodsList);//list页面;

    }


//更新上架 下架
    @RequestMapping("/updateSale")
    public Msg updateSale(@RequestBody Goods goods){
        int id=goods.getId();
        int onsale=goods.getOnsale();
        goodService.updateSale(goods);
        return  Msg.success();

    }
    //    private Integer id;
//    private Integer goodstypeId;
//    private String goodsname;
//    private String producingArea;
//    private Date createTime;
//    private BigDecimal price;
//    private BigDecimal sellPrice;
//    private Integer stock;
//    private String picture;
//    private Boolean discount;
//    private Integer shopId;
//    private Date updateTime;
//    private String description;
    //更新商品信息
    @RequestMapping("/updateProduct")
    public Msg updateProduct(@RequestBody Goods goods){
        int id=goods.getId();
        int goodstypeId=goods.getGoodstypeId();
        String goodsname=goods.getGoodsname();
        String producingArea=goods.getProducingArea();
        Date createTime=goods.getCreateTime();
        BigDecimal price=goods.getPrice();
        BigDecimal sellPrice=goods.getSellPrice();
        int stock=goods.getStock();
        String picture=goods.getPicture();
        int discount=goods.getDiscount();
        Date updateTime=goods.getUpdateTime();
        String description=goods.getDescription();
        int onsale=goods.getOnsale();

        goodService.updateProduct(goods);
        return Msg.success();

    }
//    添加商品
    @RequestMapping(value="/addProduct",produces = {"application/json;charset=UTF-8"})
    public Msg addProduct(@RequestBody Goods goods) {
        int goodstypeId=goods.getGoodstypeId();
        String goodsname=goods.getGoodsname();
        String producingArea=goods.getProducingArea();
        Date createTime=goods.getCreateTime();
        BigDecimal price=goods.getPrice();
        BigDecimal sellPrice=goods.getSellPrice();
        int stock=goods.getStock();
        String picture=goods.getPicture();
        int discount=goods.getDiscount();
        Date updateTime=goods.getUpdateTime();
        String description=goods.getDescription();
        goodService.addProduct(goods);
        return Msg.success();
    }

    //删除商品
    @RequestMapping("/delProduct")
    public Msg delProduct(@RequestBody Goods goods){
        int id=goods.getId();
        System.out.println(id);
        goodService.delProduct(id);
        return Msg.success();
    }

    //批量删除
    @RequestMapping("/delProductByBatch")
    public Msg deleteByBatch(String ids){
        System.out.println(ids);
        String[] d=ids.split(",");//把数组里的值逗号隔开
        goodService.deleteByBatch(d);
        return  Msg.success();
    }

    //上传图片 并将图片的名称放入到数据库中
    @RequestMapping("/uploadImg")
    public Msg uploadImg(MultipartFile file, Goods goods,ModelMap map)throws IOException {
        //图片上传成功后，将图片的地址写到数据库
        //E:\idea\workspace\hf\img
        String filePath = "D:\\shop\\img";//保存图片的路径
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        //新的文件名字
        String newFileName = UUID.randomUUID()+originalFilename;
        System.out.println(newFileName);
        //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        return Msg.success().add("imgData",newFileName);

    }


    //pageHelper实现分页value={"/showAllProducts","/search"}
//    @RequestMapping(value = {"/getlistByPage1"})//get方法获取@RequestBody Goods goods
//    public Msg getlistByPage(int pageNum,int pageSize){
////        int shopId=goods.getShopId();
//        //指定当前是第几页，以及每页显示的数据条数
//        PageHelper.startPage(pageNum,pageSize);
//        //获取商品的记录
//        List<Goods> goodsList=goodService.getList();
//        //获取当前分页的对象
//        PageInfo<Goods> pageInfo=new PageInfo<Goods>(goodsList);
//
//        return Msg.success().add("pageInfo",pageInfo);//list页面;
//    }

    //搜索+条件查询+分页（后台）
    @RequestMapping("/getlistByPage")
    public Msg getSearch(int pageNum,int pageSize,String searchname,Integer typeId,Integer hasSale){
        System.out.println(searchname);
        PageHelper.startPage(pageNum,pageSize);
        List<Goods>  goodsList =goodService.getSearch(searchname,typeId,hasSale);

        //获取当前分页的对象
        PageInfo<Goods> pageInfo=new PageInfo<Goods>(goodsList);
        System.out.println("pageingo\n"+pageInfo);

        return Msg.success().add("pageInfo",pageInfo);

    }

    /*
     * 前台数据管理
     * */
    //获取商品列表，前台 (显示已上架的商品）onsale=1，查询前40条数据
    @RequestMapping("list")
    public Msg getList(){
        List<Goods> goodsList=goodService.getList();
        return Msg.success().add("goodsList",goodsList);

    }
    //获取

    //搜索商品列表 ，前台（显示已经上架的商品onSale=1）,根据商品名称，商品类别，商品价格区间来查询 并且进行分页
    @RequestMapping("/getsearchGoods")
    public Msg getsearchGoods(int pageNum,int pageSize,String searchname,Integer typeId,Integer maxPrice,Integer minPrice){
        PageHelper.startPage(pageNum,pageSize);
        List<Goods>  goodsList =goodService.getsearchGoods(searchname,typeId,maxPrice,minPrice);
        PageInfo<Goods> pageInfo=new PageInfo<Goods>(goodsList);
        return Msg.success().add("pageInfo",pageInfo);

    }


    // 根据id获取查看商品详情（前后台都有用到这个接口）
    @RequestMapping("getProductById")
    public Msg getProductById(@RequestBody Goods goods){
        int id=goods.getId();
        Goods goods1=goodService.getProductById(id);
        return Msg.success().add("goods",goods1);
    }


    //更改商品评价数量
    @RequestMapping("updateRemrk")
    public Msg updateRemrk(@RequestBody Goods goods){
        goodService.updateRemrk(goods);
        return Msg.success();

    }



}
