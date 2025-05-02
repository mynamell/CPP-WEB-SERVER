import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import chayuanzhuguan from '@/views/modules/chayuanzhuguan/list'
    import xiaoshourenyuan from '@/views/modules/xiaoshourenyuan/list'
    import wuziruku from '@/views/modules/wuziruku/list'
    import caizhaixinxi from '@/views/modules/caizhaixinxi/list'
    import discusschayexinxi from '@/views/modules/discusschayexinxi/list'
    import friendlink from '@/views/modules/friendlink/list'
    import wuzichuku from '@/views/modules/wuzichuku/list'
    import jiagongyuangong from '@/views/modules/jiagongyuangong/list'
    import forum from '@/views/modules/forum/list'
    import kehu from '@/views/modules/kehu/list'
    import caizhaijihua from '@/views/modules/caizhaijihua/list'
    import chat from '@/views/modules/chat/list'
    import orders from '@/views/modules/orders/list'
    import chayexinxi from '@/views/modules/chayexinxi/list'
    import gonggaoxinxi from '@/views/modules/gonggaoxinxi/list'
    import zhongzhijihua from '@/views/modules/zhongzhijihua/list'
    import chayezhongzhi from '@/views/modules/chayezhongzhi/list'
    import wuzikucun from '@/views/modules/wuzikucun/list'
    import config from '@/views/modules/config/list'


//2.配置路由   注意：名字
export const routes = [{
    path: '/',
    name: '系统首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '系统首页',
      component: Home,
      meta: {icon:'', title:'center', affix: true}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/chayuanzhuguan',
        name: '茶园主管',
        component: chayuanzhuguan
      }
      ,{
	path: '/xiaoshourenyuan',
        name: '销售人员',
        component: xiaoshourenyuan
      }
      ,{
	path: '/wuziruku',
        name: '物资入库',
        component: wuziruku
      }
      ,{
	path: '/caizhaixinxi',
        name: '采摘信息',
        component: caizhaixinxi
      }
      ,{
	path: '/discusschayexinxi',
        name: '茶叶信息评论',
        component: discusschayexinxi
      }
      ,{
	path: '/friendlink',
        name: '友情链接',
        component: friendlink
      }
      ,{
	path: '/wuzichuku',
        name: '物资出库',
        component: wuzichuku
      }
      ,{
	path: '/jiagongyuangong',
        name: '加工员工',
        component: jiagongyuangong
      }
      ,{
	path: '/forum',
        name: '交流论坛',
        component: forum
      }
      ,{
	path: '/kehu',
        name: '客户',
        component: kehu
      }
      ,{
	path: '/caizhaijihua',
        name: '采摘计划',
        component: caizhaijihua
      }
      ,{
	path: '/chat',
        name: '在线留言',
        component: chat
      }
      ,{
        path: '/orders/:status',
        name: '订单管理',
        component: orders
      }
      ,{
	path: '/chayexinxi',
        name: '茶叶信息',
        component: chayexinxi
      }
      ,{
	path: '/gonggaoxinxi',
        name: '公告信息',
        component: gonggaoxinxi
      }
      ,{
	path: '/zhongzhijihua',
        name: '种植计划',
        component: zhongzhijihua
      }
      ,{
	path: '/chayezhongzhi',
        name: '茶叶种植',
        component: chayezhongzhi
      }
      ,{
	path: '/wuzikucun',
        name: '物资库存',
        component: wuzikucun
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default router;
