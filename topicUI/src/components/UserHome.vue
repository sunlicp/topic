<template>
<div style="background-image: url('/static/background/background2.jpg');height: 100vh;background-size: 100% 100%;
    background-repeat: no-repeat;">
  <div class="header">
    <el-button style="font-size: large;margin-left:30px;margin-top: 4px;color: #0066ff" type="text" icon="el-icon-arrow-left"
               @click="$router.push('/layout')">返回首页</el-button>
  </div>
  <div class="container">
    <div class="avatar">
      <el-tooltip effect="dark" content="点击上传头像" placement="left">
        <el-avatar :size="60" :src="circleUrl" style="cursor: pointer" @click.native="show=!show"></el-avatar>
      </el-tooltip>
      <div style="font-size: 25px;font-weight: bolder;margin-left: 25px;color: #0066ff">{{username}}</div>
    </div>
    <my-upload field="picture" @crop-upload-success="cropUploadSuccess" v-model="show"
               :width="200" :height="200" url="http://topic.slcp.top/blog/uploadImg"
               img-format="png" :params="params"></my-upload>
    <div class="content">
      <el-tabs tab-position="left" v-model="selectName" style="height: 430px;" @tab-click="handleClick">
        <el-tab-pane label="个人收藏" name="userStar"><router-view></router-view></el-tab-pane>
        <el-tab-pane label="修改密码" name="updatePwd"><router-view></router-view></el-tab-pane>
      </el-tabs>
    </div>
  </div>
</div>
</template>

<script>
import myUpload from 'vue-image-crop-upload/upload-2.vue'
export default {
  name: "UserHome",
  data(){
    return{
      selectName:'userStar',
      show:false,
      params:{
        username:JSON.parse(sessionStorage.getItem('username'))
      },
      circleUrl:JSON.parse(sessionStorage.getItem('avatar'))
    }
  },
  components: {
    'my-upload': myUpload
  },
  mounted() {
    if (JSON.parse(sessionStorage.getItem('username'))===null){
      this.$router.push('/')
    }
  },
  computed:{
    //用户名
    username(){
      return JSON.parse(sessionStorage.getItem('username'))
    },
  },
  methods:{
    cropUploadSuccess(jsonData){
      sessionStorage.setItem("avatar",JSON.stringify(jsonData.t))
      this.circleUrl=jsonData.t
    },
    handleClick(tab) {
      this.$router.push('/userHome/'+tab.name)
    }
  }
}
</script>

<style scoped>
.header {
  background-color: #000;
  height: 53px;
  width: 100%;
}
.container {
  width: 70%;
  height: 600px;
  margin: 30px auto;
  border-radius: 50px;
  background-color: #fff;
}
.avatar {
  width: 100%;
  height: 150px;
  display: -webkit-flex;
  display: flex;
  align-items: center;
  justify-content: center;
}
.content {
  width: 100%;
  height: 450px;
}
</style>
