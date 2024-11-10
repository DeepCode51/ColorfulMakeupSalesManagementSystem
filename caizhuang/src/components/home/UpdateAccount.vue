<template>
  <div>
     <div class="top-title"> 您的个人资料</div>
     <div class="acc-p">
       <p>亲爱的{{userInfo.userName}}，填写真实的资料，有助于好友找到你哦。</p>
        <div class="center-box">
          会员名： {{userInfo.userName}}
        </div>
      
        <!-- <div class="center-box">
          昵称： <Input v-model="userInfo.realName" placeholder="请输入用户昵称" clearable style="width: 200px" />
        </div>
        <div class="center-box">
          性别 ：
          <RadioGroup v-model="userInfo.gender">
            <Radio label="男"></Radio>
            <Radio label="女"></Radio>
        </RadioGroup>
        </div>
         <div class="center-box">
           
          手机号： <Input prop="phone" v-model="userInfo.mobile" placeholder="请输入手机号" clearable style="width: 200px" @on-change="change"/>
        </div>

        <div v-if="!userInfo.passwordQuestion" class="center-box">
          密保问题： <Input v-model="userInfo.passwordQuestion" placeholder="请输入密保问题" clearable style="width: 200px" />
        </div>
         <div v-if="!userInfo.passwordHintAnswer" class="center-box">
          密保答案： <Input v-model="userInfo.passwordHintAnswer" placeholder="请输入密保答案" clearable style="width: 200px" />
        </div> -->
        <div class="center-box">
          <Form ref="formCustom" :model="formCustom" :rules="ruleCustom" :label-width="80"  label-position="left"class="form">
            <FormItem label="昵称:" prop="realName">
                <Input  v-model="formCustom.realName" placeholder="请输入用户昵称"></Input>
            </FormItem>
            <FormItem label="性别：">
                <RadioGroup v-model="formCustom.gender">
                  <Radio :label='1'>男</Radio>
                  <Radio :label='0'>女</Radio>
              </RadioGroup>
            </FormItem>
            <FormItem label="手机号：" prop="phone" >
                <Input v-model="formCustom.phone" contenteditable=""placeholder="请输入手机号" ></Input>
            </FormItem>

            <div v-if="!userInfo.passwordQuestion" >
            
               <FormItem label="密保问题：" prop="passwordQuestion" >
                <Input v-model="formCustom.passwordQuestion" contenteditable=""placeholder="请输入密保问题" ></Input>
              </FormItem>
              <FormItem label="密保答案：" prop="passwordHintAnswer" >
                <Input v-model="formCustom.passwordHintAnswer" contenteditable=""placeholder="请输入密保答案" ></Input>
              </FormItem>

            </div>
         
        </Form>
        </div>
     </div>
    
      <hr/>
     <div class="btn">
         <Button type="warning" @click="handleSubmit('formCustom')" >保存</Button>
         <Button type="primary" @click="handleReset('formCustom')">重置</Button>
     </div>
   

  <!-- <div v-if="!userInfo.passwordQuestion" >
     <div class="top-title"> 您的密保</div>
     <div class="acc-p">
        <p>您还未填写密保问题，请尽快填写</p>
        <div class="center-box">
          密保问题： <Input v-model="userInfo.passwordQuestion" placeholder="请输入密保问题" clearable style="width: 200px" />
        </div>
          <div class="center-box">
          密保答案： <Input v-model="userInfo.passwordHintAnswer" placeholder="请输入密保答案" clearable style="width: 200px" />
        </div>

         <div class="btn1">
          <Button type="warning" @click="handleSubmit('formCustom')"  >保存</Button>
         </div>
     </div>
  </div> -->
     
  </div>

</template>

<script>
import { sendServer } from '../../utils/common';
export default {
  data(){
    const validateName = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请输入用户昵称'));
        }else{
          callback()
        }
    };
     const validateQuestion = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请输入密码问题'));
        }else{
          callback()
        }
    };
     const validateAns = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请输入密码答案'));
        }else{
          callback()
        }
    };
    const validatePhone = (rule, value, callback) => {
      let myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (value === '') {
            callback(new Error('请输入11位手机号'));
        }else if(!myreg.test(value)){
            callback(new Error('手机号格式错误'));
        }else{
            callback()
        }
        
        
    };
    return{
      userInfo:JSON.parse(localStorage.getItem('userdata')),
      formCustom:{
        realName:'',
        gender:'',
        phone:'',
        passwordQuestion:'',
        passwordHintAnswer:'',
        
      },
      ruleCustom: {
          realName: [
              { validator: validateName, trigger: 'blur' }
          ],
          phone: [
              { validator: validatePhone, trigger: 'blur' }
          ],
          passwordQuestion:[
            { validator: validateQuestion, trigger: 'blur' }
          ],
          passwordHintAnswer:[
            { validator: validateAns,trigger: 'blur'}
          ]

      },
    }
  },
  methods:{
   
    handleSubmit (name) {
        this.$refs[name].validate((valid) => {
            if (valid) {
              console.log(12123143)
              let params={},send={};
              let formCustom=this.formCustom;
              send.id=this.userInfo.id;
              if(this.userInfo.passwordQuestion){
                send.passwordQuestion=this.userInfo.passwordQuestion
                send.passwordHintAnswer=this.userInfo.passwordHintAnswer
              }else{
                send.passwordQuestion=formCustom.passwordQuestion
                send.passwordHintAnswer=formCustom.passwordHintAnswer
              }
              send.realName=formCustom.realName;
              send.gender=formCustom.gender;
              send.mobile=formCustom.phone;
              params.send=send;
            
              params.url="customer/updateInfo";
              sendServer(params,(commRes)=>{
               
                if(commRes.code==100){
                  this.userInfo.realName=formCustom.realName;
                  this.userInfo.gender=formCustom.gender;
                  if(!this.userInfo.passwordQuestion){
                    this.userInfo.passwordQuestion=formCustom.passwordQuestion;
                    this.userInfo.passwordHintAnswer=formCustom.passwordHintAnswer;
                  }
                  localStorage.setItem('userdata',JSON.stringify(this.userInfo))
                  this.$Message.success('保存成功!');
                }else{
                  this.$Message.error('网络超时，请重试！');
                }
              })   
            } else {
                this.$Message.error('表单不能为空!');
            }
        })
    },
    handleReset (name) {
        this.$refs[name].resetFields();
    }
    
  },
  mounted(){
    this.formCustom.realName=this.userInfo.realName;
    this.formCustom.gender=this.userInfo.gender;
    this.formCustom.phone=this.userInfo.mobile;
  }

}

</script>


<style scoped>
.btn1{
  margin-top: 30px;
  margin-left: 50px;

}
.form{
  width: 400px;
}
.ivu-form .ivu-form-item-label {
    /* text-align: right; */
    vertical-align: middle;
    float: left;
    font-size: 12px;
    color: #495060;
    line-height: 1;
    padding: 10px 12px 10px 0;
    box-sizing: border-box;
}

.top-title{
  margin-top: 10px;
  margin-left: 20px;
  font-size: 14px;
  font-weight: 600;
  margin-bottom: 10px;
}
.acc-p{
  margin-top: 10px;
  margin-left: 30px;
  font-size: 12px;
  font-weight: 600;
  margin-bottom: 10px;
}
.center-box{
  margin-top: 30px;
  margin-left: 20px;

}
.btn{
  margin-top: 30px;
  margin-left: 80px;
}
</style>