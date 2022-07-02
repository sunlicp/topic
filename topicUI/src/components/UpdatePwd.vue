<template>
<div >
  <div style="width: 300px;margin: 20px auto;background-color: #0066ff;border-radius: 20px;padding: 5px 10px;">
    <div style="color: #fff; font-size: 25px;font-weight: bold; text-align: center;padding: 30px 0">修改密码</div>
    <el-form ref="form" :model="form" size="normal" >
      <el-form-item >
        <el-input prefix-icon="el-icon-lock" v-model="form.password" clearable placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item >
        <el-input prefix-icon="el-icon-lock" v-model="form.confirm" clearable placeholder="请确认密码" show-password></el-input>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button style="width: 96%" round type="danger" plain @click="updatePwd">修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
export default {
  name: "UpdatePwd",
  data(){
    return{
      form:{}
    }
  },
  mounted() {
    if (JSON.parse(sessionStorage.getItem('username'))===null){
      this.$router.push('/')
    }
  },
  methods:{
    updatePwd(){
      if (this.form.password !== this.form.confirm){
        this.$message({
          type: "error",
          message: "两次输入密码不一致",
          duration:1500,
          center:true
        })
        return
      }
      this.$axios({
        url:'/user/update',
        method:'post',
        data:{
          username:JSON.parse(sessionStorage.getItem('username')),
          password:this.form.password
        }
      }).then(({data})=>{
        if (data.code==200){
          this.$message({
            type: "success",
            message: "修改成功",
            duration:1000,
            center:true
          })
          sessionStorage.removeItem('username')
          sessionStorage.removeItem('avatar')
          this.$router.push('/')
        }else {
          this.$message({
            type: "error",
            message: data.message,
            duration:1000,
            center:true
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
