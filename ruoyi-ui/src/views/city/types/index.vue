<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入类型名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入类型描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['city:types:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['city:types:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['city:types:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['city:types:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="typesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="类型ID" align="center" prop="id" />
      <el-table-column label="类型名称" align="center" prop="name" />
      <el-table-column label="类型描述" align="center" prop="description" />
      <el-table-column label="状态：0-禁用, 1-启用" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['city:types:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['city:types:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改工单类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入类型名称" />
        </el-form-item>
        <el-form-item label="类型描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入类型描述" />
        </el-form-item>
        <el-divider content-position="center">工单子类型信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddReportSubtypes">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteReportSubtypes">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="reportSubtypesList" :row-class-name="rowReportSubtypesIndex" @selection-change="handleReportSubtypesSelectionChange" ref="reportSubtypes">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="子类型名称" prop="name" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.name" placeholder="请输入子类型名称" />
            </template>
          </el-table-column>
          <el-table-column label="子类型描述" prop="description" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.description" placeholder="请输入子类型描述" />
            </template>
          </el-table-column>
          <el-table-column label="状态：0-禁用, 1-启用" prop="status" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.status" placeholder="请选择状态">
                <el-option label="启用" :value="1" />
                <el-option label="禁用" :value="0" />
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTypes, getTypes, delTypes, addTypes, updateTypes } from "@/api/city/types";

export default {
  name: "Types",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedReportSubtypes: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 工单类型表格数据
      typesList: [],
      // 工单子类型表格数据
      reportSubtypesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        description: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "类型名称不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态：0-禁用, 1-启用不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工单类型列表 */
    getList() {
      this.loading = true;
      listTypes(this.queryParams).then(response => {
        this.typesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        description: null,
        status: null,
        createTime: null,
        updateTime: null
      };
      this.reportSubtypesList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工单类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTypes(id).then(response => {
        this.form = response.data;
        this.reportSubtypesList = response.data.reportSubtypesList;
        this.open = true;
        this.title = "修改工单类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.reportSubtypesList = this.reportSubtypesList;
          if (this.form.id != null) {
            updateTypes(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTypes(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除工单类型编号为"' + ids + '"的数据项？').then(function() {
        return delTypes(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 工单子类型序号 */
    rowReportSubtypesIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 工单子类型添加按钮操作 */
    handleAddReportSubtypes() {
      let obj = {};
      obj.name = "";
      obj.description = "";
      obj.status = "";
      this.reportSubtypesList.push(obj);
    },
    /** 工单子类型删除按钮操作 */
    handleDeleteReportSubtypes() {
      if (this.checkedReportSubtypes.length == 0) {
        this.$modal.msgError("请先选择要删除的工单子类型数据");
      } else {
        const reportSubtypesList = this.reportSubtypesList;
        const checkedReportSubtypes = this.checkedReportSubtypes;
        this.reportSubtypesList = reportSubtypesList.filter(function(item) {
          return checkedReportSubtypes.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleReportSubtypesSelectionChange(selection) {
      this.checkedReportSubtypes = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('city/types/export', {
        ...this.queryParams
      }, `types_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
