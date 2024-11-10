<template>
 <div class="app-container" style="text-align:center">
   <!--  -->
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
      <el-table-column  label="序号" width="100" >
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>

      <el-table-column label="分类ID" width="200">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="类别名称" >
        <template slot-scope="scope">
          {{ scope.row.goodstypename }}
        </template>
      </el-table-column>
      <el-table-column label="类别描述" >
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="300px" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="addType(scope.row.id)">
            编辑
          </el-button>
           <el-button type="success" size="mini" @click="addType()">
            添加
          </el-button>
          <el-button size="mini" type="danger"  @click="open(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
       <!-- 分页 -->


    </el-table>
    <addPop ref="editProp" @getGoodsType='getGoodsType()' ></addPop>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getGoodsType" />

 </div>

</template>

<script>
import addPop from '@/views/system/addPop'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { sendServer } from "../../utils/common"
export default {
    components: { Pagination ,addPop},

    data(){
        return{
          multipleSelection: [],
            total: 0,
            listQuery: {
                page: 1,
                limit: 10,
            },
            list:null,
            listLoading: true
        }
    },
    created(){
        this.getGoodsType()
    },
    methods:{
      /**
     * 多选
     * @method handleSelectionChange
     * @param {array} val 选中的数据集合
     */
      handleSelectionChange(val) {
        this.multipleSelection = val;
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
          url:'http://localhost:8089/hf/goods/deleteByBatch?ids='+ids

        }).then(function(res){
          // console.log(res.data)
          if(res.data.code==100){
            vm.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            vm.getGoodsType()

          }
        })

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
                method:'GET',
                // ?goodstypeId=48&id=48
                url:'http://localhost:8089/hf/goods/delType?id='+id+'&goodstypeId='+id,
                // data:{
                // "id":id
                // }

            }).then(function(res){
                // console.log(res.data)
                if(res.data.code==100){
                vm.$message({
                    type: 'success',
                    message: '删除成功!'
                });
                vm.getGoodsType()
                }else{
                vm.$message({
                    type: 'error',
                    message: '删除失败,此分类下面包含商品！！!'
                });
                }
            })
        },
        // editType(data){
        //
        //     this.$refs.editProp.init(data);
        // },
        addType(data){
            console.log(data)
            this.$refs.editProp.init(data);
        },
        getGoodsType(){
            var vm=this;
            this.listLoading = true;
            this.axios({
                method:'GET',
                url:'http://localhost:8089/hf/goods/getGoodsType2?pageNum='+vm.listQuery.page+'&pageSize='+vm.listQuery.limit,
            }).then(function(res){
                // console.log(res.data)
                vm.list=res.data.extend.pageInfo.list
                vm.total=res.data.extend.pageInfo.total
                vm.listLoading = false
            });



        },

    }

}
</script>

<style lang="scss" scoped>
.left{
  float: left;
  margin: 5px auto 5px 5px;
}

</style>
