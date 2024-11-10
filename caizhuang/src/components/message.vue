<template>
  <div>
    <div class="freeback-container">
      <div class="freeback-img-box">
        <img src="static/img/freeback.png">
      </div>
      <div class="freeback-box-border">
        <div class="freeback-box">
          <div class="freeback-title">
            <h1>公告</h1>
            <h2>我们会积极改善，做出更好的服务的</h2>
          </div>
          <div class="freeback-content">
            <div class="freeback-form">
              <Form :model="formItem" :label-width="80">
                <FormItem label="公告标题">
                  <textarea  v-model="formItem.title" ></textarea>
                </FormItem>
                <FormItem label="公告信息">
                  <i-input v-model="formItem.content" type="textarea" :autosize="{minRows: 8,maxRows: 10}" placeholder="请输入反馈信息"></i-input>
                </FormItem>
              </Form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {sendServer} from "../utils/common";

  export default {
  name: 'Freeback',
  data () {
    return {
      formItem: {
        title: '',
        content: ''
      }
    };
  },
    created(){
      this.showAnnouncement()
    },
    methods:{
      showAnnouncement(){
        let params={},send={}
        params.send=send;
        params.url="announcement/showAnnouncement";
        sendServer(params,(commRes)=>{
          console.log(commRes);
          this.formItem.title=commRes.extend.announcement.title;
          this.formItem.content=commRes.extend.announcement.content;
          console.log(this.formItem.title);
      })
    }
      }

};
</script>

<style scoped>
.freeback-container {
  margin: 15px auto;
  width: 80%;
  height: 600px;
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
  width: 480px;
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
