<template>
  <div class="center-box">
    <div class="layout">
        <Layout>
            <Sider ref="side1" hide-trigger collapsible :collapsed-width="78" v-model="isCollapsed" :style="{ background: '#fff', overflow: 'auto'}">
                <Menu active-name=""  theme="light" width="auto"  :class="menuitemClasses">
                    <MenuItem name="1-0" to="/home/index">
                        <Icon type="md-apps" />
                        <span>全部功能</span>
                    </MenuItem>
                    <MenuItem name="1-1" to="/showCart" target="_blank">
                        <Icon type="md-appstore" />
                        <span>购物车</span>
                    </MenuItem>
                    <MenuItem name="1-2"to="/myCollection">
                        <Icon type="md-star" />
                        <span>收藏夹</span>
                    </MenuItem>
                    <MenuItem name="1-3" to="/home/showOrder" >
                        <Icon type="ios-list-box" />
                        <span>我的订单</span>
                    </MenuItem>
                    <MenuItem name="1-4" to="/home/myReview">
                        <Icon type="ios-create" />
                        <span>我的评价</span>
                    </MenuItem>
                </Menu>
            </Sider>
            <Layout>
                <Header :style="{padding: 0}" class="layout-header-bar">
                    <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{margin: '0 20px'}" type="md-menu" size="24"></Icon>
                    

                </Header>
                <Content :style="{margin: '20px', background: '#fff', minHeight: '260px'}">
                    <transition mode="out-in">
                      <router-view></router-view>
                    </transition>
                </Content>
                <!-- <Footer class="layout-footer-center">2021 &copy; hanfu.store.com 版权所有</Footer> -->
            </Layout>
        </Layout>
    </div>
    
  </div>
</template>

<script>
export default {
  data () {
            return {
                isCollapsed: false
            }
        },
        computed: {
            rotateIcon () {
                return [
                    'menu-icon',
                    this.isCollapsed ? 'rotate-icon' : ''
                ];
            },
            menuitemClasses () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        },
        methods: {
            collapsedSider () {
                this.$refs.side1.toggleCollapse();
            },
        }

}
</script>

<style scoped>
.center-box{
    width: 1300px;
    margin:0 auto;
    /* border: 1px solid red; */
}
.layout{
        /* border: 1px solid #d7dde4; */
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-header-bar{
        background: #fff;
        box-shadow: 0 1px 1px rgba(0,0,0,.1);
    }
    .layout-logo-left{
        width: 90%;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        margin: 15px auto;
    }
    .menu-icon{
        transition: all .3s;
    }
    .rotate-icon{
        transform: rotate(-90deg);
    }
    .menu-item span{
        display: inline-block;
        overflow: hidden;
        width: 69px;
        text-overflow: ellipsis;
        white-space: nowrap;
        vertical-align: bottom;
        transition: width .2s ease .2s;
    }
    .menu-item i{
        transform: translateX(0px);
        transition: font-size .2s ease, transform .2s ease;
        vertical-align: middle;
        font-size: 16px;
    }
    .collapsed-menu span{
        width: 0px;
        transition: width .2s ease;
    }
    .collapsed-menu i{
        transform: translateX(5px);
        transition: font-size .2s ease .2s, transform .2s ease .2s;
        vertical-align: middle;
        font-size: 22px;
    }

</style>

