import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  // 侧边栏
  {
    path: '/',
    component: Layout,//组件
    redirect: '/dashboard',//重定向
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '后台管理系统', icon: 'dashboard' }
    }]
  },

  // 如果子路由正好等于一个就会默认将子路由作为根路由显示在侧边栏中，若不想这样，可以通过设置在根路由中设置alwaysShow: true来取消这一特性
  {
    path: '/example',
    component: Layout,
    // redirect: '/example/table',
    name: 'Example',
    meta: { title: '商品管理', icon: 'product' },
    children: [
      {
        path: 'products',
        name: 'products',
        component: () => import('@/views/products/index'),
        meta: { title: '商品列表', icon: 'list' }
      },

      {
        path: 'addProduct',
        name: 'addProduct',
        component: () => import('@/views/addProduct/index'),
        // component: () => import('@/views/tree/index'),
        meta: { title: '商品新增', icon: 'product-add' },

      },

      {
        path: 'goodsType',
        name: 'goodsType',
        component: () => import('@/views/products/goodsType'),
        // component: () => import('@/views/tree/index'),
        meta: { title: '分类管理', icon: 'table' },

      }
    ]
  },

  //订单管理
  {
    path: '/form',
    component: Layout,
    name:'Form',
    meta: { title: '订单管理', icon: 'order' },
    children: [
      {
        path: 'showOrder',
        name: 'ShowOrder',
        component: () => import('@/views/order/showOrder'),
        meta: { title: '订单列表', icon: 'list' }
      },
      {
        path: 'freeshop',
        name: 'freeshop',
        component: () => import('@/views/order/freeshop'),
        meta: { title: '试用列表', icon: 'list' }
      },
      {
        path: 'backOrder',
        name: 'backOrder',
        component: () => import('@/views/order/backOrder'),
        meta: { title: '退货管理', icon: 'order-return' },

      },
      {
        path: 'usertool',
        name: 'usertool',
        component: () => import('@/views/order/usertool'),
        meta: { title: '用户管理', icon: 'order-return' },
      },
    ]
  },
  {
    path: '/review',
    component: Layout,
    meta: { title: '评价管理', icon: 'sms' },
    children: [
      {
        path: 'myReview',
        name: 'myReview',
        component: () => import('@/views/review/myReview'),
        meta: { title: '我的评价', icon: 'example' }
      },
      {
        path: 'showReview',
        name: 'showReview',
        component: () => import('@/views/review/showReview'),
        meta: { title: '买家评价', icon: 'product-attr' }
      },
      {
        path: 'note',
        name: 'note',
        component: () => import('@/views/review/note'),
        meta: { title: '公告管理', icon: 'sms-hot' }
      },
      {
        path: 'liuyan',
        name: 'liuyan',
        component: () => import('@/views/review/liuyan'),
        meta: { title: '留言管理', icon: 'sms-hot' }
      },

      //查看订单评价详情
      {
        path: 'reviewDetail',
        hidden:true,
        name: 'reviewDetail',
        component: () => import('@/views/review/reviewDetail'),
        meta: { title: '评价详情', icon: 'form' }
      }
    ]
  },

  //查看订单详情
  {
    path: '/orderDetail',
    hidden: true ,
    component: Layout,
    children: [
      {
        path: 'index/:orderId',
        name: 'orderDetail',
        component: () => import('@/views/order/orderDetail'),
        meta: { title: '订单详情', icon: 'form' }
      }
    ]
  },

  //编辑商品
  {
    path: '/editProduct',
    hidden: true ,
    component: Layout,
    children: [
      {
        path: 'index/:id',
        name: 'editProduct',
        component: () => import('@/views/addProduct/index'),
        meta: { title: '编辑商品', icon: 'form' }
      }
    ]
  },

//个人中心

  {
    path: '/Center',
    hidden: true ,
    component: Layout,
    children: [
      {
        path: 'center',
        name: 'center',
        component: () => import('@/views/my/index'),
        meta: { title: '个人中心', icon: 'form' }
      }
    ]
  },


  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: '用户管理', icon: 'form' }
  //     }
  //   ]
  // },



  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'),
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       name: 'Menu2',
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },



//   当设置 true 的时候该路由不会在侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
// hidden: true // (默认 false)
// 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
