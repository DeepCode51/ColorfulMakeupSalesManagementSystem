<template>
  <div class="app-container">
  <el-form ref="form" :model="product" label-width="120px">
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
        <el-input v-model="product.picture" />
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
    </el-form-item>


  </el-form>
    <!-- <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Activity name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Activity zone">
        <el-select v-model="form.region" placeholder="please select your zone">
          <el-option label="Zone one" value="shanghai" />
          <el-option label="Zone two" value="beijing" />
        </el-select>
      </el-form-item>
      <el-form-item label="Activity time">
        <el-col :span="11">
          <el-date-picker v-model="form.date1" type="date" placeholder="Pick a date" style="width: 100%;" />
        </el-col>
        <el-col :span="2" class="line">-</el-col>
        <el-col :span="11">
          <el-time-picker v-model="form.date2" type="fixed-time" placeholder="Pick a time" style="width: 100%;" />
        </el-col>
      </el-form-item>
      <el-form-item label="Instant delivery">
        <el-switch v-model="form.delivery" />
      </el-form-item>
      <el-form-item label="Activity type">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="Online activities" name="type" />
          <el-checkbox label="Promotion activities" name="type" />
          <el-checkbox label="Offline activities" name="type" />
          <el-checkbox label="Simple brand exposure" name="type" />
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="Resources">
        <el-radio-group v-model="form.resource">
          <el-radio label="Sponsor" />
          <el-radio label="Venue" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="Activity form">
        <el-input v-model="form.desc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      product: {
        "id": 1,
        "goodstypeId": 1,
        "goodsname": "池夏秋日下午茶",
        "producingArea": "安徽合肥",
        "createTime": null,
        "price": 228.0000,
        "sellPrice": 200.0000,
        "stock": 100,
        "picture": "1.jpg",
        "discount": true,
        "shopId": 1,
        "updateTime": null,
        "description": "池夏秋日下午茶:月夕兔 明制对襟长衫百迭裙云肩 原创改良汉服女"
      },
      options:[],
      value:'',
    }
  },
  created() {
    this.fetchDataById()
  },
  methods: {
    onSubmit() {
      var vm=this;
      var id=this.$route.params.id;
      // var goodsname=this.product.goodsname;
      var goods=this.product;
      var time=new Date().getTime()
      // console.log(id,goodsname,goods)
      // console.log(id)
      this.axios({
        method:'POST',
        url:'http://localhost:8089/hf/goods/updateProduct',
        data:{

          "id": goods.id,
          "goodstypeId": goods.goodstypeId,
          "goodsname": goods.goodsname,
          "producingArea": goods.producingArea,
          "createTime": goods.createTime,
          "price": goods.price,
          "sellPrice": goods.sellPrice,
          "stock": goods.stock,
          "picture": goods.picture,
          "discount": goods.discount,
          "shopId": goods.shopId,
          "updateTime": time,
          "description": goods.description
        }


      }).then(function(res){
        console.log(res)
        if(res.data.code=100){
          vm.$message('编辑成功!')
          vm.$router.push('/example/products')
        }else{

        }
      })
      // this.$message('submit!')
    },
    onCancel() {
      this.$router.push('/example/products')
      this.$message({
        message: '取消编辑!',
        type: 'warning'
      })
    },
    fetchDataById(){
      var vm=this;
      var id=this.$route.params.id;
      this.axios({
        method:'POST',
        // url:'http://localhost:8089/hf/goods/getProductById?id='+id
        url:'http://localhost:8089/hf/goods/getProductById',
        data:{
          id:id
        }

      }).then(function (res){
        console.log(res.data)
        vm.product=res.data.extend.goods
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

