<script setup lang="ts">
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
          登录
        </h1>
        <p>快登录账号，来一场七圣召唤吧！</p>
        <p color-gray text-sm mt-1>
          没有账号?输入邮箱将自动注册
        </p>
        <form mt-12>
          <div>
            <input
              v-model="emailValue"
              type="text"
              input
              placeholder="邮箱"
              @change="emitChangeFn"
              @input="emitInputFn"
            >
          </div>
          <div mt-4>
            <input
              v-model="password"
              type="password"
              input
              placeholder="密码"
            >
          </div>
          <div flex mt-20 justify-between items-center>
            <button btn>
              登陆
            </button>
            <a href="#">忘记密码，点我重置！</a>
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
