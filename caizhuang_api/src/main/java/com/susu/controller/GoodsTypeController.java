package com.susu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.susu.entity.Goods;
import com.susu.entity.GoodsType;
import com.susu.entity.Msg;
import com.susu.service.GoodService;
import com.susu.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private GoodService goodService;

    @RequestMapping("/getGoodsType")
    public Msg getGoodsType(){
        List<GoodsType> list=goodsTypeService.getGoodsType();
        return Msg.success().add("list",list);
    }


    @RequestMapping("/getGoodsType2")
    public Msg getGoodsType(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsType> list=goodsTypeService.getGoodsType();
        PageInfo<GoodsType> pageInfo=new PageInfo<GoodsType>(list);
        return Msg.success().add("pageInfo",pageInfo);
    }
    @RequestMapping("/getTypeById")
    public Msg getTypeById(@RequestBody GoodsType goodsType){
        int id=goodsType.getId();
        GoodsType goodsType1=goodsTypeService.getTypeById(id);
        return Msg.success().add("goodsType",goodsType1);
    }

    @RequestMapping("/getGoodsCount")
    public Msg getGoodsCount(){
        List<GoodsType> list=goodsTypeService.getGoodsType();
        for(GoodsType g:list){
            int id=g.getId();
            System.out.println("sjhdfshd"+id);
            Integer count=goodService.getCount(id);
            System.out.println("sudfhsdfdshfbsdfs"+count);
            g.setCount(count);
        }
        return Msg.success().add("list",list);
    }



    @RequestMapping("/addGoodsType")
    public Msg addGoodsType(@RequestBody GoodsType goodsType){
        String goodstypename=goodsType.getGoodstypename();
        String description=goodsType.getDescription();
        goodsTypeService.addGoodsType(goodsType);
        return Msg.success();
    }
    @RequestMapping("/editGoodsType")
    public Msg editGoodsType(@RequestBody GoodsType goodsType){
        String goodstypename=goodsType.getGoodstypename();
        String description=goodsType.getDescription();
        goodsTypeService.editGoodsType(goodsType);
        return Msg.success();
    }


    @RequestMapping("/delType")
    public Msg delType(@RequestParam("id") int id, @RequestParam("goodstypeId") int goodstypeId ){
//        int id=goodsType.getId();
//        int id1=goods.getGoodstypeId();
        System.out.println("miasfdnasuifh"+goodstypeId);
        System.out.println("asjfhgsdf"+id);
        List<Goods> goodsList=goodService.getListByType(goodstypeId);
//        return Msg.success().add("goodsList",goodsList).add("length",goodsList.size());
        if(goodsList.size()==0){
            goodsTypeService.delType(id);
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @RequestMapping("/deleteByBatch")
    public Msg deleteByBatch(String ids){
        System.out.println(ids);
        String[] d=ids.split(",");//把数组里的值逗号隔开
        goodsTypeService.deleteByBatch(d);
        return  Msg.success();
    }


    @RequestMapping("/count")
    public Msg count(@RequestBody GoodsType goodsType){
        String typeName=goodsType.getGoodstypename();
        boolean b=goodsTypeService.count(typeName);
        if(b){
            return Msg.success();
        }else{
            return Msg.fail().add("fail_msg", "该分类已经存在");
        }
    }
}
