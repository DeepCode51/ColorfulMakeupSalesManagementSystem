<template>
  <div class="shop-nav-box">
    <div class="shop-nav-container">
      <ul>
        <li v-for="(item, index) in shopNav" :key="index">
          <router-link to="/merchant">{{item}}</router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GoodsDetailNav',
  data () {
    return {
      shopNav: [ '所有宝贝', '新品上市', '全店热销版单', '关于缤色彩妆', '会员宠粉升级','自助购物' ]
    };
  }
};
</script>

<style scoped>
.shop-nav-box {
  height: 30px;
  background-color: #7a040e !important;
  margin-bottom: 40px;
}

.shop-nav-container {
  width: 80%;
  height: 100%;
  margin: 0px auto;
}

.shop-nav-container ul {
  padding: 0px;
  margin: 0px;
  list-style: none;
  justify-content: center;
  display: flex;
}

.shop-nav-container li {
  float: left;
  margin-right: 30px;
  line-height: 35px;
}

.shop-nav-container a {
  text-decoration: none;
  color: rgb(233, 224, 224);
  font-weight: bold;
}
</style>
