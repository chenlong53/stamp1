<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
      <span style="font-family: 微软雅黑;font-size: large">报警电话列表</span>
      </el-col>
    </el-row>

    <el-table :data="alarmList" >
      <el-table-column label="电话录音ID" align="center" prop="tapeId" />
      <el-table-column label="报警人手机号" align="center" prop="alarmTelephone" />
      <el-table-column label="录音文件" align="center" prop="recordFile" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >查看任务单</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >创建任务单</el-button>
          <el-button
            v-show="true"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >取消任务单</el-button>
        </template>

      </el-table-column>
    </el-table>

    <pagination
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <div slot="title" class="header-title">
        <span class="title-name">{{ title }}</span>
        <el-divider></el-divider>
      </div>

      <div style="margin:0px auto;width: 600px">
        <el-form  ref="form" :model="form" :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="20">
              <el-form-item label="报警人姓名" label-width="100px" align="center">
                <el-input v-model="form.alarmName"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="报警人电话" label-width="100px" align="center" prop="configKey">
                <el-input v-model="form.configKey" placeholder="请输入参数键名" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="报警地址" label-width="100px" align="center" prop="configKey">
                <el-input v-model="form.configKey"  placeholder="请输入参数键名" />
              </el-form-item>
            </el-col>
            <el-col :span="15" style="margin-left: 100px">
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
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20" style="margin-top: 60px">
              <el-form-item label="警情内容" label-width="100px" align="center" prop="configKey">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 2, maxRows: 5}"
                  placeholder="请输入内容"
                  v-model="textarea2">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>


          <el-row>
            <el-col :span="20">
              <el-form-item label="报警人备注" label-width="100px" align="center" prop="configKey">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 2, maxRows: 5}"
                  placeholder="请输入内容"
                  v-model="textarea2">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="10">
              <el-form-item label="警员" label-width="100px" align="center" prop="configKey">
                <el-input v-model="form.configKey" placeholder="请输入参数键名" />
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-divider></el-divider>
        <el-button @click="cancel">取 消</el-button>
        <el-button @click="cancel">保 存</el-button>
        <el-button type="primary" @click="submitForm">创建任务单</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listAlarm} from "@/api/table";

    export default {
      name: "AlarmList",
      data(){
        return {
          q:'',
          // 参数表格数据
          alarmList: [
            {
              tapeId:'12321'
            }
          ],
          // 查询参数
          queryParams: {
            pageNum: 1,
            pageSize: 10,
            configName: undefined,
            configKey: undefined,
            configType: undefined
          },
          // 总条数
          total: 0,
          // 弹出层标题
          title: "新建任务单",
          // 是否显示弹出层
          open: false,
          // 表单参数
          form: {},
          // 表单校验
          rules: {
            configName: [
              { required: true, message: "参数名称不能为空", trigger: "blur" }
            ],
            configKey: [
              { required: true, message: "参数键名不能为空", trigger: "blur" }
            ],
            configValue: [
              { required: true, message: "参数键值不能为空", trigger: "blur" }
            ]
          },
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


        }
      },
      created() {
        this.getList()
      },
      methods: {
        /** 查询参数列表 */
        getList() {
          listAlarm().then(response => {
            debugger
            this.alarmList = response.data.items
          })
        },
        handleUpdate(){
          this.open = true;
        },
        /** 提交按钮 */
        submitForm: function() {

        },
        // 取消按钮
        cancel() {
          this.open = false;
        },
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
        // submit () {
        //   this.$emit('callback', this.marker.position)
        //   this.dialogVisible = false
        // },
      }

    }
</script>

<style scoped>
  .amap-page-container {
    margin-top: -50px;
    width: 400px;
    height: 300px;

  }
  .el-dialog{
    display: flex;
    flex-direction: column;
    margin:0 !important;
    position:absolute;
    top:50%;
    left:50%;
    transform:translate(-50%,-50%);
    /*height:600px;*/
    max-height:calc(100% - 30px);
    max-width:calc(100% - 30px);
  }
  .el-dialog .el-dialog__body{
    flex:1;
    overflow: auto;
  }
  .amap-demo {
    height: 250px;
    width: 400px;
    margin-bottom: 20px;
  }
  .search-box {
    position: relative;
    top: 60px;
    left: 20px;
  }
  .amap-logo{
    display: none;
  }
  .amap-copyright {
    opacity:0;
  }

</style>
