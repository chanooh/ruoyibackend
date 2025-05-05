<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入工单标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入详细地址"
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
          v-hasPermi="['city:reports:add']"
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
          v-hasPermi="['city:reports:edit']"
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
          v-hasPermi="['city:reports:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['city:reports:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单ID" align="center" prop="id" />
      <el-table-column label="提交用户ID" align="center" prop="userId" />
      <el-table-column label="工单类型ID" align="center" prop="typeId" />
      <el-table-column label="工单标题" align="center" prop="title" />
      <el-table-column label="工单描述" align="center" prop="description" />
      <el-table-column label="详细地址" align="center" prop="address" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ statusMap[scope.row.status] || scope.row.status }}
        </template>
      </el-table-column>
      <el-table-column label="图片" align="center">
        <template slot-scope="scope">
          <div v-if="scope.row.imageUrls && scope.row.imageUrls.length">
            <el-image
              v-for="(url, index) in scope.row.imageUrls"
              :key="index"
              style="width: 50px; height: 50px; object-fit: cover; margin-right: 5px;"
              :src="url"
              :preview-src-list="scope.row.imageUrls"
              fit="cover"
              :lazy="true"
              @error="handleImageError"
            >
              <div slot="error" class="image-error">无效图片</div>
            </el-image>
          </div>
          <span v-else>无图片</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['city:reports:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['city:reports:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-share"
            @click="handleAssign(scope.row)"
            v-hasPermi="['city:reports:assign']"
          >分配</el-button>
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

    <!-- 添加或修改工单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="提交用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入提交用户ID" />
        </el-form-item>
        <el-form-item label="工单类型ID" prop="typeId">
          <el-input v-model="form.typeId" placeholder="请输入工单类型ID" />
        </el-form-item>
        <el-form-item label="工单标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入工单标题" />
        </el-form-item>
        <el-form-item label="工单描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="pending">待处理</el-radio>
            <el-radio label="processing">处理中</el-radio>
            <el-radio label="completed">已完成</el-radio>
            <el-radio label="rejected">已拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider content-position="center">工单图片信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddReportImages">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteReportImages">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="reportImagesList" :row-class-name="rowReportImagesIndex" @selection-change="handleReportImagesSelectionChange" ref="reportImages">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="图片URL" align="center" prop="imageUrl">
            <template slot-scope="scope">
              <el-input v-model="scope.row.imageUrl" placeholder="请输入图片URL" />
            </template>
          </el-table-column>
          <el-table-column label="预览" align="center" width="100">
            <template slot-scope="scope">
              <el-image
                v-if="scope.row.imageUrl"
                style="width: 50px; height: 50px; object-fit: cover;"
                :src="scope.row.imageUrl"
                :preview-src-list="[scope.row.imageUrl]"
                fit="cover"
                :lazy="true"
                @error="handleImageError"
              >
                <div slot="error" class="image-error">无效</div>
              </el-image>
              <span v-else>无</span>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 分配对话框 -->
    <el-dialog title="工单分配" :visible.sync="assignVisible" width="500px">
      <el-form ref="assignForm" :model="assignForm" label-width="80px">
        <el-form-item label="分配目标" prop="role">
          <el-select 
            v-model="assignForm.role"
            placeholder="请选择工作组"
            style="width: 100%"
          >
            <el-option
              v-for="item in workGroups"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="处理时限" prop="deadline">
          <el-date-picker
            v-model="assignForm.deadline"
            type="datetime"
            placeholder="选择截止时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            style="width: 100%"
          />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="assignVisible = false">取消</el-button>
        <el-button type="primary" @click="submitAssign">确认分配</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReports, getReports, delReports, addReports, updateReports, assignReport } from "@/api/city/reports";

export default {
  name: "Reports",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedReportImages: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 工单管理表格数据
      reportsList: [],
      // 工单图片表格数据
      reportImagesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态映射
      statusMap: {
        pending: '待处理',
        processing: '处理中',
        completed: '已完成',
        rejected: '已拒绝'
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        description: null,
        address: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "提交用户ID不能为空", trigger: "blur" },
          { pattern: /^\d+$/, message: "提交用户ID必须为数字", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "工单类型ID不能为空", trigger: "blur" },
          { pattern: /^\d+$/, message: "工单类型ID必须为数字", trigger: "blur" }
        ],
        title: [
          { required: true, message: "工单标题不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "详细地址不能为空", trigger: "blur" }
        ],
        latitude: [
          { pattern: /^-?\d+(\.\d+)?$/, message: "请输入有效的纬度", trigger: "blur" }
        ],
        longitude: [
          { pattern: /^-?\d+(\.\d+)?$/, message: "请输入有效的经度", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ]
      },
      assignVisible: false,
      assignForm: {
        reportId: null,
        role: null,
        deadline: null
      },
      workGroups: [
        { value: 'road_quality_group', label: '道路品质组' },
        { value: 'lighting_quality_group', label: '照明品质组' },
        { value: 'city_appearance_group', label: '市容品质组' },
        { value: 'landscape_group', label: '园林品质组' },
        { value: 'block_quality_group', label: '街区品质组' }
      ]
      };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工单管理列表 */
    getList() {
      this.loading = true;
      listReports(this.queryParams).then(response => {
        // 为每条记录添加 imageUrls
        this.reportsList = response.rows.map(report => ({
          ...report,
          imageUrls: report.reportImagesList ? report.reportImagesList.map(img => img.imageUrl) : []
        }));
        this.total = response.total;
        this.loading = false;
        console.log(this.reportsList)
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
        userId: null,
        typeId: null,
        title: null,
        description: null,
        address: null,
        latitude: null,
        longitude: null,
        status: 'pending', // 默认待处理
        createTime: null,
        updateTime: null
      };
      this.reportImagesList = [];
      this.checkedReportImages = [];
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
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getReports(id).then(response => {
        this.form = response.data;
        this.reportImagesList = response.data.reportImagesList || [];
        this.reportImagesList = this.reportImagesList.map((item, index) => ({
          ...item,
          index: index + 1
        }));
        this.open = true;
        this.title = "修改工单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.reportImagesList = this.reportImagesList.map(item => ({
            imageUrl: item.imageUrl
          }));
          if (this.form.id != null) {
            updateReports(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReports(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除工单管理编号为"' + ids + '"的数据项？').then(function() {
        return delReports(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 工单图片序号 */
    rowReportImagesIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
      return '';
    },
    /** 工单图片添加按钮操作 */
    handleAddReportImages() {
      this.reportImagesList.push({
        index: this.reportImagesList.length + 1,
        imageUrl: ''
      });
    },
    /** 工单图片删除按钮操作 */
    handleDeleteReportImages() {
      if (this.checkedReportImages.length === 0) {
        this.$modal.msgError("请先选择要删除的工单图片数据");
      } else {
        this.reportImagesList = this.reportImagesList.filter(item => 
          !this.checkedReportImages.includes(item.index)
        );
        // 重新计算序号
        this.reportImagesList = this.reportImagesList.map((item, index) => ({
          ...item,
          index: index + 1
        }));
      }
    },
    /** 复选框选中数据 */
    handleReportImagesSelectionChange(selection) {
      this.checkedReportImages = selection.map(item => item.index);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('city/reports/export', {
        ...this.queryParams
      }, `reports_${new Date().getTime()}.xlsx`);
    },
    /** 处理图片加载失败 */
    handleImageError() {
      console.log('图片加载失败');
    },


    handleAssign(row) {
      this.assignForm = {
        // reportId: row.id,
        role: row.assigned_to || null,
        deadline: null
      }
      this.assignVisible = true
    },

    submitAssign() {
      console.log(123)
      this.$refs.assignForm.validate(valid => {
        if (valid) {
          assignReport(this.assignForm).then(response => {
            this.$modal.msgSuccess("分配成功")
            this.assignVisible = false
            this.getList()
          })
        }
      })
    }
  }
};
</script>

<style scoped>
.image-error {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  background-color: #f5f7fa;
  color: #909399;
  font-size: 12px;
  text-align: center;
}
</style>