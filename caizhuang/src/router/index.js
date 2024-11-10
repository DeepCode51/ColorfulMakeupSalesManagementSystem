import Vue from 'vue';
import Router from 'vue-router';
import Index from '@/components/Index';
import index from '@/views/index';
import Cookies from 'js-cookie';
import message from "../components/message";
//登录
const Login = resolve => require(['@/components/Login'], resolve);
//注册
const SignUp = resolve => require(['@/components/SignUp'], resolve);
const CheckPhone = resolve => require(['@/components/signUp/CheckPhone'], resolve);
const InputInfo = resolve => require(['@/components/signUp/InputInfo'], resolve);
const SignUpDone = resolve => require(['@/components/signUp/SignUpDone'], resolve);
//商品搜索页
const GoodsList = resolve => require(['@/components/GoodsList'], resolve);
//商品明细页
const GoodsDetail = resolve => require(['@/components/GoodsDetail'], resolve);
//添加购物车成功页
const ShoppingCart = resolve => require(['@/components/ShoppingCart'], resolve);
//查看购物车
const ShowCart = resolve => require(['@/components/ShowCart'], resolve);
//查看收藏夹
const MyCollection =resolve=>require(['@/components/MyCollection'], resolve);
//查看订单详情
const OrderDetail=resolve=>require(['@/components/OrderDetail'], resolve);

//评价页
const Review=resolve=>require(['@/components/Review'], resolve);

const Order = resolve => require(['@/components/Order'], resolve);
const Pay = resolve => require(['@/components/Pay'], resolve);
const PayDone = resolve => require(['@/components/PayDone'], resolve);
const Freeback = resolve => require(['@/components/Freeback'], resolve);
const Message = resolve => require(['@/components/message'], resolve);
//个人主页
const Home = resolve => require(['@/components/Home'], resolve);
//个人主页下的首页
const MyCenter = resolve => require(['@/components/home/MyCenter'], resolve);
//个人主页下的账户设置
const MySetting = resolve => require(['@/components/home/MySetting'], resolve);
//个人主页下的账户设置下的账户管理
const MyAccount =resolve=>require(['@/components/home/MyAccount'], resolve);
//个人主页下的账户设置下的个人资料(可以修改个人信息，头像，昵称，手机号 等等)
const UpdateAccount =resolve=>require(['@/components/home/UpdateAccount'], resolve);

const UpdatePassword =resolve=>require(['@/components/home/UpdatePassword'], resolve);
//个人主页下的账户设置下的地址管理，可以添加地址 删除地址 修改地址，查看地址 ，设置默认地址等等
const MyAddress = resolve => require(['@/components/home/MyAddress'], resolve);
//个人主页 下的首页下的订单管理
const ShowOrder = resolve => require(['@/components/home/ShowOrder'], resolve);
const IndexCenter = resolve => require(['@/components/home/Index'], resolve);

// const MyOrder = resolve => require(['@/components/home/MyOrder'], resolve);
//我的评价
const MyReview = resolve => require(['@/components/home/MyReview'], resolve);

const Merchant = resolve => require(['@/components/Merchant'], resolve);

Vue.use(Router);
// 重复点击导航时，控制台出现报错 ，虽然不影响功能使用，但也不能视而不见。以下代码解决路由重复控制台报错的问题
const VueRouterPush = Router.prototype.push
Router.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}

// export default new Router({
const router=new Router({
  routes: [
    {
      path: '/', // 首页
      name: 'Index',
      component: index
    },
    {
      path: '/Login', // 登录
      name: 'Login',
      component: Login
    },
    {
      path: '/SignUp', // 注册
      // name: 'SignUp',
      //因为当某个路由有子级路由的时候，这时候父级路由需要一个默认的路由，所以父级路由不能定义name属性。
      //去掉父路由的name属性
      component: SignUp,
      // component:Index,
      children: [
        {
          path: '/',
          name: 'index',
          component: CheckPhone
        },

        {
          path: 'inputInfo',
          name: 'InputInfo',
          component: InputInfo
        },
        {
          path: 'signUpDone',
          name: 'SignUpDone',
          component: SignUpDone
        }
      ]
    },
    {
      path: '/goodsList', // 商品列表
      name: 'GoodsList',
      component: GoodsList

    },
    {
      path: '/goodsDetail', // 商品详情
      name: 'GoodsDetail',
      component: GoodsDetail

    },
    {
      path: '/shoppingCart', // 加入购物车成功的跳转
      name: 'ShoppingCart',
      component: ShoppingCart,
      meta:{
        auth:true//添加字段判断该页面是否需要登录
      }
    },
    {
      path: '/showcart', // 查看购物车
      name: 'ShowCart',
      component: ShowCart,
      meta:{
        auth:true//添加字段判断该页面是否需要登录
      }
    },
    {
      path: '/myCollection', // 查看收藏夹
      name: 'MyCollection',
      component: MyCollection,
      meta:{
        auth:true//添加字段判断该页面是否需要登录
      }
    },
    {
      path: '/order', // 订单页面
      name: 'Order',
      component: Order
    },
    {
      path: '/orderDetail', // 订单详情
      name: 'OrderDetail',
      component: OrderDetail
    },
    {
      path: '/review', // 评价页
      name: 'Review',
      component: Review
    },
    {
      path: '/pay', // 支付页面
      name: 'Pay',
      component: Pay
    },
    {
      path: '/payDone', // 支付成功页面
      name: 'PayDone',
      component: PayDone
    },
    {
      path: '/freeback', // 反馈页面
      name: 'Freeback',
      component: Freeback
    },
    {
      path: '/message', // 反馈页面
      name: 'message',
      component: Message
    },
    {
      path: '/home', // 主页(一级路由)
      // name: 'Home',
      component: Home,
      children: [
        // {
        //   path: '/',
        //   name: 'HomeIndex',
        //   component: MyOrder
        // },
        //这个是一进去设置页的首选项 ，（二级路由）
        {
          path: '/',
          name: 'HomeIndex',
          redirect: {name: 'IndexCenter'},
          component: MyCenter,
          children:[
            {
              path: 'index',
              name: 'IndexCenter',
              component:IndexCenter,
            },
            //查看订单
            {
              path: 'showOrder',
              name: 'ShowOrder',
              component:ShowOrder,
            },
            //查看评价
            {
              path: 'myReview',
              name: 'MyReview',
              component:MyReview,
            },
          ]

        },
        //这是/home/mySetting 个人设置页
        {
          path: 'mySetting',
          // name: 'setting',
          component: MySetting,
          redirect: {name: 'MyAccount'},
          children:[
            // 账户设置（三级路由）
            {
              path: 'myAccount',
              name: 'MyAccount',
              component:MyAccount,
            },
             // 个人资料
             {
              path: 'updateAccount',
              name: 'UpdateAccount',
              component:UpdateAccount,
            },
            //修改密码
            {
              path: 'updatePassword',
              name: 'UpdatePassword',
              component: UpdatePassword
            },
            //收货地址
            {
              path: 'myAddress',
              name: 'MyAddress',
              component: MyAddress
            },
          ]
        },

        // {
        //   path: 'myOrder',
        //   name: 'MyOrder',
        //   component: MyOrder
        // },

      ]
    },
    {
      path: '/merchant',
      name: 'Merchant',
      component: Merchant
    }
  ]
});
router.beforeEach((to, from, next) => {
  // to要跳转到的路径
  // from从哪个路径来
  // next往下执行的回调
  // 在cookies里面取token
  let token=Cookies.get('token1');
  // 判断该页面是否需要登录
  if(to.meta.auth){
     // 如果token存在直接跳转
     if(token){
       next()
     }else{
         // 否则跳转到login登录页面
         next({
           path:'/login',
           query:{
             redirect: to.fullPath
           }
         })
     }

  }else{
    // 如果不需要登录，则直接跳转到对应页面
    next()
  }

})

export default router

