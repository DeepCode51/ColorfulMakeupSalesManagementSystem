package com.susu.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.susu.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class testJson {
//    public static void main(String[] args) {
////        在JSON.parseObject 的时候，会去填充名称相同的属性。
////        对于Json字符串中没有，而model类有的属性，会为null；
////        对于model类没有，而Json字符串有的属性，不做任何处理。
////        String text="[{itemId:1,bb:1},{itemId:2,bb:1},{itemId:3,bb:1}]";
//        String text="[{\"id\":17,\"userId\":1,\"goodsId\":9,\"quantity\":5,\"size\":\"M\",\"goods\":{\"id\":9,\"goodstypeId\":1,\"goodsname\":\"汉风吟【美人骨】\",\"producingArea\":\"浙江杭州\",\"createTime\":null,\"price\":193,\"sellPrice\":193,\"stock\":100,\"picture\":\"9.jpg\",\"discount\":1,\"updateTime\":null,\"onsale\":1,\"description\":\"汉风吟【美人骨】原创正品明制披风印花刺绣立领马面裙中长款汉服\",\"remark\":0,\"soldNum\":0},\"choosed\":true,\"sum\":965},{\"id\":16,\"userId\":1,\"goodsId\":7,\"quantity\":4,\"size\":\"S\",\"goods\":{\"id\":7,\"goodstypeId\":1,\"goodsname\":\"池夏芒种·菱歌\",\"producingArea\":\"安徽合肥\",\"createTime\":null,\"price\":228,\"sellPrice\":228,\"stock\":100,\"picture\":\"7.jpg\",\"discount\":1,\"updateTime\":null,\"onsale\":1,\"description\":\"池夏芒种·菱歌 宋制对襟衫吊带四破三裥裙 原创改良汉服 夏天\",\"remark\":0,\"soldNum\":0},\"choosed\":true,\"sum\":912}]";
//
//        System.out.println(array);
////        List<OrderItem> list = JSON.parseArray(text, OrderItem.class);
////        System.out.println(list.toString());
//    }
//    public static void main(String[] args) {
//        String text="[{\"id\":17,\"userId\":1,\"goodsId\":9,\"quantity\":5,\"size\":\"M\",\"goods\":{\"id\":9,\"goodstypeId\":1,\"goodsname\":\"汉风吟【美人骨】\",\"producingArea\":\"浙江杭州\",\"createTime\":null,\"price\":193,\"sellPrice\":193,\"stock\":100,\"picture\":\"9.jpg\",\"discount\":1,\"updateTime\":null,\"onsale\":1,\"description\":\"汉风吟【美人骨】原创正品明制披风印花刺绣立领马面裙中长款汉服\",\"remark\":0,\"soldNum\":0},\"choosed\":true,\"sum\":965},{\"id\":16,\"userId\":1,\"goodsId\":7,\"quantity\":4,\"size\":\"S\",\"goods\":{\"id\":7,\"goodstypeId\":1,\"goodsname\":\"池夏芒种·菱歌\",\"producingArea\":\"安徽合肥\",\"createTime\":null,\"price\":228,\"sellPrice\":228,\"stock\":100,\"picture\":\"7.jpg\",\"discount\":1,\"updateTime\":null,\"onsale\":1,\"description\":\"池夏芒种·菱歌 宋制对襟衫吊带四破三裥裙 原创改良汉服 夏天\",\"remark\":0,\"soldNum\":0},\"choosed\":true,\"sum\":912}]";
//        JSONArray array = JSON.parseArray(text);
//        System.out.println(array);
//
//        List<OrderItem> list = new ArrayList<>();
//        list =array.toJavaList(OrderItem.class);
//        System.out.println(list.toString());
//
//    }
//    public static void main(String[] args) {
//        String []list=new String[]{"1","2","3","4"};
//        for(String s:list){
//            System.out.println(s);
//        }
//    }
    public static void main(String[] args) {
        A[] students = new A[2]; //创建2个学生的学生数组
        A as= new A();

        as.setAge(15);

        as.setName("tom");

        A as1= new A();

        as1.setAge(16);

        as1.setName("cat");

        A[] a={as,as1}; //动态创建学生数组

        for (A st : a) {//遍历数组

            System.out.println(st.getName()+"");

        }
    }


}
