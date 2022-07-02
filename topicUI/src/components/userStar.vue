<template>
<div>
  <div class="right">
    <div class="right-item" v-for="item in blogList" :key="item.title">
      <div style="height: 50px;line-height: 50px;font-weight: bold;
        font-size: large;color: #0066ff;padding-left: 20px">{{item.title}}</div>
      <div style="padding:0 20px;color: #757474;height: 56px">{{item.contentText}}</div>
      <div style="margin: 0 20px;display: -webkit-flex;display: flex">
        <div style="width: 220px"><el-tag type="warning" >{{item.label}}</el-tag></div>
        <el-link :underline="false"
                 style="color: #ff4757;margin-left: 20px;font-size: medium;font-weight: bold"
                 @click.native="SearchByTitle(item.title)">查看文章
          <i class="el-icon-d-arrow-right"></i>
        </el-link>
      </div>
    </div>
    <div style="width: 100%;text-align: center">
                <el-pagination
            :page-size="pageSize"
            :pager-count="currentPage"
            background
            layout="prev, pager, next"
            @current-change="handleChange" @prev-click="preClick" @next-click="nextClick"
            :total="total">
          </el-pagination>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "userStar",
  data(){
    return{
      //文章简介列表
      blogList:[],
      //当前页码
      currentPage:1,
      //每页数量
      pageSize:4,
      //分页总数量
      total:0,
    }
  },
  mounted() {
    this.handleChange(1)
  },
  methods:{
    //处理页码变化
    handleChange(num){
      this.currentPage=num
      this.$axios({
        url: '/content/loadStarBlog',
        method: 'get',
        params:{
          username:JSON.parse(sessionStorage.getItem('username')),
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(({data})=>{
        if (data.code==200){
          this.total=data.data.total
          this.blogList=data.data.records
        }else {
          this.$message({
            message:'加载出错了',
            type:"error",
            center:true,
            duration:2000
          })
        }
      })
    },
    //查看收藏
    SearchByTitle(name){
      let routeUrl = this.$router.resolve({
        path:`/blog/${name}`
      })
      window.open(routeUrl.href, '_blank')
    },
    //用户点击上一页按钮改变当前页后触发
    preClick(){
      this.currentPage--
    },
    //用户点击下一页按钮改变当前页后触发
    nextClick(){
      this.currentPage++
    },
  }
}
</script>

<style scoped>
.right {
  height: 430px;
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 2%;
  display: -webkit-flex;
  display: flex;
  justify-content: space-around;
  align-content: space-around;
  flex-wrap: wrap;
}
.right-item {
  width: 45%;
  height: 33%;
  background-color: #ecf5ff;
  border-radius: 10px;
}
</style>
