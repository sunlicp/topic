<template>
  <div style="background-image: url('/static/background/background2.jpg');height: 100vh;background-size: 100% 100%;
    background-repeat: no-repeat;">
    <div class="header">
      <el-button style="font-size: large;color: #0066ff;margin-left:30px;margin-top: 4px" type="text" icon="el-icon-arrow-left"
                 @click="$router.push('/layout')">返回首页</el-button>
      <el-select class="select" v-model="selectValue" filterable
                 placeholder="请输入或选择文章分类进行搜索" style="margin-left: 30%;width: 300px">
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.name"
          :value="item.name">
        </el-option>
      </el-select>
      <el-button style="height: 80%;margin-left: 80px;font-size: medium;"
                 type="primary" round plain icon="el-icon-search"
                 @click="searchContent">搜索文章</el-button>
    </div>
    <div class="container">
      <div class="left">
        <div class="left1">
          <div style="height: 50px;line-height: 50px;font-weight: bold;
          font-size: large;border-bottom: #fff 1px solid;color: #fff;padding-left: 10px" >分类热榜</div>
          <div style="display: -webkit-flex;display: flex;margin-top: 10px" v-for="(item,index) in hotLabelList" :key="index">
            <div style="margin-left: 5px;color:#fff" v-text="index+1"></div>
            <el-link style="margin-left: 40px;color: #fff" v-text="item" :underline="false"
                     @click.native="hotSearchByLabel(item)"></el-link>
          </div>
        </div>
        <div class="left2">
          <div style="height: 50px;line-height: 50px;font-weight: bold;
          font-size: large;border-bottom: #fff 1px solid;color: #fff;padding-left: 10px" >文章热榜</div>
          <div style="display: -webkit-flex;display: flex;margin-top: 10px" v-for="(item,index) in hotTitleList" :key="index">
            <div style="margin-left: 5px;color: #fff" v-text="index+1"></div>
            <el-link style="margin-left: 40px;color: #fff" v-text="item" :underline="false"
                     @click.native="hotSearchByTitle(item)"></el-link>
          </div>
        </div>
      </div>
      <div class="right">
        <div class="right-item" v-for="item in blogList" :key="item.title">
          <div style="height: 50px;line-height: 50px;font-weight: bold;
        font-size: large;color: #0066ff;padding-left: 20px">{{item.title}}</div>
          <div style="padding:0 20px;color: #757474;height: 56px">{{item.contentText}}</div>
          <div style="margin: 0 20px;display: -webkit-flex;display: flex">
            <div style="width: 220px"><el-tag type="warning" >{{item.label}}</el-tag></div>
            <el-link :underline="false"
                     style="color: #ff4757;margin-left: 20px;font-size: medium;font-weight: bold"
                     @click.native="hotSearchByTitle(item.title)">查看文章
              <i class="el-icon-d-arrow-right"></i>
            </el-link>
          </div>
        </div>
        <div style="width: 100%;text-align: center">
          <!-- <el-pagination background layout="prev, pager, next" :pager-count="5" :page-size="pageSize"
                         :total="total" :hide-on-single-page="true" :current-page="currentPage"
                         @current-change="handleChange" @prev-click="preClick" @next-click="nextClick">
          </el-pagination> -->
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
  </div>
</template>

<script>
export default {
  name: "Community",
  data(){
    return{
      //分类列表
      options:[],
      //选择的分类
      selectValue:'',
      //选择的分类热榜值
      hotLabel:'',
      //文章简介列表
      blogList:[],
      //当前页码
      currentPage:1,
      //每页数量
      pageSize:6,
      //分页总数量
      total:0,
      //分类热榜
      hotLabelList:[],
      //文章热榜
      hotTitleList:[]
    }
  },
  mounted() {
    if (JSON.parse(sessionStorage.getItem('username'))===null){
      this.$router.push('/')
    }
    const loading = this.$loading({
      fullscreen:true
    });
    this.loadLabelList()
    this.loadBlogList()
    this.loadHotLabelList()
    this.loadHotTitleList()
    loading.close()
  },
  methods:{
    //根据输入选择的分类搜索内容
    searchContent(){
      this.hotLabel=''
      this.currentPage=1
      this.handleChange(this.currentPage)
    },
    //用户点击上一页按钮改变当前页后触发
    preClick(){
      this.currentPage--
    },
    //用户点击下一页按钮改变当前页后触发
    nextClick(){
      this.currentPage++
    },
    //处理页码变化
    handleChange(num){
      this.currentPage=num
      let label=this.selectValue!==''? this.selectValue:this.hotLabel
      this.$axios({
        url: '/content/loadBlogListByLabel',
        method: 'get',
        params:{
          label:label,
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
    //加载分类热榜
    loadHotLabelList(){
      this.$axios({
        url: '/content/loadHotLabel',
        method: 'get'
      }).then(({data})=>{
        if (data.code==200){
          this.hotLabelList=data.data
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
    //加载文章热榜
    loadHotTitleList(){
      this.$axios({
        url: '/content/loadHotTitle',
        method: 'get'
      }).then(({data})=>{
        if (data.code==200){
          this.hotTitleList=data.data
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
    //点击分类热搜榜查看相关文章
    hotSearchByLabel(name){
      this.selectValue=''
      this.hotLabel=name
      this.currentPage=1
      this.handleChange(this.currentPage)
    },
    //点击文章热榜或查看文章进入文章展示页面
    hotSearchByTitle(name){
      let routeUrl = this.$router.resolve({
        path:`/blog/${name}`
      })
      window.open(routeUrl.href, '_blank')
    },
    //加载排名前六的文章列表
    loadBlogList(){
      this.$axios({
        url: '/content/loadBlogList',
        method: 'get'
      }).then(({data})=>{
        if (data.code==200){
          this.blogList=data.data
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
    //加载文章分类选项列表
    loadLabelList(){
      this.$axios({
        url: '/label/loadList',
        method: 'get'
      }).then(({data})=>{
        if (data.code==200){
          this.options=data.data
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
  width: 99%;
  height: calc(100vh - 60px);
  margin: 0 auto;
  display: -webkit-flex;
  display: flex;
  justify-content: space-between;
}
.left {
  height: 100%;
  width: 25%;
  margin-left: 10%;
}
.left1 {
  width: 70%;
  height: 40%;
  margin: 25px auto;
  background-color: #ff4757;
  border-radius: 20px;
}
.left2 {
  width: 70%;
  height: 40%;
  margin: 30px auto;
  background-color: #0066ff;
  border-radius: 20px;
}
.right {
  height: 90%;
  width: 55%;
  margin-right: 10%;
  margin-top: 2%;
  display: -webkit-flex;
  display: flex;
  justify-content: space-around;
  align-content: space-around;
  flex-wrap: wrap;
}
.right-item {
  width: 45%;
  height: 25%;
  background-color: #ecf5ff;
  border-radius: 10px;
}
</style>
