<template>
<!-- 登录页面 -->
<!-- --registry=https://registry.npm.taobao.org -->
  <div>

    <Row>
        <Col span="8" class="row-text">
        <img :src="logoImg" class="logo-img"/>缤色彩妆<span class="span-text">欢迎您！</span></Col>
        <Col span="12"></Col>
    </Row>


    <Row class="container">

      <i-col span="7" class="login-box">
        <div class="login-container">
          <div class="login-header">
            <p>登录</p>
          </div>
          <div class="form-box">
            <Form ref="loginForm" :model="loginForm" :rules="ruleInline">

              <FormItem prop="username">
                  <i-input type="text" v-model="loginForm.username" clearable size="large" placeholder="用户名">
                      <Icon type="person" slot="prepend"></Icon>
                  </i-input>
              </FormItem>

              <FormItem prop="password">
                  <i-input type="password" v-model="loginForm.password" clearable size="large" placeholder="密码">
                      <Icon type="ios-locked-outline" slot="prepend"> </Icon>
                  </i-input>
              </FormItem>

              <FormItem>
                  <!-- <Button type="primary" class="btn" size="large" @click="handleSubmit('formInline')" long>登录</Button> -->
                  <Button type="primary" class="btn" size="large" @click="onLogin()" long>登录</Button>
              </FormItem>

               <FormItem>
                  <p class="ptext"><router-link to="/SignUp">注册账号<Icon type="person-add"></Icon></router-link></p>
              </FormItem>
          </Form>
          </div>
        </div>
      </i-col>
    </Row>

  </div>
</template>

<script>
import Cookies from "js-cookie";
import store from '@/vuex/store';
import { mapMutations, mapActions } from 'vuex';
export default {
  name: 'Login',
  data () {
    return {
      logoImg:require("../../static/img/logo.png"),
      loginForm: {
        username: '',
        password: ''
      },
      ruleInline: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码不能少于6位', trigger: 'blur' }
        ]
      }
    };
  },

  methods: {
    ...mapMutations(['SET_USER_LOGIN_INFO']),
    ...mapActions(['login']),

    onLogin(){
      localStorage.removeItem('userData');
      Cookies.remove('token1');
      var vm=this;
      this.$refs.loginForm.validate((valid)=>{
         if (valid) {
          this.loading = true
          var vm=this;
          this.axios({
            method:'GET',
            url:'http://localhost:8089/hf/customer/login?userName='+vm.loginForm.username+'&password='+vm.loginForm.password}).then(function(res)
          {
            console.log(res.data)
            vm.loading=false;
            if(res.data.code===100){
              vm.$Message.success('登录成功');
              Cookies.set("token1",res.data.extend.user.token)
              localStorage.setItem('userdata',JSON.stringify(res.data.extend.user))
              // dispatch：含有异步操作，例如向后台提交数据，写法： this.$store.dispatch('action方法名',值)
              // commit：同步操作，写法：this.$store.commit('mutations方法名',值)
              vm.$store.commit('setUserInfo',res.data.extend.user)
              console.log(vm.$store.state.userInfo)
              // 接收参数，如果存在redirect参数，登录后重定向到指定页面
              if(vm.$route.query.redirect){
                vm.$router.push({ path: vm.$route.query.redirect });
              }else{
                // 如不存在，直接跳转到首页
                vm.$router.push({ path:'/'})

              }


            }else{
              vm.$Message.error(res.data.extend.message);
            }
          });
        } else {
          vm.$Message.error('用户名或密码不能为空');
          return false
        }
      })

    }
  },
  store
};
</script>

<style scoped>
.container {
  /* margin-top: 30px; */
  width: 100%;
  height: 550px;
  /* background-color: #fff; */
  background-image: url('../../static/img/index/czbg.png');
  background-position:center;
  background-repeat:no-repeat;
  background-size:cover;



}
.login-img-box {
  height: 485px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}
.login-img-box>img {
  width: 68%;
}
.login-box {
  height: 485px;
  margin-left: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.login-container {
  width: 80%;
  height: 300px;
  border: #4ea0f1 solid 1px;
  border-radius: 10px;
}
.login-header {
  height: 50px;
  font-size: 20px;
  text-align: center;
  line-height: 50px;
  letter-spacing: 5px;
  color: #fff;
  /* background-color: #ED3F14; */
  background-color: #4ea0f1;
  border-radius: 10px 10px 0 0;
}
.form-box {
  width: 80%;
  margin: 30px auto;
}
.foot{
    /* background: #fff8f0;  */
    width: 100%;
    padding-top: 15px;
    padding-bottom: 10px;
    text-align: center;
    color: #999
}
.foot p{
  font-size: 13px;
}
.row-text{
  margin: 10px auto;
  color: #ED3F14;
  font-size: 30px;
  text-align: center;
}
.span-text{
  padding-left: 20px;
  color: black;
  font-size: 20px;
}
.top-text{
	  background: #fff8f0;
    width: 100%;
    padding-top: 10px;
    padding-bottom: 10px;
    text-align: center;
    color: #999
	}
  .btn{
    background-color: #4ea0f1;
    border: solid 1px #4ea0f1;
  }
  .logo-img{
    width: 40px;
    height: 40px;
    line-height: 72px;
    /* display: table-cell; */
    text-align: center;
    vertical-align: middle;

  }
  .ptext{
    font-size: 15px;
    color: #04182D;
    float: right;
    /* margin: 10px 20px 30px 10px; */
  }
  .ptext1{
    font-size: 15px;
    color: white;
    float: left;
  }

.ptext1 a:hover {
  color: #d9534f;
}
.ptext1 a {
  color: #04182D;
}
.ptext a:hover {
  color: #d9534f;
}
.ptext a {
  color: #04182D;
}
</style>
