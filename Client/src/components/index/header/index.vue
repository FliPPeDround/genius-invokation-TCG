<script setup lang="ts">
import TCGLogo from '/logo/tcg_logo.png'

const loginDialogVisible = ref(false)
const openloginDialog = () => {
  loginDialogVisible.value = true
}
</script>

<template>
  <div
    w-full h-18
    flex justify-between items-center
    bg="black/40"
    text-white
    px-8
  >
    <img :src="TCGLogo" alt="TCGLogo" h-18>
    <div
      flex
      justify-between items-center
      color-gray
      hover:color="#F9F4E3"
      cursor-pointer
      transition duration-200 ease-in-out
      @click="openloginDialog"
    >
      <div mr-4 text-xl>
        登&nbsp;录
      </div>
      <div i-carbon:user-avatar-filled w10 h10 />
    </div>
  </div>
  <login v-model="loginDialogVisible" />
</template>
