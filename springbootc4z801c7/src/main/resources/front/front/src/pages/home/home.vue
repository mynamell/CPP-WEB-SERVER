<template>
<div class="home-preview" :style='{"margin":"0px auto","flexWrap":"wrap","flexDirection":"column","background":"none","display":"flex","width":"100%","justifyContent":"center"}'>




		


<!-- 商品推荐 -->
<div id="animate_recommendgonggaoxinxi" class="recommend animate__animated" :style='{"padding":"20px 0","margin":"0 auto","borderColor":"#ddd","background":"url(http://codegen.caihongy.cn/20230921/12de1b225a744f6a8b34b9e1979637b7.jpg),#fff","borderWidth":"1px 0","width":"100%","borderStyle":"solid","order":"3"}'>
	<div v-if="false" class="idea recommendIdea" :style='{"padding":"20px","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
		<div class="box1" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box2" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box3" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box4" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box5" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box6" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box7" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box8" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box9" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box10" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
	</div>
	
    <div class="title" :style='{"width":"100%","margin":"30px auto","lineHeight":"50px","textAlign":"center","background":"none"}'>
		<span :style='{"color":"#333","fontSize":"32px","lineHeight":"40px"}'>公告信息推荐</span>
	</div>
	
	
	
	
	
	
	
	
	
	




	<div v-if="gonggaoxinxiRecommend.length" class="list list13 index-pv1" :style='{"width":"100%","padding":"0 7%","background":"none","height":"auto"}'>
	  <div :style='{"cursor":"pointer","width":"100%","padding":"12px 10px","justifyContent":"center","display":"flex","height":"auto"}' class="top">
		<template v-for="item,index in gonggaoxinxiRecommend">
		<template v-if="index < 5">
		<img @click="toDetail('gonggaoxinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
		<img @click="toDetail('gonggaoxinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
		</template>
		</template>
	  </div>
	  <div v-if="gonggaoxinxiRecommend.length > 5" :style='{"cursor":"pointer","width":"100%","padding":"12px 10px","justifyContent":"center","display":"flex","height":"auto"}' class="center">
		<template v-for="item,index in gonggaoxinxiRecommend">
		<template v-if="index > 4 && index < 8">
		<img @click="toDetail('gonggaoxinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
		<img @click="toDetail('gonggaoxinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
		</template>
		</template>
	  </div>
	  <div v-if="gonggaoxinxiRecommend.length > 8" :style='{"cursor":"pointer","width":"100%","padding":"12px 10px","justifyContent":"center","display":"flex","height":"auto"}' class="bottom">
		<template v-for="item,index in gonggaoxinxiRecommend">
		<template v-if="index == 8">
		<img @click="toDetail('gonggaoxinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
		<img @click="toDetail('gonggaoxinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
		</template>
		</template>
	  </div>
	</div>



	
	<div @click="moreBtn('gonggaoxinxi')" :style='{"border":"1px solid #ddd","cursor":"pointer","margin":"20px auto","textAlign":"center","background":"#fff","display":"block","width":"120px","lineHeight":"36px"}'>
		<span :style='{"color":"#333","fontSize":"14px"}'>查看更多</span>
		<i :style='{"color":"#333","fontSize":"14px"}' class="icon iconfont icon-gengduo1"></i>
	</div>
	
</div>
<!-- 商品推荐 -->

<!-- 商品推荐 -->
<div id="animate_recommendchayexinxi" class="recommend animate__animated" :style='{"padding":"20px 0","margin":"0 auto","borderColor":"#ddd","background":"url(http://codegen.caihongy.cn/20230921/12de1b225a744f6a8b34b9e1979637b7.jpg),#fff","borderWidth":"1px 0","width":"100%","borderStyle":"solid","order":"3"}'>
	<div v-if="false" class="idea recommendIdea" :style='{"padding":"20px","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
		<div class="box1" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box2" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box3" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box4" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box5" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box6" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box7" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box8" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box9" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box10" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
	</div>
	
    <div class="title" :style='{"width":"100%","margin":"30px auto","lineHeight":"50px","textAlign":"center","background":"none"}'>
		<span :style='{"color":"#333","fontSize":"32px","lineHeight":"40px"}'>茶叶信息推荐</span>
	</div>
	
	
	
	
	
	
	
	
	
	




	<div v-if="chayexinxiRecommend.length" class="list list13 index-pv1" :style='{"width":"100%","padding":"0 7%","background":"none","height":"auto"}'>
	  <div :style='{"cursor":"pointer","width":"100%","padding":"12px 10px","justifyContent":"center","display":"flex","height":"auto"}' class="top">
		<template v-for="item,index in chayexinxiRecommend">
		<template v-if="index < 5">
		<img @click="toDetail('chayexinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
		<img @click="toDetail('chayexinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
		</template>
		</template>
	  </div>
	  <div v-if="chayexinxiRecommend.length > 5" :style='{"cursor":"pointer","width":"100%","padding":"12px 10px","justifyContent":"center","display":"flex","height":"auto"}' class="center">
		<template v-for="item,index in chayexinxiRecommend">
		<template v-if="index > 4 && index < 8">
		<img @click="toDetail('chayexinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
		<img @click="toDetail('chayexinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
		</template>
		</template>
	  </div>
	  <div v-if="chayexinxiRecommend.length > 8" :style='{"cursor":"pointer","width":"100%","padding":"12px 10px","justifyContent":"center","display":"flex","height":"auto"}' class="bottom">
		<template v-for="item,index in chayexinxiRecommend">
		<template v-if="index == 8">
		<img @click="toDetail('chayexinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
		<img @click="toDetail('chayexinxiDetail', item)" :name="item.id" :style='{"width":"18%","margin":"0 1%","objectFit":"cover","display":"block","clipPath":"polygon(100% 88%, 88% 100%, 0% 100%, 0% 0%, 100% 0%)","height":"200px"}' v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
		</template>
		</template>
	  </div>
	</div>



	
	<div @click="moreBtn('chayexinxi')" :style='{"border":"1px solid #ddd","cursor":"pointer","margin":"20px auto","textAlign":"center","background":"#fff","display":"block","width":"120px","lineHeight":"36px"}'>
		<span :style='{"color":"#333","fontSize":"14px"}'>查看更多</span>
		<i :style='{"color":"#333","fontSize":"14px"}' class="icon iconfont icon-gengduo1"></i>
	</div>
	
</div>
<!-- 商品推荐 -->

	
	<!-- 友链 -->
	<div id="friendly" class="friendly animate__animated" :style='{"padding":"0px 7%","margin":"0px auto 0","borderColor":"#ededed","background":"#fcfcfc","borderWidth":"1px 0 0","width":"100%","borderStyle":"solid","height":"auto","order":"12"}'>
	  <div :style='{"borderColor":"#ddd","margin":"20px auto 0","textAlign":"center","borderWidth":"0 0 0px","width":"100%","borderStyle":"solid","height":"auto"}'>
	    <div :style='{"padding":"0 10px","borderColor":"rgba(64, 158, 255)","margin":"0 0 -1px 0","color":"#333","borderWidth":"0 0 0px","display":"inline-block","lineHeight":"60px","fontSize":"32px","borderStyle":"solid"}'>合作客户</div>
	  </div>
	  <div :style='{"width":"100%","padding":"10px 0","flexWrap":"wrap","justifyContent":"center","display":"flex","height":"auto"}'>
	    <div :style='{"cursor":"pointer","margin":"0 2% 20px 0","alignItems":"center","flexDirection":"column","display":"flex","width":"auto","justifyContent":"center","height":"auto"}' v-for="item in friendLinkList" :key="item.id" @click="friendlinkClick(item.url)">
	      <img :style='{"border":"1px solid #ddd","padding":"10px","margin":"0 auto","objectFit":"contain","borderRadius":"0px","background":"#fff","display":"block","width":"auto","height":"120px"}' :src="item.picture?(baseUrl + item.picture.split(',')[0]): ''" alt="">
	      <div :style='{"overflow":"hidden","whiteSpace":"nowrap","color":"#333","textAlign":"center","width":"100%","lineHeight":"30px","fontSize":"14px","textOverflow":"ellipsis"}'>{{item.name}}</div>
	    </div>
	  </div>
	</div>
	<!-- 友链 -->
</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

  export default {
    //数据集合
    data() {
      return {
        baseUrl: '',
        newsList: [],
        gonggaoxinxiRecommend: [],
        chayexinxiRecommend: [],





		friendLinkList: [],
      }
    },
    created() {
		this.baseUrl = this.$config.baseUrl;
		this.getList();
		this.getFriendLinkList()
    },
	mounted() {
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
		
		this.swiperChanges()
	},
	beforeDestroy() {
	  window.removeEventListener('scroll', this.handleScroll)
	},
    //方法集合
    methods: {
		swiperChanges() {




















































































			setTimeout(()=>{










































			},250)
		},
		// 获取友链列表
		getFriendLinkList(){
			this.$http.get('friendlink/list', {params:{page: 1,limit: 100}}).then(res => {
			  if(res.data.code == 0) {
			    this.friendLinkList = res.data.data.list
			  }
			})
		},
		// 友链跳转
		friendlinkClick(url){
			if(url){
				window.open(url)
			}
		},

		listIndexClick12(index, name) {
			this['listIndex12' + name] = index
			this.getList()
			
			document.querySelectorAll('.lists .list12' + name + ' .list .item').forEach(el => {
			  el.classList.remove("active")
			})
			setTimeout(() => {
			  document.querySelectorAll('.lists .list12' + name + ' .list .item').forEach(el => {
			    el.classList.add("active")
			  })
			}, 1);
		},

		handleScroll() {
			let arr = [
				{id:'search',css:'animate__fadeInDown'},
				{id:'about',css:'animate__fadeInRight'},
				{id:'system',css:'animate__fadeInRight'},
				{id:'animate_recommendgonggaoxinxi',css:'animate__fadeInLeft'},
				{id:'animate_recommendchayexinxi',css:'animate__fadeInLeft'},
				{id:'msgs',css:'animate__fadeInDown'},
				{id:'friendly',css:'animate__fadeInUp'}
			]
			
			for (let i in arr) {
				let doc = document.getElementById(arr[i].id)
				if (doc) {
					let top = doc.offsetTop
					let win_top = window.innerHeight + window.pageYOffset
					// console.log(top,win_top)
					if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
						// console.log(doc)
						doc.classList.add(arr[i].css)
					}
				}
			}
		},
      preHttp(str) {
          return str && str.substr(0,4)=='http';
      },
		getList() {
			let autoSortUrl = "";
			let data = {}
          autoSortUrl = "gonggaoxinxi/autoSort";
			data = {
				page: 1,
				limit: 9,
			}
			this.$http.get(autoSortUrl, {params: data}).then(res => {
				if (res.data.code == 0) {
					this.gonggaoxinxiRecommend = res.data.data.list;
					
					
					// 商品列表样式五
					
				}
			});
          autoSortUrl = "chayexinxi/autoSort";
			data = {
				page: 1,
				limit: 9,
			}
			this.$http.get(autoSortUrl, {params: data}).then(res => {
				if (res.data.code == 0) {
					this.chayexinxiRecommend = res.data.data.list;
					
					
					// 商品列表样式五
					
				}
			});
			
		},
		toDetail(path, item) {
			this.$router.push({path: '/index/' + path, query: {id: item.id}});
		},
		moreBtn(path) {
			this.$router.push({path: '/index/' + path});
		}
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		// -------- search --------
		.search .select /deep/ .el-input__inner {
			border: 1px solid #ddd;
			border-radius: 4px;
			padding: 0 30px 0 10px;
			box-shadow: 0 0 0px rgba(64, 158, 255, .3);
			outline: none;
			color: rgba(64, 158, 255, 1);
			width: 180px;
			font-size: 14px;
			height: 36px;
		}
		
		.search .input /deep/ .el-input__inner {
			border: 1px solid #ddd;
			border-radius: 4px;
			padding: 0 10px;
			box-shadow: 0 0 0px rgba(64, 158, 255, .3);
			outline: none;
			color: rgba(64, 158, 255, 1);
			width: 480px;
			font-size: 14px;
			height: 36px;
		}
		// -------- search --------
		.recommend {
			.list3 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list3 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list3 .swiper-button-next {
				left: auto;
				right: 10px;
			}
			
			.list3 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list5 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
        }
        
        .list5 .swiper-button-next {
				left: auto;
				right: 10px;
			}
			
			.list5 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 {
				.swiper-slide-prev {
					position: relative;
					z-index: 3;
				}
		
				.swiper-slide-next {
					position: relative;
					z-index: 3;
				}
		
				.swiper-slide-active {
					position: relative;
					z-index: 5;
				}
			}
			
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.02) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
		}
		
		.news {
			.list3 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list3 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list3 .swiper-button-next {
				left: auto;
				right: 10px;
			}
			
			.list3 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list6 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list6 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list6 .swiper-button-next {
				left: auto;
				right: 10px;
			}
			
			.list6 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
		}
	
		.lists {
			.list3 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list3 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list3 .swiper-button-next {
				left: auto;
				right: 10px;
        }
        
        .list3 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list5 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 .swiper-button-next {
            left: auto;
            right: 10px;
			}
			
			.list5 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 {
				.swiper-slide-prev {
					position: relative;
					z-index: 3;
				}
		
				.swiper-slide-next {
					position: relative;
					z-index: 3;
				}
		
				.swiper-slide-active {
					position: relative;
					z-index: 5;
				}
			}
			
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
		}
	}
	
	.home-preview .news .list12 .left .list .item {
				cursor: pointer;
				padding: 0 0 0 10px;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 1px;
				position: relative;
				border-style: dashed;
				transition: 0.3s;
				height: auto;
			}
	.home-preview .news .list12 .left .list .item:hover {
				transform: translate3d(10px, 0px, 0px);
			}










	.home-preview .lists .list12 .tab .item {
				cursor: pointer;
				border: 1px solid #eee;
				padding: 0 20px;
				margin: 0 10px;
				color: #333;
				background: #fff;
				width: auto;
				font-size: 16px;
				line-height: 36px;
				height: auto;
			}
	
	.home-preview .lists .list12 .tab .item:hover {
				color: #fff;
				background: #1abc9e;
				border-color: #1abc9e;
				border-width: 1px;
				border-style: solid;
			}
	
	.home-preview .lists .list12 .tab .item.active {
				color: #fff;
				background: #1abc9e;
				border-color: #1abc9e;
				border-width: 1px;
				border-style: solid;
			}
	
	.home-preview .lists .list12 .tab .more {
				cursor: pointer;
				padding: 5px 10px;
				margin: 0 10px;
				color: #666;
				background: #fff;
				display: flex;
				line-height: 36px;
				align-items: center;
				height: 36px;
			}
	
	.home-preview .lists .list12 .tab .more:hover {
				color: red;
			}
	
	.home-preview .lists .list12 .item.active {
	  animation-name: mymove;
	
	  &:nth-of-type(1) {
	    animation-duration: 1s;
	  }
	  &:nth-of-type(2) {
	    animation-duration: 1.2s;
	  }
	  &:nth-of-type(3) {
	    animation-duration: 1.4s;
	  }
	  &:nth-of-type(4) {
	    animation-duration: 1.6s;
	  }
	  &:nth-of-type(5) {
	    animation-duration: 1.8s;
	  }
	  &:nth-of-type(6) {
	    animation-duration: 2s;
	  }
	}
	
	@keyframes mymove
	{
		from {top: 320px;}
		to {top: 0;}
	}
</style>
