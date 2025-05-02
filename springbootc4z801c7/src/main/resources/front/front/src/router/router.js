import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import Storeup from '../pages/storeup/list'
import AddrList from '../pages/shop-address/list'
import AddrAdd from '../pages/shop-address/addOrUpdate'
import Order from '../pages/shop-order/list'
import OrderConfirm from '../pages/shop-order/confirm'
import Cart from '../pages/shop-cart/list'
import payList from '../pages/pay'

import kehuList from '../pages/kehu/list'
import kehuDetail from '../pages/kehu/detail'
import kehuAdd from '../pages/kehu/add'
import chayuanzhuguanList from '../pages/chayuanzhuguan/list'
import chayuanzhuguanDetail from '../pages/chayuanzhuguan/detail'
import chayuanzhuguanAdd from '../pages/chayuanzhuguan/add'
import jiagongyuangongList from '../pages/jiagongyuangong/list'
import jiagongyuangongDetail from '../pages/jiagongyuangong/detail'
import jiagongyuangongAdd from '../pages/jiagongyuangong/add'
import xiaoshourenyuanList from '../pages/xiaoshourenyuan/list'
import xiaoshourenyuanDetail from '../pages/xiaoshourenyuan/detail'
import xiaoshourenyuanAdd from '../pages/xiaoshourenyuan/add'
import gonggaoxinxiList from '../pages/gonggaoxinxi/list'
import gonggaoxinxiDetail from '../pages/gonggaoxinxi/detail'
import gonggaoxinxiAdd from '../pages/gonggaoxinxi/add'
import zhongzhijihuaList from '../pages/zhongzhijihua/list'
import zhongzhijihuaDetail from '../pages/zhongzhijihua/detail'
import zhongzhijihuaAdd from '../pages/zhongzhijihua/add'
import chayezhongzhiList from '../pages/chayezhongzhi/list'
import chayezhongzhiDetail from '../pages/chayezhongzhi/detail'
import chayezhongzhiAdd from '../pages/chayezhongzhi/add'
import wuzikucunList from '../pages/wuzikucun/list'
import wuzikucunDetail from '../pages/wuzikucun/detail'
import wuzikucunAdd from '../pages/wuzikucun/add'
import wuzichukuList from '../pages/wuzichuku/list'
import wuzichukuDetail from '../pages/wuzichuku/detail'
import wuzichukuAdd from '../pages/wuzichuku/add'
import wuzirukuList from '../pages/wuziruku/list'
import wuzirukuDetail from '../pages/wuziruku/detail'
import wuzirukuAdd from '../pages/wuziruku/add'
import caizhaijihuaList from '../pages/caizhaijihua/list'
import caizhaijihuaDetail from '../pages/caizhaijihua/detail'
import caizhaijihuaAdd from '../pages/caizhaijihua/add'
import caizhaixinxiList from '../pages/caizhaixinxi/list'
import caizhaixinxiDetail from '../pages/caizhaixinxi/detail'
import caizhaixinxiAdd from '../pages/caizhaixinxi/add'
import chayexinxiList from '../pages/chayexinxi/list'
import chayexinxiDetail from '../pages/chayexinxi/detail'
import chayexinxiAdd from '../pages/chayexinxi/add'
import friendlinkList from '../pages/friendlink/list'
import friendlinkDetail from '../pages/friendlink/detail'
import friendlinkAdd from '../pages/friendlink/add'
import discusschayexinxiList from '../pages/discusschayexinxi/list'
import discusschayexinxiDetail from '../pages/discusschayexinxi/detail'
import discusschayexinxiAdd from '../pages/discusschayexinxi/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'storeup',
					component: Storeup
				},
                {
                    path: 'shop-address/list',
                    component: AddrList
                },
                {
                    path: 'shop-address/addOrUpdate',
                    component: AddrAdd
                },
				{
					path: 'shop-order/order',
					component: Order
				},
				{
					path: 'cart',
					component: Cart
				},
				{
					path: 'shop-order/orderConfirm',
					component: OrderConfirm
				},
				{
					path: 'kehu',
					component: kehuList
				},
				{
					path: 'kehuDetail',
					component: kehuDetail
				},
				{
					path: 'kehuAdd',
					component: kehuAdd
				},
				{
					path: 'chayuanzhuguan',
					component: chayuanzhuguanList
				},
				{
					path: 'chayuanzhuguanDetail',
					component: chayuanzhuguanDetail
				},
				{
					path: 'chayuanzhuguanAdd',
					component: chayuanzhuguanAdd
				},
				{
					path: 'jiagongyuangong',
					component: jiagongyuangongList
				},
				{
					path: 'jiagongyuangongDetail',
					component: jiagongyuangongDetail
				},
				{
					path: 'jiagongyuangongAdd',
					component: jiagongyuangongAdd
				},
				{
					path: 'xiaoshourenyuan',
					component: xiaoshourenyuanList
				},
				{
					path: 'xiaoshourenyuanDetail',
					component: xiaoshourenyuanDetail
				},
				{
					path: 'xiaoshourenyuanAdd',
					component: xiaoshourenyuanAdd
				},
				{
					path: 'gonggaoxinxi',
					component: gonggaoxinxiList
				},
				{
					path: 'gonggaoxinxiDetail',
					component: gonggaoxinxiDetail
				},
				{
					path: 'gonggaoxinxiAdd',
					component: gonggaoxinxiAdd
				},
				{
					path: 'zhongzhijihua',
					component: zhongzhijihuaList
				},
				{
					path: 'zhongzhijihuaDetail',
					component: zhongzhijihuaDetail
				},
				{
					path: 'zhongzhijihuaAdd',
					component: zhongzhijihuaAdd
				},
				{
					path: 'chayezhongzhi',
					component: chayezhongzhiList
				},
				{
					path: 'chayezhongzhiDetail',
					component: chayezhongzhiDetail
				},
				{
					path: 'chayezhongzhiAdd',
					component: chayezhongzhiAdd
				},
				{
					path: 'wuzikucun',
					component: wuzikucunList
				},
				{
					path: 'wuzikucunDetail',
					component: wuzikucunDetail
				},
				{
					path: 'wuzikucunAdd',
					component: wuzikucunAdd
				},
				{
					path: 'wuzichuku',
					component: wuzichukuList
				},
				{
					path: 'wuzichukuDetail',
					component: wuzichukuDetail
				},
				{
					path: 'wuzichukuAdd',
					component: wuzichukuAdd
				},
				{
					path: 'wuziruku',
					component: wuzirukuList
				},
				{
					path: 'wuzirukuDetail',
					component: wuzirukuDetail
				},
				{
					path: 'wuzirukuAdd',
					component: wuzirukuAdd
				},
				{
					path: 'caizhaijihua',
					component: caizhaijihuaList
				},
				{
					path: 'caizhaijihuaDetail',
					component: caizhaijihuaDetail
				},
				{
					path: 'caizhaijihuaAdd',
					component: caizhaijihuaAdd
				},
				{
					path: 'caizhaixinxi',
					component: caizhaixinxiList
				},
				{
					path: 'caizhaixinxiDetail',
					component: caizhaixinxiDetail
				},
				{
					path: 'caizhaixinxiAdd',
					component: caizhaixinxiAdd
				},
				{
					path: 'chayexinxi',
					component: chayexinxiList
				},
				{
					path: 'chayexinxiDetail',
					component: chayexinxiDetail
				},
				{
					path: 'chayexinxiAdd',
					component: chayexinxiAdd
				},
				{
					path: 'friendlink',
					component: friendlinkList
				},
				{
					path: 'friendlinkDetail',
					component: friendlinkDetail
				},
				{
					path: 'friendlinkAdd',
					component: friendlinkAdd
				},
				{
					path: 'discusschayexinxi',
					component: discusschayexinxiList
				},
				{
					path: 'discusschayexinxiDetail',
					component: discusschayexinxiDetail
				},
				{
					path: 'discusschayexinxiAdd',
					component: discusschayexinxiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
