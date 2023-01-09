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
const password = ref('')
const emitChangeFn = async () => {
  if (isEmail(emailValue.value)) {
    const isRegister = await getIsRegisteredByEmail(emailValue.value)
    if (isRegister.data) {
      showPassword.value = true
      isErrorEmail.value = false
    }
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

const allowLoin = computed(() => {
  return !isErrorEmail.value && emailValue.value !== '' && password.value.length > 6
})
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
        <input
          v-model="emailValue"
          type="text"
          placeholder="请输入邮箱"
          input
          w-60
          @change="emitChangeFn"
          @input="emitInputFn"
        >
        <p v-if="isErrorEmail" mt-2 ml-2 color="red">
          邮箱格式错误,请检查后输入
        </p>
      </div>
      <div v-if="showPassword">
        <input
          v-model="password"
          type="password"
          placeholder="请输入密码"
          input
          w-60
        >
      </div>
      <div>
        <button
          btn
          :disabled="!allowLoin"
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
