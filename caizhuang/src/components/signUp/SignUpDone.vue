<template>
  <div class="container1">
<!--    <p><Icon type="ios-checkmark-circle-outline" /></Icon></p>-->
    <router-link to="/login"><Button type="success" size="large"  long class="btn-success">注册成功</Button></router-link>
  </div>
</template>

<script>
export default {
  name: 'SignUpDone'
};
</script>

<style scoped>
.container1 {
  width: 100%;
  text-align: center;
}
.container p {
  font-size: 90px;
  color: #4ea0f1;
  text-align: center;
}
.btn-success {
  margin-top: 30px;
  background: #4ea0f1;
  border: 1px solid #4ea0f1;
}
</style>
