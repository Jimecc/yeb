<template>
  <div>
    <el-input
        prefix-icon="el-icon-search"
        placeholder="请输入部门名称进行搜索..."
        v-model="filterText">
    </el-input>

    <el-tree
        style="width: 500px"
        class="filter-tree"
        :data="deps"
        :props="defaultProps"
        :filter-node-method="filterNode"
        ref="tree">
    </el-tree>
  </div>
</template>

<script>
export default {
  name: "DepMana",
  data(){
    return{
      filterText:'',
      deps:[],
      defaultProps:{
        children:'children',
        label:'name'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },
  mounted() {
    this.intiDeps();
  },
  methods:{
    intiDeps(){
      this.getRequest('/system/basic/department/').then(resp=>{
        if(resp){
          this.deps = resp;
        }
      })
    },
    filterNode(value,data){
      if(!value) return true;
      return data.name.indexOf(value) !== -1;
    }
  }
}
</script>

<style scoped>

</style>
