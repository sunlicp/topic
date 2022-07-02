<template>
  <el-container style="height: 100vh;background-image: url('/static/background/background1.jpg');background-size: 100% 100%;
    background-repeat: no-repeat;">
    <el-aside width="200px">
      <el-dropdown trigger="click" placement="bottom"
                   style="margin-bottom: 15px;margin-top: 10px;width: 100%;">
        <el-button type="success" plain round icon="el-icon-edit-outline"
                   style="width: 100%;font-size: medium;font-weight: bold">新建 / 修改</el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item icon="el-icon-plus" @click.native="addBlog">新建文章</el-dropdown-item>
          <el-dropdown-item icon="el-icon-edit" @click.native="updateBlog">修改本篇</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
        <el-menu
          style="height: 90%;overflow: hidden;border-right: 1px solid #0066ff;border-radius: 40px"
          background-color="#0066ff"
          text-color="#fff"
          active-text-color="#ffd04b"
          :unique-opened="true"
          @open="handleOpen">
          <el-submenu v-for="item1 in menuList" :key="item1" :index="item1">
            <template slot="title">
              <i class="el-icon-s-flag" style="color: white"></i>
              <span>{{item1}}</span>
            </template>
            <el-menu-item @click.native="loadContent(item)" v-for="item in itemList" :key="item" :index="item">
              <i class="el-icon-reading" style="color: white"></i>
              <span slot="title">{{item}}</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <el-avatar :size="40" :src="circleUrl" style="margin: auto 20px"></el-avatar>
        <el-dropdown trigger="click">
          <span class="el-dropdown-link" style="color: white;font-weight: bold;font-size: medium;cursor: pointer">
            {{username}}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-discover" @click.native="$router.push('/community')">文章社区</el-dropdown-item>
              <el-dropdown-item icon="el-icon-user" @click.native="$router.push('/userHome')">个人资料</el-dropdown-item>
              <el-dropdown-item icon="el-icon-switch-button" @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
      </el-header>
      <el-main>
        <mavon-editor
          v-if="value !== ''"
          style="height: 580px"
          v-model="value"
          :subfield="false"
          :boxShadow="false"
          defaultOpen="preview"
          :toolbarsFlag="false"
          :codeStyle="codeStyle"
          :ishljs="true"
          :externalLink="externalLink"/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Layout",
  data(){
    return{
      //分类菜单
      menuList:[],
      //分类item列表
      itemList:[],
      //文章内容
      value:'',
      //选中的文章标题
      title:'',
      emptyTitle:'empty',
      //编辑器主题样式
      codeStyle: '',
      //外链样式需要配置的内容：
      externalLink: {
        markdown_css: function() {
          // 这是你的markdown css文件路径
          return '/static/mavon-editor/markdown/github-markdown.min.css';
        },
        hljs_js: function() {
          // 这是你的hljs文件路径
          return '/static/mavon-editor/highlightjs/highlight.min.js';
        },
        hljs_css: function(css) {
          // 这是你的代码高亮配色文件路径
          return '/static/mavon-editor/highlightjs/styles/' + css + '.min.css';
        },
        hljs_lang: function(lang) {
          // 这是你的代码高亮语言解析路径
          return '/static/mavon-editor/highlightjs/languages/' + lang + '.min.js';
        },
        katex_css: function() {
          // 这是你的katex配色方案路径路径
          return '/static/mavon-editor/katex/katex.min.css';
        },
        katex_js: function() {
          // 这是你的katex.js路径
          return '/static/mavon-editor/katex/katex.min.js';
        },
      }
    }
  },
  computed:{
    //用户名
    username(){
      return JSON.parse(sessionStorage.getItem('username'))
    },
    //用户头像
    circleUrl(){
      return JSON.parse(sessionStorage.getItem('avatar'))
    }
  },
  mounted() {
    if (JSON.parse(sessionStorage.getItem('username'))===null){
      this.$router.push('/')
    }
    this.loadMenuList()

    let that = this;
    that.codeStyle = "rainbow";
  },
  methods:{
    //进入创作文章页面
    addBlog(){
      this.$router.push(`/editor/${this.emptyTitle}`)
    },
    //修改文章内容
    updateBlog(){
      if (this.title===''){
        this.$notify.error({
          title: '错误',
          message: '请先打开一篇文章',
          duration:2000
        })
        return
      }
      this.$router.push(`/editor/${this.title}`)
    },
    //打开分类菜单并加载item
    handleOpen(key) {
      this.$axios({
        url:'/content/loadTitle',
        method:'get',
        params:{
          label:key,
          username:this.username
        }
      }).then(({data})=>{
        if (data.code==200){
          this.itemList=data.data
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
    //根据标题加载文章内容
    loadContent(item){
      this.$axios({
        url:'/content/loadContent',
        method:'get',
        params:{
          title:item
        }
      }).then(({data})=>{
        if (data.code==200){
          this.value=data.data
          this.title=item
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
    //加载分类菜单
    loadMenuList(){
      this.$axios({
        url:'/content/loadLabel',
        method:'get',
        params: {
          username:this.username
        }
      }).then(({data})=>{
        if (data.code==200){
          this.menuList=data.data
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
    //退出登录
    logout(){
      this.$confirm('请确认是否退出登录', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
        center: true,
        roundButton:true
      }).then(() => {
        sessionStorage.removeItem('user')
        this.$router.push('/')
      }).catch(() => {
      });
    }
  }
}
</script>

<style scoped>
.el-header {
  background-color: #ff4757;
  text-align: right;
  line-height: 60px;
  padding-right: 50px;
  border-radius: 20px;
  margin: 10px 10px;
  display: -webkit-flex;
  display: flex;
  align-content: center;
  justify-content: center;
}
.el-aside {
  margin: 10px 10px;
}
</style>
