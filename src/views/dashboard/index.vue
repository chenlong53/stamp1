<template>
  <el-card title="选择坐标" fullscreen
             :modal-append-to-body="false" :append-to-body="true"
             :close-on-click-modal="false"
             @close="closeHandle"
             :visible.sync="dialogVisible">
    <el-row>
      <div class="amap-page-container">
        <el-amap-search-box class="search-box"
                            :search-option="searchOption"
                            :on-search-result="onSearchResult"
                            :center="center"></el-amap-search-box>
        <el-amap :vid="'amap-vue'"
                 :zoom="zoom"
                 :center="center"
                 :events="events">
          <el-amap-marker :position="marker.position"
                          :events="marker.events"
                          :visible="marker.visible"
                          :draggable="marker.draggable"></el-amap-marker>
        </el-amap>
      </div>
    </el-row>
    <span slot="footer"
          class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary"
                 @click="submit">确 定</el-button>
    </span>
  </el-card>
</template>

<script>
  export default {
    data () {
      return {
        q: '',
        zoom: 14,
        center: [116.602489, 40.080734],
        marker: {
          position: [216.602489, 40.080734],
          visible: true,
          draggable: false
        },
        searchOption: {
          city: '全国',
          citylimit: true
        },
        events: {
          click: (e) => {
            this.marker.position = [e.lnglat.lng, e.lnglat.lat]
            console.log(this.marker.position)
          }
        },
        dialogVisible: false
      }
    },
    methods: {
      open (lng, lat) {
        if (lng && lat) {
          this.center = [lng, lat]
          this.marker.position = [lng, lat]
        }
        this.dialogVisible = true
      },
      onSearchResult (pois) {
        let latSum = 0
        let lngSum = 0
        pois.forEach(poi => {
          lngSum += poi.lng
          latSum += poi.lat
        })
        let center = {
          lng: lngSum / pois.length,
          lat: latSum / pois.length
        }
        this.center = [center.lng, center.lat]
        this.marker.position = [center.lng, center.lat]
        console.log(this.center)
      },
      submit () {
        this.$emit('callback', this.marker.position)
        this.dialogVisible = false
      },
      // 弹窗关闭时
      closeHandle () {
      }
    }
  }
</script>

<style>
  .amap-page-container {
    margin-top: -50px;
    width: 100%;
    height: 450px;
  }

  .search-box {
    margin-top: 0px;
    position: relative;
    top: 65px;
    left: 20px;
  }
</style>
