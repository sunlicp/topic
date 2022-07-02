<template>
  <div class="container">
    <video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
        <source src="../../static/vedio/login.mp4" type="video/mp4">
    </video>
    <el-row style="height: 100%;opacity: 0.8;" type="flex" class="row-bg" justify="center" align="middle">
      <el-col :span="5" class="formStyle">
        <div style="color: #409EFF; font-size: 25px;font-weight:bold; text-align: center;padding: 30px 0">欢迎登录</div>
        <el-form ref="form" :rules="rules" :model="form" size="normal">

          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user-solid" v-model="form.username" clearable
                      placeholder="请输入用户名"></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" v-model="form.password" clearable placeholder="请输入密码"
                      show-password></el-input>
          </el-form-item>
        </el-form>
        <div style="display: flex;justify-content: space-around;padding-top: 10px">
          <el-button style="width: 48%" type="primary" round plain @click="login">登录</el-button>
          <el-button style="width: 48%" type="danger" round plain @click="toRegister">注册</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "Login",
  data() {
    return {
      form: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    login() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          const loading = this.$loading({
            fullscreen:true
          });
          this.$axios({
            url:'/user/login',
            method:'post',
            data:this.form
          }).then(({data})=>{
            if (data.code ==200) {
              sessionStorage.setItem("username", JSON.stringify(data.data.username))
              sessionStorage.setItem("avatar", JSON.stringify(data.data.picture))
              loading.close()
              this.$router.push("/layout")
              this.$message({
                type: "success",
                message: "登录成功",
                duration:1500,
                center: true
              })
            } else {
              loading.close()
              this.$router.push('/')
              this.$message({
                type: "error",
                message: data.message,
                center: true
              })
            }
          })
        }
      })
    },
    toRegister(){
      this.$router.push('/register')
    }
  }
}
</script>

<style scoped>
.row-bg {
  height: 80vh;
}
.container {
  width: 100%;
  height: 100vh;
  background-image: url("/static/background/login.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
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
</style>
