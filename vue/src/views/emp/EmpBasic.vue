<template>
<div>
  <div style="margin-top:10px;display:flex;justify-content: space-between">
    <div>
      <el-input style="width: 400px;margin-right: 10px" v-model="empName" @clear="initEmps" clearable @keydown.enter.native="initEmps" prefix-icon="el-icon-search" placeholder="请输入员工姓名进行搜索..."></el-input>
      <el-button type="primary" icon="el-icon-search" @click="initEmps">搜索</el-button>
      <el-button type="primary">
        <i class="fa fa-angle-double-down" aria-hidden="true"></i>高级搜索</el-button>
    </div>
    <div>
      <el-button type="success"><i class="fa fa-level-up" aria-hidden="true"></i>导入数据</el-button>
      <el-button type="success"><i class="fa fa-level-down" aria-hidden="true"></i>导出数据</el-button>
      <el-button type="primary" icon="el-icon-plus" @click="showAddEmpView">添加员工</el-button>
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
            width="180"
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
            width="100"
            label="籍贯">
        </el-table-column>
        <el-table-column
            prop="politicsStatus.name"
            width="120"
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
          width="300"
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
          width="150"
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
          <el-tag style="width: 60px;">{{scope.row.contractTerm}}</el-tag>年
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
          @size-change="sizeChange"
          layout="sizes,prev, pager, next,jumper,->,total"
          :total="total">
      </el-pagination>
    </div>
  </div>
  <el-dialog
      title="添加员工"
      :visible.sync="dialogVisible"
      width="80%">
    <div>
      <el-form ref="empForm" :model="emp">
        <el-row :gutter="0">
          <el-col :span="6">
            <el-form-item label="姓名" prop="name">
              <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="emp.name" placeholder="请输入员工姓名..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="emp.gender" style="margin-top: 8px">
                <el-radio label="男">男</el-radio>
                <el-radio label="女">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出生日期" prop="birthday">
              <el-date-picker
                  v-model="emp.birthday"
                  type="date"
                  size="mini"
                  style="width: 150px"
                  value-format="yyyy-MM-dd"
                  placeholder="出生日期">
              </el-date-picker>
            </el-form-item>

          </el-col>
          <el-col :span="7">
            <el-form-item label="政治面貌" prop="politicId">
              <el-select size="mini" style="width: 200px" v-model="emp.politicId" placeholder="政治面貌">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="民族" prop="nationId">
              <el-select size="mini" style="width: 150px" v-model="emp.nationId" placeholder="民族">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="籍贯" prop="nativePlace">
              <el-input v-model="emp.nativePlace" plaveholder="请输入籍贯..." size="mini" style="width: 150px" prefix-icon="el-icon-edit"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电子邮箱" prop="email">
              <el-input v-model="emp.email" placeholder="请输入电子邮箱..." size="mini" style="width: 150px" prefix-icon="el-icon-message" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="联系地址" prop="address">
              <el-input v-model="emp.assign" placeholder="请输入通讯地址" size="mini" style="width: 200px" prefix-icon="el-icon-edit"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="职位" prop="posId">
              <el-select size="mini" style="width: 150px" v-model="emp.posId" placeholder="职位">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="职称" prop="jobLevelId">
              <el-select size="mini" style="width: 150px" v-model="emp.jobLevelId" placeholder="职称">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="所属部门" prop="departmentId">
              <el-input v-model="emp.departmentId" placeholder="请输入部门..." size="mini" style="width: 150px" prefix-icon="el-icon-edit"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="电话号码" prop="phone">
              <el-input v-model="emp.phone" placeholder="请输入电话号码..." size="mini" style="width: 200px" prefix-icon="el-icon-phone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="工号" prop="workID">
              <el-input v-model="emp.workID" placeholder="请输入工号..." size="mini" style="width: 150px " prefix-icon="el-icon-edit"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="学历" prop="tiptopDegree">
              <el-select size="mini" style="width: 150px" v-model="emp.tiptopDegree" placeholder="学历">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="毕业院校" prop="school">
              <el-input v-model="emp.school" placeholder="请输入学校..." size="mini" style="width: 150px" prefix-icon="el-icon-edit"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="毕业院校" prop="specialty">
              <el-input v-model="emp.specialty" placeholder="请输入专业名称..." size="mini" style="width: 200px" prefix-icon="el-icon-edit"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="入职日期" prop="beginDate">
              <el-date-picker
                  v-model="emp.beginDate"
                  type="date"
                  size="mini"
                  style="width: 165px"
                  value-format="yyyy-MM-dd"
                  placeholder="入职日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="转正日期" prop="conversionTime">
              <el-date-picker
                  v-model="emp.conversionTime"
                  type="date"
                  size="mini"
                  style="width: 165px"
                  value-format="yyyy-MM-dd"
                  placeholder="转正日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="合同起始日期" prop="beginContract">
              <el-date-picker
                  v-model="emp.beginContract"
                  type="date"
                  size="mini"
                  style="width: 165px"
                  value-format="yyyy-MM-dd"
                  placeholder="合同起始日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="合同截止日期" prop="endContract">
              <el-date-picker
                  v-model="emp.endContract"
                  type="date"
                  size="mini"
                  style="width: 170px"
                  value-format="yyyy-MM-dd"
                  placeholder="合同截止日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="身份证号码" prop="idCard">
              <el-input v-model="emp.idCard" placeholder="请输入身份证号码..." size="mini" style="width: 180px" prefix-icon="el-icon-edit"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="聘用形式" prop="engageForm">
              <el-radio-group v-model="emp.engageForm" style="margin-top: 8px">
                <el-radio label="劳动合同">劳动合同</el-radio>
                <el-radio label="劳务合同">劳务合同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="婚姻状况" prop="wedlock">
              <el-radio-group v-model="emp.wedlock" style="margin-top: 8px">
                <el-radio label="已婚">已婚</el-radio>
                <el-radio label="未婚">未婚</el-radio>
                <el-radio label="离异">离异</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>

    </div>

    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
  </el-dialog>
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
      size:10,
      empName:'',
      dialogVisible: true,
      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      emp:{
        id: 1,
        name: '',
        gender: '',
        birthday: '',
        idCard: '',
        wedlock: '',
        nationId: null,
        nativePlace: '',
        politicId: null,
        email: '',
        phone: '',
        address: '',
        departmentId: null,
        jobLevelId: null,
        posId: null,
        engageForm: '',
        tiptopDegree: '',
        specialty: '',
        school: '',
        beginDate: '',
        workState: '',
        workID: '',
        contractTerm: null,
        conversionTime: '',
        notWorkDate: null,
        beginContract: '',
        endContract: '',
        workAge: null,
        salaryId: null
      }
    }
  },
  mounted() {
    this.initEmps();
  },
  methods:{
    showAddEmpView(){
      this.dialogVisible = true;
    },
    sizeChange(size){
      this.size=size;
      this.initEmps();
    },
    currentChange(currentPage){
      this.page = currentPage;
      this.initEmps();
    },
    initEmps(){
      this.loading = true;
      this.getRequest('/employee/basic/?currentPage='+this.page+'&size='+this.size+'&name='+this.empName).then(resp=>{
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
