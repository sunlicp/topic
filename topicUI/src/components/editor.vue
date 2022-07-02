<template>
  <div style="background-image: url('/static/background/background2.jpg');height: 100vh;background-size: 100% 100%; background-repeat: no-repeat;">
    <div class="header">
      <el-button style="font-size: large;color: #0066ff" type="text" icon="el-icon-arrow-left"
                 @click="goBack">返回</el-button>
      <el-input class="input" v-model="titleValue" placeholder="请输入文章标题"></el-input>
      <el-select class="select" v-model="selectValue" filterable  placeholder="请输入或选择文章分类">
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.name"
          :value="item.name">
        </el-option>
      </el-select>
      <el-button style="height: 70%;margin: auto 1px;font-size: medium"
                 type="primary" round plain icon="el-icon-folder-checked"
                 @click="saveContent">保存文章</el-button>
    </div>
    <mavon-editor
      style="height: 600px;width: 95%;margin: 10px auto"
      :toolbars="markdownOption"
      :codeStyle="codeStyle"
      :ishljs="true"
      :externalLink="externalLink"
      v-model="value"
      @imgAdd="$imgAdd"
      ref="md"
      @save="saveContent"/>
  </div>

</template>

<script>

export default {
  name: "editor",
  data(){
    return{
      //文章标题
      titleValue:'',
      //文章内容
      value:'',
      //文章id
      blogId:'',
      //分类列表
      options:[],
      //选择的分类
      selectValue:'',
      //编辑器配置选项
      markdownOption:{
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: false, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: false, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: false, // 单双栏模式
        preview: true, // 预览
      },
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
    username(){
      return JSON.parse(sessionStorage.getItem('username'))
    }
  },
  mounted() {
    if (JSON.parse(sessionStorage.getItem('username'))===null){
      this.$router.push('/')
    }
    this.loadLabelList()
    let that = this;
    that.codeStyle = "github";

    if (this.$route.params.title!=='empty'){
      this.loadBlogDetails()
    }
  },
  methods:{
    //如果修改文章，则加载文章内容
    loadBlogDetails(){
      this.$axios({
        url:'/content/loadBlogOnUpd',
        method:'get',
        params:{
          title:this.$route.params.title
        }
      }).then(({data})=>{
        if (data.code==200){
          this.blogId=data.data.id
          this.value=data.data.contentText
          this.titleValue=data.data.title
          this.selectValue=data.data.label
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
    //返回上一页
    goBack(){
      this.$confirm('请确认是否已保存文章, 是否返回?', '提示', {
        confirmButtonText: '返回',
        cancelButtonText: '取消',
        type: 'warning',
        center: true,
        roundButton:true
      }).then(() => {
        this.$router.push('/layout')
      }).catch(() => {
      });
    },

    //将文章中的图片上传服务器
    $imgAdd(pos, $file){
      // 第一步.将图片上传到服务器.
      let formdata = new FormData();
      formdata.append('picture', $file);
      this.$axios({
        url: '/uploadImg',
        method: 'post',
        data: formdata,
        headers: { 'Content-Type': 'multipart/form-data' },
      }).then((url) => {
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        /**
         * $vm 指为mavonEditor实例，可以通过如下两种方式获取
         * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
         * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
         */
        this.$refs.md.$img2Url(pos, url.data.t);
      })
    },
    //保存文章内容
    saveContent(){
      if (this.titleValue===null || this.titleValue===''){
        if (this.selectValue===null || this.titleValue===''){
          this.$notify.error({
            title: '错误',
            message: '保存失败，请输入标题并选择文章分类',
            duration:2000
          });
          return
        }
      }
      this.$axios({
        url: '/content/save',
        method: 'post',
        data: {
          id:this.blogId,
          title:this.titleValue,
          contentText: this.value,
          label:this.selectValue,
          userName:this.username
        }
      }).then(({data})=>{
        if (data.code==200){
          this.blogId=data.data
          this.$message({
            message:'保存成功',
            type:'success',
            duration:2000,
            center:true
          })
        }else {
         this.$message({
           message:data.message,
           type:'error',
           duration:2000,
           center:true
         })
        }
      })
    }
  }
}
</script>

<style scoped>
.header {
  background-color: #000;
  height: 60px;
  width: 100%;
  display: flex;
  justify-content: space-around;
  align-content: center;
}
.input {
  width: 50%;
  height: 60%;
  margin: auto 1px;
}
.select {
  height: 60%;
  margin: auto 1px;
}
</style>
