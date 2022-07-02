<template>
  <div style="width: 100%; height: 100%; overflow: hidden">
    <video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
        <source src="../../static/vedio/register.mp4" type="video/mp4">
    </video>
    <div class="me-login-box me-login-box-radius">
      <div class="formStyle">
        <div style="color: #409EFF; font-size: 25px;font-weight: bold; text-align: center;padding: 30px 0">欢迎注册</div>
        <el-form ref="form" :rules="rules" :model="form" size="normal" >
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user-solid" v-model="form.username" clearable placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" v-model="form.password" clearable placeholder="请输入密码" show-password></el-input>
          </el-form-item>
          <el-form-item prop="confirm">
            <el-input prefix-icon="el-icon-lock" v-model="form.confirm" clearable placeholder="请确认密码" show-password></el-input>
          </el-form-item>
        </el-form>
        <div style="display: flex;justify-content: space-around;margin-bottom: 15px">
          <el-button style="width: 48%" type="primary" round plain @click="register">注册</el-button>
          <el-button style="width: 48%" type="danger" round plain @click="goToBack">返回</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data(){
    return{
      form: {},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirm: [
          { required: true, message: '请确认密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    register(){
      if (this.form.password !== this.form.confirm){
        this.$message({
          type: "error",
          message: "两次输入密码不一致"
        })
        return
      }
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.$axios({
            url:'/user/register',
            method:'post',
            data:this.form
          }).then(({data})=>{
            if (data.code == 200){
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/")
            }else {
              this.$message({
                type: "error",
                message: data.message
              })
            }
          })
        }
      })
    },
    goToBack(){
      this.$router.back()
    }
  }
}
</script>

<style scoped>
.formStyle {
  height: 50%;
  padding: 10px 20px;
  background-color: white;
  border-radius: 20px;
}

  .me-video-player {
    background-color: transparent;
    width: 100%;
    height: 100%;
    object-fit: fill;
    display: block;
    position: absolute;
    left: 0;
    z-index: 0;
    top: 0;
  }

  .me-login-box {
    position: absolute;
    width: 400px;
    height: 320px;
    background-color: white;
    margin: 150px auto;
    left: 50%;
    padding: 30px;
    opacity: 0.8;
  }

  .me-login-box-radius {
    border-radius: 10px;
    box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
  }

  .me-login-box h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    vertical-align: middle;
  }

</style>
