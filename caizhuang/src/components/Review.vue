<template>
  <div class="center-box">
      <div class="tabbed-box-hd">
          <p class="title">评价宝贝</p>
          <ul class="help-nav">
              <li>
                   <el-popover
                        placement="top-start"
                        title="评价须知"
                        width="200"
                        trigger="hover"
                        content="请您根据本次交易，给予真实、客观、仔细地评价。您的评价将是其他会员的参考，也将影响卖家的信用。">
                        <el-button slot="reference" class="btn">评价须知</el-button>
                    </el-popover>
               </li>
          </ul>
      </div>
      <div class="content">
        <div class="top-content">
            <div class="item-rate-info">
                <div class="item-detail">
                    <a class="item-d-img">
                        <img :src="temfielPath+item.goods.picture" />
                    </a>
                    <div class="item-title"><a>{{item.goods.description}}</a></div>
                    <div>颜色分类:默认</div>
                    <div>参数:{{item.size}}</div>
                </div>
            </div>
            <div class="item-rate-main">
                <div style="margin-top:10px;padding: 10px;display:flex;flex-direction:row">
                    <RadioGroup v-model="degree" style="min-width: 200px;">
                        <Radio label="0">
                            <!-- 好评 -->
                            <span><img :src="haoping"/></span>
                        </Radio>
                        <Radio label="1">
                            <!-- 中评 -->
                            <span><img :src="zhongping"/></span>
                        </Radio>
                        <Radio label="2">
                            <!-- 差评 -->
                            <span><img :src="chaping"/></span>
                        </Radio>
                    </RadioGroup>
                    <div v-if="degree==0">亲，好评无法修改和删除，请验货后再对商品和购物感受做出评论。</div>
                    <div v-if="degree==1 || degree==2">亲，很抱歉没能给您带来良好的购物体验，如有不满，您可联系卖家协商或发起售后维权</div>

                </div>
                <div style="margin-top:10px;">
                    <div class="review-content">
                        <Input v-model="content" type="textarea" :rows="4" placeholder="在此输入你的评论" />
                        <div style="display:flex;flex-direction:row">
                            <div class="shaitu"><Icon type="ios-film-outline" />晒图</div>
                             <span style="padding-left:20px;padding-top:10px">最多只能上传5张图片哦~</span>
                        </div>

                        <!-- 上传图片 -->
                        <div style="margin-top:10px;margin-left: 5px;">
                            <div class="demo-upload-list" v-for="item in uploadList">
                                <template v-if="item.status === 'finished'">
                                    <img :src="temfielPath+'review/'+item.response.extend.imgData">
                                    <div class="demo-upload-list-cover">
                                        <Icon type="ios-eye-outline" @click.native="handleView(item.response.extend.imgData)"></Icon>
                                        <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
                                    </div>
                                </template>
                                <template v-else>
                                    <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
                                </template>
                            </div>
                            <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :on-success="handleSuccess"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUpload"
                                multiple
                                type="drag"
                                action="http://localhost:8089/hf/review/upload"
                                style="display: inline-block;width:58px;">
                                <div style="width: 58px;height:58px;line-height: 58px;">
                                    <Icon type="ios-camera" size="20"></Icon>
                                </div>
                            </Upload>
                            <Modal title="查看大图" v-model="visible">
                                <img :src="temfielPath +'review/'+ imgName" v-if="visible" style="width: 100%">
                            </Modal>
                        </div>


                    </div>
                </div>
            </div>
        </div>
        <div class="ses-content">
           <div class="ses-title">
               店铺动态评分
           </div>
           <div class="ses-degree">
               <div style="margin-bottom:10px">宝贝与描述相符  <Rate show-text v-model="valueText1" />
                <span v-if="valueText1==1">- 差得太离谱，与卖家描述的严重不符，非常不满</span>
                 <span v-if="valueText1==2">- 部分有破损，与卖家描述的不符，不满意</span>
                 <span v-if="valueText1==3">- 质量一般，没有卖家描述的那么好</span>
                 <span v-if="valueText1==4"> - 质量不错，与卖家描述的基本一致，还是挺满意的</span>
                 <span v-if="valueText1==5"> - 质量非常好，与卖家描述的完全一致，非常满意</span>
               </div>
               <div style="margin-bottom:10px">卖家的服务态度  <Rate show-text v-model="valueText2" />
                <span v-if="valueText2==1">- 卖家态度很差，还骂人、说脏话，简直不把顾客当回事</span>
                 <span v-if="valueText2==2">- 卖家有点不耐烦，承诺的服务也兑现不了</span>
                 <span v-if="valueText2==3">- 卖家回复问题很慢，态度一般，谈不上沟通顺畅</span>
                  <span v-if="valueText2==4">卖家服务挺好的，沟通挺顺畅的，总体满意</span>
                  <span v-if="valueText2==5">- 卖家的服务太棒了，考虑非常周到，完全超出期望值</span>
               </div>
               <div style="margin-bottom:10px">物流服务的质量  <Rate show-text v-model="valueText3" />
                <span v-if="valueText3==1">- 到货速度严重延误，货物破损，派件员态度恶劣</span>
                 <span v-if="valueText3==2">- 到货速度慢，外包装严重变形，派件员不耐烦，态度差</span>
                 <span v-if="valueText3==3">- 到货速度一般，外包装变形，派件员态度一般</span>
                 <span v-if="valueText3==4">- 到货速度及时，派件员态度较好</span>
                 <span v-if="valueText3==5">- 到货速度非常快，商品完好无损，派件员态度很好</span>
               </div>
           </div>
        </div>

      </div>
      <div class="submit-btn">
          <Button type="primary" @click="submitReview">发表评论</Button>
          <Button  @click="exit">退出评价</Button>
      </div>

  </div>
</template>

<script>
import api  from '../utils/api';
import { sendServer } from '../utils/common';
export default {
    data(){
        return{
            userInfo:JSON.parse(localStorage.getItem('userdata')),
            item:JSON.parse(decodeURIComponent(this.$route.query.item)),
            temfielPath:'http://localhost:8089/img/',
            imgurl:require('../../static/img/index/susu.png'),
            haoping:require('../../static/img/index/eat/hp.png'),
            zhongping:require('../../static/img/index/eat/zp.png'),
            chaping:require('../../static/img/index/eat/cp.png'),
            degree:'4',//好评0 中评1 差评2
            content:'',//评论内容
            valueText1:0,
            valueText2:0,
            valueText3:0,
            imgData:'',//上传图片的名称，将名称存入到数据库中

            imgchoseList:[],
            imgName: '',//查看大图
            visible: false,
            uploadList: [],


        }
    },
    mounted () {
        this.uploadList = this.$refs.upload.fileList
    },

    methods:{
        handleView (imgData) {
            console.log(imgData)
            this.imgName = imgData;
            this.visible = true;
        },
        handleRemove (file) {
            const fileList = this.$refs.upload.fileList;
            this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
            this.imgchoseList.splice(this.imgchoseList.indexOf(file.response.extend.imgData),1)
            console.log(this.imgchoseList);

        },
        handleSuccess (res, file,fileList) {
            console.log(file)
            console.log(fileList)
            let list=[]
            fileList.forEach(item=>{
                list.push(item.response.extend.imgData)
            })
            console.log(list)
            this.imgchoseList = list
        },
        handleFormatError (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件格式 ' + file.name + ' 不正确的，请选择jpg或者png文件.'
            });
        },
        handleMaxSize (file) {
            this.$Notice.warning({
                title: '图片大小',
                desc: '图片  ' + file.name + ' 太大了，最多不能超过2M.'
            });
        },
        handleBeforeUpload () {
            const check = this.uploadList.length < 5;
            if (!check) {
                this.$Notice.warning({
                    title: '最多可以上传5张图片'
                });
            }
            return check;
        },
        //退出评价
        exit(){
            this.$confirm('退出后，当前内容将不会保存?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '不了',
                type: 'warning'
            }).then(() => {
                this.$router.back()
            }).catch(() => {

            });
        },
        //查看评价？？orderId+goodsId+size？===》确定一个itemId，明细ID
        //唯一的还是明细ID?订单ID，商品ID
        //订单一旦被删除，订单明细也不存在了！！！所以肯定得有商品ID，订单被删除，但是交易是存在的
        //
        //商品ID，以及购买的尺寸，订单ID  ===》决定一个itemId
        //后台查看评价的时候 得看到订单ID吧 所以得有订单ID
        submitReview(){
            let params={},send={}
            var time=new Date().getTime()
            send.userId=this.userInfo.id;
            send.goodsId=this.item.orderGoodsId;//商品ID
            send.goodsSize=this.item.size;//购买的尺寸
            send.orderId=this.item.orderId;//订单明细
            send.orderitemId=this.item.itemId//明细ID===》有这个明细ID，可以查看购买商品的尺寸，但是这个被删除的话，尺寸也没了
            send.degree=this.degree;
            send.content=this.content;
            send.goodscore=this.valueText1;
            send.shopscore=this.valueText2;
            send.logisticscore=this.valueText3;
            send.createTime=time;
            send.img=this.imgchoseList.join(',');
            params.send=send;
            params.url="review/insertReview";

            sendServer(params,(commRes)=>{
                if(commRes.code==100){
                    this.$Message.success("评论成功！！！")
                    //评论成功后 更改status的状态 改为1，表示已评价
                    api.updateItemStatus(send.orderitemId,1).then((res)=>{
                        if(res.code==100){
                            this.updateRemark(this.item.orderGoodsId)
                            this.isAllReview(this.item.orderId);
                        }
                    })
                    this.$router.back()
                    // this.$router.push("/home/showOrder")
                }else{
                    this.$Message.error("网络卡顿，请稍后重试一下！！")
                }
            })
        },
        isAllReview(orderId){
            let params={},send={}
            send.orderId=orderId;
            params.send=send;
            params.url="orderItem/isAllReview";
            sendServer(params,(commRes)=>{
               if(commRes.code==100){

               }
            })
        },
        /**
         * 当评价成功之后，更改数据库中商品表的评价条数
         */
        updateRemark(goodsId){
            let params={},send={}
            send.id=goodsId
            params.send=send;
            params.url="goods/updateRemrk"
            sendServer(params,(commRes)=>{
                console.log(commRes)
                if(commRes.code==100){

                }
            })
        }


    }

}
</script>
<style>
.ivu-rate-text {
    margin-left: 8px;
    vertical-align: middle;
    display: inline-block;
    font-size: 15px;
    color: #f40;
}
</style>
<style scoped>
.submit-btn{
    margin: 20px auto;
    text-align: center;
}
.ses-degree{
    width:700px;
    margin-left:50px;
    /* border: 1px solid red; */
}
.ses-title{
    width:150px;
    height:50px;
    line-height: 50px;
    text-align: center;
    background-color: #f3f3f3;
    font-size: 15px;
    color: #999999;

}
.ses-content{
    margin: 10px auto;
    width: 900px;
    /* border:1px solid red; */
    display: flex;
    flex-direction: row;
    /* justify-content: space-between; */
    /* align-items: center; */
}
.top-content{
    margin-bottom: 40px;
    border-bottom: 1px dashed #d9e3eb;
    display: flex;
    flex-direction: row;
    padding-bottom: 60px;
}
.shaitu{
    margin-top: 5px;
    margin-left: 5px;
    width:60px;
    height: 20px;
    line-height: 20px;
    border: 1px solid #7CA0B8;
    background-color: #93B4C6;
    color: #fff;
    font-size: 14px;
}
.review-content{
    padding: 5px;
    background-color: #EEF8FD;
}
.item-rate-main {
    margin-left: 20px;
    width: 600px;
    /* border: 1px solid red; */
}
li{
    list-style: none;
}
.item-title {
    height: 18px;
    line-height: 18px;
    overflow: hidden;
    margin-top: 5px;
    width: 123px;
    word-wrap: break-word;
    word-break: break-all;
}
.item-d-img img {
    vertical-align: middle;
}
.item-d-img {
    display: block;
    background: #F2F2F2;
    border: 1px solid #E6E6E6;
    width: 128px;
    height: 130px;
    line-height: 127px;
    text-align: center;
    overflow: hidden;
}
.item-detail {
    width: 130px;
    color: #999;
}
.item-rate-info {
    width: 290px;
    position: relative;
    padding: 20px 0 0 100px;
}
.content{
    border: 1px solid #d9e3eb;
    padding: 20px;

}

.btn{
    color:#fff;
    width: 100px;
    height: 35px;
    border: none;
    border-radius: 0;
    background-color: #f40;
}
.center-box{
    width: 1050px;
    margin: 20px auto;
}
.tabbed-box-hd {
    background: #efefef;
    border-bottom: 2px solid #f40;
    position: relative;
    vertical-align: middle;
    line-height: 35px;
    z-index: 1;
    height: 35px;
}
.title {
    float: left;
    background-color: #f40;
    padding: 0 2.4em;
    margin: 0 .4em 0 0;
    min-width: 4em;
    text-align: center;
    color: #FFF;
    font-size: 1.4em;
    font-weight: 300;
}
.help-nav {
    position: absolute;
    right: 0;
    top: 0;
    height: 100%;
    list-style: none;
}
.help-nav li {
    float: left;
    margin-left: 2em;
    /* margin-right: 20px; */
}


.demo-upload-list{
        display: inline-block;
        width: 60px;
        height: 60px;
        text-align: center;
        line-height: 60px;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        background: #fff;
        position: relative;
        box-shadow: 0 1px 1px rgba(0,0,0,.2);
        margin-right: 4px;
    }
    .demo-upload-list img{
        width: 100%;
        height: 100%;
    }
    .demo-upload-list-cover{
        display: none;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0,0,0,.6);
    }
    .demo-upload-list:hover .demo-upload-list-cover{
        display: block;
    }
    .demo-upload-list-cover i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }

</style>
