<script setup lang="ts">
interface Props {
  modelValue: boolean
}
const props = defineProps<Props>()
const emit = defineEmits(['update:modelValue'])
const loginDialogVisible = useVModel(props, 'modelValue', emit)

const showPassword = ref(false)
const isErrorEmail = ref(false)
const emailValue = ref('')
const emitChangeFn = () => {
  if (isEmail(emailValue.value)) {
    showPassword.value = true
    isErrorEmail.value = false
  }
  else {
    isErrorEmail.value = true
    showPassword.value = false
  }
}

const emitInputFn = () => {
  if (emailValue.value === '') {
    isErrorEmail.value = false
    showPassword.value = false
  }
}

const login = () => {
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
        <div>
          <input
            v-model="emailValue"
            type="text"
            placeholder="请输入邮箱"
            input
            w-60
            @change="emitChangeFn"
            @input="emitInputFn"
          >
        </div>
        <div v-if="isErrorEmail" mt-2 ml-2>
          <p color="red">
            邮箱格式错误,请检查后输入
          </p>
        </div>
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
      <div>
        <button
          btn
          :disabled="!showPassword"
          w-60
          h-10 mt-10
          text-lg
          @click="login"
        >
          登&nbsp;&nbsp;&nbsp;&nbsp;录
        </button>
      </div>
    </div>
  </tcg-dialog>
</template>
