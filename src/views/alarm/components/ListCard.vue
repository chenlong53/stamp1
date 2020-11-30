<template>
  <el-card style="margin-bottom:20px;">
    <div slot="header" class="clearfix">
      <span>任务单</span>
    </div>

    <div class="user-bio">
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header"></div>
        <div class="user-bio-section-body">
          <div class="text-muted">
            全部任务单
          </div>
        </div>
      </div>
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header"></div>
        <div class="user-bio-section-body">
          <div class="text-muted">
            待分派
          </div>
        </div>
      </div>
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header"></div>
        <div class="user-bio-section-body">
          <div class="text-muted">
            待出警
          </div>
        </div>
      </div>
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header"></div>
        <div class="user-bio-section-body">
          <div class="text-muted">
            出警中
          </div>
        </div>
      </div>
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header"></div>
        <div class="user-bio-section-body">
          <div class="text-muted">
            已完结
          </div>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script>
    export default {
        name: "ListCard"
    }
</script>

<style lang="scss" scoped>
  .user-bio {
    margin-top: 20px;
    color: #606266;

  span {
    padding-left: 4px;
  }

  .user-bio-section {
    font-size: 14px;
    padding: 15px 0;

  .user-bio-section-header {
    border-bottom: 1px solid #dfe6ec;
    padding-bottom: 10px;
    margin-bottom: 10px;
    font-weight: bold;
  }
  }
  }
</style>
