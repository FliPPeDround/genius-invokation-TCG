import { defineConfig } from 'tsup'

export default defineConfig({
  entry: [
    'src/index.ts',
  ],
  dts: true,
  format: [
    'esm',
  ],
  target: 'esnext',
  outDir: 'dist',
  clean: true,
})
