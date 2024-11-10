<template>
<div>

    <div class="remarks-box"  v-for="(item,index) in reviewList" :key="index">

        <div class="remarks-user">
            <Avatar :src="tempfilePath+item.customer.img" style="width:38px;height:38px" v-if="item.customer.img"/>
            <Avatar icon="ios-person" style="width:38px;height:38px" v-if="!item.customer.img"/>
            <span class="remarks-user-name">{{item.customer.userName}}</span>
        </div>
        <div class="remarks-content-box">
            <p v-if="item.degree==0">
                <Rate disabled :value="5" allow-half class="remarks-star"></Rate>
            </p>
            <p v-if="item.degree==1">
                <Rate disabled :value="3" allow-half class="remarks-star"></Rate>
            </p>
            <p v-if="item.degree==2">
                <Rate disabled :value="1" allow-half class="remarks-star"></Rate>
            </p>
            <p class="remarks-content">评价：{{item.content}}</p>
          <p class="remarks-content" v-if="!item.replyContent"></p>
          <p class="remarks-content" v-if="item.replyContent">店主回复：{{item.replyContent}}</p>
            <p class="remarks-sub">
                <span class="remarks-item" >购买尺寸：{{item.goodsSize}}</span>
                <span class="remarks-time">{{item.createTime}}</span>
            </p>
        </div>
        <div style="display:flex;flex-direction:column;width:300px">
            <p>买家秀：</p>
            <div v-if="!item.strs">暂无买家秀~</div>
            <div v-if="item.strs" style="display:flex;flex-direction:row;">
                <div v-for="(item0,index) in item.strs ":key="index" style="margin-right:10px">
                    <img :src="tempfilePath+'review/'+item0" class="img-40"/>
                </div>
            </div>

        </div>
    </div>
    <div class="goods-page">
        <Page v-show="total>0" :total="total" :page-size.sync="listQuery.limit"  :current.sync="listQuery.page"
        @on-change="goNext" show-elevator show-total placement="bottom" show-sizer @on-page-size-change="getSizeNext"/>
    </div>
    <div v-if="reviewList.length==0" style="margin-top:30px;margin-left:200px">该商品暂时木有评价~~</div>

</div>

</template>

<script>
import { sendServer, sendServerGet } from '../../utils/common';
export default {
    props:{
        c:{
            type:String,
            default: "0",
        },
    },
    data(){
        return{
            tempfilePath:'http://localhost:8089/img/',
            total:0,
            listQuery: {
                page: 1,
                limit: 10,
            },
            reviewList:[],
            goodsRating:'',
            totalLength:'',
            goodLenght:'',
            middleLength:'',
            lowLength:'',


        }
    },
    created(){
        this.getAllGoodsReview()
        if(this.c==0){
            this.getGoodsReview(0)
        }else if(this.c==1){
            this.getGoodsReview(1)
        }else if(this.c==2){
            this.getGoodsReview(2)
        }else if(this.c=="all"){
            this.getGoodsReview(null)
        }

    },
    methods: {
        goNext(data){
            this.listQuery.page=data
            if(this.c==0){
                this.getGoodsReview(0)
            }else if(this.c==1){
                this.getGoodsReview(1)
            }else if(this.c==2){
                this.getGoodsReview(2)
            }else if(this.c=="all"){
                this.getGoodsReview(null)
            }
        },
        getSizeNext(data){
            this.listQuery.limit=data
           if(this.c==0){
                this.getGoodsReview(0)
            }else if(this.c==1){
                this.getGoodsReview(1)
            }else if(this.c==2){
                this.getGoodsReview(2)
            }else if(this.c=="all"){
                this.getGoodsReview(null)
            }
        },
    // 获取商品评价
        getGoodsReview(degree){
        let params={},send={}
        send.goodsId=this.$route.query.goodsId;
        send.degree=degree
        send.pageSize=this.listQuery.limit
        send.pageNum=this.listQuery.page

        params.send=send
        params.url="review/getGoodsReview";
        sendServerGet(params,(commRes)=>{
            if(commRes.code==100){
                this.total=commRes.extend.pageInfo.total;
                let list=commRes.extend.pageInfo.list;
                console.log(list);
                list.forEach(item=>{
                    let imgData=item.img;
                    if(imgData){
                        var strs=new Array()
                        strs=imgData.split(",");
                        item.strs=strs
                    }
                })
                this.reviewList=list;
            }
        })
    },
    getAllGoodsReview(){
        let params={},send={}
        send.goodsId=this.$route.query.goodsId;
        send.pageSize=50000
        send.pageNum=1
        params.send=send
        params.url="review/getGoodsReview";
        sendServerGet(params,(commRes)=>{
            if(commRes.code==100){
                console.log(commRes)
                let list=commRes.extend.pageInfo.list;
                let goods=[],middle=[],low=[]

                list.forEach(item=>{
                    if(item.degree==0){
                        goods.push(item)
                    }
                    if(item.degree==1){
                        middle.push(item)
                    }
                    if(item.degree==2){
                        low.push(item)
                    }
                })
                //总评价数
                let totalLenght=list.length
                this.totalLength=totalLenght;
                //好评数
                let goodLenght=goods.length;
                this.goodLenght=goodLenght;
                //中评数
                this.middleLength=middle.length;
                //差评数目
                this.lowLength=low.length;
                //好评率
                if(goodLenght!=0){
                    this.goodsRating=(parseInt(goodLenght)/parseInt(totalLenght)*100).toFixed(0,0)
                }


            }
        })
    }



  },

}
</script>

<style scoped>
.goods-page {
  margin-top: 20px;
  display: flex;
  /* justify-content: flex-end; */
}
.img-40{
    width:40px;
    height: 40px;
}
.remarks-title {
  padding-left: 15px;
  height: 36px;
  font-size: 16px;
  font-weight: bolder;
  line-height: 36px;
  color: #666666;
  background-color: #F7F7F7;
}
.remarks-analyse-box {
  padding: 15px;
  display: flex;
  align-items: center;
}
.remarks-analyse-goods {
  margin-left: 15px;
  margin-right: 15px;
}
.remarks-analyse-num {
  font-size: 26px;
}
.remarks-analyse-title {
  font-size: 12px;
  line-height: 20px;
}
.remarks-bar {
  padding-left: 15px;
  height: 36px;
  line-height: 36px;
  color: #666666;
  background-color: #F7F7F7;
}
.remarks-bar span {
  margin-right: 15px;
}
.remarks-box {
  padding: 15px;
  display: flex;
  flex-direction: row;
  border-bottom: 1px #ccc dotted;
}
.remarks-user {
  width: 180px;
}
.remarks-user-name {
  padding-left: 15px;
}
.remarks-content-box {
    /* width:500px; */
  width: calc(100% - 380px);
}
.remarks-star {
  background-color: #fff;
}
.remarks-content {
  font-size: 14px;
  color: #232323;
  line-height: 28px;
}
.remarks-sub {
  margin-top: 15px;
  color: #ccc;
}
.remarks-time {
  margin-left: 15px;
}
.remarks-page {
  margin: 15px;
  display: flex;
  justify-content:flex-end;
}

</style>
