假设您使用的是 Vue 3 和 Vue CLI。

### 1. 安装依赖

首先确保安装了 Axios：

```
bash
npm install axios
```

### 2. 文件结构

```
plaintext复制编辑src/
  ├── components/
  │   ├── PatientList.vue      // 病例列表组件
  │   ├── PatientDelete.vue    // 删除病例组件
  ├── services/
  │   ├── patientService.js   // 请求相关逻辑
  ├── App.vue                 // 主组件
  ├── main.js                 // Vue 入口文件
```

### 3. 请求相关逻辑 - `services/patientService.js`

```
javascript复制编辑import axios from 'axios';

// 设置Axios基础路径
const API_URL = 'http://your-api-url/patients';

export const fetchPatients = (params) => {
  return axios.get(API_URL, { params });
};

export const deletePatients = (ids) => {
  return axios.delete(API_URL, { params: { ids: ids.join(',') } });
};
```

### 4. 病例列表组件 - `components/PatientList.vue`

```
vue复制编辑<template>
  <div>
    <form @submit.prevent="handleSearch">
      <input v-model="query.name" type="text" placeholder="姓名" />
      <select v-model="query.gender">
        <option value="">选择性别</option>
        <option value="0">女</option>
        <option value="1">男</option>
      </select>
      <input v-model="query.begin" type="date" />
      <input v-model="query.end" type="date" />
      <button type="submit">查询</button>
    </form>

    <div v-if="loading">Loading...</div>
    <table v-else>
      <thead>
        <tr>
          <th>姓名</th>
          <th>年龄</th>
          <th>性别</th>
          <th>诊断日期</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="patient in patients" :key="patient.id">
          <td>{{ patient.name }}</td>
          <td>{{ patient.age }}</td>
          <td>{{ patient.gender === 1 ? '男' : '女' }}</td>
          <td>{{ patient.diagTime }}</td>
          <td>
            <PatientDelete :patientId="patient.id" @deleted="fetchPatientsList" />
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { fetchPatients } from '../services/patientService';
import PatientDelete from './PatientDelete.vue';

export default {
  components: {
    PatientDelete,
  },
  setup() {
    const patients = ref([]);
    const loading = ref(false);
    const query = ref({
      name: '',
      gender: '',
      begin: '',
      end: '',
      page: 1,
      pageSize: 10,
    });

    const fetchPatientsList = async () => {
      loading.value = true;
      try {
        const response = await fetchPatients(query.value);
        patients.value = response.data.data.rows;
      } catch (error) {
        console.error('Error fetching patients:', error);
      } finally {
        loading.value = false;
      }
    };

    const handleSearch = () => {
      query.value.page = 1; // Reset to first page on search
      fetchPatientsList();
    };

    onMounted(fetchPatientsList);

    return {
      patients,
      loading,
      query,
      fetchPatientsList,
      handleSearch,
    };
  },
};
</script>
```

### 5. 删除病例组件 - `components/PatientDelete.vue`

```
vue复制编辑<template>
  <div>
    <button @click="handleDelete" :disabled="loading">{{ loading ? '删除中...' : '删除' }}</button>
    <p v-if="error" style="color: red">{{ error }}</p>
  </div>
</template>

<script>
import { ref } from 'vue';
import { deletePatients } from '../services/patientService';

export default {
  props: {
    patientId: {
      type: Number,
      required: true,
    },
  },
  emits: ['deleted'],
  setup(props, { emit }) {
    const loading = ref(false);
    const error = ref('');

    const handleDelete = async () => {
      loading.value = true;
      try {
        await deletePatients([props.patientId]);
        emit('deleted'); // Notify parent to refresh the list
        alert('病例删除成功');
      } catch (err) {
        error.value = '删除失败';
      } finally {
        loading.value = false;
      }
    };

    return {
      loading,
      error,
      handleDelete,
    };
  },
};
</script>
```

### 6. 主组件 - `App.vue`

```
vue复制编辑<template>
  <div>
    <h1>病例管理系统</h1>
    <PatientList />
  </div>
</template>

<script>
import PatientList from './components/PatientList.vue';

export default {
  components: {
    PatientList,
  },
};
</script>
```

### 7. 启动项目

```
bash


复制编辑
npm run serve
```

### 8. 简要说明

- **查询病历列表**：用户可以根据姓名、性别、日期等条件查询病历，表格会动态更新。使用 `v-model` 双向绑定来处理表单输入。
- **删除病历**：点击每行的删除按钮，调用 `PatientDelete` 组件中的删除功能。删除成功后，触发 `@deleted` 事件来刷新父组件的病历列表。