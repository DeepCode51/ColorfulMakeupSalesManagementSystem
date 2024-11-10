<template>
    <div class="app-container">

  <el-form ref="form" :model="product" label-width="150px">

      <el-form-item label="商品名称">
          <el-input v-model="product.goodsname" />
      </el-form-item>
    <el-form-item label="商品类别">
      <el-select v-model="product.goodstypeId" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.goodstypename"
          :value="item.id">
        </el-option>
      </el-select>

      <el-button @click="addType" style="margin:0px 0px 0px 20px">新增分类</el-button>


    </el-form-item>
    <el-form-item label="商品价格">
        <el-input v-model="product.price" />
    </el-form-item>
    <div class="choseBox">
      <el-form-item label="是否折扣">
      <el-switch
        v-model="product.discount"
        :active-value="1"
        :inactive-value="0"
        active-color="#13ce66"
        inactive-color="#ff4949">
      </el-switch>
    </el-form-item>

     <el-form-item label="是否上架">
      <el-switch
        v-model="product.onsale"
        :active-value="1"
        :inactive-value="0"
        active-color="#13ce66"
        inactive-color="#ff4949">
      </el-switch>
    </el-form-item>

    </div>


    <el-form-item  label="折扣价" v-if="product.discount==1">
        <el-input v-model="product.sellPrice" />
    </el-form-item>


    <el-form-item label="商品产地">
        <el-input v-model="product.producingArea" />
    </el-form-item>
     <el-form-item label="商品库存">
        <el-input v-model="product.stock" />
    </el-form-item>
    <el-form-item label="商品描述">
        <el-input v-model="product.description" />
    </el-form-item>



    <el-form-item label="商品图片">
      <div v-if="this.$route.params.id !=null">
        <img :src="url+product.picture" min-width="160" height="160" />
      </div>

      <!-- upload -->
        <el-upload
          class="upload-demo"
          action="http://localhost:8089/hf/goods/uploadImg"
          accept="image/jpeg,image/gif,image/png"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :on-success="success"
          ref="upload"
          :limit="1"
          list-type="picture">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
        <!-- <el-input v-model="product.picture" /> -->
    </el-form-item>





    <el-form-item>
        <el-button type="primary" @click="onSubmit">{{this.$route.params.id?'修改':'新增'}}</el-button>
        <el-button @click="onCancel">取消</el-button>
    </el-form-item>

    <editPop ref="editProp" @getType='getGoodsType()'></editPop>


  </el-form>
  </div>
</template>

<script>
import editPop from '@/views/system/editPop'
import { sendServer } from "../../utils/common"
export default {
  components: {
    editPop,
  },
  data() {
    return {
      url:"http://localhost:8089/img/",
      imaData:'',
      product: {

        "goodstypeId": '',
        "goodsname": "",
        "producingArea": "",
        "createTime": '',
        "price": '',
        "sellPrice": '',
        "stock": '',
        "picture": '',
        "discount":0,
        "updateTime": '',
        "description": "",
        "onsale":0
      },
      options: [],

    }
  },
  mounted(){

  },
  created(){
    this.getGoodsType()
    if(this.$route.params.id !=null){
      this.fetchDataById()//获取编辑的信息
    }
    console.log(this.data())

  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
   success(){
     this.imaData=this.$refs.upload.uploadFiles[0].response.extend.imgData
     this.product.picture=this.imaData;

   },
    fetchDataById(){
      var vm=this;
      var id=this.$route.params.id;
      this.axios({
        method:'POST',
        url:"http://localhost:8089/hf/goods/getProductById",
        data:{
          id:id
        }
      }).then(function (res){
        console.log(res.data)
        vm.product=res.data.extend.goods
      })
    },
    addType(data){
      console.log(data)
      this.$refs.editProp.init(data);
    },
    getGoodsType(){
      var vm=this;
      let urlParams={}
      let send={}
      urlParams.url='goods/getGoodsType',
      sendServer(urlParams,(commRes)=>{
        console.log(commRes)
        if(commRes.code=100){
           vm.options=commRes.extend.list
        }
      })

    },


    onSubmit() {
      var vm=this;
      let urlParams={}
      let send={}
      var time=new Date().getTime()
      var goods=this.product;


      send.discount=goods.discount;
      send.goodstypeId=goods.goodstypeId
      send.goodsname=goods.goodsname
      send.producingArea=goods.producingArea
      send.price=goods.price
      send.sellPrice=goods.sellPrice
      send.stock=goods.stock
      send.updateTime=goods.updateTime
      send.description=goods.description
      send.createTime=time;
      send.onsale=goods.onsale;
      console.log(send)
      if(!this.$route.params.id){
        send.picture=this.imaData
        urlParams.url='goods/addProduct'
        urlParams.send=send;
        sendServer(urlParams,(commRes)=>{
          if(commRes.code=100){
            vm.$message('新增成功!')
            vm.$router.push('/example/products')
          }else{
            vm.$message('新增失败了！!')
          }
        })

      }else{
        send.id=this.$route.params.id;
        send.updateTime=time;
        send.createTime=goods.createTime;
        send.picture=this.imaData || this.product.picture
        urlParams.url='goods/updateProduct'
        urlParams.send=send;
        sendServer(urlParams,(commRes)=>{
          if(commRes.code=100){
            vm.$message('修改成功!')
            vm.$router.push('/example/products')
          }else{
            vm.$message('修改失败了！!')
          }
        })
      }

    },
    onCancel() {
       this.$router.push('/example/products')
      this.$message({
        message: '取消成功!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
.app-container {
  margin: 50px 5% 30px 5%;
  padding: 50px;
  width: 90%;
  height: 1000;
  border: 1px solid #DCDFE6;
  border-radius:20px ;
  box-shadow: 0px 0px 20px  #DCDFE6;
}
.box{
  display: flex;
  flex-direction: row;
  align-items: center;

}
.choseBox{
  display: flex;
  flex-direction: row;
}
</style>

