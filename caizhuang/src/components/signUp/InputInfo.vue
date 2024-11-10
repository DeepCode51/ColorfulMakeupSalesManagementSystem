<template>
  <div class="info-form">
     <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80" >
        <FormItem label="用户名" prop="name">
            <i-input v-model="formValidate.name" clearable size="large" placeholder="请输入你的姓名"></i-input>
        </FormItem>
        <FormItem label="昵称" prop="nickname">
            <i-input v-model="formValidate.nickname" clearable size="large" placeholder="请输入你的姓名"></i-input>
        </FormItem>
        <!-- <FormItem label="邮箱" prop="mail">
            <i-input v-model="formValidate.mail" clearable size="large" placeholder="请输入你的邮箱"></i-input>
        </FormItem> -->
        <FormItem label="密码" prop="password">
            <i-input type="password" v-model="formValidate.password" clearable size="large" placeholder="请输入你的密码"></i-input>
        </FormItem>
        <FormItem label="确认密码" prop="repassword">
            <i-input type="password" v-model="formValidate.repassword" clearable size="large" placeholder="请再次输入你的密码"></i-input>
        </FormItem>
        <!-- <FormItem label="密保问题" prop="Pwdquestion">
            <i-input v-model="formValidate.Pwdquestion" clearable size="large" placeholder="请输入你密码问题"></i-input>
        </FormItem>
        <FormItem label="密保答案" prop="Pwdanswer">
            <i-input v-model="formValidate.Pwdanswer" clearable size="large" placeholder="请输入你的密保答案"></i-input>
        </FormItem> -->
        <!-- <Button type="primary" class="btn" size="large" long @click="handleSubmit('formValidate')">注册</Button> -->
        <Button type="primary" class="btn" size="large" long @click="onRegister('formValidate')">注册</Button>
    </Form>
  </div>
</template>

<script>
import store from '@/vuex/store';
import { mapMutations, mapActions } from 'vuex';
import { sendServer} from '@/utils/common';
export default {
  name: 'InputInfo',
  data () {
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.formValidate.password) {
        callback(new Error('两次输入的密码不一样'));
      } else {
        callback();
      }
    };
    const hasUserName = function(rule, value, callback) {
      if (value) {
          let params={}
          let send={}
          params.url="customer/count";
          send.userName=value
          console.log(value)
          params.send=send;
          sendServer(params,(commRes)=>{
              console.log(commRes)
              if(commRes.code==100){
                  callback()
              }else{
                  callback(new Error('该用户名已存在'))
              }
          });
      }else{
          return callback()
      }
    };
    return {
      formValidate: {
        name: '',
        password: '',
        repassword: '',
        Pwdquestion:'',
        Pwdanswer:'',
        nickname:''
      },
      ruleValidate: {
        name: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { validator: hasUserName, trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码长度不能小于6', trigger: 'blur' }
        ],
        repassword: [
          { validator: validatePassCheck, trigger: 'blur' }
        ]
      }
    };
  },
  mounted () {
    let phone=this.$route.query.phone
    // console.log(phone)取得到
  },
  methods: {
    ...mapMutations(['SET_SIGN_UP_SETP']),
    ...mapActions(['addSignUpUser']),

    onRegister(name){
      var vm=this;
      this.$refs[name].validate((valid)=>{
        if(valid){
          let params={},send={}
          let time=new Date().getTime()
          send.createTime=time;
          send.userName=this.formValidate.name;
          send.mobile=this.$route.query.phone;
          send.realName=this.formValidate.nickname;
          send.password=this.formValidate.password;
          send.passwordQuestion=this.formValidate.Pwdquestion
          send.passwordHintAnswer=this.formValidate.Pwdanswer
          console.log(send)
          params.send=send;
          params.url="customer/insertUser"
          sendServer(params,(commRes)=>{
            console.log(commRes)
            if(commRes.code==100){
               vm.SET_SIGN_UP_SETP(2);
               vm.$router.push({ path: '/SignUp/signUpDone' });
            }else{
              this.$Message.error('注册失败');
            }
          })
        }
      })

    }
  },
  // store
};
</script>

<style scoped>
.info-form {
  width: 90% !important;
}
.btn{
  background: #4ea0f1;
  border: 1px solid #4ea0f1;

}
</style>
