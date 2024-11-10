<template>

    <div class="container">
      <div class="top1">
        <img :src="searchImgurl" class="logo-search"/>
      </div>
      <div class="top2">
        <Input  v-model="searchData" size="large" class="sreach" placeholder="输入你想查找的商品" style="outline:none"  >
          <Button slot="append" icon="ios-search" @click="goSearch" class="searchBtn"></Button>
        </Input >
        <Tag v-for="(item, index) in promotionTags" :key="index" closable  @on-close="closeTags(index)"><span @click="selectTags(index)">{{item}}</span></Tag>
      </div>
    </div>



</template>

<script>
export default {
  name: 'Search',
  data () {
    return {
      searchImgurl:require('../../static/img/index/logo.png'),
      searchData: '',
      promotionTags: ['彩妆', '口红', '眼影', '眉笔', '正红','完美日记']
    };
  },
  methods: {
    closeTags (index) {
      this.promotionTags.splice(index, 1);
    },
    selectTags (index) {
      this.searchData = this.promotionTags[index];
    },
    goSearch () {
      let searchData=this.searchData;
      this.$store.commit('getSearchName',searchData)
      console.log(this.$store.state.searchName)
      this.$router.push({path: '/goodsList',query: { searchData: this.$store.state.searchName}});//, query: { searchData: this.searchData }
      // this.$emit('chooseName');
    }
  }
};
</script>

<style scoped>
.container {
  padding-top: 17px;
  margin: 0px auto;
  margin-bottom: 15px;
  width:100%;
  display: flex;
  flex-direction: row;
  background-color: #fff;
  justify-content: center
}
.sreach {
  margin: 5px 0px;
  border: 3px solid #ff5000;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  overflow: hidden;
  height: 36px;
}
.logo-search{
  width: 160px;
  height: 80px;
  margin: 0 auto;
}
.top1{
  width: 210px;
}
.top2{
  width: 700px;
}


.sreach >>> .ivu-input:focus{
    outline:none;
    border:none;
    box-shadow: 0 0 0 2px transparent;
    border-bottom: 1.3px solid gainsboro;
}

</style>
