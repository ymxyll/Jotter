<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span style="font-weight: bold;font-size: 20px">最近更新</span>
      <!--<el-button style="float: right; padding: 3px 0" type="text">修改</el-button>-->
    </div>
    <div class="block">
      <el-timeline>
        <el-timeline-item timestamp="2023/5/12" placement="top">
          <el-card>
            <h4>前端MarkDown编辑器组件嵌入</h4>
            <p>ZLY 提交于 2023/5/12 23:31</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2023/4/10" placement="top">
          <el-card>
            <h4>实现文章修改功能</h4>
            <p>YLL 提交于 2023/5/10 23:26</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </el-card>
</template>

<script>
  export default {
    name: 'UpdateCard'
  }
</script>

<style scoped>
  .box-card {
    margin-top: 5px;
    width: 988px;
    height: 1050px;
    text-align: left;
  }
</style>
