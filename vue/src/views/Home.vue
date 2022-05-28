<template>
<div>
  <el-container>
    <el-header>Header</el-header>
    <el-container>

      <el-aside width="200px">
        <el-menu router>
        <el-submenu index="1" v-for="(item,index) in this.$router.options.routes"
                    :key="index"
                    v-if="!item.hidden">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>{{item.name}}</span>
          </template>
            <el-menu-item :index="children.path"
                          v-for="(children,indexj) in item.children">
                {{children.name}}
            </el-menu-item>
        </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <router-view/>
        </el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </el-container>
  </el-container>
</div>
</template>

<script>
export default {
  name: "Home"
}
</script>

<style scoped>

</style>
