<template>
  <div class="app-container">
       <el-card shadow="never" style="margin-top: 15px">
            <div style="margin-top: 20px;margin-bottom:10px">
                <i class="el-icon-s-flag"></i>
                <span class="font-small">用户基本信息</span>
            </div>
            <div v-if="review.customer"style="display:flex;flex-direction:row">
                <img :src="temfielPath+review.customer.img" class="img-50"/>
                 <p style="padding-left:30px">{{review.customer.userName}}</p>
                 <p style="padding-left:30px">信誉分：500</p>
                 <p style="padding-left:30px">购买次数：20次</p>
                 <p style="padding-left:30px">最近购买时间：【2021-3-30】</p>
            </div>


            <div style="margin-top: 20px">
                 <i class="el-icon-s-flag"></i>
                <span class="font-small">订单商品信息</span>
            </div>

            <div class="table-layout">
                <el-row>
                    <el-col :span="4" class="table-cell-title">商品图片</el-col>
                    <el-col :span="4" class="table-cell-title">商品名称</el-col>
                    <el-col :span="4" class="table-cell-title">商品描述</el-col>
                    <el-col :span="4" class="table-cell-title">商品价格</el-col>
                    <el-col :span="4" class="table-cell-title">商品产地</el-col>
                    <el-col :span="4" class="table-cell-title">已购尺寸</el-col>

                </el-row>
                <el-row>
                    <el-col :span="4" class="table-cell" ><img :src="temfielPath+item.goods.picture"class="img-80"/></el-col>
                     <el-col :span="4" class="table-cell" >{{item.goods.goodsname}}</el-col>
                    <el-col :span="4" class="table-cell" >{{item.goods.description}}</el-col>
                    <el-col :span="4" class="table-cell" >{{item.goods.sellPrice}}元</el-col>
                    <el-col :span="4" class="table-cell" >{{item.goods.producingArea}}</el-col>
                    <el-col :span="4" class="table-cell" >{{item.goodsSize}}</el-col>
                </el-row>
            </div>

            <div style="margin-top: 20px">
                 <i class="el-icon-s-flag"></i>
                <span class="font-small">评价信息</span>
            </div>

            <div class="table-layout">
                <el-row>
                    <el-col :span="4" class="table-cell-title">总体评价</el-col>
                    <el-col :span="4" class="table-cell-title">商品评价</el-col>
                    <el-col :span="4" class="table-cell-title">卖家评价</el-col>
                    <el-col :span="4" class="table-cell-title">物流评价</el-col>
                    <el-col :span="4" class="table-cell-title">评价内容</el-col>
                    <el-col :span="4" class="table-cell-title">评价时间</el-col>

                </el-row>
                <el-row>
                    <el-col :span="4" class="table-cell" >
                        <div v-if="item.degree==0">好评</div>
                        <div v-if="item.degree==1">中评</div>
                        <div v-if="item.degree==2">差评</div>
                    </el-col>
                     <el-col :span="4" class="table-cell" style="padding-top:35px">
                        <el-rate v-model="review.goodscore" show-text :disabled="true">
                        </el-rate>
                    </el-col>
                    <el-col :span="4" class="table-cell" style="padding-top:35px">
                         <el-rate v-model="review.shopscore" show-text :disabled="true">
                        </el-rate>
                    </el-col>
                    <el-col :span="4" class="table-cell" style="padding-top:35px">
                        <el-rate v-model="review.logisticscore" show-text :disabled="true">
                        </el-rate>
                    </el-col>
                    <el-col :span="4" class="table-cell" >{{review.content}}</el-col>
                    <el-col :span="4" class="table-cell" >{{review.createTime}}</el-col>
                </el-row>
            </div>

            <div style="margin-top: 20px;margin-bottom:20px">
                 <i class="el-icon-s-flag"></i>
                <span class="font-small">买家晒图</span>
            </div>
            <div v-if="imgList.length==0" style="margin-top：20px；margin-left:40px;color:#f40">暂无买家秀</div>
            <div class="img-box" v-if="imgList.length!=0">
                <div v-for="(item,index) in imgList" :key="index" style="margin-left:20px">
                    <img :src="temfielPath+'review/'+item" class="img-100"/>
                </div>
            </div>

             <div style="margin-top: 20px;margin-bottom:20px">
                 <i class="el-icon-s-flag"></i>
                <span class="font-small">回复买家</span>
            </div>

            <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入回复内容"
                v-model="replyContent">
            </el-input>

        </el-card>
        <div style="margin:10px auto">
             <el-button round style="display:block;margin:0 auto" @click="toReply">提交回复</el-button>
        </div>

  </div>
</template>

<script>
import { sendServer } from '@/utils/common'
export default {
    data(){
        return{
            // goodscore:0,
            // shopscore:0,
            // logisticscore:0,
            temfielPath:'http://localhost:8089/img/',
            review:{},
            item:JSON.parse(decodeURIComponent(this.$route.query.item)),
            imgList:[],
            replyContent:'',
        }
    },
    created(){
        this.getReviewDetail()
    },
    methods:{
        getReviewDetail(){
            let params={},send={}
            console.log(this.item)
            send.id=this.item.id;
            params.send=send;
            params.url="review/toBgDetail"
            sendServer(params,(commRes)=>{
                if(commRes.code==100){
                    console.log(commRes)
                    this.review=commRes.extend.review
                    if(this.review.img){
                        let imgData=this.review.img;
                        let strs=new Array()
                        strs=imgData.split(",")
                        this.imgList=strs;
                        console.log(this.imgList)
                    }

                    this.replyContent=this.review.replyContent

                }
            })

        },
        //回复
        toReply(){
            let params={},send={}
            let time=new Date().getTime()
            send.id=this.item.id;
            send.replyContent=this.replyContent;
            send.replyTime=time;
            params.send=send;
            console.log(send)
            params.url="review/updateReview";
            sendServer(params,(commRes)=>{
                if(commRes.code==100){
                    this.$message.success("回复成功！")
                }
            })

        }

    }
}
</script>

<style scoped>
.img-100{
    width:100px;
    height:100px;
}
.img-box{
    display: flex;
    flex-direction: row;
}
.img-50{
    width:50px;
    height:50px;
    border-radius: 50%;
}
.img-80{
    width:80px;
    height:80px;
}
 .table-layout {
    margin-top: 20px;
    /* border-left: 1px solid #DCDFE6;
    border-top: 1px solid #DCDFE6; */
  }
  .order-box{
    width: 80%;
    padding: 20px 20px 20px 20px;
    margin: 20px auto;
    /* border: 1px solid #DCDFE6;
    border-radius:5px ;
    box-shadow: 0px 0px 20px  #DCDFE6; */
}
.title{
    width:100%;
    height: 80px;
    line-height: 80px;
    border-radius:5px ;
    background: #f2f6fc;

}
.operate-container {
    background: #F2F6FC;
    height: 80px;
    margin: -20px -20px 0;
    line-height: 80px;
  }

  .operate-button-container {
    float: right;
    margin-right: 20px
  }

  .table-layout {
    margin-top: 20px;
    border-left: 1px solid #DCDFE6;
    border-top: 1px solid #DCDFE6;
  }

  .table-cell {
    height: 100px;
    line-height: 80px;
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    font-size: 14px;
    color: #606266;
    text-align: center;
    overflow: hidden;
  }

  .table-cell-title {
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    background: #F2F6FC;
    text-align: center;
    font-size: 14px;
    color: #303133;
  }

.color-danger{
    color: red;
}

</style>
