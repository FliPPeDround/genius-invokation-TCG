<script setup lang="ts">
interface Props {
  modelValue: boolean
}
const props = defineProps<Props>()
const emit = defineEmits(['update:modelValue'])
const loginDialogVisible = useVModel(props, 'modelValue', emit)

const showPassword = ref(false)
const errorEmail = ref(false)
const email = ref('')
const emitChangeFn = () => {
  if (isEmail(email.value)) {
    showPassword.value = true
    errorEmail.value = false
  }
  else {
    errorEmail.value = true
    showPassword.value = false
  }
}
</script>

<template>
  <tcg-dialog
    v-model="loginDialogVisible"
    width="400px"
  >
    <div
      flex="~ col"
      h-110 p-10
    >
      <h1 font-900 text-4xl color="#243042" mb-2>
        登录你的账户
      </h1>
      <p mb-5>
        还未注册账号？输入邮箱📮自动注册
      </p>
      <div mb-5>
        <span>
          <input
            v-model="email"
            type="text"
            placeholder="请输入邮箱"
            input
            w-60
            @change="emitChangeFn"
          >
        </span>
        <span v-if="errorEmail">
          <p color="red">邮箱格式错误</p>
        </span>
      </div>
      <div v-if="showPassword">
        <span>
          <input
            type="password"
            placeholder="请输入密码"
            input
            w-60
          >
        </span>
      </div>
    </div>
  </tcg-dialog>
</template>
