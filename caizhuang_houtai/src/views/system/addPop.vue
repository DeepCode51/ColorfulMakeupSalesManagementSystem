<template>
    <div>
        <el-dialog :title="id?'编辑商品分类':'新增商品分类'" :visible.sync="dialogVisible" width="500px" >
            <el-form :model="dataForm" ref="dataForm" :rules="rules" label-width="100px" class="demo-ruleForm" style="overflow:hidden">
                <el-form-item label="分类名称" prop="goodstypename" style="width:100%;float:center">
                    <el-input v-model="dataForm.goodstypename" ></el-input>
                </el-form-item>
                <el-form-item label="分类描述" prop="description" style="width:100%;float:center">
                    <el-input v-model="dataForm.description" ></el-input>
                </el-form-item>
            </el-form>
           
            <span slot="footer" class="dialog-footer">
                <!-- dialogVisible = false -->
                <el-button @click="cancel('dataForm')">取 消</el-button>
                <el-button type="primary" @click="addProduct()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import { sendServer } from "../../utils/common"
export default {
    data() {
        return {
            dialogVisible: false,
            id:'',
            dataForm: {
               id:'',
               goodstypename:'',
               description:''
            },
            rules:{
                 goodstypename: [
                    { required: true, message: '请输入分类 名称', trigger: 'blur' }
                ],
                description: [
                    { required: true, message: '请输入分类描述', trigger: 'blur' }
                ],
                
            }
           
           
           
        };
    },
    mounted(){
    },
    created(){
        // this.getTypeById()

    },
    methods: {
        
        init(data=''){
            this.dialogVisible=true;
            this.dataForm.goodstypename = '';
            this.dataForm.description = '';
            this.id = '';
            if(data!==''){
                console.log("编辑商品")
                this.id = data
                // console.log(this.id)===>可以取到
                this.getTypeById(data);

            }
           
            
        },
        cancel(dataForm){
              this.$refs[dataForm].resetFields();
            this.dialogVisible=false;
            this.dataForm.goodstypename = '';
            this.dataForm.description = '';

        },
         getTypeById(id){
            var vm=this;
            let urlParams={}
            let send={}
            urlParams.url='goods/getTypeById';
            send.id=id;
            // console.log(send)
            urlParams.send=send;
            sendServer(urlParams,(commRes)=>{
                console.log(commRes)
                if(commRes.code=100){
                    vm.dataForm=commRes.extend.goodsType
                    
                }
            })

        },
        addProduct(){
            var vm=this;
            this.$refs.dataForm.validate((valid) =>{
                if(valid){
                    let urlParams={}
                    let send={}
                    
                    send.goodstypename=vm.dataForm.goodstypename;
                    send.description=vm.dataForm.description;
                    if(this.id){
                        send.id = this.id
                        urlParams.url='goods/editGoodsType';
                    }else{
                        urlParams.url='goods/addGoodsType';
                    }
                    urlParams.send=send;
                    sendServer(urlParams,(commRes)=>{
                        console.log(commRes)
                        if(commRes.code=100){
                            vm.dialogVisible=false;
                            vm.$message("处理成功")
                            vm.$emit('getGoodsType')
                        }
                    })
                }else {
                    // vm.dataForm.goodstypename = ''
                    console.log('error submit!!');
                    return false;
                }
            })
            

        }

    }
    
};
</script>