<template>
<div>
  <div style="margin-top:10px;display:flex;justify-content: space-between">
    <div>
      <el-input style="width: 400px;margin-right: 10px" prefix-icon="el-icon-search" placeholder="请输入员工姓名进行搜索..."></el-input>
      <el-button type="primary" icon="el-icon-search">搜索</el-button>
      <el-button type="primary">
        <i class="fa fa-angle-double-down" aria-hidden="true"></i>高级搜索</el-button>
    </div>
    <div>
      <el-button type="success"><i class="fa fa-level-up" aria-hidden="true"></i>导入数据</el-button>
      <el-button type="success"><i class="fa fa-level-down" aria-hidden="true"></i>导出数据</el-button>
      <el-button type="primary" icon="el-icon-plus">添加员工</el-button>
    </div>
  </div>
  <div style="margin-top: 20px">
    <el-divider></el-divider>
    <el-table
        v-loading="loading"
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        :data="emps"
        stripe
        border
        style="width: 100%">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          fixed
          align="left"
          width="90">
      </el-table-column>
      <el-table-column
          prop="workID"
          label="工号"
          align="left"
          width="85">
      </el-table-column>
      <el-table-column
          prop="gender"
          width="50"
          align="left"
          label="性别">
      </el-table-column>
        <el-table-column
            prop="birthday"
            width="95"
            align="left"
            label="出生日期">
        </el-table-column>
        <el-table-column
            prop="idCard"
            width="150"
            align="left"
            label="身份证号">
        </el-table-column>
        <el-table-column
            prop="wedlock"
            width="70"
            label="婚姻状况">
        </el-table-column>
        <el-table-column
            prop="nation.name"
            width="50"
            label="民族">
        </el-table-column>
        <el-table-column
            prop="nativePlace"
            width="80"
            label="籍贯">
        </el-table-column>
        <el-table-column
            prop="politicsStatus.name"
            width="100"
            label="政治面貌">
        </el-table-column>
        <el-table-column
            prop="email"
            width="180"
            align="left"
            label="邮箱">
        </el-table-column>
      <el-table-column
          prop="phone"
          width="100"
          align="left"
          label="电话号码">
      </el-table-column>
      <el-table-column
          prop="email"
          width="180"
          align="left"
          label="邮箱">
      </el-table-column>
      <el-table-column
          prop="address"
          width="250"
          align="left"
          label="通讯地址">
      </el-table-column>
      <el-table-column
          prop="department.name"
          width="100"
          align="left"
          label="部门">
      </el-table-column>
      <el-table-column
          prop="joblevel.name"
          width="100"
          label="职称">
      </el-table-column>
      <el-table-column
          prop="position.name"
          width="100"
          label="职位">
      </el-table-column>
      <el-table-column
          prop="engageForm"
          width="100"
          align="left"
          label="聘用形式">
      </el-table-column>
      <el-table-column
          prop="tiptopDegree"
          width="80"
          label="最高学历">
      </el-table-column>
      <el-table-column
          prop="school"
          width="100"
          align="left"
          label="毕业学校">
      </el-table-column>
      <el-table-column
          prop="specialty"
          width="150"
          align="left"
          label="专业">
      </el-table-column>
      <el-table-column
          prop="workState"
          width="70"
          align="left"
          label="在职状态">
      </el-table-column>
      <el-table-column
          prop="beginDate"
          width="95"
          align="left"
          label="入职如期">
      </el-table-column>
      <el-table-column
          prop="conversionTime"
          width="95"
          align="left"
          label="转正日期">
      </el-table-column>
      <el-table-column
          prop="beginContract"
          width="95"
          align="left"
          label="合同起始日期">
      </el-table-column>
      <el-table-column
          prop="endContract"
          width="95"
          align="left"
          label="合同截止日期">
      </el-table-column>
      <el-table-column
          width="100"
          align="left"
          label="合同期限">
        <template slot-scope="scope">
          <el-tag>{{scope.row.contractTerm}}</el-tag>年
        </template>
      </el-table-column>
      <el-table-column
          width="200"
          label="操作"
          fixed="right">
        <template slot-scope="scope">
          <el-button style="padding:3px" size="mini">编辑</el-button>
          <el-button style="padding:3px" size="mini">查看高级资料</el-button>
          <el-button style="padding:3px" size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="display: flex;justify-content: flex-end">
      <el-pagination
          style="margin-top: 10px"
          background
          @current-change="currentChange"
          layout="sizes,prev, pager, next,jumper,->,total"
          :total="total">
      </el-pagination>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "EmpBasic",
  data(){
    return{
      emps:[],
      loading:false,
      total:0,
      page:1,
      size:10
    }
  },
  mounted() {
    this.initEmps();
  },
  methods:{
    currentChange(currentPage){
      this.page = currentPage;
      this.initEmps();
    },
    initEmps(){
      this.loading = true;
      this.getRequest('/employee/basic/?currentPage='+this.page+'&size='+this.size).then(resp=>{
        this.loading=false;
        if(resp){
          this.total = resp.total;
          this.emps = resp.data;
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
