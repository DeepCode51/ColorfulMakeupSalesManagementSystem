<template>
  <div>
    <div class="freeback-container">
      <div class="freeback-box-border">
        <div class="freeback-box">
          <div class="freeback-title">
            <h1>公告</h1>
            <h2>我们会积极改善，做出更好的服务的</h2>
          </div>
          <div class="freeback-content">
            <div class="freeback-form">
              <el-form :model="formItem" :label-width="150">
                <el-form-item label="公告标题">
                  <el-input v-model="formItem.title" placeholder="请输入标题"></el-input>
                </el-form-item>

                <el-form-item label="公告信息">
                  <el-input v-model="formItem.content" type="textarea" :autosize="{minRows: 16,maxRows: 15}" placeholder="请输入公告内容"></el-input>
                </el-form-item>
                <Form_item>
                  <el-button @click="addAnnouncement">提交</el-button>
                  <el-button>清空信息</el-button>
                </Form_item>
              </el-form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import { sendServer } from '@/utils/common';
export default {
  name: 'note',
  data () {
    return {
      formItem: {
        title: '',
        content: ''
      }
    };
  },
  methods:{
    addAnnouncement(){
      let params={},send={}
      send.title = this.formItem.title
      send.content = this.formItem.content
      params.send = send
      params.url = "announcement/addAnnouncement"
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          this.$message({
            type: 'success',
            message: '发布成功!'
          })
          console.log(commRes)
    }
  })

}
  }
}
</script>

<style scoped>
.freeback-container {
  margin: 15px auto;
  width: 80%;
  height: 720px;
  display: flex;
  align-items: center;
  /* background-color: #ccc; */
}
.freeback-img-box {
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.freeback-img-box img {
  width: 80%;
}
.freeback-box-border {
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.freeback-box {
  width: 680px;
}
.freeback-content {
  margin: 15px auto;
  border: 1px #ccc dotted;
}
.freeback-form {
  margin: 30px auto;
  width: 90%;
}
</style>
