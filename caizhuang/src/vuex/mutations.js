//设置汉服配饰专区数据
export const SET_PEISHI_INFO = (state, peishi) => {
  state.peishi = peishi;
};
//设置汉服数据
export const SET_HANFU_INFO = (state, hanfu) => {
  state.hanfu = hanfu;
};
//设置商品销售量前四十
export const SET_GOODSFORTY_INFO=(state,data)=>{
  state.goodsListforForty=data;
};
//获取搜索的名称
export const getSearchName=(state,data)=>{  
  state.searchName = data;
}
//获取搜索的分类
export const getTypeId=(state,data)=>{  
  state.typeId = data;
}
// 设置用户登录信息
export const setUserInfo = (state, data) => {
  state.userInfo = data;
};


// 设置注册步骤
export const SET_SIGN_UP_SETP = (state, step) => {
  state.signUpStep = step;
};

//查看收藏夹
export const getAllCollection=(state,data)=>{
  state.collection=data;
}

//存储商品详情
export const getGoodsDetail=(state,data)=>{
  state.goodsDetail=data;
}

export const setHotList=(state,data)=>{
  state.hotList=data;
};














// 设置加载状态
export const SET_LOAD_STATUS = (state, status) => {
  state.isLoading = status;
};

// 设置秒杀商品
export const SET_SECKILLS_INFO = (state, seckills) => {
  state.seckills.goodsList = seckills[0];
  state.seckills.deadline = seckills[1];
};

// 设置轮播(营销)图
export const SET_CAROUSELITEMS_INFO = (state, { carouselItems, activity }) => {
  state.marketing.CarouselItems = carouselItems;
  state.marketing.activity = activity;
};

// 设置电脑专栏数据
export const SET_COMPUTER_INFO = (state, computer) => {
  state.computer = computer;
};


// 设置爱吃专栏数据
export const SET_EAT_INFO = (state, eat) => {
  state.eat = eat;
};

// 减少秒杀时间
export const REDUCE_SECKILLS_TIME = state => {
  state.seckills.deadline.seconds--;
  if (state.seckills.deadline.seconds === -1) {
    state.seckills.deadline.seconds = 59;
    state.seckills.deadline.minute--;
    if (state.seckills.deadline.minute === -1) {
      state.seckills.deadline.minute = 59;
      state.seckills.deadline.hour--;
    }
  }
};

// 设置商品列表(搜索)
export const SET_GOODS_LIST = (state, data) => {
  state.goodsList = data.goodsList;
  state.asItems = data.asItems;
};

// 设置商品列表排序
export const SET_GOODS_ORDER_BY = (state, data) => {
  state.orderBy = data;
};

// 设置商品详细信息
export const SET_GOODS_INFO = (state, data) => {
  state.goodsInfo = data;
};

// 添加购物车
export const ADD_SHOPPING_CART = (state, data) => {
  const item = {
    goods_id: data.goods_id,
    count: data.count,
    img: data.package.img,
    package: data.package.intro,
    price: data.package.price,
    title: data.title
  };
  state.shoppingCart.push(item);
  state.newShoppingCart = data;
};

// 设置购物车信息
export const SET_SHOPPING_CART = (state, data) => {
  state.shoppingCart = data;
};

// 设置推荐信息
export const SET_RECOMMEND_INFO = (state, data) => {
  state.recommend = data;
};

// 设置收获地址
export const SET_USER_ADDRESS = (state, data) => {
  state.address = data;
};
