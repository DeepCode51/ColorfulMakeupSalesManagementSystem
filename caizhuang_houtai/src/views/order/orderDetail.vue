<template>
  <div class="app-container">
      <div class="order-box">
        <div>
            <el-steps :active="formatStepStatus(bgOrder.status)" finish-status="success" align-center>
                <el-step title="支付订单"></el-step>
                <el-step title="平台发货"></el-step>
                <el-step title="确认收货"></el-step>
                <el-step title="买家已评价"></el-step>
                <el-step title="商家已评价"></el-step>
                <el-step title="用户申请退货"></el-step>
                <el-step title="退货成功"></el-step>
            </el-steps>
        </div>

        <el-card shadow="never" style="margin-top: 15px">
            <div class="operate-container">
                <i class="el-icon-warning color-danger" style="margin-left: 20px"></i>
                <span class="color-danger" v-if="bgOrder.status==1">当前订单状态：待发货</span>
                <span class="color-danger" v-if="bgOrder.status==2">当前订单状态：已发货</span>
                <span class="color-danger" v-if="bgOrder.status==3">当前订单状态：已收货</span>
                <span class="color-danger" v-if="bgOrder.status==4">当前订单状态：交易关闭</span>
                <span class="color-danger" v-if="bgOrder.status==5">当前订单状态：买家已评价</span>
                <span class="color-danger" v-if="bgOrder.status==6">当前订单状态：商家已评价</span>
                <span class="color-danger" v-if="bgOrder.status==7">当前订单状态：用户申请退货</span>
                <span class="color-danger" v-if="bgOrder.status==8">当前订单状态：退货成功</span>
                <div class="operate-button-container" v-show="bgOrder.status==1">
                    <el-button size="mini" @click="showUpdateReceiverDialog">修改收货人信息</el-button>
                    <el-button size="mini">修改商品信息</el-button>
                    <el-button size="mini" @click="">修改费用信息</el-button>
                    <el-button size="mini" @click="">关闭订单</el-button>
                    <el-button size="mini" @click="">备注订单</el-button>
                    <el-button size="mini" @click="updataStatus">发货</el-button>
                </div>

                <div class="operate-button-container" v-show="bgOrder.status==2">
                    <el-button size="mini" @click="">修改收货人信息</el-button>
                </div>
                <div class="operate-button-container" v-show="bgOrder.status==5">
                    <el-button size="mini" @click="giveReview">评价买家</el-button>
                </div>
                 <div class="operate-button-container" v-show="bgOrder.status==3 || bgOrder.status==4||bgOrder.status==5 ||bgOrder.status==6">
                    <el-button size="mini" @click="">删除订单</el-button>
                </div>
                <div class="operate-button-container" v-show="bgOrder.status==2||bgOrder.status==3">
                    <el-button size="mini" @click="">订单跟踪</el-button>
                    <el-button size="mini" @click="">备注订单</el-button>
                </div>
              <div class="operate-button-container" v-show="bgOrder.status==7">
                <el-button size="big" @click="returnOrder">退货成功</el-button>
              </div>


            </div>


            <div style="margin-top: 20px">
                <i class="el-icon-s-flag"></i>
                <span class="font-small">基本信息</span>
            </div>


            <div class="table-layout">
                <el-row>
                    <el-col :span="4" class="table-cell-title">订单编号</el-col>
                    <el-col :span="4" class="table-cell-title">订单总价</el-col>
                    <el-col :span="4" class="table-cell-title">配送方式</el-col>
                    <el-col :span="4" class="table-cell-title">支付方式</el-col>
                    <el-col :span="4" class="table-cell-title">订单备注</el-col>
                    <el-col :span="4" class="table-cell-title">创建时间</el-col>
                </el-row>
                <el-row>
                    <el-col :span="4" class="table-cell">{{bgOrder.orderId}}</el-col>
                    <el-col :span="4" class="table-cell">{{bgOrder.totalprice}}</el-col>
                    <el-col :span="4" class="table-cell">{{bgOrder.deliverymethod}}</el-col>
                    <el-col :span="4" class="table-cell">{{bgOrder.paymethod}}</el-col>
                    <el-col :span="4" class="table-cell">{{bgOrder.memo}}</el-col>
                    <el-col :span="4" class="table-cell">{{bgOrder.createTime}}</el-col>
                </el-row>
            </div>

            <div style="margin-top: 20px">
                <i class="el-icon-s-flag"></i>
                <span class="font-small">收货人信息</span>
            </div>
            <div class="table-layout">
                <el-row>
                <el-col :span="6" class="table-cell-title">收货人</el-col>
                <el-col :span="6" class="table-cell-title">手机号码</el-col>
                <el-col :span="6" class="table-cell-title">邮政编码</el-col>
                <el-col :span="6" class="table-cell-title">收货地址</el-col>
                </el-row>
                <el-row>
                <el-col :span="6" class="table-cell">{{bgOrder.receName}}</el-col>
                <el-col :span="6" class="table-cell">{{bgOrder.recePhone}}</el-col>
                <el-col :span="6" class="table-cell">{{bgOrder.postcode}}</el-col>
                <el-col :span="6" class="table-cell">{{bgOrder.address}}</el-col>
                </el-row>
            </div>

            <div style="margin-top: 20px">
                 <i class="el-icon-s-flag"></i>
                <span class="font-small">商品信息</span>
            </div>
            <el-table
                ref="orderItemTable"
                :data="bgOrder.bgOrderItemList"
                style="width: 100%;margin-top: 20px" border>
                <el-table-column label="商品图片" width="120" align="center">
                    <template slot-scope="scope">
                        <img :src="temfielPath+scope.row.goods.picture" style="height: 80px">
                    </template>
                </el-table-column>

                <el-table-column label="商品名称" align="center">
                    <template slot-scope="scope">
                        <p>{{scope.row.goods.description}}</p>
                    </template>
                </el-table-column>

                <el-table-column label="价格/货号" width="120" align="center">
                    <template slot-scope="scope">
                        <p>价格：￥{{scope.row.goodsbuyprice}}</p>

                    </template>
                </el-table-column>

                <el-table-column label="属性" width="120" align="center">
                    <template slot-scope="scope">
                        {{scope.row.size}}
                    </template>
                </el-table-column>
                <el-table-column label="数量" width="120" align="center">
                    <template slot-scope="scope">
                        {{scope.row.quantity}}
                    </template>
                </el-table-column>

                <el-table-column label="小计" width="120" align="center">
                    <template slot-scope="scope">
                        ￥{{scope.row.quantity*scope.row.goodsbuyprice}}
                    </template>
                </el-table-column>
            </el-table>
            <div style="float: right;margin: 20px">
                <p>运费:0.00元</p>
                合计：<span class="color-danger">￥{{bgOrder.totalprice}}</span>
            </div>


        </el-card>

        <el-dialog title="修改收货人信息"
                   :visible.sync="receiverDialogVisible"
                   width="40%">
            <el-form :model="receiverInfo"
                    ref="receiverInfoForm"
                    label-width="150px">
                <el-form-item label="收货人姓名：">
                <el-input v-model="receiverInfo.receiverName" style="width: 200px"></el-input>
                </el-form-item>
                <el-form-item label="手机号码：">
                <el-input v-model="receiverInfo.receiverPhone" style="width: 200px">
                </el-input>
                </el-form-item>
                <el-form-item label="邮政编码：">
                <el-input v-model="receiverInfo.receiverPostCode" style="width: 200px">
                </el-input>
                </el-form-item>
                <el-form-item label="详细地址：">
                <el-input v-model="receiverInfo.receiverDetailAddress" type="textarea" rows="3">
                </el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="receiverDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleUpdateReceiverInfo">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="进行评价"
                   :visible.sync="modalReview"
                   width="40%">
            <el-form :model="reviewInfo"
                    ref="reviewInfoForm"
                    label-width="150px">
                <el-form-item label="订单编号">
                    <div>{{bgOrder.orderId}}</div>
                </el-form-item>
                <el-form-item label="评价等级">
                    <div>

                        <el-radio-group v-model="reviewInfo.degree">
                            <el-radio :label="0">
                                <!-- 好评 -->
                                <span><img :src="hp"/></span>
                            </el-radio>
                            <el-radio :label="1">
                                 <!-- 中评 -->
                                <span><img :src="zp"/></span>
                            </el-radio>
                            <el-radio :label="2">
                                <!-- 差评 -->
                                <span><img :src="cp"/></span>
                            </el-radio>
                        </el-radio-group>
                    </div>
                </el-form-item>
                 <el-form-item label="评价内容">
                    <el-autocomplete
                        placement="bottom"
                        type="textarea"
                        :rows="2"
                        class="inline-input"
                        v-model="reviewInfo.content"
                        :fetch-suggestions="querySearch"
                        placeholder="请输入内容"
                        @select="handleSelect">
                    </el-autocomplete>
                 </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="modalReview = false">取 消</el-button>
                <el-button type="primary" @click="submitReview">确 定</el-button>
            </span>
        </el-dialog>
      </div>
  </div>
</template>

<script>
import { sendServer } from '@/utils/common';
import { compile } from 'path-to-regexp';
export default {
    data(){
        return{

            modalReview:false,
            temfielPath:'http://localhost:8089/img/',
            bgOrder:{},
            temfielPath:'http://localhost:8089/img/',
            receiverDialogVisible:false,
            receiverInfo:{},
            reviewInfo:{
                degree:'',
                content:'',
            },
            hp:require('../../assets/img/hp.png'),
            zp:require('../../assets/img/zp.png'),
            cp:require('../../assets/img/cp.png'),
            contentList:[
                {"value":"谢谢光临!爽快的买家,赞!"},
                {"value":"有您的支持我们会做得更好，凯拉欢迎亲的再次光临！！!"},
                {"value":"好买家，交易虽然结束，服务仍在继续，我们会用时间证明，您的选择没有错！!"},
                {"value":"感谢您对本店的支持，欢迎下次再来，祝您生活愉快，工作顺利！!"},
                {"value":"感谢亲光临本店，我们将一如既往的为您提供优质.时尚.高性价比的服务，我们完善的售前.售后服务，将全程为您的愉快购物保驾护航，希望您在本店购物愉快！如有任何问题，可随时与本店联系！记得收藏下本店，没事的时候常来逛逛，一定会有意外的惊喜！期待再合作啦!顺祝：工作顺利，生活愉快，身体健康！!"},
                {"value":"谢谢小可爱哟，有您的支持我们会做得更好呢，欢迎您再次光临哦!"},
                {"value":"感谢您的光临，希望我们的努力能给您带来一份快乐与满意！"},
                {"value":"我们坚信所有的误解皆源于沟通不畅，我们一直拿诚意对待每位顾客，若您在购物途中遇到任何疑问、有任何意见，都可反馈给我们，我们定会给您妥善处理，给您一个满意的答复!"},
                {"value":"我们拒绝恶意差评，该买家毫无道德底线，恶意差评我们绝不妥协"},
            ],
        }
    },
    mounted(){
        console.log(this.contentList)
    },
    created(){
        this.getOrderDetail()
    },
    methods:{
        //提交评价
        submitReview(){
            let ids=[]
            this.bgOrder.bgOrderItemList.forEach(item=>{
                ids.push(item.orderGoodsId)//==>可以获取 ids
            })
            ids.forEach(item=>{
                let params={},send={}
                let time=new Date().getTime()
                send.userId=this.bgOrder.userId
                send.orderid=this.bgOrder.orderId
                send.degree=this.reviewInfo.degree
                send.content=this.reviewInfo.content;
                send.goodsId=item;
                send.createTime=time;
                params.send=send;
                console.log(send)
                params.url="review/insertBgReview";
                sendServer(params,(commRes)=>{
                    if(commRes.code==100){
                        send.orderId=this.bgOrder.orderId
                        send.status=6;//表示商家已经评价 改交易已完成
                        params.send=send;
                        params.url="bgorder/updateStatus";
                        //评价成功之后，改变状态，显示为已评价
                        sendServer(params,(commRes)=>{
                            if(commRes.code==100){
                                this.bgOrder.status=6
                                this.modalReview=false
                            }
                        })

                    }
                })
            })

            //前台查看商家评价  有用到商品的Id，一个订单有很多商品 ，前台评价的时候可以分别评价这个商品 ，后台评价只针对订单而言，那需要商品ID吗？
            //假设需要商品id ，有一个订单号，商品id，商品id可能是多个的？后台评价只有一次 ，前台要是想查看商品，可以根据订单号 ，但是订单要是被删了，那么评价的商品也看不了了。
            //后台查看我的评价的时候，展示订单编号，评级，被评用户，购买时间，评价内容 ，评价时间，假设被评用户注销了，那么userid去查用户会报错？？或许不该用userid，应该用username做主键，

        },
        querySearch(queryString, cb) {
            var contentList = this.contentList;
            var results = queryString ? contentList.filter(this.createFilter(queryString)) : contentList;
            // 调用 callback 返回建议列表的数据
            cb(results);
        },
        createFilter(queryString) {
            return (contentList) => {
                return (contentList.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        handleSelect(item) {
        },
        giveReview(){
            this.modalReview=!this.modalReview
        },
        //进行发货处理，改变订单的status==2表示已发货
        updataStatus(){
            this.$confirm('是否要进行发货操作?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.fahuo();
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消发货'
                });
            });
        },
        fahuo(){
            let params={},send={};
            let orderId=this.$route.params.orderId;
            send.orderId=orderId
            send.status=2;//表示发货
            params.send=send;
            params.url="bgorder/updateStatus";
            sendServer(params,(commRes)=>{
                if(commRes.code==100){
                    let params={},send={};
                    send.orderId=orderId;
                    send.status=2;//前台订单status==2 表示已发货
                    params.send=send;
                    params.url="order/updateStatus";
                    sendServer(params,(commRes)=>{
                        if(commRes.code==100){
                            this.$message({
                                type: 'success',
                                message: '发货成功!'
                            });
                            this.bgOrder.status=2;
                        }
                    })

                }
            })
        },
      //进行退货处理，改变订单的status==8表示已退货
      returnOrder(){
        this.$confirm('是否确认收到用户的退货?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.tuihuo();
        }).catch(() => {
        });
      },
      tuihuo(){
        let params={},send={};
        let orderId=this.$route.params.orderId;
        send.orderId=orderId
        send.status=8;//表示已退货
        params.send=send;
        params.url="bgorder/updateStatus";
        sendServer(params,(commRes)=>{
          if(commRes.code==100){
            let params={},send={};
            send.orderId=orderId;
            send.status=8;//前台订单status==8 表示退货成功
            params.send=send;
            params.url="order/updateStatus";
            sendServer(params,(commRes)=>{
              if(commRes.code==100){
                this.$message({
                  type: 'success',
                  message: '退货成功!'
                });
                this.bgOrder.status=8;
              }
            })

          }
        })
      },
        formatStepStatus(value) {
            if (value == 1) {
                //支付订单
                return 1;
            } else if (value == 2) {
                //已发货
                return 2;
            } else if (value == 3) {
                //已完成
                return 3;
            }else if(value==5){
                return 4;//买家已评价
            }else if(value==6){
                return 5
            }else if(value==7){
              return 6
            }else if(value==8){
              return 7
            }
            else {
                //待付款、已关闭、无限订单
                return 1;
            }
        },
        getOrderDetail(){
            var orderId=this.$route.params.orderId;
            let params={},send={}
            send.orderId=orderId;
            params.send=send;
            params.url="bgorder/getOrderDetail";
            sendServer(params,(commRes)=>{
                if(commRes.code==100){
                    console.log(commRes)
                    this.bgOrder=commRes.extend.bgOrder
                }
            })
        },
        showUpdateReceiverDialog(){
            this.receiverDialogVisible=true;
            this.receiverInfo={
                orderId:this.bgOrder.orderId,
                receiverName:this.bgOrder.receName,
                receiverPhone:this.bgOrder.recePhone,
                receiverPostCode:this.bgOrder.postcode,
                receiverDetailAddress:this.bgOrder.address
            }
        },
        //修改收件人信息
        handleUpdateReceiverInfo(){

        },
        //新增一个信誉分的话 ， 基础分数都是500 算了算了不搞了


    }

}
</script>

<style scoped>
@import url("//unpkg.com/element-ui@2.15.1/lib/theme-chalk/index.css");
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
    height: 60px;
    line-height: 40px;
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
