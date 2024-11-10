<template>
	<div>
		<BackTop></BackTop>
		<!-- <BackTop :height="100" :bottom="200">
        	<div class="top">返回顶端</div>
    	</BackTop> -->
		<Affix>
			 <Search></Search>
    	</Affix>

		<!-- 首页轮播图 -->
		<!-- START REVOLUTION SLIDER 5.0 -->
		<div class="newbox">
			<div id="home-revslider" class="slider-section no-padding">

					<div class="rev_slider_wrapper">
						<div id="home-slider1" class="rev_slider" data-version="5.0">
							<ul>
								<li data-transition="zoomout" data-slotamount="default"  data-easein="easeInOut" data-easeout="easeInOut" data-masterspeed="2000" data-rotate="0"  data-fstransition="fade" data-fsmasterspeed="1500" data-fsslotamount="7">
									<img :src="imgUrl" alt="slider" data-bgposition="center center" data-bgfit="cover" data-bgrepeat="no-repeat" data-bgparallax="10" class="rev-slidebg" data-no-retina>
									<div class="tp-caption NotGeneric-Title tp-resizeme rs-parallaxlevel-0" id="slide-layer-1"
										data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
										data-y="['middle','middle','middle','middle']" data-voffset="['-200','-130','-110','-80']"
										data-fontsize="['38','30','25','16']"
										data-lineheight="['24','24','24','24']"
										data-width="none"
										data-height="none"
										data-whitespace="nowrap"
										data-transform_idle="o:1;"
										data-transform_in="y:-50px;opacity:0;s:1000;e:Power4.easeOut;"
										data-transform_out="opacity:0;s:3000;e:Power4.easeIn;s:3000;e:Power4.easeIn;"
										data-mask_in="x:0px;y:0px;s:inherit;e:inherit;"
										data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
										data-start="1000"
										data-splitin="chars"
										data-splitout="none"
										data-responsive_offset="on"
										data-elementdelay="0.05"
										style="z-index: 5; white-space: nowrap; letter-spacing: 3.04px; color:#333; font-weight: 700; font-family: 'Montserrat', sans-serif; text-transform: uppercase;">
									</div>
									<div class="tp-caption NotGeneric-Title tp-resizeme rs-parallaxlevel-0" id="slide-layer-2"
										data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
										data-y="['middle','middle','middle','middle']" data-voffset="['-135','-80','-60','-50']"
										data-fontsize="['18','18','18','14']"
										data-lineheight="['26','26','26','26']"
										data-width="['750','750','738','450']"
										data-height="none"
										data-whitespace="nowrap"
										data-transform_idle="o:1;"
										data-transform_in="x:[105%];z:0;rX:45deg;rY:0deg;rZ:90deg;sX:1;sY:1;skX:0;skY:0;s:2000;e:Power4.easeInOut;"
										data-transform_out="y:[100%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
										data-mask_in="x:0px;y:0px;s:inherit;e:inherit;"
										data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
										data-start="1000"
										data-splitin="chars"
										data-splitout="none"
										data-responsive_offset="on"
										data-elementdelay="0.05"
										style="z-index: 5; white-space: nowrap; letter-spacing: 1.5px; color:#777; font-weight: normal; font-family: 'Lato', sans-serif;">
									</div>
									<div class="tp-caption NotGeneric-Title tp-resizeme rs-parallaxlevel-0" id="slide-layer-3"
										data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
										data-y="['middle','middle','middle','middle']" data-voffset="['-110','-50','-35','-30']"
										data-fontsize="['18','18','18','14']"
										data-lineheight="['26','26','26','26']"
										data-height="none"
										data-whitespace="nowrap"
										data-transform_idle="o:1;"
										data-transform_in="x:[105%];z:0;rX:45deg;rY:0deg;rZ:90deg;sX:1;sY:1;skX:0;skY:0;s:2000;e:Power4.easeInOut;"
										data-transform_out="y:[100%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
										data-mask_in="x:0px;y:0px;s:inherit;e:inherit;"
										data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
										data-start="1000"
										data-splitin="chars"
										data-splitout="none"
										data-responsive_offset="on"
										data-elementdelay="0.05"
										style="z-index: 5; white-space: nowrap; letter-spacing: 1.5px; color:#777; font-weight: normal; font-family: 'Lato', sans-serif;">
									</div>
									<div class="tp-caption NotGeneric-Button rev-btn  rs-parallaxlevel-0" id="slide-layer-4"
										data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
										data-y="['middle','middle','middle','middle']" data-voffset="['-30','20','40','30']"
										data-fontsize="['14','14','14','12']"
										data-lineheight="['24','24','24','24']"
										data-width="none"
										data-height="none"
										data-whitespace="nowrap"
										data-transform_idle="o:1;"
										data-transform_hover="o:1;rX:0;rY:0;rZ:0;z:0;s:100;e:Power1.easeInOut;"
										data-style_hover="c:rgb(255, 255, 255);bg:rgba(182, 121, 95);"
										data-transform_in="x:[-100%];z:0;rX:0deg;rY:0deg;rZ:0deg;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2500;e:Power3.easeInOut;"
										data-transform_out="auto:auto;s:1000;e:Power2.easeInOut;"
										data-start="2000"
										data-splitin="none"
										data-splitout="none"
										data-actions='[{"event":"click","action":"scrollbelow","offset":"0px"}]'
										data-responsive_offset="on"
										data-responsive="off"
										style="z-index: 10; padding:8px 38px; letter-spacing:0.56px; color: #b6795f; border-color: #b6795f; font-family: 'Montserrat', sans-serif; text-transform:uppercase; background-color:transparent; white-space: nowrap;outline:none;box-shadow:none;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;">立即购买
									</div>
								</li>
								<li data-transition="zoomout" data-slotamount="default"  data-easein="easeInOut" data-easeout="easeInOut" data-masterspeed="2000" data-rotate="0"  data-fstransition="fade" data-fsmasterspeed="1500" data-fsslotamount="7">
									<img :src="imgUrl1" alt="slider" data-bgposition="center center" data-bgfit="cover" data-bgrepeat="no-repeat" data-bgparallax="10" class="rev-slidebg" data-no-retina>
									<div class="tp-caption NotGeneric-Title tp-resizeme rs-parallaxlevel-0" id="slide-layer-5"
										data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
										data-y="['middle','middle','middle','middle']" data-voffset="['-200','-130','-110','-80']"
										data-fontsize="['38','30','25','16']"
										data-lineheight="['24','24','24','24']"
										data-width="none"
										data-height="none"
										data-whitespace="nowrap"
										data-transform_idle="o:1;"
										data-transform_in="y:-50px;opacity:0;s:1000;e:Power4.easeOut;"
										data-transform_out="opacity:0;s:3000;e:Power4.easeIn;s:3000;e:Power4.easeIn;"
										data-mask_in="x:0px;y:0px;s:inherit;e:inherit;"
										data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
										data-start="1000"
										data-splitin="chars"
										data-splitout="none"
										data-responsive_offset="on"
										data-elementdelay="0.05"
										style="z-index: 5; white-space: nowrap; letter-spacing: 3.04px; color:#333; font-weight: 700; font-family: 'Montserrat', sans-serif; text-transform: uppercase;">
										<!-- do what you want , what you need -->
									</div>
									<div class="tp-caption NotGeneric-Title tp-resizeme rs-parallaxlevel-0" id="slide-layer-6"
										data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
										data-y="['middle','middle','middle','middle']" data-voffset="['-135','-80','-60','-50']"
										data-fontsize="['18','18','18','14']"
										data-lineheight="['26','26','26','26']"
										data-width="['750','750','738','450']"
										data-height="none"
										data-whitespace="nowrap"
										data-transform_idle="o:1;"
										data-transform_in="x:[105%];z:0;rX:45deg;rY:0deg;rZ:90deg;sX:1;sY:1;skX:0;skY:0;s:2000;e:Power4.easeInOut;"
										data-transform_out="y:[100%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
										data-mask_in="x:0px;y:0px;s:inherit;e:inherit;"
										data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
										data-start="1000"
										data-splitin="chars"
										data-splitout="none"
										data-responsive_offset="on"
										data-elementdelay="0.05"
										style="z-index: 5; white-space: nowrap; letter-spacing: 1.5px; color:#777; font-weight: normal; font-family: 'Lato', sans-serif;">
									</div>
									<div class="tp-caption NotGeneric-Title tp-resizeme rs-parallaxlevel-0" id="slide-layer-7"
										data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
										data-y="['middle','middle','middle','middle']" data-voffset="['-110','-50','-35','-30']"
										data-fontsize="['18','18','18','14']"
										data-lineheight="['26','26','26','26']"
										data-height="none"
										data-whitespace="nowrap"
										data-transform_idle="o:1;"
										data-transform_in="x:[105%];z:0;rX:45deg;rY:0deg;rZ:90deg;sX:1;sY:1;skX:0;skY:0;s:2000;e:Power4.easeInOut;"
										data-transform_out="y:[100%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
										data-mask_in="x:0px;y:0px;s:inherit;e:inherit;"
										data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
										data-start="1000"
										data-splitin="chars"
										data-splitout="none"
										data-responsive_offset="on"
										data-elementdelay="0.05"
										style="z-index: 5; white-space: nowrap; letter-spacing: 1.5px; color:#777; font-weight: normal; font-family: 'Lato', sans-serif;">
									</div>
									<div class="tp-caption NotGeneric-Button rev-btn  rs-parallaxlevel-0" id="slide-layer-8"
										data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
										data-y="['middle','middle','middle','middle']" data-voffset="['-30','20','40','30']"
										data-fontsize="['14','14','14','12']"
										data-lineheight="['24','24','24','24']"
										data-width="none"
										data-height="none"
										data-whitespace="nowrap"
										data-transform_idle="o:1;"
										data-transform_hover="o:1;rX:0;rY:0;rZ:0;z:0;s:100;e:Power1.easeInOut;"
										data-style_hover="c:rgb(255, 255, 255);bg:rgba(182, 121, 95);"
										data-transform_in="x:[-100%];z:0;rX:0deg;rY:0deg;rZ:0deg;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2500;e:Power3.easeInOut;"
										data-transform_out="auto:auto;s:1000;e:Power2.easeInOut;"
										data-start="2000"
										data-splitin="none"
										data-splitout="none"
										data-actions='[{"event":"click","action":"scrollbelow","offset":"0px"}]'
										data-responsive_offset="on"
										data-responsive="off"
										style="z-index: 10; padding:8px 38px; letter-spacing:0.56px; color: #b6795f; border-color: #b6795f; font-family: 'Montserrat', sans-serif; text-transform:uppercase; background-color:transparent; white-space: nowrap;outline:none;box-shadow:none;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;">
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="goto-next"><a href="#category-section"><i class="icon icon-Mouse bounce"></i></a></div>
			</div>
		</div>
		<!-- 首页轮播图 -->
		<!-- 中间logo -->
		<div class="services-section container-fluid">
				<div class="container">
					<div class="col-md-4 col-sm-6 col-xs-6">
						<div class="srv-box">
							<i class="icon icon-Truck"></i><h5>免费 邮寄</h5><i class="icon icon-Dollar"></i>
							<!-- <span class="icon_close"></span> -->
						</div>
					</div>
					<div class="col-md-4 col-sm-6 col-xs-6">
						<div class="srv-box">
							<i class="icon icon-Goto"></i><h5>支持 退货</h5><i class="icon icon-Dollars"></i>
						</div>
					</div>
					<div class="col-md-4 col-sm-6 col-xs-6">
						<div class="srv-box">
							<i class="icon icon-Headset"></i><h5>全天候支持</h5><i class="icon icon-Timer"></i>
						</div>
					</div>
				</div>
		</div>
		<!-- 中间logo -->

		<!-- 分类   导航 -->
		<div class="center-box">
			<img :src="sectionImg" alt="section-seprator" />
			<img :src="sectionImg" alt="section-seprator" />
			<img :src="sectionImg" alt="section-seprator" />
			<img :src="sectionImg" alt="section-seprator" />
		</div>
		<div class="p1">
			<p class="text0">分类   导航</p>
		</div>
		<!--  分类   导航-->

		<div class="cc1">
		<div class="col-box">
		<Row>
			<Col span="6" class="aaa" v-for="(item,index) in goodsTypeList" :key="index">
<!--				<img :src="tempfilePath+item.img" class="col-img" alt="-" />-->
				<div class="bbb" @click="toType(item.id)">
					<p title="Shop Now" >{{item.goodstypename}}</p>
				</div>
			</Col>
    	</Row>

		</div>
		</div>

	</div>

</template>

<script>
import api from '../utils/api';
import goodsCenter from '@/components/goodsCenter';
import Index from '../components/Index';
import Search from '@/components/Search';
export default {
	components:{
		Index,
		Search,
		goodsCenter,
	},
	data(){
		return{
			goodsTypeList:[],
			tempfilePath:'http://localhost:8089/img/type/',
			fenleiImg:require("../../static/img/nav/fenlei.png"),
			sectionImg:require("../../static/img/index/section-seprator.png"),
			imgUrl:require("../../static/img/index/susu.png"),
			imgUrl1:require('../../static/img/index/susu2.png'),
			// hfImgUrl:[require('../../static/img/nav/peishi.png'),
			// require('../../static/img/nav/fenlei2.jpg'),
			// require('../../static/img/index/hanfu/9.jpg'),
			// require('../../static/img/nav/fen.jpg')
			// ]
		}
	},
	mounted(){
		$("#home-slider1").revolution({
				sliderType:"standard",
				sliderLayout:"auto",
				delay:6000,
				navigation: {
					arrows:{
						enable:true,
						style:"uranus"
					},
					bullets: {
						enable:false,
						hide_onmobile:true,
						hide_under:480,
						style:"zeus",
						hide_onleave:false,
						direction:"horizontal",
						h_align:"center",
						v_align:"bottom",
						h_offset:0,
						v_offset:40,
						space:15,
						tmp:''
					}
				},
				responsiveLevels:[1920,1024,768,480],
				gridwidth:[1920,1024,768,480],
				gridheight:[851,675,580,480],
		});



	},
	created(){
		api.getGoodsType().then((res)=>{
			let a=res.extend.list.slice(0,4)
			let b=["fen.jpg","9.jpg","peishi.png","nan.jpg",]
			for(let i=0;i<a.length;i++){
				a[i].img=b[i]
				// console.log(b[i])
			}
			this.goodsTypeList=a
			// console.log(this.goodsTypeList)
		})

	},
	methods:{
		toType(id){
			console.log(id)
			let routeData = this.$router.resolve({ path: '/goodsList', query: {id:id} });
 			window.open(routeData.href, '_blank');
			//  this.$route.query.id====》获取参数
		},
	}

}
</script>

<style scoped>
.rev-slidebg{
	width: 100%;
	height: 300px;
}
.newbox{
	width: 100%;
	height: 510px;
	/* margin-bottom: 50px; */
}
.home-revslider{
	width: 100%;
	height: 300px;
}
.img-center{
	width: 300px;
	height: 300px;
}
.top{
        padding: 10px;
        background: #ED3F14;
        color: #fff;
        text-align: center;
        border-radius: 2px;
    }

.affix{
	width:100%;
	height: 100px;
	background-color:red;
}
.demo-affix{
	width:100%;
	height: 80px;
	background-color:#fff;
}
.center-box{
	display: flex;
	flex-direction: row;
	justify-content: center;
	/* overflow: scroll; */

}
.text0{
	font-size: 15px;
	font-weight: 200;
	line-height: 50px;
	color: #383231;
	text-align: center;
}
.p1{
	margin: 0 auto;
	width: 200px;
	height: 50px;
	border: solid 1px #dabcaf;
	border-radius: 10px;
	margin-top: 20px;
	margin-bottom: 20px;
}
.col-box {
  width: 1200px;
  margin: 0px auto;
}
.col-img{
	width:300px;
	height: 350px;
}
.fenlei{
	width: 60px;
	background-color: #fff;
	border-radius: 5px;
	top:-20px;
	z-index:1;
	position: absolute;

}
.bbb p {
    border: 1px solid #b6795f;
    /* color: #b6795f; */
	color:#FFF;
	font-weight: 700;
    display: inline-block;
    font-family: 'Montserrat', sans-serif;
    letter-spacing: 0.56px;
    line-height: 26px;
    padding: 8px 40px;
    text-transform: uppercase;
    text-decoration: none;
	top: 50%;
    left: 30%;
    position: absolute;
}
.bbb p:hover {
	background-color: #b6795f;
	color: #fff;
}
.aaa{
	position: relative;
}
.cc1{
	width: 100%;
	background-color: #F6F6F6;
	margin-bottom: 40px;
}
.end{
	width: 100%;
	background-color: #F6F6F6;
	margin: 0 auto;
	padding-top: 30px;
	padding-bottom: 30px;
	text-align: center;
	font-size: 20px;
	color: rgb(202, 194, 194);
}
</style>
