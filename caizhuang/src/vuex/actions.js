import api from '@/utils/api'
import Cookies from "js-cookie";
//加载汉服配饰专区的数据
export const loadPeishi= ({ commit })=>{
  return new Promise((resolve, reject) => {
    const peishi={
      title: '配饰',
      // 璎珞、宫绦、簪、钗、发带、发梳、钿子、耳坠、褙子扣、禁步、步摇、腰带、带钩、腰绳、狄髻、手镯、手链、佛珠、玉簪花、各种冠、各种巾、绢花、戒指、扳指、荷包。
      link: [ '璎珞', '宫绦', '簪', '发带', '褙子扣', '禁步', '步摇', '玉簪花', '狄髻' ,'荷包','>>>>'],
      detail: [
        {
          bigImg: 'static/img/index/peishi/14.jpg',
          itemFour: [
            {
              title: '团扇古风',
              intro: '长柄流苏扇子 ',
              img: 'static/img/index/peishi/17.jpg'
            },
            {
              title: '圆扇古风团扇',
              intro: '双面团扇刺绣长',
              img: 'static/img/index/peishi/15.jpg'
            },
            {
              title: '紫芳斋国画扇',
              intro: '长柄流苏汉服手工',
              img: 'static/img/index/peishi/13.jpg'
            },
            {
              title: '重回汉唐浮梦',
              intro: '正品汉服周边配',
              img: 'static/img/index/peishi/16.jpg'
            }
          ],
          itemContent: [
            'static/img/index/peishi/b2.png',
            'static/img/index/peishi/b3.png',
            'static/img/index/peishi/b4.png'
          ]
        },
        {
          bigImg: 'static/img/index/peishi/12.jpg',
          itemFour: [
            {
              title: '花铃铛',
              intro: '美人玉簪且天下！',
              img: 'static/img/index/peishi/2.jpg'
            },
            {
              title: '汉服配饰品发簪子古装',
              intro: '1000减618',
              img: 'static/img/index/peishi/3.jpg'
            },
            {
              title: '头饰仙气',
              intro: '珍珠流苏发梳配饰',
              img: 'static/img/index/peishi/4.jpg'
            },
            {
              title: '重回汉唐浮梦',
              intro: '正品汉服周边配',
              img: 'static/img/index/peishi/7.jpg'
            }
          ],
          itemContent: [
            'static/img/index/peishi/b5.png',
            'static/img/index/peishi/b6.png',
            'static/img/index/peishi/b7.png'
          ]
        },

      ]
    };
    commit('SET_PEISHI_INFO',peishi);
  })
};
//加载汉服数据
export const loadHanfu=({commit})=>{
  return new Promise((resolve, reject) =>{
    const hanfu={
      title: '汉服',
      // 唐圆领袍,深衣,直裾,道袍,直身,直裰,明圆领袍,曳撒,贴里,罩甲,搭护,短打,襦裙,褙子,大氅,披风
      link: [ '唐圆领袍', '深衣', '直裾', '道袍', '直身', '直裰', '明圆领袍', '曳撒', '贴里', '罩甲', '短打' ,'>>>>'],
      detail: [
        {
          bigImg: 'static/img/index/hanfu/18.jpg',
          itemFour: [
            {
              title: '华裳九州',
              intro: '原创汉服正品花凰！',
              img: 'static/img/index/hanfu/5.jpg'
            },
            {
              title: '搭夫人原创',
              intro: '1000减618',
              img: 'static/img/index/hanfu/6.jpg'
            },
            {
              title: '池夏仲夏夜之梦:',
              intro: '正品原创夏季',
              img: 'static/img/index/hanfu/7.jpg'
            },
            {
              title: '如梦霓裳两同心',
              intro: '唐制原创汉服',
              img: 'static/img/index/hanfu/22.jpg'
            }
          ],
          itemContent: [
            'static/img/index/hanfu/b1.png',
            'static/img/index/hanfu/b2.png',
            'static/img/index/hanfu/b3.png'
          ]
        },
        {
          bigImg: 'static/img/index/hanfu/23.jpg',
          itemFour: [
            {
              title: '华裳九州',
              intro: '将军和慈山',
              img: 'static/img/index/hanfu/13.jpg'
            },
            {
              title: '如梦霓裳两同心',
              intro: '一去东流动',
              img: 'static/img/index/hanfu/25.jpg'
            },
            {
              title: '南雅季',
              intro: '汉水流云',
              img: 'static/img/index/hanfu/28.jpg'
            },
            {
              title: '十三妹',
              intro: '唐制原创汉服',
              img: 'static/img/index/hanfu/33.jpg'
            }
          ],
          itemContent: [
            'static/img/index/hanfu/b4.png',
            'static/img/index/hanfu/b5.png',
            'static/img/index/hanfu/b6.png'
          ]
        },

      ],
    };
    commit('SET_HANFU_INFO', hanfu);
  })
}
//
export const getGoodsForty = ({ commit }) => {
  return new Promise((resolve, reject) => {
    api.getGoodsList().then((res)=>{
      console.log(res)
      commit('SET_GOODSFORTY_INFO', res.extend.goodsList.slice(0,10));
      commit('setHotList',res.extend.goodsList.slice(0,5))
      commit('SET_RECOMMEND_INFO', res.extend.goodsList.slice(12,24));

      const date = new Date();
      const hours = date.getHours();
      const minute = date.getMinutes();
      const seconds = date.getSeconds();
      // 距离开始秒杀时间
      const deadline = {
        hours: 1,
        minute: 38,
        seconds: 36
      };
      commit('SET_SECKILLS_INFO', [res.extend.goodsList.slice(0,6), deadline]);
    })

  })
}
// 设置用户登录信息
export const setUserInfo =({commit}, data)=>{
  return new Promise((resolve, reject)=>{
    commit('setUserInfo', data);

  })
}
// 判断是否登陆
export const isLogin = ({ commit }) => {
  const hasToken=Cookies.get('token1')
  if(hasToken){
    const user = localStorage.getItem('userdata');
    if (user) {
      commit('setUserInfo', JSON.parse(user));
    }

  }else{
    localStorage.removeItem('userdata')
  }

};
// 退出登陆
export const signOut = ({ commit }) => {
  Cookies.remove('token1');
  localStorage.removeItem('userdata');
  commit('setUserInfo', {});
};
//查看收藏夹
export const getAllCollection=({commit})=>{
  return new Promise((resolve,reject)=>{
    api.getAllCollection().then((res)=>{
      commit('getAllCollection',res.extend.likeList)
    })

  })
};
//存储商品详情
export const getGoodsDetail =({commit}, data)=>{
  return new Promise((resolve, reject)=>{
    console.log("123 shi detailma ")
    commit('getGoodsDetail', data);
  })
}
// 获取用户推荐
// export const loadRecommend = ({ commit }) => {
//   return new Promise((resolve, reject) => {
//     commit('SET_RECOMMEND_INFO', data);
//   });
// };







// 获取秒杀数据
export const loadSeckillsInfo = ({ commit }) => {
  return new Promise((resolve, reject) => {
    const data = [
      {
        intro: '【赠小风扇】维他 柠檬茶250ml*32盒 礼品装 整箱',
        img: 'static/img/index/seckill/seckill-item1.jpg',
        price: 71.9,
        realPrice: 89.6
      },
       {
        intro: '【赠小风扇】维他 柠檬茶250ml*32盒 礼品装 整箱',
        img: 'static/img/index/seckill/seckill-item1.jpg',
        price: 71.9,
        realPrice: 89.6
      },
      {
        intro: 'Kindle Paperwhite 全新升级版6英寸护眼非反光电子墨水',
        img: 'static/img/index/seckill/seckill-item2.jpg',
        price: 989.0,
        realPrice: 1299.0
      },
      {
        intro: '粮悦 大吃兄糯米锅巴 安徽特产锅巴糯米原味400g*2盒',
        img: 'static/img/index/seckill/seckill-item3.jpg',
        price: 21.8,
        realPrice: 49.0
      },
      {
        intro: '【京东超市】清风（APP）抽纸 原木纯品金装系列 3层',
        img: 'static/img/index/seckill/seckill-item4.jpg',
        price: 49.9,
        realPrice: 59.0
      },
      {
        intro: 'NIKE耐克 男子休闲鞋 AIR MAX 90 ESSENTIAL 气垫',
        img: 'static/img/index/seckill/seckill-item5.jpg',
        price: 559.9,
        realPrice: 759.9
      }
    ];
    const date = new Date();
    const hours = date.getHours();
    const minute = date.getMinutes();
    const seconds = date.getSeconds();
    // console.log([hours, minute, seconds]);
    // 距离开始秒杀时间
    const deadline = {
      hours: 1,
      minute: 38,
      seconds: 36
    };
    commit('SET_SECKILLS_INFO', [data, deadline]);
  //   state.seckills.goodsList = seckills[0];
  // state.seckills.deadline = seckills[1];
  });
};

// 获取轮播(营销)图片
export const loadCarouselItems = ({ commit }) => {
  return new Promise((resolve, reject) => {
    const data = {
      carouselItems: [
        'static/img/nav/1.jpg',
        'static/img/nav/2.jpg',
        'static/img/nav/3.jpg',
        'static/img/nav/4.jpg',
        'static/img/nav/5.jpg'
      ],
      activity: [
        'static/img/nav/nav_showimg1.jpg',
        'static/img/nav/nav_showimg2.jpg'
      ]
    };
    commit('SET_CAROUSELITEMS_INFO', data);
  });
};




// 加载爱吃专栏数据
// export const loadEat = ({ commit }) => {
//   return new Promise((resolve, reject) => {
//     const eat = {
//       title: '爱吃',
//       link: [ '休闲零食', '坚果', '牛奶', '饮料冲调', '食用油', '大米', '白酒', '红酒', '烧烤食材', '牛排', '樱桃' ],
//       detail: [
//         {
//           bigImg: 'static/img/index/eat/item-eat-1-1.jpg',
//           itemFour: [
//             {
//               title: '粮油调味',
//               intro: '买2免1',
//               img: 'static/img/index/eat/item-eat-1-2.jpg'
//             },
//             {
//               title: '饮料冲调',
//               intro: '第二件半价',
//               img: 'static/img/index/eat/item-eat-1-3.jpg'
//             },
//             {
//               title: '休闲零食',
//               intro: '满99减40',
//               img: 'static/img/index/eat/item-eat-1-4.jpg'
//             },
//             {
//               title: '中外名酒',
//               intro: '满199减100',
//               img: 'static/img/index/eat/item-eat-1-5.jpg'
//             }
//           ],
//           itemContent: [
//             'static/img/index/eat/item-eat-1-6.jpg',
//             'static/img/index/eat/item-eat-1-7.jpg',
//             'static/img/index/eat/item-eat-1-8.jpg'
//           ]
//         },
//         {
//           bigImg: 'static/img/index/eat/item-eat-2-1.jpg',
//           itemFour: [
//             {
//               title: '东家菜',
//               intro: '丰富好味',
//               img: 'static/img/index/eat/item-eat-2-2.jpg'
//             },
//             {
//               title: '东家菜',
//               intro: '丰富好味',
//               img: 'static/img/index/eat/item-eat-2-2.jpg'
//             },
//             {
//               title: '东家菜',
//               intro: '丰富好味',
//               img: 'static/img/index/eat/item-eat-2-2.jpg'
//             },
//             {
//               title: '东家菜',
//               intro: '丰富好味',
//               img: 'static/img/index/eat/item-eat-2-2.jpg'
//             }
//           ],
//           itemContent: [
//             'static/img/index/eat/item-eat-2-6.jpg',
//             'static/img/index/eat/item-eat-2-7.jpg',
//             'static/img/index/eat/item-eat-2-8.jpg'
//           ]
//         }
//       ]
//     };
//     commit('SET_EAT_INFO', eat);
//   });
// };

// 请求获得商品详细信息
export const loadGoodsInfo = ({ commit }) => {
  commit('SET_LOAD_STATUS', true);
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const data = {
        goodsImg: [
          'static/img/goodsDetail/item-detail-1.jpg',
          'static/img/goodsDetail/item-detail-2.jpg',
          'static/img/goodsDetail/item-detail-3.jpg',
          'static/img/goodsDetail/item-detail-4.jpg'
        ],
        title: '苹果8/7手机壳iPhone7 Plus保护壳全包防摔磨砂硬外壳',
        tags: ['满69-20元', '关注产品★送钢化膜', 'BIT配次日达'],
        discount: ['满148减10', '满218减20', '满288减30'],
        promotion: ['跨店满减', '多买优惠'],
        remarksNum: 6000,
        setMeal: [
          [
            {
              img: 'static/img/goodsDetail/pack/1.jpg',
              intro: '4.7英寸-深邃蓝',
              price: 28.0
            },
            {
              img: 'static/img/goodsDetail/pack/2.jpg',
              intro: '4.7英寸-星空黑',
              price: 29.0
            },
            {
              img: 'static/img/goodsDetail/pack/3.jpg',
              intro: '5.5英寸-香槟金',
              price: 28.5
            }
          ],
          [
            {
              img: 'static/img/goodsDetail/pack/4.jpg',
              intro: '5.5英寸-玫瑰金',
              price: 32.0
            },
            {
              img: 'static/img/goodsDetail/pack/5.jpg',
              intro: '5.5英寸-深邃蓝',
              price: 32.0
            },
            {
              img: 'static/img/goodsDetail/pack/6.jpg',
              intro: '5.5英寸-星空黑',
              price: 35.0
            }
          ],
          [
            {
              img: 'static/img/goodsDetail/pack/7.jpg',
              intro: '4.7英寸-香槟金',
              price: 26.0
            },
            {
              img: 'static/img/goodsDetail/pack/8.jpg',
              intro: '4.7英寸-玫瑰金',
              price: 25.0
            },
            {
              img: 'static/img/goodsDetail/pack/9.jpg',
              intro: '4.7英寸-中国红',
              price: 28.0
            }
          ]
        ],
        hot: [
          {
            img: 'static/img/goodsDetail/hot/1.jpg',
            price: 28.0,
            sale: 165076
          },
          {
            img: 'static/img/goodsDetail/hot/2.jpg',
            price: 36.0,
            sale: 135078
          },
          {
            img: 'static/img/goodsDetail/hot/3.jpg',
            price: 38.0,
            sale: 105073
          },
          {
            img: 'static/img/goodsDetail/hot/4.jpg',
            price: 39.0,
            sale: 95079
          },
          {
            img: 'static/img/goodsDetail/hot/5.jpg',
            price: 25.0,
            sale: 5077
          },
          {
            img: 'static/img/goodsDetail/hot/6.jpg',
            price: 20.0,
            sale: 3077
          }
        ],
        goodsDetail: [
          'static/img/goodsDetail/intro/1.jpg',
          'static/img/goodsDetail/intro/2.jpg',
          'static/img/goodsDetail/intro/3.jpg',
          'static/img/goodsDetail/intro/4.jpg'
        ],
        param: [
          {
            title: '商品名称',
            content: 'iPhone 7手机壳'
          },
          {
            title: '商品编号',
            content: '10435663237'
          },
          {
            title: '店铺',
            content: 'Gavin Shop'
          },
          {
            title: '商品毛重',
            content: '100.00g'
          },
          {
            title: '商品产地',
            content: '中国大陆'
          },
          {
            title: '机型',
            content: 'iPhone 7'
          },
          {
            title: '材质',
            content: 'PC/塑料'
          },
          {
            title: '款式',
            content: '软壳'
          },
          {
            title: '适用人群',
            content: '通用'
          }
        ],
        remarks: {
          goodAnalyse: 90,
          remarksTags: [ '颜色可人', '实惠优选', '严丝合缝', '极致轻薄', '质量没话说', '比定做还合适', '完美品质', '正品行货', '包装有档次', '不容易发热', '已经买第二个', '是全覆盖' ],
          remarksNumDetail: [ 2000, 3000, 900, 1 ],
          detail: [
            {
              username: 'p****1',
              values: 3,
              content: '颜色很好看，质量也不错！，还送了个指环，想不到哦！',
              goods: '4.7英寸-深邃蓝',
              create_at: '2018-05-15 09:20'
            },
            {
              username: '13****1',
              values: 5,
              content: '手感没的说，是硬壳，后背带有磨砂手感。很不错，很喜欢，还加送了钢化膜，支架环，物超所值，准备再买一个。很满意。物流很快。很愉快的一次购物！',
              goods: '5.5英寸-玫瑰金',
              create_at: '2018-05-13 15:23'
            },
            {
              username: '3****z',
              values: 4.5,
              content: '相当轻薄，店家还送了一大堆配件，*元非常值得！',
              goods: '4.7英寸-玫瑰金',
              create_at: '2018-05-05 12:25'
            },
            {
              username: 'gd****c',
              values: 3.5,
              content: '就是我想要的手机壳，壳子很薄，手感不错，就像没装手机壳一样，想要裸机手感的赶快下手了。',
              goods: '4.7英寸-中国红',
              create_at: '2018-04-06 16:23'
            },
            {
              username: 'r****b',
              values: 4.5,
              content: '壳子还不错，送的膜也可以，不过还是感觉膜小了那么一点，屏幕没法完全覆盖。对了，壳子稍微有点硬，可能会伤漆，所以不要频繁取壳就好。',
              goods: '4.7英寸-中国红',
              create_at: '2018-03-15 19:24'
            },
            {
              username: 'd****e',
              values: 5,
              content: '磨砂的，相当漂亮，尺寸非常合适！精工细作！',
              goods: '5.5英寸-星空黑',
              create_at: '2018-03-10 10:13'
            }
          ]
        }
      };
      commit('SET_GOODS_INFO', data);
      commit('SET_LOAD_STATUS', false);
    }, 300);
  });
};

// 获取商品列表
export const loadGoodsList = ({ commit }) => {
  commit('SET_LOAD_STATUS', true);
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const data = {
        asItems: [
          {
            img: 'static/img/goodsList/item-as-img-1.jpg',
            price: 39.9,
            intro: '测试用',
            num: 3140,
            sale: 9000
          },

        ],
        goodsList: [
          {
            img: 'static/img/goodsList/item-show-8.jpg',
            price: 29.90,
            intro: '测试用',
            remarks: 6080,
            shopName: 'zzz',
            sale: 3560
          }
        ]
      };
      commit('SET_GOODS_LIST', data);
      commit('SET_LOAD_STATUS', false);
    });
  });
};

// 添加购物车
export const addShoppingCart = ({ commit }, data) => {
  return new Promise((resolve, reject) => {
    commit('ADD_SHOPPING_CART', data);
  });
};



export const loadAddress = ({ commit }) => {
  return new Promise((resolve, reject) => {
    const address = [
      {
        addressId: '123456',
        name: 'Gavin',
        province: '广东省',
        city: '广州市',
        area: '天河区',
        address: '燕岭路633号',
        phone: '152****0609',
        postalcode: '510000'
      },
      {
        addressId: '123458',
        name: 'Kevin',
        province: '上海市',
        city: '上海市',
        area: '浦东新区',
        address: '沙新镇',
        phone: '158****0888',
        postalcode: '200120'
      }
    ];
    commit('SET_USER_ADDRESS', address);
  });
};

export const loadShoppingCart = ({ commit }) => {
  return new Promise((resolve, reject) => {
    const data = [{
      goods_id: 1529931938150,
      count: 1,
      img: 'static/img/goodsDetail/pack/1.jpg',
      package: '4.7英寸-深邃蓝',
      price: 28,
      title: '苹果8/7手机壳iPhone7 Plus保护壳全包防摔磨砂硬外壳'
    }];
    commit('SET_SHOPPING_CART', data);
  });
};

// 添加注册用户
// export const addSignUpUser = ({ commit }, data) => {
//   return new Promise((resolve, reject) => {
//     const userArr = localStorage.getItem('users');
//     let users = [];
//     if (userArr) {
//       users = JSON.parse(userArr);
//     }
//     users.push(data);
//     localStorage.setItem('users', JSON.stringify(users));
//   });
// };

// 用户登录
// export const login = ({ commit }, data) => {
//   return new Promise((resolve, reject) => {
//     if (data.username === 'Gavin' && data.password === '123456') {
//       localStorage.setItem('loginInfo', JSON.stringify(data));
//       commit('SET_USER_LOGIN_INFO', data);
//       resolve(true);
//       return true;
//     }
//     const userArr = localStorage.getItem('users');
//     console.log(userArr);
//     if (userArr) {
//       const users = JSON.parse(userArr);
//       for (const item of users) {
//         if (item.username === data.username) {
//           localStorage.setItem('loginInfo', JSON.stringify(item));
//           commit('SET_USER_LOGIN_INFO', item);
//           resolve(true);
//           break;
//         }
//       }
//     } else {
//       resolve(false);
//     }
//   });
// };



