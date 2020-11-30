<template>
  <div :class="{active:isActive}" class="share-dropdown-menu">
    <div class="share-dropdown-menu-wrapper">
      <div v-for="(item,index) of items" @click="handleClick(item)" :key="index" :class="{'hoverBg':index==hoverIndex}" class="share-dropdown-menu-item">
        <font color="#000000" opacity="0.45" style="margin-left: 30px" v-if="item.href" >{{ item.title }}</font>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    items: {
      type: Array,
      default: function() {
        return []
      }
    },
    title: {
      type: String,
      default: 'vue'
    }
  },
  data() {
    return {
      isActive: true,
      hoverIndex: 0,
    }
  },
  created() {

  },
  methods: {
    setStyle(){

    },
    handleClick(value){
      this.hoverIndex=value.id
      console.log(value)
    }
  }
}
</script>

<style lang="scss" >
$n: 5; //和items.length 相同
$t: .1s;
.share-dropdown-menu {
  width: 100%;
  position: relative;
  z-index: 1;
  height: auto!important;

  &-wrapper {
    position: relative;
  }
  &-item {
    text-align: justify;
    position: absolute;
    width: 100%;
    background: #FFFFFF;
    color: #000;
    line-height: 60px;
    height: 60px;
    cursor: pointer;
    font-size: 15px;
    overflow: hidden;
    opacity: 1;
    transition: transform 0.28s ease;
    &:hover {
      background: #1890FF;
      color: white;
      opacity: 0.3;
      border-left: 5px solid blue;
    }
    @for $i from 1 through $n {
      &:nth-of-type(#{$i}) {
        z-index: -1;
        transition-delay: $i*$t;
        transform: translate3d(0, -60px, 0);
      }
    }
  }
  &.active {
    .share-dropdown-menu-wrapper {
      z-index: 1;
    }
    .share-dropdown-menu-item {
      @for $i from 1 through $n {
        &:nth-of-type(#{$i}) {
          transition-delay: ($n - $i)*$t;
          transform: translate3d(0, ($i - 1)*60px, 0);
        }
      }
    }
  }
  .hoverBg{

    background: #1890FF;
    color: white;
    opacity: 0.3;
    border-left: 5px solid blue;

  }
}
</style>
