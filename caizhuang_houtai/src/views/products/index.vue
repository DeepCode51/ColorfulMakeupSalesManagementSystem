<template>
  <div class="app-container">

    <div class="searchBox">
      <!-- <div align="right" class="button">
        <el-button type="success" @click="addPro()">添加商品</el-button>
      </div> -->
      <el-form :model="product" :inline="true" size="small" label-width="80px" class="demo-ruleForm" style="overflow:hidden">
        <el-form-item label="商品名称">
          <el-input v-model="product.goodsname" clearable placeholder="请输入商品名称"></el-input>
        </el-form-item>

        <el-form-item label="商品分类">
          <el-select v-model="product.goodstypeId" placeholder="请选择" >
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.goodstypename"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="是否上架">
          <el-select v-model="product.onsale" placeholder="请选择" >
            <el-option
              v-for="item in options1"
              :key="item.salechose"
              :label="item.name"
              :value="item.salechose">
            </el-option>
          </el-select>
        </el-form-item>

        <!-- <el-form-item label="是否有折扣" label-width="100">
          <el-select v-model="product.discount" placeholder="请选择" >
            <el-option
              v-for="item in options2"
              :key="item.discountchose"
              :label="item.name"
              :value="item.discountchose">
            </el-option>
          </el-select>
        </el-form-item> -->

        <!-- <el-form-item v-if="deptId===null" label="分类">
          <el-cascader ref="deptTree" :options="options" v-model="dataForm.dept_id" filterable :show-all-levels='false' :props="props" clearable style="width:100%"></el-cascader>
        </el-form-item> -->
        <!-- <el-form-item label="商品类型">
          <el-select ref="types" v-model="dataForm.goods_type_id" placeholder="商品类型" style="width:100%">
            <el-option v-for="item in GoodsTypes" :label="item.dictName" :value="item.dictId" :key="item.dictId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item> -->
        <el-form-item align="right">
          <el-button @click="reset">重置</el-button>
          <el-button @click="getList">查询</el-button>
          <el-button @click="addPro">添加商品</el-button>
           <!-- <el-button @click="bb">测试</el-button> -->
        </el-form-item>
      </el-form>
    </div>

    <div class="left">
      <el-button type="danger" size="mini" icon="el-icon-delete" @click="delChose()" :disabled='multipleSelection.length===0'>批量删除</el-button>
    </div>
    <el-table
     :cell-style="{'text-align':'center'}"
     :header-cell-style="{'text-align':'center'}"
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row

      @selection-change="handleSelectionChange"
      ref="multipleTable"
    >

      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column align="center" label="序号" width="55">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>

      <el-table-column label="商品ID" width="75">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="商品名称" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ scope.row.goodsname }}
        </template>
      </el-table-column>
      <el-table-column label="分类" >
        <template slot-scope="scope">
          {{ scope.row.goodsType.goodstypename }}
        </template>
      </el-table-column>
      <el-table-column label="商品产地">
        <template slot-scope="scope">
          {{ scope.row.producingArea }}
        </template>
      </el-table-column>


      <el-table-column label="原价" >
        <template slot-scope="scope">
          {{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column label="折扣价" >
        <template slot-scope="scope">
          {{ scope.row.sellPrice }}
        </template>
      </el-table-column>
      <el-table-column label="商品库存">
        <template slot-scope="scope">
          {{ scope.row.stock }}
        </template>
      </el-table-column>



      <el-table-column show-overflow-tooltip label="描述">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>


      <el-table-column label="图片">
        <template slot-scope="scope">
          <!-- <img :src="url+scope.row.picture" min-width="70" height="70" /> -->
          <div class="demo-image__preview">
            <el-image
              style="width: 80px; height: 80px"
              :src="url+scope.row.picture"
              >
              <!-- :preview-src-list="url+scope.row.picture" -->
            </el-image>
          </div>
        </template>
        <!-- <template slot-scope="scope">
          {{ scope.row.picture }}
        </template> -->
      </el-table-column>

      <el-table-column label="操作" align="center" width="300px" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="editPro(scope.row.id)">
            编辑
          </el-button>
          <!-- <el-button  size="mini" type="danger" @click="delPro(scope.row.id)">
            删除
          </el-button> -->
          <el-button size="mini" type="danger"  @click="open(scope.row.id)">删除</el-button>

          <el-switch
            v-model="scope.row.onsale"
            :active-value="1"
            :inactive-value="0"
            active-text="上架"
            inactive-text="下架"
            @change="changeSale(scope.row.id,scope.row.onsale)">
        </el-switch>
        </template>
      </el-table-column>

      <!-- <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="Title">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="Author" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Pageviews" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.pageviews }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="Display_time" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.display_time }}</span>
        </template>
      </el-table-column> -->
    </el-table>

    <!-- 分页 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { sendServer } from "../../utils/common"
// import { getList } from '@/api/table'
import Qs from 'qs'
export default {
  components: { Pagination },
  // filters: {
  //   statusFilter(status) {
  //     const statusMap = {
  //       published: 'success',
  //       draft: 'gray',
  //       deleted: 'danger'
  //     }
  //     return statusMap[status]
  //   }
  // },
  data() {
    return {
      multipleSelection: [],
      url:"http://localhost:8089/img/",
      total: 0,
      listQuery: {
        page: 1,
        limit: 10,
      },
      list:null,
      product: {

        "goodstypeId": '',
        "goodsname": "",
        "onsale":'',
        "discount":''
      },
      options1:[
        {
          "salechose":0,
          "name":"已下架"
        },
        {
          "salechose":1,
          "name":"已上架"
        }
      ],
       options2:[
        {
          "discountchose":0,
          "name":"原价出售"
        },
        {
          "discountchose":1,
          "name":"打折出售"
        }
      ],
      options: [],
      value:'',
      listLoading: true
    }
  },
  created() {
    this.getList();

  },
  mounted(){
    // this.shopId =JSON.parse(localStorage.getItem("userdata")).shopId
    this.getGoodsType()
  },
  methods: {
    bb(){
      console.log(this.product.goodsname,this.product.goodstypeId,this.product.onsale)

    },
    delChose(){
        let arr =this.multipleSelection.map((item) => item.id);
        console.log(arr);
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                this.delByBatch(arr);
                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });

      },
      delByBatch(ids){
        var vm=this;
        this.axios({
          method:"GET",
          url:'http://localhost:8089/hf/goods/delProductByBatch?ids='+ids

        }).then(function(res){
          // console.log(res.data)
          if(res.data.code==100){
            vm.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            vm.getList()

          }
        })

      },
     handleSelectionChange(val) {
        this.multipleSelection = val;
     },

    changeSale(id,onsale){

      console.log(id,onsale)
      let urlParams={}
      let send={}
      send.id=id;
      send.onsale=onsale;
      urlParams.send=send;
      urlParams.url='goods/updateSale'
      sendServer(urlParams,(commRes)=>{
        console.log(commRes)
      })


    },
    /**
     * 清空条件查询，并刷新数据
     */
    reset() {
      this.product.goodsname = "";
      this.product.goodstypeId = "";
      this.product.onsale="",
      this.product.discount=""
      this.getList();
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
   getList(){

     var vm=this;
     this.listLoading = true;
     this.axios({
        method:'POST',
        url:'http://localhost:8089/hf/goods/getlistByPage',
        // url:'http://localhost:8089/hf/goods/getlistByPage?pageNum='+vm.listQuery.page+'&pageSize='+vm.listQuery.limit
        transformRequest:[function(data){
          return Qs.stringify(data)
        }],
        data:{
          pageNum:vm.listQuery.page,
          pageSize:vm.listQuery.limit,
          searchname:vm.product.goodsname,
          typeId:vm.product.goodstypeId,
          hasSale:vm.product.onsale
        }
      }).then(function(res){
        console.log(res.data)
        vm.list=res.data.extend.pageInfo.list
        vm.total=res.data.extend.pageInfo.total
        vm.listLoading = false
      });


   },
    addPro(){
      this.$router.push('/example/addProduct')
    },
    editPro(id){
      this.$router.push('/editProduct/index/'+id)
      // console.log(id)
    },
    open(id){

      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.delPro(id);

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },
    delPro(id){
      var vm=this;

      this.axios({
        method:'POST',
        url:'http://localhost:8089/hf/goods/delProduct',
        data:{
          "id":id
        }

      }).then(function(res){
        console.log(res.data)
        if(res.data.code==100){
          vm.$message({
            type: 'success',
            message: '删除成功!'
          });
          vm.getList()
        }else{
          vm.$message({
            type: 'error',
            message: '删除失败!'
          });
        }
      })
    },

  }
}
</script>
<style scoped>

.searchBox{
  margin: 0 0 20px 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 1500;
}

.left{
  float: left;
  margin: 5px auto 5px 5px;
}

</style>
