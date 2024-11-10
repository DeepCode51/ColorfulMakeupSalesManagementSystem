<template>
  <div>
    <Row class="top-box">
        <Col span="12" class="row-text">
        <img :src="logoImg" class="logo-img"/>
        缤色彩妆<span class="span-text">欢迎注册</span></Col>
        <Col span="12" class="row-text1">
        已有账号，请<router-link to="/login">登录<Icon type="person"></Icon></router-link>
        </Col>
    </Row>
    <Row class="container">

      <i-col span="10" class="box">
        <div class="sign-up-title">
        </div>
        <div class="sing-up-step-box">
          <div class="step-box">
            <Steps :current="signUpStep">
                <Step title="验证手机号" icon="iphone"></Step>
                <Step title="填写账号信息" icon="person-add"></Step>
                <Step title="注册成功" icon="ios-checkmark-outline"></Step>
            </Steps>
          </div>
          <div class="sign-up-box">
            <transition mode="out-in">
              <router-view></router-view>
            </transition>
          </div>
        </div>
      </i-col>
    </Row>


  </div>
</template>

<script>
import store from '@/vuex/store';
import { mapState, mapMutations } from 'vuex';

export default {

  name: 'SignUp',
  data () {
    return {
      logoImg:require("../../static/img/logo.png"),
      // index: 0
    };
  },




  computed: {
    ...mapState(['signUpStep'])
  },
  methods: {




    ...mapMutations(['SET_SIGN_UP_SETP'])
  },
  store,
  mounted () {

    // console.log(this.$store.state.name)
    this.SET_SIGN_UP_SETP(0);
  }
};
</script>

<style scoped>

.code {
  margin: 400px auto;
  width: 114px;
  height: 40px;
  border: 1px solid #4ea0f1;
}
.container {
  width: 100%;
  margin: 15px auto;
  height: 600px;
  overflow: hidden;
  background-image: url('../../static/img/index/czbg.png');
  background-position:center;
  background-repeat:no-repeat;
  background-size:cover;

}
.signup-img-box {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.signup-img-box>img {
  height: 80%;
}
.sign-up-title {
  width: 430px;
  margin: 10px auto;
  height: 40px;
}
.sing-up-step-box {
  margin: 15px auto;
  /* padding-left: 30px;
  padding-top: 30px; */
  width: 430px;
  height: 430px;
  /* height: 515px; */
  border: 1px solid #4ea0f1;
  /* 495060 */
  border-radius: 5px;
}
.sign-up-box {
  padding-left: 30px;
  padding-top: 30px;
  margin: 30px 25px;
  width: 350px;
  display: flex;
  align-items: center;
}
.row-text{
  margin: 10px auto;
  color: #4ea0f1;
  font-size: 30px;
  text-align: center;
}
 .logo-img{
    width: 40px;
    height: 40px;
    line-height: 72px;
    /* display: table-cell; */
    text-align: center;
    vertical-align: middle;

  }
  .span-text{
  padding-left: 20px;
  color: black;
  font-size: 20px;
}
.row-text1{
  margin: 25px auto;
  color: #aca09d;
  font-size: 15px;
  text-align: center;
  vertical-align: middle;
}
.top-box{
  box-shadow:
    -2px 0 3px -1px #fff,
    /* 左边阴影 */
    0 -2px 3px -1px #fff,
     /* //顶部阴影 */
    0 2px 8px -1px rgb(235, 225, 225),
    /* //底部阴影 */
    2px 0 3px -1px #fff;
    /* //右边阴影 */
    /* 3px 为阴影扩展半径 不难看出box-shadow: 第1像素 第2像素 第3像素 第4像素 颜色； */
}
.row-text1 a{
  color: #4ea0f1;
}
.foot{
    /* background: #fff8f0;  */
    width: 100%;
    padding-top: 15px;
    padding-bottom: 10px;
    text-align: center;
    color: #999;
     box-shadow: 2px -2px 2px rgb(235, 225, 225);

}
.foot p{
  font-size: 13px;
}
.step-box{
  padding-top: 15px;
  background-color: #fff;
  height: 50px;
   border-radius: 5px 5px 0 0;
}

/* {box-shadow:[inset] x-offset y-offset blur-radius spread-radiuscolor}

对象选择器 {box-shadow:[投影方式] X轴偏移量 Y轴偏移量阴影模糊半径 阴影扩展半径 阴影颜色} */
</style>
