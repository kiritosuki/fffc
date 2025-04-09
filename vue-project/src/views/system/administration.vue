<script setup>
import { computed, onMounted, ref } from "vue";
import { ElLoading, ElMessage, ElMessageBox } from "element-plus";
import { CheckPatients, DeletePatient } from "../../api/patients";
import { useRouter } from "vue-router";
import { watch } from "vue";
import router from "../../router";

// 初始化
const pageVisible = ref(false)




// 用于存储加载动画的实例
const loading = ref(null);

// 控制 Drawer 显示/隐藏的状态
const isDrawerOpen = ref(false);

// Drawer 的宽度或高度
const drawersize = ref("30%");

// 打开抽屉默认为精确查询
const activeName = ref("first");

// 查询数据类
const checkName = ref("");
const checkGender = ref("");
const checkIdCard = ref("");
const checkPhoneNumber = ref("");
const checkTime = ref("");
const checkPage = ref(1);
const checkPageSize = ref(10);

// 页面大小设置
const pageSizeList = ref([
  {
    value: "选项1",
    label: "10",
  },
  {
    value: "选项2",
    label: "15",
  },
  {
    value: "选项3",
    label: "20",
  },
]);

// 总计患者人数
const totalPatient = ref("");

// 最大页数
const maxPage = computed(() => {
  if (!totalPatient.value) return 1;
  else return Math.ceil(totalPatient.value / checkPageSize.value);
});

// 格式化日期
function formatDate(date) {
  // 检查 date 是否为有效的 Date 对象
  if (!(date instanceof Date) || isNaN(date.getTime())) {
    return "";
  } else {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0"); // 月份从 0 开始，需要加 1
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  }
}

// 默认的异步更新函数
const RefreshRows = async (
  name = checkName.value,
  gender = checkGender.value,
  begin = formatDate(checkTime.value[0]),
  end = formatDate(checkTime.value[1]),
  phone = checkPhoneNumber.value,
  idCard = checkIdCard.value,
  page = checkPage.value,
  pageSize = checkPageSize.value
) => {
  loading.value = ElLoading.service({
    lock: true,
    text: "正在加载...",
    background: "rgba(0, 0, 0, 0.7)",
  });
  try {
    // 发送异步更新请求
    await CheckPatients(
      name,
      gender,
      begin,
      end,
      phone,
      idCard,
      page,
      pageSize
    ).then((res) => {
      if (res.data?.code === 1) {
        // 数据接收
        tableDataRows.value = res.data.data?.rows;
        FixGender();
        console.log(res.data.data.rows);
        totalPatient.value = res.data.data?.total;
      } else {
        ElMessage.error(res.data?.msg);
      }
    });
    ElMessage.success("数据加载成功");
  } catch (error) {
    ElMessage.error("服务器丢失，请稍后再试");
  } finally {
    // 关闭加载动画
    loading.value.close();
  }
};

// 翻页器的更新函数逻辑
const RefreshPage = async (value) => {
  RefreshRows(
    checkName.value,
    checkGender.value,
    formatDate(checkTime.value[0]),
    formatDate(checkTime.value[1]),
    checkPhoneNumber.value,
    checkIdCard.value,
    value,
    checkPageSize.value
  );
};

// 页面尺寸更改更新逻辑
const RefreshPageSize = async (value) => {
  checkPage.value = 1;
  RefreshRows(
    checkName.value,
    checkGender.value,
    formatDate(checkTime.value[0]),
    formatDate(checkTime.value[1]),
    checkPhoneNumber.value,
    checkIdCard.value,
    checkPage.value,
    value
  );
};

// 删除患者并更新逻辑
const DeleteAndRefresh = async (ids) => {
  try {
    // 显示加载动画
    loading.value = ElLoading.service({
      lock: true,
      text: "正在删除...",
      background: "rgba(0, 0, 0, 0.7)",
    });

    await DeletePatient(ids).then((response) => {
      if (response.data?.code === 1) {
        // 重新加载表格数据
        ElMessage.success("删除成功");
        RefreshRows();
      } else {
        ElMessage.error("删除失败");
      }
    });
  } catch (error) {
    ElMessage.error("服务器丢失，请稍后再试");
  } finally {
    loading.value.close();
  }
  try {
    // 发送异步更新请求
    await CheckPatients(
      (name = checkName.value),
      (gender = checkGender.value),
      (begin = formatDate(checkTime.value[0])),
      (end = formatDate(checkTime.value[1])),
      (phone = checkPhoneNumber.value),
      (idCard = checkIdCard.value),
      (page = checkPage.value),
      (pageSize = checkPageSize.value)
    ).then((res) => {
      if (res.data?.code === 1) {
        // 数据接收
        tableDataRows.value = res.data.data?.rows;
        console.log(res.data.data.rows);
        totalPatient.value = res.data.data?.total;
      } else {
        ElMessage.error(res.data?.msg);
      }
    });
    ElMessage.success("数据加载成功");
  } catch (error) {
    ElMessage.error("服务器丢失，请稍后再试");
  } finally {
    // 关闭加载动画
    loading.value.close();
  }
};

// 数据监听与限制
watch(checkIdCard, (newValue) => {
  if (newValue.length > 19) {
    checkIdCard.value = checkIdCard.value.slice(0, 14);
  }
});

watch(checkPhoneNumber, (newValue) => {
  if (newValue.length > 11) {
    checkIdCard.value = checkIdCard.value.slice(0, 11);
  }
});

// 数据库
const tableDataRows = ref([
  {
    id: 1,
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    id: 2,
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
  {
    name: "王小虎",
    gender: 1,
    idCard: "123456789012345678",
    phone: "12345678901",
    diagTime: "2023-07-01",
    doctorName: "Dr. Johnson",
    updateTime: "2023-07-01",
  },
]);

const tableDataRowsFix = ref([])

const FixGender = () => {
  tableDataRowsFix.value = tableDataRows.value.map((item) => {
    return {
      ...item, // 保留原有属性
      gender: item.gender === 1 ? "男" : "女", // 转换 gender 属性
    };
  });
};

// 勾选的数据库
const selectedData = ref([]);

// 将选择的数据加入库
const handleSelectionChange = (val) => {
  selectedData.value = val;
  console.log(selectedData.value);
};

// 一键删除
const DeleteAllSelected = async () => {
  await ElMessageBox.confirm("确定要删除选中的数据吗？", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  });

  // 将所有的id 封装到ids数组
  var ids = selectedData.value.map((item) => item.id);
  DeleteAndRefresh(ids);
};

// 单独删除操作
const handleDelete = async (index, row) => {
  await ElMessageBox.confirm("确定要删除该的数据吗？", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  });
  DeleteAndRefresh([row.id]);
};

// 编辑操作
const handleEdit = async (index, row) => {
  loading.value = ElLoading.service({
    text: "加载中...",
    background: "rgba(0, 0, 0, 0.7)", // 背景颜色
    spinner: "el-icon-loading", // 自定义加载图标
    target: document.body, // 指定加载动画覆盖的区域
  });
  console.log("跳转参数:", {
    path: "/patients/info",
    query: { id: row.id },
  });
  // 跳转组件
  router
    .push({
      path: "/patients/info",
      query: { id: row.id },
    })
    .then(() => {
      console.log("跳转成功");
    })
    .catch((err) => {
      console.error("跳转失败:", err);
    });
  loading.value.close()
};

// 初始化表格数据
onMounted(async () => {
  // 开始加载动画
  loading.value = ElLoading.service({
    text: "加载中...",
    background: "rgba(0, 0, 0, 0.7)", // 背景颜色
    spinner: "el-icon-loading", // 自定义加载图标
    target: document.body, // 指定加载动画覆盖的区域
  });

  // 表格初始化
  try {
    const response = await CheckPatients(
      "",
      "",
      "",
      "",
      "",
      "",
      checkPage.value,
      checkPageSize.value
    );

    // 测试响应数据
    console.log(response);

    if (response.data?.code === 1) {
      totalPatient.value = response.data.data?.total;
      tableDataRows.value = response.data.data?.rows;
      FixGender();
      ElMessage.success("数据加载成功");
    } else if (response.data?.code === 0) {
      ElMessage.error("数据加载失败,请再次刷新");
      // ElMessage.error('数据加载失败,正在刷新中...')
      // await sleep(1000, signal)
      // 刷新页面
      // window.location.reload();
    }
  } catch (error) {
    ElMessage.error("服务器丢失，请稍后再试");
  } finally {
    loading.value.close();
  }

  // 页面加载完成后触发动画
  setTimeout(() => {
    pageVisible.value = true
  }, 100) // 添加微小延迟确保DOM已渲染

});

// 全页面开关抽屉
const handleClick = (tab, event) => {
  console.log(tab, event);
};
// 开启抽屉
const toggleDrawer = () => {
  isDrawerOpen.value = true;
};

// 精确查询
const ExactCheck = async () => {
  checkName.value = "";
  checkGender.value = "";
  checkPage.value = "1";
  checkTime.value = [];
  RefreshRows();
  isDrawerOpen.value = false;
};

// 模糊查询
const VagueCheck = async () => {
  checkPage.value = 1;
  checkIdCard.value = "";
  checkPhoneNumber.value = "";
  RefreshRows();
  isDrawerOpen.value = false;
};

// 测试函数
// const Check = () => {
//   console.log(checkName.value);
//   console.log(checkGender.value);
//   console.log(checkIdCard.value);
//   console.log(checkPhoneNumber.value);
//   console.log(checkTime.value);
//   console.log(formatDate(checkTime.value[0]));
//   console.log(formatDate(checkTime.value[1]));
// }

// 日期快捷选项功能
const pickerOptions = {
  shortcuts: [{
    text: '最近一周',
    onClick(picker) {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
      picker.$emit('pick', [start, end]);
    }
  }, {
    text: '最近一个月',
    onClick(picker) {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
      picker.$emit('pick', [start, end]);
    }
  }, {
    text: '最近三个月',
    onClick(picker) {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
      picker.$emit('pick', [start, end]);
    }
  }]
}

// 这是json格式的请求废稿
// try {
//   const checkbox ={
//     name: checkName.value,
//     gender: checkGender.value,
//     begin: formatDate(checkTime.value[0]),
//     end: formatDate(checkTime.value[1]),
//     phone: checkPhoneNumber.value,
//     idCard: checkIdCard.value,
//     page: checkPage.value,
//     pageSize: checkPageSize.value,
//   }

//   const response = await CheckPatients(checkbox)
//   console.log(response)
//   tableDataRows.value = response.data
//   loading.value.close();
// } catch (error) {
//   console.error('Error fetching data:', error);
// } finally {
//   // 停止加载
//   loading.value.close();
// }
// 翻页器的更新函数逻辑
// const RefreshPage = async (value) => {
//   try {
//     const response = await CheckPatients(
//       checkName.value,
//       checkGender.value,
//       formatDate(checkTime.value[0]),
//       formatDate(checkTime.value[1]),
//       checkPhoneNumber.value,
//       checkIdCard.value,
//       value,
//       checkPageSize.value
//     );
//     if (response.data.code === 1) {
//       tableDataRows.value = response.data.data.rows;
//       ElMessage.success("数据加载成功");
//       isDrawerOpen.value = false;
//     } else {
//       ElMessage.error(response.data.msg);
//     }
//   } catch (error) {
//     ElMessage.error("服务器丢失，请稍后再试");
//   } finally {
//     loading.value.close();
//   }
// };
</script>

<template>
  <div class="page-container">


    <div class="shadebox" :class="{ 'page-visible': pageVisible }">
     <div class="option">
    <!-- 控制 Drawer 的按钮 -->
    <el-button type="primary" @click="toggleDrawer"> 搜索病例 </el-button>
    <el-button type="danger" @click="DeleteAllSelected"> 一键删除 </el-button>
  </div>


      <el-table ref="tableRows" :data="tableDataRowsFix" stripe style="width: 100%" class="table" height="72vh"
        highlight-current-row @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="50"> </el-table-column>

        <el-table-column prop="diagTime" label="诊断日期" width="130">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="90"> </el-table-column>
        <el-table-column prop="gender" label="性别" width="90">
        </el-table-column>
        <el-table-column prop="doctorName" label="诊断医师" width="90"> </el-table-column>
        <el-table-column prop="updateTime" label="最后更新日期" width="">
        </el-table-column>
        <el-table-column label="操作" width="">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
              详细
            </el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pageSelect">
        <el-input-number v-model="checkPage" @change="RefreshPage" :min="1" :max="maxPage"
          label="描述文字"></el-input-number>
      </div>


      <!-- Drawer 组件 -->
      <el-drawer v-model="isDrawerOpen" title="搜索栏" :direction="'rtl'" :size="drawersize" id="drawer">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="精确查询" name="first" id="drawer1">
            <p>身份证号:</p>
            <el-input placeholder="身份证号" v-model="checkIdCard" clearable></el-input>
            <p>电话号码:</p>
            <el-input placeholder="电话号码" v-model="checkPhoneNumber" clearable></el-input>
            <el-button type="primary" class="search-btn" @click="ExactCheck">搜索</el-button>
          </el-tab-pane>

          <el-tab-pane label="模糊查询" name="second" id="drawer2">
            <p>姓名:</p>
            <el-input placeholder="姓名" v-model="checkName" clearable></el-input>
            <p>性别:</p>
            <el-radio v-model="checkGender" label="1">男</el-radio>
            <el-radio v-model="checkGender" label="0">女</el-radio>
            <p>诊断时间：</p>
            <div class="block">
              <el-date-picker v-model="checkTime" type="daterange" style="width: 94%" unlink-panels range-separator="至"
                start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions">
              </el-date-picker>
            </div>
            <el-button type="primary" class="search-btn" @click="VagueCheck">搜索</el-button>
          </el-tab-pane>
        </el-tabs>
      </el-drawer>

      <!-- <div class="pageSelector">
    <el-pagination background layout="prev, pager, next" :total="totalPatient" :page-size="checkPageSize" hide-on-single-page>
    </el-pagination>
    </div> -->
    </div>
  </div>
</template>

<style scoped>
.shadebox {
  opacity: 0;
  transform: translateY(30px);
  transition: all 0.6s cubic-bezier(0.25, 0.1, 0.25, 1);
}

.page-visible {
  opacity: 1;
  transform: translateY(0);
}

.page-container {
  width: 100%;
  height: auto;
  display: inline-block;
  text-align: center;
  padding: 20px;
}

.shadebox {
  width: 85%;
  display: inline-block;
  text-align: center;
  padding: 20px;
  background: #fefefe;
  border-radius: 30px;
  box-shadow: 9px 9px 18px #c3c3c3,
    -9px -9px 18px #e6e6e6;
}
.option {
  text-align: left;
}


.search-btn {
  margin-top: 2vw;
  margin-left: 83%;
}

.table {
  margin-top: 10px;
}

.pageSelect {
  margin-top: 10px;
  text-align: center;
}

#drawer1, #drawer2, #drawer {
  text-align: left;
}
</style>
