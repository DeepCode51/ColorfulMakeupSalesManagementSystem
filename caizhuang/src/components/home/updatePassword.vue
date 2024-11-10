<template>
  <div>
    <div class="top-title"> 修改密码</div>
    <div class="acc-p">
        <p>设置一个包含数字和字母，长度超过6位以上的密码</p>
         <div class="center-box">
          <Form ref="formCustom" :model="formCustom" :rules="ruleCustom" :label-width="80"  label-position="left"class="form">
            <FormItem label="旧密码:" prop="oldPsw" >
                <Input  v-model="formCustom.oldPsw" placeholder="请输入旧密码" type="password"></Input>
            </FormItem>
            <FormItem label="新密码："prop="newPsw">
                <Input  v-model="formCustom.newPsw" placeholder="请输入新密码" type="password"></Input>
            </FormItem>
            <FormItem label="确认密码:" prop="checkPsw" >
                <Input v-model="formCustom.checkPsw" placeholder="请再次输入密码" type="password"></Input>
            </FormItem>
        </Form>
        </div>
        <hr/>

        <div class="btn">
         <Button type="warning" @click="handleSubmit('formCustom')" >保存</Button>
         <Button type="primary" @click="handleReset('formCustom')">重置</Button>

        
        </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import { sendServer } from '../../utils/common';
export default {
    data(){
        const validateOldPsw = (rule, value, callback) => {
            if (value === ''|| value.length<6) {
                callback(new Error('密码不能为空，且密码不能少于6位'));
            }else{
                callback()
            }
        };
        const validateNewPsw = (rule, value, callback) => {
            if (value === '' || value.length<6 ) {
                callback(new Error('密码不能为空，且密码不能少于6位'));
            } else {
                if (this.formCustom.checkPsw !== '') {
                    // 对第二个密码框单独验证
                    this.$refs.formCustom.validateField('checkPsw');
                }
                callback();
            }
        };
        
        const validatePassCheck = (rule, value, callback) => {
            if (value === '' || value.length<6 ) {
                callback(new Error('密码不能为空，且密码不能少于6位'));
            } else if (value !== this.formCustom.newPsw) {
                callback(new Error('两次输入的密码不一致！！'));
            } else {
                callback();
            }
        };
        
        return{
            userInfo:JSON.parse(localStorage.getItem('userdata')),
            formCustom:{
                oldPsw:'',
                newPsw:'',
                checkPsw:'',
            },
            ruleCustom: {
                oldPsw: [
                    { validator: validateOldPsw, trigger: 'blur' }
                ],
                newPsw: [
                    { validator: validateNewPsw, trigger: 'blur' }
                ],
                checkPsw:[
                    { validator: validatePassCheck, trigger: 'blur' }
                ],
                
            },
        }
    },
    methods:{
        ...mapActions(['signOut']),
        handleSubmit (name) {
            this.$refs[name].validate((valid) => {
                if (valid) {
                    console.log(123)
                    let params={},send={};
                    let formCustom=this.formCustom;
                    send.id=this.userInfo.id;
                    send.oldPsw=formCustom.oldPsw;
                    send.password=formCustom.newPsw;
                    params.send=send;
                    console.log(params)
                    params.url="customer/updatePsw";
                    sendServer(params,(commRes)=>{
                        if(commRes.code==100){
                            this.userInfo.password=formCustom.newPsw;
                            localStorage.setItem('userdata',JSON.stringify(this.userInfo))
                            this.$Message.success('修改成功!请重新登录');
                            this.logout()
                        }else{
                            this.$Message.error('旧密码不对，请重新输入');
                        }
                    })   
                } else {
                    this.$Message.error('表单不能为空!');
                }
            })
        },
        handleReset (name) {
            this.$refs[name].resetFields();
        },
        logout(){
            this.signOut()
            this.$router.push('/');
        }
        
    }

}
</script>

<style scoped>

.top-title{
  margin-top: 10px;
  margin-left: 20px;
  font-size: 14px;
  font-weight: 600;
  margin-bottom: 10px;
}
.form{
  width: 400px;
}
.btn{
  margin-top: 30px;
  margin-left: 80px;
}
.acc-p{
  margin-top: 10px;
  margin-left: 30px;
  font-size: 12px;
  font-weight: 600;
  margin-bottom: 10px;
}
.center-box{
    margin-top: 20px;
}

</style>