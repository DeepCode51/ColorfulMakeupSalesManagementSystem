<template>
  <div>
    <div v-if="b==1" class="action" @click="doAction">批量管理</div>
    <div v-if="b==1 && show==true" class="action" @click="">删除</div>
    <div v-if="b==1 &&  show==true" class="action" @click="choseAll">全选</div>
    <div v-if="b==1 &&  show==true" class="action" @click="">清空评价</div>
    <table class="tb-rate-table thm-list ">
        <colgroup>
            <col width="80px"></col>
            <col></col>
            <col width="150px"></col>
            <col width="150px"></col>
            <col width="200px"></col>
        </colgroup>
        <thead>
            <tr class="bg-tr">
                <th class="align-c">
                    <i-select v-model="degree" size="small" style="width:80px" @on-change="change" >
                        <i-option v-for="item in degreelist" :value="item.value" :key="item.value">{{item.label}}</i-option>
                    </i-select>
                </th>
                <th></th>
                <th v-if="b==1">被评价人</th>
                <th v-if="b==2">评价人</th>
                <th class="align-c">宝贝信息</th>
                <th class="align-c">操作</th>
            </tr>

        </thead>
        <tbody>
            <!-- 评价内容 -->
            <tr v-if="reviewList.length==0" >
                <td></td>
                <td style="margin-left:80px">暂时没有相关评价哦~~</td>
            </tr>
            <tr v-for="(item,index) in reviewList" :key="index">
                <td class="align-c" v-if="item.degree==0">
                    <span v-if="show==true" :class="item.choseItem?'mark':''" @click="choseItem(item)"><Icon type="md-checkmark" /></span>
                    <img :src="hp"/>
                </td>
                <td class="align-c" v-if="item.degree==1">
                    <span v-if="show==true" :class="item.choseItem?'mark':''"@click="choseItem(item)"><Icon type="md-checkmark" /></span>
                    <img :src="zp"/>
                </td>
                <td class="align-c" v-if="item.degree==2">
                    <span v-if="show==true" :class="item.choseItem?'mark':''"@click="choseItem(item)"><Icon type="md-checkmark" /></span>
                    <img :src="cp"/>
                </td>
                <td>
                    <p class="comment">评语：{{item.content}}</p>
                    <ul class="photos" v-if="item.strs">
                        <li v-for="(item0,index) in item.strs" :key="index">
                            <img :src="temfielPath+'review/'+item0" class="img-40"/>
                        </li>

                    </ul>
                    <p class="date">[{{item.createTime}}]</p>
                    <div v-if="item.replyContent">
                        <p class="reply-text">[掌柜回复]：{{item.replyContent}}</p>
                        <p class="date">[{{item.replyTime}}]</p>
                    </div>
                </td>
                <td>卖家：缤色彩妆</td>
                <td class="new-a">
                    <p v-if="item.goods"><router-link target="_blank" :to="{path:'/goodsDetail',query:{goodsId:item.goodsId}}">&emsp;{{item.goods.goodsname}}</router-link></p>
                    <p v-html=""v-if="item.goods" class="price">&emsp;&emsp;&emsp;&emsp;{{item.goods.sellPrice}}元</p>
                </td>
                <!-- 操作 -->
                <td>
                    <div v-if="b==1 && item.degree==1 || item.degree==2 " class="btn1">
                    &emsp;&emsp;
                        <Button size="small" @click="openModal(item)">修改评价</Button>
                        <Button size="small" @click="del(item.id)">删除评价</Button>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
     <div class="goods-page">
        <Page v-show="total>0" :total="total" :page-size.sync="listQuery.limit"  :current.sync="listQuery.page"
          @on-change="goNext" show-elevator show-total placement="bottom" show-sizer @on-page-size-change="getSizeNext"/>
     </div>
     <Modal v-model="modal5" title="修改评价" width="900px">
        <div class="content">
            <div class="top-content">
                <div class="item-rate-info">
                    <div class="item-detail">
                        <a class="item-d-img">
                            <img v-if="modalForm.goods" :src="temfielPath+modalForm.goods.picture" />
                        </a>
                        <div class="item-title"><a v-if="modalForm.goods">{{modalForm.goods.description}}</a></div>
                        <div>颜色分类:默认</div>
                        <div>尺码:{{modalForm.goodsSize}}</div>
                    </div>
                </div>
                <div class="item-rate-main">
                    <div style="margin-top:10px;padding: 10px;display:flex;flex-direction:row">
                        <RadioGroup v-model="modalForm.degree" style="min-width: 200px;">
                            <Radio label="0">
                                <!-- 好评 -->
                                <span><img :src="hp"/></span>
                            </Radio>
                            <Radio label="1">
                                <!-- 中评 -->
                                <span><img :src="zp"/></span>
                            </Radio>
                            <Radio label="2">
                                <!-- 差评 -->
                                <span><img :src="cp"/></span>
                            </Radio>
                        </RadioGroup>
                        <div v-if="modalForm.degree==0">亲，好评无法修改和删除，请验货后再对商品和购物感受做出评论。</div>
                        <div v-if="modalForm.degree==1 || modalForm.degree==2">亲，很抱歉没能给您带来良好的购物体验，如有不满，您可联系卖家协商或发起售后维权</div>

                    </div>

                    <div style="margin-top:10px;">
                        <div class="review-content">
                            <Input v-model="modalForm.content" type="textarea" :rows="4" placeholder="亲，每个漂亮的女生都拥有一件连衣裙，你也有很多款吧，写点评把你喜欢的连衣裙分享给同样美丽的女子" />
                            <div style="display:flex;flex-direction:row">
                                <div class="shaitu"><Icon type="ios-film-outline" />晒图</div>
                                <span style="padding-left:20px;padding-top:10px">最多只能上传5张图片哦~</span>
                            </div>

                            <!-- 上传图片 -->
                            <div style="margin-top:10px;margin-left: 5px;">
                                <div class="demo-upload-list" v-if="uploadList.length!=0" v-for="item in uploadList">
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
                    <div style="margin-bottom:10px">宝贝与描述相符  <Rate show-text v-model="goodscore" type="number"/>
                        <span v-if="modalForm.goodscore==1">- 差得太离谱，与卖家描述的严重不符，非常不满</span>
                        <span v-if="modalForm.goodscore==2">- 部分有破损，与卖家描述的不符，不满意</span>
                        <span v-if="modalForm.goodscore==3">- 质量一般，没有卖家描述的那么好</span>
                        <span v-if="modalForm.goodscore==4"> - 质量不错，与卖家描述的基本一致，还是挺满意的</span>
                        <span v-if="modalForm.goodscore==5"> - 质量非常好，与卖家描述的完全一致，非常满意</span>
                    </div>
                    <div style="margin-bottom:10px">卖家的服务态度  <Rate show-text v-model="shopscore" />
                        <span v-if="modalForm.shopscore==1">- 卖家态度很差，还骂人、说脏话，简直不把顾客当回事</span>
                        <span v-if="modalForm.shopscore==2">- 卖家有点不耐烦，承诺的服务也兑现不了</span>
                        <span v-if="modalForm.shopscore==3">- 卖家回复问题很慢，态度一般，谈不上沟通顺畅</span>
                        <span v-if="modalForm.shopscore==4">卖家服务挺好的，沟通挺顺畅的，总体满意</span>
                        <span v-if="modalForm.shopscore==5">- 卖家的服务太棒了，考虑非常周到，完全超出期望值</span>
                    </div>
                    <div style="margin-bottom:10px">物流服务的质量  <Rate show-text v-model="logisticscore" />
                        <span v-if="modalForm.logisticscore==1">- 到货速度严重延误，货物破损，派件员态度恶劣</span>
                        <span v-if="modalForm.logisticscore==2">- 到货速度慢，外包装严重变形，派件员不耐烦，态度差</span>
                        <span v-if="modalForm.logisticscore==3">- 到货速度一般，外包装变形，派件员态度一般</span>
                        <span v-if="modalForm.logisticscore==4">- 到货速度及时，派件员态度较好</span>
                        <span v-if="modalForm.logisticscore==5">- 到货速度非常快，商品完好无损，派件员态度很好</span>
                    </div>
                </div>
            </div>
        </div>
        <div slot="footer">
            <Button type="text" size="large" @click="modalCancel">取消</Button>
            <Button type="primary" size="large" @click="modalOk(modalForm)">确定</Button>
        </div>
     </Modal>
  </div>
</template>

<script>
import { sendServer, sendServerGet } from '../../utils/common';
export default {
    props:{
        b:{
            type:String,
            default: "0",
        },
    },
    data(){
        return{
            allchose:false,//全选为false
            show:false,//是否展示全选 删除的权限
            logisticscore:0,
            shopscore:0,
            goodscore:0,
            imgData:'',//上传图片的名称，将名称存入到数据库中
            imgchoseList:[],
            imgName: '',//查看大图
            visible: false,
            uploadList: [],
            strs:[],
            modalForm:{},
            //修改评价
             modal5:false,
             temfielPath:'http://localhost:8089/img/',
             hp:require('../../../static/img/index/eat/hp.png'),
             zp:require('../../../static/img/index/eat/zp.png'),
             cp:require('../../../static/img/index/eat/cp.png'),
             imgUrl:require("../../../static/img/index/susu.png"),
             degree:'',
             degreelist:[
                  {
                     value:"all",
                     label:"评价"
                 },
                 {
                     value:"0",
                     label:"好评"
                 },
                 {
                    value:"1",
                    label:"中评"
                },
                {
                    value:"2",
                    label:"差评"
                }
             ],
             userInfo:JSON.parse(localStorage.getItem('userdata')),
             total:0,
             listQuery: {
                page: 1,
                limit: 10,
            },
            reviewList:[],
        }
    },
    mounted(){

    },
    created(){
        if(this.b==1){
            this.getAllReview();
        }else if(this.b==2){
            this.getBgReview();
        }

    },
    methods:{
     del(id){
            let params={},send={};
            send.ids=id;

            params.send=send;
            params.url="review/deleteUserReviewById";
            sendServerGet(params,(commRes)=>{
              console.log(commRes);
              if(commRes.code==100){
                this.$message({
                  type: 'success',
                  message: '删除成功!',
                });
              }
            })
          },
        getBgReview(){
            let params={},send={};
            send.userId=this.userInfo.id;
            if(this.degree){
                send.degree=this.degree
            }
            if(this.degree=='all'){
                send.degree=null
            }
            console.log(send)
            send.pageSize=this.listQuery.limit
            send.pageNum=this.listQuery.page
            params.send=send;
            params.url="review/reviewByBg";
            sendServerGet(params,(commRes)=>{
                console.log(commRes)
                if(commRes.code==100){
                    this.total=commRes.extend.pageInfo.total;
                    let list=commRes.extend.pageInfo.list;
                    this.reviewList=list
                    console.log(this.reviewList)
                }
            })

        },
        choseAll(){
            this.allchose=!this.allchose
            if(this.allchose){
                this.reviewList.forEach(item=>{
                    item.choseItem=true
                })
            }else{
                this.reviewList.forEach(item=>{
                    item.choseItem=false
                })
            }
        },
        choseItem(item){
            item.choseItem=!item.choseItem
        },
        doAction(){
            console.log("212")
            this.show=!this.show
        },

        modalOk(modalFrom){
            console.log(modalFrom)
            let time=new Date().getTime()
            let params={},send={}
            send.id=modalFrom.id;
            send.degree=modalFrom.degree;
            send.content=modalFrom.content;
            send.img=this.imgchoseList.join(',');
            send.createTime=time;
            send.goodscore=this.goodscore;
            send.shopscore=this.shopscore;
            send.logisticscore=this.logisticscore;
            console.log(send)
            params.send=send;
            params.url="review/updateReview";
            sendServer(params,(commRes)=>{
                if(commRes.code==100){
                    this.$Message.success("修改成功")
                    this.$router.back();
                }
            })

        },
        modalCancel(){
            this.modal5=false
        },
        handleView (imgData) {
            console.log(imgData)
            this.imgName = imgData;
            this.visible = true;
        },
        handleRemove (file) {
            const fileList = this.uploadList
            this.uploadList.splice(fileList.indexOf(file), 1);//可以删除了
            this.imgchoseList.splice(this.imgchoseList.indexOf(file.response.extend.imgData),1)
            console.log(this.imgchoseList);

        },
        handleSuccess (res,file) {
            //一共两个list  uploadList和itemchoseList
            console.log(file)
            console.log(this.uploadList)
            this.uploadList.push(file)
            let list=[];//图片上传列表
            list.push(file.response.extend.imgData)
            console.log("图片上传列表"+list)
            this.imgchoseList = list.concat(this.imgchoseList)
            console.log( this.imgchoseList)
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
        /**
         * 打开模态框，修改评价
         */
        openModal(item){

            console.log(item)
            this.modalForm=item
            this.goodscore=parseInt(this.modalForm.goodscore);
            this.logisticscore=parseInt(this.modalForm.logisticscore);
            this.shopscore=parseInt(this.modalForm.logisticscore);
            this.imgData=item.img//如果存在图片 即展示 不存在无
            if(this.imgData){
                console.log("hshdsfsd")
                let imgList=new Array();
                imgList=this.imgData.split(",");
                //默认的已上传列表
                imgList.forEach(item=>{
                    this.imgchoseList.push(item)
                })
                //默认的已上传列表
                let strlist=[];
                imgList.forEach((item,index)=>{
                    let strs={}
                    strs.status="finished"
                    strs.response={}
                    strs.response.extend={}
                    strs.response.extend.imgData=item
                    strlist.push(strs)
                })
                this.uploadList =strlist
                console.log(this.uploadList.length)
            }
            this.modal5=!this.modal5;
        },
        getAllReview(){
            let params={},send={}
            send.userId=this.userInfo.id;
            if(this.degree){
                send.degree=this.degree
            }
            if(this.degree=='all'){
                send.degree=null
            }
            send.pageSize=this.listQuery.limit
            send.pageNum=this.listQuery.page
            params.send=send;
            console.log(send)
            params.url="review/showReview";
            sendServerGet(params,(commRes)=>{
                console.log(commRes)
                if(commRes.code==100){
                    this.total=commRes.extend.pageInfo.total;
                    let list=commRes.extend.pageInfo.list;
                    list.forEach(item=>{
                        item.choseItem=false;
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
        goNext(data){
            this.listQuery.page=data
            this.getAllReview();
        },
        getSizeNext(data){
            this.listQuery.limit=data
            this.getAllReview();
        },
        change(data){
            console.log(data)
            this.degree=data;
            if(this.b==1){
                this.getAllReview();
            }else if(this.b==2){
                this.getBgReview();
            }
        },
    }
}
</script>

<style scoped>
.mark{
  color: #fff;
  font-size: 20px;
  background-color: red;
}
.action{
    margin-left: 10px;
    float: right;
    padding: 2px 10px ;
    line-height: 15px;
    border: 1px solid #DDD;
    background-color: bisque;
    margin-bottom: 4px;
}
.reply-text{
    color:#b28500;
    background-color:  #f6f6f6;
}
.btn1 Button{
 font-size: 10px;
}
.new-a a{
    color:#3c3c3c;
}
.new-a a:hover{
    color:#f40;
}
.goods-page {
  margin-top: 20px;
  display: flex;
  /* justify-content: flex-end; */
}
.photos{
    display: flex;
    flex-direction: row;

}
.img-40{
    width:40px;
    height: 40px;
}
ul.photos li {
    border: 1px solid #CCC;
    margin-right: 5px;
    position: relative;
    width: 40px;
    height: 40px;
}
ul{
    list-style: none;
}
ul.photos {
    margin-bottom: 5px;
}
.price{
    color: #F60;
    margin: 0 2px;
}
.date {
    color: #999;
}
.comment {
    word-wrap: break-word;
    line-height: 18px;
}
.tb-rate-table p {
    margin-bottom: 5px;
}
.bg-tr{
    border: 1px solid #DDD;
    background-color: bisque;
}
table.thm-list th{
    padding: 15px 10px;
    border-bottom: 1px solid #DDD;
}
table.thm-list td {
    padding: 15px 10px;
    border-bottom: 1px solid #DDD;
}
table.tb-rate-table td.align-c {
    text-align: center;
}
table.tb-rate-table th {
    font-weight: 700;
    text-align: center;
}
table.tb-rate-table.thm-list td {
    padding: 15px 10px;
    border-bottom: 1px solid #DDD;
}
table.tb-rate-table {
    width: 100%;
    table-layout: fixed;
    background: #FFF;
    line-height: 1.4;
    border-collapse: collapse;
    border-spacing: 0;
}
/* //修改评价 */
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
