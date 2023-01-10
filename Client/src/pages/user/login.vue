<script setup lang="ts"></script>

<template>
  <div class="contanier">
    <div class="login-card">
      <div class="column">
        <h1>登录</h1>
        <p>想来一局七圣召唤吗？请输入账号密码！</p>
        <form>
          <div class="form-item">
            <input type="text" class="form-element" placeholder="用户名">
          </div>
          <div class="form-item">
            <input type="password" class="form-element" placeholder="密码">
          </div>
          <div class="form-checkbox-item">
            <input id="rememberMe" type="checkbox" checked>
            <label for="rememberMe">记住我</label>
          </div>
          <div class="flex">
            <button type="button">
              登陆
            </button>
            <a href="#">忘记密码，点我重置！</a>
          </div>
          <p style="margin-top: 3rem; margin-bottom: 1.5rem">
            第三方账号登入
          </p>
          <div class="social-buttons">
            <a href="#" class="wechat">
              <i class="bi bi-wechat" />
            </a>
            <a href="#" class="twitter">
              <i class="bi bi-twitter" />
            </a>
            <a href="#" class="github">
              <i class="bi bi-github" />
            </a>
          </div>
        </form>
      </div>
      <div class="column">
        <h2>自然选择号欢迎您登舰！</h2>
        <p>如果你没有账号，你想要现在注册一个吗？</p>
        <a href="#">注册</a>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
$theme-color: #88abaf;
.contanier {
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
    padding: 1.5rem;
    height: 100vh;

    &:after {
        content: "";
        position: fixed;
        inset: 0;
        background-color: $theme-color;
        width: 60%;
        height: 100vh;
        clip-path: polygon(0 100%, 0 0, 100% 0, 70% 100%);
        z-index: -1;
    }
}

.login-card {
    background-color: white;
    border: 1px solid #ddd;
    box-shadow: 0 10px 50px -30px black;
    width: 1200px;
    border-radius: 30px;
    overflow: hidden;
    display: grid;
    grid-template-columns: auto auto;

    a {
        text-decoration: none;
        color: $theme-color;
    }

    .column {
        padding: 4rem;

        &:last-child {
            background: url('./../../../public/logo/login_poster.png') center;
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

            a {
                display: inline-block;
                padding: 12px 30px;
                font-size: 16px;
                border: 2px solid $theme-color;
                color: black;
                border-radius: 50px;
                cursor: pointer;
                transition: all 0.3;
                font-weight: 600;

                &:hover {
                    background-color: $theme-color;
                    color: white;
                }
            }
        }

        &:last-child {
            text-align: center;
        }
    }

    h1 {
        margin-bottom: 15px;
    }

    .form-element {
        width: 100%;
        border: none;
        background-color: white;
        padding: 20px 30px;
        font-size: 18px;
        border-radius: 50px;
        transition: box-shadow 0.2s;

        &:focus {
            outline: none;
            box-shadow: 0 0 0 2px $theme-color;
        }
    }

    input[type=checkbox] {
        accent-color: $theme-color;
        width: 16px;
        height: 16px;
    }

    form {
        margin-top: 3rem;

        &>* {
            margin-top: 1rem;
        }

        button {
            background-color: $theme-color;
            color: white;
            border: none;
            padding: 20px 40px;
            border-radius: 50px;
            cursor: pointer;
            font-weight: 600;
            font-size: 18px;
            transition: all 0.2s;

            &:active {
                scale: 0.95;
                background-color: darken($theme-color, 10%);
            }
        }

        .form-check-item {
            display: flex;
            align-items: center;
            gap: 0.7rem;
            margin-left: 5px;
        }
    }
}

.flex {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.social-buttons {
    display: flex;
    gap: 1rem;

    a {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 50px;
        height: 50px;
        border-radius: 50px;
        font-size: 22px;
        color: white;
        background-color: green;

        &.wechat {
            background-color: #06ca5c;
        }

        &.twitter {
            background-color: #55acee;
        }

        &.github {
            background-color: #111;
        }

        &:hover {
            scale: 0.95;
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
        background: url('./../../../public/logo/login_poster.png') center;
        background-size: cover;

        .column {
            &:last-child {
                display: none;
            }
        }
    }

    .flex {
        flex-direction: column;
        gap: 1rem;
    }

    .social-buttons {
        justify-content: center;
    }
}
</style>
