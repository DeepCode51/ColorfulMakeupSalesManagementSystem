/*
 * @Describe: 搜索商品导航标签
 */
<template>
  <div class="item-class-show">
    <Row class="item-class-group" v-for="(items, index) in tagsInfo" :key="index" >
      <i-col class="item-class-name" span="3">{{ items.tagName }} : </i-col>
      <i-col class="item-class-select" span="21">
        <span v-for="(item, subIndex) in items.tags"  @click="choseByName" :data-name="item" :class="[choosedName==item?'selected1':'no']"><a>{{ item }}</a></span>
      </i-col>
    </Row>
  </div>
</template>

<script>
import store from '@/vuex/store';
import { mapState, mapActions, mapGetters, mapMutations } from 'vuex';
export default {
  name: 'GoodsClassNav',
  data () {
    return {
      choosedName:'',
      tagsInfo: [
        {
          tagName: '品牌',
          tags: [ '完美日记', '花西子', '毛戈平', '卡姿兰', '稚优泉', '橘朵 ', '美康粉黛', '巧迪尚惠', '希芸', '谢馥春 ', '火烈鸟' ,'珂拉琪']
        },
        {
          tagName: '品类',
          // XXS XS S M 均码 L XL 2XL XXXL XXXXL
          tags: [ '修容', '口红', '眼影', '眉笔', '睫毛膏', '粉底', '散粉', '唇膏', '眼线', '其他' ]
        }
      ]
    };
  },
  methods:{
    choseByName(options){
      // console.log(options)
      let name=options.srcElement.innerText ///有一个a标签在，所以不能通过data-set直接获取
      this.choosedName=name;
      this.$store.commit('getSearchName',name)
      console.log(this.$store.state.searchName)
      this.$emit('chooseName')

    }
  }
};
</script>

<style scoped>
.item-class-show {
  margin: 15px auto;
  width: 100%;
  border:solid 1px #ccc;
}
.item-class-group {
  margin-top: 1px;
  height: 45px;
  border-bottom: 1px solid #ccc;
}
/* .item-class-group:first-child {
  border-top: 1px solid #ccc;
} */
.item-class-name {
  padding-left: 15px;
  line-height: 44px;
  color: #666;
  font-weight: bold;
  background-color: #f3f3f3;
}
.item-class-name:first-child {
  line-height: 43px;
}
.item-class-select span {
  margin-left: 15px;
  margin-right: 15px;
  width: 180px;
  /* color: #005aa0; */
  color:#606468;
  line-height: 45px;
  cursor: pointer;
}
/* .item-class-select span a{
  color:#606468;
} */
.item-class-select span a:hover{
  color:#da361d;
}
/* .item-class-select span a:active{
  color: #0000FF
} */
.selected1{
  background: rgb(196, 67, 67);


}
.selected1 a{
 color: #fff;
}
.no a{
color:#606468;
}

</style>
