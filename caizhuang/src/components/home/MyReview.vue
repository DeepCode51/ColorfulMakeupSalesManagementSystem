<template>
    <div class="order-box">
        <el-tabs v-model="activeName" @tab-click="handleClick" class="tabs">
            <el-tab-pane label="我的评价" name="first">
               <review-template b="1"></review-template>
            </el-tab-pane>
            <el-tab-pane label="来自卖家的评价" name="second">
              <review-template b="2"></review-template>
            </el-tab-pane>

            <el-tab-pane  name="six">
                <span slot="label"><i class="el-icon-delete"></i>评价回收站</span>
               
            </el-tab-pane>
        </el-tabs>
    
       
    </div>
</template>

<script>
import ReviewTemplate from '../home/ReviewTemplate'
export default {
    components:{
        ReviewTemplate
    },
    data(){
        return{
             activeName:'first',
             

        }
    },
    methods:{
        handleClick(tab, event) {
            // if(tab.label=='全部宝贝'){
            //     this.$refs.coll.getAllCollection();
            //     this.searchData=null
            // }
        },
    }

}
</script>

<style>
.el-tabs__item:hover {
  color: #f40;
  
}
.el-tabs__item.is-active {
    color: #f40;
    
}
.el-tabs__active-bar {
    position: absolute;
    bottom: 0;
    left: 0;
    height: 2px;
    background-color: #f40;
    z-index: 1;
}
</style>
<style scoped>
.action:hover{
    color:#f40;
}
.action{
    right: 40px;
    position: absolute;
    z-index: 2;
    display: flex;
    justify-content: flex-end;
    font-size: 13px;
    text-align: right;   
    margin-top: 20px;
}
.order-box{
    width: 100%;
}
.tabs{
    padding: 10px;
}
</style>