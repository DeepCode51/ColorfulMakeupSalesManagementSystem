<template>
  <div class="my-acc">
      <div class="top-title"> 您的基础信息</div>
      <div class="contenx-acc">
        <div class="user-text">
          会员名：{{userInfo.userName}}
        </div>
        <div class="user-text">
          昵称：{{userInfo.realName}}
        </div>
        <div class="user-text">
          绑定手机：{{userInfo.mobile}}
        </div>

      </div>
      <hr/>
      <div class="top-title"> 您的安全服务</div>
      <div class="center-box">
        <div>
          <!-- <Icon type="md-checkmark-circle" /> -->
          <div v-if="userInfo.userName">已完成</div>
        </div>
        <div>
          <p>身份认证</p>
        </div>
        <div class="text-p">
         <p> 用于提升账号的安全性和信任级别。认证后的有卖家记录的账号不能修改认证信息。</p>
        </div>
        <div>
          <p>ok</p>
        </div>
      </div>

       <div class="center-box">
        <div>
          <div v-if="userInfo.password">已设置</div>
          <div v-if="!userInfo.password" class="no-text">未设置</div>
        </div>
        <div>
          <p>登录密码</p>
        </div>
        <div class="text-p">
         <p> 安全性高的密码可以使账号更安全。建议您定期更换密码，且设置一个包含数字和字母，并长度超过6位以上的密码。</p>
        </div>
        <div>
          <router-link to="/home/mySetting/updatePassword" class="no-caolor"><p v-if="userInfo.password" class="a">修改</p></router-link>
          <p v-if="!userInfo.password" class="a">设置</p>
        </div>
      </div>

<!--       <div class="center-box">-->
<!--        <div>-->
<!--          <div v-if="userInfo.passwordQuestion">已设置</div>-->
<!--          <div v-if="!userInfo.passwordQuestion" class="no-text">未设置</div>-->
<!--        </div>-->
<!--        <div>-->
<!--          <p>密保问题</p>-->
<!--        </div>-->
<!--        <div class="text-p">-->
<!--         <p> 是您找回登录密码的方式之一。建议您设置一个容易记住，且最不容易被他人获取的问题及答案，更有效保障您的密码安全。</p>-->
<!--        </div>-->
<!--        <div>-->
<!--          <p v-if="userInfo.passwordQuestion">ok</p>-->
<!--          <router-link to="/home/mySetting/updateAccount" class="no-caolor"><p v-if="!userInfo.passwordQuestion" class="a">设置</p></router-link>-->
<!--        </div>-->
<!--      </div>-->

       <div class="center-box">
        <div>
          <div v-if="userInfo.userName">已绑定</div>
        </div>
        <div>绑定手机</p>
        </div>
        <div class="text-p">
         <p>绑定手机后，您即可享受缤色彩妆丰富的手机服务，帮助你更好的在这个平台购物。</p>
        </div>
        <div>
          <router-link to="/home/mySetting/updateAccount" class="no-caolor"><p class="a">修改</p></router-link>
        </div>
      </div>

  </div>
</template>

<script>
export default {
  data(){
    return{
      userInfo:JSON.parse(localStorage.getItem('userdata')),
    }
  },
  created(){
    console.log(this.userInfo)
  }

}
</script>

<style scoped>
.no-caolor{
  color: black;
}
.no-text{
  color: red;
}
.top-title{
  margin-top: 10px;
  margin-left: 20px;
  font-size: 14px;
  font-weight: 600;
  margin-bottom: 10px;
}
.contenx-acc{
  /* border: 1px solid red; */
  margin-left: 40px;

}
.user-text{
  margin-bottom: 10px;
}
.center-box{
  margin-left: 40px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 700px;
  margin-bottom: 40px;
}
.text-p{
  width: 300px;
  word-wrap:break-word
}
.center-box .a:hover{
  color: rgb(214, 35, 35);
}

</style>
