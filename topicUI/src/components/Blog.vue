<template>
<div style="background-image: url('/static/background/background2.jpg');background-size: 100% 100%;
    background-repeat: no-repeat;">
  <div class="header">
    <el-avatar :size="40" :src="authorPicture" style="margin-left: 80%;margin-right: 20px"></el-avatar>
    <span class="el-dropdown-link"
          style="color: #0066ff;font-weight: bold;font-size: large">
            {{author}}</span>
  </div>
  <mavon-editor v-if="value !== ''" style="width: 70%;margin: 0 auto" v-model="value"
                :subfield="false" :boxShadow="false" defaultOpen="preview" :toolbarsFlag="false"
                :codeStyle="codeStyle" :ishljs="true" :externalLink="externalLink"/>
  <div style="width: 70%;margin: 0 auto;background-color: #fbfbfb;text-align: right">
    <el-button style="margin-bottom: 15px" type="danger" round plain
               icon="el-icon-star-off" @click="clickStar">{{star? '取消收藏':'收藏'}}</el-button>
    <el-button type="success" round plain icon="el-icon-chat-dot-round"
               style="margin-left: 3%;margin-right: 4%;margin-bottom: 15px" @click="openComment">评论</el-button>
  </div>
  <div v-if="commentArea" style="width: 70%;margin: 10px auto;text-align: center;
  color: #0066ff;font-weight: bold;font-size: large;">评论</div>
  <div v-if="commentArea" style="width: 70%;margin: 0 auto">
    <comment :commentWidth="'100%'" @doSend="send" :avatar="circleUrl"
             :commentList="cList" :authorId="authorId" :commentNum="totalComment"
             @doChidSend="sendChild"></comment>
  </div>
  <div v-if="commentArea" style="width: 70%;margin: 0px auto;text-align: center">
    <el-pagination background layout="prev, pager, next" :pager-count="5" :page-size="pageSize"
                   :total="total" :hide-on-single-page="true" :current-page="currentPage"
                   @current-change="handleChange" @prev-click="preClick" @next-click="nextClick">
    </el-pagination>
  </div>
  <el-tooltip placement="top" content="回到顶部">
    <back-to-top :custom-style="myBackToTopStyle"
                 :visibility-height="300" :back-position="0" transition-name="fade"/>
  </el-tooltip>
</div>
</template>

<script>
import  BackToTop from '../../node_modules/element-ui/packages/backtop/index'
import comment from 'bright-comment'
export default {
  name: "Blog",
  components:{
    comment,
    BackToTop
  },
  data(){
    return{
      //记录文章是否被用户收藏
      star:false,
      //作者头像
      authorPicture:'',
      //作者名
      author:'',
      //作者id
      authorId:'',
      //文章标题
      title:'',
      //文章内容
      value:'',
      //评论内容
      commentValue:'',
      //评论总数
      totalComment:0,
      //控制评论区显示
      commentArea:false,
      //评论列表
      cList:[],
      //当前页码
      currentPage:1,
      //每页数量
      pageSize:2,
      //分页总数量
      total:0,
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
      },
      //回到顶部按钮设置
      myBackToTopStyle: {
        right: '50px',
        bottom: '50px',
        width: '40px',
        height: '40px',
        borderRadius: '4px',
        lineHeight: '45px', // 请保持与高度一致以垂直居中
        background: '#b8bade'// 按钮的背景颜色
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
    const loading = this.$loading({
      fullscreen:true
    });
    this.title=this.$route.params.title
    this.loadContent()
    this.getAuthor()
    this.judgeStar()

    let that = this;
    that.codeStyle = "rainbow";

    loading.close()
  },
  methods:{

    //判断文章是否被收藏
    judgeStar(){
      this.$axios({
        url:'/content/judgeStar',
        method:'get',
        params:{
          title:this.title,
          username:this.username
        }
      }).then(({data})=>{
        if (data.code==200){
          this.star=data.data
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

    //点击收藏按钮
    clickStar(){
      this.$axios({
        url:'/content/handleStar',
        method:'get',
        params:{
          blogTitle:this.title,
          username:this.username
        }
      }).then(({data})=>{
        if (data.code==200){
          this.judgeStar()
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
    loadContent(){
      this.$axios({
        url:'/content/loadContent',
        method:'get',
        params:{
          title:this.title
        }
      }).then(({data})=>{
        if (data.code==200){
          this.value=data.data
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

    //根据文章标题查询作者名及id头像
    getAuthor(){
      this.$axios({
        url:'/user/getName',
        method:'get',
        params:{
          title:this.title
        }
      }).then(({data})=>{
        if (data.code==200){
          this.authorId=data.data.id
          this.author=data.data.username
          this.authorPicture=data.data.picture
        }else {
          this.$message({
            message:'加载作者名出错了',
            type:"error",
            center:true,
            duration:2000
          })
        }
      })
    },

    //打开评论区并加载数据
    openComment(){
      this.loadTotalComNum()
      this.loadComment()
      this.commentArea=true
    },

    //加载评论总数
    loadTotalComNum(){
      this.$axios({
        url:'/comment/totalNum',
        method:'get',
        params:{
          title:this.title,
        }
      }).then(({data})=>{
        if (data.code==200){
          this.totalComment=data.data
        }else {
          this.$message({
            message:'评论数目出错了',
            type:"error",
            center:true,
            duration:2000
          })
        }
      })
    },
    //加载评论
    loadComment(){
      this.$axios({
        url:'/comment/load',
        method:'get',
        params:{
          title:this.title,
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(({data})=>{
        if (data.code==200){
          this.total=data.data.total
          this.cList=data.data.records
          this.$notify.success({
            title: '成功',
            message: '成功加载评论',
            showClose: false,
            duration:1500
          });
        }else {
          this.$message({
            message:'加载评论出错了',
            type:"error",
            center:true,
            duration:2000
          })
        }
      })
    },
    //发送评论
    send(content){
      this.$axios({
        url:'/comment/save',
        method:'post',
        data:{
          content:content,
          username:this.username,
          title:this.title,
        }
      }).then(({data})=>{
        if (data.code==200){
          this.$notify.success({
            title: '成功',
            message: '发送成功',
            showClose: false,
            duration:1500
          });
          this.openComment()
        }else {
          this.$message({
            message:'发送出错了',
            type:"error",
            center:true,
            duration:2000
          })
        }
      })
    },

    //发送子评论
    sendChild(content,id,fatherId){
      this.$axios({
        url:'/comment/save',
        method:'post',
        data:{
          content:content,
          targetId:id,
          username:this.username,
          title:this.title,
          fatherId:fatherId
        }
      }).then(({data})=>{
        if (data.code==200){
          this.$notify.success({
            title: '成功',
            message: '发送成功',
            showClose: false,
            duration:1500
          });
          this.openComment()
        }else {
          this.$message({
            message:'加载作者名出错了',
            type:"error",
            center:true,
            duration:2000
          })
        }
      })
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
      this.$axios({
        url:'/comment/load',
        method:'get',
        params:{
          title:this.title,
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(({data})=>{
        if (data.code==200){
          this.total=data.data.total
          this.cList=data.data.records
        }else {
          this.$message({
            message:'加载评论出错了',
            type:"error",
            center:true,
            duration:1500
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
  width: 70%;
  margin:0 auto;
  height: 60px;
  display: -webkit-flex;
  display: flex;
  align-items: center;
}
</style>
