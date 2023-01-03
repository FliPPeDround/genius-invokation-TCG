<script setup lang="ts">
interface Props {
  character: string
  hp: number
}
const { character, hp } = defineProps<Props>()

const getCharacterSrc = (name: string): string => {
  const picModules = import.meta.glob('./assets/*.png', { eager: true })
  const path = `./assets/${name}.png`
  return (picModules[path]! as { default: string }).default
}
</script>

<template>
  <div
    relative
    w-20vw h-34vw
    m="t-2vw l-1.2vw"
  >
    <img
      :src="getCharacterSrc(character)"
      absolute
      w-20vw h-34vw
      v-bottom
    >
    <div
      class="bg-bar"
    />
    <div
      class="hp-icon"
    >
      {{ hp }}
    </div>
  </div>
</template>

<style scoped>
.bg-bar{
  width: 20vw;
  height: 34vw;
  background: url(/cards/card_bg.png);
  background-size: 100% 100%;
  position: absolute;
  left: 0;
  top: 0;
}
.hp-icon {
  position: absolute;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
  font-size: 4.5vw;
  color: #fff;
  background-size: 100% 100%;
  width: 6.4vw;
  height: 7.4vw;
  left: -1vw;
  top: -1.9vw;
  background-image:url(/cards/hp_icon.png);
  font-weight: 500;
}
</style>
