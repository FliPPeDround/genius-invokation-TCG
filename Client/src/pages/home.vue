<script setup lang="ts">
const el = ref(null)
const { toggle } = useFullscreen(el)
</script>

<template>
  <div ref="el">
    <button btn>
      创建房间
    </button>
    <button btn>
      加入房间
    </button>
    <button @click="toggle">
      全屏
    </button>
    <button btn>
      登录
    </button>
  </div>
</template>
