import Vue from 'vue'
import Router from 'vue-router'
import Layout from "../components/Layout";
import Editor from "../components/editor";
import Login from "../components/Login";
import Register from "../components/Register";
import UpdatePwd from "../components/UpdatePwd";
import Community from "../components/Community";
import Blog from "../components/Blog";
import UserHome from "../components/UserHome";
import userStar from "../components/userStar";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/layout',
      component:Layout
    },
    {
      path:'/editor/:title',
      component:Editor
    },
    {
      path:'/',
      component:Login
    },
    {
      path:'/register',
      component:Register
    },
    {
      path:'/community',
      component:Community
    },
    {
      path:'/blog/:title',
      component: Blog
    },
    {
      path:'/userHome',
      component:UserHome,
      redirect:'/userHome/userStar',
      children:[
        {
          path:'userStar',
          component:userStar
        },
        {
          path:'updatePwd',
          component:UpdatePwd
        },
      ]
    }
  ]
})
