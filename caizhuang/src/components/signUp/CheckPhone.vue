<template>
  <div class="info-form">
    <Form ref="formValidate" :model="formValidate" :label-width="80" :rules="ruleValidate">

      <FormItem label="手机号" prop="phone">
          <i-input v-model="formValidate.phone" clearable size="large"  placeholder="请输入手机号"></i-input>
      </FormItem>
      <FormItem label="验证码" prop="checkNum">
          <i-input v-model="formValidate.checkNum" size="large"  placeholder="请输入验证码">
            <!-- <Button slot="append" @click="getcheckNum">获取验证码</Button> -->
          </i-input>
      </FormItem>
      <FormItem>
        <div class="code" @click="refreshCode">
          <s-identify :identifyCode="identifyCode"></s-identify >
          <p class="code-text">看不清，换一张</p>
        </div>
         <!-- <Button slot="append" @click="refreshCode">获取验证码</Button> -->
      </FormItem>
      <Button type="primary" class="btn" size="large" long @click="handleSubmit('formValidate')">验证手机号</Button>
    </Form>
  </div>
</template>

<script>
import SIdentify from '../../components/identify'
import store from '@/vuex/store';
import { mapMutations } from 'vuex';
export default {
  components: {
    SIdentify
  },
  name: 'CheckPhone',
  data () {
     const validateCode = (rule, value, callback) => {
      if (value !== this.identifyCode) {
        this.refreshCode();
        callback(new Error('验证码错误'))
      } else {
        callback()
      }
    }
    return {

      identifyCodes: "1234567890",
      identifyCode: "",
      formValidate: {
        phone: '',
        checkNum: ''//验证码
      },
      ruleValidate: {
        phone: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          { type: 'string', pattern: /^1[3|4|5|7|8][0-9]{9}$/, message: '手机号格式出错', trigger: 'blur' }
        ],
        checkNum: [
          { required: true, message: '必须填写验证码', trigger: 'blur' },
          { type: 'string', min: 4, max: 4, message: '验证码长度错误', trigger: 'blur' },
          { trigger: 'blur', validator: validateCode },
        ]
      }
    };
  },
  created(){
    this.refreshCode()
  },
  methods: {
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },
    refreshCode() {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
        ];
      }
      console.log(this.identifyCode);
    },
    mounted () {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },


    ...mapMutations(['SET_SIGN_UP_SETP']),
    // getcheckNum () {
    //   if (this.formValidate.phone.length === 11) {
    //     this.$Message.success({
    //       content: '验证码为: 1234',
    //       duration: 6,
    //       closable: true
    //     });
    //   } else {
    //     this.$Message.error({
    //       content: '请输入正确的手机号',
    //       duration: 6,
    //       closable: true
    //     });
    //   }
    // },
    handleSubmit (name) { // 提交验证
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$router.push({ path: '/SignUp/inputInfo', query: { phone: this.formValidate.phone } });//路由跳转
          this.SET_SIGN_UP_SETP(1);
        } else {
          this.$Message.error({
            content: '请填写正确的信息',
            duration: 6,
            closable: true
          });
        }
      });
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
    background-color: #4ea0f1;
    border: solid 1px #4ea0f1;
  }
.code {
  display: flex;
  flex-direction: row;
}
.code-text{
  padding-left: 8px;
  font-size: 10px;
  color:  #4ea0f1;
}
</style>
