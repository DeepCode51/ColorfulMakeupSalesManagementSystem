<template>
    <div>
        <el-dialog title="新增商品分类" :visible.sync="dialogVisible" width="500px" >
            <el-form :model="dataForm" ref="dataForm" :rules="rules" label-width="100px" class="demo-ruleForm" style="overflow:hidden">
                <el-form-item label="分类名称" prop="goodsTypeName" style="width:100%;float:center">
                    <el-input v-model="dataForm.goodsTypeName"></el-input>
                </el-form-item>
                <el-form-item label="分类描述" prop="description" style="width:100%;float:center">
                    <el-input v-model="dataForm.description" ></el-input>
                </el-form-item>
            </el-form>
           <!-- <el-form :model="dataForm" ref="dataForm" :rules="rules" label-width="100px" class="demo-ruleForm" style="overflow:hidden">
                <el-form-item label="用户名称" prop="userName" style="width:50%;float:left">
                    <el-input v-model="dataForm.userName" ></el-input>
                </el-form-item>
                <el-form-item label="用户昵称" prop="nickName" style="width:50%;float:left">
                    <el-input v-model="dataForm.nickName" ></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone" style="width:50%;float:left">
                    <el-input v-model="dataForm.phone" ></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender" style="width:50%;float:left">
                    <el-select v-model="dataForm.gender" placeholder="请选择" style="width:100%">
                        <el-option v-for="item in genderList" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="角色" prop="roleId" style="float:left;width:50%" >
                    <el-select v-model="dataForm.roleId" placeholder="请选择" style="width:100%">
                        <el-option v-for="item in roleList" :key="item.roleName" :label="item.roleName" :value="item.roleId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-show="dataForm.roleId!=1" label="部门" :prop="dataForm.roleId==1?'deptIdaas':'deptId'" style="float:left;width:50%" >
                    <el-cascader :disabled="deptId!==''" ref="deptTree" @change='getDept' :options="options" v-model="dataForm.deptId" filterable :show-all-levels='false' :props="props" clearable style="width:100%"></el-cascader>
                </el-form-item>
                <el-form-item label="备注" prop="remark" style="float:left;width:100%">
                    <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="dataForm.remark"></el-input>
                </el-form-item>
            </el-form> -->
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
        const hasTypeName = function(rule, value, callback) {
            if (value) {
                let params={}
                let send={}
                params.url="goods/count";
                send.goodstypename=value;
                params.send=send;
                sendServer(params,(commRes)=>{
                    console.log(commRes)
                    if(commRes.code==100){
                        callback()
                    }else{
                        callback(new Error('该分类已存在'))
                    }
                });
            }else{
                return callback()
            }
        }
        
        return {
            dialogVisible: false,
           
            dataForm: {
               goodsTypeName:'',
                description:''
            },
            rules:{
                 goodsTypeName: [
                    { required: true, message: '请输入分类 名称', trigger: 'blur' },
                    { validator: hasTypeName, trigger: 'blur' }
                ],
                description: [
                    { required: true, message: '请输入分类描述', trigger: 'blur' }
                ],
                
            }
           
           
           
        };
    },
    mounted(){
    // let userData=localStorage.getItem("userdata")
    // let user=JSON.parse(userData)
    // var shopId=user.shopId;
    this.shopId =JSON.parse(localStorage.getItem("userdata")).shopId
  },
    methods: {
        blur(){
            // alert("hhhh");
            // console.log(123)
            var goodsTypeName=this.dataForm.goodsTypeName
            console.log(goodsTypeName)
            let params={}
            let send={}
            params.url="goods/count";
            send.goodstypename=goodsTypeName;
            params.send=send;
            sendServer(params,(commRes)=>{
                console.log(commRes)
                if(commRes.code=100){

                }else{
                    // commRes.extend.va_msg
                }
            })


        },
        init(){
           
            this.dialogVisible=true;
            this.dataForm.goodsTypeName = '';
            this.dataForm.description = '';
        },
        cancel(dataForm){
            this.$refs[dataForm].resetFields();
            this.dialogVisible=false;
            this.dataForm.goodsTypeName = '';
            this.dataForm.description = '';

        },
        addProduct(){
            var vm=this;
            this.$refs.dataForm.validate((valid) =>{
                if(valid){
                    let urlParams={}
                    let send={}
                    urlParams.url='goods/addGoodsType',
                    send.goodstypename=vm.dataForm.goodsTypeName,
                    send.description=vm.dataForm.description,
                    // send.shopId=this.shopId
                    console.log(send)
                    urlParams.send=send;
                    sendServer(urlParams,(commRes)=>{
                        console.log(commRes)
                        if(commRes.code=100){
                            vm.dialogVisible=false;
                            vm.$message("新增成功！！")
                            vm.$emit('getType')
                        }
                    })
                }else {
                    // vm.dataForm.goodsTypeName = ''
                    console.log('error submit!!');
                    return false;
                }
            })
            

        }

    }
    
};
</script>