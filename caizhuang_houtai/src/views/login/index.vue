<template>
  <div class="login-container">
    <div class="title-box">
      <div class="text-top">缤色彩妆商家管理</div>
    </div>
    <div class="bg">
       <img :src="imgSrc" width="100%" height="100%" alt="" />
    </div>
   <div class="center-box">
    <el-card class="card">

      <el-form ref="loginForm" :model="loginForm" :rules="loginRules"  auto-complete="on" label-position="left">
        <div class="title-container">
          <h3 class="title">缤色彩妆后台管理系统</h3>
        </div>

        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            ref="username"
            v-model="loginForm.username"
            placeholder="请输入用户名"
            name="username"
            type="text"
            tabindex="1"
            auto-complete="on"
            clearable

          />
        </el-form-item>

        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="请输入密码"
            name="password"
            tabindex="2"
            auto-complete="on"
            clearable
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>

        <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>


      </el-form>
    </el-card>
   </div>




  </div>

</template>

<script>
import { validatAlphabets } from '@/utils/validate'
import Cookies from "js-cookie";
import { getToken, setToken, removeToken } from '@/utils/auth'
export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validatAlphabets(value)) {
        callback(new Error('请输入正确的用户名(只限英文字符)'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error('密码不能少于三位数'))
      } else {
        callback()
      }
    }
    return {
      imgSrc:require('../../assets/bg3.jpg'),
      loginForm: {
        username: 'admin',
        password: '123'
      },
      loginRules: {
        username: [
          { trigger: 'blur', validator: validateUsername },
          { required: true, trigger: 'blur', message: "用户名不能为空" }
          ],
        password: [
          { trigger: 'blur', validator: validatePassword },
          { required: true, trigger: 'blur', message: "密码不能为空" }
          ]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  mounted(){

  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          var vm=this;
          this.axios({
            method:'GET',
            url:'http://localhost:8089/hf/user/login?userName='+vm.loginForm.username+'&password='+vm.loginForm.password
          }).then(function(res){
            console.log(res.data)
            vm.loading=false;
            if(res.data.code==100){
              vm.$message('登录成功');
              // console.log(res.data.extend.token)
              // let seconds = 10;
              // let expires = new Date(new Date() * 1 + seconds * 1000);
              // Cookies.set('token', res.data.extend.user.token, { expires: expires }); // 10 秒后失效
              // let token=Cookies.get('token')
              // console.log(token)

              Cookies.set("token",res.data.extend.user.token)
              localStorage.setItem('userdata',JSON.stringify(res.data.extend.user))
              // vm.$router.push({ path: vm.redirect || '/' })
              // vm.$store.commit('user/SET_NAME', res.data.extend.user.userName)
              vm.$router.push({ path:'/'})

            }else{
               vm.$message({
                  showClose: true,
                  message: '用户名或密码不正确',
                  type: 'error'
               });
            }
          });

        } else {
          this.$message({
                showClose: true,
                message: '用户名或密码不能为空',
                type: 'error'
          });
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
// $bg:#2d3a4b;
$bg:#fff;
$dark_gray:#889aa4;
$light_gray:#eee;
// $bg-img:url('~@/assets/bg.jpg')no-repeat center;


.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 420px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 20px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .title-box{
    // border: solid 1px red;
    width: 100%;
    height: 80px;
    font-size: 23px;
    // font-family: "宋体";
    color: #99150c;
    line-height: 80px;
    font-weight:bold
  }
  .text-top{
    margin: 0 20%;
  }
  .center-box{

     z-index: 1;
     width: 100%;
    height: 420px;;
    position: relative;
  }
  .bg{
    width:100%;
    height:500px;  /**宽高100%是为了图片铺满屏幕 */
    position: absolute;
 }
 .box-card {
    width: 480px;
  }
  .foot{
	  // background: #fff8f0;
    width: 100%;
    padding-top: 15px;
    padding-bottom: 10px;
    text-align: center;
    color: #999
	}
  .foot p{
    font-size: 13px;
  }
  .card{
    width: 400px;
    margin:80px auto;
    // margin-left:calc(calc(100vw-420px)/2);
    background-color: rgba(200,200,200,0.5);
    border-color: rgba(200,200,200,0.5);
    border-radius: 10px;
  }
  .el-button--primary {
    color: #FFF;
    background-color: rgb(129, 9, 9);
    border-color: rgb(129, 9, 9);
 }
}
</style>
