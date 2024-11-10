<template>
    <div>
        <div class="home-top">
            <div class="top1">
                <p class="top-text">缤色彩妆</p>
            </div>
            <div class="top3">
                <p> 我的收藏夹</p>
            </div>
            <div class="top2">
                <Input  v-model="searchData" size="large" class="sousuo" placeholder="输入你想查找的商品" style="outline:none"  >
                <Button slot="append" icon="ios-search" @click="goSearch" class="searchBtn"></Button>
                </Input >
            </div>
        </div>
        <div class="coll-box">
            <div class="action">
                <p v-if="hasaction" class="action-title1" @click="clearColl">清空收藏夹</p>
                <p v-if="hasaction" class="action-title1" @click="choseAll"><Icon type="ios-checkmark-circle-outline" v-if="allchose"/><Icon type="md-radio-button-off" v-if="!allchose"/>全选</p>
                <p v-if="hasaction" class="action-title1" @click="deleByBatch"><Icon type="md-trash" />删除</p>
                <p class="action-title" @click="openAction">批量管理</p>
            </div>
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="全部宝贝" name="first">
                    <collection ref="coll" :searchData="searchData"></collection>
                </el-tab-pane>
                <el-tab-pane label="失效宝贝" name="second">
                    <!-- 调用失效的接口 -->
                </el-tab-pane>

            </el-tabs>
            <div class="cai-like">
                <p class="like-p">猜你喜欢</p>
                <hr style="height:2px;border:none;color:#E4E7ED;background-color:#E4E7ED;"/>
                <div>
                     <hot-cart></hot-cart>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import HotCart from '@/components/common/HotCart';
import { sendServer,sendServerGet } from '../utils/common';
import Collection from './common/Collection.vue';
export default {
    components:{
        Collection,
        HotCart
    },
    data(){
        return{
            userInfo:JSON.parse(localStorage.getItem('userdata')),
            activeName:'first',
            searchData:'',
            hasaction:false,
            allchose:false,


        }
    },
    methods:{
        deleByBatch(){
            let list = []
            this.$refs.coll.collectList.forEach(item => {
                if(item.choseItem){
                    list.push(item.id)
                }
            })
            console.log(list)
            this.$confirm('是否删除这些商品?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.delByBatch(list);
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        delByBatch(ids){
            this.axios({
                method:"GET",
                url:'http://localhost:8089/hf/like/deleteByBatch?ids='+ids
            }).then((res)=>{
                if(res.data.code==100){
                    this.$refs.coll.getAllCollection()
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }else{
                    this.$message({
                        type: 'error',
                        message: '删除失败!'
                    });
                }
            })
        },
        //全选
        choseAll(){
            this.allchose=!this.allchose
            if(this.allchose){
                this.$refs.coll.collectList.forEach(item=>{
                    item.choseItem=true
                })
            }else{
                this.$refs.coll.collectList.forEach(item=>{
                    item.choseItem=false
                })
            }

        },
        handleClick(tab, event) {
            if(tab.label=='全部宝贝'){
                this.$refs.coll.getAllCollection();
                this.searchData=null
            }
        },
        goSearch(){
            //调用子组件的方法
            this.$refs.coll.getAllCollection(this.searchData)
        },
        openAction(){
            this.hasaction=!this.hasaction
            this.$refs.coll.chose=!this.$refs.coll.chose//调用子组件的属性，改变相应的状态
        },
        //清空收藏夹
        clearColl(){
            this.$confirm('是否清空?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let userId= this.userInfo.id
                console.log(userId)
                let params={},send={}
                send.userId=userId;
                params.send=send;
                params.url='like/clearLike'
                sendServer(params,(commRes)=>{
                    console.log(commRes)
                    if(commRes.code==100){
                        this.$Message.success("清空成功了！")
                        this.$refs.coll.collectList=[]
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消清空'
                });
            });
        },

    }

}
</script>
<style>
.el-tabs__active-bar {
    position: absolute;
    bottom: 0;
    left: 0;
    height: 2px;
    background-color: #f40;
    z-index: 1;
}
.el-tabs__item.is-active {
    color: #f40;
}
.el-tabs__item:hover {
    color: #f40;
}

</style>

<style scoped>
.cai-like{
    margin-top: 40px;
}
.like-p{
    font-size: 15px;
    font-weight: 700;
    color: #f40;
}
.action-title:hover{
    border: 1px solid red;
    color: red;
}
.action-title1:hover{
    border: 1px solid red;
    color: red;
}
.action-title1{
    border: 1px solid rgb(177, 167, 167);
    padding: 5px;
    margin-right: 10px;
}
.action-title{
    border: 1px solid rgb(177, 167, 167);
    padding: 5px;
}
.action{
    right: 0;
    position: absolute;
    z-index: 2;
    display: flex;
    justify-content: flex-end;
    font-size: 12px;
    text-align: right;
}
.coll-box{
    width: 1050px;
    margin: 10px auto;
    position: relative;
}
.home-top{
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin: 0 auto;
  margin-bottom: 10px;
  background-color: #7a040e;
}
.home-top .top1{
  width: 100px;
  height: 80px;
  font-size: 26px;
  color: #fff;
  font-weight: 600;
  line-height: 80px;
  margin-left: 60px;
}
.top2{
  /* background-color: rgb(30, 158, 158); */
  width: 350px;
  margin-right: 60px;
  float: right;
}
.top3{
  /* background-color: rgb(54, 177, 23); */
  width: 650px;
  height: 80px;
  line-height: 80px;
  margin-left: 20px;
  font-size: 16px;
  color: #fff;
  text-align: left;

}
.top3-home{
  width: 60px;
  /* background-color: cadetblue; */
  margin-right: 10px;
}
.top3 a{
  color:#fff;
}
.sousuo {
  margin: 5px 0px;
  height: 36px;
}

.sousuo >>> .ivu-input:focus{
    outline:none;
    border:none;
    box-shadow: 0 0 0 2px transparent;
    border-bottom: 1.3px solid gainsboro;
}

</style>
