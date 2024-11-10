<template>
  <div>
    <iframe
      src="http://101.34.50.199:4999"
      id="mobsf"
      scrolling="no"
      frameborder="0"
      style="position:absolute;height:100%;width:100%;"
    ></iframe>
  </div>
</template>

<script>
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
  };
</script>

<style scoped>
  .freeback-container {
    margin: 15px auto;
    width: 80%;
    height: 600px;
    display: flex;
    align-items: center;
    background-color: blue;
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
    border: 1px blue dotted;
  }
  .freeback-form {
    margin: 30px auto;
    width: 90%;
  }
</style>
