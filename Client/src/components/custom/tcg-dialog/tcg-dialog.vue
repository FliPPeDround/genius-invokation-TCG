<script setup lang="ts">
import { ElDialog } from 'element-plus'
import 'element-plus/es/components/dialog/style/css'

interface Props {
  modelValue: boolean
  width?: string
  top?: string
}
const props = defineProps<Props>()

const emit = defineEmits(['update:modelValue'])

const dialogVisible = useVModel(props, 'modelValue', emit)
</script>

<template>
  <ElDialog
    v-model="dialogVisible"
    :width="props.width"
    :top="props.top"
    :show-close="false"
    destroy-on-close
    append-to-body
    bg="white/0"
    shadow="none"
  >
    <slot />
  </ElDialog>
</template>

<style>
.el-dialog {
  background-image: url('./assets/bg-img.png');
  background-size:100% 100%;
  background-repeat:no-repeat;
}
.el-dialog__header {
  display: none;
}
</style>
