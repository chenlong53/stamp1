<template>
    <div class="app-container">
      <el-row :gutter="20">
        <el-col :span="6" :xs="24">
      <el-card :body-style="{ padding: '0px' }" class="box-card">
        <div slot="header" style="height: 55px" class="clearfix">
          <span style="float: left;margin-top: 10px">任务单</span>
          <el-button type="primary" style="float: right; " >  新建任务单</el-button>
        </div>
        <div class="component-item" style="height:420px;">
      <dropdown-menu :items="articleList" style="margin:0 auto;" />
        </div>
      </el-card>
        </el-col >

        <el-col :span="18" :xs="24">

          <el-row>
            <el-card class="box-one">

                <el-form  size="small" label-width="80px" label-position="right">
                   <el-row :gutter="6">
                    <el-col  :span="4">
                    <el-form-item label="状态:" label-width="50px">
                      <el-select placeholder="请选择" clearable>

                      </el-select>
                    </el-form-item>
                    </el-col>
                     <el-col :span="8">
                       <el-form-item label="辖区派出所:" label-width="90px">
                         <el-input  size="small">

                         </el-input>
                       </el-form-item>

                     </el-col>

                     <el-col :span="4">
                       <el-form-item label="警员:" label-width="50px">
                         <el-input  size="small">

                         </el-input>
                       </el-form-item>

                     </el-col>

                     <el-col :span="7">
                       <el-form-item label="报警人手机号:" label-width="100px">
                         <el-input  size="small">

                         </el-input>
                       </el-form-item>

                     </el-col>
                   </el-row>
                   <el-row :gutter="6">
                     <el-col :span="10">
                       <el-form-item label="选择日期:" label-width="80px">
                       <el-date-picker
                         v-model="dateRange"
                         size="small"
                         value-format="yyyy-MM-dd"
                         type="daterange"
                         range-separator="-"
                         start-placeholder="开始日期"
                         end-placeholder="结束日期"
                       ></el-date-picker>
                       </el-form-item>
                     </el-col>

                        <el-col :span="5">
                       <el-form-item>
                         <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                       </el-form-item>
                        </el-col>
                   </el-row>
                </el-form>



            </el-card>
          </el-row>

          <el-row>


              <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column
                  prop="date"
                  label="任务ID"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="name"
                  label="报警人手机号"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="address"
                  label="报警内容">
                </el-table-column>
                <el-table-column
                  prop="address"
                  label="状态">
                </el-table-column>
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-edit"
                      @click="toDetail(scope.row)"
                    >查看详情</el-button>
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-edit"
                      @click="handleUpdate(scope.row)"
                    >分配</el-button>
                    <el-button
                      v-show="true"
                      size="mini"
                      type="text"
                      icon="el-icon-edit"
                      @click="handleUpdate(scope.row)"
                    >发消息</el-button>
                  </template>

                </el-table-column>
              </el-table>


          </el-row>

        </el-col>
      </el-row>
    </div>
</template>

<script>
    import DropdownMenu from '@/components/Share/DropdownMenu'
    export default {
        name: "AlarmTask",
        components: {
         DropdownMenu
        },
        data() {
          return {
            articleList: [
              { title: '全部任务单(1000)', href: 'https://juejin.im/post/59097cd7a22b9d0065fb61d2',id:0 },
              { title: '待分派(100)', href: 'https://juejin.im/post/591aa14f570c35006961acac',id:1 },
              { title: '出警中(100)', href: 'https://juejin.im/post/595b4d776fb9a06bbe7dba56',id:2 },
              { title: '已完结(100)', href: 'https://juejin.im/post/5c92ff94f265da6128275a85',id:3 },
            ],
            tableData: [{
              date: '2016-05-02',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1518 弄'
            }, {
              date: '2016-05-04',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1517 弄'
            }, {
              date: '2016-05-01',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1519 弄'
            }, {
              date: '2016-05-03',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1516 弄'
            }]
          }
        },
      created() {
      },
      methods :{
        toDetail(value){
          this.$router.push({ path: "/taskDetail", query: { value } });
        }
      }
    }
</script>

<style lang="scss" scoped>
  .box-card {
    width: 400px;
    max-width: 100%;
    margin: 20px auto;
  }
  .component-item{
    min-height: 100px;
  }
  .box-one {
    width: 100%;
    max-width: 100%;
    margin: 20px auto;
  }
</style>
