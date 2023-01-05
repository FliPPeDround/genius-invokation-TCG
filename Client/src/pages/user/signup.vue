<script setup lang="ts">
</script>

<template>
  <!-- 注册页面 -->
  <div class="signup">
    <div class="signup__container">
      <div class="signup__container__title">
        <h1>注册</h1>
      </div>
      <div class="signup__container__form">
        <form>
          <div class="signup__container__form__item">
            <label for="username">用户名</label>
            <input id="username" type="text" placeholder="请输入用户名">
          </div>
          <div class="signup__container__form__item">
            <label for="password">密码</label>
            <input id="password" type="password" placeholder="请输入密码">
          </div>
          <div class="signup__container__form__item">
            <label for="confirmPassword">确认密码</label>
            <input id="confirmPassword" type="password" placeholder="请再次输入密码">
          </div>
          <div class="signup__container__form__item">
            <button class="signup__container__form__item__btn" type="submit">
              注册
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
// 注册页面样式
.signup {
  width: 100%;
  height: 100%;
  background: url(../../assets/images/login-bg.jpg) no-repeat;
  background-size: cover;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
  .signup__container {
    width: 400px;
    height: 400px;
    background: rgba(0, 0, 0, 0.5);
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .signup__container__title {
      width: 100%;
      height: 50px;
      display: flex;
      justify-content: center;
      align-items: center;
      h1 {
        color: #fff;
      }
    }
    .signup__container__form {
      width: 100%;
      height: 300px;
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
        .signup__container__form__item {
          width: 100%;
          height: 50px;
          display: flex;
          justify-content: space-between;
          align-items: center;
          label {
            color: #fff;
          }
          input {
            width: 60%;
            height: 30px;
            border-radius: 5px;
            border: none;
            outline: none;
            padding-left: 10px;
          }
        }
        .signup__container__form__item__btn {
          width: 100%;
          height: 40px;
          border-radius: 5px;
          border: none;
          outline: none;
          background: #fff;
          color: #000;
          cursor: pointer;
        }
      }
    }
  }
}
</style>
