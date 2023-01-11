<script setup lang="ts">
const isErrorEmail = ref(false)
const emailValue = ref('')
const password = ref('')
const rePassword = ref('')
const isRegisted = ref(true)
const emailCode = ref('')

const loginLabel = computed(() => {
  return isRegisted.value ? '登录' : '注册'
})
const emailChange = async () => {
  if (!emailValue.value)
    return

  if (isEmail(emailValue.value)) {
    isRegisted.value = (await getIsRegisteredByEmail(emailValue.value)).data
    if (isRegisted.value)
      isErrorEmail.value = false
  }
  else {
    isErrorEmail.value = true
  }
}

const emailInput = () => {
  if (emailValue.value === '')
    isErrorEmail.value = false
}

const rePasswordChangeFn = () => {
  if (password.value === rePassword.value) {
    // isErrorEmail.value = false
  }
}

const loginByEmail = async (email: string, password: string) => {
  // const res = await login({ email, password })
  // if (res.code === 200)
  //   router.push('/')
}

const registerByEmail = async (email: string, password: string, emailCode: string) => {
  // const res = await register({ email, password, emailCode })
  // if (res.code === 200)
  //   router.push('/')
}

const login = () => {
  if (isErrorEmail.value)
    return

  if (isRegisted.value) {
    // 登录
    loginByEmail(emailValue.value, password.value)
  }
  else {
    // 注册
    registerByEmail(emailValue.value, password.value, emailCode.value)
  }
}

const isBtnDisabled = computed(() => {
  if (isRegisted.value)
    return !emailValue.value || !password.value || isErrorEmail.value

  else
    return !emailValue.value || !password.value || !rePassword.value || !emailCode.value || isErrorEmail.value
})
</script>

<template>
  <div
    class="contanier"
    flex items-center justify-center
    h-100vh p-6
  >
    <div class="login-card">
      <div class="column">
        <h1 mb-5 text-4xl font-900>
          {{ loginLabel }}
        </h1>
        <p>快{{ loginLabel }}账号，来一场七圣召唤吧！</p>
        <p color-gray text-sm mt-1>
          {{ isRegisted ? '没有账号?输入邮箱将自动注册' : '该邮箱未绑定账号，现为你注册' }}
        </p>
        <form mt-12>
          <div>
            <input
              v-model="emailValue"
              type="text"
              input
              placeholder="邮箱"
              @change="emailChange"
              @input="emailInput"
            >
          </div>
          <div v-if="isErrorEmail">
            <p color-red text-xs ml-2>
              邮箱格式错误
            </p>
          </div>
          <div
            :class="isErrorEmail ? '' : 'mt-4'"
          >
            <input
              v-model="password"
              type="password"
              input
              placeholder="密码"
            >
          </div>
          <div mt-4>
            <input
              v-if="!isRegisted"
              v-model="emailCode"
              type="password"
              input
              placeholder="再次确认密码"
              @change="rePasswordChangeFn"
            >
          </div>
          <div mt-4>
            <input
              v-if="!isRegisted"
              v-model="rePassword"
              type="text"
              input
              placeholder="邮箱验证码"
              @change="rePasswordChangeFn"
            >
          </div>
          <div flex mt-20 justify-between items-center>
            <button
              btn
              :disabled="isBtnDisabled"
              @click="login"
            >
              {{ loginLabel }}
            </button>
            <a v-if="isRegisted" href="#">忘记密码，点我重置！</a>
          </div>
        </form>
      </div>
      <div class="column" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
$theme-color: #88abaf;
.contanier:after {
    content: "";
    position: fixed;
    inset: 0;
    background-color: $theme-color;
    width: 60%;
    height: 100vh;
    clip-path: polygon(0 100%, 0 0, 100% 0, 70% 100%);
    z-index: -1;
}

.login-card {
    background-color: white;
    border: 1px solid #ddd;
    box-shadow: 0 10px 50px -30px black;
    width: 70rem;
    min-width: 370px;
    border-radius: 30px;
    overflow: hidden;
    display: grid;
    grid-template-columns: 1fr 1fr;

    .column {
        padding: 4rem;

        &:last-child {
            background: url('/logo/login_poster.png') center;
            background-size: cover;
            position: relative;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            gap: 40px;

            &:after {
                content: "";
                position: absolute;
                background: linear-gradient(90deg, white, #ffffff22);
                inset: 0;
            }

            * {
                z-index: 1;
            }
        }
    }
}

//响应式布局
@media(max-width:992px) {
    .contanier:after {
        background-color: #ddd;
    }
    .login-card {
        display: block;
        width: 500px;
        text-align: center;
        background: url('/logo/login_poster.png') center;
        background-size: cover;

        .column {
            &:last-child {
                display: none;
            }
        }
    }
}
</style>
