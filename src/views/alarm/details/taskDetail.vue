<template>
    <div class="app-container">

      <el-row :gutter="20">
        <el-col :span="15" :xs="24">
          <el-card style="height: 700px">
            <div class="task-details">
            <el-tabs v-model="activeTab">
              <el-tab-pane label="聊天记录" name="activity">
                <div class="chat-wrapper">
                  <section class="chat-item" v-for="(v, i) in message" :key="i">
                    <section class="avator">
                      <img class="img-circle" :src="'https://wpimg.wallstcn.com/57ed425a-c71e-4201-9428-68760c0537c4.jpg'+'?imageView2/1/w/80/h/80'">
                    </section>
                    <section class="content-box">
                      <section class="info">
                        <span style="font-weight:bold">{{ v.name }}</span><span style="font-size:10px"> {{ v.time }}</span>
                      </section>
                      <section class="content">
                        <p>{{ v.desc }}</p>
                      </section>
                    </section>
                  </section>
                </div>
              </el-tab-pane>
              <el-tab-pane label="操作日志" name="second">
                <div class="chat-wrapper">
                  <section class="chat-item" v-for="(v, i) in logList" :key="i">
                    <section class="avator">
                      <img  :src="'https://wpimg.wallstcn.com/57ed425a-c71e-4201-9428-68760c0537c4.jpg'+'?imageView2/1/w/80/h/80'">
                    </section>
                    <section class="content-box">
                      <section class="info">
                        <span style="color:#1890ff">{{ v.name }}</span><span style="font-size:10px"> {{ v.time }}</span>
                      </section>
                      <section class="content">
                        <p>{{ v.desc }}</p>
                      </section>
                    </section>
                  </section>
                </div>
              </el-tab-pane>
            </el-tabs>
            </div>
          </el-card>
        </el-col>

        <el-col :span="9" :xs="24">
         <el-card >
           <div slot="header" class="clearfix">
             <span>任务单信息</span>
             <el-button style="float: right; padding: 3px 0" type="text">出警中</el-button>
           </div>
           <el-row :gutter="0">
             <el-col :span="6">
               <span style="font-size: 10px">报警人</span>
             </el-col>

             <el-col :span="8">
                <span style="font-size: 10px">张先生</span>
             </el-col>

           </el-row>
           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">报警人电话</span>
             </el-col>

             <el-col :span="8">
               <span style="font-size: 10px">张先生</span>
             </el-col>

           </el-row>
           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">警情内容</span>
             </el-col>

             <el-col :span="18">
               <span style="font-size: 10px">报警人目睹XX小区内有人打架斗殴，已发生流血事件</span>
             </el-col>

           </el-row>

           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">报警地址</span>
             </el-col>

             <el-col :span="18">
               <span style="font-size: 10px">浙江省西湖区三墩镇</span>
             </el-col>

             <el-col :span="18" style="float: right">
               <el-amap vid="test" :zoom="zoom" :center="center" class="amap-demo">
                 <el-amap-marker :position="center" :vid="1"></el-amap-marker>
               </el-amap>
             </el-col>

           </el-row>

           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">来源</span>
             </el-col>

             <el-col :span="18">
               <span style="font-size: 10px">短信/电话</span>
             </el-col>

           </el-row>

           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">指派出警人</span>
             </el-col>

             <el-col :span="18">
               <span style="font-size: 10px">李警官 0571110</span>
             </el-col>

           </el-row>

           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">出警人电话</span>
             </el-col>

             <el-col :span="18">
               <span style="font-size: 10px">李警官 0571110</span>
             </el-col>

           </el-row>

           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">任务单号</span>
             </el-col>

             <el-col :span="18">
               <span style="font-size: 10px">李警官 0571110</span>
             </el-col>

           </el-row>

           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">创建时间</span>
             </el-col>

             <el-col :span="18">
               <span style="font-size: 10px">李警官 0571110</span>
             </el-col>

           </el-row>

           <el-row :gutter="0" style="margin-top: 5px">
             <el-col :span="6">
               <span style="font-size: 10px">更新时间</span>
             </el-col>

             <el-col :span="18">
               <span style="font-size: 10px">李警官 0571110</span>
             </el-col>

           </el-row>


         </el-card>
        </el-col>

      </el-row>

    </div>
</template>

<script>
    export default {
      name: "TaskDetail",
      data() {
          return {
            activeTab: 'activity',
            message: [
              {
                id: 1,
                name: "170000000",
                time: "11月13日 周三 下午11:04",
                desc:
                  "我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。",
              },
              {
                id: 1,
                name: "170000000",
                time: "11月13日 周三 下午11:04",
                desc:
                  "我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。",
              },
              {
                id: 1,
                name: "170000000",
                time: "11月13日 周三 下午11:04",
                desc:
                  "我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。",
              },
              {
                id: 1,
                name: "170000000",
                time: "11月13日 周三 下午11:04",
                desc:
                  "我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。",
              },
              {
                id: 1,
                name: "170000000",
                time: "11月13日 周三 下午11:04",
                desc:
                  "我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。",
              },
              {
                id: 1,
                name: "170000000",
                time: "11月13日 周三 下午11:04",
                desc:
                  "我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。我在XX小区这里，有人闹矛盾打架，请警察叔叔过来处理一下。",
              },


            ],
            logList: [
              {
                id: 1,
                name: "接警员",
                time: "11月13日 周三 下午11:04",
                desc:
                  "创建了任务单。",
              },
              {
                id: 1,
                name: "接警员",
                time: "11月13日 周三 下午11:04",
                desc:
                  "已报结，报结内容：已妥善处置该警情，调解群众矛盾完毕。",
              },
              {
                id: 1,
                name: "李警官",
                time: "11月13日 周三 下午11:04",
                desc:
                  "创建了任务单。",
              },
              {
                id: 1,
                name: "李警官",
                time: "11月13日 周三 下午11:04",
                desc:
                  "已报结，报结内容：已妥善处置该警情，调解群众矛盾完毕。",
              },
            ],
            zoom: 14,
            center: [121.5273285, 31.21515044],
          }
        },

        created() {
        },

        methods :{

        },
        mounted(){

        }

    }
</script>

<style lang="scss" scoped>
  .amap-demo {
    height: 100px;
  }
  .map-container {
    height: 300px;
    flex: 1;
  }
  .task-details {
    .chat-wrapper {
      .chat-item {
        display: flex;
        margin-bottom: 20px;
        .avator {
          width: 50px;
          margin-right: 10px;
          & > img {
            width: 100%;
          }
        }
        .content-box {
          flex: 1;
          // background-color: cyan;
          text-align: left;
          border-bottom: 1px solid #ccc;

          .content {
            display: -webkit-box;
            -webkit-line-clamp: 2;
            overflow: hidden;
            text-overflow: ellipsis;
            -webkit-box-orient: vertical;
          }
        }
      }
    }
  }
</style>
