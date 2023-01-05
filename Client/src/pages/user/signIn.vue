<script lang="ts" setup>

</script>

<template>
  <!-- 登录页面 -->
  <div class="login">
    <div class="login__container">
      <div class="login__container__title">
        <h1>登录</h1>
      </div>
      <div class="login__container__form">
        <form>
          <div class="login__container__form__item">
            <label for="username">用户名</label>
            <input id="username" type="text" placeholder="请输入用户名">
          </div>
          <div class="login__container__form__item">
            <label for="password">密码</label>
            <input id="password" type="password" placeholder="请输入密码">
          </div>
          <div class="login__container__form__item">
            <button class="login__container__form__item__btn" type="submit">
              登录
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
// 游戏风格登录页面样式
.login {
  width: 100%;
  height: 100%;
  background: url(../../assets/images/login-bg.jpg) no-repeat;
  background-size: cover;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
  .login__container {
    width: 400px;
    height: 400px;
    background: rgba(0, 0, 0, 0.5);
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .login__container__title {
      width: 100%;
      height: 50px;
      display: flex;
      justify-content: center;
      align-items: center;
      h1 {
        color: #fff;
        font-size: 24px;
      }
    }
    .login__container__form {
      width: 100%;
      height: 350px;
      display: flex;
      justify-content: center;
      align-items: center;
      form {
        width: 80%;
        height: 80%;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
        .login__container__form__item {
          width: 100%;
          height: 50px;
          display: flex;
          justify-content: center;
          align-items: center;
          label {
            width: 30%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
            font-size: 18px;
          }
          input {
            width: 70%;
            height: 100%;
            border: none;
            border-radius: 5px;
            padding: 0 10px;
            outline: none;
          }
        }
        .login__container__form__item__btn {
          width: 100%;
          height: 100%;
          border: none;
          border-radius: 5px;
          background: #fff;
          color: #000;
          font-size: 18px;
          outline: none;
          cursor: pointer;
        }
      }
    }
  }
}
</style>
