<template>
  <div id="app" >
    <Header v-if="excludeHead.indexOf($route.name) == -1 && excludeHeadpath.indexOf($route.path)==-1"></Header>
    <router-view :key="key"/>
    <!-- 部分路由不应该包含这个Footer -->
    <Footer v-if="excludeRoutes.indexOf($route.name) == -1 && excludeFootpath.indexOf($route.path)==-1"></Footer>
  </div>
</template>

<script>
import Header from '@/components/header/Header';
import Footer from '@/components/footer/Footer';
export default {
  name: 'App',
  computed:{
    key(){
      return this.$route.name?this.$route.name+ +new Date():this.$route+ + new Date()
    }

  },
  data () {
    return {
      
      excludeRoutes: ['HomeIndex','setting', 'MyOrder','Login','InputInfo','SignUpDone'],
      excludeHead:['Login','InputInfo','SignUpDone'],
      excludeHeadpath:['/SignUp'],
      excludeFootpath:['/SignUp']
    };
    
  },
  components: {
    Header,
    Footer
  }
};
</script>
<style lang="scss">
  body{
    font-size: 12px;
    margin:0px;
    width:100%;
    height:100%;
    overflow-x: auto;
    font-size: 12px;
  }
</style>
