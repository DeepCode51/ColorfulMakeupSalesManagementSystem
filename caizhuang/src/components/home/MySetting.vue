<template>
    <div class="setting-box">

      <Layout class="layout">
        <Sider class="side-bar" :style="{ background: '#fff', overflow: 'auto'}" ref="side" :collapsed-width="78">
          <Menu  theme="light" width="auto" @on-select="onSelect">
            <!-- active-name="1-2" :active-name="act_name"    -->
              <div class="user-icon">
                <el-upload
                  class="avatar-uploader"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  ref='upload' :auto-upload='false' :multiple='false'
                  :limit="1" accept="image/jpeg,image/gif,image/png"
                  :on-change='upload'
                  :show-file-list="false"
                  :before-upload="beforeAvatarUpload">
                  <img v-if="userInfo.img" :src="tempfilePath+userInfo.img" title="点击修改头像"  class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
                <p>{{userInfo.userName}}</p>
              </div>
              <div class="menuitem">
                <MenuItem name="myAccount">
                    <Icon type="ios-navigate"></Icon>
                    <span>账号管理</span>
                </MenuItem>
                <MenuItem name="updateAccount">
                    <Icon type="ios-contact-outline" />
                    <span>个人资料</span>
                </MenuItem>
                <MenuItem name="myAddress">
                    <Icon type="ios-home-outline" />
                    <span>收货地址</span>
                </MenuItem>


              </div>
            <!-- <Submenu name="1">
              <template slot="title">
                  <Icon type="location"></Icon>
                  <span>收货地址</span>
              </template>
              <MenuItem name="myAddress">我的收货地址</MenuItem>
              <MenuItem name="addAddress">添加收货地址</MenuItem>
            </Submenu>
            <Submenu name="2">
              <template slot="title">
                  <Icon type="clipboard"></Icon>
                  <span>购物订单</span>
              </template>
              <MenuItem name="myOrder">我的订单</MenuItem>
            </Submenu>
            <Submenu name="3">
              <template slot="title">
                  <Icon type="ios-cart"></Icon>
                  <span>购物车</span>
              </template>
              <MenuItem name="myShoppingCart">我的购物车</MenuItem>
            </Submenu> -->
          </Menu>
        </Sider>
        <Layout class="layout">
          <Header :style="{background: '#fff'}">
            {{activeTitle}}
          </Header>
          <Content class="content">
            <transition mode="out-in">
              <router-view></router-view>
            </transition>
            <!-- <keep-alive>
               <router-view></router-view>
            </keep-alive> -->
          </Content>

          <!-- <Footer class="layout-footer-center">2021 &copy; hanfu.store.com 版权所有</Footer> -->
        </Layout>

      </Layout>

    </div>


     <!-- <div class="layout">
        <Layout>
            <Sider ref="side1" hide-trigger collapsible :collapsed-width="78" v-model="isCollapsed">
                <Menu active-name="1-2" theme="dark" width="auto" :class="menuitemClasses">
                    <MenuItem name="1-1">
                        <Icon type="ios-navigate"></Icon>
                        <span>Option 1</span>
                    </MenuItem>
                    <MenuItem name="1-2">
                        <Icon type="ios-search"></Icon>
                        <span>Option 2</span>
                    </MenuItem>
                    <MenuItem name="1-3">
                        <Icon type="ios-settings"></Icon>
                        <span>Option 3</span>
                    </MenuItem>
                </Menu>
            </Sider>
            <Layout>
                <Header :style="{padding: 0}" class="layout-header-bar">
                    <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{margin: '0 20px'}" type="md-menu" size="24"></Icon>
                </Header>
                <Content :style="{margin: '20px', background: '#fff', minHeight: '260px'}">
                    Content
                </Content>
            </Layout>
        </Layout>
    </div> -->

</template>

<script>
import { sendServer } from '../../utils/common'

export default {
    name:'MySetting',
    data(){
        return{
            tempfilePath:'http://localhost:8089/img/',
            userInfo:'',
            activeTitle: '账号管理',
            // act_name: this.$route.path,
            bar:{
                'updateAccount': '个人资料',
                'myAddress': '收货地址',
                'myAccount':'账号管理',
            }
        }
    },
    watch: {
      // "$route"(){
      //   this.act_name = this.$route.path
      // }
    },
    created(){
        this.userInfo=JSON.parse(localStorage.getItem('userdata'))
    },
    methods: {
      //上传头像时候的判断
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      //上传修改头像
      upload(file,fileList){
        var vm=this;
        console.log(file)
        this.file = file.raw;
        this.fileName = file.name;
        console.log(this.file,this.fileName)
        let formData = new FormData();
        formData.append('file', this.file);

        let urlParams={},send={}
        urlParams.url="user/upload";
        urlParams.send=formData;
        sendServer(urlParams,(commRes)=>{
            console.log(commRes)
            if(commRes.code==100){
                  vm.$message({
                    message: "上传成功",
                    type: 'success'
                });
                vm.userInfo.img = commRes.extend.imgData
                this.updateImg(commRes.extend.imgData)
                localStorage.setItem('userdata',JSON.stringify(vm.userInfo))

            }
        })

      },
        updateImg(imgData){
            let params={},send={}
            send.id=this.userInfo.id;
            send.img=imgData;
            params.send=send;
            params.url="customer/updateImg"
             sendServer(params,(commRes)=>{
                 console.log(commRes)

             })
        },
      //搜索框
      goSearch(){

      },
      onSelect (name) {
        console.log(name)
        this.activeTitle = this.bar[name];
        console.log(  this.bar[name])
        console.log(this.activeTitle)
        this.$router.push(`/home/mySetting/${name}`);
      }
  }
}
</script>
<style>
.menuitem{
  text-align: center;
  height: 100%;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 50%;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 130px;
    height: 130px;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 130px;
    height: 130px;
    line-height: 130px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
.el-upload__input {
    display: none!important;;
}
.ivu-layout-header {
    background: #495060;
    padding: 0 50px;
    height: 30px;
    line-height: 30px;
    font-size: 15px;
    font-weight: 600;

}

.side-bar a{
  color: #232323;
}
.user-icon {
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.user-icon span {
  font-size: 96px;
}
/* .user-img {
  margin-bottom: 15px;
  width: 96px;
  height: 96px;
  border-radius: 48px;
  overflow: hidden;
}
.user-img img{
  width: 100%;
} */

.content {
  margin: 15px;
  background-color: #fff;
  padding: 15px;
}
.layout-footer-center {
  padding: 0px 15px;
  padding-bottom: 15px;
  text-align: center;
}
.setting-box{
    width: 1150px;
    margin:0 auto;
}

</style>
