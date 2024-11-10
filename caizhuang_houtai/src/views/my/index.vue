<template>
    <div>
        <div class="left">

            <el-card class="box-card " header="个人信息" style="width:100%">
                <div style="overflow:hidden;text-align:center">
                    <el-upload class="avatar-uploader" ref='upload' :auto-upload='false' :multiple='false'
                        :limit="1" accept="image/jpeg,image/gif,image/png" action='' :show-file-list='false'  :on-change='upload'
                          >
                        <img v-if="imageUrl" :src="imageUrl" title="点击修改头像" class="avatar img">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>

                        <!-- :on-change='upload'   ：enctype="multipart/form-data"  :before-upload="beforeAvatarUpload"-->
                    </el-upload>
                </div>
                <div class="left-item"><svg-icon icon-class="user"></svg-icon> 用户昵称<span class="left-span">{{data.nickName}}</span></div>
                <div class="left-item"><svg-icon icon-class="phone"></svg-icon> 手机号码<span class="left-span">{{data.phone}}</span></div>

                <div class="left-item" v-if="data.role"><svg-icon icon-class="peoples"></svg-icon> 所属角色<span class="left-span">{{data.role.roleName}}</span></div>
                <div class="left-item"><svg-icon icon-class="date"></svg-icon> 创建日期<span class="left-span">{{data.createTime}}</span></div>
            </el-card>
        </div>
        <div class="right">
            <el-card class="box-card" header="基本资料" style="width:100%">
                <el-tabs v-model="activeName">
                    <el-tab-pane label="基本资料" name="基本资料">
                        <el-form :model="dataForm" ref="dataForm" :rules="rules" label-width="100px" class="demo-ruleForm" style="overflow:hidden">
                            <el-form-item label="用户昵称" prop="nickName" >
                                <el-input v-model="dataForm.nickName" ></el-input>
                            </el-form-item>
                            <el-form-item label="手机号码" prop="phone" >
                                <el-input v-model="dataForm.phone" ></el-input>
                            </el-form-item>
                            <el-form-item label="性别" prop="gender" >
                                <el-select v-model="dataForm.gender" placeholder="请选择" style="width:100%">
                                    <el-option
                                    v-for="item in genderList"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item >
                                <el-button type="primary" size="small" @click="update()">保存</el-button>
                                <el-button size="small" @click="reset()">重置</el-button>
                            </el-form-item>
                        </el-form>
                    </el-tab-pane>
                    <el-tab-pane label="修改密码" name="修改密码">
                        <el-form :model="psdForm" ref="psdForm" :rules="psdrules" label-width="100px" class="demo-ruleForm" style="overflow:hidden">
                            <el-form-item label="旧密码" prop="oldpsd" >
                                <el-input v-model="psdForm.oldpsd" show-password clearable></el-input>
                            </el-form-item>
                            <el-form-item label="新密码" prop="newpsd1" >
                                <el-input v-model="psdForm.newpsd1" show-password clearable></el-input>
                            </el-form-item>
                            <el-form-item label="确认密码" prop="newpsd2" >
                                <el-input v-model="psdForm.newpsd2" show-password clearable></el-input>
                            </el-form-item>
                            <el-form-item >
                                <el-button type="primary" size="small" @click="uptpsd()">保存</el-button>
                            </el-form-item>
                        </el-form>
                    </el-tab-pane>
                </el-tabs>
            </el-card>
        </div>
    </div>
</template>
<script>
import {sendServer} from '../../utils/common';
import {isPhone} from '../../utils/util';
import Cookies from "js-cookie";
import Axios from 'axios';
import index from '@/components/Breadcrumb/index.vue';
export default {
  components: { index },
    data () {
        const validatePassword = (rule, value, callback) => {
            if (value.length < 3) {
                callback(new Error('密码不能为空，且不少三位数'))
            } else {
                callback()
            }
        }
        return {

            userInfo:JSON.parse(localStorage.getItem("userdata")),
            imageUrl:' ',
            userId: null,
            data : "",
            img:'',
            activeName:'基本资料',
            dataForm:{
                nickName:'',
                phone:'',
                gender:''
            },
            psdForm:{
                oldpsd:'',
                newpsd1:'',
                newpsd2:''
            },
            genderList: [
                {value: 0,label: '女'},
                {value: 1,label: '男'}
            ],
            rules: {
                nickName: [
                    { required: true, message: '请输入用户昵称', trigger: 'blur' }
                ],
                phone: [
                    {required: true, message: '请输入11位手机号', trigger: 'blur'},
                    { validator: isPhone, trigger: 'blur' }
                ],
                gender: [
                    { required: true, message: '请选择性别', trigger: 'blur' }
                ]
            },
            psdrules:{
                oldpsd: [
                    { required: true, message: '请输入旧密码', trigger: 'blur' }
                ],
                newpsd1: [
                    { required: true, message: '请输入新密码', trigger: 'blur' },
                    { required: true, trigger: 'blur', validator: validatePassword }

                ],
                newpsd2: [
                    { required: true, message: '请输入新密码', trigger: 'blur' },
                    { validator: this.verifypsd, trigger: 'blur' }
                ],
            }
        }
    },
    mounted () {
        this.userId = JSON.parse(localStorage.getItem("userdata")).userId
        this.getUserInfo()
        console.log(this.imageUrl)
    },
    created(){
        console.log(this.userInfo)
    },

    methods: {



        upload(file,fileList){
            var vm=this;
            console.log(file)
            // console.log(fileList)
            this.file = file.raw;
            this.fileName = file.name;
            console.log(this.file,this.fileName)
            let formData = new FormData();
            formData.append('file', this.file);

            let urlParams={},send={}
            urlParams.url="user/upload";
            // send.file=formData,
            urlParams.send=formData;
            sendServer(urlParams,(commRes)=>{
                console.log(commRes)
                if(commRes.code==100){
                     vm.$message({
                        message: "上传成功",
                        type: 'success'
                    });
                    vm.imageUrl = 'http://localhost:8089/img/'+commRes.extend.imgData

                    this.updateImg(commRes.extend.imgData)
                    this.userInfo.img=commRes.extend.imgData;
                    localStorage.setItem('userdata',JSON.stringify(this.userInfo))

                }
            })

        },
        updateImg(imgData){
            let params={},send={}
            send.userId=this.userId;
            send.img=imgData;
            params.send=send;
            params.url="user/updateImg"
             sendServer(params,(commRes)=>{
                 console.log(commRes)
             })
        },

        /**
         * 获取用户详细信息
         * @method details
         */

        getUserInfo(){
            let urlParams = {},send = {}
            urlParams.url = 'user/getUserInfo'
            send.userId = this.userId
            urlParams.send = send

            sendServer(urlParams,(commRes)=>{
                console.log(commRes)
                if(commRes.code==100){
                    this.data=commRes.extend.user
                    this.dataForm.nickName = commRes.extend.user.nickName
                    this.dataForm.phone = commRes.extend.user.phone
                    this.dataForm.gender = commRes.extend.user.gender
                    this.imageUrl = 'http://127.0.0.1:8089/img/'+commRes.extend.user.img
                }
            })
        },
        /**
         * 重置用户基本信息
         * @method reset
         */
        reset(){
            this.dataForm.nickName = ""
            this.dataForm.phone = ""
            this.dataForm.gender = null
        },
        /**
         * 修改用户基本信息
         * @method update
         */
        update(){
            var vm=this;
            this.$refs.dataForm.validate((valid) => {
                if (valid) {
                    let Params = {},send = {}
                    Params.url = 'user/updateInfo'
                    send.userId = this.userId
                    send.nickName = this.dataForm.nickName
                    send.phone = this.dataForm.phone
                    send.gender = this.dataForm.gender
                    Params.send = send
                    sendServer(Params,(commRes)=>{
                        console.log(commRes)
                        if(commRes.code==100){
                             vm.$message({
                                message: "修改成功",
                                type: 'success'
                             });
                             vm.getUserInfo()
                        }
                    })
                }else{
                    console.log('error submit!!');
                    return false;
                }
            })
        },
        /**
         * 验证两次输入的新密码相不相同
         * @method verifypsd
         */
        verifypsd(rule, value, callback){
            if (this.psdForm.newpsd1===this.psdForm.newpsd2) {
                return callback()
            }else{
                return callback(new Error('两次密码不一致'))
            }
        },
        /**
         * 修改密码
         * @method uptpsd
         */
        uptpsd(){
            this.$refs.psdForm.validate((valid) => {
                if (valid) {
                    let Params = {},
                        send = {}
                    Params.url = 'user/updatePsw'
                    send.userId = this.userId
                    send.oldPsw = this.psdForm.oldpsd
                    send.password = this.psdForm.newpsd2
                    Params.send = send
                    console.log(send)
                    sendServer(Params,(commRes)=>{
                        console.log(commRes)
                        if(commRes.code==100){
                            this.$message({
                                message: "修改成功，请重新登录",
                                type: 'success'
                            });
                            this.logout()
                        }else{
                             this.$message({
                                message: "您输入的旧密码不对，请重新输入",
                                type: 'error'
                            });
                        }
                    })
                }else{
                    console.log('error submit!!');
                    return false;
                }
            })
        },
        /**
         * 登出，清除token
         * @method logout
         */
        logout() {
            Cookies.remove('token');
            window.localStorage.clear()
            localStorage.removeItem('userdata')
            this.$router.push(`/login?redirect=${this.$route.fullPath}`)
            // let Params = {},
            //     send = {}
            // Params.url = '/f/logout'
            // Params.send = send
            // sendServer(Params,this).then(
            //     (res)=>{
            //     Cookies.remove('token');
            //     window.localStorage.clear()
            //     localStorage.removeItem('rou')
            //     localStorage.removeItem('userdata')
            //     localStorage.removeItem('jurisdiction')
            //     this.$router.push(`/login?redirect=${this.$route.fullPath}`)
            //     },(res)=>{
            //     }
            // )
        }

    }
}
</script>
<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    /* border: 1px solid red; */
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    border: 1px dashed #d9d9d9;
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 150px;
    line-height: 150px;
    text-align: center;
    border-radius: 50%;
  }
  /* .avatar-uploader-icon.el-upload:hover{
      border-color: #409EFF;
  } */
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
<style scoped>

.left{
    float: left;
    width: 40%;
    padding: 20px;
}
.left-item{
    height: 40px;
    line-height: 40px;
    font-size: 13px;
    border-bottom: 1px solid #EBEEF5;
}
.left-span{
    float: right;
}
.right{
    float: left;
    width: 60%;
    padding:20px 20px 20px 0;
}
.img{
    width: 100px;
    height: 100px;
    border-radius: 100px;
    display: block;
    margin: 10px auto 20px;
}

</style>
