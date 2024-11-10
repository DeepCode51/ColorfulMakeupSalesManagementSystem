<template>
  <div>
    <div class="top-title"> 收货地址</div>
    <div class="add-box">
      <p class="add-title">新增收货地址</p>
      <div>
        <Form ref="formCustom" :model="formCustom" :rules="ruleValidate" :label-width="100"  label-position="left"class="form">
          <FormItem label="地址信息:" prop="address" >
             <v-distpicker :placeholders="placeholders" :province="select.province" :city="select.city" :area="select.area" @selected="onSelected"></v-distpicker>
              <!-- <Input  v-model="formCustom.address" placeholder="请选择省/市/区/街道" ></Input> -->
          </FormItem>
          <FormItem label="详细地址"prop="detail">
              <Input  v-model="formCustom.detail" placeholder="请输入详细地址信息，如道路、门牌号、小区"></Input>
          </FormItem>
          <FormItem label="邮政编码:" prop="postcode" >
              <Input v-model="formCustom.postcode" placeholder="请填写编码"></Input>
          </FormItem>
          <FormItem label="收货人姓名:" prop="receName" >
              <Input v-model="formCustom.receName" placeholder="长度不超过25个字符"></Input>
          </FormItem>
          <FormItem label="手机号码:" prop="recePhone" >
              <Input v-model="formCustom.recePhone" placeholder="请填写手机号码"></Input>
          </FormItem>
          <!-- <Radio v-model="formCustom.status" @on-change="change">设置为默认地址</Radio> -->
          <i-switch v-model="formCustom.status" size="small"/>设置为默认地址
      </Form>
      </div>

      <div class="btn">
        <Button type="primary" @click="handleSubmit('formCustom')" >保存</Button>
        <Button type="default" @click="handleReset('formCustom')">重置</Button>
      </div>

      <div class="address-list">
        <p>地址列表</p>
      </div>
      <!-- 地址列表 -->
      <!-- columns12   columns4   @on-selection-change="selectChange"   @on-row-click="rowClick" @on-cell-click="cellClick"-->
      <div class="table-list">
        <i-table border ref="selection" :columns="columns4" :data="addressList" @on-select="handleSelectRow()" @on-selection-change="selectChange" >
          <template slot-scope="{ row, index }" slot="action">
            <Button type="primary" size="small" style="margin-right: 5px" @click="update(index)">修改</Button>
            <Button type="error" size="small" @click="remove(index)">删除</Button>
          </template>

           <!-- <template slot-scope="{ row, index }" slot="action1">
            <Button type="default" size="small" style="margin-right: 5px" @click="show(index)">修改</p></Button>
          </template> -->
        </i-table>
      </div>
       <!-- <Button @click="handleSelectAll(true)">Set all selected</Button> -->
       <Button type="error" size="small" @click="delByBatch" :disabled='multipleSelection.length===0'>批量删除</Button>

    </div>
    <Modal
        v-model="modal5"
        title="修改地址"
        width="600"
        >
        <Form ref="updateForm" :model="updateForm" :rules="ruleValidate" :label-width="100"  label-position="left"class="form">
          <FormItem label="地址信息:" prop="address" >
            <Input  v-model="updateForm.address" placeholder="请选择省/市/区/街道" ></Input>
          </FormItem>

          <FormItem label="请选择:" class="picker">
             <v-distpicker :placeholders="placeholders" @selected="updateSelect"></v-distpicker>
          </FormItem>

          <FormItem label="详细地址"prop="detail">
              <Input  v-model="updateForm.detail" placeholder="请输入详细地址信息，如道路、门牌号、小区"></Input>
          </FormItem>
          <FormItem label="邮政编码:" prop="postcode" >
              <Input v-model="updateForm.postcode" placeholder="请填写编码"></Input>
          </FormItem>
          <FormItem label="收货人姓名:" prop="receName" >
              <Input v-model="updateForm.receName" placeholder="长度不超过25个字符"></Input>
          </FormItem>
          <FormItem label="手机号码:" prop="recePhone" >
              <Input v-model="updateForm.recePhone" placeholder="请填写手机号码"></Input>
          </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" size="large" @click="modalCancel">取消</Button>
        <Button type="primary" size="large" @click="modalOk">确定</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
import VDistpicker from 'v-distpicker'
import { sendServer } from '../../utils/common'
export default {
  components:{
    VDistpicker
  },
  data(){
    const validatePhone = (rule, value, callback) => {
      let myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (value === '') {
            callback(new Error('请输入11位手机号'));
        }else if(!myreg.test(value)){
            callback(new Error('手机号格式错误'));
        }else{
            callback()
        }
    };
    return{
      multipleSelection:[],
      ids:[],
      userInfo:JSON.parse(localStorage.getItem('userdata')),
      placeholders: {
          province: '------- 省 --------',
          city: '--- 市 ---',
          area: '--- 区 ---',
      },
      select:{
        province:'',
        city:'',
        area:''
      },
      addressList:[],
      formCustom:{
        address:'',
        detail:'',
        postcode:'',
        receName:'',
        recePhone:'',
        status:false
      },
      ruleValidate: {
          address: [
              { required: true, message: '地址不可以为空！', trigger: 'blur' }
          ],
          detail: [
              { required: true, message: '详细地址不可以为空', trigger: 'blur' },
          ],
          postcode: [
              { required: true, message: '邮编不能为空！', trigger: 'blur' }
          ],
          receName: [
              { required: true, message: '收件人姓名不能为空', trigger: 'blur' }
          ],
          recePhone: [
              { required: true, message: '手机号码不能为空', trigger: 'blur' },
              { validator: validatePhone, trigger: 'blur' }
          ]

      },
      //columns 数据设置一项，指定 type: 'selection'，即可自动开启多选功能。
      columns4:[
        {
            type: 'selection',
            width: 50,
            align: 'center'
        },
        {
            title: '收货人',
            key: 'receName'
        },
        {
            title: '所在地区',
            key: 'address',
            width: 150,
        },
        {
            title: '详细地址',
            key: 'detail',
            width: 100,
        },
        {
            title: '邮编',
            key: 'postcode',
            width: 80,
        },
         {
            title: '手机',
            key: 'recePhone',
        },
        {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
        },
        {
            title: '是否默认',
            key:'moren',
            width: 90,
            render: (h, params) => {
              return h('div', [
                  h('strong', {
                    on:{
                      click: () => {
                        this.handleShow(params.row)      //点击事件
                      }
                    }
                  },params.row.moren),

              ]);
          }
        },

      ],
      modal5:false,
      updateForm:{
        address:'',
        detail:'',
        postcode:'',
        receName:'',
        recePhone:'',
        status:'',
        id:'',
      },

    }
  },
  created(){
    this.getAddList()
  },
  methods:{
    modalCancel(){
      this.modal5=false
    },
    //修改
    update(index){
      this.modal5=!this.modal5
      let add=this.addressList[index]
      this.updateForm.address=add.address
      this.updateForm.detail=add.detail
      this.updateForm.postcode=add.postcode
      this.updateForm.receName=add.receName
      this.updateForm.recePhone=add.recePhone
      this.updateForm.id=add.id
      this.updateForm.status=add.status

    },
    modalOk(){
      let updateForm=this.updateForm
      console.log(updateForm)
      let params={},send={}
      send.id=updateForm.id;
      send.status=updateForm.status;
      send.address=updateForm.address
      send.detail=updateForm.detail
      send.postcode=updateForm.postcode
      send.receName=updateForm.receName
      send.recePhone=updateForm.recePhone
      params.send=send;
      params.url="address/update"
      console.log(params)
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          this.getAddList()
          this.$Message.success('修改成功！！')
        }else{
          this.$Message.error('修改失败！！')
        }
      })
      this.modal5=false
    },
    handleShow(data){
      console.log(data)
      console.log(data)
      let id=data.id
      let userId=this.userInfo.id
      let params={},send={}
      send.userId=userId;
      send.id=id;
      params.send=send;
      params.url="address/setDeafult"
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          this.$Message.success("设置成功！！")
           this.getAddList()
        }
      })
    },
    selectChange(selection){
      // let ids=[];
      // selection.forEach(item=>{
      //  ids.push(item.id)
      // })
      // console.log(ids)
      // return ids;
      this.multipleSelection = selection;
    },
    handleSelectRow () {
      let ids = [];
      this.$refs['selection'].getSelection().map(item => {
        ids.push(item.id)
      })
      console.log(ids)
      return ids
    },
    //批量删除
    delByBatch(){
      this.$confirm('确定删除这些地址吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var ids=this.handleSelectRow()
        console.log(ids)
        this.axios({
          method:"GET",
          url:'http://localhost:8089/hf/address/deleteByBatch?ids='+ids
        }).then((res)=>{
          console.log(res)
          if(res.data.code==100){
            //删除成功的时候刷新一下购物车
            this.getAddList()
            this.$Message.success("删除成功！")
          }else{
            this.$Message.error("删除失败")
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    //删除
    remove (index) {
      // console.log(this.addressList[index])
      this.$confirm('是否删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let id=this.addressList[index].id;
        let params={},send={}
        send.id=id;
        params.send=send;
        params.url="address/delete"
        sendServer(params,(commRes)=>{
          if(commRes.code==100){
            this.$Message.success("删除成功！")
            this.addressList.splice(index, 1);
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    //获取收货地址
    getAddList(){
      let params={},send={}
      send.userId=this.userInfo.id;
      params.send=send;
      params.url='address/showAddress'
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          console.log(commRes)
          commRes.extend.addressList.forEach(item=>{
            if(item.status=='1'){
              item.moren='默认地址'
            }else{
              item.moren='设为默认'
            }
          })
          this.addressList=commRes.extend.addressList
          console.log(this.addressList)
        }
      })

    },

   updateSelect(data){
      let province=data.province.value,
      city=data.city.value,
      area=data.area.value
      let address=province+city+area
      this.updateForm.address=address
   },
    onSelected(data) {
        //  console.log(data)
        let province=data.province.value,
        city=data.city.value,
        area=data.area.value
        let address=province+city+area
        console.log(address)
        this.formCustom.address=address
    },
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
          if (valid) {
            let params={},send={};
            let status=0
            let formCustom=this.formCustom;
            send.userId=this.userInfo.id;
            send.address=formCustom.address
            send.detail=formCustom.detail
            send.postcode=formCustom.postcode
            send.receName=formCustom.receName
            send.recePhone=formCustom.recePhone
            //状态为1 是默认地址
            if(formCustom.status){
              status=1
            }
            send.status=status
            console.log(send)
            params.send=send;
            params.url="address/addAddress"
            console.log(params)
            sendServer(params,(commRes)=>{
              if(commRes.code==100){
                  this.getAddList()
                  this.$Message.success('新增成功!');
                  this.formCustom={}
                  this.select.province='------- 省 --------',
                  this.select.city='--- 市 ---',
                  this.select.area='--- 区 ---'
              }else{
                  this.$Message.error('新增失败，请稍后再试');
              }
            })
          } else {
            this.$Message.error('表单不能为空');
          }
      })
    },
    handleReset (name) {
        this.$refs[name].resetFields();
    }
  }

}
</script>
<style>
.picker{
  padding-left: 10px;
}
.ivu-table-cell {
    padding-left: 18px;
    padding-right: 18px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
    word-break: break-all;
    box-sizing: border-box;
    font-size: 10px;
}
</style>
<style scoped>

.table-list{
  font-size: 12px;
  margin-bottom: 10px;
}
.address-list p{
  line-height: 30px;
  font-weight: 600;
  margin-left: 10px;
}
.address-list{
  width:100%;
  height: 30px;
  background-color: rgb(175, 207, 248);
  margin-bottom: 10px;
}

.btn{
  margin-top: 10px;
  margin-bottom: 20px;
}
.form{
  margin-top: 30px;
  width: 500px;
}
.top-title{
  margin-top: 10px;
  margin-left: 20px;
  font-size: 14px;
  font-weight: 600;
  margin-bottom: 10px;
}
.add-box{
  margin-top: 10px;
  margin-left: 20px;
  margin-bottom: 10px;
}
.add-title{
  color:#d4313f;
  font-size: 13px;
}


</style>
